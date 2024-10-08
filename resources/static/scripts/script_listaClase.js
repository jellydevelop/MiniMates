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
const botonExit = document.getElementById('exit');
const botonAtras = document.getElementById('rediAtras');


//----------------FUNCIONES


//************************** */

function rediAlumnoStats() {

    const nombreAlumno=document.getElementsByTagName("a").value;

    window.location.href = '/tus_estadisticas'+"?nombreAlumno"; 
}

//************************** */

function salirSesion() {
    localStorage.removeItem('haJugado'); 
    window.location.href = '/login'; 
}
//************************** */

function rediMenuProfe(evento){

      window.location.href = '/profesor'; 

}
//************************** */



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

//----------------LISTENERS
botonExit.addEventListener('click', salirSesion);
botonAtras.addEventListener('click', rediMenuProfe);


//----------------PETICION
	
	
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




