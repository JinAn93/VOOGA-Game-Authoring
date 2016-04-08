package util;

import java.io.Serializable;


/**
 * Utility class that stores two double values as a coordinate and manipulates them.
 * 
 * @author Dhrumil
 *
 */

public class Coordinate {

    private double xCoordinate;
    private double yCoordinate;

    public Coordinate (double xCor, double yCor) {
        setX(xCor);
        setY(yCor);
    }

    public double getX () {
        return xCoordinate;
    }

    private void setX (double xCor) {
        xCoordinate = xCor;
    }

    public double getY () {
        return yCoordinate;
    }

    private void setY (double yCor) {
        yCoordinate = yCor;
    }

    public void setLocation (double x, double y) {
        setX(x);
        setY(y);

    }

}
