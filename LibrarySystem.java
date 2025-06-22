public class LibrarySystem {
    private final Book[] books;
    private int bookCount;
    private final int MAX_BOOKS = 100;

    public LibrarySystem() {
        books = new Book[MAX_BOOKS];
        bookCount = 0;
    }
 public boolean addBook(Book book) {
        if (bookCount < MAX_BOOKS) {
            books[bookCount] = book;
            bookCount++;
            return true;
        }
        System.out.println("Library is full! Cannot add more books");
        return false;
    }
    // Tambah buku serta cek duplikat judul
    public boolean addBookAlt(Book newBook) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(newBook.getTitle())) {
                System.out.println("Book with the same title already exists!");
                return false;
            }
        }

        if (bookCount >= MAX_BOOKS) {
            System.out.println("Cannot add more books. Library is full.");
            return false;
        }

        books[bookCount++] = newBook;
        System.out.println("Book added successfully.");
        return true;
    }

    //Hapus buku: berdasarkan judul
    public boolean removeBookByTitle(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getTitle().equalsIgnoreCase(title) && books[i].isAvailable()) {
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[bookCount - 1] = null;
                bookCount--;
                System.out.println("Book \"" + title + "\" removed successfully.");
                return true;
            }
        }
        System.out.println("Book not found or currently borrowed.");
        return false;
    }

    // Cari buku: berdasarkan judul
    public void searchBooksByTitle(String pattern) {
    System.out.println("\n=== SEARCH RESULTS ===");
    boolean found = false;

        for (Book book : books) {
            if (book != null && book.getTitle().toLowerCase().contains(pattern.toLowerCase())) {
                book.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found for: " + pattern);
        }
    }

    public void displayAllBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("=== ALL BOOKS IN LIBRARY ===");
        for (int i = 0; i < bookCount; i++) {
            System.out.println((i + 1) + ". ");
            books[i].displayInfo();
        }
    }

    public void displayAvailableBooks() {
        System.out.println("=== AVAILABLE BOOKS ===");
        boolean hasAvailableBooks = false;

        for (Book book : books) {
            if (book != null && book.isAvailable()) {
                book.displayInfo();
                hasAvailableBooks = true;
            }
        }

        if (!hasAvailableBooks) {
            System.out.println("No available books at the moment");
        }
    }

    public void displayAvailableBooksForRemoval() {
        int availableCount = 0;

        for (Book book : books) {
            if (book != null && book.isAvailable()) {
                availableCount++;
                book.displayRemovalInfo(availableCount);
            }
        }

        if (availableCount == 0) {
            System.out.println("No available books to remove");
        }
    }

    public void displayBorrowedBooks() {
        System.out.println("=== BORROWED BOOKS ===");
        boolean hasBorrowedBooks = false;

        for (Book book : books) {
            if (book != null && !book.isAvailable()) {
                book.displayInfo();
                hasBorrowedBooks = true;
            }
        }

        if (!hasBorrowedBooks) {
            System.out.println("No borrowed books at the moment");
        }
    }

    public void displayBorrowedBooksByUser(String userId) {
        boolean hasBorrowedBooks = false;

        for (Book book : books) {
            if (book != null && !book.isAvailable() && userId.equals(book.getBorrowerId())) {
                book.displayInfo();
                hasBorrowedBooks = true;
            }
        }

        if (!hasBorrowedBooks) {
            System.out.println("You haven't borrowed any books");
        }
    }

    public void displayAvailableBooksForBorrowing() {
        int availableCount = 0;

        for (Book book : books) {
            if (book != null && book.isAvailable()) {
                availableCount++;
                System.out.println("[" + availableCount + "] " + book.getTitle() + " - " + book.getAuthor());
            }
        }

        if (availableCount == 0) {
            System.out.println("No available books to borrow");
        }
    }

    public void displayBorrowedBooksForReturning(String userId) {
        int borrowedCount = 0;

        for (Book book : books) {
            if (book != null && !book.isAvailable() && userId.equals(book.getBorrowerId())) {
                borrowedCount++;
                System.out.println("[" + borrowedCount + "] " + book.getTitle() + " - " + book.getAuthor());
            }
        }

        if (borrowedCount == 0) {
            System.out.println("You haven't borrowed any books");
        }
    }

    public boolean borrowBookByNumber(int bookNumber, String userId) {
        int availableIndex = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                availableIndex++;
                if (availableIndex == bookNumber) {
                    return books[i].borrowBook(userId);
                }
            }
        }
        return false;
    }

    public boolean returnBookByNumber(int bookNumber, String userId) {
        int borrowedIndex = 0;
        bookNumber = bookNumber - 1;
        // System.out.println("Book Count : " + bookCount);
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && !books[i].isAvailable() && userId.equals(books[i].getBorrowerId())) {
                // System.out.println(">>> Borrowed Index : " + borrowedIndex);
                // System.out.println(">>> Book number : " + bookNumber);
                if (borrowedIndex == bookNumber) {
                    return books[i].returnBook(userId);
                }
            }
            borrowedIndex++;
        }
        return false;
    }

    public int getTotalBooks() {
        return bookCount;
    }

    public int getAvailableBooks() {
        int count = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public int getBorrowedBooks() {
        return bookCount - getAvailableBooks();
    }
}
