package com.worksample.projects.multivaluedictionary.runnable;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.worksample.projects.multivaluedictionaryimplementation.MultiValueDictionary;

public class MultiValueDictionaryAccessPoint {
    
    private static Map<Integer, Operation> operations = new HashMap<>(Operation.values().length);
    
    
	public static void main(String args[])
	{
	    System.out.println("Initialized MultiValue Dictionary.....");
	    defineOperations();
	    
	    final MultiValueDictionary MVDictionary = new MultiValueDictionary();

	    System.out.println(operations);
	    
	    // Select Operation To Perform in MultiValue Dictionary
	    Scanner scanner = new Scanner(new InputStreamReader(System.in));
	    System.out.println("Reading input from console using Scanner in Java ");
	    System.out.println("Please Enter Number to perform specific operation From Following:");
	    final int operationNumber = scanner.nextInt();
	    
	    final Operation operation = operations.get(operationNumber);
	    
	    if (operation.equals(Operation.KEYS))
	    {
	    }
	    else if (operation.equals(Operation.MEMBERS))
	    {
	        
	    }
	    else if (operation.equals(Operation.ADD))
        {
            
        }
	    else if (operation.equals(Operation.REMOVE))
        {
            
        }
	    else if (operation.equals(Operation.REMOVEALL))
        {
            
        }
	    else if (operation.equals(Operation.KEYEXISTS))
        {
            
        }
	    else if (operation.equals(Operation.MEMBEREXISTS))
        {
            
        }
	    else if (operation.equals(Operation.ALLMEMBERS))
        {
            
        }
	    else if (operation.equals(Operation.ITEMS))
        {
            
        }
	    else if (operation.equals(Operation.CLEAR))
        {
            
        }
	    else
	    {
	        
	    }
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
