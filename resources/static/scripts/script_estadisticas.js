document.addEventListener('DOMContentLoaded', function() {


//----------------VERIFICACION DE PARTIDA YA JUGADA

    // Verificar si el alumno ha jugado antes
    if (localStorage.getItem('haJugado')) {

        // Si ha jugado, ocultar la opción de jugar
        const jugarOption = document.getElementById('dataPartidas');
        
        if (jugarOption) {
            jugarOption.hidden=true;
        }
    }
    

//----------------ID
const botonExit = document.getElementById('exit');
const botonAtras = document.getElementById('rediAtras');

//----------------FUNCIONES


//************************** */

function rediMenuAlumno(evento){
    window.location.href = '/alumno'; 

}

//************************** */

function salirSesion() {
    localStorage.removeItem('haJugado'); 
    window.location.href = '/login'; 
}

//----------------LISTENERS

botonExit.addEventListener('click', salirSesion);
botonAtras.addEventListener('click', rediMenuAlumno);



});

