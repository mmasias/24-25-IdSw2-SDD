import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private Storage storage;
    private List<User> users;
    private List<Loan> loans;

    public LibraryManager() {
        this.storage = new Storage();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void addUser(String name, String email, String type) {
        // crear logica  
        // users.add(user);
    }

    public void addLoan(User user, Loan loan) {
        loans.add(loan);
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public String getUser(User user) {
        return "User: " + user.getName() + " (Email: " + user.getEmail() + ", Type: " + user.getType() + ")";
    }

    public Storage getStorage() {
        return storage;
    }

    public List<User> getUsers() {
        return users;
    }
}
