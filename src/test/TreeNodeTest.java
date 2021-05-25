//andrew ncneill
package test;

import model.tree.Tree;
import model.tree.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {

    TreeNode treeNode;
    TreeNode leftNode;
    TreeNode rightNode;

    @BeforeEach
    void setUp() {
         treeNode = new TreeNode("Test Question", null, "Test", false);
        leftNode = new TreeNode("Test Left Question", treeNode, "Test", false);
        rightNode = new TreeNode("Test Right Question", treeNode, "Test", false);
        treeNode.setLeft(leftNode);
        treeNode.setRight(rightNode);

    }

    @Test
    void nodeTest() {
        assertFalse(treeNode.isRecommended());
        assertEquals("Test Question", treeNode.getQuestion());
        assertEquals("Test Left Question", treeNode.getLeft().getQuestion());
        assertEquals("Test Right Question", treeNode.getRight().getQuestion());
        assertEquals(leftNode, treeNode.getLeft());
        assertEquals(rightNode, treeNode.getRight());
    }
}