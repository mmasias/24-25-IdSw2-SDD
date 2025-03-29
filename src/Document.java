public abstract class Document {
    public int id;
    public String title;
    public String author;
    public int publicationYear;
    public boolean available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAñoPublicacion() {
        return publicationYear;
    }

    public void publicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isDisponible() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
