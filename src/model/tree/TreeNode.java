//andrew mcneill
package model.tree;

public class TreeNode {
    private String question; // question
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;
    private boolean recommended;
    private String type;

    //two constructors for two types of tree, allrides and single ride
    public TreeNode(String question, TreeNode parent, String type) {
        this.question = question;
        this.parent = parent;
        this.type = type;
    }

    public TreeNode(String question, TreeNode parent, String type, boolean isRecommended) {
        this.question = question;
        this.parent = parent;
        this.type = type;
        this.recommended = isRecommended;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRoot() {
        return parent == null;
    }
}
