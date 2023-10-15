module com.empleados.trabajo.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.empleados.trabajo.demo to javafx.fxml;
    exports com.empleados.trabajo.demo;
}