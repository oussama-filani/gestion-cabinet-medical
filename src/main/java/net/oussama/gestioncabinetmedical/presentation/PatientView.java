package net.oussama.gestioncabinetmedical.presentation;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.oussama.gestioncabinetmedical.entities.Patient;

public class PatientView extends VBox {

    private TextField idField;
    private TextField nomField;
    private TextField prenomField;
    private TextField telField;
    private Button addButton;
    private Button updateButton;
    private Button deleteButton;
    private TableView<Patient> tableView;

    public PatientView() {

        // Link the external CSS stylesheet for PatientView
        this.getStylesheets().add(getClass().getResource("/styles/patient-view.css").toExternalForm());


        setPadding(new Insets(20)); // Added more padding for spacious layout
        setSpacing(20);  // Increased spacing between components

        // Formulaire pour les informations patient
        GridPane formPane = new GridPane();
        formPane.setHgap(15);  // Adjusted gap between form elements
        formPane.setVgap(15);  // Adjusted gap between form elements
        formPane.setPadding(new Insets(20));
        formPane.getStyleClass().add("panel-primary");

        Label formTitle = new Label("Informations du Patient");
        formTitle.getStyleClass().addAll("panel-heading", "h4");

        idField = new TextField();
        idField.setEditable(false);
        idField.setPromptText("ID (généré automatiquement)");

        nomField = new TextField();
        nomField.setPromptText("Nom du patient");

        prenomField = new TextField();
        prenomField.setPromptText("Prénom du patient");

        telField = new TextField();
        telField.setPromptText("Numéro de téléphone");

        formPane.add(new Label("ID:"), 0, 0);
        formPane.add(idField, 1, 0);
        formPane.add(new Label("Nom:"), 0, 1);
        formPane.add(nomField, 1, 1);
        formPane.add(new Label("Prénom:"), 0, 2);
        formPane.add(prenomField, 1, 2);
        formPane.add(new Label("Téléphone:"), 0, 3);
        formPane.add(telField, 1, 3);

        // Boutons d'action
        HBox buttonBar = new HBox(15);  // Increased button spacing
        buttonBar.setPadding(new Insets(10, 0, 10, 0)); // Padding for button row

        addButton = new Button("Ajouter");
        addButton.getStyleClass().addAll("btn", "btn-success");

        updateButton = new Button("Modifier");
        updateButton.getStyleClass().addAll("btn", "btn-primary");

        deleteButton = new Button("Supprimer");
        deleteButton.getStyleClass().addAll("btn", "btn-danger");

        buttonBar.getChildren().addAll(addButton, updateButton, deleteButton);

        // Table des patients
        tableView = new TableView<>();

        TableColumn<Patient, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Patient, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Patient, String> prenomColumn = new TableColumn<>("Prénom");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Patient, String> telColumn = new TableColumn<>("Téléphone");
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));

        tableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, telColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Ajout des éléments à la vue
        getChildren().addAll(formTitle, formPane, buttonBar, tableView);

        // Sélection d'une ligne dans la table
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idField.setText(String.valueOf(newSelection.getId()));
                nomField.setText(newSelection.getNom());
                prenomField.setText(newSelection.getPrenom());
                telField.setText(newSelection.getTel());
            }
        });
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getNomField() {
        return nomField;
    }

    public TextField getPrenomField() {
        return prenomField;
    }

    public TextField getTelField() {
        return telField;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public TableView<Patient> getTableView() {
        return tableView;
    }

    public void clearFields() {
        idField.clear();
        nomField.clear();
        prenomField.clear();
        telField.clear();
    }
}
