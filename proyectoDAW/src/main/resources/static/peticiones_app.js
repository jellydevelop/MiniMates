////********************************************* LOGIN . HTML ******************************************** */

// RECOGIDA DE DATOS DEL FORMULARIO
let mailUser = document.getElementById("mailUser").value;
let passUser = document.getElementById("passUser").value;


// PETICION
async function mandamosInfoUser(event) {
    event.preventDefault();

    // RECOGIDA DE DATOS DEL FORMULARIO
    let mailUser = document.getElementById("mailUser").value;
    let passUser = document.getElementById("passUser").value;

   /* // CREACIÓN DEL OBJETO USUARIO
    let usuario = {
        mail: mailUser,
        passUsuario: passUser
    */

    // OPCIONES PARA PETICION
    let options = {
    method: 'POST',
    body: JSON.stringify({ mail: mailUser, pass: passUser }),
    headers: {
        'Content-Type': 'application/json'
    }
};


    try {
        // PETICION HTTP
const response = await fetch('http://localhost:8080/verificacion', options);
        if (!response.ok) {
            throw new Error('Error en la solicitud. Código de estado: ' + response.status);
        }

        // RESPUESTA DEL SERVIDOR
        const data = await response.json();
        const redirectUrl = data.redirectUrl;

        // Redirigir al usuario a la URL proporcionada
        window.location.href = redirectUrl;
        
    } catch (error) {
        console.error('Error:', error.message);
    }
}

/// ----- LISTENER

document.getElementById("botonComprobar").addEventListener("click", mandamosInfoUser);

 ////FIN FUNCION PETICION EN EL LOGIN

   
    
////******************************************************************************************************* */

////********************************************* LISTA ALUMNOS ESTADISTICAS . HTML ******************************************** */
document.addEventListener("DOMContentLoaded", function() {
    // Realiza una solicitud al backend para obtener la lista de alumnos
    fetch("/obtener_alumnos_clase")
    .then(response => {
        if (!response.ok) {
            throw new Error('No se pudo obtener la lista de alumnos');
        }
        return response.json();
    })
    .then(data => {
        // Procesar los datos y mostrar la lista de alumnos en la página
        mostrarListaAlumnos(data);
    })
    .catch(error => {
        console.error('Error al obtener la lista de alumnos:', error);
    });
});

function mostrarListaAlumnos(listaAlumnos) {
    let divEnlaces = document.getElementById("divEnlaces");
    divEnlaces.innerHTML = ""; // Borra el contenido anterior del div

    // Crea una lista ul y agrega un elemento li por cada alumno
    let ul = document.createElement("ul");
    
   // Itera sobre la lista de alumnos
    listaAlumnos.forEach(alumno => {
        // Crea un elemento de lista li
        let li = document.createElement("li");
        
        // Crea un enlace a la página de detalles del alumno
        let enlace = document.createElement("a");
        enlace.href = `/detalle_alumno?id=${alumno.id}`; // Suponiendo que tengas una página para mostrar los detalles del alumno y pasas el ID como parámetro en la URL
        enlace.textContent = `${alumno.nombre}`; // Suponiendo que tienes propiedades nombre y edad en tu objeto alumno
        
        // Agrega el enlace al elemento de lista
        li.appendChild(enlace);
        
        // Agrega el elemento de lista a la lista ul
        ul.appendChild(li);
    });
    
    // Agrega la lista ul al divEnlaces
    divEnlaces.appendChild(ul);
}
////******************************************************************************************************* */






