/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming.beroepsproduct1;

/**
 *
 * @author Frenky
 */
public class Transactie {
    
    private int id;
    private String title;
    private double bedrag;
    private int jaar;
    private int maand;
    
    public Transactie(int id, String title, double bedrag, int jaar, int maand){
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
