package user;


import java.io.IOException;
import java.util.Map;

import database.StudentLogin;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sda.App;


public class ParentTrackPerformance {
   @FXML private Text headerText;
    @FXML private TextField childIdField;
    @FXML private VBox performanceDetailsContainer;
    
    @FXML private PieChart activityChart;  // Pie chart for activities (quizzes, assignments, etc.)
    @FXML private PieChart progressChart;  // Pie chart for progress (completed, remaining, etc.)

    private StudentLogin studentLogin = new StudentLogin(); // Assuming StudentLogin is your existing class

    @FXML
    public void handleSubmit() {
        String childIdText = childIdField.getText();
        
        if (childIdText != null && !childIdText.trim().isEmpty()) {
            try {
                int childId = Integer.parseInt(childIdText);  // Parse the child ID
                
                // Fetch the child's data using the student ID
                Map<String, Object> childData = studentLogin.getChildInfo(childId);
                
                if (childData != null) {
                    // If data is found, update the UI with the child's data
                    updatePerformanceDetails(childData);
                } else {
                    // Handle the case where no student data was found
                    System.err.println("No data found for child ID: " + childId);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid child ID format: " + e.getMessage());
            }
        } else {
            System.err.println("Please enter a valid child ID.");
        }
    }

    private void setPieChartSize(PieChart chart, double width, double height) {
        chart.setMinSize(width, height);
        chart.setMaxSize(width, height);
        chart.setPrefSize(width, height);
    }
    
    private void updatePerformanceDetails(Map<String, Object> childData) {
        // Display basic information
        Text performanceText = new Text("Performance of Child: " + childData.get("name"));
        performanceDetailsContainer.getChildren().clear();
        performanceDetailsContainer.getChildren().add(performanceText);
        
        // Extract performance data from the child data map
        int quizzesAttempted = (int) childData.get("number_of_quizzes_attempted");
        int assignmentsAttempted = (int) childData.get("number_of_assignments_attempted");
        int totalCourses = (int) childData.get("number_of_courses");

        // Ensure both PieCharts are the same size
        //setPieChartSize(activityChart, 200, 200);
        setPieChartSize(progressChart, 200, 200);
        // Adding activity chart data (e.g., quizzes, assignments)
        activityChart.getData().clear(); // Clear any previous data
        activityChart.getData().addAll();

         // Populating PieCharts
         activityChart.getData().addAll(
            new PieChart.Data("Quizzes", quizzesAttempted),
            new PieChart.Data("Assignments", assignmentsAttempted),
            new PieChart.Data("Other",100-(quizzesAttempted+assignmentsAttempted))
        );
        progressChart.getData().clear(); // Clear any previous data
        progressChart.getData().addAll();

        // Similar for progress chart, assuming values for demonstration
        progressChart.getData().addAll(
            new PieChart.Data("Completed", 75),
            new PieChart.Data("Remaining", 25)
        );
    }

    @FXML
    private void handleGoBack() {
        try {
            App.setRoot("parentMenu");
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
