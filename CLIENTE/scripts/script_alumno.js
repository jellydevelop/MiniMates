document.addEventListener('DOMContentLoaded', function() {

//----------------VERIFICACION DE PARTIDA YA JUGADA

    // Verificar si el alumno ha jugado antes
    if (localStorage.getItem('haJugado')) {

        // Si ha jugado, ocultar la opción de jugar
        const jugarOption = document.getElementById('dataPartidas');
        const jugarOption2 = document.getElementById('dataLabel');
        
        if (jugarOption) {
            jugarOption.hidden=true;
            jugarOption2.hidden=true;
        }
    }
//----------------ID
const radioStats=document.getElementById('statsAlum');
const radioJugar=document.getElementById('dataPartidas');


//----------------FUNCIONES



function rediAlumno(evento){

    if(evento){

        if(radioStats.checked){
            window.location.href='estadisticas_alumno.html';
            radioStats.checked=false;
        }else{
            window.location.href='pagina3_presentacion.html';

        }
    }
}


//----------------LISTENERS
radioStats.addEventListener('change', rediAlumno);
radioJugar.addEventListener('change', rediAlumno);

});