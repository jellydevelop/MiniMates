document.addEventListener('DOMContentLoaded', function() {

	//----------------VERIFICACION DE PARTIDA YA JUGADA

	// Verificar si el alumno ha jugado antes
	/* if (localStorage.getItem('haJugado')) {
 
		 // Si ha jugado, ocultar la opción de jugar
		 const jugarOption = document.getElementById('dataPartidas');
		 
		 if (jugarOption) {
			 jugarOption.hidden=true;
		 }
	 }*/

	//----------------OBTENEMOS EMAIL DEL PROFESOR LOGUEADO
	const emailUsuario = localStorage.getItem("emailUsuario");
	console.log("Email del profesor:", emailUsuario);

	if (emailUsuario) {
		obtenerListaAlumnos(emailUsuario);
	} else {
		console.error("No se encontró el email del profesor en el almacenamiento local.");
	}
	//----------------ID
	const botonExit = document.getElementById('exit');
	const botonAtras = document.getElementById('rediAtras');


	//----------------FUNCIONES


	//************************** */

	function salirSesion() {
		localStorage.removeItem('haJugado');
		localStorage.removeItem('emailUsuario');
		localStorage.removeItem('rolUsuario');
		localStorage.removeItem('idAlumno');

		window.location.href = '/login';
	}
	//************************** */

	function rediMenuProfe(evento) {

		window.location.href = '/profesor';

	}
	//************************** */

	function obtenerListaAlumnos(emailUsuario) {
		fetch(`/obtener_alumnos_clase?emailProfesor=${encodeURIComponent(emailUsuario)}`)
			.then(response => {
				if (response.ok) {
					return response.json();
				}
				throw new Error("Error al obtener la lista de alumnos");
			})
			.then(data => mostrarListaAlumnos(data))
			.catch(error => console.error(error.message));
	}

	//************************** */


	function mostrarListaAlumnos(listaAlumnos) {

		let divListaClase = document.getElementById("divListaClase");

		if (divListaClase) {

			// Borra el contenido anterior del div
			divListaClase.innerHTML = "";

			// Ordena los alumnos por apellido (primero por el primer apellido y luego por el segundo)
			listaAlumnos.sort((a, b) => {
				// Comparar el primer apellido
				const apellidoA = a.primApellidoUsuario.toLowerCase();
				const apellidoB = b.primApellidoUsuario.toLowerCase();

				// Si son iguales, comparar el segundo apellido
				if (apellidoA === apellidoB) {
					return a.secApellidoUsuario.toLowerCase().localeCompare(b.secApellidoUsuario.toLowerCase());
				}

				return apellidoA.localeCompare(apellidoB);
			});


			// Crea una lista ordenada y agrega un elemento li por cada alumno
			let ol = document.createElement("ol");

			// por cada alumno..
			listaAlumnos.forEach(alumno => {

				// Crea un elemento de lista li
				let li = document.createElement("li");

				// Crea un enlace dentro de li a la página de stats 
				let enlace = document.createElement("a");

				//redireccionamos segun el alumno que se cliquee
				enlace.href = "#"; // Cambiamos a '#' para que no navegue inmediatamente
				enlace.textContent = `${alumno.primApellidoUsuario} ${alumno.secApellidoUsuario},${alumno.nombreUsuario}`;
				enlace.target = "_blank"; //abriremos nueva pestaña
				///---->>listener
				enlace.addEventListener('click', function() {
					 event.preventDefault();//para que no se duplique la ventana
					localStorage.setItem('idAlumno', alumno.idUsuario); // Guardar ID en localStorage
					window.open(`/tus_estadisticas?id=${alumno.idUsuario}`, '_blank'); // Abrir en nueva pestaña
				})

				// Agrega el enlace al elemento de li
				li.appendChild(enlace);

				// Agrega el elemento de lista a la lista ol
				ol.appendChild(li);
			});

			// Agrega la lista ol al contenedor vacio
			divListaClase.appendChild(ol);
		}
	}
	//----------------LISTENERS
	botonExit.addEventListener('click', salirSesion);
	botonAtras.addEventListener('click', rediMenuProfe);


	//----------------PETICION


	// Realiza una solicitud al backend para obtener la lista de alumnos
	fetch(`/obtener_alumnos_clase?emailProfesor=${encodeURIComponent(emailUsuario)}`)

		.then(response => {

			if (!response.ok) {
				throw new Error('No se pudo obtener la lista de alumnos');
			} else {
				return response.json();

			}
		})

		.then(data => {
			// Procesa los datos y muestra la lista de alumnos 
			mostrarListaAlumnos(data);
		})


		.catch(error => {
			console.error('Error al obtener la lista de alumnos:', error);
		});
});




