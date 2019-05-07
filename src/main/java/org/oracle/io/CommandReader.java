package org.oracle.io;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class contains method to read commands from input.
 */
public class CommandReader {

    private Scanner scanner;

    public CommandReader(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    /**
     * Advances this scanner past the current line and returns the input that was skipped
     *
     * @return string to be used as a command
     */
    public String getNextCommand() {
        System.out.println("Left, Right, Advance <n>, Quit , Help: ");
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }


}
