document.addEventListener('DOMContentLoaded', function() {

	//----------------DEFINIMOS EL ROL DEL USUARIO PARA BLOQUEAR PERFILES
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

	function salirSesion() {
		localStorage.removeItem('haJugado');
		localStorage.removeItem('emailUsuario');
		localStorage.removeItem('rolUsuario');
		localStorage.removeItem('idAlumno');

		window.location.href = '/login';
	}

	//----- GESTIONAR LETRA PROFESOR

	// Declaramos la variable global para almacenar la letra de la clase del profesor
	let letraClaseProfesor = '';

	// Función para recoger la letra de la clase del profesor al cargar la página
	async function recogemosLetraProfe() {
		const emailProfesor = localStorage.getItem('emailUsuario'); // Obtenemos el email del profesor desde localStorage
		console.log(emailProfesor);

		// Validamos si el email está presente
		if (!emailProfesor) {
			console.log("Email no encontrado.");
			alert("No se ha encontrado el email del profesor.");
			return;
		}

		try {
			// Petición al servidor para obtener la letra de la clase del profesor
			const response = await fetch('/pedirLetraProfesor', {
				method: 'POST',
				headers: {
					'Content-Type': 'text/plain' // Mandamos solo un string
				},
				body: emailProfesor.trim() // Limpiamos el string
			});

			// Validamos la respuesta
			if (!response.ok) {
				throw new Error(`Error en la respuesta del servidor: ${response.statusText}`);
			}

			const data = await response.text(); // Leemos la respuesta del servidor
			console.log("Respuesta del servidor: ", data);

			// Validamos si la respuesta contiene datos válidos
			if (!data) {
				throw new Error("No se ha recibido la letra de clase del profesor.");
			}

			// Guardamos la letra de la clase del profesor
			letraClaseProfesor = data;

			// Guardamos la letra de clase en el localStorage para su uso posterior
			localStorage.setItem('idC', letraClaseProfesor);

			// Redireccionamos
			window.location.href = '/datosAlumnnoProfesor';

		} catch (error) {
			console.error('Error en recogemosLetraProfe:', error);
			alert('Error al procesar la solicitud: ' + error.message);
		}
	}

	//----------------LISTENERS
	botonStats.addEventListener('click', rediProfesorListaStats);
	botonDatos.addEventListener('click', recogemosLetraProfe); // Aquí llamamos a la función que hace el fetch
	botonExit.addEventListener('click', salirSesion);
	labelMenuStats.addEventListener('click', rediProfesorListaStats);
	labelMenuData.addEventListener('click', recogemosLetraProfe); // También al hacer click en esta opción, se hace la petición

});

