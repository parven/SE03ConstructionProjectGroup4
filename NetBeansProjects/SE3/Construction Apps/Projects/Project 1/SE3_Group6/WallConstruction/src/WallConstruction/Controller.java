package WallConstruction;

/**
 * This is the main Controller class. It controls and connects the other components of this application.
 * 
 * @author Akram Alofi, 2117863 and alof0002
 * @version 1.0
 */
public class Controller {

    //Decalre the main Model class
    private final Model model;
    
    //Decalre the main View class
    public final ConstructionView constructionView;
    
    /**
     * This is the constructor method. It creates and connects the other main 
     * components of this application
     * 
     */
    public Controller () {
        
        //Create the main Model class
        model = new Model(this);
        
        //DecaCreate the main View class
        constructionView = new ConstructionView(this);
    }
    
    /**
     * Clears and rest all options to their deault cases
     * 
     */
    public void clear () {
        
        model.clear();
        constructionView.clear();
    }
    
    /**
     * Creates new wall
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     */
    public void newWall () {
        model.newWall();
    }
    
    /**
     * Gets number of walls which is the size of walls {@link WallModel} {@link ArrayList}
     * 
     *    **** THIS IS CONNECTING METHOD ****
     * 
     * @return {@link WallModel} {@link ArrayList} size
     */
    public int getNumberOfWalls() {
        
        return model.getNumberOfWalls();
    }
    
    /**
     * Removes the last element in {@link WallModel} {@link ArrayList}
     * 
     *     **** THIS IS CONNECTING METHOD ****
     * 
     */
    public void removeLast() {
        model.removeLast();
    }
    
    /**
     * Updates text resalut and show it to user
     * 
     *     **** THIS IS CONNECTING METHOD ****
     * 
     * 
     */
    public void updateResult () {
     
        model.updateResult();
    }
    /**
     * Sets and validate job identiﬁer that entered by user.
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @param jobIdentiﬁer - user input job identiﬁer
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setJobIdentiﬁer (String jobIdentiﬁer) {
        return model.setJobIdentiﬁer(jobIdentiﬁer);
    }
    
    /**
     * Sets and validate wall length that entered by user.
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @param index - wall index in the {@link ArrayList}
     * @param wallLength - user input wall length
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setWallLength(int index, String wallLength) {
        return model.setWallLength(index, wallLength);
    }
    
    /**
     * Sets and validate start height that entered by user.
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @param index - wall index in the {@link ArrayList}
     * @param startHeight - user input start height
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setStartHeight(int index, String startHeight) {
        return model.setStartHeight(index, startHeight);
    }
    
    /**
     * Sets and validate end height that entered by user.
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @param index - wall index in the {@link ArrayList}
     * @param endHeight - user input end height
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setEndHeight(int index, String endHeight) {
        return model.setEndHeight(index, endHeight);
    }
    
    /**
     * Sets and validate end height that entered by user.
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @param index - model index in the {@link ArrayList}
     * @param angle - user input wall angle
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setWallAngle(int index, String angle) {
        
        return model.setWallAngle(index, angle);
    }
    
    /**
     * Sets and validate wall location that entered by user.
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @param location - user input wall location
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setLocation (String location) {
        return model.setLocation(location);
    }
    
    /**
     * Sets and validate wall location accessibility that entered by user.
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @param accessibility - user input wall location accessibility
     * 
     * @return {@link Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setAccessibility(String accessibility) {
        return model.setAccessibility(accessibility);
    }
    
    /**
     * Sets and validate wall location difficulty that entered by user.
     * 
     *  **** THIS IS CONNECTING METHOD **** 
     * 
     * @param difficulty - user input wall location difficulty
     * 
     * @return {@linl Boolean} validation result, true if input is valid and false otherwise.
     */
    public boolean setDifficulty(String difficulty) {
        return model.setDifficulty(difficulty);
    }
    
    /**
     * Invokes to display wall calculation result
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @param text - result text to be displayed in result {@link JLabel}
     */
    public void setResultText (String text) {
        constructionView.getResultPanel().setResultText(text);
    }
    
    /**
     * Adds extra sleepers that user added in the run time
     * 
     *    **** THIS IS CONNECTING METHOD ****
     * 
     * @param index - wall index in the {@link ArrayList}
     * @param bay - {@link Integer} bay index in bay Array
     * @param extraSleepers - {@link Integer} number of extra sleepers
     * 
     */
    public void addExtraSleppers (int index, int bay , int extraSleepers) {
        
        model.addExtraSleppers(index, bay, extraSleepers);
    }
    
    /**
     * Gets  job identiﬁer variable
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @return {@link String} wall job identiﬁer
     */
    public String getJobIdentiﬁer() {
        return model.getJobIdentiﬁer();
    }

    /**
     * Gets wall length variable
     * 
     * @param index - wall index in the {@link ArrayList}
     * @return {@link Double} wall length
     */
    public double getWallLength(int index) {
        return model.getWallLength(index);
    }

    /**
     * Gets wall start height variable
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @param index - wall index in the {@link ArrayList}
     * 
     * @return {@link Integer} wall start height
     */
    public int getStartHeight(int index) {
        return model.getStartHeight(index);
    }

    /**
     * Gets wall end height variable
     * 
     * @param index - wall index in the {@link ArrayList}
     * 
     * @return {@link Integer} wall end height
     */
    public int getEndHeight(int index) {
        return model.getEndHeight(index);
    }

    /**
     * Gets wall end height variable
     * 
     * @param index - model index in the {@link ArrayList}
     * 
     * @return {@link Double} wall angle
     */
    public double getWallAngle(int index) {
        
        return model.getWallAngle(index);
    }
    
    /**
     * Gets wall location variable
     * It returns true for Yes and false for No
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @return {@link Boolean} wall location code
     */
    public boolean getLocation() {
        return model.getLocation();
    }

    /**
     * Gets location accessibility variable
     * It returns true for Yes and false for No
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @return {@link Boolean} location accessibility code
     */
    public boolean getAccessibility() {
        return model.getAccessibility();
    }

    /**
     * Gets difficulty variable
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     * @return {@link Character} difficulty code 
     */
    public char getDifficulty() {
        return model.getDifficulty();
    }
    
    /**
     * Gets bays array
     * 
     *  **** THIS IS CONNECTING METHOD ****
     * 
     * @param index - wall index in the {@link ArrayList}
     * 
     * @return {@link Integer} array contains bays 
     */
    public int[] geBays(int index) {
        return model.geBays(index);
    }
    
    /**
     * Executes calculation process to get sleepers distribution.
     * 
     *  **** THIS IS CONNECTING METHOD ****
     * 
     * @param index - model index in the {@link ArrayList}
     * 
     */
    public void process(int index) {
        model.process(index);
    }
    
    /**
     * Adds result {@link JPanel} to main {@link JFrame} in the run time
     * 
     *   **** THIS IS CONNECTING METHOD ****
     * 
     */
    public void showresult () {
        constructionView.showresult();
    }
    
    /**
     * Gets wall text result
     * 
     *    **** THIS IS CONNECTING METHOD ****
     * 
     * @return {@link String} wall result in text format
     */
    public String getResultText () {
        
        return model.getResultText();
    }
    
    /**
     * Invokes draw the specified wall
     * 
     *  **** THIS IS CONNECTING METHOD ****
     * 
     */
    public void paintWall() {
        constructionView.getResultPanel().paintWall();
    }
    
    /**
     * Informs this method that there is a wall has been removed
     * 
     *  **** THIS IS CONNECTING METHOD ****
     * 
     */
    public void wallRemoved () {
        constructionView.wallRemoved();
    }
}
