package org.oracle.site;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CostTest {

    private Cost cost;

    @Before
    public void setUp() {
        cost = new Cost();
        Cost.setTotalSquares(50);
        Cost.setTotalProtectedTrees(3);
    }

    @Test
    public void fuel_cost_increment_by_1_if_square_is_plain() {
        cost.updateCost("org.oracle.site.PlainSquare");
        int result = cost.getFuelCost();
        assertEquals(1,result);
    }

    @Test
    public void fuel_cost_increment_by_2_if_square_is_tree() {
        cost.updateCost("org.oracle.site.TreeSquare");
        int result = cost.getFuelCost();
        assertEquals(2,result);
    }

    @Test
    public void fuel_cost_increment_by_2_if_square_is_rocky() {
        cost.updateCost("org.oracle.site.RockySquare");
        int result = cost.getFuelCost();
        assertEquals(2,result);
    }

    @Test
    public void legal_penalty_increment_if_square_is_protected() {
        cost.updateCost("org.oracle.site.ProtectedSquare");
        int resultLegalPenalty = cost.getLegalPenaltyCost();
        int resultPenaltyQty = cost.getLegalPenaltyQty();
        assertEquals(10,resultLegalPenalty);
        assertEquals(1,resultPenaltyQty);
    }

    @Test
    public void communication_overhead_increment_by_1() {
        cost.communicationCost();
        int result = cost.getCommCost();
        assertEquals(1,result);
    }

    @Test
    public void increment_totalCleared_land_by_1() {
        cost.updateTotalCleared();
        int result = cost.getSquaresCleared();
        assertEquals(1,result);
    }

    @Test
    public void increment_paint_damage_cost_by_2() {
        cost.paintDamage();
        int paintDamageCost = cost.getPaintDamageCost();
        int paintDamageQty = cost.getPaintDamageQty();
        assertEquals(2,paintDamageCost);
        assertEquals(1,paintDamageQty);
    }

    @Test
    public void return_total_cost_of_operation(){
        cost.updateCost("org.oracle.site.PlainSquare");
        cost.updateCost("org.oracle.site.TreeSquare");
        cost.updateCost("org.oracle.site.RockySquare");
        cost.updateCost("org.oracle.site.ProtectedSquare");
        cost.communicationCost();
        cost.paintDamage();
        int unclearedCost = Cost.getTotalSquares() - cost.getSquaresCleared() - Cost.getTotalProtectedTrees();

        int expected = cost.getFuelCost()+ cost.getCommCost() + (unclearedCost*3) +cost.getLegalPenaltyCost()+ cost.getPaintDamageCost();
        int result = cost.calculateTotalCost();

        assertEquals(expected,result);
    }


}