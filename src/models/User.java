public abstract class User{
    private int userId;
    private String name;
    private String email;
    private String phone;

    public User(int userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void login() {
        // Login logic
    }

    public void logout() {
        // Logout logic
    }

    public int getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }
}
