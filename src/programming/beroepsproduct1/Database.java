/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming.beroepsproduct1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Frenky
 */
public class Database {
    
    public static void init(){
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
    
     private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:Database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
     }     
     
     public static void insert(String titel, double bedrag, int jaar, int maand){
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
     
     public static void remove(int id){
         String sql = "DELETE FROM transacties WHERE id = ?;";

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
     
     public static void update(int id, String titel, double bedrag){
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
     
      public static ArrayList<Transactie> select(int jaar, int maand){
        ArrayList<Transactie> transacties = new ArrayList<>();
        String sql = "SELECT id, titel, bedrag, jaar, maand FROM transacties WHERE jaar = ? AND maand = ?;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, jaar);
            stmt.setInt(2, maand);

            ResultSet rs = stmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                transacties.add(
                    new Transactie(rs.getInt("id"), rs.getString("titel"), rs.getDouble("bedrag"), rs.getInt("jaar"), rs.getInt("maand"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transacties;
     }
      
     public static double totalBedrag(int jaar, int maand){
        String sql = "SELECT SUM(bedrag) FROM transacties WHERE jaar < ?1 OR (jaar = ?1 AND maand <= ?2);";
         try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
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
