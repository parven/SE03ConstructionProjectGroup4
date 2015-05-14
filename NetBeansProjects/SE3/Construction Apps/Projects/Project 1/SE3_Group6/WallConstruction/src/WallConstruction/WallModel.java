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
public class WallModel {
    
    //Declare Controller object to connect this class with Controller class
    private final Controller controller;
    
    //Declare variables to hold  wall specifications
    private double wallLength;
    private int startHeight;
    private int endHeight;
    private double connAngle;
    private int totalNumerOfSleepers;
    private final int sleeperLengthinm;
    private final double sleeperHeightinm;
    
    //Hold total numer of sleepers in each bay
    private int[] bays;
    
    /**
     * This is the constructor method. It connects this class with {@link Controller} class
     * and initialize default values. 
     * 
     * @param controller - {@link Controller} class object
     */
    public WallModel (Controller controller) {
        
        //Connect this class with Controller class
        this.controller = controller;
        
        //Set default values
        sleeperLengthinm = 2;
        sleeperHeightinm = 0.2;
        totalNumerOfSleepers = 0;
        connAngle = 0;
    }

    /**
     * Sets and validate wall length that entered by user.
     * 
     * @param wallLength - user input wall length
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setWallLength(String wallLength) {
    
        //If entered value is not valid throw exception and return false
        try{
           double parsedDouble = Double.parseDouble(wallLength);
            
            if(parsedDouble > 0 && parsedDouble <= 100)
            {
                this.wallLength = parsedDouble;
                return true;
            }
            
        }catch(Exception ex)
        {
            return false;
        }
        return false;   
    }
    
    /**
     * Sets and validate start height that entered by user.
     * 
     * @param startHeight - user input start height
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setStartHeight(String startHeight) {
        
        //If entered value is not valid throw exception and return false
        try{
           int parsedInteger = Integer.parseInt(startHeight);
            
           if(parsedInteger > 0 && parsedInteger <= 10)
           {
                this.startHeight = parsedInteger;
                return true;
           }
            
        }catch(Exception ex)
        {
            return false;
        }
        return false;
    }
    
    /**
     * Sets and validate end height that entered by user.
     * 
     * @param endHeight - user input end height
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setEndHeight(String endHeight) {
        
        //If entered value is not valid throw exception and return false
        try{
           int parsedInteger = Integer.parseInt(endHeight);
            
           if(parsedInteger > 0 && parsedInteger <= 10)
           {
                this.endHeight = parsedInteger;
                return true;
           }
            
        }catch(Exception ex)
        {
            return false;
        }
        return false;
    }
    
    /**
     * Sets and validate end height that entered by user.
     * 
     * @param angle - user input wall angle
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setWallAngle(String angle) {
        
        //If entered value is not valid throw exception and return false
        try{
           double parsedDouble = Double.parseDouble(angle);
            
           if(parsedDouble > 0 && parsedDouble < 180)
           {
                this.connAngle = parsedDouble;
                return true;
           }
            
        }catch(Exception ex)
        {
            return false;
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
     * Gets wall length variable
     * 
     * @return {@link Double} wall length
     */
    public double getWallLength() {
        
        return wallLength;
    }

    /**
     * Gets wall start height variable
     * 
     * @return {@link Integer} wall start height
     */
    public int getStartHeight() {
        
        return startHeight;
    }

    /**
     * Gets wall end height variable
     * 
     * @return {@link Integer} wall end height
     */
    public int getEndHeight() {
        
        return endHeight;
    }
    
    /**
     * Gets wall angle variable
     * 
     * @return {@link Double} wall end height
     */
    public double getWallAngle() {
        
        return connAngle;
    }

    /**
     * Gets bays array
     * 
     * @return {@link Integer} array contains bays 
     */
    public int[] geBays() {
        
        return bays;
    }
    
    /**
     * Executes calculation process to get sleepers distribution and show result.
     * 
     */
    public void process() {
        
        sleeperCalc();
        controller.showresult();
        controller.paintWall();
    }
    
