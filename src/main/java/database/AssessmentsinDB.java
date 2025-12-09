package database;
import java.sql.*;
import java.util.ArrayList;

import assessments.Question;

public class AssessmentsinDB {

    public static boolean saveQuiz(String quizName, int maxScore, ArrayList<Question> questions) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.connect();
            String sql = "INSERT INTO quizzes (assessmentName, maxScore) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, quizName);
            stmt.setInt(2, maxScore);
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int assessmentCode = rs.getInt(1);  // Get the generated assessmentCode

                for (Question question : questions) {
                    boolean result = addQuestionQuiz(assessmentCode, question);
                    if (!result) {
                        return false;  // If adding any question fails, return false
                    }
                }
            }
            return true;  // All operations succeeded
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, stmt, rs);
        }
    }
    private static boolean addQuestionQuiz(int assessmentCode, Question question) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.connect();
            String sql = "INSERT INTO quiz_questions (assessmentCode, topic, questionText, answer) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, assessmentCode);
            stmt.setString(2, question.getTopic());
            stmt.setString(3, question.getQuestion());
            stmt.setString(4, question.getAnswer());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public static boolean saveAssignment(String assignmentName, int maxScore, ArrayList<Question> questions) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            conn = DatabaseConnector.connect();
            String sql = "INSERT INTO assignments (assessmentName, maxScore) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, assignmentName);
            stmt.setInt(2, maxScore);
            stmt.executeUpdate();
    
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int assessmentCode = rs.getInt(1);  // Retrieve the generated assessmentCode
    
                for (Question question : questions) {
                    boolean result = addQuestionAssignment(assessmentCode, question);
                    if (!result) {
                        return false;  // Return false if any question fails to insert
                    }
                }
            }
            return true;  // Assignment and questions inserted successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, stmt, rs);
        }
    }

    private static boolean addQuestionAssignment(int assessmentCode, Question question) {
        Connection conn = null;
        PreparedStatement stmt = null;
    
        try {
            conn = DatabaseConnector.connect();
            String sql = "INSERT INTO assignment_questions (assessmentCode, topic, questionText, answer, marks) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, assessmentCode);
            stmt.setString(2, question.getTopic());
            stmt.setString(3, question.getQuestion());
            stmt.setString(4, question.getAnswer());
            stmt.setInt(5, question.getMarks());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }
    
    

    private static void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    

