package WallConstruction;

import WallConstruction.WallSimulationAreaPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class extends {@link JPanel}. It contains all components that provide the  
 * outcomes of this application.
 * 
 * @author Akram Alofi, 2117863 and alof0002
 * @version 1.0
 */
public class ResultPanel extends JPanel{
    
    //Declare variables to hold JPanel width and height
    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    
    //Declare Controller object to connect this class with Controller class
    private final Controller controller;
    
    //Declare JPanel object to be used as wall drawing area
    private final WallSimulationAreaPanel wallSimulationAreaPanel;
    
    //Declare JScrollPane object to be used in wall drawing and resault area
    public final JScrollPane wallDrawAreaScrollPane;
    private final JScrollPane textResultScrollPane;
    
    //Declare TextArea objects to show result
    private final JTextArea textResultTextArea;
    
    /**
     * This is the constructor method. It specifies all {@link JPanel}s, {@link JScrollPane}s and {@link JTextArea}
     * properties. Also, it connects this class with {@link Controller} class.
     * 
     * @param controller - {@link Controller} class object
     */
    public ResultPanel (Controller controller) {
    
        //Connect this class with Controller class
        this.controller = controller;
        
        //Set this JPanel Layout to null
        setLayout(null);
        
        //Calculate JPanel height and width based on screen size  
        PANEL_WIDTH  = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2) * 0.93);
        PANEL_HEIGHT = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2) * 0.85);
        
        //Set the size of this JPanel
        setSize(PANEL_WIDTH,PANEL_HEIGHT);

        //Set the location of this JPanel
        setLocation(((int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()* 0.02)), ((int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.47)));
        setBorder(BorderFactory.createTitledBorder("Wall Simulation Area"));
        
        //Create wallSimulationAreaPanel object
        wallSimulationAreaPanel = new WallSimulationAreaPanel(controller, this);
        
        //Create wallDeawAreaScrollPane object and add it to this JPanel
        wallDrawAreaScrollPane = new JScrollPane (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        wallDrawAreaScrollPane.setSize( (int)(PANEL_WIDTH* 0.95), (int)(PANEL_HEIGHT* 0.6));
        wallDrawAreaScrollPane.setPreferredSize((new Dimension( (int)(PANEL_WIDTH* 0.95), (int)(PANEL_HEIGHT* 0.6))));
        wallDrawAreaScrollPane.setLocation((int)(PANEL_WIDTH* 0.2), (int)(PANEL_HEIGHT* 0.05));
        wallDrawAreaScrollPane.setViewportView(wallSimulationAreaPanel);

        //Create textResultTextArea object
        textResultTextArea = new JTextArea ();
        textResultTextArea.setFont(new Font("Times New Roman", 0, 18));
        textResultTextArea.setEditable(false);
        //textResultTextArea.setPreferredSize((new Dimension((int)(PANEL_WIDTH* 0.95), (int)((PANEL_HEIGHT* 0.25)))));

        //Create textResultScrollPane object and add it to this JPanel
        textResultScrollPane = new JScrollPane (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textResultScrollPane.setSize(  (new Dimension((int)(getSize().width* 0.95), (int)((getSize().height* 0.25)))));
        textResultScrollPane.setPreferredSize((new Dimension((int)(getSize().width* 0.95), (int)((getSize().height* 0.25)))));
        textResultScrollPane.setViewportView(textResultTextArea);
        add(textResultScrollPane);
    }
    
    /**
     * Invokes to display wall calculation result
     * 
     * @param text - result text to be displayed in result {@link JLabel}
     */
    public void setResultText (String text) {

        textResultTextArea.setText("  Job Identiﬁer: "+controller.getJobIdentiﬁer()+"\n   "+text.replaceFirst("There will be ", "\n   There will be "));
        
        //Resize textResultTextArea to accommodate full result text
        textResultTextArea.setSize((new Dimension(Math.max((int)(getSize().width* 0.95),((int)((getFontMetrics(textResultTextArea.getFont()).stringWidth(textResultTextArea.getText()))))), (int)((getSize().height* 0.25)))));
        textResultTextArea.setPreferredSize((new Dimension(Math.max((int)(getSize().width* 0.95),((int)((getFontMetrics(textResultTextArea.getFont()).stringWidth(textResultTextArea.getText()))))), (int)((getSize().height* 0.25)))));
        
    }
    
    /**
     * Resize all components within this {@link JPanel}
     * 
     * @param resizeWidth - the difference between previous {@link JFrame} width and current {@link JFrame} width
     * @param resizeHeight - the difference between previous {@link JFrame} height and current {@link JFrame} height
     */
    public void resizeComponent (int resizeWidth, int resizeHeight) {
        
        setSize(PANEL_WIDTH+resizeWidth,getSize().height);

        System.out.println();
        wallSimulationAreaPanel.setWallDeawAreaScrollPane();     
        wallDrawAreaScrollPane.setSize((new Dimension((int)(getSize().width* 0.95), (int)((getSize().height* 0.6)))));
        textResultScrollPane.setSize(  (new Dimension((int)(getSize().width* 0.95), (int)((getSize().height* 0.25)))));
        textResultTextArea.setSize((new Dimension(Math.max((int)(getSize().width* 0.95),((int)((getFontMetrics(textResultTextArea.getFont()).stringWidth(textResultTextArea.getText()))))), (int)((getSize().height* 0.25)))));
        textResultTextArea.setPreferredSize((new Dimension(Math.max((int)(getSize().width* 0.95),((int)((getFontMetrics(textResultTextArea.getFont()).stringWidth(textResultTextArea.getText()))))), (int)((getSize().height* 0.25)))));
        wallDrawAreaScrollPane.setLocation((int)(PANEL_WIDTH* 0.035), (int)(PANEL_HEIGHT* 0.05));
        textResultScrollPane.setLocation((int)(PANEL_WIDTH* 0.035), (int)(PANEL_HEIGHT* 0.7));
    }

    /**
     * Invokes to make result area visable and draw the specified wall
     * 
     */
    public void paintWall() {
        add(wallDrawAreaScrollPane);
        wallSimulationAreaPanel.setWallDeawAreaScrollPane();
        wallSimulationAreaPanel.repaint();
    }
    
    /**
     * Clears and rest all options to their default cases
     * 
     */
    public void clear () {
        
        repaint();
        textResultTextArea.setText("");
    }
}