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
        String sql = "CREATE TABLE IF NOT EXISTS transacties (" +
                "id INTEGER PRIMARY KEY," +
                "titel TEXT NOT NULL," +
                "bedrag REAL NOT NULL," +
                "jaar INT NOT NULL," +
                "maand INT NOT NULL" +
                ");";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(String titel, double bedrag, int jaar, int maand) {
        String sql = "INSERT INTO transacties (titel, bedrag, jaar, maand) VALUES (?, ?, ?, ?);";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titel);
            stmt.setDouble(2, bedrag);
            stmt.setInt(3, jaar);
            stmt.setInt(4, maand);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void remove(int id) {
        String sql = "DELETE FROM transacties WHERE id = ?;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(int id, String titel, double bedrag) {
        String sql = "UPDATE transacties SET titel = ?, bedrag = ? WHERE id = ?;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titel);
            stmt.setDouble(2, bedrag);
            stmt.setInt(3, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Transactie> select(int jaar, int maand) {
        ArrayList<Transactie> transacties = new ArrayList<>();
        String sql = "SELECT id, titel, bedrag, jaar, maand FROM transacties WHERE jaar = ? AND maand = ?;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jaar);
            stmt.setInt(2, maand);

            ResultSet rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                transacties.add(new Transactie(
                        rs.getInt("id"),
                        rs.getString("titel"),
                        rs.getDouble("bedrag"),
                        rs.getInt("jaar"),
                        rs.getInt("maand")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transacties;
    }

    public static double totalBedrag(int jaar, int maand) {
        String sql = "SELECT SUM(bedrag) FROM transacties WHERE jaar < ?1 OR (jaar = ?1 AND maand <= ?2);";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jaar);
            stmt.setInt(2, maand);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return rs.getDouble(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}
