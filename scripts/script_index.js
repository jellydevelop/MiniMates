// -------------------VARIABLES
const btn_redi = document.getElementById("btn_index");

// -------------------LISTENERS
btn_redi.addEventListener("click", rediLogin); // Redirigir al login al hacer clic en el botón

// -------------------FUNCIONES

//  carga el audio y redirige a la página de login al hacer clic en el botón
function rediLogin(evento) {
    // Crear un objeto de audio
    const audio = new Audio('/audios/childrens-comedy-131842.mp3');

    // Reproducir el audio cuando se hace clic en el botón
    audio.play();

    // Redirigir a la página de login después de que el audio haya comenzado a reproducirse
    window.location.href = "/login";
}

