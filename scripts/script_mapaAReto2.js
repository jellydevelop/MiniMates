// -------------------VARIABLES

// -------------------IDS
const btn_aInicio = document.getElementById("btn_inicio");
const btn_aReto1 = document.getElementById("btn_reto1");
const btn_aReto2 = document.getElementById("btn_reto2");
const mati = document.querySelector("#mati img");
const audio = document.getElementById("audio_reto2");

// -------------------LISTENERS

window.onload = function() {
    setTimeout(irAReto1, 1000); // Ejecuta la función después de 1 segundo
};

// -------------------FUNCIONES

function audioPlay() {
    audio.play(); // Reproduce el audio
}

// Función para mover a Mati de Inicio a Reto 1
function irAReto1() {
    btn_aInicio.style.background = "lightgreen";
    mati.style.animation = "inicioReto1 2s forwards";

    // Reproducir el audio
    audioPlay();

    // Cuando termine la animación `inicioReto1`, agregar la clase de posición y ejecutar `reto1Reto2`
    mati.addEventListener("animationend", () => {
        // Posicionar sobre Reto 1
        mati.classList.add("posicion-reto1");
        
        // Llamar a la segunda animación
        setTimeout(reto1Reto2, 500); // Espera un poco para iniciar la segunda animación
    }, { once: true }); // `{ once: true }` asegura que el listener solo se ejecute una vez
}

// Función para mover a Mati de Reto 1 a Reto 2
function reto1Reto2() {
    btn_aReto1.style.background = "lightgreen";
    mati.style.animation = "reto1Reto2 2s forwards"; 

    // Reproducir el audio
    audioPlay();

    // Cuando termine la animación, redirigir a la siguiente pantalla
    mati.addEventListener("animationend", function() {
        window.location.href = "/reto2M1";  // Redirige a la pantalla de Reto 2
    }, { once: true });
}

