package user;

import java.time.LocalDate;
import java.util.ArrayList;

import assessments.Assignments;
import assessments.Exercise;
import assessments.Question;
import assessments.Quiz;
import courses.Course;

import sda.App;

public class Admin extends User {
    // Static instance for singleton
    private static Admin instance;

    private Admin()
    {
    }

    // Constructor
    /*private Admin(int id, String name, String username) {
        super(id, name, username);
    }*/

    @Override
    public void showDashboard() {
        System.out.println("Welcome Admin! This is your dashboard.");
    }

    // Admin-specific methods can be added here
      public void addCourses(ArrayList<Course> c) {
        c.add(new Course(1, "Mathematics", "MATH101"));
        c.add(new Course(2, "Science", "SCI101"));
        c.add(new Course(3, "History", "HIST101"));
        c.add(new Course(4, "English", "ENG101"));
        c.add(new Course(5, "Geography", "GEO101"));
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

	public boolean addExercise(String assessmentName,String courseName,ArrayList<Question>questions)
    {

    	//Course c=new Course(1, courseName, "MATH101");
		int assessmentCode=1;
		
		Course targetCourse = null;
	    for (Course course : App.courses) {
	        if (course.getCourseName().equalsIgnoreCase(courseName)) {
	            targetCourse = course;
	            break;
	        }
	    }

	    if (targetCourse != null) {
	        // Create the exercise and add it to the target course
	        Exercise exercise = new Exercise(assessmentName, assessmentCode, questions);
	        exercise.printDetails();
	        targetCourse.addExercise(exercise);
	        System.out.println("Exercise added to course: " + courseName);
	    } else {
	        // Course not found
	        System.out.println("Course not found: " + courseName);
	        return false;
	    }
		
	    System.out.println();
	    System.out.println();
	    System.out.println();
	    Admin.printAllCourses();
	    return true;
	    
    }

     public boolean addQuiz(String assessmentName, String duration, String courseName, ArrayList<Question>questions)
    {
    	int assessmentCode=1;
		int maxScore=0;
		
		for (Question question : questions) {
            maxScore += question.getMarks();
        }
    	
    	Course targetCourse = null;
	    for (Course course : App.courses) {
	        if (course.getCourseName().equalsIgnoreCase(courseName)) {
	            targetCourse = course;
	            break;
	        }
	    }
    	
	    if (targetCourse != null) {
	        // Create the exercise and add it to the target course
	    	Quiz quiz = new Quiz(assessmentName, assessmentCode,Integer.parseInt(duration),maxScore,questions);
			quiz.printDetails();
	        targetCourse.addQuiz(quiz);
	        System.out.println("Quiz added to course: " + courseName);
	    } else {
	        // Course not found
	        System.out.println("Course not found: " + courseName);
	        return false;
	    }
		
	    System.out.println();
	    System.out.println();
	    System.out.println();
	    Admin.printAllCourses();
	    return true;
	    
    }

    public boolean addAssignment(String assessmentName,String duration,String courseName,ArrayList<Question>questions)
    {
    	
		int assessmentCode=1;
		int maxScore=0;
		
		LocalDate deadline=LocalDate.parse(duration);
		
		for (Question question : questions) {
            maxScore += question.getMarks();
        }
		
		Course targetCourse = null;
	    for (Course course : App.courses) {
	        if (course.getCourseName().equalsIgnoreCase(courseName)) {
	            targetCourse = course;
	            break;
	        }
	    }
    	
	    if (targetCourse != null) {
	        // Create the exercise and add it to the target course
	    	Assignments assignment = new Assignments(assessmentName, assessmentCode,deadline,maxScore,questions);
			assignment.printDetails();
	        targetCourse.addAssignment(assignment);
	        System.out.println("Assignment added to course: " + courseName);
	    } else {
	        // Course not found
	        System.out.println("Course not found: " + courseName);
	        return false;
	    }
	    
	    System.out.println();
	    System.out.println();
	    System.out.println();
	    Admin.printAllCourses();
		return true;
	    
    }
    public static void printAllCourses() {
        System.out.println("Courses and Details:");
        for (Course course : App.courses) {
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Assignments:");
            course.printAssignments();
            System.out.println("Quizzes:");
            course.printQuizzes();
            System.out.println("Exercises:");
            course.printExercises();
            System.out.println("-------------------------------------------------");
        }
    }
}
