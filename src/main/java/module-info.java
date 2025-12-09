module sda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
	requires java.desktop;
    

    opens sda to javafx.fxml;
    opens user to javafx.fxml;
    opens student to javafx.fxml; 
    opens courses to javafx.fxml; 
    opens courseResources to javafx.fxml;
    opens assessments to javafx.fxml, javafx.base;
    exports student;
    exports sda;
    exports user;
    exports courses;
    exports assessments;


    
}
