setTimeout(
    function() {
        elementos = document.querySelectorAll(".mensaje-temporal");
        for (let j = 0; j < elementos.length; j++) {
            elementos[j].style.display = "none";
        }
    }
    , 3000);
