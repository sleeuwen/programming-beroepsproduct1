/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming.beroepsproduct1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Frenky
 */
public class Database {
    
    public static void init(){
        try (Connection conn = connect();
            Statement stmt = conn.createStatement()) {
            // create a new table
            String sql = "CREATE TABLE IF NOT EXISTS transacties (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	titel text NOT NULL,\n"
                + "	bedrag real NOT NULL,\n"
                + "	jaar int NOT NULL,\n"
                + "	maand int NOT NULL"
                + ");";
            stmt.execute(sql);
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
        try (Connection conn = connect();
            Statement stmt = conn.createStatement()) {
            // insert
            String sql = "INSERT INTO transacties (titel, bedrag, jaar, maand ) VALUES ('" + titel + "'," + bedrag + "," + jaar + "," + maand +")";
            
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
     
     public static void remove(int id){
        try (Connection conn = connect();
            Statement stmt = conn.createStatement()) {
            // insert
            String sql = "DELETE FROM transacties WHERE id="+ id +";";
            
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
     
     public static void update(int id, String titel, double bedrag){
        try (Connection conn = connect();
            Statement stmt = conn.createStatement()) {
            // insert
            //            UPDATE transacties SET title='de titel   ', bedrag=20.95        WHERE id=1;
            String sql = "UPDATE transacties SET titel='"+ titel +"', bedrag="+ bedrag +" WHERE id="+ id +";";
            
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
     
      public static ArrayList<Transactie> select(int jaar, int maand){
        ArrayList<Transactie> transacties = new ArrayList<>();
        String sql = "SELECT id, titel, bedrag, jaar, maand FROM transacties WHERE jaar="+ jaar + " AND maand=" + maand +";";
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
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
        String sql = "SELECT SUM(bedrag) FROM transacties WHERE jaar <="+ jaar +" AND maand <="+maand+";";
         try (Connection conn = connect();
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(sql)){
            
             rs.next();
             return rs.getDouble(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         return 0;
    }
}
