document.addEventListener('DOMContentLoaded', function() {

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
			const botonRadioEleccion=document.getElementById('botonEnviarEleccion');



        let altaBtn = document.getElementById("botonEnviarAlta");
        let bajaBtn = document.getElementById("botonEnviarBaja");
        let updateBtn = document.getElementById("radioMod");

        ////--- LOGS

        let nameLog = document.getElementById("nameAlum");
        let mailLog = document.getElementById("mailAlum");
        let idLOG=document.getElementById("idAlum");




//----- FCNES VALIDACIONES

//Estas 2 funciones gestionan el contenido de las casillas
    function validacionTrue() {
                // Quita el mensaje según escribes
                miInput.setCustomValidity('');
                // Comprueba si debe validarlo
                miInput.checkValidity();
    }
    
    //-********************
        
    function validacionFalse (){
        miInput.setCustomValidity('Si no es molestia,¿puedes elegir una acción?');
    }

//----- FCNES LISTENER

function gestionarRadioBtns(event){
        // Muestra/oculta formularios según la selección del radio button

        event.preventDefault();  // Evitamos que el formulario se envíe y recargue la página



	// Muestra el formulario de alta si el radioAlta está seleccionado
    altaForm.hidden = !radioAlta.checked;
    // Muestra el formulario de baja si el radioBaja está seleccionado
    bajaForm.hidden = !radioBaja.checked;
    // Muestra el formulario de modificación si el radioMod está seleccionado
    updateForm.hidden = !radioMod.checked;
}

//**************************************************** */

function limpiaEspacios()  {
    miInput.value = miInput.value.trim();
}
//**************************************************** */

function obtenerTresPrimerasLetras(nombre) {
    return nombre.substring(0, 3);
}

//**************************************************** */
    
   function rediMenuProfesor(){
    window.location.href = '/profesor'; 

}

//************************** */

function salirSesion() {
    localStorage.removeItem('haJugado'); 
    window.location.href = '/login'; 
}

//************************** */

function generarPassword(nombre) {
	
    // Obtenemos las tres primeras letras del nombre usando la función
    let letras = obtenerTresPrimerasLetras(nombre);

    // Generamos tres números aleatorios
    let numeros = Math.floor(Math.random() * 900) + 100; 
    
    // Concatenamos las letras y los números
    let password = letras + numeros;
    return password;

 }



////********************************************* DATOS ALUMNO . HTML ******************************************** */
   ////*********************** ALTA ******************************************** */

     // Obtenemos el formulario
const formAlta = document.getElementById('formAlta');

// Agregamos un evento de escucha para el envío del formulario
formAlta.addEventListener('submit', function(event) {
    event.preventDefault(); // Evitamos que se envíe el formulario de forma predeterminada
    
    // Capturamos los valores de los campos de entrada
    const nombreAlumno = document.getElementById('nameAlum').value;
    const mailAlumno = document.getElementById('mailAlum').value;
    
    //preparamos el pass
    const passAlum=generarPassword(nombreAlumno);

    // Construimos el objeto con los datos del alumno a enviar al servidor
    const datosAlumno = {
        nombreUsuario: nombreAlumno,
        mailUsuario: mailAlumno,
        rolUsuario: 'alumno',
        passUsuario: passAlum
    };
    
    console.log(datosAlumno);

    // Realizamos la solicitud fetch al servidor para añadir el nuevo alumno
		    fetch('/aniadiralumno', {
			    method: 'POST',
			    headers: {
			        'Content-Type': 'application/json'
			    },
		    body: JSON.stringify(datosAlumno)
})
.then(response => {
    if (!response.ok) {
        throw new Error('Error en la respuesta del servidor');
    }
    return response.json();
})
.then(datosAlumno => {
    
    console.log(datosAlumno);
    
    alert(`El alumno ${datosAlumno.nombreUsuario} ha sido añadido correctamente`);
    
})
.catch(error => {
    console.error('Error:', error);
        alert('Error al procesar la solicitud');

});
});


   ////*********************** BAJA POR ID ******************************************** */
   
  /* bajaForm.addEventListener("submit", function(event) {
   		 event.preventDefault(); // Evita que se envíe el formulario de forma predeterminada

    // Captura el ID del formulario
    let idAlum = document.getElementById("idAlum").value;


    // Realiza la solicitud AJAX al servidor para eliminar al alumno por su ID
     fetch('/borrar_usuario/' + idAlum, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al dar de baja al alumno');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        alert('Alumno dado de baja correctamente');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Hubo un error al dar de baja al alumno');
    });
});
/*

////*********************** BAJA POR MAIL ******************************************** */
		

    if(bajaForm) {
		
		// el listener se activa cuanddo se activa el formulario de baja y el contenido del input
     bajaForm.addEventListener("submit", function(event) {
			
            event.preventDefault(); // Evita que se envíe el formulario de forma predeterminada

            // Captura el valor del campo de entrada
        let mailAlumBaja = document.getElementById("mailLog").value; // Cambia mailLog por el ID correcto
            	console.log(mailAlumBaja);
            	
            	//validamos
            	
        if (!mailAlumBaja) {
            alert("Por favor, ingresa un correo electrónico.");
            return; 
        }
      
//----- PETICION

            // Realiza la solicitud AJAX al servidor para eliminar al alumno por su ID
            fetch(`/borrar_usuario_mail/${encodeURIComponent(mailAlumBaja)}`, 
            {
                method: 'DELETE'
            })
            
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al dar de baja al alumno');
                }
                return response.json();
            })
            
            .then(data => {
                console.log(data);
               	 alert(`Alumno ${data.nombreUsuario} ha sido dado de baja correctamente`);
            })
            
            .catch(error => {
                console.error('Error:', error);
                	alert('Hubo un error al dar de baja al alumno');
            });
        });
    }


 
   ////*********************** UPDATE ******************************************** */
  const formModificacion = document.getElementById("formModificacion");

		if(formModificacion){

	
//----- LISTENER PARA MODIFICAR EL TIPO DE INPUT SEGUN LA ELECCION DEL SELECT
	//-- ESTE DATO SERÁ EL QUE SE MANDE A SERVIDOR PARA CAMBIAR
	
	    const selectMod = document.getElementById('opcionesMod');
			selectMod.addEventListener('change', function() {


	    // Obtenemos el valor de lo seleccionado
	    let opcionSeleccionada = this.value;

    // Obtenemos los inputs de actual y nuevo 
    let inputActual = document.querySelector('#actual input');
    let inputNuevo = document.querySelector('#nuevo input');

		    if (opcionSeleccionada === 'email') {
				
		        // Cambiamos los tipos de input a "email"
		        inputActual.setAttribute('type', 'email');
		        inputNuevo.setAttribute('type', 'email');
		        
		        
		    } else if (opcionSeleccionada === 'nombre') {
				
		        // Cambiamos los tipos de input a "text"
		        inputActual.setAttribute('type', 'text');
		        inputNuevo.setAttribute('type', 'text');
		    }
});

//----- LISTENER PARA ENVIAR LA INFORMACIÓN A SERVER Y ASÍ REALIZAR LA MODIFICACION

     formModificacion.addEventListener("submit", function(event) {
		 
		  // Evita que se envíe el formulario de forma predeterminada
	 		 event.preventDefault();
		
		 
	// ---------------------------------IDS
 
		 const mailUsuario = document.getElementById('mailTutor').value;
    	const opcionModificacion = document.getElementById('opcionesMod').value;
   		 const actual = document.querySelector('#actual input').value;
  		  const nuevo = document.querySelector('#nuevo input').value;

	//  objeto JSON con la información que se quiere modificar
   		 let datosModificacion = {};
    
    // campo de los datos del usuario vamos a modificar
		      if (opcionModificacion === 'nombre') {
				  
		        // Si estamos modificando el nombre
		        datosModificacion.nombreUsuario = nuevo;
		        
		    } else if (opcionModificacion === 'email') {
				
		        // Si estamos modificando el email
		        datosModificacion.mailUsuario = nuevo;
		    }

		   console.log(datosModificacion);

//----- PETICION

		     fetch(`/modificamos_datos_usuario/${mailUsuario}`,
		            {
		        method: 'PUT', 
		        headers: {
		            'Content-Type': 'application/json'
		        },
		        
		        body: JSON.stringify(datosModificacion) // Convertimos los datos a formato JSON

		    })
		    

		    .then(response => {
				
		        if (response.ok) {
					
				 console.log(response);

					 alert('Datos de ' + mailUsuario + ' modificados con éxito');
		         	   return response.json(); 
		            
		        } else {
		            throw new Error('Error en la modificación de los datos');
		        }
		    })
		    
		    .then(data => {
				
		        console.log('Datos modificados con éxito', data);
		        alert('Datos de modificados con éxito');
		    })
		    .catch(error => {
    			console.error('Error:', error);
				    if (error.response) {
				        console.error('Error Response:', error.response.data);
				    }
    			alert('Hubo un error al modificar los datos');
});
	     });
    }
  


//----- LISTENERS GENERALS

///Muestra los formularios segun la eleccion del profesor al pulsar el boton 
    radioForm.addEventListener('submit', gestionarRadioBtns);

// Quita la validación mientras escribes
    miInput.addEventListener('input', validacionTrue);

// Muestra el mensaje de validación
    miInput.addEventListener('invalid',validacionFalse);

// Quita los espacios al principio y al final
    miInput.addEventListener('blur', limpiaEspacios);
 
//Redirige a la página anterior--> menu
botonAtras.addEventListener('click', rediMenuProfesor);

//cierra la sesión--> login
botonExit.addEventListener('click', salirSesion);

});



////FIN FUNCION PETICION
