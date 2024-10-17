module eu.andreatt.ejercicioe_dein {
    requires javafx.controls;
    requires javafx.fxml;


    opens eu.andreatt.ejercicioe_dein to javafx.fxml;
    exports eu.andreatt.ejercicioe_dein;
}