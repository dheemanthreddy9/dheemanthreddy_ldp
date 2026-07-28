const generateBill = () => {
    if (!currentTable) {
        return;
    }
    if (currentTable.orders.length === 0) {
        alert("No items ordered.");
        return;
    }

    let message = "Restaurant Bill\n\n";
    currentTable.orders.forEach((item) => {
        message += `${item.name} x ${item.quantity} = ₹${item.price * item.quantity}\n`;
    });

    message += "\n-------------------------\n";
    message += `Total Amount : ₹${currentTable.totalPrice}`;
    alert(message);
    currentTable.orders = [];
    currentTable.totalItems = 0;
    currentTable.totalPrice = 0;
    renderTables();
    closePopup();
};

document.querySelector(".bill-btn").addEventListener("click", () => {
    generateBill();
});