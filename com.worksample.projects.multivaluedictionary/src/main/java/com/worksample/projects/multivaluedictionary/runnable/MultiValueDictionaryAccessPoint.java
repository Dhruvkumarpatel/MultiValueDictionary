package com.worksample.projects.multivaluedictionary.runnable;

import java.util.HashMap;
import java.util.Map;

public class MultiValueDictionaryAccessPoint {
    
    private static Map<Integer, Operation> operations = new HashMap<>(Operation.values().length);
    
    
	public static void main(String args[])
	{
	    System.out.println("Initialized MultiValue Dictionary.....");
	    defineOperations();
	    
	    System.out.println("Select From Following Operations:");
	    System.out.println(operations);
	    
	    // Select Operation To Perform in MultiValue Dictionary
	    
	    
	}
	
	
	private static void defineOperations()
	{
	    operations.put(1, Operation.KEYS);
	    operations.put(2, Operation.MEMBERS);
	    operations.put(3, Operation.ADD);
	    operations.put(4, Operation.REMOVE);
	    operations.put(5, Operation.REMOVEALL);
	    operations.put(6, Operation.CLEAR);
	    operations.put(7, Operation.KEYEXISTS);
	    operations.put(8, Operation.MEMBEREXISTS);
	    operations.put(9, Operation.ALLMEMBERS);
	    operations.put(10, Operation.ITEMS);
	}
}
