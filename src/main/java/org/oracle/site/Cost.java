package org.oracle.site;


/**
 * Calculates cost of clearing land
 */
public class Cost {

    private static int totalSquares;
    private static int totalProtectedTrees;
    private int fuelCost;
    private int legalPenaltyCost;
    private int legalPenaltyQty;
    private int paintDamageCost;
    private int paintDamageQty;
    private int commCost;
    private int squaresCleared;

    public static int getTotalSquares() {
        return Cost.totalSquares;
    }

    public static void setTotalSquares(int totalSquares) {
        Cost.totalSquares = totalSquares;
    }

    public static int getTotalProtectedTrees() {
        return Cost.totalProtectedTrees;
    }

    public static void setTotalProtectedTrees(int totalProtectedTrees) {
        Cost.totalProtectedTrees = totalProtectedTrees;
    }

    /**
     * Increment Fuel Cost upon clearing a single square of land.
     * Invoke {@link Cost#calculateLegalPenalty()} if square is of type {@link ProtectedSquare}
     *
     * @param squareType String representation of object of type{@link Square}
     */
    public void updateCost(String squareType) {
        if (squareType.equals(PlainSquare.class.getName())) fuelCost++;
        else if (squareType.equals(TreeSquare.class.getName())) fuelCost += 2;
        else if (squareType.equals(RockySquare.class.getName())) fuelCost += 2;
        else if (squareType.equals(ProtectedSquare.class.getName())) calculateLegalPenalty();
    }

    /**
     * Increment Legal Penalty caused from an attempt to clear protected tree square.
     */
    private void calculateLegalPenalty() {
        this.legalPenaltyCost += 10;
        this.legalPenaltyQty++;
    }

    /**
     * Increment Communication Cost
     */
    public void communicationCost() {
        this.commCost++;
    }

    /**
     * Increment Paint Damage
     */
    public void paintDamage() {
        this.paintDamageQty++;
        this.paintDamageCost += 2;
    }

    /**
     * Increment Total Squares cleared
     */
    public void updateTotalCleared() {
        this.squaresCleared++;
    }

    /**
     * Calculate Uncleared Squares by subtracting Total Cleared and Total Protected Squares from Total Squares on the site.
     */
    private int unclearedSquares() {
        return totalSquares - squaresCleared - totalProtectedTrees;
    }

    /**
     * Calculate cost of Uncleared Squares.
     */
    private int unclearedSquaresCost() {
        return unclearedSquares() * 3;
    }

    /**
     * Calculate total cost of operation which is a summation of:
     * <pre>
     *     Communication cost
     *     Fuel cost
     *     Uncleared Squares cost
     *     Legal Penalty cost
     *     Paint Damage cost
     * </pre>
     */
    public int calculateTotalCost() {
        return commCost + fuelCost + unclearedSquaresCost() + legalPenaltyCost + paintDamageCost;
    }

    public int getFuelCost() {
        return fuelCost;
    }

    public int getLegalPenaltyCost() {
        return legalPenaltyCost;
    }

    public int getLegalPenaltyQty() {
        return legalPenaltyQty;
    }

    public int getCommCost() {
        return commCost;
    }

    public int getSquaresCleared() {
        return squaresCleared;
    }

    public int getPaintDamageCost() {
        return paintDamageCost;
    }

    public int getPaintDamageQty() {
        return paintDamageQty;
    }

    /**
     * Prints a table providing itemized costs of the clearing operation and total cost.
     */
    public void finalCostTable() {

        System.out.println("\nThe costs for this land clearing operation were:\n");

        //Heading//
        System.out.printf("%-40s", "No. Item"); // "Quantity-5%f" + "Cost=5%f"
        System.out.printf("%-15s", "Quantity");
        System.out.printf("%-15s %n", "Cost");

        //Communication//
        System.out.printf("%-40s", "1. Communication Overhead");
        System.out.printf("%-15d", commCost);
        System.out.printf("%-15d %n", commCost);

        //Fuel//
        System.out.printf("%-40s", "2. Fuel Usage");
        System.out.printf("%-15d", fuelCost);
        System.out.printf("%-15d %n", fuelCost);

        //Uncleared Squares//
        System.out.printf("%-40s", "3. Uncleared Squares");
        System.out.printf("%-15d", unclearedSquares());
        System.out.printf("%-15d %n", unclearedSquaresCost());

        //Destruction of protected tree//
        System.out.printf("%-40s", "4. Destruction of protected tree");
        System.out.printf("%-15d", legalPenaltyQty);
        System.out.printf("%-15d %n", legalPenaltyCost);

        //Paint Damage to Bulldozer//
        System.out.printf("%-40s", "5. Paint Damage to Bulldozer");
        System.out.printf("%-15d", paintDamageQty);
        System.out.printf("%-15d %n", paintDamageCost);

        //Total//
        System.out.println("_____");
        System.out.println();
        System.out.printf("%-55s", "Total");
        System.out.printf("%-15d", calculateTotalCost());

        System.out.println(System.getProperty("line.separator"));

    }

    @Override
    public String toString() {
        return "Cost{" +
                "fuelCost=" + fuelCost +
                ", legalPenaltyCost=" + legalPenaltyCost +
                ", legalPenaltyQty=" + legalPenaltyQty +
                ", paintDamageCost=" + paintDamageCost +
                ", paintDamageQty=" + paintDamageQty +
                ", commCost=" + commCost +
                ", totalSquares=" + totalSquares +
                ", totalProtectedTrees=" + totalProtectedTrees +
                ", squaresCleared=" + squaresCleared +
                '}';
    }


}
