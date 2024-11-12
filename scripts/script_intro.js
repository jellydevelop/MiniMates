// -------------------VARIABLES

// -------------------IDS

const btn_redi=document.getElementById("btn_iniciarJuego");
const elementoAudio = document.getElementById("audio");
const btn_audio = document.getElementById("audio_mati");


// -------------------FUNCIONES

function rediMapa(evento){

    if(evento){

        window.location.href = "/mapaMundo1P1";
    }
}

// -*****************************
function audioPlay(evento){

    elementoAudio.play().catch(error => {
        console.log('Error al reproducir el audio:', error);
    });
}
// -*****************************
function inicioAudio() {

    setTimeout(() => {
        elementoAudio.play().catch(error => {
            console.log('Autoplay was prevented:', error);
        });
    }, 1500); // 1.5 segundos después de cargar la página
}


// -------------------LISTENERS

btn_redi.addEventListener("click",rediMapa);
audio.addEventListener("click",audioPlay);

//  listener para el evento que carga el audio al cargar la web => auto lectura
document.addEventListener("DOMContentLoaded", inicioAudio);
window.onload = alert("****** MUNDO 1 ******");
////////////////////PENDIENTE DE ELIMINAR PORQUE NO ME CONVENCE