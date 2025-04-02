package net.oussama.gestioncabinetmedical.presentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.oussama.gestioncabinetmedical.dao.ConsultationDao;
import net.oussama.gestioncabinetmedical.dao.PatientDao;
import net.oussama.gestioncabinetmedical.service.CabinetService;
import net.oussama.gestioncabinetmedical.service.ICabinetService;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialisation des DAOs
        PatientDao patientDao = new PatientDao();
        ConsultationDao consultationDao = new ConsultationDao();

        // Initialisation du service
        ICabinetService cabinetService = new CabinetService(patientDao, consultationDao);

        // Création de la vue principale
        MainView mainView = new MainView();

        // Création des contrôleurs
        PatientController patientController = new PatientController(mainView.getPatientView(), cabinetService);
        ConsultationController consultationController = new ConsultationController(mainView.getConsultationView(), cabinetService);

        // Configuration de la scène
        Scene scene = new Scene(mainView, 900, 600);
        primaryStage.setTitle("Gestion des Consultations Médicales");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}