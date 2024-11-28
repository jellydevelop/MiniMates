document.addEventListener('DOMContentLoaded', function() {


	//----------------OBTENEMOS LETRA DE LA CLASE  DEL PROFESOR LOGUEADO
	const letraClase = localStorage.getItem("letraClase");
	console.log("Letra de la clase:", letraClase);

	if (!letraClase) {
		alert("No se encontró la letra de la clase. Por favor, seleccione una clase válida.");
		return;
	}
	//----------------ID
	const botonExit = document.getElementById('exit');
	const botonAtras = document.getElementById('rediAtras');


	//----------------FUNCIONES

	//************************** */

	function salirSesion() {
		alert('Cerrando sesión...');
		localStorage.removeItem('haJugado');
		localStorage.removeItem('emailUsuario');
		localStorage.removeItem('rolUsuario');
		localStorage.removeItem('idAlumno');
		localStorage.removeItem('letraClase');

		window.location.href = '/login';
	}
	//************************** */

	function rediMenuProfe(evento) {

		window.location.href = '/profesor';

	}
	//************************** */
	//-----------------PETICION DE OBTENER NOMBRES ALUMNOS
	function obtenerListaAlumnos(letraClase) {


		if (letraClase == null) {
			alert('No hay letra guardada');
		}


		fetch(`/obtener_alumnos_clase?letraClase=${encodeURIComponent(letraClase)}`)


			.then(response => {
				if (!response.ok) {
					throw new Error("Error al obtener la lista de alumnos");
				}
				return response.json();
			})
			.then(data => mostrarListaAlumnos(data))
			.catch(error => console.error(error.message));

	}


	//************************** */
	//-----------------FUNCIÓN DE MOSTRAR NOMBRES ALUMNOS


	function mostrarListaAlumnos(listaAlumnos) {

		const divListaClase = document.getElementById("divListaClase");

		if (!divListaClase) {
			console.error("El contenedor para la lista de alumnos no existe.");
			return;
		}

		// Borra el contenido anterior del div
		divListaClase.innerHTML = "";


		if (!listaAlumnos || listaAlumnos.length === 0) {
			divListaClase.innerHTML = "<p>No hay alumnos disponibles para esta clase.</p>";
			return;
		}

		// Ordena los alumnos por apellido (primero por el primer apellido y luego por el segundo)
		listaAlumnos.sort((a, b) => {
			// Comparar el primer apellido
			const apellidoA = (a.primApellidoUsuario || "").toLowerCase();
			const apellidoB = (b.primApellidoUsuario || "").toLowerCase();

			// Si son iguales, comparar el segundo apellido
			if (apellidoA === apellidoB) {
				return (a.secApellidoUsuario || "").toLowerCase()
					.localeCompare((b.secApellidoUsuario || "").toLowerCase());
			}

			return apellidoA.localeCompare(apellidoB);
		});


		// Crea una lista ordenada y agrega un elemento li por cada alumno
		const ol = document.createElement("ol");

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

			///---->>listener redireccion TUS_ESTADISTICAS
			enlace.addEventListener('click', function() {
								event.preventDefault();//para que no se duplique la ventana

				//preparamos iniciales 
    const iniciales = `${alumno.nombreUsuario[0].toUpperCase()}${alumno.primApellidoUsuario[0].toUpperCase()}${alumno.secApellidoUsuario[0].toUpperCase()}`;
    
    
     // Guardamos el objeto completo del alumno en localStorage
    const alumnoInfo = {
        idUsuario: alumno.idUsuario,
        nombre: alumno.nombreUsuario,
        primApellido: alumno.primApellidoUsuario,
        secApellido: alumno.secApellidoUsuario,
        iniciales: iniciales
    };
    
     // Guardar el objeto de alumno en localStorage
    localStorage.setItem('alumnoSeleccionado', JSON.stringify(alumnoInfo));
				window.open(`/tus_estadisticas`, '_blank'); // Abrir en nueva pestaña
			})

			// Agrega el enlace al elemento de li
			li.appendChild(enlace);

			// Agrega el elemento de lista a la lista ol
			ol.appendChild(li);
		});

		// Agrega la lista ol al contenedor vacio
		divListaClase.appendChild(ol);

	}

	//----------------LISTENERS
	botonExit.addEventListener('click', salirSesion);
	botonAtras.addEventListener('click', rediMenuProfe);


	// Cargar la lista de alumnos al cargar la página
	obtenerListaAlumnos(letraClase);

});




