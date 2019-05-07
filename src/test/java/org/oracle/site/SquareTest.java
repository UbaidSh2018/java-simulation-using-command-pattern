package org.oracle.site;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SquareTest {

    @Test
    public void should_return_PlainSquareObj_for_char_o() {
        Square result = Square.squareFor('o');
        assertThat(result, instanceOf(PlainSquare.class));
    }

    @Test
    public void should_return_TreeSquareObj_for_char_t() {
        Square result = Square.squareFor('t');
        assertThat(result, instanceOf(TreeSquare.class));
    }

    @Test
    public void should_return_RockySquareObj_for_char_r() {
        Square result = Square.squareFor('r');
        assertThat(result, instanceOf(RockySquare.class));

    }

    @Test
    public void should_return_ProtectedSquareObj_for_char_T() {
        Square result = Square.squareFor('T');
        assertThat(result, instanceOf(ProtectedSquare.class));

    }

    @Test
    public void test_Plain_toString() {
        PlainSquare square = new PlainSquare();
        assertEquals("Plain", square.toString());
    }

    @Test
    public void test_Rocky_toString() {
        RockySquare square = new RockySquare();
        assertEquals("Rocky", square.toString());
    }

    @Test
    public void test_Tree_toString() {
        TreeSquare square = new TreeSquare();
        assertEquals("Tree", square.toString());
    }

    @Test
    public void test_Protected_toString() {
        ProtectedSquare square = new ProtectedSquare();
        assertEquals("Protected", square.toString());
    }
}
