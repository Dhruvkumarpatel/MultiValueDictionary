package com.worksample.projects.multivaluedictionaryimplementation;

/**
 * Model class that represents the Node in Balanced Binary Search Tree.
 * 
 * @author DP051767
 */
public class BSTNode
{
    private String val;

    private int height;

    private BSTNode left;

    private BSTNode right;

    /**
     * Constructor to define BSTNode.
     * 
     * @param val the node value.
     */
    public BSTNode(final String val)
    {
        this.val = val;
        this.height = 1;
        this.left = null;
        this.right = null;
    }

    /**
     * @return the height of the node in BST.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @param val the node value.
     */
    public void setVal(final String val)
    {
        this.val = val;
    }

    /**
     * @param height the height of the node in BST.
     */
    public void setHeight(final int height)
    {
        this.height = height;
    }

    /**
     * @return the left child of the node.
     */
    public BSTNode getLeft()
    {
        return left;
    }

    /**
     * @param left the left child of the node.
     */
    public void setLeft(final BSTNode left)
    {
        this.left = left;
    }

    /**
     * @return the right child of node.
     */
    public BSTNode getRight()
    {
        return right;
    }

    /**
     * @param right the right child of node.
     */
    public void setRight(BSTNode right)
    {
        this.right = right;
    }

    /**
     * @return the value of the node.
     */
    public String getVal()
    {
        return val;
    }
}