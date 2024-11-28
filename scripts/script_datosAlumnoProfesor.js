document.addEventListener('DOMContentLoaded', async function() {

	//----- RECOGIDA IDS
	const miInput = document.querySelector('input');

	////--- FORMULARIOS
	let radioForm = document.getElementById("formEleccion");
	let altaForm = document.getElementById("formAlta");
	let bajaForm = document.getElementById("formBaja");
	let updateForm = document.getElementById("formModificacion");

	////--- BOTONES
	let radioAlta = document.getElementById("radioAlta");
	let radioBaja = document.getElementById("radioBaja");
	let radioMod = document.getElementById("radioMod");

	const botonAtras = document.getElementById('rediAtras');
	const botonExit = document.getElementById('exit');
	const botonRadioEleccion = document.getElementById('botonEnviarModificacion');



	let altaBtn = document.getElementById("botonEnviarAlta");
	let bajaBtn = document.getElementById("botonEnviarBaja");
	let updateBtn = document.getElementById("radioMod");

	////--- LOGS

	let nameLog = document.getElementById("nameAlum");
	let mailLog = document.getElementById("mailAlum");
	let idLOG = document.getElementById("idAlum");




	//----- FCNES VALIDACIONES

	//Estas 2 funciones gestionan el contenido de las casillas
	function validacionTrue() {
		// Quita el mensaje según escribes
		miInput.setCustomValidity('');
		// Comprueba si debe validarlo
		miInput.checkValidity();
	}

	//-********************

	function validacionFalse() {
		miInput.setCustomValidity('Si no es molestia,¿puedes elegir una acción?');
	}

	//----- GESTIONAR RADIO BUTTONS

	function gestionarRadioBtns(event) {
		// Muestra/oculta formularios según la selección del radio button

		event.preventDefault();  // Evitamos que el formulario se envíe y recargue la página



		// Muestra el formulario de alta si el radioAlta está seleccionado
		altaForm.hidden = !radioAlta.checked;
		// Muestra el formulario de baja si el radioBaja está seleccionado
		bajaForm.hidden = !radioBaja.checked;
		// Muestra el formulario de modificación si el radioMod está seleccionado
		updateForm.hidden = !radioMod.checked;

		// Asegurarse de que siempre un formulario está visible
		if (!radioAlta.checked && !radioBaja.checked && !radioMod.checked) {
			alert("Por favor, selecciona una acción.");
		}
	}


	//----- FCNES  PASSWORD

	//**************************************************** */
	// Limpia espacios en blanco
	function limpiaEspacios() {
		miInput.value = miInput.value.trim();
	}
	//**************************************************** */

	function obtenerTresPrimerasLetras(nombre) {
		return nombre.substring(0, 3);
	}

	function generarPassword(nombre) {

		// Obtenemos las tres primeras letras del nombre usando la función
		let letras = obtenerTresPrimerasLetras(nombre);

		// Generamos tres números aleatorios
		let numeros = Math.floor(Math.random() * 900) + 100;

		// Concatenamos las letras y los números
		let password = letras + numeros;
		return password;

	}

	//**************************************************** */
	//----- GESTIONAR  BUTTONS CABECERA
	function rediMenuProfesor() {
		window.location.href = '/profesor';

	}

	//************************** */

	function salirSesion() {
		localStorage.removeItem('haJugado');
		localStorage.removeItem('emailUsuario');
		localStorage.removeItem('rolUsuario');
		localStorage.removeItem('idAlumno');

		window.location.href = '/login';
	}


	////********************************************* DATOS ALUMNO . HTML ******************************************** */
	////*********************** ALTA ******************************************** */


	//******************************/
	// Obtenemos el formulario
	const formAlta = document.getElementById('formAlta');

	// Agregamos un evento de escucha para el envío del formulario
	formAlta.addEventListener('submit', async function(event) {
		event.preventDefault(); // Evitamos que se envíe el formulario de forma predeterminada

		// Capturamos los valores de los campos de entrada
		const nombreAlumno = document.getElementById('nameAlum').value;
		const mailAlumno = document.getElementById('mailAlum').value;
		const niaAlumno = document.getElementById('niaAlum').value;
		const primApellidoUsuario = document.getElementById('primApellidoUsuario').value;
		const secApellidoUsuario = document.getElementById('secApellidoUsuario').value;


		//preparamos el pass
		const passAlum = generarPassword(nombreAlumno);

		//preparamos la letra de la clase
		const letraClaseProfesor = localStorage.getItem('letraClase');

		let letraClase = '';

		if (letraClaseProfesor) {
			letraClase = letraClaseProfesor;
		} else {
			alert('No se ha asignado una clase al alumno.');
			return;
		}


		// Construimos el objeto con los datos del alumno a enviar al servidor
		const datosAlumno = {

			//el código de centro es fijo xq esta bsdd es para el nismo centro
			codigoCentro: "28010101",

			//datos del form
			primApellidoUsuario: primApellidoUsuario,
			secApellidoUsuario: secApellidoUsuario,
			nombreUsuario: nombreAlumno,
			mailUsuario: mailAlumno,
			niaAlumno: niaAlumno,
			//rol es fijo
			rolUsuario: 'alumno',
			//pass generado por funcion
			passUsuario: passAlum,

			clase: {
				letraClase: letraClase,
				centro: {
					idCentro: "28010101"
				}
			}
		}


		console.log(datosAlumno);

		try {
			// Realizamos la solicitud fetch al servidor para añadir el nuevo alumno
			const response = await fetch('/aniadiralumno', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(datosAlumno)

			})
			
			// Verificamos si la respuesta es un conflicto (409)
        if (response.status === 409) {
            const mensajeError = await response.text();
            alert(mensajeError); 
            return;
        }

			// Validamos respuesta
			if (!response.ok) throw new Error('Error en la respuesta del servidor');


			// Obtenemos los datos del alumno que han sido añadidos
			const datosAlumnoRespuesta = await response.json();
			console.log(datosAlumnoRespuesta);


			alert(`El alumno ${datosAlumnoRespuesta.niaAlumno} ha sido añadido correctamente`);


		} catch (error) {
			console.error('Error:', error);
			alert('Error al procesar la solicitud');
		}
});

////*********************** BAJA POR NIA ******************************************** */

		document.getElementById('formBaja').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevenir el comportamiento por defecto (enviar formulario)
    console.log("Formulario de baja enviado");

    // Captura el valor del NIA
    let niaAlumBaja = document.getElementById("niaAlumBaja").value;
    console.log(`NIA a eliminar: ${niaAlumBaja}`);

    // Validación del NIA
    if (!niaAlumBaja || niaAlumBaja.length !== 8) {
        alert("Por favor, ingresa un NIA válido.");
        return;
    }
    let url = `/borrar_usuario_nia/${encodeURIComponent(niaAlumBaja)}`;
    console.log("URL de la solicitud: ", url);

    // Realiza la solicitud AJAX al servidor para eliminar al alumno por su NIA
    fetch(url, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al dar de baja al alumno');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        if (data) {
            alert(`Alumno eliminado correctamente: \nNombre: ${data.nombreUsuario}\nCorreo: ${data.mailUsuario}`);
        } else {
            alert("No se encontró un alumno con ese NIA.");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Hubo un error al dar de baja al alumno');
    });
});
	
	////*********************** UPDATE ******************************************** */
