const renderTables = (tableData = tables) => {
    const tablesContainer = document.querySelector(".tables-container");
    tablesContainer.innerHTML = "";
    tableData.forEach((table) => {
        tablesContainer.innerHTML += `
            <div
                class="table-card"
                data-id="${table.id}"
                onclick="openPopup(${table.id})"
            >
                <h3>${table.name}</h3>
                <p>Rs. ${table.totalPrice} | Total Items: ${table.totalItems}</p>
            </div>
        `;
    });

    enableDrop();
};



const renderMenu = (menuData = menu) => {
    const menuContainer = document.querySelector(".menu-container");
    menuContainer.innerHTML = "";
    menuData.forEach((item) => {
        menuContainer.innerHTML += `
            <div
                class="menu-card"
                draggable="true"
                data-id="${item.id}"
            >
                <h3>${item.name}</h3>
                <p>${item.course}</p>
                <strong>₹${item.price}</strong>
            </div>
        `;
    });
    enableDrag();
};
