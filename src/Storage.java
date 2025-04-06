import java.util.ArrayList;
import java.util.List;

public class Storage {
    private int id;
    private List<Magazine> magazines = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Audiovisual> audiovisuals = new ArrayList<>();
    private List<Material> materials = new ArrayList<>();
    private List<Classroom> classrooms = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMagazine(Magazine magazine) {
        magazines.add(magazine);
    }

    public void addAudiovisual(Audiovisual audiovisual) {
        audiovisuals.add(audiovisual);
    }

    public void addClassroom(Classroom classroom) {
        classrooms.add(classroom);
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }

    public void eraseBook(int id) {
        books.removeIf(b -> b.getId() == id);
    }

    public void eraseMagazine(int id) {
        magazines.removeIf(m -> m.getId() == id);
    }

    public void eraseAudiovisual(int id) {
        audiovisuals.removeIf(a -> a.getId() == id);
    }

    public void eraseClassroom(int id) {
        classrooms.removeIf(c -> c.getId() == id);
    }

    public void eraseMaterial(int id) {
        materials.removeIf(m -> m.getId() == id);
    }

    public Book getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public Magazine getMagazineById(int id) {
        return magazines.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    public Audiovisual getAudiovisualById(int id) {
        return audiovisuals.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public Classroom getClassroomById(int id) {
        return classrooms.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Material getMaterialById(int id) {
        return materials.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }
}
