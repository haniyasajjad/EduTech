package courseResources;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import courses.Course;
import database.Main;
import database.ResourcesInDb;

import java.util.ArrayList;
import java.util.List;

import sda.App;

public class bookController {
    private String resourceType;  // The resource type (Book)
    private static String selectedCourseName; // The selected course name

    @FXML private TextField bookTitleField;
    @FXML private TextField bookDescriptionField;
    @FXML private TextField bookUrlField;

    // Setter methods to pass courses and resource type
    public void setCourseName(String courseName) {
        selectedCourseName = courseName; // Store the selected course name
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType; // Set resource type (if needed)
    }

    @FXML
    private void handleAddBook() {
        String bookTitle = bookTitleField.getText();
        String bookDescription = bookDescriptionField.getText();
        String bookUrl = bookUrlField.getText();

        // Validate inputs
        if (bookTitle.isEmpty() || bookDescription.isEmpty() || bookUrl.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in the book title, description, and URL.");
            alert.showAndWait();
        } else {
            // Search for the course by name from the global courses list in Main
            Course selectedCourse = findCourseByName(selectedCourseName);
            
            if (selectedCourse != null) {
                // Add the book to the found course
                selectedCourse.addBook(bookTitle, bookDescription, selectedCourseName, bookUrl); // Matching addBook method in Course.java
                Book b=new Book(bookTitle,bookDescription,selectedCourseName,bookUrl);
                try {
                    ResourcesInDb.addBook(b);
                    System.out.println("Book Added to db");
                } catch (Exception e) {
                    System.out.println("Cannot Add books to db");
                }
               
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Book Added");
                alert.setHeaderText("Success");
                alert.setContentText("The book '" + bookTitle + "' has been added to the course '" + selectedCourseName + "'.");
                alert.showAndWait();
            } else {
                // If no course with the provided name is found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Course Not Found");
                alert.setHeaderText("Error");
                alert.setContentText("No course found with the name '" + selectedCourseName + "'. Please check the course name.");
                alert.showAndWait();
            }
        }
        try {
            App.setRoot("usertype");
        } catch (Exception e) {
            System.out.println("Don't know y");
        }
       
    }

    // Helper method to find a course by its name from Main's courses list
    private Course findCourseByName(String courseName) {
        // Access the courses list from Main and search for the course by name
        for (Course course : App.courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;  // Return the course if it matches the name
            }
        }
        return null;  // Return null if no course is found with the given name
    }
    
}
