module net.oussama.gestioncabinetmedical {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;


    opens net.oussama.gestioncabinetmedical.entities to javafx.base;
    exports net.oussama.gestioncabinetmedical;
    exports net.oussama.gestioncabinetmedical.presentation;
}