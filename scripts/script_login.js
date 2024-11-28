// ---- PREPARAMOS CONTENEDOR DE ROL

//preparamos contenedor para el localstorage
localStorage.setItem('rolUsuario', '');

// ---- RECOGIDA IDS
let btnSubmit = document.getElementById("botonComprobar");

// ---- LISTENER
btnSubmit.addEventListener("click", mandamosInfoUser);

// ----- FUNCIONES
async function mandamosInfoUser(event) {
	event.preventDefault();

	// RECOGIDA CONTENIDO FORMULARIO
	let mailUser = document.getElementById("mailUser").value;
	let passUser = document.getElementById("passUser").value;

	// Validación de campos vacíos

	// Expresión regular que solo acepta letras y espacios
	/////COGIDO DE STACKOVERFLOW
	//https://es.stackoverflow.com/questions/453176/como-validar-correctamente-un-email-con-expresiones-regulares
	const patronMail = /[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}/;

	if (mailUser === '' || passUser === '') {
		alert("Por favor, rellena los campos vacíos");
		return; // salir de la función si hay campos vacíos

	} else if (!patronMail.test(mailUser)) {
		alert("Por favor, revisa el correo que has introducido");
		return; // salir de la función si hay campos vacíos						

	} else {

		// Si todo está correcto, continuamos con el envío de datos

	}


	console.log('Datos formulario:');
	console.log(mailUser);
	console.log(passUser);

	// PREPARACIÓN DEL JSON CON DATOS PARA ENVIAR
	let data = {
		mailUsuario: mailUser,
		passUsuario: passUser
	};
	console.log('Datos JSON:', data);

	// OPCIONES PARA PETICIÓN
	let options = {
		method: 'POST',
		body: JSON.stringify(data),
		headers: {
			'Content-Type': 'application/json'
		}
	};

	// PETICIÓN HTTP
	try {
		const response = await fetch('/verificacion', options);

		if (!response.ok) {
			throw new Error('Error en la solicitud. Código de estado: ' + response.status);
		}

		const result = await response.json();
		console.log('Respuesta del servidor:', result);

		if (result.redirectUrl) {

			// Creamos el contenedor y guardamos  el email en localStorage
			localStorage.setItem('emailUsuario', mailUser);
			alert('Registro correcto!!', result);

			//redirigimos
			window.location.href = result.redirectUrl;
		} else {
			alert('Error en el registro:', result.message);
		}
	} catch (error) {
		console.error('Error en el login:', error.message);
		window.location.href = '/rediLogin'; // Redirigir en caso de un error

	}/////FIN FUNCIÓN mandainfousaer
}////FIN LOGIN.JS
