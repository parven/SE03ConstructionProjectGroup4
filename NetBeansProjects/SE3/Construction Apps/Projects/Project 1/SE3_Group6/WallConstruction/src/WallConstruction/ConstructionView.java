package WallConstruction;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;

/**
 * This class extends {@link JFrame}. It is the main view components which contains
 * the main two {@link JPanel}s.
 * 
 * @author Akram Alofi, 2117863 and alof0002
 * @version 1.0
 */
public class ConstructionView extends JFrame {

    //Declare variables to hold JFrame width and height
    private int FRAME_WIDTH;
    private int FRAME_HEIGHT;
    
    //Declare Controller object to connect this class with Controller class
    private final Controller controller;
    
    //Declare SpeciﬁcationPanel object
    private final SpeciﬁcationPanel speciﬁcationPanel;
    
    //Declare ResultPanel object
    private final ResultPanel resultPanel;
    
    //Declare isShowResultVisable object
    private boolean isShowResultVisable;
       
    /**
     * This is the constructor method. It specifies all {@link JFrame} properties. 
     * Also, it connects this class with {@link Controller} class.
     * 
     * @param controller - {@link Controller} class object
     */
    public ConstructionView (Controller controller)
    {
        //Connect this class with Controller class
        this.controller = controller;
        
        //Calculate JFrame height and width based on screen size  
        FRAME_WIDTH  = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2) );
        FRAME_HEIGHT = (int) (((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)));

        //Set isShowResultVisable to false
        isShowResultVisable = false;
        
        //Set this JFrame minimum size
        setMinimumSize(new Dimension (FRAME_WIDTH, FRAME_HEIGHT));
        
        //Create speciﬁcationPanel object and add it to this JFrame
        speciﬁcationPanel = new SpeciﬁcationPanel(controller);
        add(speciﬁcationPanel);
        
        //Create speciﬁcationPanel object and add it to this JFrame
        resultPanel = new ResultPanel(controller);
        
        //Set this JFrame properties
        setLayout(null);
        setTitle("Quotation of the construction wall");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set JFrame position
        setLocationRelativeTo(null);
        
        pack();
        setVisible(true);
        
        //Add ComponentListener to JFram
        addComponentListener(new ComponentAdapter() {
            @Override
            //Invokes whenever user resizes this JFrame
            public void componentResized(ComponentEvent e) {

                //Invokes the speciﬁcationPanel and resultPanel resizing methods
                speciﬁcationPanel.resizeComponent(getSize().width-FRAME_WIDTH, getSize().height-FRAME_HEIGHT);
                resultPanel.resizeComponent(getSize().width-FRAME_WIDTH, getSize().height-FRAME_HEIGHT);
            }
        });
    }
    
    /**
     * Adds result {@link JPanel} to this {@link JFrame} in the run time
     * 
     */
    public void showresult () {
        
        //Increase this JFrame size
        FRAME_HEIGHT = (int) (((Toolkit.getDefaultToolkit().getScreenSize().getHeight())) * 0.95);

        //Reset minimum size
        setMinimumSize(new Dimension (FRAME_WIDTH, FRAME_HEIGHT));

        //Set JFrame position
        setLocationRelativeTo(null);

        //Add resultPanel
        add(resultPanel);
    }
    
    /**
     * Gets resultPanel object which extends {@link JPanel}
     * 
     * @return {@link ResultPanel} resultPanel object
     */
    public ResultPanel getResultPanel () {
        
        return resultPanel;
    }
    
    /**
     * Clears and resets all options to their default cases
     * 
     */
    public void clear () {
        
        speciﬁcationPanel.clear();
        resultPanel.clear();
    }
    
    /**
     * Informs this method that there is a wall has been removed
     * 
     *  **** THIS IS CONNECTING METHOD ****
     * 
     */
    public void wallRemoved () {
        speciﬁcationPanel.wallRemoved();
    }
}