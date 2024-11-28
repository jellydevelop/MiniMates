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

	function salirSesion() {
		console.log("Botón 'Salir' presionado");
		localStorage.removeItem('haJugado');
		localStorage.removeItem('emailUsuario');
		localStorage.removeItem('rolUsuario');
		localStorage.removeItem('idAlumno');
		window.location.href = '/login';
	}

	//----------------VALIDACION DATOS ENTRADA
	inputTutor.addEventListener('input', function() {
		// REGEX que solo acepta letras y espacios
		const patronName = /^[a-zA-Z\s]+$/;

		// valor del input eliminando espacios en blanco
		let tutor = inputTutor.value.trim();
		if (!patronName.test(tutor)) {
			alert('El campo de tutor solo debe contener letras y espacios.');
		}
	});

	//----------------PETICION MENSAJE
	async function mandarMensaje(event) {
		event.preventDefault();

	 // Mostrar el mensaje de carga
    const loadingMessage = document.getElementById('loadingMessage');
    loadingMessage.style.display = 'block'; // Mostrar el mensaje de "Enviando..."

		// RECOGIDA CONTENIDO FORMULARIO
		let tutor = document.getElementById("name").value.trim();
		let mensaje = document.getElementById("consulta").value.trim();

		// Obtener el email del usuario + letra clase desde el localStorage
		let emailUsuario = localStorage.getItem('emailUsuario');
		let letraClase = localStorage.getItem('letraClase');
		if (!emailUsuario || !letraClase) {
			alert("No se encontró el email o letra clase del usuario en el localStorage.");
			return;
		}

		// Expresión regular que solo acepta letras y espacios
		const patronName = /^[a-zA-Z\s]+$/;

		// Validación de campos vacíos
		if (tutor === '' || mensaje === '') {
			alert("Por favor, rellena los campos vacíos");
			return;
		} else if (!patronName.test(tutor)) {
			alert("Por favor, usa sólo letras");
			return;
		}

		// Si todo está correcto, se crea el JSON de datos
		let data = {
			emailAlumno: emailUsuario,
			letraClase: letraClase,
			nombreTutor: tutor,
			cuerpoMensaje: mensaje
		};

		console.log('Datos JSON:', data);

		// PETICIÓN POST
		try {
			const response = await fetch('/enviarEmail', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(data)
			});

			const result = await response.text();
			
			  // Ocultar el mensaje de carga
        loadingMessage.style.display = 'none';
        
			if (response.ok) {
				alert("El email se ha enviado correctamente.¡Gracias!");
				window.location.href = '/alumno';
			} else {
				alert("Error al enviar el correo: " + result);
			}
		} catch (error) {
			  // Ocultar el mensaje de carga en caso de error
        loadingMessage.style.display = 'none'; 
			alert("Error al enviar el correo: " + error);
		}
	}

	//----------------LISTENERS
	botonExit.addEventListener('click', salirSesion);
	botonEnviar.addEventListener('click', mandarMensaje);
	if (botonAtras) {
		botonAtras.addEventListener('click', rediMenuAlumno);
	} else {
		console.error("El botón 'rediAtras' no se encontró en el DOM.");
	}
});
