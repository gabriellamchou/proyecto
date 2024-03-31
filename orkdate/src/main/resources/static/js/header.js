const userButton = document.getElementById("userButton");

if (userButton != null) {
    userButton.addEventListener("click", showMenu);
}

function showMenu() {
    document.getElementById("userDropdown").classList.toggle("show");
}

window.addEventListener("click", function (event) {
    if (!event.target.matches('.dropbtn')) {
        const dropdowns = document.getElementsByClassName("dropdown-content");
        for (let i = 0; i < dropdowns.length; i++) {
            const openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
});