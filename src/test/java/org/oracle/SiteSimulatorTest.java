package org.oracle;

import org.junit.Before;
import org.junit.Test;
import org.oracle.receiver.Bulldozer;
import org.oracle.receiver.Direction;
import org.oracle.site.*;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple SiteSimulator.
 */
public class SiteSimulatorTest
{
    private SiteSimulator siteSimulator;

    private Bulldozer bulldozer;


    @Before
    public void setup() {

        Square[][] siteMap = new Square[][]{
                {new PlainSquare(), new TreeSquare(), new PlainSquare(), new ProtectedSquare()},
                {new TreeSquare(), new RockySquare(), new TreeSquare(), new ProtectedSquare()},
                {new PlainSquare(), new RockySquare(), new RockySquare(), new ProtectedSquare()},
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new ProtectedSquare()},
                {new TreeSquare(), new RockySquare(), new TreeSquare(), new PlainSquare()}
        };

        siteSimulator = new SiteSimulator(siteMap);
        bulldozer = siteSimulator.getBulldozer();
        Cost.setTotalProtectedTrees(ConstructionSite.countProtectedTrees(siteMap));
    }


  @Test
    public void test_advance_end_to_end(){
      siteSimulator.executeCommand("Advance 3");
      assertEquals(bulldozer.getX(), 2);
      assertEquals(bulldozer.getY(), 0);
      assertEquals(bulldozer.getFacingDirection(), Direction.EAST);
      assertEquals(bulldozer.getCost().getCommCost(), 1);
      assertEquals(bulldozer.getCost().getFuelCost(), 4);
      assertEquals(bulldozer.getCost().calculateTotalCost(),  46);
      assertEquals(bulldozer.getCost().getPaintDamageQty(),  1);
      assertEquals(bulldozer.getCost().getPaintDamageCost(), 2);
  }

    @Test
    public void test_left_end_to_end() {
        siteSimulator.executeCommand("Advance 3");
        siteSimulator.executeCommand("Left");
        assertEquals(bulldozer.getX(), 2);
        assertEquals(bulldozer.getY(), 0);
        assertEquals(bulldozer.getFacingDirection(), Direction.NORTH);
        assertEquals(bulldozer.getCost().getCommCost(), 2);
        assertEquals(bulldozer.getCost().getFuelCost(), 4);
        assertEquals(bulldozer.getCost().calculateTotalCost(),  47);
        assertEquals(bulldozer.getCost().getPaintDamageQty(),  1);
        assertEquals(bulldozer.getCost().getPaintDamageCost(), 2);
    }

    @Test
    public void test_right_end_to_end() {
        siteSimulator.executeCommand("Advance 3");
        siteSimulator.executeCommand("Right");
        assertEquals(bulldozer.getX(), 2);
        assertEquals(bulldozer.getY(), 0);
        assertEquals(bulldozer.getFacingDirection(), Direction.SOUTH);
        assertEquals(bulldozer.getCost().getCommCost(), 2);
        assertEquals(bulldozer.getCost().getFuelCost(), 4);
        assertEquals(bulldozer.getCost().calculateTotalCost(),  47);
        assertEquals(bulldozer.getCost().getPaintDamageQty(),  1);
        assertEquals(bulldozer.getCost().getPaintDamageCost(), 2);
    }


}
