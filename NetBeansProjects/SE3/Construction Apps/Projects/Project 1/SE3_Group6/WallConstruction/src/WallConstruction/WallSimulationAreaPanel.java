package WallConstruction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This class extends {@link JPanel}. It draws the specified walls and shows all 
 * the required sleepers to construct it.
 * 
 * @author Akram Alofi, 2117863 and alof0002
 * @version 1.0
 */
public final class WallSimulationAreaPanel extends JPanel implements MouseListener, MouseMotionListener{
    
    //Declare variables to hold JPanel width and height
    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
       
    //Declare variables to hold JPanel width and height
    private int EXTRA_PANEL_WIDTH;
    private int EXTRA_PANEL_HEIGHT;
    
    //Declare Controller object to connect this class with Controller class
    private final Controller controller;
    
    //Declare ResultPanel object to connect this class with ResultPanel class
    private final ResultPanel resultPanel;
    
    //Declare variables to hold factors to be used to adjust sleepers width and height 
    private final int sleeperHeightFactor;
    private final int sleeperWidthFactor; 
    
    //Declare Rectangle2D object to hold user's selected sleeper within drawed wall
    private Rectangle2D selectedRect;
    
    //Declare ArrayList contains all walll
    private final ArrayList<Object> allWalls;
    
    //Declare variables to hold mouse current x and y positions
    private int mouseXpos;
    private int mouseYpos;
    
    //Declare variables to hold the selected sleeper indexes in 'wall' ArrayList
    private int selectedWallIndex;
    private int selectedSleeperXIndex;
    private int selectedSleeperYIndex;
    
    //Declare variables to determine mouse movement direction when mouse being dragged
    private boolean isMamousMovesDown;
    private boolean isMamousMovesUp;
    
