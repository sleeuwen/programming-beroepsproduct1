package programming.beroepsproduct1;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String DB_NAME = "transactions.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_NAME;

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void init() {
        String sql = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id INTEGER PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "amount REAL NOT NULL," +
                "year INT NOT NULL," +
                "month INT NOT NULL" +
                ");";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a new transaction into the database.
     */
    public static void insert(String title, double amount, int year, int month) {
        String sql = "INSERT INTO transactions (title, amount, year, month) VALUES (?, ?, ?, ?);";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setDouble(2, amount);
            stmt.setInt(3, year);
            stmt.setInt(4, month);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove a transaction from the database.
     */
    public static void remove(int id) {
        String sql = "DELETE FROM transactions WHERE id = ?;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the details of a given transaction in the database.
     */
    public static void update(int id, String title, double amount) {
        String sql = "UPDATE transactions SET title = ?, amount = ? WHERE id = ?;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setDouble(2, amount);
            stmt.setInt(3, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve all transactions for the given year and month.
     */
    public static ArrayList<Transaction> select(int year, int month) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT id, title, amount, year, month FROM transactions WHERE year = ? AND month = ?;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, year);
            stmt.setInt(2, month);

            ResultSet rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("amount"),
                        rs.getInt("year"),
                        rs.getInt("month")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    /**
     * Retrieve the user's balance up until a given year and month.
     */
    public static double totalAmount(int year, int month) {
        String sql = "SELECT SUM(amount) FROM transactions WHERE year < ?1 OR (year = ?1 AND month <= ?2);";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, year);
            stmt.setInt(2, month);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
