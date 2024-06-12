// -------------------VARIABLES

// -------------------IDS
const btn_redi=document.getElementById("btn_continuar");
const elementoAudio = document.getElementById("audio");

// -------------------FUNCIONES

function audioPlay(evento){

    if(evento){

        elementoAudio.play();
    }
}

// -*****************************
function rediIntro(evento){

    if(evento){

        window.location.href = "introMundo1_pagi4.html";
    }
    
}

// -------------------LISTENERS

document.getElementById("audio_mati").addEventListener("click", audioPlay);

audio.addEventListener("click",audioPlay);

btn_redi.addEventListener("click",rediIntro);