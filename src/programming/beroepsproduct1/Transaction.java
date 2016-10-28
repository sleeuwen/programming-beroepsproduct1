package programming.beroepsproduct1;

public class Transaction {
    private final int id;
    private final String title;
    private final double amount;
    private final int year;
    private final int month;

    public Transaction(int id, String title, double amount, int year, int month) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.year = year;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
