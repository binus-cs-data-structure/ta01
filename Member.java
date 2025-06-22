import java.util.Scanner;

public class Member extends User {
    private final Scanner scanner;
    private static String idMember = "MEM" + System.currentTimeMillis() % 1000;

    public Member(String name, Scanner scanner) {
        super(name, idMember, "Member");
        this.scanner = scanner;
    }

    @Override
    public void interact(LibrarySystem library) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> searchBook(library);
                case 2 -> borrowBook(library);
                case 3 -> returnBook(library);
                case 4 -> library.displayAllBooks();
                case 5 -> library.displayAvailableBooks();
                case 6 -> library.displayBorrowedBooks();
                case 7 -> {
                    System.out.println("Member session ended.");
                    running = false;
                }
                default -> System.out.println("Invalid option! Please choose from 1-7.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== MEMBER MENU ===");
        System.out.println("1. Search Book");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. Display All Books");
        System.out.println("5. Display Available Books");
        System.out.println("6. Display Borrowed Books");
        System.out.println("7. Exit");
        System.out.print("Choose option: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void borrowBook(LibrarySystem library) {    
        library.displayAllBooks();
        System.out.print("Choose option: ");
        int choice = getUserChoice();

        boolean success = library.borrowBookByNumber(choice, Member.idMember);  // versi terbaru tanpa duplikat
        if (success) {
            System.out.println("Book borrowed successfully");
        }
    }

    private void returnBook(LibrarySystem library) {    
        library.displayAllBooks();
        System.out.print("Choose option: ");
        int choice = getUserChoice();

        boolean success = library.returnBookByNumber(choice, Member.idMember);  // versi terbaru tanpa duplikat
        if (success) {
            System.out.println("Book return successfully");
        } else {
            System.out.println("Book return failed");
        }
    }

    private void searchBook(LibrarySystem library) {
        System.out.print("Enter search pattern (title): ");
        String pattern = scanner.nextLine().trim();
        if (pattern.isEmpty()) {
            System.out.println("Search cancelled. Empty pattern.");
            return;
        }
        library.searchBooksByTitle(pattern); 
    }
}
