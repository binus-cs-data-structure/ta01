package assignments.TA01.ta01;

/**
 * Book - Class untuk merepresentasikan buku dalam perpustakaan
 */
public class Book {
    private String title;
    private String author;
    private boolean isAvailable;
    /**
     * UserID - Track who borrowed the book
     */
    private String borrowerId;
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.borrowerId = null;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public String getBorrowerId() {
        return borrowerId;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
    
    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }
    
    /**
     * BorrowBook - Method untuk meminjam buku dengan user ID
     * @param userId - UserID dari peminjam
     * @return true jika berhasil, false jika buku tidak tersedia
     */
    public boolean borrowBook(String userId) {
        if (isAvailable) {
            isAvailable = false;
            borrowerId = userId;
            return true;
        }
        return false;
    }
    
    /**
     * ReturnBook - Method untuk mengembalikan buku dengan user ID verification
     * @param userId - UserID dari peminjam
     * @return true jika berhasil, false jika buku tidak tersedia atau buku tidak dipinjam oleh user
     */
    public boolean returnBook(String userId) {
        if (!isAvailable && borrowerId != null && borrowerId.equals(userId)) {
            isAvailable = true;
            borrowerId = null;
            return true;
        }
        return false;
    }
    
    /**
     * DisplayInfo - Method untuk menampilkan informasi buku
     */
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " + (isAvailable ? "Available" : "Borrowed by: " + borrowerId));
        System.out.println("------------------------");
    }
    
    /**
     * DisplayRemovalInfo - Method untuk menampilkan info dalam format one-line untuk removal
     * @param number - Nomor urut buku
     */
    public void displayRemovalInfo(int number) {
        System.out.println("[" + number + "] " + title + " - " + author);
    }
} 