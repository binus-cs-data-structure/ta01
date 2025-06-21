/**
 * User - Parent class untuk sistem perpustakaan ini
 * Basic implementasi ataupun abstrak untuk Admin dan Member harus di define disini
 */
public abstract class User {
    protected String userId;
    protected String name;
    protected String userType;
    
    public User(String name, String userId, String userType) {
        this.name = name;
        this.userId = userId;
        this.userType = userType;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Interact - Abstract method untuk polymorphism - setiap child class harus implement
     * @param library - LibrarySystem yang akan digunakan
     */
    public abstract void interact(LibrarySystem library);
    
    /**
     * DisplayInfo - Method untuk menampilkan info user
     */
    public void displayInfo() {
        System.out.println("Welcome, " + name + " (" + userType + ")");
        System.out.println("User ID: " + userId);
    }
} 