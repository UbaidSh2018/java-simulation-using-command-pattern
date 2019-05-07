package org.oracle;

import org.apache.log4j.Logger;
import org.oracle.command.Command;
import org.oracle.command.CommandParser;
import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.exceptions.InvalidCommandException;
import org.oracle.io.CommandReader;
import org.oracle.io.FileOutput;
import org.oracle.io.FileReader;
import org.oracle.receiver.Bulldozer;
import org.oracle.site.Square;

import java.io.IOException;

/**
 * Main class of the Application.
 */
public class SiteSimulator {

    private static final Logger LOGGER = Logger.getLogger(CommandParser.class);

    private Bulldozer bulldozer;
    private CommandReader commandReader;

    SiteSimulator(Square[][] siteMap) {
        bulldozer = new Bulldozer(siteMap);
        commandReader = new CommandReader(System.in);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            run(args[0]);
        } else {
            System.out.println("Please pass one argument");
        }
    }

    /**
     * Runs the simulation.
     *
     * @param filePath path of file to read to create site map of type {@link Square}
     */
    private static void run(String filePath) {
        if (FileReader.isFileValid(filePath)) {
            FileOutput.printSiteMap(filePath);
            FileOutput.printBulldozerInfo();
            Square[][] siteMap = FileReader.createSiteFromInputFile(filePath);
            SiteSimulator siteSimulator = new SiteSimulator(siteMap);
            try {
                siteSimulator.getCommand();
            } catch (IOException e) {
                System.err.println("Error in receiving command from input stream. Check logs for details ");
                LOGGER.debug("Error in receiving command from input stream.");
                LOGGER.debug(e.getMessage());
                System.exit(-1);
            }
        } else {
            System.err.println("Please check your file again. It is either invalid or empty.");
            System.err.println("Terminating Simulation...");
            LOGGER.info("Terminating Simulation: File invalid or Empty");
            System.exit(-1);
        }
    }

    /**
     * Create {@link Command } object to execute command passed as string on the {@link Bulldozer}
     *
     * @param commandString command string
     */
    public void executeCommand(String commandString) {
        try {
            Command command = new CommandParser().createCommand(commandString); // setting the command object here to call the right command object
            command.execute(bulldozer); // calling execute command
        } catch (InvalidBulldozerMovementException e) {
            System.err.println("Bulldozer not on Site. To place it on site, enter 'Advance <n>' command or 'Quit' to end Simulation");
        } catch (InvalidCommandException e) {
            LOGGER.debug("Invalid Command");
            LOGGER.debug(e.getMessage());
            System.err.println("Invalid Command!");
        }
    }


    /**
     * Reads input from the terminal and invokes {@link SiteSimulator#executeCommand(String)}
     *
     * @throws IOException Throws IOException
     */
    public void getCommand() throws IOException {
        try {
            String commandString;
            while ((commandString = commandReader.getNextCommand()) != null) {
                if (!commandString.isEmpty()) {
                    executeCommand(commandString);
                } else System.err.println("Empty Command");
            }
        } finally {
            commandReader.close();
        }

    }

    public Bulldozer getBulldozer() {
        return bulldozer;
    }
}
