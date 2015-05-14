package WallConstruction;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * This class extends {@link JPanel}. It contains all components that user 
 * need to interact with to specify and draw the wall.
 * 
 * @author Akram Alofi, 2117863 and alof0002
 * @version 1.0
 */
public class SpeciﬁcationPanel extends JPanel {
    
    //Declare variables to hold JPanel width and height
    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;

    //Declare JPanel objects to be used as containers for other components
    private final JPanel jobIdentiﬁerPanel;
    private final JPanel wallSpeciﬁcationPanel;
    private final JPanel multipliersSpeciﬁcationPanel;
    private final JPanel drawButtonPanel;
    
    //Declare JLabel objects to describe required user input
    private final JLabel jobIdentiﬁerLabel;
    private final JLabel wallLengthLabel;
    private final JLabel startHeightLabel;
    private final JLabel endHeightLabel;
    private final JLabel locationLabel;
    private final JLabel accessibilityLabel;
    private final JLabel difficultyLabel;
    
    //Declare JTextField objects to receive inputs from user
    private final JTextField jobIdentiﬁerTextField;
    private final JTextField wallLengthTextField;
    private final JTextField startHeightTextField;
    private final JTextField endHeightTextField;
    
    //Declare JRadioButtons to allow user to select from the options
    private final JRadioButton locationYesJRadioButton;
    private final JRadioButton locationNoJRadioButton;
    private final JRadioButton accessibilityYesJRadioButton;
    private final JRadioButton accessibilityNoJRadioButton;
    
    //Declare ButtonGroup to group JRadioButtons 
    private final ButtonGroup locationButtonGroup;
    private final ButtonGroup accessibilityButtonGroup;
    
    //Declare JComboBox to allow user to select from list options
    private final JComboBox difficultyJComboBox;
   
    //Declare a variable that hold selected item index in difficultyJComboBox
    private int selectedIndex;
    
    private int numberOfwalls;
    
    //Declare JButton to allow execute wall draw action and show result
    private final JButton drawButton;
    
    //Declare JButton to allow user to clear GUI
    private final JButton clearButton;
    
    //Declare Controller object to connect this class with Controller class
    private final Controller controller;
    
    private boolean IsActionCompleted; 

