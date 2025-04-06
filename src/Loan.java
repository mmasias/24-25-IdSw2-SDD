public class Loan {
    private int maxDays;
    private User user;
    private Storage storage;

    public Loan(int maxDays, User user, Storage storage) {
        this.maxDays = maxDays;
        this.user = user;
        this.storage = storage;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(int maxDays) {
        this.maxDays = maxDays;
    }

    public User getUser() {
        return user;
    }

    public Storage getStorage() {
        return storage;
    }

    public void createLoan(int maxDays, User user) {
        this.maxDays = maxDays;
        this.user = user;
    }
}

