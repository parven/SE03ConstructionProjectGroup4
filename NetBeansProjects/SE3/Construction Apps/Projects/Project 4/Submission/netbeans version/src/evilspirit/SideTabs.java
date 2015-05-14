package evilspirit;

import javax.swing.*;
import java.awt.*;

/**
 * Formerly contained methods that extended a tabbedPane, now is really just a plain JTabbedPane with a special name.
 */
public class SideTabs extends JTabbedPane {

    public SideTabs(Dimension SidePanelDimension) {
        this.setPreferredSize(SidePanelDimension);
    }

}
