public abstract class Service {
    public int id;
    public String name;
    public boolean available;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisponible() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
