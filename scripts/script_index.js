// -------------------VARIABLES

// -------------------IDS

const btn_redi=document.getElementById("btn_index");


// -------------------LISTENERS

btn_redi.addEventListener("click",rediLogin);

// -------------------FUNCIONES

function rediLogin(evento){

    if(evento){

        window.location.href = "/login";
    }
}