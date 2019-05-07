package org.oracle.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;


/**
 * Class contains methods to display the output on console.
 */
public class FileOutput {

    private static List<String> userEnteredCommands = new LinkedList<>();


    /**
     * @return The list of successfully executed commands entered by a trainee
     */
    public static List<String> getUserEnteredCommands() {
        return userEnteredCommands;
    }


    /**
     * Display the list of commands entered by a trainee
     */
    public static void printCommandsEntered() {
        System.out.println("\n" + userEnteredCommands);
    }

    private static void printWelcomeMessage() {
        System.out.println(Message.WELCOME);
    }

    public static void printBulldozerInfo() {
        System.out.println(Message.BULLDOZER_INFO);
    }

    /**
     * Reads the file to display site map.
     *
     * @param filePath path of file to read the site map from.
     */
    public static void printSiteMap(String filePath) {
        printWelcomeMessage();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.filter(x -> !x.isEmpty()).forEach(System.out::println);
            System.out.println();
        } catch (IOException e) {
            System.out.println("IO Exception: Failed to read construction site map file");
        }
    }


}
