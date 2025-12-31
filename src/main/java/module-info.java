module edu.khas.cmpe343.group5 {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.khas.cmpe343.group5 to javafx.fxml;
    exports edu.khas.cmpe343.group5;
}
