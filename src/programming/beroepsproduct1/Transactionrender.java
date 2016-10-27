/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming.beroepsproduct1;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

/**
 *
 * @author Frenky
 */
public class Transactionrender extends JPanel implements ListCellRenderer<Transactie> {

   public Transactionrender(){
       setOpaque(true);
       setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
       setBorder(BorderFactory.createEmptyBorder(2,5, 2, 5));
   }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Transactie> list, Transactie transactie, int index, boolean isSelected, boolean cellHasFocus) {
        setComponentOrientation(list.getComponentOrientation());
        
        JLabel title = new JLabel(transactie.getTitle(), SwingConstants.LEFT);
        CurrencyLabel amount = new CurrencyLabel(transactie.getBedrag(), SwingConstants.RIGHT);
        
        removeAll();
        add(title);
        add(Box.createHorizontalGlue());
        add(amount);
        
        if(isSelected){
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }else{
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        
        setEnabled(true);
        setFont(list.getFont());
        
    return this;
    }
}
