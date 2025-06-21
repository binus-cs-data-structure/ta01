import java.util.Scanner;

public class Admin extends User {
    private final Scanner scanner;

    public Admin(String name, Scanner scanner) {
        super(name, "ADM" + System.currentTimeMillis() % 1000, "Admin");
        this.scanner = scanner;
    }

    @Override
    public void interact(LibrarySystem library) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> addBook(library);
                case 2 -> removeBook(library);
                case 3 -> searchBook(library);
                case 4 -> library.displayAllBooks();
                case 5 -> library.displayAvailableBooks();
                case 6 -> library.displayBorrowedBooks();
                case 7 -> {
                    System.out.println("Admin session ended.");
                    running = false;
                }
                default -> System.out.println("Invalid option! Please choose from 1-7.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== ADMIN MENU ===");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Search Book");
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

    private void addBook(LibrarySystem library) {
        System.out.print("Enter book title (0 to cancel): ");
        String title = scanner.nextLine().trim();
        if (title.equals("0") || title.isEmpty()) {
            System.out.println("Add book operation cancelled.");
            return;
        }

        System.out.print("Enter book author (0 to cancel): ");
        String author = scanner.nextLine().trim();
        if (author.equals("0") || author.isEmpty()) {
            System.out.println("Add book operation cancelled.");
            return;
        }

        Book newBook = new Book(title, author);
        boolean success = library.addBookAlt(newBook);  // versi terbaru tanpa duplikat
        if (success) {
            System.out.println("Book added successfully: \"" + title + "\" by " + author);
        }
    }

   private void removeBook(LibrarySystem library) {
    System.out.println("\n=== 🗑 AVAILABLE BOOKS ===");
    library.displayAvailableBooks(); // Menampilkan semua buku available

    System.out.print("Enter exact book title to remove (0 to cancel): ");
    String title = scanner.nextLine().trim();
    if (title.equals("0") || title.isEmpty()) {
        System.out.println("Remove book operation cancelled.");
        return;
    }

    System.out.print("Are you sure you want to remove \"" + title + "\"? (y/n): ");
    String confirm = scanner.nextLine().trim().toLowerCase();
    if (!confirm.equals("y")) {
        System.out.println("Operation cancelled.");
        return;
    }

    boolean success = library.removeBookByTitle(title);
    if (success) {
        System.out.println("Book \"" + title + "\" removed successfully.");
    } else {
        System.out.println("Book not found or not available.");
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
