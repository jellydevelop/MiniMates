document.addEventListener('DOMContentLoaded', function() {

	//----------------DEFINIMOS EL ROL DLE USUARIO PARA BLOQUEAR PERFILES
	localStorage.setItem('rolUsuario', 'esProfesor');


	//----------------ID
	const botonStats = document.getElementById('stats');
	const botonDatos = document.getElementById('dataAlum');
	const botonExit = document.getElementById('exit');
	const labelMenuStats = document.getElementById("lStats");
	const labelMenuData = document.getElementById("lData");


	//----------------FUNCIONES


	function rediProfesorListaStats() {
		window.location.href = '/listaAlumnos';
	}
	//************************** */

	function rediProfesorDatos() {
		window.location.href = '/datosAlumnnoProfesor';
	}
	//************************** */


function salirSesion() {
		localStorage.removeItem('haJugado');
		localStorage.removeItem('emailUsuario');
		localStorage.removeItem('rolUsuario');
		localStorage.removeItem('idAlumno');

		window.location.href = '/login';
	}


	//----------------LISTENERS
	botonStats.addEventListener('click', rediProfesorListaStats);
	botonDatos.addEventListener('click', rediProfesorDatos);
	botonExit.addEventListener('click', salirSesion);
	labelMenuStats.addEventListener('click', rediProfesorListaStats);
	labelMenuData.addEventListener('click', rediProfesorDatos);

});
