package WallConstruction;


import java.util.ArrayList;

/**
 * This is the main Model class. It holds and calculates the required sleepers 
 * to build the specified wall and produces the result.
 * 
 * @author Akram Alofi, 2117863 and alof0002
 * @author Nupur
 * @version 1.0
 */
public class Model {
    
    //Declare Controller object to connect this class with Controller class
    private final Controller controller;
    
    //Decalre the main Model class
    public final ArrayList<WallModel> walls;
    
    //Declare variables to hold  wall specifications
    private String jobIdentiﬁer;
    private boolean location;
    private boolean accessibility;
    private char difficulty;
    private int totalNumerOfBays;
    private int totalNumerOfSleepers;
    private double wallArea;
    private final int baseAmount;
    private final int sleeperLengthinm;
    private final double sleeperHeightinm;
    
    /**
     * This is the constructor method. It connects this class with {@link Controller} class
     * and initialize default values. 
     * 
     * @param controller - {@link Controller} class object
     */
    public Model (Controller controller) {
        
        //Connect this class with Controller class
        this.controller = controller;
        
        //Create the WallModel Model Arraylist
        walls = new ArrayList<WallModel>();
        
        //Set default values
        baseAmount = 425;
        sleeperLengthinm = 2;
        sleeperHeightinm = 0.2;
        totalNumerOfSleepers = 0;
        wallArea = 0.0;
        location = true;
        accessibility = true;
        difficulty = 'N';
    }
    
    /**
     * Invokes to create new {@link WallModel} object and add ti to model {@link ArrayList}.
     * Each object represents a wall
     * 
     * @return the index of the created model
     */
    public int newWall() {
        
        walls.add(new WallModel(controller));
        
        return walls.size();
    }
    
    /**
     * Gets number of walls which is the size of walls {@link WallModel} {@link ArrayList}
     * 
     * @return {@link WallModel} {@link ArrayList} size
     */
    public int getNumberOfWalls() {
        
        return walls.size();
    }
  
    /**
     * Removes the last element in {@link WallModel} {@link ArrayList}
     * 
     */
    public void removeLast() {
        
        if (walls.size() >0) {
            walls.remove(walls.size()-1);
        }
    }
    
    /**
     * Sets and validate job identiﬁer that entered by user.
     * 
     * @param jobIdentiﬁer - user input job identiﬁer
     * 
     * @return true if input is valid and false otherwise.
     */
    public boolean setJobIdentiﬁer (String jobIdentiﬁer) {
        
        //If jobIdentiﬁer is NOT empty
        if (jobIdentiﬁer.isEmpty()) {
            return false;
        }
        
        this.jobIdentiﬁer = jobIdentiﬁer;
        return true;
    }
    
