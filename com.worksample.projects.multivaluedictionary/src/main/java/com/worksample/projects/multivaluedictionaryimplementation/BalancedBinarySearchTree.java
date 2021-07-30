package com.worksample.projects.multivaluedictionaryimplementation;

import java.util.ArrayList;
import java.util.List;

public class BalancedBinarySearchTree
{
    BSTNode root;

    public BalancedBinarySearchTree(final String value)
    {
        root = new BSTNode(value);
    }

    public void insert(final String value) throws Exception
    {
        if (value == null || value.isEmpty())
        {
            throw new Exception("Given value is null or empty");
        }
        
        // BST normal insertion operation
        
        insert(root, value);
    }

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

    public void remove(final String value)
    {
        root = remove(root, value);
    }

    public List<String> getAllValues()
    {
        final List<String> values = new ArrayList<>();
        inOrderTraversal(root, values);

        return values;
    }

    // InOrder Traversal
    private void inOrderTraversal(BSTNode node, final List<String> values)
    {
        if (node == null)
        {
            return;
        }

        inOrderTraversal(node.getLeft(), values);
        values.add(node.getVal());
        inOrderTraversal(node.getRight(), values);
    }

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
            // if node has atmost 1 child
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
    
    private BSTNode applyBalancingToTreeForDelete(BSTNode node)
    {
        // update the height of parent
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));

        final int balanceAtNode = calculateBalanceAtGivenNode(node);

        if (balanceAtNode > 1 && calculateBalanceAtGivenNode(node.getLeft()) >= 0)
        {
            // left left case
            return rightRotate(node);
        }

        if (balanceAtNode < -1 && calculateBalanceAtGivenNode(node.getRight()) <= 0)
        {
            // right right case
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

    private BSTNode applyBalancingToTreeForSearch(BSTNode node, String value)
    {
        // update the height of parent
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));

        final int balanceAtNode = calculateBalanceAtGivenNode(node);

        if (balanceAtNode > 1 && value.compareTo(node.getLeft().getVal()) < 0)
        {
            // left left case
            return rightRotate(node);
        }

        if (balanceAtNode < -1 && value.compareTo(node.getRight().getVal()) > 0)
        {
            // right right case
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

    private BSTNode findNextAvailableDescendant(BSTNode right)
    {
        BSTNode current = right;

        while (current.getLeft() != null)
        {
            current = current.getLeft();
        }

        return current;
    }

    private BSTNode insert(final BSTNode node, final String value)
    {
        if (node == null)
        {
            return new BSTNode(value);
        }

        int compare = value.compareTo(node.getVal());

        if (compare > 0)
        {
            node.setRight(insert(node.getRight(), value));
        }
        else if (compare < 0)
        {
            node.setLeft(insert(node.getLeft(), value));
        }
        else
        {
            return node;
        }

        return applyBalancingToTreeForSearch(node, value);
    }

    /**
     * n2 / n1 /
     * 
     * 
     * @param n2
     * @return
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
     * n2 / n1 /
     * 
     * 
     * @param n2
     * @return
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

    private int calculateBalanceAtGivenNode(final BSTNode node)
    {
        if (node == null)
        {
            return 0;
        }

        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }
    
    private int getHeight(final BSTNode node)
    {
        return node != null ? node.getHeight() : 0; 
    }
}
