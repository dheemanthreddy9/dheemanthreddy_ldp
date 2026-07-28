const searchTables = () => {
    const tableSearch = document.querySelector("#table-search");
    tableSearch.addEventListener("keyup", () => {
        const searchValue = tableSearch.value.toLowerCase();
        const filteredTables = tables.filter((table) => {
            return table.name.toLowerCase().includes(searchValue);
        });
        renderTables(filteredTables);
    });
};

const searchMenu = () => {
    const menuSearch = document.querySelector("#menu-search");
    menuSearch.addEventListener("keyup", () => {
        const searchValue = menuSearch.value.toLowerCase();
        const filteredMenu = menu.filter((item) => {
            return (
                item.name.toLowerCase().includes(searchValue) ||
                item.course.toLowerCase().includes(searchValue)
            );
        });
        renderMenu(filteredMenu);
    });
};