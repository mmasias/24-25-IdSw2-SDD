public class User {
    private int id;
    private String name;
    private String email;
    private String type;
    private int maxItems;

    public User(int id, String name, String email, String type, int maxItems) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
        this.maxItems = maxItems;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }
}
