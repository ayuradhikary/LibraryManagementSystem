package LibraryManagementSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class UserManager {
    private static final String SYSTEM_USER_ID = "admin";
    private static final String SYSTEM_USER_PASSWORD = "adminpass";
    private static final String Creds_file = "credentials.txt";
    static File file = new File(Creds_file);

    private static boolean authenticateSystemUser(String userID, String password) {
        return SYSTEM_USER_ID.equals(userID) && SYSTEM_USER_PASSWORD.equals(password);
    }

    public static void createUser(String currentUser, String currentUserpassword, String newUser,
            String newUserPassword, boolean overwrite) throws ExceptionHandler {
        if (!authenticateSystemUser(currentUser, currentUserpassword)) {
            throw ExceptionHandler.UnauthorizedAccessException("User does not have sufficient privileges.");
        } else {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, !overwrite))) {
                writer.write(newUser + ":" + newUserPassword);
                writer.newLine();
                System.out.println("User created successfully.");
            } catch (Exception ex) {
                throw ExceptionHandler
                        .resourceNotFoundException("An error occurred while creating the user: " + ex.getMessage());
            }

        }
    }

    public static String validateUser(String userId, String password) throws ExceptionHandler {
        if (userId.equals(SYSTEM_USER_ID) && password.equals(SYSTEM_USER_PASSWORD)) {
            return "admin";
        } else if (userId.equals(SYSTEM_USER_ID) && !password.equals(SYSTEM_USER_PASSWORD)) {
            System.out.println("password for the admin is incorrect");
            return null;
        } else {
            File file = new File("credentials.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] credentials = line.split(":");
                    if (credentials[0].equals(userId) && credentials[1].equals(password)) {
                        if ("admin".equals(userId)) {
                            return "admin"; // Admin user
                        } else {
                            return "user"; // Regular user
                        }
                    }
                }
            } catch (Exception ex) {
                throw ExceptionHandler.invalidCredentialsException("Your Id or password did not match!");
            }

            return null;
        }
    }

}
