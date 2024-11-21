// -------------------VARIABLES

// -------------------IDS
const btn_aInicio=document.getElementById("btn_inicio");
const btn_aReto1=document.getElementById("btn_reto1");
const btn_aReto2=document.getElementById("btn_reto2");
const mati = document.getElementById('mati');
const audio=document.getElementById('audio_reto2');


// -------------------LISTENERS

window.onload = function() {
    setTimeout(reto1Reto2, 2000);
}
// -------------------FUNCIONES

function audioPlay(){

    audio.play();

}

// -*****************************

function reto1Reto2(){

    const mati = document.querySelector("#mati img");

    btn_aInicio.style.background="lightgreen";

    //llamada a la animacion boton inicio a boton reto1
    mati.style.animation = "inicioReto1 2s forwards";

    btn_reto1.style.background="lightgreen";

        //llamada a la animacion boton inicio a boton reto1
    mati.style.animation = "reto1Reto2  2s forwards"; 

    // Reproducir el audio antes de redirigir
    audioPlay();

     //dejamos hueco de tiempo para que se redireccione
    ////a la pantalla 2
    setTimeout(() => {
        window.location.href = "pantalla2_M1.html";
    }, 2000);
}

