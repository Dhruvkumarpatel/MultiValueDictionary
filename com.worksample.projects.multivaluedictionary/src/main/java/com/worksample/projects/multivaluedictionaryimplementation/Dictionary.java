package com.worksample.projects.multivaluedictionaryimplementation;

import java.util.List;

public interface Dictionary
{
    public List<String> getAllKeys();
    
    public List<String> getAllMembers();
    
    public List<String> getAllMembers(final String key);
    
    public void add(final String key, final String value);
    
    public void remove(final String keey, final String value);
    
    public void removeAllMembers(final String key);
    
    public void clear();
    
    public boolean isKeyExists(final String key);
    
    public boolean isMemberExist(final String key, final String value);
}
