package scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBar extends JScrollBar {

    public ScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(20, 20));
        setBackground(new Color(0,102,0));
        setUnitIncrement(20);
    }
}
