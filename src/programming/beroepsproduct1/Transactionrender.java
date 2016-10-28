package programming.beroepsproduct1;

import javax.swing.*;
import java.awt.*;

public class Transactionrender extends JPanel implements ListCellRenderer<Transactie> {
    public Transactionrender() {
        super();

        setOpaque(true);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
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

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            // Fix for Nimbus background color.
            setBackground(new Color(list.getBackground().getRGB()));
            setForeground(new Color(list.getForeground().getRGB()));
        }

        setEnabled(true);
        setFont(list.getFont());

        return this;
    }
}