    /**
     * This is the constructor method. It specifies all {@link JPanel}s, {@link JLabel}s,
     * {@link JTextField}s and {@link JButton} properties. Also, it connects this class with {@link Controller} class.
     * Furthermore, It validates user input and show warning messages.
     * 
     * @param controller - {@link Controller} class object
     */
    public SpeciﬁcationPanel(final Controller controller) {
        
        //Connect this class with Controller class
        this.controller = controller;
        
        //Set this JPanel Layout to null
        setLayout(null);
        
        //Calculate JPanel height and width based on screen size  
        PANEL_WIDTH  = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)*0.93);
        PANEL_HEIGHT = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)*0.95);

        numberOfwalls = 0;
        
        selectedIndex=0;
        
        IsActionCompleted = false;
        
        //Set the size of this JPanel
        setPreferredSize((new Dimension(PANEL_WIDTH, PANEL_HEIGHT)));
      
        //Set the location of this JPanel
        setLocation(((int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()* 0.02)), 0 );

        //Create jobIdentiﬁerPanel container object, set its layout to null, border title, location and size
        jobIdentiﬁerPanel = new JPanel();
        jobIdentiﬁerPanel.setLayout(null);
        jobIdentiﬁerPanel.setBorder(BorderFactory.createTitledBorder("Job Identifier"));
        jobIdentiﬁerPanel.setLocation(0 , ((int)(PANEL_WIDTH * 0.01)));
        jobIdentiﬁerPanel.setSize((int)(getSize().width* 1),((int)(PANEL_WIDTH * 0.1)));

        
        //Add jobIdentiﬁerPanel to this JPanel container
        add(jobIdentiﬁerPanel);
        
        //Create jobIdentiﬁerLabel JLabel object, set its location, size and text
        jobIdentiﬁerLabel = new JLabel();
        jobIdentiﬁerLabel.setLocation(((int)(jobIdentiﬁerPanel.getSize().width * 0.3)), ((int)(jobIdentiﬁerPanel.getSize().height * 0.04)));
        jobIdentiﬁerLabel.setSize(100, 50);
        jobIdentiﬁerLabel.setFont(new Font("Times New Roman", 0, 17));
        jobIdentiﬁerLabel.setText("Job identiﬁer:");
        
        //Add jobIdentiﬁerLabel to jobIdentiﬁerPanel JPanel container
        jobIdentiﬁerPanel.add(jobIdentiﬁerLabel);
                
        //Create wallSpeciﬁcationPanel container object, set its layout to null, border title, location and size
        wallSpeciﬁcationPanel = new JPanel();
        wallSpeciﬁcationPanel.setLayout(null);
        wallSpeciﬁcationPanel.setBorder(BorderFactory.createTitledBorder("Wall Speciﬁcation"));
        wallSpeciﬁcationPanel.setLocation(0, ((int)(PANEL_WIDTH * 0.1)));
        
        //Add jobIdentiﬁerLabel to jobIdentiﬁerPanel JPanel container
        wallSpeciﬁcationPanel.setSize(getSize().width,((int)(PANEL_WIDTH * 0.1)));
        
        //Add wallSpeciﬁcationPanel to this JPanel container
        add(wallSpeciﬁcationPanel);
        
        //Create wallLengthLabel JLabel object, set its location, size and text
        wallLengthLabel = new JLabel();
        wallLengthLabel.setLocation(20, 10);
        wallLengthLabel.setSize(350, 50);
        wallLengthLabel.setFont(new Font("Times New Roman", 0, 17));
        wallLengthLabel.setText("Wall length (in metres):");
        
        //Add wallLengthLabel to wallSpeciﬁcationPanel JPanel container
        wallSpeciﬁcationPanel.add(wallLengthLabel);
       
        //Create startHeightLabel JLabel object, set its location, size and text
        startHeightLabel = new JLabel();
        startHeightLabel.setLocation(20, 40);
        startHeightLabel.setSize(350, 50);
        startHeightLabel.setFont(new Font("Times New Roman", 0, 16));
        startHeightLabel.setText("Wall start height (in metres):");
        
        //Add startHeightLabel to wallSpeciﬁcationPanel JPanel container
        wallSpeciﬁcationPanel.add(startHeightLabel);
        
        //Create endHeightLabel JLabel object, set its location, size and text
        endHeightLabel = new JLabel();
        endHeightLabel.setLocation(20, 70);
        endHeightLabel.setSize(350, 50);
        endHeightLabel.setFont(new Font("Times New Roman", 0, 17));
        endHeightLabel.setText("Wall end height (in metres):");
        
        //Add endHeightLabel to wallSpeciﬁcationPanel JPanel container
        wallSpeciﬁcationPanel.add(endHeightLabel);
        
        //Create multipliersSpeciﬁcationPanel container object, set layout to null, border title, location and size
        multipliersSpeciﬁcationPanel = new JPanel();
        multipliersSpeciﬁcationPanel.setLayout(null);
        multipliersSpeciﬁcationPanel.setBorder(BorderFactory.createTitledBorder("Multipliers Speciﬁcation"));
        multipliersSpeciﬁcationPanel.setLocation(0, ((int)(PANEL_WIDTH * 0.27)));
        multipliersSpeciﬁcationPanel.setSize(730,130);
        
        //Add multipliersSpeciﬁcationPanel to this JPanel container
        add(multipliersSpeciﬁcationPanel);
                
        //Create locationLabel JLabel object, set its location, size and text
        locationLabel = new JLabel();
        locationLabel.setLocation(20, 10);
        locationLabel.setSize(350, 50);
        locationLabel.setFont(new Font("Times New Roman", 0, 17));
        locationLabel.setText("Is the wall to be built locally?");
        
        //Add locationLabel to locationLabel JPanel container
        multipliersSpeciﬁcationPanel.add(locationLabel);
        
        //Create accessibilityLabel JLabel object, set its location, size and text
        accessibilityLabel = new JLabel();
        accessibilityLabel.setLocation(20, 40);
        accessibilityLabel.setSize(350, 50);
        accessibilityLabel.setFont(new Font("Times New Roman", 0, 17));
        accessibilityLabel.setText("Is there adequate access to the site?");
        
        //Add accessibilityLabel to multipliersSpeciﬁcationPanel JPanel container
        multipliersSpeciﬁcationPanel.add(accessibilityLabel);
        
        //Create difficultyLabel JLabel object, set its location, size and text
        difficultyLabel = new JLabel();
        difficultyLabel.setLocation(20, 70);
        difficultyLabel.setSize(350, 50);
        difficultyLabel.setFont(new Font("Times New Roman", 0, 17));
        difficultyLabel.setText("What is the difficulty of the site?");
        
        //Add difficultyLabel to difficultyLabel JPanel container
        multipliersSpeciﬁcationPanel.add(difficultyLabel);
        
        //Create jobIdentiﬁerTextField JTextField object, set its location, size and background colour
        jobIdentiﬁerTextField = new JTextField ();
        jobIdentiﬁerTextField.setLocation(215, ((int)(PANEL_HEIGHT*0.04)));
        jobIdentiﬁerTextField.setSize(100,((int)(PANEL_HEIGHT*0.06)));
        jobIdentiﬁerTextField.setFont(new Font("Times New Roman", 0, 17));
        jobIdentiﬁerTextField.setBackground(Color.white);
        
        //Add jobIdentiﬁerTextField to jobIdentiﬁerTextField JPanel container
        jobIdentiﬁerPanel.add(jobIdentiﬁerTextField);
        
        //Create wallLengthTextField JTextField object, set its location, size and background colour
        wallLengthTextField = new JTextField ();
        wallLengthTextField.setLocation(300, ((int)(PANEL_HEIGHT*0.04)));
        wallLengthTextField.setSize(100,((int)(PANEL_HEIGHT*0.06)));
        wallLengthTextField.setFont(new Font("Times New Roman", 0, 17));
        wallLengthTextField.setBackground(Color.white);
        
        //Add wallLengthTextField to wallSpeciﬁcationPanel JPanel container
        wallSpeciﬁcationPanel.add(wallLengthTextField);
        
        //Create startHeightTextField JTextField object, set its location, size and background colour
        startHeightTextField = new JTextField ();
        startHeightTextField.setLocation(300, ((int)(PANEL_HEIGHT*0.10)));
        startHeightTextField.setSize(100,((int)(PANEL_HEIGHT*0.06)));
        startHeightTextField.setFont(new Font("Times New Roman", 0, 17));
        startHeightTextField.setBackground(Color.white);
        
        //Add startHeightTextField to wallSpeciﬁcationPanel JPanel container
        wallSpeciﬁcationPanel.add(startHeightTextField);
        
        //Create endHeightTextField JTextField object, set its location, size and background colour
        endHeightTextField = new JTextField ();
        endHeightTextField.setLocation(300, ((int)(PANEL_HEIGHT*0.16)));
        endHeightTextField.setSize(100,((int)(PANEL_HEIGHT*0.06)));
        endHeightTextField.setFont(new Font("Times New Roman", 0, 17));
        endHeightTextField.setBackground(Color.white);
        
        //Add endHeightTextField to wallSpeciﬁcationPanel JPanel container
        wallSpeciﬁcationPanel.add(endHeightTextField);
        
        //Create locationYesJRadioButton JRadioButton, set it as "Yes", make it the default selection and, set its location and size
        locationYesJRadioButton = new JRadioButton();
        locationYesJRadioButton.setText("Yes");
        locationYesJRadioButton.setSelected(true);
        locationYesJRadioButton.setLocation(300, ((int)(PANEL_HEIGHT*0.04)));
        locationYesJRadioButton.setSize(50, ((int)(PANEL_HEIGHT*0.06)));
        
        //Create locationNoJRadioButton JRadioButton set it as "No" and, set its location and size
        locationNoJRadioButton = new JRadioButton();
        locationNoJRadioButton.setText("No");
        locationNoJRadioButton.setLocation(350, ((int)(PANEL_HEIGHT*0.04)));
        locationNoJRadioButton.setSize(50, ((int)(PANEL_HEIGHT*0.06)));
        
        //Add ActionListener to locationNoJRadioButton which performs whenever user selecte it
        locationNoJRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                //Show information message dialog to user that contains the consequences of the selection
                if(controller.getLocation() != false) {
                    JOptionPane.showMessageDialog(new JPanel(), "The cost will be increased by 5%", "Information",JOptionPane.INFORMATION_MESSAGE);

                    setLocation("N");
                    if(numberOfwalls > 0) {
                        controller.updateResult();
                    }
                }
                
            }
        });
        
        //Add ActionListener to locationYesJRadioButton which performs whenever user selecte it
        locationYesJRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                //Show information message dialog to user that contains the consequences of the selection
                if(controller.getLocation() != true) {
                    JOptionPane.showMessageDialog(new JPanel(), "The cost will be decreased by 5%", "Information",JOptionPane.INFORMATION_MESSAGE);

                    setLocation("Y");
                    if(numberOfwalls > 0) {
                        controller.updateResult();
                    }
                }
            }
        });
                
        //Create locationButtonGroup object to group locationYesJRadioButton and locationNoJRadioButton JRadioButtons
        locationButtonGroup = new ButtonGroup();
        
        //Add locationYesJRadioButton and locationNoJRadioButton JRadioButtons to locationButtonGroup
        locationButtonGroup.add(locationYesJRadioButton);
        locationButtonGroup.add(locationNoJRadioButton);
        
        //Add locationYesJRadioButton and locationNoJRadioButton JRadioButtons to multipliersSpeciﬁcationPanel
        multipliersSpeciﬁcationPanel.add(locationYesJRadioButton);
        multipliersSpeciﬁcationPanel.add(locationNoJRadioButton);
        
        //Create accessibilityYesJRadioButton JRadioButton set it as "Yes", make it the default selection and, set its location and size
        accessibilityYesJRadioButton = new JRadioButton();
        accessibilityYesJRadioButton.setText("Yes");
        accessibilityYesJRadioButton.setSelected(true);
        accessibilityYesJRadioButton.setLocation(300, ((int)(PANEL_HEIGHT*0.10)));
        accessibilityYesJRadioButton.setSize(50, ((int)(PANEL_HEIGHT*0.06)));
        
        //Create accessibilityNoJRadioButton JRadioButton set it as "No" and, set its location and size
        accessibilityNoJRadioButton = new JRadioButton();
        accessibilityNoJRadioButton.setText("No");
        accessibilityNoJRadioButton.setLocation(350, ((int)(PANEL_HEIGHT*0.10)));
        accessibilityNoJRadioButton.setSize(50, ((int)(PANEL_HEIGHT*0.06)));
        
        //Add ActionListener to locationNoJRadioButton which performs whenever user selected it
        accessibilityNoJRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                //Show information message dialog to user that contains the consequences of the selection
                if(controller.getAccessibility() != false) {
                    JOptionPane.showMessageDialog(new JPanel(), "The cost will be increased by 30%", "Information",JOptionPane.INFORMATION_MESSAGE);

                    setAccessibility("N");
                    if(numberOfwalls > 0) {
                        controller.updateResult();
                    }
                }
            }
        });
        
        //Add ActionListener to accessibilityYesJRadioButton which performs whenever user selected it
        accessibilityYesJRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                //Show information message dialog to user that contains the consequences of the selection
                if(controller.getAccessibility() != true) {
                    JOptionPane.showMessageDialog(new JPanel(), "The cost will be decreased by 30%", "Information",JOptionPane.INFORMATION_MESSAGE);

                    setAccessibility("Y");
                    if(numberOfwalls > 0) {
                        controller.updateResult();
                    }
                }
            }
        });
                
        //Create accessibilityButtonGroup object to group locationYesJRadioButton and locationNoJRadioButton JRadioButtons
        accessibilityButtonGroup = new ButtonGroup();
        
        //Add accessibilityYesJRadioButton and accessibilityNoJRadioButton JRadioButtons to accessibilityButtonGroup
        accessibilityButtonGroup.add(accessibilityYesJRadioButton);
        accessibilityButtonGroup.add(accessibilityNoJRadioButton);      
        
        //Add accessibilityYesJRadioButton and accessibilityNoJRadioButton JRadioButtons to multipliersSpeciﬁcationPanel
        multipliersSpeciﬁcationPanel.add(accessibilityYesJRadioButton);
        multipliersSpeciﬁcationPanel.add(accessibilityNoJRadioButton);

        //Create difficultyJComboBox object, add list items, set its location and alignment
        difficultyJComboBox = new JComboBox();
        difficultyJComboBox.addItem("Normal");
        difficultyJComboBox.addItem("Sandy");
        difficultyJComboBox.addItem("Lime Stone");
        difficultyJComboBox.addItem("Blue Stone");
        difficultyJComboBox.setSize(100,((int)(PANEL_HEIGHT*0.05)));
        difficultyJComboBox.setLocation(300, ((int)(PANEL_HEIGHT*0.18)));
        difficultyJComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        //Add difficultyJComboBox to multipliersSpeciﬁcationPanel JPanel container
        multipliersSpeciﬁcationPanel.add(difficultyJComboBox);
        
        //Add ActionListener to difficultyJComboBox which performs whenever user selected an item
        difficultyJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                //Get the user selected item index within difficultyJComboBox
                selectedIndex = ((JComboBox)evt.getSource()).getSelectedIndex();
                
                //Show information message dialog that contains the consequences of the selection
                if(selectedIndex == 0) {
                    //JOptionPane.showMessageDialog(new JPanel(), "The extra land difficulty cost will be decreased", "Information",JOptionPane.INFORMATION_MESSAGE);
                    
                    setDifficulty("N");
                    if(numberOfwalls > 0) {
                        controller.updateResult();
                    }
                }
                else if(selectedIndex == 1 && !(controller.getDifficulty()+"").equalsIgnoreCase("S")) {
                    JOptionPane.showMessageDialog(new JPanel(), "The cost will be increased by 10%", "Information",JOptionPane.INFORMATION_MESSAGE);
                    
                    setDifficulty("S");
                    if(numberOfwalls > 0) {
                        controller.updateResult();
                    }
                }
                else if(selectedIndex == 2 && !(controller.getDifficulty()+"").equalsIgnoreCase("L")) {
                    JOptionPane.showMessageDialog(new JPanel(), "The cost will be increased by 15%", "Information",JOptionPane.INFORMATION_MESSAGE);
                    
                    setDifficulty("L" );
                    if(numberOfwalls > 0) {
                        controller.updateResult();
                    }
                }
                else if(selectedIndex == 3 && !(controller.getDifficulty()+"").equalsIgnoreCase("B")) {
                    JOptionPane.showMessageDialog(new JPanel(), "The cost will be increased by 30%", "Information",JOptionPane.INFORMATION_MESSAGE);
                    
                    setDifficulty("B");
                    if(numberOfwalls > 0) {
                        controller.updateResult();
                    }
                }
            }
	});
        
        //Create drawButtonPanel container object, set layout to null, border title, location and size
        drawButtonPanel = new JPanel();
        drawButtonPanel.setLayout(null);
        drawButtonPanel.setLocation(30, 350);
        drawButtonPanel.setSize(730,5);
        
        //Add drawButtonPanel to this JPanel container
        add(drawButtonPanel);
        
        //Create drawButton object, Sets the button's text, size and location 
        drawButton = new JButton();
        drawButton.setText("Draw Wall");
        drawButton.setSize(((int)(drawButtonPanel.getSize().width * 0.35)), 40);
        //drawButton.setLocation(110, 10);
        
        //Create drawButton object, Sets the button's text, size and location 
        clearButton = new JButton();
        clearButton.setText("Clear");
        clearButton.setSize(((int)(drawButtonPanel.getSize().width * 0.35)), 40);
        
        //Add drawButton to drawButtonPanel JPanel container
        drawButtonPanel.add(drawButton);
        drawButtonPanel.add(clearButton);
        
        //Add ActionListener to drawButton which performs whenever user press it
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(controller.getNumberOfWalls() == numberOfwalls) {
                    
                    if(numberOfwalls >= 4) {
                        showWarningMessageDialog("Number of walls is limited to 4");
                        return;
                    }
                    
                    controller.newWall();
                    IsActionCompleted = false;
                }
                
                //Set all JTextFields background colour to the default colour (white)
                jobIdentiﬁerTextField.setBackground(Color.WHITE);
                wallLengthTextField.setBackground(Color.WHITE);
                startHeightTextField.setBackground(Color.WHITE);
                endHeightTextField.setBackground(Color.WHITE);
                    
                //Set and validate all user inputs and if there is any invalid input
                //warning message dialog will be shown to user that informs user with invalid input and highlight its JtextFiled
                if(!setJobIdentiﬁer(jobIdentiﬁerTextField.getText())) {
                    showWarningMessageDialog("Job Identiﬁer Field\nFiled cannot be empty");
                    jobIdentiﬁerTextField.setBackground(new Color(255, 194, 191));
                }
                else if(!setWallLength(wallLengthTextField.getText())) {
                  showWarningMessageDialog("Wall Length Field\nInvalid input\nMust be a number greater than 0 and less than or equals to 100");
                  wallLengthTextField.setBackground(new Color(255, 194, 191));
                } 
                else if(!setStartHeight(startHeightTextField.getText())) {
                  showWarningMessageDialog("Wall Start Height Field\nInvalid input\nMust be an integer number greater than 0 and less than or equals to 10");  
                  startHeightTextField.setBackground(new Color(255, 194, 191));
                }                
                else if(!setEndHeight(endHeightTextField.getText())) {
                  showWarningMessageDialog("Wall End Height Field\nInvalid input\nMust be an integer number greater than 0  and less than or equals to 10");  
                  endHeightTextField.setBackground(new Color(255, 194, 191));
                }
                //All the previous inputs are valid
                else {
                    
                    //Set wall location based on user selection
                    if(locationNoJRadioButton.isSelected()){
                        setLocation("N");
                    }
                    else {
                        setLocation("Y");
                    }
                    
                    //Set wall accessibility based on user selection
                    if(accessibilityNoJRadioButton.isSelected()){
                        setAccessibility("N");
                    }
                    else {
                        setAccessibility("Y");
                    }

                    //Set difficulty based on user selection
                    if(selectedIndex == 0) {
                        setDifficulty("N");
                    }
                    else if(selectedIndex == 1) {
                        setDifficulty("S");
                    }
                    else if(selectedIndex == 2) {
                        setDifficulty("L");
                    }
                    else if(selectedIndex == 3) {
                        setDifficulty("B");
                    }
                    
                    if(numberOfwalls > 0) {
                        
                        String angle;
                        String msg = "Specify walls connection angle";
                                
                        do{
                            //Show input dialog
                            angle = JOptionPane.showInputDialog(new JPanel(), msg);
                            
                            //Add warning message
                            msg = "Invalid input\nSpecify walls connection angle\nMust be a number greater than 0 and less than 180";
                            
                        }while(angle != null && !controller.setWallAngle(numberOfwalls, angle));
                        
                        
                        if(angle != null) {
                            
                            //Invoke method to calculate the number and disputation of sleepers, draw wall and show the results
                            controller.process(numberOfwalls);
                            numberOfwalls++;
                            
                            wallLengthTextField.setText("");
                            startHeightTextField.setText("");
                            endHeightTextField.setText("");
                        }
                    }
                    else {
                        
                        //Invoke method to calculate the number and disputation of sleepers, draw wall and show the results
                        controller.process(numberOfwalls);
                        
                        numberOfwalls++;
                        IsActionCompleted = true;
                        
                        jobIdentiﬁerTextField.setEditable(false);
                        drawButton.setText("Draw Connected Wall");
                        
                        wallLengthTextField.setText("");
                        startHeightTextField.setText("");
                        endHeightTextField.setText("");
                    }
                }
            }
        });     
    
        //Add ActionListener to clearButton which performs whenever user press it
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                controller.clear();
                
            }
        }); 
    }
    
    /**
     * Sets and validate job identiﬁer that entered by user.
     * 
     * @param jobIdentiﬁer - user input job identiﬁer
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    private boolean setJobIdentiﬁer (String jobIdentiﬁer) {
        return controller.setJobIdentiﬁer(jobIdentiﬁer);
    }
    
    /**
     * Sets and validate wall length that entered by user.
     * 
     * @param wallLength - user input wall length
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    private boolean setWallLength(String wallLength) {
    
        return controller.setWallLength(numberOfwalls, wallLength);   
    }
    
    /**
     * Sets and validate start height that entered by user.
     * 
     * @param startHeight - user input start height
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    private boolean setStartHeight(String startHeight) {
        return controller.setStartHeight(numberOfwalls, startHeight);
    }
    
    /**
     * Sets and validate end height that entered by user.
     * 
     * @param endHeight - user input end height
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    private boolean setEndHeight(String endHeight) {
        return controller.setEndHeight(numberOfwalls, endHeight);
    }
    
    /**
     * Sets and validate wall location that entered by user.
     * 
     * @param location - user input wall location
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    private boolean setLocation (String location) {
        return controller.setLocation(location);
    }
    
    /**
     * Sets and validate wall location accessibility that entered by user.
     * 
     * @param accessibility - user input wall location accessibility
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    private boolean setAccessibility(String accessibility) {
        return controller.setAccessibility(accessibility);
    }
    
    /**
     * Sets and validate wall land difficulty that entered by user.
     * 
     * @param difficulty - user input wall land difficulty
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    private boolean setDifficulty(String difficulty) {
        return controller.setDifficulty(difficulty);
    } 
    
    /**
     * Resizes all components within this {@link JPanel}
     * 
     * @param resizeWidth - the difference between previous {@link JFrame} width and current {@link JFrame} width
     * @param resizeHeight - the difference between previous {@link JFrame} height and current {@link JFrame} height
     */
    public void resizeComponent (int resizeWidth, int resizeHeight) {
        
        //Resize this JPanel
        setSize(PANEL_WIDTH+resizeWidth,PANEL_HEIGHT+(resizeHeight/50));
        
        //Resize all JPanel containers 
        jobIdentiﬁerPanel.setSize(getSize().width ,((int)(PANEL_HEIGHT * 0.1)));
        wallSpeciﬁcationPanel.setSize(getSize().width,((int)(PANEL_HEIGHT * 0.25)));
        multipliersSpeciﬁcationPanel.setSize(getSize().width,((int)(PANEL_HEIGHT * 0.25)));
        drawButtonPanel.setSize( getSize().width,70);
        drawButtonPanel.setLocation(0, PANEL_HEIGHT-90);

        //Resize all JLabel
        jobIdentiﬁerLabel.setLocation(((int)(jobIdentiﬁerPanel.getSize().width * 0.25)), ((int)(jobIdentiﬁerPanel.getSize().height * 0.06)));
        wallLengthLabel.setLocation(((int)(wallSpeciﬁcationPanel.getSize().width * 0.25)), ((int)(wallSpeciﬁcationPanel.getSize().height * 0.1)));
        startHeightLabel.setLocation(((int)(wallSpeciﬁcationPanel.getSize().width * 0.25)), ((int)(wallSpeciﬁcationPanel.getSize().height * 0.3)));
        endHeightLabel.setLocation(((int)(wallSpeciﬁcationPanel.getSize().width * 0.25)), ((int)(wallSpeciﬁcationPanel.getSize().height * 0.55)));
        locationLabel.setLocation(((int)(multipliersSpeciﬁcationPanel.getSize().width * 0.25)), ((int)(multipliersSpeciﬁcationPanel.getSize().height * 0.1)));
        accessibilityLabel.setLocation(((int)(multipliersSpeciﬁcationPanel.getSize().width * 0.25)), ((int)(multipliersSpeciﬁcationPanel.getSize().height * 0.3)));
        difficultyLabel.setLocation(((int)(multipliersSpeciﬁcationPanel.getSize().width * 0.25)), ((int)(multipliersSpeciﬁcationPanel.getSize().height * 0.55)));

        //Resize all JTextFiled
        jobIdentiﬁerTextField.setLocation(((int)(jobIdentiﬁerPanel.getSize().width * 0.7)), ((int)(jobIdentiﬁerPanel.getSize().height * 0.3)));
        wallLengthTextField.setLocation(((int)(wallSpeciﬁcationPanel.getSize().width * 0.7)), ((int)(wallSpeciﬁcationPanel.getSize().height * 0.2)));
        startHeightTextField.setLocation(((int)(wallSpeciﬁcationPanel.getSize().width * 0.7)), ((int)(wallSpeciﬁcationPanel.getSize().height * 0.45)));
        endHeightTextField.setLocation(((int)(wallSpeciﬁcationPanel.getSize().width * 0.7)), ((int)(wallSpeciﬁcationPanel.getSize().height * 0.7)));
       
        //Resize all JRadioButton
        locationYesJRadioButton.setLocation(((int)(multipliersSpeciﬁcationPanel.getSize().width * 0.7)), ((int)(multipliersSpeciﬁcationPanel.getSize().height * 0.2)));
        locationNoJRadioButton.setLocation(((int)(multipliersSpeciﬁcationPanel.getSize().width * 0.8)), ((int)(multipliersSpeciﬁcationPanel.getSize().height * 0.2)));
        accessibilityYesJRadioButton.setLocation(((int)(multipliersSpeciﬁcationPanel.getSize().width * 0.7)), ((int)(multipliersSpeciﬁcationPanel.getSize().height * 0.45)));
        accessibilityNoJRadioButton.setLocation(((int)(multipliersSpeciﬁcationPanel.getSize().width * 0.8)), ((int)(multipliersSpeciﬁcationPanel.getSize().height * 0.45)));
        
        //Resize difficultyJComboBox
        difficultyJComboBox.setLocation(((int)(multipliersSpeciﬁcationPanel.getSize().width * 0.7)), ((int)(multipliersSpeciﬁcationPanel.getSize().height * 0.7)));

        //Resize drawButton and clearButton
        drawButton.setLocation(((int)(drawButtonPanel.getSize().width * 0.05)), 0);
        clearButton.setLocation(((int)(drawButtonPanel.getSize().width * 0.55)), 0);
        drawButton.setSize(((int)(drawButtonPanel.getSize().width * 0.35)), 40);
        clearButton.setSize(((int)(drawButtonPanel.getSize().width * 0.35)), 40);
    }
        
    /**
     * Shows dialog with the warning message
     * 
     * @param warningMessage - the warning message
     */
    private void showWarningMessageDialog (String warningMessage) {
        
        JOptionPane.showMessageDialog(new JPanel(), warningMessage, "Warning",JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Clears this {@link JPanel} and sey all options to their deault cases
     * 
     */
    public void clear () {
        
        numberOfwalls = 0;
        IsActionCompleted = true;
        selectedIndex = 0;
        
        jobIdentiﬁerTextField.setEditable(true);
        jobIdentiﬁerTextField.setText("");
        controller.setJobIdentiﬁer("");
        
        wallLengthTextField.setText("");
        startHeightTextField.setText("");
        endHeightTextField.setText("");
        
        locationYesJRadioButton.setSelected(true);
        controller.setLocation("Y");
        accessibilityYesJRadioButton.setSelected(true);
        controller.setAccessibility("Y");
        
        difficultyJComboBox.setSelectedIndex(selectedIndex);
        controller.setDifficulty("N");
        
        drawButton.setText("Draw Wall");
    }
    
    /**
     * Informs this method that there is a wall has been removed
     * 
     */
    public void wallRemoved () {
        numberOfwalls--;
    }
}