formModificacion.addEventListener("submit", function(event) {
    // Evita que se envíe el formulario de forma predeterminada
    event.preventDefault();

    // ---------------------------------IDS
    const mailUsuario = document.getElementById('mailTutor').value;
    const opcionModificacion = document.getElementById('opcionesMod').value;
    const actual = document.querySelector('#actual input').value;
    const nuevo = document.querySelector('#nuevo input').value;

    // Objeto JSON con la información que se quiere modificar
    let datosModificacion = {};

    // Campo de los datos del usuario vamos a modificar
    if (opcionModificacion === 'nombre') {
        // Si estamos modificando el nombre
        datosModificacion.nombreUsuario = nuevo;
    } else if (opcionModificacion === 'email') {
        // Si estamos modificando el email
        datosModificacion.mailUsuario = nuevo;
    }

    console.log(datosModificacion);

    //----- PETICION
    fetch(`/modificamos_datos_usuario/${mailUsuario}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(datosModificacion) // Convertimos los datos a formato JSON
    })
    .then(response => {
            if (response.ok) {
                return response.json(); // Procesa la respuesta como JSON
            } else if (response.status === 404) {
                throw new Error('Usuario no encontrado');
            } else {
                throw new Error('Error en la modificación de los datos');
            }
        })
    .then(data => {
        console.log('Datos modificados con éxito', data);
        alert('Datos de modificados con éxito');
        
         let mensaje = "";
        if (opcionModificacion === 'nombre') {
            mensaje = `El dato inicial del nombre: ${actual} ha sido modificado a: ${data.nombreUsuario}`;
        } else if (opcionModificacion === 'email') {
            mensaje = `El dato inicial del email: ${actual} ha sido modificado a: ${data.mailUsuario}`;
        }
        
        alert(mensaje);
    })
    .catch(error => {
        console.error('Error:', error);
        if (error.response) {
 alert(error.message || 'Hubo un error al modificar los datos');        }
    });
});
	//----- LISTENERS GENERALS

	///Muestra los formularios segun la eleccion del profesor al pulsar el boton 
	radioForm.addEventListener('submit', gestionarRadioBtns);

	// Quita la validación mientras escribes
	miInput.addEventListener('input', validacionTrue);

	// Muestra el mensaje de validación
	miInput.addEventListener('invalid', validacionFalse);

	// Quita los espacios al principio y al final
	miInput.addEventListener('blur', limpiaEspacios);

	//Redirige a la página anterior--> menu
	botonAtras.addEventListener('click', rediMenuProfesor);

	//cierra la sesión--> login
	botonExit.addEventListener('click', salirSesion);




});
////FIN FUNCION PETICION
