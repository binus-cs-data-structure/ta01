package assignments.TA01.ta01;

/**
 * LibrarySystem - Class untuk mengelola sistem perpustakaan
 * Menggunakan array untuk menyimpan data buku
 */
public class LibrarySystem {
    private final Book[] books;
    private int bookCount;
    private final int MAX_BOOKS = 100;
    
    public LibrarySystem() {
        books = new Book[MAX_BOOKS];
        bookCount = 0;
    }
    
    /**
     * Menambahkan buku baru ke dalam perpustakaan
     * @param book Buku yang akan ditambahkan
     * @return true jika berhasil, false jika gagal
     */
    public boolean addBook(Book book) {
        if (bookCount < MAX_BOOKS) {
            books[bookCount] = book;
            bookCount++;
            return true;
        }
        System.out.println("Library is full! Cannot add more books");
        return false;
    }
    
    /**
     * Menghapus buku dari perpustakaan berdasarkan nomor urut (hanya yang available aja)
     * @param bookNumber Nomor urut buku yang akan dihapus
     * @return true jika berhasil, false jika tidak ditemukan atau tidak tersedia
     */
    public boolean removeBookByNumber(int bookNumber) {
        int availableCount = 0;
        int targetIndex = -1;
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                availableCount++;
                if (availableCount == bookNumber) {
                    targetIndex = i;
                    break;
                }
            }
        }

        // Jika buku tidak ditemukan
        if (targetIndex == -1) {
            return false;
        }
        
        // Memindahkan elemen dari kanan ke sebelah kiri
        // supaya elemen yang dihapus dari array itu tidak kosong
        for (int j = targetIndex; j < bookCount - 1; j++) {
            books[j] = books[j + 1];
        }
        // Elemen terakhir dari array di buat null sehingga lebih clean
        books[bookCount - 1] = null;
        bookCount--; // Mengurangi jumlah buku yang tersedia
        return true;
    }
    
    /**
     * Mencari buku berdasarkan judul (case insensitive)
     * @param pattern Pattern untuk mencari judul buku
     */
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
    
    /**
     * Menampilkan semua buku dalam perpustakaan
     */
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
    
    /**
     * Menampilkan semua buku yang tersedia (tidak dipinjam)
     */
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
    
    /**
     * Menampilkan buku tersedia sebelum dihapus
     */
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
    
    /**
     * Menampilkan semua buku yang sedang dipinjam
     */
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
    
    /**
     * Menampilkan buku yang dipinjam oleh user
     * @param userId ID user yang ingin dicek
     */
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
    
    /**
     * Menampilkan buku yang tersedia untuk di pinjam
     */
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
    
    /**
     * Menampilkan buku yang dipinjam user untuk dikembalikan
     * @param userId ID user yang ingin dicek
     */
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
    
    /**
     * Meminjam buku berdasarkan nomor urut available books (untuk Member)
     * @param bookNumber Nomor urut buku yang akan dipinjam
     * @param userId ID user yang meminjam
     * @return true jika berhasil, false jika tidak tersedia atau tidak ditemukan
     */
    public boolean borrowBookByNumber(int bookNumber, String userId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable() && i == bookNumber) {
                return books[i].borrowBook(userId);
            }
        }
        return false;
    }
    
    /**
     * Mengembalikan buku berdasarkan nomor urut borrowed books (untuk Member)
     * @param bookNumber Nomor urut buku yang akan dikembalikan
     * @param userId ID user yang mengembalikan
     * @return true jika berhasil, false jika tidak ditemukan atau tidak dipinjam oleh user
     */
    public boolean returnBookByNumber(int bookNumber, String userId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && userId.equals(books[i].getBorrowerId()) && i == bookNumber) {
                return books[i].returnBook(userId);
            }
        }
        return false;
    }
    
    /**
     * Mendapatkan jumlah total buku dalam perpustakaan
     * @return jumlah buku
     */
    public int getTotalBooks() {
        return bookCount;
    }
    
    /**
     * Mendapatkan jumlah buku yang tersedia
     * @return jumlah buku tersedia
     */
    public int getAvailableBooks() {
        int count = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Mendapatkan jumlah buku yang dipinjam
     * @return jumlah buku dipinjam
     */
    public int getBorrowedBooks() {
        return bookCount - getAvailableBooks();
    }
} 