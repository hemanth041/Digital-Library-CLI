import java.util.ArrayList;
import java.util.Scanner;
class LibraryManager{
    private ArrayList<Book> books=new ArrayList<>();
    private Scanner sc=new Scanner(System.in);
    public static void main(String[] args){
        LibraryManager manager=new LibraryManager();
        manager.run();
    }
    public void run(){
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
                    System.out.println("GoodBye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
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