    /**
     * This is the constructor method. It specifies this {@link JPanel} properties. 
     * Also, it connects this class with {@link Controller} class.
     * In addition to add MouseListener and MouseMotionListener
     * 
     * @param controller - {@link Controller} class object
     * @param resultPanel - {@link ResultPanel} class object
     */
    public WallSimulationAreaPanel (Controller controller, ResultPanel resultPanel) {
    
        //Connect this class with Controller class
        this.controller = controller;
        
        //Connect this class with ResultPanel class
        this.resultPanel = resultPanel;
        
        //Set this JPanel Layout to null
        setLayout(null);
        
        //Set default height and width
        PANEL_WIDTH = 1800;
        PANEL_HEIGHT = 1800;
       
        //Initialize extra height and width size to zero (Hold the extra size required to make all walls visible)
        EXTRA_PANEL_WIDTH = 0;
        EXTRA_PANEL_HEIGHT = 0;
 
        //Initialize variables that hold factors to be used to adjust sleepers width and height 
        sleeperHeightFactor = 60;
        sleeperWidthFactor  = 60; 
        
        //Set the size, background colour, border title and location of this JPanel
        setPreferredSize(new Dimension(EXTRA_PANEL_WIDTH+PANEL_WIDTH,EXTRA_PANEL_HEIGHT+PANEL_HEIGHT));
        setBackground(Color.WHITE);
        setLocation(30, 0);
        
        //Create and initialize allWalls ArrayList variables
        allWalls = new ArrayList<>();
        
        //Initialize selectedRect by setting it to null (no sleeper is selected)
        selectedRect = null;
        
        //Initialize isMamousMovesDown and isMamousMovesUp by setting their status to false (there is no movement direction)
        isMamousMovesDown = false;
        isMamousMovesUp = false;
        
        //Initialize selectedRect by setting it to null (no sleeper is selected)
        mouseXpos =-1;
        mouseYpos =-1;
    
        //Initialize selectedSleeperXIndex and selectedSleeperYIndex be -1 (there is no indexes)
        selectedSleeperXIndex = -1;
        selectedSleeperYIndex = -1;
        
        //Add mouse listeners to this JPanel
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    /**
     * Sets draw area scroll pane
     * 
     */
    public void setWallDeawAreaScrollPane () {
        
        //Set scroll bar location
        resultPanel.wallDrawAreaScrollPane.getVerticalScrollBar().setValue(resultPanel.wallDrawAreaScrollPane.getVerticalScrollBar().getMaximum());
    }
    
    /**
     * Paints the wall as it specified with the required sleepers.
     * The  dimensions of each sleeper is 2m * 2mm.
     *
     * @param g - the Graphics context in which to paint.
     */
    @Override
    public void paintComponent(Graphics g) {
              
        super.paintComponent(g);
        
        //Remove all the components from this container
        removeAll();

        //Cast Graphics to Graphics2D
        Graphics2D g2 = (Graphics2D) g;

        //Claea wall ArrayList to add wall sleepers
        allWalls.clear();
                
        int lastWallXpost = 0;
        
        for(int w=0 ; w< controller.getNumberOfWalls() ; w++) {
            
            ArrayList<Object> wall = new ArrayList<>();
            wall.clear();
            
            //Get all bays in the wall
            int bays [] = controller.geBays(w);

            //If there is at least 1 bay
            if(bays != null) {

                //Hold value if highest number of sleepers
                int highestNumberOfSleepers = 0;

                //Get value if highest number of sleepers
                for(int i=0 ; i<bays.length ; i++) {

                    highestNumberOfSleepers = Math.max(highestNumberOfSleepers, bays[i]);
                }

                //Initialize this ArrayList to null - make it empty
                ArrayList<Rectangle2D> bay = null;
                
                //Create sleepers and prepare them to be drawn and add them to wall ArrayList
                for(int i=0 ; i<bays.length ; i++) {

                    bay = new ArrayList<Rectangle2D>();
                    
                    for(int j=0 ; j<bays[i] ; j++) {

                        //Specifie sleepers
                        Rectangle2D rect = new Rectangle2D.Double((lastWallXpost)+20+(i*2*sleeperWidthFactor), (int)getHeight()-20-((int)(0.2*sleeperHeightFactor)*j), 2*sleeperWidthFactor, (int)((0.2*sleeperHeightFactor)));
                        bay.add(rect);

                        //Check if user orders to increase bays height
                        if (selectedRect != null && isMamousMovesUp) {

                            //Start drawing when user movees mouse out of the selected sleeper
                            if (mouseYpos != 0) {

                                if(selectedWallIndex == w && selectedSleeperXIndex == i && selectedSleeperYIndex == j) {

                                    for(int z=0 ; z<(selectedRect.getMaxY()-mouseYpos)/((int)((0.2*sleeperHeightFactor)))-1 ; z++) {
                                        rect = new Rectangle2D.Double((lastWallXpost)+20+(i*2*sleeperWidthFactor), (int)getHeight()-20-((int)(0.2*sleeperHeightFactor)*(j+z+1)), 2*sleeperWidthFactor, (int)((0.2*sleeperHeightFactor)));
                                        bay.add(rect);
                                    }
                                }
                            }
                        }
                    }

                    //Add bay to wall ArrayList
                    wall.add(bay); 
                }
                
                //Calculate  extra size to extend JPanel size
                if(bay != null && bay.size() > 0) {
                    
                    lastWallXpost = lastWallXpost = (int)( bay.get(bay.size()-1).getMaxX() + (4*sleeperWidthFactor) + 15);
                    EXTRA_PANEL_WIDTH = lastWallXpost;
                    EXTRA_PANEL_HEIGHT = Math.max(((int)(0.2*sleeperHeightFactor)*highestNumberOfSleepers), EXTRA_PANEL_HEIGHT);
                }
            }
            
            allWalls.add(wall);
        }

        //Draw wall
        for(int w=0 ; w< allWalls.size() ; w++) {
            
            ArrayList<Object> wall = ((ArrayList<Object>) allWalls.get(w));
            boolean isAngleStringDraw = false;
            
            for (int i=0 ; i<wall.size() ; i++) {
                for (int j = ((ArrayList<Rectangle2D>) wall.get(i)).size()-1; j >=0 ; j--) {

                    //Declare and set value to tmp rectangle
                    Rectangle2D tmpTrect = ((ArrayList<Rectangle2D>) wall.get(i)).get(j);

                    //Draw selection box if this is the selected seleeper and mouse moves down
                    if (selectedWallIndex == w && selectedSleeperXIndex == i && selectedSleeperYIndex == j && isMamousMovesDown) {

                        //Calculate how many sleepers are selected
                        int numberOdSelectedSleepers = (int)((((ArrayList<Rectangle2D>)wall.get(selectedSleeperXIndex)).get(selectedSleeperYIndex).getMinY()- mouseYpos)/(0.2*sleeperHeightFactor))*-1;
                        
                        //Drwa rectangle
                        g2.setColor(new Color(100, 177, 255, 130));
                        g2.fill(tmpTrect);
                        g2.setColor(Color.DARK_GRAY);
                        g2.setStroke(g2.getStroke());
                        g2.draw(tmpTrect);
                        numberOdSelectedSleepers--;
                            
                        //If this is NOT the last selected sleeper
                        if(!tmpTrect.contains(mouseXpos,mouseYpos)) {
                            j--;

                            while(j >=0) {

                                //Drwa rectangle
                                tmpTrect = ((ArrayList<Rectangle2D>) wall.get(i)).get(j);
                                g2.setColor(new Color(100, 177, 255, 130));
                                g2.fill(tmpTrect);
                                g2.setColor(Color.DARK_GRAY);
                                g2.setStroke(g2.getStroke());
                                g2.draw(tmpTrect);
                                j--;
                                numberOdSelectedSleepers--;
                                    
                                //If this is the last selected sleeper
                                if(numberOdSelectedSleepers == 0 || tmpTrect.contains(mouseXpos,mouseYpos) ) {

                                    if (j >=0) {

                                        //Drwa rectangle
                                        tmpTrect = ((ArrayList<Rectangle2D>) wall.get(i)).get(j);

                                        g2.setColor(Color.LIGHT_GRAY);
                                        g2.fill(tmpTrect);
                                        g2.setColor(Color.DARK_GRAY);
                                        g2.setStroke(g2.getStroke());
                                        g2.draw(tmpTrect);
                                    }
                                    break;

                                }
                            }
                        }  
                    }
                    //If not selected sleeper
                    else {
                        // Set Colour Blue if sleepler os selected
                        if(selectedWallIndex == w && selectedWallIndex == w && selectedSleeperXIndex == i && selectedSleeperYIndex < j) {
                            g2.setColor(new Color(100, 177, 255, 130));
                        }
                        // Set Colour Gray otherwise
                        else {
                            g2.setColor(Color.LIGHT_GRAY);
                        }

                        //Drwa rectangle
                        g2.fill(tmpTrect);
                        g2.setColor(Color.DARK_GRAY);
                        g2.setStroke(g2.getStroke());
                        g2.draw(tmpTrect);
                    }
                    
                    if(!isAngleStringDraw && w >0) {
                           
                        g2.setColor(new Color(56, 129, 167));
                        g2.setFont(new Font("Times New Roman", 1, 16));
                        g2.drawString("The two walls are connected", (int)(tmpTrect.getMinX()-(4*sleeperWidthFactor)), (int)getHeight()-80);
                        g2.drawString(("by " + String.valueOf(controller.getWallAngle(w))+" degree"), (int)(tmpTrect.getMinX()+50-(4*sleeperWidthFactor)), (int)getHeight()-60);
                        isAngleStringDraw = true;
                    } 
                }
            }
        }

        //Set horizontal and vertical scroll bar policy
        resultPanel.wallDrawAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resultPanel.wallDrawAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                
        //Resize this JPanel
        setPreferredSize(new Dimension(EXTRA_PANEL_WIDTH+PANEL_WIDTH,(int)(60*0.2*sleeperHeightFactor)));       
    }
    
    /**
     * Invokes when the mouse button has been clicked.
     *
     *  **** IT WILL NOT BE USED IN THIS APPLICATION ****
     *
     *  @param e - the new {@link MouseEvent}
     */
    @Override
    public void mouseClicked(MouseEvent e) {
	// There is no need to use it in this application
    }

    /**
     * Invokes when the mouse button is pressed.
     *
     *  @param e - the new {@link MouseEvent}
     */
    @Override
    public void mousePressed(MouseEvent e) {
      
        //Create temporary Rectangle2D object
        Rectangle2D tmpRect;
        
        //find which sleeper is selected
        for(int w=0 ; w< allWalls.size() ; w++) {
            
            ArrayList<Object> wall = ((ArrayList<Object>) allWalls.get(w));
            for(int i=0 ; i<wall.size() ; i++) {
                for(int j=0 ; j<((ArrayList<Rectangle2D>)wall.get(i)).size() ; j++) {

                    tmpRect = ((ArrayList<Rectangle2D>)wall.get(i)).get(j);
                    if (tmpRect.contains(e.getX(), e.getY())){

                        //Hold sellper index
                        selectedWallIndex = w;
                        selectedSleeperXIndex = i;
                        selectedSleeperYIndex = j;

                        //Hold selected sleeper in selectedRect
                        selectedRect = tmpRect;
                    }   
                }    
            }
        }          
        repaint();
    }
    
    /**
     *  Invokes when the mouse pointer is released.
     *
     *
     * @param e - the new {@link MouseEvent}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        
        //If there is selected sleeper
        if(selectedRect != null && selectedSleeperXIndex >= 0 && selectedSleeperYIndex >= 0) {
            
            int extra;
            
            ArrayList<Object> wall = ((ArrayList<Object>) allWalls.get(selectedWallIndex));
            
            //Get how many sleepers have been removed
            if (((ArrayList<Rectangle2D>)wall.get(selectedSleeperXIndex)).get(selectedSleeperYIndex).getMaxY()< e.getY()) {
                
                extra = (int)((((ArrayList<Rectangle2D>)wall.get(selectedSleeperXIndex)).get(selectedSleeperYIndex).getMinY()- e.getY())/(0.2*sleeperHeightFactor));
            }
            //Get how many sleepers have been added
            else {
                
                extra = (int)((((ArrayList<Rectangle2D>)wall.get(selectedSleeperXIndex)).get(selectedSleeperYIndex).getMaxY()- e.getY())/(0.2*sleeperHeightFactor));
            }
            
            //Store extra resault
            controller.addExtraSleppers(selectedWallIndex, selectedSleeperXIndex, extra);
            
            //Get and show resault
            resultPanel.setResultText(controller.getResultText());
        }
        
        //Set variable to default values
        isMamousMovesDown = false;
        isMamousMovesUp = false;
        mouseXpos = -1;
        mouseYpos = -1;
        
        selectedWallIndex = -1;
        selectedSleeperXIndex = -1;
        selectedSleeperYIndex = -1;
        
        repaint();
        
        //Set selectedRect to null (these is no selected sleeper)
        selectedRect = null;
    }

    /**
     * Invokes when the mouse arrow has entered the {@link JPanel}.
     *
     *  **** IT WILL NOT BE USED IN THIS APPLICATION ****
     *
     * @param e - the new {@link MouseEvent}
     */
    @Override
    public void mouseEntered(MouseEvent e) {
	// There is no need to use it in this application
    }

    /**
     * Invokes when the mouse pointer excites this {@link JPanel}.
     * 
     * @param e - the new {@link MouseEvent}
     */
    @Override
    public void mouseExited(MouseEvent e) {

        //Set variable to default values
        isMamousMovesDown = false;
        isMamousMovesUp = false;
        mouseXpos = -1;
        mouseYpos = -1;        
        selectedWallIndex = -1;
        selectedSleeperXIndex = -1;
        selectedSleeperYIndex = -1;
    }

    /**
     * Invokes when the mouse pointer has dragged.
     *
     * @param e - the new {@link MouseEvent}
     */
    @Override
    public void mouseDragged(MouseEvent e) {  
        
        //If there is selected sleeper
        if(selectedRect != null && selectedSleeperXIndex >= 0 && selectedSleeperYIndex >= 0) {
            
            ArrayList<Object> wall = ((ArrayList<Object>) allWalls.get(selectedWallIndex));
            
            //Determine mouse movement direction
            if (((ArrayList<Rectangle2D>)wall.get(selectedSleeperXIndex)).get(selectedSleeperYIndex).getMaxY()< e.getY()){
                
                isMamousMovesDown = true;
            }
            else if (((ArrayList<Rectangle2D>)wall.get(selectedSleeperXIndex)).get(selectedSleeperYIndex).getMinY() > e.getY()){
            
                isMamousMovesUp = true;
            }
            else {
                
                isMamousMovesDown = false;
                isMamousMovesUp = false;
            }
        
            //Get mouse position
            mouseXpos = e.getX();
            mouseYpos = e.getY();


            repaint();
        } 
    }

    /**
     * Invokes when the mouse pointer moves within {@link JPanel}.
     * 
     *  **** IT WILL NOT BE USED IN THIS APPLICATION ****
     * 
     * @param e - the new {@link MouseEvent}
     */
    @Override
    public void mouseMoved(MouseEvent e) {        
        // There is no need to use it in this application
    }
}