package edu.usc.maolintu;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author maolintu
 */
public class HashMapTest {

    private NewHashMap hashMap;
    private static int map_size = 1000;
    private static int data_num = 1100;

    @Before
    public void setUp(){
        try {
			hashMap = new NewHashMap(map_size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testSetGet(){

        try {
            for(int i = 0; i < map_size; i++){
                hashMap.set(Integer.toString(i), Integer.toString(i));
                assertEquals(Integer.toString(i), hashMap.get(Integer.toString(i)));
    			
            }
            for(int i = 0; i < map_size; i++){
            	hashMap.delete(Integer.toString(i));    
            	assertEquals(null, hashMap.get(Integer.toString(i)));
            }
            assertEquals(0, hashMap.getCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testSetGetDataMoreThanSize(){

        try {
            for(int i = 0; i < data_num; i++){
                if(hashMap.set(Integer.toString(i), Integer.toString(i)))
                	assertEquals(Integer.toString(i), hashMap.get(Integer.toString(i)));
    			
            }
            for(int i = 0; i < data_num; i++){
            	hashMap.delete(Integer.toString(i));    
            	assertEquals(null, hashMap.get(Integer.toString(i)));
            }
            assertEquals(0, hashMap.getCount());
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testReplace() {
        try {
        	for(int i = 0; i < map_size; i++) {
        		hashMap.set("test", i);
                assertEquals(new Integer(i), hashMap.get("test"));
        	}
			hashMap.delete("test");
			assertEquals(0, hashMap.getCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testDelete() {

        try {
            for(int i = 0; i < map_size; i++){
                hashMap.set(Integer.toString(i), Integer.toString(i));
                assertEquals(Integer.toString(i), hashMap.delete(Integer.toString(i)));
    			assertEquals(null , hashMap.get(Integer.toString(i)));
                
            }
            assertEquals(0, hashMap.getCount());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testHashMap() {
        try {
            /* Build up full hashmap */
            for(int i = 0; i < map_size; i++){
                hashMap.set(Integer.toString(i), Integer.toString(i));
            }
            /* Test get function */
            for(int i = 0; i < map_size; i++){
                assertEquals(Integer.toString(i), hashMap.get(Integer.toString(i)));
            }
            int count = map_size-1;
            /* Test delete function */
            for(int i = 0; i < map_size; i++){
            	assertEquals(Integer.toString(i), hashMap.get(Integer.toString(i)));
                hashMap.delete(Integer.toString(i));
                assertEquals(null, hashMap.get(Integer.toString(i)));
                assertEquals(count--, hashMap.getCount());
                
            }
            assertEquals(0, hashMap.getCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @After
    public void tearDown(){
        hashMap = null;
        System.gc();
    }
    
    public static void main(String[] args) {
        HashMapTest test = new HashMapTest();
        test.setUp();
        System.out.println("Test set and get function");
        test.testSetGet();
        System.out.println("Test set adn get function while data more than map's size");
        test.testSetGetDataMoreThanSize();
        System.out.println("Test replace function");
        test.testReplace();
        System.out.println("Test delete function");
        test.testDelete();
        System.out.println("Test complete functions");
        test.testHashMap();

    }
}
