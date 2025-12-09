package student;

public class GroupMessage {
    private String sender;
    private String message;
    private String timestamp;

    // Constructor, getters, and setters
    public GroupMessage(String sender, String message, String timestamp) {
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
    
}
