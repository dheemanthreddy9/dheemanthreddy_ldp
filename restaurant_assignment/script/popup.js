let currentTable = null;

const openPopup = (tableId) => {
    currentTable = tables.find((table) => {
        return table.id === tableId;
    });
    if (!currentTable) {
        return;
    }

    document.querySelector(".popup").style.display = "block";
    displayOrders();
};

const closePopup = () => {
    document.querySelector(".popup").style.display = "none";
};

document.querySelector(".close-btn").addEventListener("click", () => {
    closePopup();
});


const displayOrders = () => {
    const orderList = document.querySelector(".order-list");
    const total = document.querySelector(".total-section h3");
    orderList.innerHTML = "";
    if (currentTable.orders.length === 0) {
        orderList.innerHTML = `
            <tr>
                <td colspan="5">
                    No Items Ordered
                </td>
            </tr>
        `;
    }

    currentTable.orders.forEach((item, index) => {
        orderList.innerHTML += `
            <tr>
                <td>${index + 1}</td>
                <td>${item.name}</td>
                <td>₹${item.price}</td>
                <td>
                    <button onclick="decreaseQuantity(${item.id})">-</button>
                    ${item.quantity}
                    <button onclick="increaseQuantity(${item.id})">+</button>
                </td>
                <td>
                    <button onclick="deleteItem(${item.id})">
                        Delete
                    </button>
                </td>
            </tr>
        `;
    });
    total.innerHTML = `Total : ₹${currentTable.totalPrice}`;
};

const increaseQuantity = (itemId) => {
    const item = currentTable.orders.find((order) => {
        return order.id === itemId;
    });
    if (!item) {
        return;
    }
    item.quantity++;
    currentTable.totalItems++;
    currentTable.totalPrice += item.price;
    renderTables();
    displayOrders();
};


const decreaseQuantity = (itemId) => {
    const item = currentTable.orders.find((order) => {
        return order.id === itemId;
    });
    if (!item) {
        return;
    }
    if (item.quantity > 1) {
        item.quantity--;
        currentTable.totalItems--;
        currentTable.totalPrice -= item.price;
    }
    else {
        deleteItem(itemId);
        return;
    }
    renderTables();
    displayOrders();
};


const deleteItem = (itemId) => {
    const item = currentTable.orders.find((order) => {
        return order.id === itemId;
    });
    if (!item) {
        return;
    }
    currentTable.totalItems -= item.quantity;
    currentTable.totalPrice -= item.price * item.quantity;
    currentTable.orders = currentTable.orders.filter((order) => {
        return order.id !== itemId;
    });
    renderTables();
    displayOrders();
};