document.addEventListener('DOMContentLoaded', function() {
		//----------------DEFINIMOS EL ROL DLE USUARIO PARA BLOQUEAR PERFILES
	localStorage.setItem('rolUsuario', 'esAlumno');

//----------------VERIFICACION DE PARTIDA YA JUGADA

    // Verificar si el alumno ha jugado antes
    if (localStorage.getItem('haJugado')) {

        // Si ha jugado, ocultar la opción de jugar
        const jugarOption = document.getElementById('dataPartidas');
        const labelMenuPlay = document.getElementById("lPlay");

        if (jugarOption) {
            jugarOption.hidden=true;
            labelMenuPlay.hidden=true;
        }
    }
    

//----------------ID
const botonStats =document.getElementById('statsAlum');
const botonJugar =document.getElementById('dataPartidas');
const botonExit = document.getElementById('exit');
const botonContact = document.getElementById('goContacto');
const labelMenuStats = document.getElementById("lStats");
const labelMenuPlay = document.getElementById("lPlay");



//----------------FUNCIONES


//************************** */

function rediAlumnoStats() {
    window.location.href = '/tus_estadisticas'; 
}

//************************** */

function salirSesion() {
    localStorage.removeItem('haJugado'); 
        localStorage.removeItem('emailUsuario'); 

    window.location.href = '/login'; 
}
//************************** */

function rediAlumContacto() {
    window.location.href = '/contacto'; 
}

//************************** */

function rediAlumnoPlay() {
    window.location.href = '/presentacionMati'; 
}
//----------------LISTENERS
botonStats.addEventListener('click', rediAlumnoStats);
botonJugar.addEventListener('click', rediAlumnoPlay);
botonExit.addEventListener('click', salirSesion);
botonContact.addEventListener('click', rediAlumContacto);
labelMenuStats.addEventListener('click', rediAlumnoStats);
labelMenuPlay.addEventListener('click', rediAlumnoPlay);






});