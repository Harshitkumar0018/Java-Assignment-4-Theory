public class Book implements Comparable<Book> {
    int bookId;
    String title;
    String author;
    String category;
    boolean isIssued;

    public Book(int bookId, String title, String author, String category, boolean isIssued) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = isIssued;
    }

    public void displayBookDetails() {
        System.out.println("Book ID: " + bookId + ", Title: " + title +
                ", Author: " + author + ", Category: " + category +
                ", Issued: " + (isIssued ? "Yes" : "No"));
    }

    public void markAsIssued() { isIssued = true; }
    public void markAsReturned() { isIssued = false; }

    // Sorting books by title
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }
}
