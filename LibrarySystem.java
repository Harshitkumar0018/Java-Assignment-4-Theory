import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        LibraryManager lm = new LibraryManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- City Library Digital Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter Choice: ");

            int ch = sc.nextInt();
            switch (ch) {
                case 1 -> lm.addBook();
                case 2 -> lm.addMember();
                case 3 -> lm.issueBook();
                case 4 -> lm.returnBook();
                case 5 -> lm.searchBooks();
                case 6 -> lm.sortBooks();
                case 7 -> { lm.saveToFile(); System.exit(0); }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
