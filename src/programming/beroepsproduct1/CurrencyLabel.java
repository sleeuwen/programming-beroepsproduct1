/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming.beroepsproduct1;

import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JLabel;

/**
 *
 * @author Frenky
 */
public class CurrencyLabel extends JLabel {
    private static final DecimalFormat formatter = new DecimalFormat(" € #,##0.00");
       
    public CurrencyLabel() {
        super();
    }
    
    public CurrencyLabel(double amount, int horizontalAligment){
    super("", horizontalAligment);
    
    setAmount(amount);
    }
    
    public void setAmount(double amount){
        setText(formatter.format(amount));
        
        if(0 > amount){
            setForeground(new Color(215, 0, 0));
        }else{
            setForeground(new Color(0, 215, 0));
        }
    };
}
