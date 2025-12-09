package courseResources;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sda.App;
import courses.Course;
import database.ResourcesInDb;

public class videosController {

     private static String selectedCourseName;  // The selected course name for video addition
    private String resourceType;  // The resource type (Video)

    @FXML private TextField courseNameField;      // Text field for course name
    @FXML private TextField videoTitleField;      // Text field for video title
    @FXML private TextField videoUrlField;        // Text field for video URL
    @FXML private TextArea videoDescriptionArea;  // Text area for video description
    @FXML private Button addVideoButton;          // Button to add a video
    @FXML private Button backButton;              // Button for going back

    // Setter methods to pass course and resource type
    public void setCourseName(String courseName) {
        selectedCourseName = courseName; // Store the selected course name
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType; // Set resource type (Video)
    }

    // Handle Add Video button click
    @FXML
    private void handleAddVideo() {
        String videoTitle = videoTitleField.getText();
        String videoDescription = videoDescriptionArea.getText();
        String videoUrl = videoUrlField.getText();

        // Validate inputs
        if (videoTitle.isEmpty() || videoDescription.isEmpty() || videoUrl.isEmpty()) {
            showAlert("Please fill in the video title, description, and URL.");
        } else {
            // Search for the course by name from the global courses list in Main
            Course selectedCourse = findCourseByName(selectedCourseName);
            
            if (selectedCourse != null) {
                // Add the video to the found course
                selectedCourse.addVideo(videoTitle, videoDescription, selectedCourseName, videoUrl); // Assuming addVideo is defined in Course.java
                Videos v=new Videos(videoTitle, "", selectedCourseName, videoUrl,videoDescription);
                try {
                    ResourcesInDb.addVideo(v);
                    System.out.println("Video Added to db");

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Video not Added to db");
                }
                // Show success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Video Added");
                successAlert.setHeaderText("Success");
                successAlert.setContentText("The video '" + videoTitle + "' has been added to the course '" + selectedCourseName + "'.");
                successAlert.showAndWait();
                
                // Clear input fields after adding the video
                clearFields();
            } else {
                // If no course with the provided name is found
                showAlert("No course found with the name '" + selectedCourseName + "'. Please check the course name.");
            }
        }

        try {
            App.setRoot("usertype");
        } catch (Exception e) {
            System.out.println("Don't know y");
        }
    }

    // Method to find a course by its name from Main's courses list
    private Course findCourseByName(String courseName) {
        // Access the courses list from Main and search for the course by name
        for (Course course : App.courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;  // Return the course if it matches the name
            }
        }
        return null;  // Return null if no course is found with the given name
    }

    // Method to handle Back button click (optional functionality)
    @FXML
    private void handleBackButton() {
        System.out.println("Back button clicked!");
        try {
            App.setRoot("adminMenu");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Cannot load adminMenu");
        }
       
        // Implement back button functionality (e.g., navigate to the previous screen)
    }

    // Method to show an alert with a custom message
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText("Incomplete Information");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to clear all input fields
    private void clearFields() {
        courseNameField.clear();
        videoTitleField.clear();
        videoUrlField.clear();
        videoDescriptionArea.clear();
    }
    
}
