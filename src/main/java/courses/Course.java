package courses;

import courseResources.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

import assessments.Assignments;
import assessments.Exercise;
import assessments.Quiz;

public class Course {
    private int courseId;
    private StringProperty courseName;
    private StringProperty courseCode;
    private List<Resources> resources; // Books, Videos, Summary Notes
    private List<Exercise> exercises; 
    private List<Assignments> assignments; 
    private List<Quiz> quiz;

    // Constructor
    public Course(int courseId, String courseName, String courseCode) {
        this.courseId = courseId;
        this.courseName = new SimpleStringProperty(courseName);
        this.courseCode = new SimpleStringProperty(courseCode);
        this.resources = new ArrayList<>();
        this.exercises = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.quiz = new ArrayList<>();
    }

    // JavaFX Bindings
    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName.get();
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode.get();
    }

    public StringProperty courseCodeProperty() {
        return courseCode;
    }

    // Resources Management
    public List<Resources> getResources() {
        return resources;
    }

    public void addBook(String title, String author, String courseName, String bookUrl) {
        Book book = new Book(title, author, courseName, bookUrl);
        resources.add(book);
    }

    public void addVideo(String title, String videoDescription, String courseName, String videoUrl) {
        Videos video = new Videos(title, videoDescription, courseName, videoUrl, videoDescription);
        resources.add(video);
    }

    public void addSummaryNotes(String title, String content, String courseName) {
        SummaryNotes summary = new SummaryNotes(title, content, courseName, content);
        resources.add(summary);
    }

    public boolean hasResource(String title) {
        return resources.stream().anyMatch(resource -> resource.getTitle().equalsIgnoreCase(title));
    }

    // Assessments Management
    public void addAssignment(Assignments assignment) {
        assignments.add(assignment);
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void addQuiz(Quiz quiz) {
        this.quiz.add(quiz);
    }

    public List<Assignments> getAssignments() {
        return assignments;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public List<Quiz> getQuizzes() {
        return quiz;
    }

    // Debugging Methods
    public void printExercises() {
        exercises.forEach(Exercise::printQuestions);
    }

    public void printAssignments() {
        assignments.forEach(Assignments::printQuestions);
    }

    public void printQuizzes() {
        quiz.forEach(Quiz::printQuestions);
    }

    @Override
    public String toString() {
        return getCourseName() + " (" + getCourseCode() + ")";
    }

    public int getNumberQuizzes() {

    	return quiz.size();
}

    

public int getNumberAssignments() {

    	return assignments.size();     

}
}
