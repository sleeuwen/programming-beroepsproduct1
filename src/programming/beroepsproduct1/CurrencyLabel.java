package programming.beroepsproduct1;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class CurrencyLabel extends JLabel {
    private static final DecimalFormat formatter = new DecimalFormat(" € #,##0.00");

    public CurrencyLabel() {
        super();
    }

    public CurrencyLabel(double amount, int horizontalAlignment) {
        super("", horizontalAlignment);

        setAmount(amount);
    }

    public void setAmount(double amount) {
        setText(formatter.format(amount));

        if (0 > amount) {
            setForeground(new Color(215, 0, 0));
        } else {
            setForeground(new Color(0, 215, 0));
        }
    }
}
