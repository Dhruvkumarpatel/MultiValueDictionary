package com.worksample.projects.multivaluedictionaryimplementation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the implementation of Blanaced Binary Search Tree to perform 
 * insert, search and delete operation.
 * 
 * @author DP051767
 */
public class BalancedBinarySearchTree
{
    BSTNode root;

    /**
     * Constructor to allow object creation.
     * 
     * @param value the root node value in BST.
     */
    public BalancedBinarySearchTree(final String value)
    {
        root = new BSTNode(value);
    }

    /**
     * Insert the given value in binary search tree.
     * 
     * @param value the value that need to be added. 
     */
    public void insert(final String value)
    {
        root = insertHelper(root, value);
    }

    /**
     * Search the given value in Binary Search Tree.
     * 
     * @param value the value that need to be searched.
     * @return the {@link BSTNode} for the given value, {@code null} otherwise.
     */
    public BSTNode search(final String value)
    {
        if (root == null)
        {
            return null;
        }

        BSTNode current = root;

        while (current != null)
        {
            int compare = value.compareTo(current.getVal());

            if (compare > 0)
            {
                current = current.getRight();
            }
            else if (compare < 0)
            {
                current = current.getLeft();
            }
            else
            {
                return current;
            }
        }

        return null;
    }

    /**
     * Remove the given value from the Binary Search Tree.
     * 
     * @param value the value that need to be searched.
     */
    public void remove(final String value)
    {
        root = remove(root, value);
    }

    /**
     * @return the {@link List} of all the node values from this Binary Search Tree.
     */
    public List<String> getAllValues()
    {
        final List<String> values = new ArrayList<>();
        inOrderTraversal(root, values);

        return values;
    }

    /**
     * This function visit all the nodes in Binary Search Tree in inOrder (left -> root-> right) manner.
     * 
     * @param node the {@link BSTNode}.
     * @param values {@link List} for collecting the values.
     */
    private void inOrderTraversal(final BSTNode node, final List<String> values)
    {
        if (node == null)
        {
            return;
        }

        inOrderTraversal(node.getLeft(), values);
        values.add(node.getVal());
        inOrderTraversal(node.getRight(), values);
    }

    /**
     * Function to remove the given value from Binary Search Tree.
     * 
     * @param remove the {@link BSTNode}
     * @param value the given value that need to be removed.
     * @return the {@link BSTNode} root of the Binary Search Tree. 
     */
    private BSTNode remove(BSTNode remove, final String value)
    {
        if (remove == null)
        {
            return remove;
        }

        final int compare = value.compareTo(remove.getVal());

        if (compare > 0)
        {
            remove.setRight(remove(remove.getRight(), value));
        }
        else if (compare < 0)
        {
            remove.setLeft(remove(remove.getLeft(), value));
        }
        else
        {
            // If node that need to be removed has atmost 1 child.
            if (remove.getLeft() == null || remove.getRight() == null)
            {
                BSTNode temp = remove.getLeft() == null ? remove.getRight() : remove.getLeft();

                // No child
                if (temp == null)
                {
                    temp = remove;
                    remove = null;
                }
                // 1 child
                else
                {
                    remove = temp;
                }
            }
            else
            {
                // Find the next available minimum value node to replace.
                BSTNode temp = findNextAvailableDescendant(remove.getRight());

                remove.setVal(temp.getVal());

                remove.setRight(remove(remove.getRight(), temp.getVal()));
            }
        }

        if (remove == null)
        {
            return remove;
        }

        return applyBalancingToTreeForDelete(remove);
    }
    
