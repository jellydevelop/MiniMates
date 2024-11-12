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
	//----------------ID
	const botonEnviar = document.getElementById('send');
	const botonExit = document.getElementById('exit');
	const botonAtras = document.getElementById('rediAtras');
	const inputTutor = document.getElementById('name');


	//----------------FUNCIONES

	function rediMenuAlumno(evento) {
		evento.preventDefault();
		        console.log("Botón 'Atrás' presionado");

		window.location.href = '/alumno';

	}

	//************************** */

	function salirSesion() {
		        console.log("Botón 'Salir' presionado");

		localStorage.removeItem('haJugado');
		localStorage.removeItem('emailUsuario');
		localStorage.removeItem('rolUsuario');
		localStorage.removeItem('idAlumno');

		window.location.href = '/login';
	}
	//************************** */


	// Validación del patrón en tiempo real
	inputTutor.addEventListener('input', function() {

		// REGEX que solo acepta letras y espacios
		const patronName = /^[a-zA-Z\s]+$/;

		// valor del input eliminando espacios en blanco
		let tutor = inputTutor.value.trim();

		if (!patronName.test(tutor)) {
			alert('El campo de tutor solo debe contener letras y espacios.');
		}
	});

	async function mandarMensaje(event) {
		event.preventDefault();

		// RECOGIDA CONTENIDO FORMULARIO
		let tutor = document.getElementById("name").value.trim();
		let mensage = document.getElementById("consulta").value.trim();


		// Expresión regular que solo acepta letras y espacios
		const patronName = /^[a-zA-Z\s]+$/;


		// Validación de campos vacíos
		if (tutor === '' || mensage === '') {
			alert("Por favor, rellena los campos vacíos");
			return; // salir de la función si hay campos vacíos
		} else if (!patronName.test(tutor)) {
			alert("Por favor, usa sólo letras");
			return; // salir de la función si hay campos vacíos						

		} else {

			// Si todo está correcto, continuamos con el envío de datos

			console.log(tutor);
			console.log(mensage);

		}
		        alert("Función mandarMensaje activada.");

	}



	// PREPARACIÓN DEL JSON CON DATOS PARA ENVIAR
	let data = {
		nombreTutor: tutor,
		cuerpoMensaje: mensage
	};
	console.log('Datos JSON:', data);



	// PETICIÓN 

	fetch('/enviarMail', {

		method: 'POST',
		headers: {

			'Content-Type': 'application/x-www-form-urlencoded',
			'Authorization': 'Bearer ' + token

		},

		body: new URLSearchParams({

			'nombreTutor': nombreTutor,
			'cuerpoMensaje': cuerpoMensaje

		})
	})


		.then(response => response.text())

		.then(data => {

			// Muestra la respuesta del servidor
			alert(data);
		})

		.catch(error => {

			alert("Error al enviar el correo: " + error);

		});



	//----------------LISTENERS
	botonExit.addEventListener('click', salirSesion);
	botonEnviar.addEventListener('click', mandarMensaje);
	    if (botonAtras) {
        botonAtras.addEventListener('click', rediMenuAlumno);
    } else {
        console.error("El botón 'rediAtras' no se encontró en el DOM.");
    }

});