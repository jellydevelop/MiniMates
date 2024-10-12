//----ID
let aSesion=document.getElementById('btn');

//----FUNCIONES
function redi_sesion(evento){

    if(evento){
        window.location.href='/login';
    }
}

//----LISTENER
aSesion.addEventListener('click',redi_sesion);
