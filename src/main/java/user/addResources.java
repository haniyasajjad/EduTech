package user;

import sda.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;

import courseResources.*;
import database.Main;


public class addResources {
    @FXML private Button quizButton;
    @FXML private Button assignmentButton;
    @FXML private Button lectureVideoButton;
    @FXML private Button exerciseButton;
    @FXML private Button bookButton;
    @FXML private Button summaryNotesButton;

    private static String selectedCourseName;

    // Setter method to receive the selected course from CourseController
    public static void setCourse(String courseName) {
        selectedCourseName = courseName;  // Set the selected course name
        
        System.out.println("Selected course" + selectedCourseName);
    }

    // Method for initializing the buttons (called after FXML is loaded)
    public void initialize() {
        quizButton.setOnAction(event -> handleAddResource("Quiz"));
        assignmentButton.setOnAction(event -> handleAddResource("Assignment"));
        lectureVideoButton.setOnAction(event -> handleAddResource("Lecture Video"));
        exerciseButton.setOnAction(event -> handleAddResource("Exercise"));
        bookButton.setOnAction(event -> handleAddResource("Book"));
        summaryNotesButton.setOnAction(event -> handleAddResource("Summary Notes"));
    }

    // Handle resource selection
    private void handleAddResource(String resourceType) {
        if ("Quiz".equals(resourceType)) {
            try {
            	App.setRoot("addQuiz"); // Assuming your FXML file is in the `users` package
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ("Exercise".equals(resourceType)) {
            try {
            	App.setRoot("addExercise"); // Assuming your FXML file is in the `users` package
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ("Assignment".equals(resourceType)) {
            try {
            	App.setRoot("addAssignment"); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ("Book".equals(resourceType)) {
            try {
                navigateTo("addBook");
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if ("Summary Notes".equals(resourceType)) {
            try {
                navigateTo("addSummary");
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if ("Lecture Video".equals(resourceType)) {
            try {
                navigateTo("addVideos");
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Navigate to the specified resource type FXML file
    private void navigateTo(String fxmlFilePath) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/sda/" + fxmlFilePath + ".fxml"));
            Parent root = loader.load();

            // Get the controller for the loaded FXML
            Object controller = loader.getController();

            // Dynamically check the controller type and set the course name
            if (controller instanceof bookController) {
                ((bookController) controller).setCourseName(selectedCourseName);
            } 
            else if (controller instanceof summaryController) {
                ((summaryController) controller).setCourseName(selectedCourseName);
            } 
            else if (controller instanceof videosController) {
                ((videosController) controller).setCourseName(selectedCourseName);
            }

            // Set the root of the scene
            App.setRoot(fxmlFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGoBack() {
        try {
            App.setRoot("adminMenu");
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("Could not go back to the previous screen.");
            alert.showAndWait();
        }
    }
    
}
