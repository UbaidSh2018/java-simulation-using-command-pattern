package org.oracle.io;

import org.junit.Before;
import org.junit.Test;
import org.oracle.site.*;

import static org.junit.Assert.*;

public class FileReaderTest {

    private Square[][] expected;

    @Before
    public void setup() {
        expected = new Square[][]{
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new ProtectedSquare()},
                {new TreeSquare(), new RockySquare(), new TreeSquare(), new ProtectedSquare()},
                {new PlainSquare(), new RockySquare(), new RockySquare(), new ProtectedSquare()},
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new ProtectedSquare()},
                {new TreeSquare(), new RockySquare(), new TreeSquare(), new PlainSquare()}
        };
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_file_does_not_exist() {
        FileReader.createSiteFromInputFile("src/test/resources/no_file");
    }

    @Test
    public void should_return_2d_array_of_square_objects() {
        Square[][] result = FileReader.createSiteFromInputFile("src/test/resources/test_file_reader_array");
        assertArraysEquals(result);
    }

    @Test
    public void should_be_able_to_filter_leading_empty_lines() {
        Square[][] result = FileReader.createSiteFromInputFile("src/test/resources/test_file_empty_lines");
        assertArraysEquals(result);
    }

    @Test
    public void should_return_true_if_file_exists() {
        boolean result = FileReader.isFileValid("src/test/resources/map");
        assertTrue(result);
    }

    @Test
    public void should_return_false_if_file_is_empty() {
        boolean result = FileReader.isFileValid("src/test/resources/empty_file");
        assertFalse(result);
    }

    @Test
    public void should_return_false_if_file_does_not_exist() {
        boolean result = FileReader.isFileValid("src/test/resources/no_file");
        assertFalse(result);
    }

    private void assertArraysEquals(Square[][] result) {
        assertEquals(expected.length, result.length);
        assertEquals(expected[0][0].getClass(), result[0][0].getClass());
        assertEquals(expected[0][3].getClass(), result[0][3].getClass());
        assertEquals(expected[3][3].getClass(), result[3][3].getClass());
    }

}
