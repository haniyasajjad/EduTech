package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupMessagingDb {

    // Load messages for a given group
    public static List<String> loadMessages(int groupId) {
        List<String> messages = new ArrayList<>();
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT s.username, gm.message, gm.timestamp " +
                     "FROM GroupMessages gm " +
                     "JOIN students s ON gm.sender = s.student_id " + // Correct column name for sender
                     "WHERE gm.groupId = ? " + // Correct column name for groupId
                     "ORDER BY gm.timestamp")) {

            stmt.setInt(1, groupId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String sender = rs.getString("username");
                String messageText = rs.getString("message"); // Correct column name for message
                String timestamp = rs.getString("timestamp");
                messages.add(sender + " (" + timestamp + "): " + messageText);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    // Send a new message to a group
    public static void sendMessage(int groupId, int studentId, String messageText) {
        if (messageText.isEmpty()) return;
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO GroupMessages (groupId, sender, message) VALUES (?, ?, ?)")) {
    
            // Log the groupId being inserted
            System.out.println("Inserting message for groupId: " + groupId);
            System.out.println("Inserting message for studentId: " + studentId);
    
            stmt.setInt(1, groupId); // This should be 1 if you're using the right group ID
            stmt.setInt(2, studentId);
            stmt.setString(3, messageText);
            stmt.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
