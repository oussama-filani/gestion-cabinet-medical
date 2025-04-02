package net.oussama.gestioncabinetmedical.dao;

import net.oussama.gestioncabinetmedical.entities.Consultation;
import net.oussama.gestioncabinetmedical.entities.Patient;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDao implements IConsultationDao {
    @Override
    public void create(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement(
                "INSERT INTO CONSULTATIONS (DATE_CONSULTATION, DESCRIPTION, ID_PATIENT) VALUES(?,?,?)"
        );
        pstm.setDate(1, consultation.getDateConsultation());
        pstm.setString(2, consultation.getDescription());
        pstm.setLong(3, consultation.getPatient().getId());
        pstm.executeUpdate();
    }

    @Override
    public void update(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement(
                "UPDATE CONSULTATIONS SET DATE_CONSULTATION=?, DESCRIPTION=?, ID_PATIENT=? WHERE ID_CONSULTATION=?"
        );
        pstm.setDate(1, consultation.getDateConsultation());
        pstm.setString(2, consultation.getDescription());
        pstm.setLong(3, consultation.getPatient().getId());
        pstm.setLong(4, consultation.getId());
        pstm.executeUpdate();
    }

    @Override
    public void delete(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM CONSULTATIONS WHERE ID_CONSULTATION=?");
        pstm.setLong(1, consultation.getId());
        pstm.executeUpdate();
    }

    @Override
    public List<Consultation> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement(
                "SELECT * FROM CONSULTATIONS"
        );
        ResultSet rs = pstm.executeQuery();
        List<Consultation> consultations = new ArrayList<>();

        PatientDao patientDao = new PatientDao();

        while (rs.next()) {
            Consultation consultation = new Consultation();
            consultation.setId(rs.getLong("ID_CONSULTATION"));
            consultation.setDateConsultation(rs.getDate("DATE_CONSULTATION"));
            consultation.setDescription(rs.getString("DESCRIPTION"));

            // Récupérer l'objet Patient associé
            Long idPatient = rs.getLong("ID_PATIENT");
            consultation.setPatient(patientDao.findById(idPatient));

            consultations.add(consultation);
        }
        return consultations;
    }

    @Override
    public Consultation findById(Long id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement(
                "SELECT * FROM CONSULTATIONS WHERE ID_CONSULTATION=?"
        );
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();

        PatientDao patientDao = new PatientDao();

        Consultation consultation = null;
        if (rs.next()) {
            consultation = new Consultation();
            consultation.setId(rs.getLong("ID_CONSULTATION"));
            consultation.setDateConsultation(rs.getDate("DATE_CONSULTATION"));
            consultation.setDescription(rs.getString("DESCRIPTION"));

            // Récupérer l'objet Patient associé
            Long idPatient = rs.getLong("ID_PATIENT");
            consultation.setPatient(patientDao.findById(idPatient));
        }
        return consultation;
    }

    // Méthode supplémentaire pour trouver les consultations d'un patient spécifique
    public List<Consultation> findByPatientId(Long patientId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement(
                "SELECT * FROM CONSULTATIONS WHERE ID_PATIENT=?"
        );
        pstm.setLong(1, patientId);
        ResultSet rs = pstm.executeQuery();
        List<Consultation> consultations = new ArrayList<>();

        PatientDao patientDao = new PatientDao();
        Patient patient = patientDao.findById(patientId);

        while (rs.next()) {
            Consultation consultation = new Consultation();
            consultation.setId(rs.getLong("ID_CONSULTATION"));
            consultation.setDateConsultation(rs.getDate("DATE_CONSULTATION"));
            consultation.setDescription(rs.getString("DESCRIPTION"));
            consultation.setPatient(patient);

            consultations.add(consultation);
        }
        return consultations;
    }
}