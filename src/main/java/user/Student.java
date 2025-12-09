package user;

import java.util.ArrayList;
import java.util.List;

import assessments.Assignments;
import assessments.Quiz;
import courses.Course;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import sda.App;



public class Student extends User {
   
    
    private String enrollmentNumber;

    private List<Course> courses;

    private List<List<Float>> quizMarks;
    private List<List<Float>> assignmentMarks;
    
    // Constructor
    public Student(){};
    public Student(int id, String name, String username) {
        super(id, name, username);
        this.courses = App.courses;
        this.quizMarks = new ArrayList<>(); // Initialize quizMarks
        for (Course course : this.courses) {
            this.quizMarks.add(new ArrayList<>()); // Initialize each course's marks
        }
        this.assignmentMarks = new ArrayList<>(); // Initialize quizMarks
        for (Course course : this.courses) {
            this.assignmentMarks.add(new ArrayList<>()); // Initialize each course's marks
        }
    }
    

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    @Override
    public void showDashboard() {
        System.out.println("Welcome Student! This is your dashboard.");
    }

    public List<Course> getCourses() {
		return courses;
	}

    public void setCourses(List<Course> C)
    {
        if (C != null) {
            this.courses = C;
        } else {
            this.courses = new ArrayList<>(); // Initialize with an empty list if null
        }

    }

    private void ensureMarksListsSize() {
        // Initialize the lists if they are null
        if (quizMarks == null) {
            quizMarks = new ArrayList<>();
        }
        if (assignmentMarks == null) {
            assignmentMarks = new ArrayList<>();
        }
        // Ensure each list has an entry per course
        while (quizMarks.size() < courses.size()) {
            quizMarks.add(new ArrayList<>());
        }
        while (assignmentMarks.size() < courses.size()) {
            assignmentMarks.add(new ArrayList<>());
        }
    }
    

    public void attemptedAssignment(Course selectedCourse, Assignments selectedAssignment, float marksObtained) {
	    	
    	// Evaluated Assignment
	    	
		int courseIndex = courses.indexOf(selectedCourse);
        if (courseIndex == -1) {
            System.out.println("Course not found.");
            return;
        }

        // Ensure the quizMarks row is properly sized
        List<Float> courseMarks = assignmentMarks.get(courseIndex);
        int assignmentIndex = selectedCourse.getAssignments().indexOf(selectedAssignment);

        if (assignmentIndex == -1) {
            System.out.println("Error: Assignment not found in the course.");
            return;
        }

        // Expand the list if needed to accommodate the quiz index
        while (courseMarks.size() <= assignmentIndex) {
            courseMarks.add(-1.0f); // Default to -1.0f for unattempted quizzes
        }

        // Update the marks for the quiz
        courseMarks.set(assignmentIndex, marksObtained);
        System.out.println("Assignment marks updated for course: " + selectedCourse.getCourseName() + 
                           ", Assignment: " + selectedAssignment.getAssessmentName() + 
                           ", Marks: " + marksObtained);
		
    }

    public void attemptedQuiz(Course selectedCourse, Quiz selectedQuiz, float marksObtained) {
        ensureMarksListsSize();
        int courseIndex = courses.indexOf(selectedCourse);
        if (courseIndex == -1) {
            System.out.println("Course not found.");
            return;
        }
        List<Float> courseMarks = quizMarks.get(courseIndex);
        int quizIndex = selectedCourse.getQuizzes().indexOf(selectedQuiz);
        if (quizIndex == -1) {
            System.out.println("Error: Quiz not found in the course.");
            return;
        }
        while (courseMarks.size() <= quizIndex) {
            courseMarks.add(-1.0f);
        }
        courseMarks.set(quizIndex, marksObtained);
        System.out.println("Quiz marks updated for " + selectedCourse.getCourseName() + ": Quiz " 
            + (quizIndex + 1) + ", Marks: " + marksObtained);
    }
    

    public void printAssignmentMarks() {
        System.out.println("Assignment Marks:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("Course: " + courses.get(i).getCourseName());
            List<Float> marks = assignmentMarks.get(i);
            for (int j = 0; j < marks.size(); j++) {
                System.out.println("  Assignment " + (j + 1) + ": " + (marks.get(j) == -1.0f ? "Not Attempted" : marks.get(j)));
            }
        }
    }

    public List<List<Float>> getQuizMarks(){
    	return this.quizMarks;
    }
    
    public void printQuizMarks() {
        System.out.println("Quiz Marks:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("Course: " + courses.get(i).getCourseName());
            List<Float> marks = quizMarks.get(i);
            for (int j = 0; j < marks.size(); j++) {
                System.out.println("  Quiz " + (j + 1) + ": " + (marks.get(j) == -1.0f ? "Not Attempted" : marks.get(j)));
            }
        }
    }
	

    
    
}
