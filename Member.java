import java.util.ArrayList;
import java.util.List;

public class Member {
    int memberId;
    String name;
    String email;
    List<Integer> issuedBooks = new ArrayList<>();

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public void displayMemberDetails() {
        System.out.println("Member ID: " + memberId + ", Name: " + name + ", Email: " + email);
        System.out.println("Issued Books: " + issuedBooks);
    }

    public void addIssuedBook(int bookId) {
        issuedBooks.add(bookId);
    }

    public void returnIssuedBook(int bookId) {
        issuedBooks.remove(Integer.valueOf(bookId));
    }
}
