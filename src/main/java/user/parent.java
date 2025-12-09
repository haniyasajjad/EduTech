package user;

import java.util.ArrayList;



public class parent extends User {
    private String childId;

    // Constructor
    public parent(int id, String name, String username) {
        super(id, name, username);
        
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    @Override
    public void showDashboard() {
        System.out.println("Welcome Parent! This is your dashboard.");
    }

    
}
