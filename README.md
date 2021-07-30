# MultiValueDictionary
MultiValue Dictionary to story multiple values for same key.

## Project Approach
I have used Balanced Binary Search Tree for bucket in HashMap. This Dictionary contains a Map of string to BinarySearchTree. BinarySearchTree is more efficient compare to LinkedList as bucket in Dictionary because using  BinarySearchTree implementation provides following runtime for each operation.

TimeComplexity : 
ADD  - O(logn) in worst case where n is the total values associated with given key.
MEMBERS - O(n) where n is the total values associated with given key
KEYS - O(1) 
REMOVE - O(logn) in worst case where n is the total values associated with given key. BST has all the values sorted in ascending order so removing will be efficient.
REMOVEALL - O(n) n is the total values for the given key.
CLEAR - O(n)
KEYEXISTS - O(1)
MEMBEREXISTS - O(logn)
ALLMEMBERS - O(n)
ITEMS - O(M) where M is the size of HashMap.

Space Complexity : O(n) where n is the size of HashMap.

##Alternate Approach 
This can be done by taking Map of String to List<String> as values. But, then search, delete, operation will be O(n) in worst case. However, we can achieve Add operation in O(1). But, i feel searching and removing should be more efficient compare to ADD. That is the reason i chose BalancedBinarySearchTree over LinkedList for values. 



## How to Access the project:
- This project can be run through Test cases MultiValueDictionaryTest class. It has test cases written out for almost all the above operations. One can modify the existing test cases or add new test cases. Also i have tested throughly the BinarySearchTree implementation. So Checkout this project and run the MultiValueDictionaryTest class. MultiValueDictionary class has core logic and all the other supported classes are being used in MultiValueDictionary.

