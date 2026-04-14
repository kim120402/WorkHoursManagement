public class WorkHourEntry {
    private int id;
    private String name;
    private String role;
    private double hours;  // Change to double for fractional hours

    // Constructor for no id (used for insertion)
    public WorkHourEntry(String name, String role, double hours) {
        this.name = name;
        this.role = role;
        this.hours = hours;
    }

    // Constructor for entries with ID (used for update or reading from DB)
    public WorkHourEntry(int id, String name, String role, double hours) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.hours = hours;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public double getHours() {
        return hours;
    }

    // toString method to display object easily
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Role: " + role + ", Hours: " + hours;
    }
}