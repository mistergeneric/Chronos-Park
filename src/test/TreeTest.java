package test;

import model.Ride;
import model.tree.Tree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    Ride ride;
    Tree tree;

    @BeforeEach
    void beforeEach() {

        ride = new Ride("test", 0, 5, false, 1, 2, false, false, false, false);
        tree = new Tree(ride);
    }

    @Test
    void checkGroupSize() {
        assertTrue(tree.checkGroupSize(1, 2, 1));
        assertFalse(tree.checkGroupSize(3, 5, 1));
    }

    @Test
    void checkHeight() {
        assertTrue(tree.checkHeight(1, 2, 1.2f, 1.3f));
        assertFalse(tree.checkHeight(1, 2, 0.2f, 2.4f));
    }


    @Test
    void answerValidator() {
        assertTrue(tree.answerValidator(ride, true, 1.2f, 4, 2));
        assertTrue(tree.answerValidator(ride, true, 0, 5, 2));
        assertFalse(tree.answerValidator(ride, true, 0, 6, 2));
        assertFalse(tree.answerValidator(ride, true, 0, 4, 3));



    }
}