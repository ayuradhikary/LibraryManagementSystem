package LibraryManagementSystem;

public interface Utilities {
    static void createUser(String currentUser, String Currentpassword, String newUser, String newUserPassword,
            boolean overwrite) {
        try {
            UserManager.createUser(currentUser, Currentpassword, newUser, newUserPassword, false);
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }

    }

    static void addBook(String book, String currentUser) {
        BookManager bm = new BookManager();
        try {
            bm.addBook(book, currentUser);
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void deleteBook(String book, String currentUser) {
        BookManager b = new BookManager();
        try {
            b.deleteBook(book, currentUser);
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void displayBooks() {
        try {
            BookManager b = new BookManager();
            b.loadBooks();
            b.displayBooks();
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void borrowBooks(String currentUser, String book) {
        BookManager bm = new BookManager();
        try {
            bm.borrowBooks(currentUser, book);
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }
    }
}
