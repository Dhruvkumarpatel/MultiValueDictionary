package com.worksample.projects.multivaluedictionaryimplementation;

import java.util.List;

/**
 * This interface represents all the operations for dictionary that can be implemented by consumers. Consumer can add additional methods to support
 * more functionalities.
 * 
 * @author Dhruv Patel [DP051767]
 */
public interface Dictionary
{
    /**
     * @return the {@link List} of string representation of keys in natural order.
     */
    public List<String> getAllKeys();

    /**
     * @return the {@link List} of all the values or members present in the dictionary.
     */
    public List<String> getAllMembers();

    /**
     * Return the {@link List} of all the values or members associated with given key.
     * 
     * @param key the given key to find associated members. Can not be {@code null}.
     * @return the the {@link List} of all the values or members for given key. Will never be {@code null} or Empty.
     * @throws Exception if given key is {@code null} or empty.
     */
    public List<String> getAllMembers(final String key) throws Exception;

    public void add(final String key, final String value) throws Exception;

    public void remove(final String keey, final String value) throws Exception;

    public void removeAllMembers(final String key) throws Exception;

    public void clear();

    public boolean isKeyExists(final String key);

    public boolean isMemberExist(final String key, final String value);
}