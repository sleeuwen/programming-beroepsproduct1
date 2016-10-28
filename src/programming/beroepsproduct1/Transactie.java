package programming.beroepsproduct1;

public class Transactie {
    private final int id;
    private final String title;
    private final double bedrag;
    private final int jaar;
    private final int maand;

    public Transactie(int id, String title, double bedrag, int jaar, int maand) {
        this.id = id;
        this.title = title;
        this.bedrag = bedrag;
        this.jaar = jaar;
        this.maand = maand;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getBedrag() {
        return bedrag;
    }

    public int getJaar() {
        return jaar;
    }

    public int getMaand() {
        return maand;
    }
}
