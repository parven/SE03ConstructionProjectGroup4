package evilspirit;

import javax.swing.*;
import java.awt.*;

/**
 * Creates and initialises main gui components. Measures the main monitor resolution to scale sub components appropriately
 */
class MainGui {
    //this measures the MAIN screens size, so it works on multi monitor setups
    //...as long as the program is on the main screen
    private final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private final int screenWidth = gd.getDisplayMode().getWidth();
    private final int screenHeight = gd.getDisplayMode().getHeight();
    private final Dimension DrawingDimension = new Dimension((screenWidth / 4) * 3, screenHeight);
    private final Dimension SidePanelDimension = new Dimension(screenWidth / 5, screenHeight);

    private final Color BluePrintBlue = new Color(0, 110, 222);//create a blueprint colour
    private JFrame mainFrame;
    private DrawingPanel drawingPanel;
    private SideTabs sideTabs;
    private Quote quote;

    public MainGui() {

        initFrame();
        initBaseComponents();
        initTestData();
    }

    void initFrame() {
        mainFrame = new JFrame("Retaining Wall Quote - Evil Spirit 2015");
        mainFrame.setSize(screenWidth, screenHeight);
        mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);       //stolen from stackex to maximise
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void initBaseComponents() {
        sideTabs = new SideTabs(SidePanelDimension);            //a tabbed pane for representing a wall (as a panel) :)
        quote = new Quote(this);                                //A quote object binds the walls
        drawingPanel = new DrawingPanel(quote);                 //the panel for drawing the pictures

        mainFrame.add(drawingPanel, BorderLayout.WEST);
        mainFrame.add(sideTabs, BorderLayout.EAST);
        drawingPanel.setPreferredSize(DrawingDimension);
        drawingPanel.setBackground(BluePrintBlue);
        mainFrame.setVisible(true);
    }


    void initTestData() {
        quote.addWall(10, 1, 2, 30);    //add the test wall from the spec
        drawingPanel.refreshWallLine();
    }

    public SideTabs getTabs() {
        return sideTabs;
    }

    public Quote getQuote() {
        return quote;
    }

    public void removeSidePanel(SideBar s) {
        if (sideTabs.getTabCount() > 1) {
            sideTabs.remove(s);
            sideTabs.setSelectedIndex(0);
        }
    }
}
