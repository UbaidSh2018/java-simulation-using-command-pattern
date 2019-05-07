package org.oracle.site;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class ConstructionSiteTest {

    private ConstructionSite site;
    private Square[][] siteMap;

    @Before
    public void setUp() {
        siteMap = new Square[][]{
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new ProtectedSquare(), new PlainSquare()},
                {new TreeSquare(), new RockySquare(), new TreeSquare(), new ProtectedSquare(), new ProtectedSquare()},
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new ProtectedSquare(), new PlainSquare()}};
        site = new ConstructionSite(siteMap);
    }

    @Test
    public void return_square_classname_on_passing_array_index(){
        String result = site.squareType(0,0);
        String expected = "org.oracle.site.PlainSquare";
        assertEquals(expected,result);
    }

    @Test
    public void change_object_at_given_index_to_PlainSquare() {
        site.clearLand(0,1);
        Square result = siteMap[0][1];
        assertThat(result, instanceOf(PlainSquare.class));
    }


    @Test
    public void count_total_ProtectedTrees_on_site() {
        int result = ConstructionSite.countProtectedTrees(siteMap);
        assertEquals(4, result);
    }

    @Test
    public void identify_if_the_obj_at_this_index_is_of_type_ProtectedSquare(){
       boolean isProtected = site.isProtectedSquare(0,3);
       assertTrue(isProtected);
    }

    @Test
    public void identify_if_the_obj_at_this_index_is_of_type_TreeSquare(){
        boolean isTree = site.isTreeSquare(1,0);
        assertTrue(isTree);
    }
}