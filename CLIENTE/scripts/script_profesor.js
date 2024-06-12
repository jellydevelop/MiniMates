
    
     
    //----------------ID
    const radioStats=document.getElementById('stats');
    const radioJugar=document.getElementById('dataAlum');
    
    
    //----------------FUNCIONES
    
    
    
    function rediProfesor(evento){
    
        if(evento){
    
            if(radioStats.checked){
                window.location.href='estadisticas_profesor.html';
                radioStats.checked=false;
            }else{
                window.location.href='datos_alumno_profesor.html';
    
            }
        }
    }
    
    
    //----------------LISTENERS
    radioStats.addEventListener('change', rediProfesor);
    radioJugar.addEventListener('change', rediProfesor);
    
    