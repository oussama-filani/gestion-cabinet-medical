package net.oussama.gestioncabinetmedical.presentation;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.kordamp.bootstrapfx.BootstrapFX;

public class MainView extends BorderPane {

    private TabPane tabPane;
    private PatientView patientView;
    private ConsultationView consultationView;

    public MainView() {
        // Application du style Bootstrap
        this.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        // Création des vues
        patientView = new PatientView();
        consultationView = new ConsultationView();

        // Configuration du TabPane
        tabPane = new TabPane();
        tabPane.getTabs().addAll(
                createTab("Patients", patientView),
                createTab("Consultations", consultationView)
        );
        tabPane.getStyleClass().add("tabs-light");

        // En-tête de l'application
        Label titleLabel = new Label("Gestion des Consultations Médicales");
        titleLabel.getStyleClass().addAll("h3", "text-primary");

        ToolBar header = new ToolBar(titleLabel);
        header.getStyleClass().add("bg-light");

        // Configuration du BorderPane
        setTop(header);
        setCenter(tabPane);
    }

    private Tab createTab(String title, javafx.scene.Node content) {
        Tab tab = new Tab(title);
        tab.setContent(content);
        tab.setClosable(false);
        return tab;
    }

    public PatientView getPatientView() {
        return patientView;
    }

    public ConsultationView getConsultationView() {
        return consultationView;
    }
}