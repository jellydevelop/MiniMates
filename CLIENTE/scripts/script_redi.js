//----ID
let aSesion=document.getElementById('btn');

//----FUNCIONES
function redi_sesion(evento){

    if(evento){
        window.location.href='login_pag2.html';
    }
}

//----LISTENER
aSesion.addEventListener('click',redi_sesion);
