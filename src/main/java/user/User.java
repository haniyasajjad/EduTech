package user;

public abstract class User {
    private String password;
    private String username;
    private int id;

    // Constructor
    public User(){};
    public User(int id,String name, String username) {
        this.id = id;
        this.password = name;
        this.username = username;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.password = name;
    }

    public String getUsername() {
        return username;
    }

   

    public void setUsername(String username) {
        this.username = username;
    }

    // Abstract method for role-specific behavior
    public abstract void showDashboard();
}