    /**
     * This function is to check Binary Search Tree balance at any given {@link BSTNode} after delete operation.
     * Restructure the nodes at all the level until at any given node the height difference from left subtree and right subtree
     * is less than or equal to 1. 
     * 
     * @param node the {@link BSTNode}.
     * @return the new or existing root of new balanced binary search tree.
     */
    private BSTNode applyBalancingToTreeForDelete(BSTNode node)
    {
        // update the height of parent
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));

        final int balanceAtNode = calculateBalanceAtGivenNode(node);

        if (balanceAtNode > 1 && calculateBalanceAtGivenNode(node.getLeft()) >= 0)
        {
            return rightRotate(node);
        }

        if (balanceAtNode < -1 && calculateBalanceAtGivenNode(node.getRight()) <= 0)
        {
            return leftRotate(node);
        }

        if (balanceAtNode > 1 && calculateBalanceAtGivenNode(node.getLeft()) < 0)
        {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balanceAtNode < -1 && calculateBalanceAtGivenNode(node.getRight()) > 0)
        {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    /**
     * This function is to check Binary Search Tree balance at any given {@link BSTNode} after search operation.
     * Restructure the nodes at all the level until at any given node the height difference from left subtree and right subtree
     * is less than or equal to 1. 
     * 
     * @param node the {@link BSTNode}.
     * @param value the given value that need to be searched.
     * @return the new or existing root of new balanced binary search tree.
     */
    private BSTNode applyBalancingToTreeForSearch(final BSTNode node, final String value)
    {
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));

        final int balanceAtNode = calculateBalanceAtGivenNode(node);

        if (balanceAtNode > 1 && value.compareTo(node.getLeft().getVal()) < 0)
        {
            return rightRotate(node);
        }

        if (balanceAtNode < -1 && value.compareTo(node.getRight().getVal()) > 0)
        {
            return leftRotate(node);
        }

        if (balanceAtNode > 1 && value.compareTo(node.getLeft().getVal()) > 0)
        {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balanceAtNode < -1 && value.compareTo(node.getRight().getVal()) < 0)
        {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    /**
     * Function to find minimum value in the subtree with given {@link BSTNode}.
     * 
     * @param right the {@link BSTNode} root of this subtree.
     * @return the lowest value in this subtree.
     */
    private BSTNode findNextAvailableDescendant(final BSTNode right)
    {
        BSTNode current = right;

        while (current.getLeft() != null)
        {
            current = current.getLeft();
        }

        return current;
    }

    /**
     * Insert helper to insert the given value in Binary Search Tree.
     * 
     * @param node the {@link BSTNode}.
     * @param value the given value that need to be searched.
     * @return the root of the newly balanced binary search tree.
     */
    private BSTNode insertHelper(final BSTNode node, final String value)
    {
        if (node == null)
        {
            return new BSTNode(value);
        }

        int compare = value.compareTo(node.getVal());

        if (compare > 0)
        {
            node.setRight(insertHelper(node.getRight(), value));
        }
        else if (compare < 0)
        {
            node.setLeft(insertHelper(node.getLeft(), value));
        }
        else
        {
            return node;
        }

        return applyBalancingToTreeForSearch(node, value);
    }

    /**
     * Rotate the given node into right side in the BST.
     * 
     * @param n2 the {@link BSTNode}
     * @return new root node in this sub tree.
     */
    private BSTNode rightRotate(final BSTNode n2)
    {
        BSTNode n1 = n2.getLeft();
        BSTNode rightOfn1 = n1.getRight();

        // rotate
        n1.setRight(n2);
        n2.setLeft(rightOfn1);

        // update the heights
        n2.setHeight(Math.max(getHeight(n2.getLeft()), getHeight(n2.getRight())) + 1);
        n1.setHeight(Math.max(getHeight(n1.getLeft()), getHeight(n1.getRight())) + 1);

        return n1;
    }

    /**
     * Rotate the given node into left side in the BST.
     * 
     * @param n2 the {@link BSTNode}
     * @return new root node in this sub tree.
     */
    private BSTNode leftRotate(final BSTNode n2)
    {
        BSTNode n1 = n2.getRight();
        BSTNode leftOfn1 = n1.getLeft();

        // rotate
        n1.setLeft(n2);
        n2.setRight(leftOfn1);

        // update the heights
        n2.setHeight(Math.max(getHeight(n2.getLeft()), getHeight(n2.getRight())) + 1);
        n1.setHeight(Math.max(getHeight(n1.getLeft()), getHeight(n1.getRight())) + 1);

        return n1;
    }

    /**
     * Calculate the left child height and right child height difference to check the balance factor at given 
     * node. 
     * 
     * @param node the given {@link BSTNode}.
     * @return the the left child height and right child height difference.
     */
    private int calculateBalanceAtGivenNode(final BSTNode node)
    {
        if (node == null)
        {
            return 0;
        }

        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }
    
    /**
     * Check the height at the given node in BST.
     * 
     * @param node the given {@link BSTNode}.
     * @return the height at the given node, 0 otherwise.
     */
    private int getHeight(final BSTNode node)
    {
        return node != null ? node.getHeight() : 0; 
    }
}
