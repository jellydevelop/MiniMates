////********************************************* LOGIN . HTML ******************************************** */

//----- RECOGIDA IDS


let btnSubmit = document.getElementById("botonComprobar");
let form = document.querySelector("form");

//----- LISTENER
btnSubmit.addEventListener("click",mandamosInfoUser) ;

//----- FCNES


function mandamosInfoUser () {
    
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
            passUser: passUser,
            mailUser: mailUser
            
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
    fetch('/verificacion', options)

    .then(response => {
        if (response.ok) {

            console.log('response:'+ response);
            // Si la respuesta es exitosa y es una redirección, redireccionamos manualmente
            if (response.status === 302) {

                const redirectUrl = response.headers.get('Location');

                window.location.href = redirectUrl;

            } else {
                // Manejar otros casos de respuesta exitosa aquí
                return response.json(); 
            }

        } else {
            // Manejar otros casos de error aquí
            throw new Error('Error en la solicitud. Código de estado: ' + response.status); 
        }
    })

            // RESPUESTA DEL SERVIDOR
            .then(data => {
                console.log('Respuesta del servidor:', data); // Maneja la respuesta del servidor
            })

            // ERRORES DE PETICION
            .catch(error => {
                console.error('Error:', error); 
            });

            
 }////FIN FUNCION PETICION EN EL LOGIN

    
////******************************************************************************************************* */

////********************************************* LISTA ALUMNOS ESTADISTICAS . HTML ******************************************** */
//----- RECOGIDA IDS


let btnListado = document.getElementById("botonMostrarLista");
let formBtn = document.querySelector("formListaAlumnos");

//----- LISTENER
btnListado.addEventListener("click",preparamosListaAlumnos) ;

//----- FCNES

//// Esta funcion llama a servidor para que le mande un JSON con los 
////alumnos que aparezcan en la BDD a modo de enlace
function preparamosListaAlumnos () {

    // Hacemos una petición GET al servidor para obtener los datos de los alumnos
    fetch('/ruta/a/los/datos/de/los/alumnos') // Reemplaza esto con la ruta correcta

        .then(response => response.json())
        .then(datosServer => {

            // Supongamos que tienes un div donde quieres mostrar los enlaces
            let divEnlaces = document.getElementById("divEnlaces");

            // Limpiamos el div por si acaso
            divEnlaces.innerHTML = "";

            // Recorremos los datos recibidos del servidor
            for (let alumno of datosServer) {

                // Creamos un nuevo elemento <a>
                let enlace = document.createElement("a");

                // Configuramos el href y el texto del enlace
                enlace.href = "/ruta/al/alumno/" + alumno.id; // Reemplaza esto con la ruta correcta
                enlace.textContent = alumno.nombre; // Reemplaza esto con la propiedad correcta

                // Agregamos el enlace al div
                divEnlaces.appendChild(enlace);
            }
        })
        .catch(error => {

            console.error('Error:', error);
        });
}
////******************************************************************************************************* */

    

