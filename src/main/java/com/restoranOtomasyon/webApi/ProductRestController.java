package com.restoranOtomasyon.webApi;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.dataAccess.abstracts.OTUARepository;
import com.restoranOtomasyon.dataAccess.abstracts.ProductRepository;
import com.restoranOtomasyon.dataAccess.abstracts.UsageAmountRepository;
import com.restoranOtomasyon.entities.concretes.OverTimeUsageAmount;
import com.restoranOtomasyon.entities.concretes.Product;
import com.restoranOtomasyon.entities.concretes.UsageAmount;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductRestController {

    private ProductRepository productRepository;
    private UsageAmountRepository usageAmountRepository;
    private OTUARepository overTimeUsageAmountRepository;

    @PostMapping("/updateProductQuantity")
    public void updateProductQuantity(@RequestParam int productId, @RequestParam double usageAmount) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        product.setQuantity(product.getQuantity() - usageAmount);
        productRepository.save(product);

        Optional<UsageAmount> existingUsageAmount = usageAmountRepository.findByProductId(product.getProductId());
        UsageAmount usageAmount2;
        if (existingUsageAmount.isPresent()) {
            usageAmount2 = existingUsageAmount.get();
            usageAmount2.setNumberOfDays(usageAmount2.getNumberOfDays() + 1);
            usageAmount2.setUsageAmount(usageAmount2.getUsageAmount() + usageAmount);
        } else {
            usageAmount2 = new UsageAmount();
            usageAmount2.setNumberOfDays(1);
            usageAmount2.setUsageAmount(usageAmount);
            usageAmount2.setProductId(product.getProductId());
        }
        usageAmountRepository.save(usageAmount2);

        Optional<OverTimeUsageAmount> existingOverTimeUsageAmount = overTimeUsageAmountRepository.findByProductId(productId);
        OverTimeUsageAmount otua;
        if (existingOverTimeUsageAmount.isPresent()) {
            otua = existingOverTimeUsageAmount.get();
        } else {
            otua = new OverTimeUsageAmount();
            otua.setProductId(product.getProductId());
            otua.setProductName(product.getProductName());
            otua.setProductQuantity(product.getQuantity());
        }

        otua.setDailyUsageAmount(usageAmount2.getUsageAmount() / usageAmount2.getNumberOfDays());
        otua.setWeeklyUsageAmount(usageAmount2.getUsageAmount() / usageAmount2.getNumberOfDays() * 7);
        otua.setMonthlyUsageAmount(usageAmount2.getUsageAmount() / usageAmount2.getNumberOfDays() * 30);
        otua.setProductQuantity(product.getQuantity());

        overTimeUsageAmountRepository.save(otua);
    }
}
