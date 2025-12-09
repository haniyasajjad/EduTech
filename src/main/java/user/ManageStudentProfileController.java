package user;

import database.StudentLogin;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import sda.App;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ManageStudentProfileController {

    @FXML
    private FlowPane studentCardContainer;
    @FXML
    private Button backButton;

    private final StudentLogin studentLogin = new StudentLogin();

    @FXML
    public void initialize() {
        List<Map<String, Object>> students = fetchAllStudents();
        if (students != null) {
            for (Map<String, Object> student : students) {
                addStudentCard(student);
            }
        }
    }

    private List<Map<String, Object>> fetchAllStudents() {
        // Implement method to fetch all students from the database.
        return studentLogin.getAllStudents();  // You need to implement `getAllStudents()` in StudentLogin
    }

    private void addStudentCard(Map<String, Object> studentData) {
        StackPane card = new StackPane();
        card.setStyle("-fx-background-color: #e3f2fd; -fx-background-radius: 10; -fx-border-color: #64b5f6; -fx-border-radius: 10;");
        card.setEffect(new DropShadow(10, Color.GRAY));
    
        // Create a Circle for student icon (similar to Study Groups)
        Circle studentIcon = new Circle(30, Color.LIGHTBLUE);
        studentIcon.setStroke(Color.DARKBLUE);
        studentIcon.setStrokeWidth(2);

        // Create the text for student information
        Text studentInfo = new Text(
            "Name: " + studentData.get("name") + "\n" +
            "Username: " + studentData.get("username") + "\n" +
            "ID: " + studentData.get("student_id") + "\n" +
            "Date Created: " + studentData.get("created_at")
        );
        studentInfo.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 16px; -fx-fill: black;");
    
        // Add elements to the card
        card.getChildren().addAll(studentIcon, studentInfo);
        studentCardContainer.getChildren().add(card);
    
        // Add animation for enlarging the card on click (like Study Groups)
        card.setOnMouseClicked(e -> expandCard(card));
    }

    private void expandCard(StackPane card) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), card);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);
        scaleTransition.play();

        // Close button to minimize the card
        Button closeButton = new Button("X");
        closeButton.setOnAction(e -> collapseCard(card, closeButton));
        card.getChildren().add(closeButton);
    }

    private void collapseCard(StackPane card, Button closeButton) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), card);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();

        card.getChildren().remove(closeButton);
    }

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        App.setRoot("adminMenu");  // Navigates back to admin menu
    }
}
