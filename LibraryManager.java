import java.io.*;
import java.util.*;

public class LibraryManager {
    Map<Integer, Book> books = new HashMap<>();
    Map<Integer, Member> members = new HashMap<>();

    final String BOOK_FILE = "books.txt";
    final String MEMBER_FILE = "members.txt";

    public LibraryManager() { loadFromFile(); }

    public void addBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        books.put(id, new Book(id, title, author, category, false));
        saveToFile();
        System.out.println("Book Added Successfully.");
    }

    public void addMember() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        members.put(id, new Member(id, name, email));
        saveToFile();
        System.out.println("Member Added Successfully.");
    }

    public void issueBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();

        if (!books.containsKey(bookId) || !members.containsKey(memberId)) {
            System.out.println("Invalid IDs.");
            return;
        }

        Book b = books.get(bookId);
        if (b.isIssued) {
            System.out.println("Book Already Issued.");
            return;
        }

        b.markAsIssued();
        members.get(memberId).addIssuedBook(bookId);
        saveToFile();
        System.out.println("Book Issued Successfully.");
    }

    public void returnBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();

        if (!books.containsKey(bookId) || !members.containsKey(memberId)) {
            System.out.println("Invalid IDs.");
            return;
        }

        books.get(bookId).markAsReturned();
        members.get(memberId).returnIssuedBook(bookId);
        saveToFile();
        System.out.println("Book Returned Successfully.");
    }

    public void searchBooks() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Search by Title/Author/Category: ");
        String key = sc.nextLine().toLowerCase();

        books.values().stream()
            .filter(b -> b.title.toLowerCase().contains(key) ||
                         b.author.toLowerCase().contains(key) ||
                         b.category.toLowerCase().contains(key))
            .forEach(Book::displayBookDetails);
    }

    public void sortBooks() {
        List<Book> list = new ArrayList<>(books.values());
        Collections.sort(list);
        list.forEach(Book::displayBookDetails);
    }

    public void loadFromFile() {
        try {
            new File(BOOK_FILE).createNewFile();
            new File(MEMBER_FILE).createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(BOOK_FILE));
            String line;
            while ((line = br.readLine()) != null) {
                String[] a = line.split(",");
                books.put(Integer.parseInt(a[0]),
                        new Book(Integer.parseInt(a[0]), a[1], a[2], a[3], Boolean.parseBoolean(a[4])));
            }
            br.close();

            br = new BufferedReader(new FileReader(MEMBER_FILE));
            while ((line = br.readLine()) != null) {
                String[] a = line.split(",");
                Member m = new Member(Integer.parseInt(a[0]), a[1], a[2]);
                if (a.length == 4 && !a[3].equals("")) {
                    for (String x : a[3].split("-")) m.issuedBooks.add(Integer.parseInt(x));
                }
                members.put(m.memberId, m);
            }
            br.close();

        } catch (Exception e) {}
    }

    public void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(BOOK_FILE));
            for (Book b : books.values())
                bw.write(b.bookId + "," + b.title + "," + b.author + "," + b.category + "," + b.isIssued + "\n");
            bw.close();

            bw = new BufferedWriter(new FileWriter(MEMBER_FILE));
            for (Member m : members.values()) {
                String issued = "";
                for (int id : m.issuedBooks) issued += id + "-";
                bw.write(m.memberId + "," + m.name + "," + m.email + "," + issued + "\n");
            }
            bw.close();
        } catch (Exception e) {}
    }
}