    /**
     * Sets and validate wall length that entered by user.
     * 
     * @param index - wall index in the {@link ArrayList}
     * @param wallLength - user input wall length
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setWallLength(int index, String wallLength) {
    
        return walls.get(index).setWallLength(wallLength);
    }
    
    /**
     * Sets and validate start height that entered by user.
     * 
     * @param index - model index in the {@link ArrayList}
     * @param startHeight - user input start height
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setStartHeight(int index, String startHeight) {
        
        return walls.get(index).setStartHeight(startHeight);
    }
    
    /**
     * Sets and validate end height that entered by user.
     * 
     * @param index - model index in the {@link ArrayList}
     * @param endHeight - user input end height
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setEndHeight(int index, String endHeight) {
        
        return walls.get(index).setEndHeight(endHeight);
    }
    
    /**
     * Sets and validate wall angle that entered by user.
     * 
     * @param index - model index in the {@link ArrayList}
     * @param angle - user input wall angle
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setWallAngle(int index, String angle) {
        
        return walls.get(index).setWallAngle(angle);
    }
    
    /**
     * Sets and validate wall location that entered by user.
     * 
     * @param location - user input wall location
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setLocation (String location) {
        
        if(location.equalsIgnoreCase("Y"))
        {
            this.location = true;
            return true;
            
        }
        else if(location.equalsIgnoreCase("N"))
        {
            this.location = false;
            return true;
        }
        return false;
    }
    
    /**
     * Sets and validate wall location accessibility that entered by user.
     * 
     * @param accessibility - user input wall location accessibility
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setAccessibility(String accessibility) {
        
        if(accessibility.equalsIgnoreCase("Y"))
        {
            this.accessibility = true;
            return true;
            
        }
        else if(accessibility.equalsIgnoreCase("N"))
        {
            this.accessibility = false;
            return true;
        }
        return false;
    }
    
    /**
     * Sets and validate wall land difficulty that entered by user.
     * 
     * @param difficulty - user input wall location difficulty
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setDifficulty(String difficulty) {
        
        if(difficulty.equalsIgnoreCase("N") || difficulty.equalsIgnoreCase("S") || difficulty.equalsIgnoreCase("L") || difficulty.equalsIgnoreCase("B"))
        {
            this.difficulty = difficulty.charAt(0);
            return true;
            
        }

        return false;
    }
    
    /**
     * Invokes to display wall calculation result
     * 
     * @param text - result text to be displayed in result {@link JLabel}
     */
    public void setResultText (String text) {
        
        controller.setResultText(text);
    }
       
    /**
     * Gets job identiﬁer variable
     * 
     * @return {@link String} wall job identiﬁer
     */
    public String getJobIdentiﬁer() {
        
        return jobIdentiﬁer;
    }

    /**
     * Gets wall length variable
     * 
     * @param index - model index in the {@link ArrayList}
     * 
     * @return {@link Double} wall length
     */
    public double getWallLength(int index) {
        
        return walls.get(index).getWallLength();
    }

    /**
     * Gets wall start height variable
     * 
     * @param index - model index in the {@link ArrayList}
     * 
     * @return {@link Integer} wall start height
     */
    public int getStartHeight(int index) {
        
        return walls.get(index).getStartHeight();
    }

    /**
     * Gets wall end height variable
     * 
     * @param index - model index in the {@link ArrayList}
     * 
     * @return {@link Integer} wall end height
     */
    public int getEndHeight(int index) {
        
        return walls.get(index).getEndHeight();
    }
    
    /**
     * Gets wall end height variable
     * 
     * @param index - model index in the {@link ArrayList}
     * 
     * @return {@link Double} wall angle
     */
    public double getWallAngle(int index) {
        
        return walls.get(index).getWallAngle();
    }

    /**
     * Gets wall location variable
     * It returns true for Yes and false for No
     * 
     * @return {@link Boolean} wall location code
     */
    public boolean getLocation() {
        
        return location;
    }

    /**
     * Gets location accessibility variable
     * It returns true for Yes and false for No
     * 
     * @return {@link Boolean} location accessibility code
     */
    public boolean getAccessibility() {
        
        return accessibility;
    }
    
    /**
     * Gets difficulty variable
     * 
     * @return {@link Character} difficulty code 
     */
    public char getDifficulty() {
        
        return difficulty;
    }

    /**
     * Gets bays array
     * 
     * @param index - model index in the {@link ArrayList}
     * 
     * @return {@link Integer} array contains bays 
     */
    public int[] geBays(int index) {
        
        return walls.get(index).geBays();
    }
    
    /**
     * Executes calculation process to get sleepers distribution and show result.
     * 
     * @param index - model index in the {@link ArrayList}
     * 
     */
    public void process(int index) {
        
     
        walls.get(index).sleeperCalc();
        
        String res = getResultText();
        controller.showresult();
        controller.setResultText(res);
        controller.paintWall();
    }
    
    /**
     * Updates text result and show it to user
     * 
     */
    public void updateResult () {

        if(walls.size() > 0) {
            String res = getResultText();
            controller.setResultText(res);
            controller.paintWall();
        }
    }
    
