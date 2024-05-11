fetch('http://localhost:8080/qrMenus')
  .then(response => response.json())
  .then(data => {
    const menuContainer = document.getElementById('menuContainer');
    data.forEach(category => {
      let categoryElement = document.createElement('div');
      categoryElement.className = 'col-lg-3 mb-4';
      categoryElement.innerHTML = `<div class="card">
                                      <div class="card-header">${category.name}</div>
                                      <ul class="list-group list-group-flush" id="menuList-${category.id}"></ul>
                                    </div>`;
      
      menuContainer.appendChild(categoryElement);
      
      let menuList = document.getElementById(`menuList-${category.id}`);
      category.menus.forEach(menu => {
        let menuItem = document.createElement('li');
        menuItem.className = 'list-group-item';
        menuItem.textContent = `${menu.name} - ${menu.price} TL`;
        menuList.appendChild(menuItem);
      });
    });
  });
