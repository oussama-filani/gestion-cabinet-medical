package net.oussama.gestioncabinetmedical.service;

import net.oussama.gestioncabinetmedical.dao.IConsultationDao;
import net.oussama.gestioncabinetmedical.dao.IPatientDao;
import net.oussama.gestioncabinetmedical.entities.Consultation;
import net.oussama.gestioncabinetmedical.entities.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CabinetService implements ICabinetService {
    private IPatientDao patientDao;
    private IConsultationDao iconsultationDao;

    public CabinetService(IPatientDao patientDao, IConsultationDao iconsultationDao) {
        this.patientDao = patientDao;
        this.iconsultationDao = iconsultationDao;
    }

    // Patients
    @Override
    public void addPatient(Patient patient) {
        try {
            patientDao.create(patient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatient(Patient patient) {
        try {
            patientDao.delete(patient);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updatePatient(Patient patient) {
        try {
            patientDao.update(patient);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = null;
        try {
            patients = patientDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patient = null;
        try {
            patient = patientDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    // Consultation

    @Override
    public void addConsultation(Consultation consultation) {
        try {
            iconsultationDao.create(consultation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        try {
            iconsultationDao.delete(consultation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        try {
            iconsultationDao.update(consultation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultation> getAllConsultations() {
        List<Consultation> consultations = new ArrayList<>();
        try {
            consultations = iconsultationDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public Consultation getConsultationById(Long id) {
        Consultation consultation = null;
        try {
            consultation = iconsultationDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultation;
    }
}