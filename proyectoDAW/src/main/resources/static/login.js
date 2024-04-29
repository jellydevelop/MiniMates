//----- RECOGIDA IDS


let btnSubmit = document.getElementById("botonComprobar");
let form = document.querySelector("form");

//----- LISTENER
btnSubmit.addEventListener("click",mandamosInfoUser) ;

//----- FCNES


async function mandamosInfoUser() {
    
    /// EVITAMOS ENVIOS VACIOS DEL FORMULARIO
    event.preventDefault();

    /// RECOGIDA CONTENIDO FORMULARIO
    let mailUser = document.getElementById("mailUser").value;
    let passUser = document.getElementById("passUser").value;

    console.log('datos formulario');

            ///PRUEBA DE RECOGIDA  DE DATOS
                console.log(mailUser);
                console.log(passUser);

    // PREPARAMOS JSON CON DATOS PARA ENVIAR
        let data = {
			mailUser: mailUser,
            passUser: passUser
            
            
        };
        console.log('data');


        console.log('datos json:'+ data);

    // OPCIONES PARA PETICION
        let options = {
            method: 'POST',
            body: JSON.stringify(data), // Convierte el objeto JavaScript en una cadena JSONV
            headers: {
                'Content-Type': 'application/json' 
            }
        }

    // PETICION HTTP
    try {
        const response = await fetch('/verificacion', options);
        
        if (!response.ok) {
            throw new Error('Error en la solicitud. Código de estado: ' + response.status);
        }
        
        const redirectUrl = await response.text();
        
        window.location.href = redirectUrl;
        
    } catch (error) {
		
        console.error('Error:', error);
    }


           
}////FIN FUNCION PETICION

    

