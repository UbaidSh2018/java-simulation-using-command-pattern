package org.oracle.receiver;

/**
 * Grid represents a 2D grid for the Bulldozer to navigate.
 */
class Grid {

    /**
     * Width of Construction Site
     */
    private final int width;

    /**
     * Height of Construction Site
     */
    private final int height;

    /**
     * Construct grid with the given width & height
     *
     * @param width  Construction Site width
     * @param height Construction Site height
     */
    public Grid(int width, int height) {
        if (width < 0) {
            throw new IllegalArgumentException("Grid width must be greater than 0");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Grid height must be greater than 0");
        }
        this.width = width;
        this.height = height;
    }

    /**
     * @param x x position, zero based
     * @param y y position, zero based
     * @return <code>true</code> if the given coordinate is a valid coordinate with respect to the
     * Construction Site dimensions, <code>false</code> otherwise
     */
    boolean isValidPosition(int x, int y) {
        return isValidXCoordinate(x) && isValidYCoordinate(y);
    }

    private boolean isValidYCoordinate(int y) {
        return y >= 0 && y < height;
    }

    private boolean isValidXCoordinate(int x) {
        return x >= 0 && x < width;
    }


}
