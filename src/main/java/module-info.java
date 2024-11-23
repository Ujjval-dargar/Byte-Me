module com.application.assignment4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.application.assignment4 to javafx.fxml;
    exports com.application.assignment4;
    exports com.application.assignment4.SceneControllers.Admin;
    opens com.application.assignment4.SceneControllers.Admin to javafx.fxml;
    exports com.application.assignment4.SceneControllers.Customer;
    opens com.application.assignment4.SceneControllers.Customer to javafx.fxml;
    exports com.application.assignment4.SceneControllers;
    opens com.application.assignment4.SceneControllers to javafx.fxml;
}