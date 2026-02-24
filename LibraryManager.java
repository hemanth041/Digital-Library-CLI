import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
class LibraryManager{
    private ArrayList<Book> books=new ArrayList<>();
    private Scanner sc=new Scanner(System.in);
    private static final String DATA_FILE = "library_data.txt";
    public static void main(String[] args){
        LibraryManager manager=new LibraryManager();
        manager.run();

    }
    public void run(){
        loadFromFile();
        while(true){
            System.out.println("\n=== Digital Library Menu ===");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    saveToFile();
                    System.out.println("GoodBye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    private void saveToFile(){
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Book book : books) {
                writer.write(book.toFileString());
                writer.newLine(); // Add a new line after each book
            }
            System.out.println("✓ Saved " + books.size() + " books to file.");
        } catch (IOException e) {
            System.out.println("❌ Error saving to file: " + e.getMessage());
        }
    }
    private void loadFromFile() {
        File file = new File(DATA_FILE);
        
        // Check if file exists (first time running, it won't)
        if (!file.exists()) {
            System.out.println("No existing library data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) continue;
                
                try {
                    Book book = new Book(line); // Use our new constructor
                    books.add(book);
                    count++;
                } catch (Exception e) {
                    System.out.println("⚠ Skipping corrupted line: " + line);
                }
            }
            
            System.out.println("✓ Loaded " + count + " books from previous session.");
            
        } catch (IOException e) {
            System.out.println("❌ Error loading from file: " + e.getMessage());
        }
    }
      private void addBook() {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Enter Year: ");
        int year = sc.nextInt();
        sc.nextLine(); // consume newline

        Book newBook = new Book(title, author, isbn, year);
        books.add(newBook);
        System.out.println("Book added successfully!");
    }
    private void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("The library has no books.");
            return;
        }
        System.out.println("\n--- Library Catalog ---");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i).toString());
        }
    }
    private void borrowBook(){
    System.out.println("Enter the ISBN of the Book to be borrowed:");
    String isbn=sc.nextLine();
    for(Book book:books){
        if(book.getisbn().equals(isbn)){
            if(book.isAvailable()){
                book.setAvailable(false);
                System.out.println("You Borrowed:"+book.getTitle());
            }else{
                System.out.println("Sorry. The has been already Borrowed");
            }
            return;
        }
    }
    System.out.println("Book not Found...!");
}
private void returnBook() {
        // Similar logic to borrowBook, but setting availability to true.
        System.out.print("Enter the ISBN of the book to return: ");
        String isbn = sc.nextLine();

        for (Book book : books) {
            if (book.getisbn().equals(isbn)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("Book returned: " + book.getTitle());
                } else {
                    System.out.println("This book wasn't borrowed.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
}