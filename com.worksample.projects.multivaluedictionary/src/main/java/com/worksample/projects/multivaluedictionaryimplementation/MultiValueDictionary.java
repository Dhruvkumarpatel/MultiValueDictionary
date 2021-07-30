package com.worksample.projects.multivaluedictionaryimplementation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of {@link Dictionary} to define multiple values storage for single key.
 * 
 * @author Dhruv Patel [DP051767]
 */
public class MultiValueDictionary implements Dictionary
{
    private Map<String, BalancedBinarySearchTree> multiValueDict;

    /**
     * Constructor to define the instance of this class.
     */
    public MultiValueDictionary()
    {
        multiValueDict = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllKeys()
    {
        if (multiValueDict.isEmpty())
        {
            return Collections.emptyList();
        }
        
        final List<String> keys = new ArrayList<>();
        multiValueDict.keySet().forEach(s -> keys.add(s));
        
        return keys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllMembers()
    {
        return multiValueDict.entrySet().stream().flatMap(e -> e.getValue().getAllValues().stream()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllMembers(final String key) throws Exception
    {
        if (key == null || key.isEmpty())
        {
            throw new Exception("ERROR : Given key is null or empty.");
        }
        
        if (!multiValueDict.containsKey(key))
        {
            throw new Exception("ERROR : key does not exist.");
        }
        
        final BalancedBinarySearchTree values = multiValueDict.get(key);
        return values.getAllValues();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(String key, String value) throws Exception
    {
        if (key == null || value == null || key.isEmpty() || value.isEmpty())
        {
            throw new Exception("ERROR : Given key or Member value is null or empty.");
        }
        
        if (multiValueDict.get(key)!= null && multiValueDict.get(key).search(value) != null)
        {
            throw new Exception("ERROR : Member already exists for the key.");
        }
        
        final BalancedBinarySearchTree values = multiValueDict.computeIfAbsent(key, k -> new BalancedBinarySearchTree(value));
        values.insert(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final String key, final String value) throws Exception
    {
        if (key == null || value == null || key.isEmpty() || value.isEmpty())
        {
            throw new Exception("ERROR : Given key or Member value is null or empty.");
        }
        
        if (!multiValueDict.containsKey(key))
        {
            throw new Exception("ERROR : key does not exist.");
        }
        
        if (multiValueDict.get(key).search(value) == null)
        {
            throw new Exception("ERROR : member does not exist.");
        }
        
        final BalancedBinarySearchTree values = multiValueDict.get(key);
        values.remove(value);
        
        if (values.root == null)
        {
            multiValueDict.remove(key);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllMembers(final String key) throws Exception
    {
        if (key == null || key.isEmpty())
        {
            throw new Exception("ERROR : Given key is null or empty.");
        }
        
        if (!multiValueDict.containsKey(key))
        {
            throw new Exception("ERROR : key does not exist.");
        }
        
        final BalancedBinarySearchTree values = multiValueDict.get(key);
        values.getAllValues().forEach(s -> values.remove(s));
        
        multiValueDict.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        multiValueDict.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isKeyExists(String key)
    {
        return multiValueDict.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMemberExist(final String key, final String value)
    {
        if (!multiValueDict.containsKey(key))
        {
            return false;
        }
        
        final BalancedBinarySearchTree values = multiValueDict.get(key);
        final BSTNode node = values.search(value);
        
        return node != null;
    }
}