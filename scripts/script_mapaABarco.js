// -------------------VARIABLES

// -------------------IDS
const btn_aInicio = document.getElementById("btn_inicio");
const btn_aReto1 = document.getElementById("btn_reto1");
const btn_aReto2 = document.getElementById("btn_reto2");
const mati = document.querySelector("#mati img");
const audio = document.getElementById("audio_reto2");

// -------------------LISTENERS

window.onload = function() {
    setTimeout(irAReto2, 1000); // Ejecuta la función después de 1 segundo
};

// -------------------FUNCIONES

function audioPlay() {
    audio.play(); // Reproduce el audio
}

// Función para mover a Mati de Reto 1 a Reto 2
function irAReto2() {
		    btn_aInicio.style.background = "lightgreen";
    btn_aReto1.style.background = "lightgreen";
    mati.style.animation = "reto1Reto2 2s forwards";

    // Reproducir el audio
    audioPlay();

    // Cuando termine la animación `reto1Reto2`, agregar la clase de posición y ejecutar `reto3Barco`
    mati.addEventListener("animationend", () => {
        // Posicionar sobre Reto 2
        mati.classList.add("posicion-reto2");

        // Llamar a la animación para el último tramo hacia el barco
        setTimeout(reto3Barco, 500); // Espera un poco para iniciar la animación final
    }, { once: true });
}

// Función para mover a Mati de Reto 2 al barco
function reto3Barco() {
	
    btn_aReto2.style.background = "lightgreen";
    mati.style.animation = "reto2Barco 2s forwards"; 

    // Reproducir el audio
    audioPlay();

    // Cuando termine la animación, redirigir a la siguiente pantalla
    mati.addEventListener("animationend", function() {
        window.location.href = "/descanso";  // Redirige a la pantalla de descanso o barco
    }, { once: true });
}

