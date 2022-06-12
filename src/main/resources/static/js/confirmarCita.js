function cambiarDatos(dniNColegiadoParametro, diaCitaParametro, horaCitaParametro,
prestacionParametro, apellidosDoctorParametro, nombreDoctorParametro, especialidadParametro, pacienteParametro){

    let dniNColegiado=document.getElementById("dniNColegiado");
    dniNColegiado.value=dniNColegiadoParametro;

    let diaCita=document.getElementById("diaCita");
    diaCita.innerHTML=diaCitaParametro;

    let diaCitaInput=document.getElementById("diaCitaInput");
    diaCitaInput.value=diaCitaParametro;

    let horaCita=document.getElementById("horaCita");
    horaCita.innerHTML=horaCitaParametro;

    let horaCitaInput=document.getElementById("horaCitaInput");
    horaCitaInput.value=horaCitaParametro;

    let paciente=document.getElementById("paciente");
    paciente.innerHTML=pacienteParametro;

    let pacienteInput=document.getElementById("pacienteInput");
    pacienteInput.value=pacienteParametro;

    
    let prestacion=document.getElementById("prestacion");
    let apellidosDoctor=document.getElementById("apellidosDoctor");
    let nombreDoctor=document.getElementById("nombreDoctor");
    let especialidad=document.getElementById("especialidad");
    
    prestacion.innerHTML=prestacionParametro;
    apellidosDoctor.innerHTML=apellidosDoctorParametro;
    nombreDoctor.innerHTML=nombreDoctorParametro;
    especialidad.innerHTML=especialidadParametro;
    
}