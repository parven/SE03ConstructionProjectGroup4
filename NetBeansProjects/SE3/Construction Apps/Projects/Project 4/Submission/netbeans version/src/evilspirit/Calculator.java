package evilspirit;

import java.util.ArrayList;


/**
 * Contains static methods for calculating the angle of a wall, given the start and end heights of a wall
 * Converts this angle, with a length to determine the number of sleepers in each bay
 */
public class Calculator {

    //static methods for calculating number of sleepers and cost

    private static double CalcAngle(Wall w) {
        double base = w.getLengthMeters() - 2;
        double height = w.getEndHeight() - w.getStartHeight();
        return Math.atan(height / base);
    }

    public static ArrayList<Integer> calcBayHeight(Wall w) {
        ArrayList<Integer> sleepers = new ArrayList<Integer>();

        double angle = CalcAngle(w);
        double base = w.getLengthMeters() - 2;
        double sheight = w.getStartHeight();

        for (double x = 0; x <= base; x += 2) {
            double bayHeight = (Math.tan(angle) * x) + sheight;
            int currentSleepers = (int) Math.round((bayHeight / 0.2));
            sleepers.add(currentSleepers);
        }

        return sleepers;

    }

}
