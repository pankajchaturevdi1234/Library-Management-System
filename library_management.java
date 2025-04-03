import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Book {
    String title;
    String author;
    int availableCopies;

    public Book(String title, String author, int availableCopies) {
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}

public class LibraryManagementSystem {
    private static HashMap<String, String> users = new HashMap<>();
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Initialize pre-added users and books
    static {
        users.put("pankajchaturvedi", "password1");
        users.put("sachinsharma", "password2");
        users.put("navneetyadav", "password3");
        users.put("sagardixit", "password4");

        books.add(new Book("Think and Grow Rich", "Napoleon Hill", 5));
        books.add(new Book("Rich Dad Poor Dad", "Robert Kiyosaki", 3));
        books.add(new Book("The Intelligent Investor", "Benjamin Graham", 2));
        books.add(new Book("The Power of Positive Thinking", "Norman Vincent Peale", 4));
        books.add(new Book("Atomic Habits", "James Clear", 1));
    }

    // Function to check if a user is valid
    private static boolean isValidUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    // Function to list available books
    private static void listBooks() {
        System.out.println("\nAvailable Books:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf("%d. %s by %s (%d available)\n", 
                            i + 1, book.title, book.author, book.availableCopies);
        }
    }

    // Function to borrow a book
    private static void borrowBook() {
        listBooks();
        try {
            System.out.print("Enter the number of the book you want to borrow: ");
            int bookIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (bookIndex >= 0 && bookIndex < books.size()) {
                Book book = books.get(bookIndex);
                if (book.availableCopies > 0) {
                    book.availableCopies--;
                    System.out.printf("You have successfully borrowed %s.\n", book.title);
                } else {
                    System.out.println("Sorry, this book is currently unavailable.");
                }
            } else {
                System.out.println("Invalid book choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    // Function to add a new book
    private static void addBook() {
        System.out.print("Enter the title of the new book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author of the new book: ");
        String author = scanner.nextLine();
        try {
            System.out.print("Enter the number of copies available: ");
            int availableCopies = Integer.parseInt(scanner.nextLine());
            Book newBook = new Book(title, author, availableCopies);
            books.add(newBook);
            System.out.println("The book has been successfully added to the library.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number for copies.");
        }
    }

    // Function to add a new user
    private static void addUser() {
        System.out.print("Enter the new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter the new password: ");
        String newPassword = scanner.nextLine();
        users.put(newUsername, newPassword);
        System.out.println("The new user has been successfully added.");
    }

    // Main function
    public static void main(String[] args) {
        // User login
        while (true) {
            System.out.println("**********Login to access the library functions**********");
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (isValidUser(username, password)) {
                System.out.printf("-------- Login successful! Welcome, %s! --------\n", username);
                break;
            } else {
                System.out.println("Login failed. Please check your username and password.");
            }
        }

        // Menu for library management
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. List Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Add a Book");
            System.out.println("4. Add a User");
            System.out.println("5. Exit");

            try {
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        listBooks();
                        break;
                    case 2:
                        borrowBook();
                        break;
                    case 3:
                        addBook();
                        break;
                    case 4:
                        addUser();
                        break;
                    case 5:
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
