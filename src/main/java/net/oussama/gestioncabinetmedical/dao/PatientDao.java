package net.oussama.gestioncabinetmedical.dao;

import net.oussama.gestioncabinetmedical.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements IPatientDao {
    @Override
    public void create(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO PATIENTS(NOM,PRENOM,TEL)" + "VALUES(?,?,?)");
        pstm.setString(1, patient.getNom());
        pstm.setString(2, patient.getPrenom());
        pstm.setString(3, patient.getTel());
        pstm.executeUpdate();

    }

    @Override
    public void update(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE PATIENTS SET NOM=?,PRENOM=?,TEL=? WHERE ID_PATIENT=?");
        pstm.setString(1, patient.getNom());
        pstm.setString(2, patient.getPrenom());
        pstm.setString(3, patient.getTel());
        pstm.setLong(4, patient.getId());
        pstm.executeUpdate();
    }

    @Override
    public void delete(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM PATIENTS WHERE ID_PATIENT=?");
        pstm.setLong(1, patient.getId());
        pstm.executeUpdate();
    }

    @Override
    public List<Patient> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PATIENTS");
        ResultSet rs = pstm.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getLong("ID_PATIENT"));
            patient.setNom(rs.getString("NOM"));
            patient.setPrenom(rs.getString("PRENOM"));
            patient.setTel(rs.getString("TEL"));
            patients.add(patient);
        }

        return patients;
    }

    @Override
    public Patient findById(Long id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PATIENTS WHERE ID_PATIENT=?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        Patient patient = new Patient();


        if (rs.next()) {
            patient.setId(rs.getLong("ID_PATIENT"));
            patient.setNom(rs.getString("NOM"));
            patient.setPrenom(rs.getString("PRENOM"));
            patient.setTel(rs.getString("TEL"));
        }
        return patient;
    }
}