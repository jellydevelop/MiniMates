document.addEventListener('DOMContentLoaded', function() {

//----------------VERIFICACION DE PARTIDA YA JUGADA

    // Verificar si el alumno ha jugado antes
    if (localStorage.getItem('haJugado')) {

        // Si ha jugado, ocultar la opción de jugar
        const jugarOption = document.getElementById('dataPartidas');
        
        if (jugarOption) {
            jugarOption.hidden=true;
        }
    }
//----------------ID
const botonEnviar =document.getElementById('send');
const botonExit = document.getElementById('exit');
const botonAtras = document.getElementById('rediAtras');
const inputTutor = document.getElementById('name');


//----------------FUNCIONES

function rediMenuAlumno(evento){
    window.location.href = '/alumno'; 

}

//************************** */

function salirSesion() {
    localStorage.removeItem('haJugado'); 
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
        let tutor = document.getElementById("name").value.trim;
        let mensage = document.getElementById("consulta").value.trim;
        
        // Expresión regular que solo acepta letras y espacios
        	const patronName = /^[a-zA-Z\s]+$/;

    
         // Validación de campos vacíos
		        if (tutor === '' || mensaje === '') {
		            alert("Por favor, rellena los campos vacíos");
		            return; // salir de la función si hay campos vacíos
		        }
		       
           
        // Si todo está correcto, continuar con el envío de datos
 
        console.log(tutor);
        console.log(mensage);
    
        // PREPARACIÓN DEL JSON CON DATOS PARA ENVIAR
        let data = {
            tutor: tutor,
            mensaje: mensage
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
            const response = await fetch('/enviarConsulta', options);
    
            if (!response.ok) {
                throw new Error('Error en la solicitud. Código de estado: ' + response.status);
            }
    
            const result = await response.json();
            console.log('Respuesta del servidor:', result);
            
        
            // Suponiendo que la respuesta indica un inicio de sesión exitoso
            if(result.status===200){
                alert("Mensaje enviado con éxito.");

                    // Limpiamos el textarea
                document.getElementById("consulta").value = ''; 


            }else{
                alert("Hubo un problema y no se pudo enviar el mensaje. Vuelva a intentarlo por favor.");
            }
    
        } catch (error) {
            console.error('Error:', error.message);
            window.location.href = '/contacto'; // Redirigir en caso de un error
        }
}

//----------------LISTENERS
botonExit.addEventListener('click', salirSesion);
botonAtras.addEventListener('click', rediMenuAlumno);
botonEnviar.addEventListener('click', mandarMensaje);

});