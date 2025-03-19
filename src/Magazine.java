public class Magazine extends Document {
    public String issn;
    public int numberPages;

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumber(int numberPages) {
        this.numberPages = numberPages;
    }
}