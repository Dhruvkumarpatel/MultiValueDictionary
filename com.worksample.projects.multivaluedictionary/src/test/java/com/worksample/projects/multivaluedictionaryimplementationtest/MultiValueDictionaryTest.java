package com.worksample.projects.multivaluedictionaryimplementationtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.worksample.projects.multivaluedictionaryimplementation.MultiValueDictionary;

/**
 * Test class to verify the functionality of {@link MultiValueDictionary}.
 * 
 * @author DP051767
 */
public class MultiValueDictionaryTest
{
    private MultiValueDictionary multiValueDict;
    
    @BeforeEach
    public void setUp()
    {
        multiValueDict = new MultiValueDictionary();
    }

    @Test
    public void testAddWithEmptyStringValue()
    {
        Assertions.assertThrows(Exception.class, () -> multiValueDict.add("Foo", ""));
    }
    
    @Test
    public void testAddWithNullStringValue()
    {
        Assertions.assertThrows(Exception.class, () -> multiValueDict.add("Foo", null));
    }
    
    @Test
    public void testAddWithEmptyKey()
    {
        Assertions.assertThrows(Exception.class, () -> multiValueDict.add("", "bar"));
    }
    
    @Test
    public void testAddWithNullKey()
    {
        Assertions.assertThrows(Exception.class, () -> multiValueDict.add(null, "bar"));
    }
    
    @Test
    public void testAddWithValidKeyValue() throws Exception
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo", "baz");
        
        final List<String> expected = new ArrayList<>();
        expected.add("bar");
        expected.add("baz");
        Assertions.assertEquals(expected, multiValueDict.getAllMembers("foo"));
    }
    
    @Test
    public void testGetAllKeys() throws Exception 
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo1", "bar");
        
        final List<String> expected = new ArrayList<>();
        expected.add("foo");
        expected.add("foo1");
        Assertions.assertEquals(expected, multiValueDict.getAllKeys());
    }
    
    @Test
    public void testGetAllMembersByKey() throws Exception 
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo", "baz");
        
        final List<String> expected = new ArrayList<>();
        expected.add("bar");
        expected.add("baz");
        Assertions.assertEquals(expected, multiValueDict.getAllMembers("foo"));
    }
    
    @Test
    public void testGetAllMembersByKeyWhenKeyDoesNotExist() throws Exception 
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo", "baz");
        
        final List<String> expected = new ArrayList<>();
        expected.add("bar");
        expected.add("baz");
        Assertions.assertThrows(Exception.class, () -> multiValueDict.getAllMembers("foo1"));
    }
    
    @Test
    public void testGetAllMembers() throws Exception 
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo1", "baz");
        
        final List<String> expected = new ArrayList<>();
        expected.add("bar");
        expected.add("baz");
        Assertions.assertEquals(expected, multiValueDict.getAllMembers());
    }
    
    @Test
    public void testRemoveMemberForGivenKeyWhenKeyDoesNotExist() throws Exception
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo", "baz");
        
        Assertions.assertThrows(Exception.class, () -> multiValueDict.remove("foo1", "bazz"));
    }
    
    @Test
    public void testRemoveMemberForGivenKeyWhenMemberDoesNotExist() throws Exception
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo", "baz");
        
        Assertions.assertThrows(Exception.class, () -> multiValueDict.remove("foo", "bazz"));
    }
    
    @Test
    public void testRemoveMemberForGivenKey() throws Exception
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo", "baz");
        multiValueDict.remove("foo", "bar");
        
        final List<String> expected = new ArrayList<>();
        expected.add("baz");
        Assertions.assertEquals(expected, multiValueDict.getAllMembers("foo"));
        
        multiValueDict.remove("foo", "baz");
        
        Assertions.assertFalse(multiValueDict.isKeyExists("foo"));
    }
    
    @Test
    public void testRemoveALlMembersForGivenKey() throws Exception
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo", "baz");
        multiValueDict.removeAllMembers("foo");
        Assertions.assertFalse(multiValueDict.isKeyExists("foo"));
    }
    
    @Test
    public void testClear() throws Exception
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo1", "baz");
        multiValueDict.add("foo2", "bar");
        multiValueDict.add("foo3", "baz");
        multiValueDict.clear();
        
        Assertions.assertEquals(Collections.emptyList(), multiValueDict.getAllKeys());
    }
    
    @Test
    public void testIsKeyExists() throws Exception
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo1", "baz");
        multiValueDict.add("foo2", "bar");
        multiValueDict.add("foo3", "baz");
        
        Assertions.assertTrue(multiValueDict.isKeyExists("foo"));
        Assertions.assertFalse(multiValueDict.isKeyExists("foo4"));
    }
    
    @Test
    public void testIsMemberExists() throws Exception
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo1", "baz");
        multiValueDict.add("foo2", "bar");
        multiValueDict.add("foo3", "baz");
        
        Assertions.assertTrue(multiValueDict.isMemberExist("foo", "bar"));
        Assertions.assertFalse(multiValueDict.isMemberExist("foo3", "bar1"));
        Assertions.assertFalse(multiValueDict.isMemberExist("foo4", "bar1"));
    }
    
    @Test
    public void testGetAllItems() throws Exception
    {
        multiValueDict.add("foo", "bar");
        multiValueDict.add("foo", "baz");
        multiValueDict.add("foo2", "bar");
        multiValueDict.add("foo3", "baz");
        
        multiValueDict.getAllKeys().forEach(k -> {
            
            try
            {
                multiValueDict.getAllMembers(k).forEach(m -> System.out.println(k +":"+m));
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        });
    }
}
