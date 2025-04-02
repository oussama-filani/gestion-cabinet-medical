package net.oussama.gestioncabinetmedical.service;

import net.oussama.gestioncabinetmedical.dao.ConsultationDao;
import net.oussama.gestioncabinetmedical.dao.PatientDao;
import net.oussama.gestioncabinetmedical.entities.Patient;

import java.sql.SQLOutput;
import java.util.List;

public class ServiceTest {
    public static void main(String[] args) {
        ICabinetService service=new CabinetService(new PatientDao(), new ConsultationDao());
        //Create new patients
        Patient patient = new Patient();
        patient.setNom("Oussama");
        patient.setPrenom("Testoo");
        patient.setTel("06006960");
        service.addPatient(patient);
        // Lister les patients
        /*List<Patient> patients=service.getAllPatients();
        patients.forEach(patient -> System.out.println(patient));*/

        // lister les patients par Id
        /*Patient patient=service.getPatientById(1L);
        System.out.println(patient);*/

        // Modifier un patient
        /*Patient patient=service.getPatientById(1L);
        patient.setTel("94884848");
        service.updatePatient(patient);*/


    }
}