package org.oracle.io;


import org.apache.log4j.Logger;
import org.oracle.site.Square;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Class to read content of a file and create a site map of {@link Square} objects from it.
 */
public class FileReader {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(FileReader.class);

    /**
     * Create a 2D Array of {@link Square} objects for site clearing.
     * The method will read each character from the file (ignoring spaces)
     * and create objects accordingly.
     * <pre>
     * 'o' creates {@link org.oracle.site.PlainSquare} object.
     * 't' creates {@link org.oracle.site.TreeSquare} object.
     * 'r' creates {@link org.oracle.site.RockySquare} object.
     * 'T' creates {@link org.oracle.site.ProtectedSquare} object.
     * </pre>
     *
     * @param filePath location on file to read.
     * @return A 2D Array of type Square
     */
    public static Square[][] createSiteFromInputFile(String filePath) {
        Square[][] squares;
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            squares = stream
                    .filter(line -> !line.isEmpty())
                    .map(line -> line.chars()
                            .filter(ch -> ch != ' ')
                            .mapToObj(Square::squareFor)
                            .toArray(Square[]::new))
                    .toArray(Square[][]::new);
        } catch (IOException e) {
            LOGGER.debug("Failed to create site map from file");
            throw new IllegalArgumentException("Error in creating site from file");
        }
        return squares;
    }


    /**
     * @param filepath location of file to read.
     * @return <code>true</code> if the file is valid and is not empty
     * <code>false</code> otherwise
     */
    public static boolean isFileValid(String filepath) {
        File file = new File(filepath);
        if (!file.canRead()) {
            return false;
        }
        if (file.exists()) {
            return file.length() != 0;
        } else
            return false;
    }

}
