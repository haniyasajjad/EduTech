package user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.scene.chart.PieChart.Data;

public class StudentTrackPerformance {
    @FXML
    private PieChart activityChart;

    @FXML
    private PieChart progressChart;

    private Student student; // Hold the student data

    // Method to set student data
    public void setStudentData(Student student) {
        this.student = student;
        initialize(); // Load performance data for this student
    }

    @FXML
    private void initialize() {
        if (activityChart != null && progressChart != null) {
            if (student != null) {
                // Add data
                activityChart.getData().addAll(
                    new PieChart.Data("Quizzes", student.getId() * 10),
                    new PieChart.Data("Assignments", student.getId() * 5),
                    new PieChart.Data("Other", 50)
                );
    
                progressChart.getData().addAll(
                    new PieChart.Data("Completed", student.getId() * 20),
                    new PieChart.Data("Remaining", 100 - (student.getId() * 20))
                );
            }
        } else {
            System.out.println("Charts are not initialized.");
        }
    }
    
}