    /**
     * Adds extra sleepers that user added in the run time and remove wall if contains no bays
     * 
     * @param index - model index in the {@link ArrayList}
     * @param bay - {@link Integer} bay index in bay Array
     * @param extraSleepers - {@link Integer} number of extra sleepers
     * 
     */
    public void addExtraSleppers (int index,int bay , int extraSleepers) {
        
        walls.get(index).addExtraSleppers(bay, extraSleepers);
        
        wallRemove();
    }
    
    /**
     * Removes empty wall from walls {@link ArrayList}
     * 
     * 
     */
    private void wallRemove () {
        
        for (int i=0 ; i < walls.size() ; i++) {
            if (walls.get(i).geBays().length == 0) {
                
                walls.remove(i);
                
                //Informs view classes that there is a wall has been deleted
                controller.wallRemoved();
            }
        }
    }
    
    /**
     * Calculates wall extra cost based on user inputs
     * 
     * @return {@link Double} the presented of total cost 
     */
    private double calculateExtraCost () {
        
        double extraCostAmount = 1;
        
        //Add all location extra cost
        if(!location) {
            extraCostAmount+= 0.05;
        }
        
        //Add all location accessibility extra cost
        if(!accessibility) {
            extraCostAmount+= 0.3;
        }
        
        //Add all land difficulty extra cost
        if("S".equalsIgnoreCase(""+difficulty)) {
            extraCostAmount+= 0.1;
        }
        else if("L".equalsIgnoreCase(""+difficulty)) {
            extraCostAmount+= 0.15;
        }
        else if("B".equalsIgnoreCase(""+difficulty)) {
            extraCostAmount+= 0.3;
        }
        
        return extraCostAmount;
    }
    
    /**
     * Calculates total number of bays in the wall
     * 
     */
    private void getNumberOfBays () {
        
        totalNumerOfBays = 0;
        
        for(int i=0 ; i < walls.size() ; i++) {
            totalNumerOfBays+=walls.get(i).geBays().length;
        }
    }
    
    /**
     * Calculates total number sleepers in the wall
     * 
     */
    private void getNumberOfSleepers () {
        
        int totalSleepers = 0;
        
        for(int i=0 ; i < walls.size() ; i++) {
            
            int [] baysymp = walls.get(i).geBays();
            for(int j=0 ; j < baysymp.length ; j++) {
                
                totalSleepers+=baysymp[j];
            }
        }
        
        totalNumerOfSleepers = totalSleepers;
    }
    
    
    /**
     * Calculates wall area
     * 
     */
    private void getWallArea () {
        
        wallArea = totalNumerOfSleepers * sleeperLengthinm * sleeperHeightinm;
    }
    
    /**
     * Gets wall text result
     * 
     * @return {@link String} wall result in text format
     */
    public String getResultText () {
        
        getNumberOfSleepers ();
        getNumberOfBays();
        getWallArea ();
        
        String res = "";
        
        res+="The wall will require " + totalNumerOfSleepers + "  sleepers, will be " + String.format("%.2f", wallArea) + " m2 and cost $" + String.format("%.2f", (wallArea * baseAmount * calculateExtraCost())) + ".";
        res+="There will be " + totalNumerOfBays + " bays with the following sleeper totals: |";
        
        for(int i=0 ; i < walls.size() ; i++) {
            
            int [] baysymp = walls.get(i).geBays();
            for(int j=0 ; j < baysymp.length ; j++) {
                
                res+=baysymp[j]+"|";
            }
            
            if(i < walls.size()-1) {
                res+=" - |";
            }
        }
        return res;
    }
    
    /**
     * Clears and resets all options to their default cases
     * 
     */
    public void clear () {
        
        walls.clear();
        totalNumerOfBays = 0;
        totalNumerOfSleepers = 0;  
    }
}