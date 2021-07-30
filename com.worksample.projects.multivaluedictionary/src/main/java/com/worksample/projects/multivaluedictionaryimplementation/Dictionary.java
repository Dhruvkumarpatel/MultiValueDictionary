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
     * @param key the given key to find associated members. Can not be {@code null} or empty.
     * @return the the {@link List} of all the values or members for given key. Will never be {@code null} or Empty.
     * @throws Exception if given key is {@code null}, empty or does not exist.
     */
    public List<String> getAllMembers(final String key) throws Exception;

    /**
     * Add the value to the dictionary for specific key.
     * 
     * @param key the given key to find associated members. Can not be {@code null} or empty.
     * @param value the given value that need to be added for the key. Can not be {@code null} or empty.
     * @throws Exception if key or value is null, empty or value already exist for the key.
     */
    public void add(final String key, final String value) throws Exception;

    /**
     * Remove given value associated with given key from dictionary. Remove Key if this is the last value mapped to the given key.
     * 
     * @param key the given key to find associated members. Can not be {@code null} or empty.
     * @param value the given value that need to be added for the key. Can not be {@code null} or empty.
     * @throws Exception if key or value is null, empty or does not exist.
     */
    public void remove(final String key, final String value) throws Exception;

    /**
     * Remove all values or members associated with given key.
     * 
     * @param key the given key to find associated members. Can not be {@code null} or empty
     * @throws Exception if key is null, empty or does not exist.
     */
    public void removeAllMembers(final String key) throws Exception;

    /**
     * Clear the Dictionary.
     */
    public void clear();

    /**
     * Check if the given key is exist in the dictionary or not.
     * 
     * @param key the given key to find associated members. Can not be {@code null} or empty.
     * @return <code>true</code> if the given key is exist in the dictionary, <code>false</code> otherwise.
     */
    public boolean isKeyExists(final String key);

    /**
     * Check if the given member is mapped to the given key or not.
     * 
     * @param key the given key to find associated members. Can not be {@code null} or empty.
     * @param value the given value that need to be added for the key. Can not be {@code null} or empty.
     * @return <code>true</code> if the given member is exist for the given key in the dictionary, <code>false</code> otherwise.
     */
    public boolean isMemberExist(final String key, final String value);
}