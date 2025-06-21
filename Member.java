package assignments.TA01.ta01;

import java.util.Scanner;

/**
 * Member - Child class dari User
 * Member hanya dapat meminjam dan mengembalikan buku
 */
public class Member extends User {
    private final Scanner scanner;
    
    public Member(String name, Scanner scanner) {
        super(name, "MEM" + System.currentTimeMillis() % 1000, "Member");
        this.scanner = scanner;
    }
    
    /**
     * Implementation polymorphic method untuk Member
     * Member hanya dapat meminjam dan mengembalikan buku
     */
    @Override
    public void interact(LibrarySystem library) {
        while (true) {
            System.out.println("\n=== MEMBER MENU ===");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Search Book");
            System.out.println("4. Display Available Books");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    borrowBook(library);
                    break;
                case 2:
                    returnBook(library);
                    break;
                case 3:
                    searchBook(library);
                    break;
                case 4:
                    library.displayAvailableBooks();
                    break;
                case 5:
                    System.out.println("Member session ended.");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
    
    /**
     * BorrowBook - Method untuk meminjam buku
     * @param library - LibrarySystem yang akan digunakan
     */
    private void borrowBook(LibrarySystem library) {
        System.out.println("\n=== AVAILABLE BOOKS FOR BORROWING ===");
        library.displayAvailableBooksForBorrowing();
        
        System.out.print("Enter book number to borrow (0 to cancel): ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine();
        
        if (bookNumber == 0) {
            System.out.println("Borrow book operation cancelled");
            return;
        }
        
        if (library.borrowBookByNumber(bookNumber, this.userId)) {
            System.out.println("Book borrowed successfully!");
            System.out.println("Borrowed by: " + this.name + " (ID: " + this.userId + ")");
        } else {
            System.out.println("Invalid book number or book not available!");
        }
    }
    
    /**
     * ReturnBook - Method untuk mengembalikan buku
     * @param library - LibrarySystem yang akan digunakan
     */
    private void returnBook(LibrarySystem library) {
        System.out.println("\n=== YOUR BORROWED BOOKS ===");
        library.displayBorrowedBooksForReturning(this.userId);
        
        System.out.print("Enter book number to return (0 to cancel): ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine();
        
        if (bookNumber == 0) {
            System.out.println("Return book operation cancelled");
            return;
        }
        
        if (library.returnBookByNumber(bookNumber, this.userId)) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Invalid book number or book not borrowed by you!");
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