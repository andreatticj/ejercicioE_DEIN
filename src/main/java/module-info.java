module eu.andreatt.ejerciciod_dein {
    requires javafx.controls;
    requires javafx.fxml;


//    opens eu.andreatt.ejerciciod_dein to javafx.fxml;
//    exports eu.andreatt.ejerciciod_dein;
    exports eu.andreatt.ejerciciod_dein.controller;
    opens eu.andreatt.ejerciciod_dein.controller to javafx.fxml;
    exports eu.andreatt.ejerciciod_dein.application;
    opens eu.andreatt.ejerciciod_dein.application to javafx.fxml;
}