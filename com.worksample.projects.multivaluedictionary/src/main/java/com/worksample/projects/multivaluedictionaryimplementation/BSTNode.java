package com.worksample.projects.multivaluedictionaryimplementation;

public class BSTNode
{
    private String val;
    private int height;
    private BSTNode left;
    private BSTNode right;
    
    
    public BSTNode(final String val)
    {
        this.val = val;
        this.height = 1;
        this.left = null;
        this.right = null;
    }


    public int getHeight()
    {
        return height;
    }


    public void setVal(String val)
    {
        this.val = val;
    }


    public void setHeight(int height)
    {
        this.height = height;
    }


    public BSTNode getLeft()
    {
        return left;
    }


    public void setLeft(BSTNode left)
    {
        this.left = left;
    }


    public BSTNode getRight()
    {
        return right;
    }


    public void setRight(BSTNode right)
    {
        this.right = right;
    }


    public String getVal()
    {
        return val;
    }
}