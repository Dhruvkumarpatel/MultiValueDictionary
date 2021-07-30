package com.worksample.projects.multivaluedictionaryimplementationtest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.worksample.projects.multivaluedictionaryimplementation.BalancedBinarySearchTree;

public class BalancedBinarySearchTreeTest
{
    private BalancedBinarySearchTree balancedBST;
    
    @BeforeEach
    public void setUp() throws Exception
    {
        balancedBST = new BalancedBinarySearchTree("j");
        balancedBST.insert("f");
        balancedBST.insert("k");
        balancedBST.insert("a");
    }
    
    @Test
    public void testInsertWithEmptyStringValue()
    {
        Assertions.assertThrows(Exception.class, () -> balancedBST.insert(""));
    }
    
    @Test
    public void testInsertWithValidStringValue() throws Exception
    {
        balancedBST.insert("g");
        Assertions.assertEquals("k", balancedBST.search("k").getVal());
        Assertions.assertEquals("j", balancedBST.search("j").getVal());
        Assertions.assertEquals("g", balancedBST.search("g").getVal());
        Assertions.assertNull(balancedBST.search("c"));
    }
    
    @Test
    public void testInsertWithNullStringValue()
    {
        Assertions.assertThrows(Exception.class, () -> balancedBST.insert(null));
    }
    
    @Test
    public void testSearchWithEmptyStringValue()
    {
        Assertions.assertThrows(Exception.class, () -> balancedBST.search(""));
    }
    
    @Test
    public void testSearchWithNullStringValue()
    {
        Assertions.assertThrows(Exception.class, () -> balancedBST.search(null));
    }
    
    @Test
    public void testSearchWithValidStringValues() throws Exception
    {
        balancedBST.insert("g");
        balancedBST.insert("l");
        balancedBST.insert("b");
        balancedBST.insert("c");
        balancedBST.insert("e");
        
        Assertions.assertEquals("c", balancedBST.search("c").getVal());
        Assertions.assertEquals("e", balancedBST.search("e").getVal());
        Assertions.assertNull(balancedBST.search("d"));
    }
    
    @Test
    public void testRemoveWithEmptyStringValue()
    {
        Assertions.assertThrows(Exception.class, () -> balancedBST.remove(""));
    }
    
    @Test
    public void testRemoveWithNullStringValue()
    {
        Assertions.assertThrows(Exception.class, () -> balancedBST.remove(null));
    }
    
    @Test
    public void testRemoveWithValidStringValues() throws Exception
    {
        balancedBST.insert("d");
        balancedBST.insert("x");
        balancedBST.insert("y");
        balancedBST.remove("d");
        Assertions.assertNull(balancedBST.search("d"));
        Assertions.assertNull(balancedBST.search("s"));
    }
    
    @Test
    public void testGetAllValues()
    {
        final List<String> expected = new ArrayList<>();
        expected.add("a");
        expected.add("f");
        expected.add("j");
        expected.add("k");
        
        Assertions.assertEquals(expected, balancedBST.getAllValues());
    }
}