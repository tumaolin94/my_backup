# Introduction
This project is made up with two .java files, `NewHashMap.java` and `HashMapTest.java`.
## NewHashMap.java

This file is the main class of my fixed-size hash map. Which mainly includes a sub class and six functions.

###  class Node<T>
	This class is used to save Key-Value pairs in the hash map. So it contains `String key`, 'T value' and ' Node<T> next' three variables saving key, value and next Node.

### NewHashMap(int)
	constructor of class NewHashMap, initialize new map with parameter `size`

### set(String, T)
	Saving Key-Value pairs in hashmap, if the key has been saved, it will update the value and return `true`. If the map is full, return `false`.

### get(String)
	Searching for value with parameter key and return, if no this pair return `false`.

### delete(String)
	Searching for value with parameter key and delete, if no this pair return `false`.	

### float()
	Return a float value representing the load factor

### hash()
	In order to calculate hashCode of String, I use the function from JAVA's library, and then `key.hashCode() % capacity`

## HashMapTest.java

This file is a test file with JUnit 4. I initialize a hashmap with `map_size`. And test set, get, delete and the whole process with date numbers less than, equal and more than `map_size`. You can run it in JUnit mode and I also design a main function so that you can run it directly.  

