package evilspirit;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A Wall segment, contains a length, start height, end height and absolute angle of the segment.
 * Contains methods for dealing with bays.
 * Measurements are contained internally in mm, and converted to meters for output. It expects meters as input.
 */
public class Wall {
    private final SideBar _sideBar;
    private final MainGui _gui;
    private double _wallLength; //stored internally in mm,
    private double _wallStart;
    private double _wallEnd;
    private LinkedList<Bay> bays;
    private double _absoluteAngle = 0;

    //input in meters, stored internally in mm (for drawing purposes)
    public Wall(MainGui gui, double length, double startHeight, double endHeight, double angle) {
        _wallLength = length * 1000;
        _wallStart = startHeight * 1000;
        _wallEnd = endHeight * 1000;
        _absoluteAngle = angle;
        _sideBar = new SideBar(this, gui);
        _gui = gui;
        update();
    }

    void generateBays() {    //(re)generate the requisite number of bays for length of wall.
        bays = new LinkedList<Bay>();   //create a fresh linked list of bays, old ones are discarded
        ArrayList<Integer> sleepersList = Calculator.calcBayHeight(this);

        for (Integer i : sleepersList) {
            createBay(i);
        }
        _wallLength = sleepersList.size() * 2000;       //redefine length of wall as the number of 2 meter bays it contains.
    }

    //create a new bay in the wall with a set number of sleepers
    void createBay(int numSleepers) {
        bays.addLast(new Bay(numSleepers));
    }


    public int getNumSleepers() {
        int totalSleepers = 0;
        for (Bay b : bays) {
            totalSleepers += b.getNumSleepers();
        }
        return totalSleepers;
    }

    public double getLength() {
        return _wallLength;
    }

    public void setLength(double length) {

        _wallLength = length * 1000;
    }

    public double getLengthMeters() {
        return (_wallLength / 1000);
    }

    public double getStartHeight() {
        return _wallStart / 1000;
    }

    public void setStartHeight(double height) {
        _wallStart = height * 1000;
    }

    public double getEndHeight() {
        return _wallEnd / 1000;
    }

    public void setEndHeight(double height) {
        _wallEnd = height * 1000;
    }

    public double getAngle() {
        return _absoluteAngle;
    }

    public void setAngle(double angle) {
        _absoluteAngle = angle;
        //update();
    }

    public void destruct() {        //removes the walls tab, wall can now be deleted
        _gui.removeSidePanel(_sideBar);
    }   //removes related side tab before removal of this

    public void update() {
        generateBays();
        _sideBar.update();      //update the gui to reflect the calculated changes
    }

}
