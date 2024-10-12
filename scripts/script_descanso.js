
// -------------------IDS

const btn_redi=document.getElementById("btn_descanso");


// -------------------LISTENERS

btn_redi.addEventListener("click",rediMenu);

// -------------------FUNCIONES

function rediMenu(evento){

    // creamos variable para guardar que ya se ha jugado una partida
    localStorage.setItem('haJugado', true);

    if(evento){

        window.location.href = "/alumno";
    }
}