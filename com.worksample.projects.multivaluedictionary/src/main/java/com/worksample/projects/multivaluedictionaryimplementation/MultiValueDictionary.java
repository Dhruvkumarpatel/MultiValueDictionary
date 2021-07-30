package com.worksample.projects.multivaluedictionaryimplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MultiValueDictionary implements Dictionary
{
    private Map<String, BalancedBinarySearchTree> multiValueDict;

    public MultiValueDictionary()
    {
        multiValueDict = new HashMap<>();
    }

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

    @Override
    public List<String> getAllMembers()
    {
        return multiValueDict.entrySet().stream().flatMap(e -> e.getValue().getAllValues().stream()).collect(Collectors.toList());
    }

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

    @Override
    public void clear()
    {
        multiValueDict.clear();
    }

    @Override
    public boolean isKeyExists(String key)
    {
        return multiValueDict.containsKey(key);
    }

    @Override
    public boolean isMemberExist(final String key, final String value)
    {
        if (!multiValueDict.containsKey(key))
        {
            return false;
        }
        
        final BalancedBinarySearchTree values = multiValueDict.get(key);
        final BSTNode node = values.search(value);
        
        return node == null ? false : true;
    }
}