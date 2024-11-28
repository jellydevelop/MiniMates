document.addEventListener('DOMContentLoaded', function() {

	//----------------DEFINIMOS EL ROL DEL USUARIO PARA BLOQUEAR PERFILES
	localStorage.setItem('rolUsuario', 'esProfesor');

	//----------------PREPARAMOS CONTENEDOR PARA LETRA CLASE DE L PROFESOR	
	localStorage.setItem('letraClase', '');
	
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
	
	function rediProfesorDataaAlum() {
            window.location.href = '/datosAlumnnoProfesor';
	}

	function salirSesion() {
		localStorage.removeItem('haJugado');
		localStorage.removeItem('emailUsuario');
		localStorage.removeItem('rolUsuario');
		localStorage.removeItem('idAlumno');
		localStorage.removeItem('letraClase');

		window.location.href = '/login';
	}

	//----- GESTIONAR LETRA PROFESOR

// Función para recoger la letra de la clase del profesor al cargar la página
function recogemosLetraProfe() {
	    console.log("recogemosLetraProfe se está ejecutando");

    const emailProfesor = localStorage.getItem('emailUsuario'); // Obtenemos el email del profesor desde localStorage
    console.log(emailProfesor);

    // Validamos si el email está presente
    if (!emailProfesor) {
        console.log("Email no encontrado.");
        alert("No se ha encontrado el email del profesor.");
        return;
    }

    // Petición al servidor para obtener la letra de la clase del profesor
    fetch('/pedirLetraProfesor', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain' // Mandamos solo un string
        },
        body: emailProfesor.trim() // Limpiamos el string
    })
    .then(response => {
        // Validamos la respuesta
        if (!response.ok) {
            throw new Error(`Error en la respuesta del servidor: ${response.statusText}`);
        }

        return response.json();  // Extraemos el cuerpo de la respuesta
    })
    .then(data => {
        console.log("Respuesta del servidor: ", data);

        // Validamos si la respuesta contiene datos válidos
        if (!data||!data.letraClase) {
            throw new Error("No se ha recibido la letra de clase del profesor.");
        }

        // Guardamos la letra de la clase del profesor
       let letraClaseProfesor = data.letraClase;

        // Guardamos la letra de clase en el localStorage para su uso posterior
        localStorage.setItem('letraClase', letraClaseProfesor);

        // Verificar si se guardó correctamente
        if (localStorage.getItem('letraClase') !== letraClaseProfesor) {
            throw new Error("La letra de clase no se ha guardado correctamente en localStorage.");
        }

    })
    .catch(error => {
        console.error('Error en recogemosLetraProfe:', error);
        alert('Error al procesar la solicitud: ' + error.message);
    });
}
	//---------------INVOCACIONES

recogemosLetraProfe();
	//----------------LISTENERS
	botonStats.addEventListener('click', rediProfesorListaStats);
	botonExit.addEventListener('click', salirSesion);
	labelMenuStats.addEventListener('click', rediProfesorListaStats);
	labelMenuData.addEventListener('click',rediProfesorDataaAlum);
	botonDatos.addEventListener('click',rediProfesorDataaAlum);

});

