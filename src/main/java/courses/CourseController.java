package courses;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sda.App;
import user.*;
import java.io.IOException;



public class CourseController {
    @FXML
    private ListView<String> courseListView;  // ListView to display courses

    private static ObservableList<String> courses = FXCollections.observableArrayList(); // Store the courses

    // This method will be called from adminMenu controller to pass the courses list
    public static void setCourses(java.util.List<Course> courseList) {
        // Convert the List of Course objects to a List of Strings (e.g., course names)
        courses.clear();
        for (Course course : courseList) {
            courses.add(course.getCourseName());  // Add the course name to the list
        }
    }

    // This method is used to handle the forwarding of the selected course
    @FXML
    private void handleForwardCourse() {
        String selectedCourse = courseListView.getSelectionModel().getSelectedItem();
        
        if (selectedCourse != null) {
            showAlert("Course Selected", "You have selected: " + selectedCourse);
            // Forward the selected course to the addResources page
            forwardCourse(selectedCourse);
        } else {
            showAlert("No Selection", "Please select a course first.");
        }
    }

    // Utility method to display alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Forward the selected course to the addResources page using setRoot
    private void forwardCourse(String selectedCourse) {
        // Simply forward the course to addResources by using the setRoot method
        try {
        	addResources.setCourse(selectedCourse);  // Call setCourse method to pass course name
            App.setRoot("addResources");  // Set the root to addResources.fxml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // Initialize the ListView with the courses
        courseListView.setItems(courses);
    }
    
}
