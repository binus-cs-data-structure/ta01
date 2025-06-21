package assignments.TA01.ta01;

import java.util.Scanner;

/**
 * Admin - Child class dari User
 * Admin dapat mengelola buku (menambah, menghapus, melihat semua buku)
 */
public class Admin extends User {
    private final Scanner scanner;
    
    public Admin(String name, Scanner scanner) {
        super(name, "ADM" + System.currentTimeMillis() % 1000, "Admin");
        this.scanner = scanner;
    }
    
    /**
     * Implementation polymorphic method untuk Admin
     * Admin memiliki akses penuh untuk mengelola buku
     */
    @Override
    public void interact(LibrarySystem library) {
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Display Available Books");
            System.out.println("6. Display Borrowed Books");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addBook(library);
                    break;
                case 2:
                    removeBook(library);
                    break;
                case 3:
                    searchBook(library);
                    break;
                case 4:
                    library.displayAllBooks();
                    break;
                case 5:
                    library.displayAvailableBooks();
                    break;
                case 6:
                    library.displayBorrowedBooks();
                    break;
                case 7:
                    System.out.println("Admin session ended.");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
    
    /**
     * AddBook - Method untuk menambahkan buku
     * @param library - LibrarySystem yang akan digunakan
     */
    private void addBook(LibrarySystem library) {
        System.out.print("Enter book title (0 to cancel): ");
        String title = scanner.nextLine();
        if (title.equals("0")) {
            System.out.println("Add book operation cancelled");
            return;
        }
        
        System.out.print("Enter book author (0 to cancel): ");
        String author = scanner.nextLine();
        if (author.equals("0")) {
            System.out.println("Add book operation cancelled");
            return;
        }
        
        Book newBook = new Book(title, author);
        library.addBook(newBook);
        System.out.println("Book added successfully!");
    }
    
    /**
     * RemoveBook - Method untuk menghapus buku
     * @param library - LibrarySystem yang akan digunakan
     */
    private void removeBook(LibrarySystem library) {
        System.out.println("\n=== AVAILABLE BOOKS FOR REMOVAL ===");
        library.displayAvailableBooksForRemoval();
        
        System.out.print("Enter book number to remove (0 to cancel): ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine();
        
        if (bookNumber == 0) {
            System.out.println("Remove book operation cancelled");
            return;
        }
        
        if (library.removeBookByNumber(bookNumber)) {
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Invalid book number or book not available!");
        }
    }
    
    /**
     * SearchBook - Method untuk mencari buku
     * @param library - LibrarySystem yang akan digunakan
     */
    private void searchBook(LibrarySystem library) {
        System.out.print("Enter search pattern (title): ");
        String pattern = scanner.nextLine();
        
        library.searchBooksByTitle(pattern);
    }
} 