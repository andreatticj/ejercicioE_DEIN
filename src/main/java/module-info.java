module eu.andreatt.ejercicioe_dein {
    requires javafx.controls;
    requires javafx.fxml;


//    opens eu.andreatt.ejercicioe_dein to javafx.fxml;
//    exports eu.andreatt.ejercicioe_dein;
    exports eu.andreatt.ejercicioe_dein.controller;
    opens eu.andreatt.ejercicioe_dein.controller to javafx.fxml;
    exports eu.andreatt.ejercicioe_dein.application;
    opens eu.andreatt.ejercicioe_dein.application to javafx.fxml;
}