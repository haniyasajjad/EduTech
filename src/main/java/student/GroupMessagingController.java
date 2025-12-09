package student;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import java.sql.*;
import java.util.List;

import database.GroupMessagingDb;
import sda.App;

public class GroupMessagingController {

    @FXML
    private VBox messageContainer;
    @FXML
    private TextField messageInput;

    private int groupId;
    private int studentId;

    public void setGroupAndStudent(int groupId, int studentId) {
        this.groupId = groupId;
        this.studentId = studentId;
        System.out.println(this.groupId+" |||| "+this.groupId);
        loadMessages();
    }

   // Load messages from the database and display them in the message container
   private void loadMessages() {
    messageContainer.getChildren().clear();  // Clear existing messages
    List<String> messages = GroupMessagingDb.loadMessages(1); // Get messages for the group

    for (String msg : messages) {
        // Create a new label for each message and add it to the message container
        Label messageLabel = new Label(msg);
        messageContainer.getChildren().add(messageLabel);
    }
}

// Handle sending a new message
@FXML
private void sendMessage() {
    String messageText = messageInput.getText().trim();
    if (!messageText.isEmpty()) {
        loadMessages(); 
        GroupMessagingDb.sendMessage(1, 1, messageText);  // Save message to database
        messageInput.clear();  // Clear the message input field
        loadMessages();  // Reload messages to show the newly sent message
    }
}


    @FXML
    private void goBack() {
        try {
            App.setRoot("studentMenu");
        } catch (Exception e) {
            // TODO: handle exception
        }
         // Navigate back to the student menu
    }
}
