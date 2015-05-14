package evilspirit;

import java.util.LinkedList;

/**
 * Effectively the main object in this project
 * Stores all values relevant to the quote, provides methods for accessing and updating these variables
 * Calculations relating to the area / cost of the project are also contained within.
 */


public class Quote {

    private final MainGui _gui;
    private LinkedList<Wall> walls = new LinkedList<Wall>();             //a list of walls
    private Difficulty _difficulty;         //how many difficulties were there again?
    private boolean _local = true;
    private boolean _accessible = true;
    private DrawingPanel drawing;
    private int totalSleepers = 0;

    public Quote(MainGui gui) {
        _gui = gui;
    }

    public int getTotalSleepers() {
        updateTotalSleepers();
        return totalSleepers;
    }

    void updateTotalSleepers() {  //get number of sleepers from walls
        totalSleepers = 0;
        for (Wall w : walls) {
            totalSleepers = w.getNumSleepers();
        }
    }

    public double getTotalArea() {
        final double metersPerSleeper = 0.4;
        return Math.round(getTotalSleepers() * metersPerSleeper);
    }

    public void setDrawingPanel(DrawingPanel d) {   //drawing panel calls this to inform quote where to draw
        drawing = d;
    }

    //add a new wall to the list with required parameters
    public void addWall(double length, double startHeight, double endHeight, double angle) {
        Wall w = new Wall(_gui, length, startHeight, endHeight, angle);
        walls.add(w);
        update();
    }

    public void removeWall(Wall w) {
        if (walls.size() > 1) {
            w.destruct();
            walls.remove(w);
        }
        update();
    }

    /**
     * getter for all walls contained within the quote.
     * Used by the drawing panel
     */
    public LinkedList<Wall> getAllWalls() {
        if (walls.iterator().hasNext()) {
            return walls;
        } else {
            System.out.println("Error: -Quote- No walls in current project");
            return null;
        }
    }

    public boolean getAccessible() {
        return _accessible;
    }

    public void setAccessible(boolean b) {
        _accessible = b;
        System.out.println("Accessible: " + b);
    }

    public boolean getLocal() {
        return _local;
    }

    public void setLocal(boolean b) {
        _local = b;
        System.out.println("Local: " + b);
    }

    public void setDifficulty(Difficulty d) {
        _difficulty = d;
        System.out.println("Difficulty changed to" + d);
    }

    public void update() {
        drawing.refreshWallLine();      //rebuild wall for drawing after parameters have been changed
    }

    /**
     * Calculates the total cost of project based on the area and applied difficulty modifiers
     */
    public double getCost() {
        double cost = (getTotalArea() * 425);
        if (!_local)
            cost = cost * 1.05;
        if (!_accessible)
            cost = cost * 1.3;

        if (_difficulty == Difficulty.SANDY)
            cost = cost * 1.1;
        else if (_difficulty == Difficulty.LIMESTONE)
            cost = cost * 1.15;
        else if (_difficulty == Difficulty.BLUESTONE)
            cost = cost * 1.3;

        return cost;
    }

}
