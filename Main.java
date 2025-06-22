
import java.util.Scanner;


/**
 * Main - Class utama untuk menjalankan program
 */
public class Main {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        initializeSampleBooks(library);

        // Hanya 1 scanner supaya input nya bisa tersinkronikasi dan tidak saling mendahului
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== WELCOME TO LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Member");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    loginAsAdmin(library, scanner);
                    break;
                case 2:
                    loginAsMember(library, scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using Library Management System ❣️");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
    
    /**
     * Menginisialisasi buku-buku sample untuk demo
     */
    private static void initializeSampleBooks(LibrarySystem library) {
        library.addBook(new Book("Java: The Complete Reference", "Herbert Schildt"));
        library.addBook(new Book("Data Structures and Algorithms in Java", "Michael Goodrich"));
        library.addBook(new Book("Clean Code", "Robert Martin"));
        library.addBook(new Book("Design Patterns", "Gang of Four"));
        library.addBook(new Book("Effective Java", "Joshua Bloch"));
        library.addBook(new Book("Spring Boot in Action", "Craig Walls"));
        library.addBook(new Book("Head First Java", "Kathy Sierra"));
        
        System.out.println("Sample books initialized successfully!");
    }
    
    /**
     * Login sebagai Admin
     */
    private static void loginAsAdmin(LibrarySystem library, Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        User user = new Admin(name, scanner);
        
        System.out.println("\n=== ADMIN LOGIN SUCCESSFUL ===");
        user.displayInfo();
        
        // Polymorphic method call - akan menjalankan Admin.interact()
        user.interact(library);
    }
    
    /**
     * Login sebagai Member
     */
        private static void loginAsMember(LibrarySystem library, Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        User user = new Member(name, scanner);
        
        System.out.println("\n=== MEMBER LOGIN SUCCESSFUL ===");
        user.displayInfo();
        
        // Polymorphic method call - akan menjalankan Admin.interact()
        user.interact(library);
    }
} 