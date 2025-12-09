package student;

import java.util.ArrayList;

public class GroupChat {
     private String groupName;
    private ArrayList<GroupMessage> messages;

    public GroupChat(String groupName) {
        this.groupName = groupName;
        this.messages = new ArrayList<>();
    }

    public void addMessage(String sender, String message) {
        String timestamp = java.time.LocalDateTime.now().toString();
        GroupMessage newMessage = new GroupMessage(sender, message, timestamp);
        messages.add(newMessage);
    }

    public ArrayList<GroupMessage> getMessages() {
        return messages;
    }
    
}
