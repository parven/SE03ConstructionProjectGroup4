package evilspirit;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 * Assembles data from wall objects into a single Path2D, and draws it within itself
 */
public class DrawingPanel extends JPanel {

    private final Path2D completeWall = new Path2D.Double();
    private final Quote quote;

    public DrawingPanel(Quote q) {
        quote = q;
        q.setDrawingPanel(this);    //tell quote this is where to draw
    }

    /**
     * Apply Translation and Scaling to generated Path2D
     * Dont even look at this method unless you know what a transform is
     */

    void scaleWall() {

        double scaleX = (this.getWidth() - (this.getWidth() / 10)) / completeWall.getBounds().getWidth();
        double scaleY = (this.getHeight() - (this.getHeight() / 10)) / completeWall.getBounds().getHeight();
        AffineTransform transformScale = new AffineTransform();
        AffineTransform transformTranslate = new AffineTransform();
        transformScale.scale(scaleX, scaleY);   //create scaling transform
        completeWall.transform(transformScale); //apply scaling transform

        transformTranslate.translate((      //generate translation transform
                        this.getWidth() / 2) - completeWall.getBounds2D().getCenterX(),
                this.getHeight() / 2 - completeWall.getBounds2D().getCenterY());

        completeWall.transform(transformTranslate); //apply translation transform

    }

    /**
     * Assemble all the wall measurements into a path2D
     */

    public void refreshWallLine() {
        completeWall.reset(); //dump the old path
        Point2D currentPoint = new Point.Double(10, this.getHeight() / 2);
        completeWall.moveTo(currentPoint.getX(), currentPoint.getY()); //move to start

        for (Wall w : quote.getAllWalls()) {    //build the wall
            currentPoint = completeWall.getCurrentPoint();
            Point2D nextPoint = calcVector(w, currentPoint);
            double nextX = nextPoint.getX();
            double nextY = nextPoint.getY();

            completeWall.lineTo(nextX, nextY); //lineTo wont accept a point, which is strange
            completeWall.moveTo(nextX, nextY); //for some stupid reason, the current point doesnt move to the end of the line its just drawn
        }

        scaleWall();    //now scale the wall to fit the drawing space
        repaint();      //Things have changed, now repaint them.
    }

    /**
     * Calculates Slope of line for drawing wall at specified angle
     */
    Point2D calcVector(Wall w, Point2D p) {
        double angle = w.getAngle();
        double rads = Math.toRadians(angle);
        return new Point.Double(p.getX() + Math.cos(rads) * w.getLength(), p.getY() + Math.sin(rads) * w.getLength());
    }

    /**
     * Paints quoted values in the drawing panel
     */
    void DrawQuoteDataInDrawingPane(Graphics2D g) {

        String numberOfSleepersLabel = "Number of Sleepers: ";
        String squareMeterLabel = "Total Square Meters: ";
        String totalCostLabel = "Total Cost: $";
        int heightOfText = (int) this.getPreferredSize().getHeight() - 50;  //get size of panel
        g.drawString(numberOfSleepersLabel + quote.getTotalSleepers(), 20, heightOfText);
        g.drawString(squareMeterLabel + quote.getTotalArea(), numberOfSleepersLabel.length() * 12, heightOfText);
        g.drawString(totalCostLabel + quote.getCost(), (numberOfSleepersLabel.length() + squareMeterLabel.length()) * 12, heightOfText);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(10));
        g2.draw(completeWall);
        g2.setStroke(new BasicStroke(1));


        DrawQuoteDataInDrawingPane(g2);
    }


}
