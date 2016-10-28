package programming.beroepsproduct1;

import javax.swing.*;

public class ProgrammingBeroepsproduct1 {
    public static void main(String[] args) {
        Database.init();

        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
