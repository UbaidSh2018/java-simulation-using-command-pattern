package org.oracle.receiver;

import org.apache.log4j.Logger;
import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.io.FileOutput;
import org.oracle.io.Message;
import org.oracle.site.ConstructionSite;
import org.oracle.site.Cost;
import org.oracle.site.Square;

import static java.lang.String.format;
import static org.oracle.receiver.Direction.*;


/**
 * Bulldozer defines the commands and movements available to the bulldozer.
 */
public class Bulldozer {

    private final static Logger LOGGER = Logger.getLogger(Bulldozer.class);

    private Grid grid;

    private int x;

    private int y;

    private Direction facingDirection;

    private ConstructionSite site;

    private Cost cost;

    private boolean onGrid;

    private int destX;

    private int destY;

    public Bulldozer(Square[][] siteMap) {
        x = -1;
        y = 0;
        onGrid = false;
        facingDirection = EAST;
        int width = siteMap[0].length;
        int height = siteMap.length;
        this.grid = new Grid(width, height);
        this.site = new ConstructionSite(siteMap);
        this.cost = new Cost();
        Cost.setTotalSquares(width * height);
        Cost.setTotalProtectedTrees(ConstructionSite.countProtectedTrees(siteMap));
        LOGGER.info("Bulldozer Initialized");
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDestX() {
        return destX;
    }

    private void setDestX(int destX) {
        this.destX = destX;
    }

    public int getDestY() {
        return destY;
    }

    private void setDestY(int destY) {
        this.destY = destY;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public boolean isOnGrid() {
        return onGrid;
    }

    public void setOnGrid(boolean onGrid) {
        this.onGrid = onGrid;
    }

    public ConstructionSite getSite() {
        return site;
    }

    public Cost getCost() {
        return cost;
    }

    /**
     * Invoke {@link #advanceOneSquare()} function as many times as the param.
     *
     * @param param number of times to invoke
     * @throws InvalidBulldozerMovementException
     */
    private void invokeAdvanceOneSquare(int param) throws InvalidBulldozerMovementException {
        for (int i = 0; i < param; i++) {
            advanceOneSquare();
        }
    }

    /**
     * Move the bulldozer one square forward, in the direction it is currently facing.
     */
    public void advanceOneSquare()
            throws InvalidBulldozerMovementException {
        LOGGER.trace("Received advance one square instruction.");
        validateBulldozerIsOnGrid();
        int newX = getX();
        int newY = getY();
        switch (facingDirection) {
            case NORTH:
                newY = getY() - 1;
                break;
            case EAST:
                newX = getX() + 1;
                break;
            case SOUTH:
                newY = getY() + 1;
                break;
            case WEST:
                newX = getX() - 1;
                break;
            default:
                throw new IllegalStateException("Unrecognized Direction!");
        }
        validPositionAction(newX, newY);
    }

    /**
     * Validate new position. Clear the land and update costs.
     * If the new location holds a ProtectedSquare, call {@link #protectedSquareAction(int, int)}.
     * If the new location holds a TreeSquare and is not the destination position, then pain damage incurs.
     *
     * @param newX next X co-ordinate of bulldozer
     * @param newY next Y co-ordinate of bulldozer
     */
    private void validPositionAction(int newX, int newY) {
        if ((newX != destX || newY != destY) && (site.isTreeSquare(newY, newX))) {
            cost.paintDamage();
            LOGGER.info("Paint damage occurred");
        }
        if (site.isProtectedSquare(newY, newX)) {
            LOGGER.info("This is a Protected Square");
            protectedSquareAction(newX, newY);
        }
        LOGGER.info("Advancing to next square on grid.");
        setX(newX);
        setY(newY);
        cost.updateCost(site.squareType(y, x));
        site.clearLand(y, x);
        cost.updateTotalCleared();
    }


    /**
     * If the new location holds a ProtectedSquare, update legal costs and end the simulation.
     *
     * @param newX next X co-ordinate of bulldozer
     * @param newY next Y co-ordinate of bulldozer
     */
    private void protectedSquareAction(int newX, int newY) {
        setX(newX);
        setY(newY);
        cost.updateCost(site.squareType(newY, newX));
        LOGGER.info("Terminating simulation for trying to clear protected land.");
        quit();
    }


    /**
     * Validate the destination position. If it's valid, update destX , destY and invoke {@link #invokeAdvanceOneSquare(int)},
     * otherwise end simulation for an attempt to cross {@link Grid} boundary.
     *
     * @param n number of times to advance
     * @throws InvalidBulldozerMovementException
     */
    public void advance(int n)
            throws InvalidBulldozerMovementException {
        LOGGER.trace("Received Advance Instruction. Check Valid destination.");
        validateBulldozerIsOnGrid();
        int newX = getX(); // starts with 0 , 0
        int newY = getY();
        switch (facingDirection) {
            case NORTH:
                newY = getY() - n;
                break;
            case EAST:
                newX = getX() + n;
                break;
            case SOUTH:
                newY = getY() + n;
                break;
            case WEST:
                newX = getX() - n;
                break;
            default:
                throw new IllegalStateException("Unrecognized Direction!");
        }

        if (grid.isValidPosition(newX, newY)) {
            setDestX(newX);
            setDestY(newY);
            cost.communicationCost();
            invokeAdvanceOneSquare(n);
        } else {
            cost.communicationCost();
            LOGGER.info("Terminating Simulation: Bulldozer attempted to navigate beyond boundary");
            quit();
        }
    }


    /**
     * Turn the bulldozer 90 degree to the left. Bulldozer will stay in the current location.
     *
     * @throws InvalidBulldozerMovementException
     */
    public void turnLeft() throws InvalidBulldozerMovementException {
        LOGGER.trace("Received turn left instruction");
        validateBulldozerIsOnGrid();
        LOGGER.info("Turning left");
        switch (facingDirection) {
            case NORTH:
                setFacingDirection(WEST);
                cost.communicationCost();
                break;
            case WEST:
                setFacingDirection(SOUTH);
                cost.communicationCost();
                break;
            case SOUTH:
                setFacingDirection(EAST);
                cost.communicationCost();
                break;
            case EAST:
                setFacingDirection(NORTH);
                cost.communicationCost();
                break;
            default:
                throw new IllegalStateException("Unrecognized Direction");
        }
    }


    /**
     * Turn the bulldozer 90 degree to the right. Bulldozer will stay in the current location.
     *
     * @throws InvalidBulldozerMovementException
     */
    public void turnRight() throws InvalidBulldozerMovementException {
        LOGGER.trace("Received turn right instruction");
        validateBulldozerIsOnGrid();
        LOGGER.info("Turning right");
        switch (facingDirection) {
            case NORTH:
                setFacingDirection(EAST);
                cost.communicationCost();
                break;
            case EAST:
                setFacingDirection(SOUTH);
                cost.communicationCost();
                break;
            case SOUTH:
                setFacingDirection(WEST);
                cost.communicationCost();
                break;
            case WEST:
                setFacingDirection(NORTH);
                cost.communicationCost();
                break;
            default:
                throw new IllegalStateException("Unrecognized Direction");
        }
    }

    private void validateBulldozerIsOnGrid() throws InvalidBulldozerMovementException {
        if (!isOnGrid()) {
            throw new InvalidBulldozerMovementException("Bulldozer is not on Site.");
        }
    }

    /**
     * @return Bulldozer location & facing direction
     */
    public String getCurrentPosition() {
        if (isOnGrid()) {
            return format("%d,%d,%s", getX(), getY(), getFacingDirection());
        } else {
            return "Bulldozer is not on site yet";
        }
    }


    /**
     * Ends simulation.
     * Invokes {@link Cost#finalCostTable()} to display cost of clearing.
     */
    public void quit() {
        System.out.println(Message.END_SIMULATION);
        FileOutput.printCommandsEntered();
        cost.finalCostTable();
        System.out.println(Message.THANK_YOU);
        System.exit(1);
    }

    /**
     * Invokes {@link Help#simulationHelp()} to display help.
     */
    public void help() {
        Help.simulationHelp();
    }

}
