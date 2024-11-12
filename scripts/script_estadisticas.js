document.addEventListener('DOMContentLoaded', function() {


	//----------------VERIFICACION DE PARTIDA YA JUGADA

	// Verificar si el alumno ha jugado antes
	if (localStorage.getItem('haJugado')) {

		// Si ha jugado, ocultar la opción de jugar
		const jugarOption = document.getElementById('dataPartidas');

		if (jugarOption) {
			jugarOption.hidden = true;
		}
	}

	//----------------VERIFICACION DE ROL

	const rolUsuario = localStorage.getItem('rolUsuario');

	//----------------ID
	const botonExit = document.getElementById('exit');
	const botonAtras = document.getElementById('rediAtras');
	const botonCierreGraf = document.getElementById('cierreGraf');
	const botonCierrTabla = document.getElementById('cierreTabla');
	const selectMundos = document.getElementById('selectMundos');
	const selectRetos = document.getElementById('selectRetos');





	//----------------FUNCIONES


	//************************** */

	function rediMenuAlumno(evento) {

		if (rolUsuario == 'esProfesor') {
			window.location.href = '/listaAlumnos';

		} else {
			window.location.href = '/alumno';

		}

	}

	//************************** */

	function salirSesion() {
		localStorage.removeItem('haJugado');
		localStorage.removeItem('emailUsuario');
		localStorage.removeItem('rolUsuario');
		localStorage.removeItem('idAlumno');

		window.location.href = '/login';
	}
	//************************** */

	function escondeTabla() {
		const tabla = document.getElementById('tablaInfo');
		tabla.hidden = true;
		botonCierrTabla.hidden = true;

	}

	//************************** */

	function escondeGrafica() {
		const grafica = document.getElementById('graficasInfo');
		grafica.hidden = true;
		botonCierreGraf.hidden = true;

	}

	//************************** */

	function despliegaTabla() {
		if (selectMundos.value !== "") {
			const tabla = document.getElementById('tablaInfo');
			tabla.hidden = false;
			botonCierrTabla.hidden = false;

		}


	}

	//************************** */

	function despliegaGrafica() {
		if (selectRetos.value !== "") {
			const grafica = document.getElementById('graficasInfo');
			grafica.hidden = false;
			botonCierreGraf.hidden = false;

		}


	}
	//----------------LISTENERS

	botonExit.addEventListener('click', salirSesion);
	botonAtras.addEventListener('click', rediMenuAlumno);
	botonCierreGraf.addEventListener('click', escondeGrafica);
	botonCierrTabla.addEventListener('click', escondeTabla);
	selectMundos.addEventListener('change', despliegaTabla);
	selectRetos.addEventListener('change', despliegaGrafica);


	//----------------LISTPETICIÓN DE DATOS




});