    /**
     * SleeperCalc Method will calculate the number of sleepers required to create a wall, its square meter
     * and total cost according to the length, starting height and ending height given by the user
     * @author Nupur
     * 
     */
    public void sleeperCalc() {
        
        //total number of sleepers
        int noOfRows = 0;
        double upper = 0.0;
        int noOfBays = 0;
        double wallLengthTmp = wallLength;
        
        int wallLengthInt = (int) wallLength;
        if((double) wallLengthInt != wallLength) {
            wallLength = wallLengthInt+1;
        }
        
        if(wallLength%2 != 0) {
            wallLength++;
        }
        
        if (wallLength <= 3) {
            
            noOfBays = 1;
            totalNumerOfSleepers = (int) (Math.max(startHeight, endHeight) / sleeperHeightinm);
            bays = new int [1];
            bays[0] = totalNumerOfSleepers;   
        } 
        else {

            //calculating number of bays required for creating he wall
            noOfBays = (int) (wallLength / sleeperLengthinm);
            if ((wallLength % sleeperLengthinm) > (sleeperLengthinm / 2)) {
                
                noOfBays += 1;
                wallLength = wallLength + (sleeperLengthinm - (wallLength % sleeperLengthinm));
            } 
            else {
                
                wallLength = wallLength - (wallLength % sleeperLengthinm);
            }

            bays = new int[noOfBays];
            
            //Calculating the height of upper part and number of rows in below 
            //part according to the difference in starting height and ending height
            
            if (startHeight > endHeight) {
                
                upper = startHeight - endHeight;
                noOfRows = (int) (endHeight / sleeperHeightinm);
            } 
            else {
                
                upper = endHeight - startHeight;
                noOfRows = (int) (startHeight / sleeperHeightinm);
            }

            //total number of sleepers in below part
            totalNumerOfSleepers = noOfRows * noOfBays;

            //Checking if its a straight wall then calculating square meter and
            //sleepers in each bay.
            if (upper == 0) {

                for (int i = 0; i < bays.length; i++) {
                    bays[i] = noOfRows;
                }
            } 
            //Calculation in case of slanting wall
            else {
                double p = upper;
                int j = bays.length - 2; //variable to point array index when start height is smaller than end height
                int k = 1;               //variable to point array index when end height is smaller than start height
                int bayHeight = 0;      //height of the upper part of each bay
                double b = wallLength - sleeperLengthinm;

                if (endHeight < startHeight) {
                    bays[0] = (int) (p / sleeperHeightinm) + noOfRows;
                    bays[bays.length - 1] = noOfRows;
                } else {
                    bays[bays.length - 1] = (int) (p / sleeperHeightinm) + noOfRows;
                    bays[0] = noOfRows;
                }

                totalNumerOfSleepers = totalNumerOfSleepers + (int) (p / sleeperHeightinm);

                double angle = Math.atan(p / b);

                while (b != sleeperLengthinm) {
                    b = b - sleeperLengthinm;
                    p = (b * (Math.tan(angle)));
                    bayHeight = (int) (p / sleeperHeightinm);
                    totalNumerOfSleepers = totalNumerOfSleepers + bayHeight;
                    if ((p % sleeperHeightinm) > (sleeperHeightinm / 2)) {
                        totalNumerOfSleepers += 1;
                        bayHeight += 1;
                    }
                    if (startHeight < endHeight) {
                        bays[j] = bayHeight + noOfRows;
                        j--;
                    } else {
                        bays[k] = bayHeight + noOfRows;
                        k++;
                    }
                }
            }
        }
        wallLength = wallLengthTmp;
    } 

    /**
     * Adds extra sleepers that user added in the run time
     * 
     * @param bay - {@link Integer} bay index in bay Array
     * @param extraSleepers - {@link Integer} number of extra sleepers
     */
    public void addExtraSleppers (int bay , int extraSleepers) {
        
        this.bays[bay]+=extraSleepers;
        
        ArrayList<Integer> baysTmp = new ArrayList<Integer>();
        
        for(int i=0 ; i<bays.length ; i++) {
            if(bays[i] > 0) {
                baysTmp.add(bays[i]);
            }
        }
        
        bays = new int [baysTmp.size()];
        for(int i=0 ; i<baysTmp.size() ; i++) {
            bays[i] = baysTmp.get(i);
        }
    }
}