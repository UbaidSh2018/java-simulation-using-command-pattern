package org.oracle.site;

import org.jetbrains.annotations.NotNull;


public class ConstructionSite {

    private Square[][] siteMap;

    public ConstructionSite(Square[][] siteMap) {
        this.siteMap = siteMap;
    }

    /**
     * Count total protected trees in the site
     *
     * @param site array of site of type {@link Square}
     * @return total number of objects of type ProtectedSquare
     */
    public static int countProtectedTrees(@NotNull Square[][] site) {
        int protectedTrees = 0;
        for (Square[] sq2 : site) {
            for (Square sq : sq2) {
                if (sq.getClass() == ProtectedSquare.class) {
                    protectedTrees++;
                }
            }
        }
        return protectedTrees;
    }

    /**
     * Identifies the Square type in the array.
     *
     * @param row    index
     * @param column index
     * @return string representation of class name of {@link Square} Object at the given array index.
     */
    public String squareType(int row, int column) {
        return siteMap[row][column].getClass().getName();
    }

    /**
     * @param row    index
     * @param column index
     * @return returns true if the {@link Square} at that index is of type {@link ProtectedSquare}.
     */
    public boolean isProtectedSquare(int row, int column) {
        return squareType(row, column).equals(ProtectedSquare.class.getName());
    }

    /**
     * @param row    index
     * @param column index
     * @return returns true if the {@link Square} at that index is of type {@link TreeSquare}.
     */
    public boolean isTreeSquare(int row, int column) {
        return squareType(row, column).equals(TreeSquare.class.getName());
    }

    /**
     * Clear land by replacing the current object with {@link PlainSquare} at the given array index.
     *
     * @param row    index
     * @param column index
     */
    public void clearLand(int row, int column) {
        if (siteMap[row][column].getClass() != PlainSquare.class) {
            siteMap[row][column] = new PlainSquare();
        }
    }


    /**
     * Print the 2D array of type {@link Square}
     */
    private void printGrid() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(siteMap[i][j].toString() + " ");
            }
            System.out.println();
        }
    }

}