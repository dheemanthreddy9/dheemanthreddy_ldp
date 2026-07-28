let draggedItemId = null;
const enableDrag = () => {
    const menuCards = document.querySelectorAll(".menu-card");
    menuCards.forEach((card) => {
        card.addEventListener("dragstart", () => {
            draggedItemId = Number(card.dataset.id);
        });
    });
};

const enableDrop = () => {
    const tableCards = document.querySelectorAll(".table-card");
    tableCards.forEach((card) => {
        card.addEventListener("dragover", (event) => {
            event.preventDefault();
        });

        card.addEventListener("drop", () => {
            const tableId = Number(card.dataset.id);
            const table = tables.find((table) => {
                return table.id === tableId;
            });
            const menuItem = menu.find((item) => {
                return item.id === draggedItemId;
            });
            if (!table || !menuItem) {
                return;
            }
            const existingItem = table.orders.find((order) => {
                return order.id === menuItem.id;
            });
            if (existingItem) {
                existingItem.quantity++;
            }
            else {
                table.orders.push({
                    id: menuItem.id,
                    name: menuItem.name,
                    price: menuItem.price,
                    quantity: 1
                });
            }

            table.totalItems++;
            table.totalPrice += menuItem.price;
            renderTables();
        });
    });
};

const startDragDrop = () => {
    enableDrag();
    enableDrop();
};