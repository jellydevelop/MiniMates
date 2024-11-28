document.addEventListener('DOMContentLoaded', function() {
		//----------------DEFINIMOS EL ROL DLE USUARIO PARA BLOQUEAR PERFILES
	localStorage.setItem('rolUsuario', 'esAlumno');
		//----------------DEFINIMOS EL MUNDO QUE EL USUARIO JUGARA

const idMundoActual = localStorage.setItem('mundoActual','');

// OBTENEMOS MAIL ALUMNO
    const emailUsuario = localStorage.getItem('emailUsuario');
    if (!emailUsuario) {
        console.error("No se encontró el email del usuario en el localStorage.");
        return;
    }
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
                localStorage.removeItem('letraClase'); 

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

//----------------PETICION

 async function obtenerLetraClase() {
        try {
            // Realizar la petición GET al servidor para obtener la letra de la clase
            const response = await fetch(`/alumno/obtenerLetraClase/${emailUsuario}`);

            if (response.ok) {
                const letraClase = await response.text(); // Obtener la letra de la clase
                console.log("Letra de la clase:", letraClase);

                // Almacenar la letra de la clase en el localStorage
                localStorage.setItem('letraClase', letraClase);
            } else {
                // Manejar errores de servidor
                const errorMessage = await response.text();
                console.error("Error al obtener la letra de la clase:", errorMessage);
            }
        } catch (error) {
            console.error("Error al hacer la petición:", error);
        }
    }
obtenerLetraClase();

//----------------LISTENERS
botonStats.addEventListener('click', rediAlumnoStats);
botonJugar.addEventListener('click', rediAlumnoPlay);
botonExit.addEventListener('click', salirSesion);
botonContact.addEventListener('click', rediAlumContacto);
labelMenuStats.addEventListener('click', rediAlumnoStats);
labelMenuPlay.addEventListener('click', rediAlumnoPlay);





});