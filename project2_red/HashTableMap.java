import java.util.LinkedList;
import java.util.NoSuchElementException;

//--== CS400 File Header Information ==--
//Name: Yifan Liu
//Email: yliu897@wisc.edu
//Team: red
//Group: AF
//TA: Mu Cai
//Lecturer: Gary
//Notes to Grader: <optional extra notes>

/**
 * @author iveniven
 * This class implements the MapADT interface. It is an class to store
 * hash table that uses an array of LinkedList to 
 * store key value pairs. In this class, it can add key value pairs by method 
 * put(KeyType key, ValueType value), and the get(KeyType key) serve as a 
 * getter class to get the value corresponds to the given key.
 * It can also remove a pair from the hash table.
 * The hash table will double its capacity when the load factor is greater than .85
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>{
  private LinkedList<Object[]>[] hashTable; // initialize the hashtable
  private int capacity = 10; // initialize the capacity of hashTable to default 10
  private int size;  // the number of pairs of key value pairs
  
  /**
   * One argument constructor
   * @param capacity the size of the hashTable array
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    hashTable =  (LinkedList<Object[]>[]) new LinkedList[capacity];
    // initialize the LinkedList
    for(int i = 0; i < hashTable.length; i++) {
      hashTable[i] = new LinkedList<Object[]>();  
    }
  }
  
  /**
   * The default constructor that set capacity to 10
   */
  public HashTableMap() {
    hashTable =  (LinkedList<Object[]>[]) new LinkedList[capacity];
    // initialize the LinkedList
    hashTable =  (LinkedList<Object[]>[]) new LinkedList[capacity];
    for(int i = 0; i < hashTable.length; i++) {
      hashTable[i] = new LinkedList<Object[]>();  
    }
  }
  
  /**
   * Resize the original array to double size and 
   * move all of the value to the new array
   * @return reference of the new array
   */
  private LinkedList<Object[]>[] resize() {
    int resizeCapacity = capacity * 2;
    LinkedList<Object[]>[] tempTable;
    tempTable = (LinkedList<Object[]>[]) new LinkedList[resizeCapacity]; 
    // initialize the LinkedList
    for(int i = 0; i < tempTable.length; i++) {
      tempTable[i] = new LinkedList<Object[]>();  
    }
    // copy the old array to the new one
    for(int i = 0 ; i < capacity; i ++) {
      if(hashTable[i] != null) {
        for(int j = 0; j < hashTable[i].size(); j++) {
          int index = Math.abs(hashTable[i].get(j)[0].hashCode())%resizeCapacity;
          if(tempTable[index] == null) {
            tempTable[index] = new LinkedList<Object[]>(); 
          }
          tempTable[index].add(hashTable[i].get(j));
        }
      }
    }
    this.capacity = resizeCapacity;
    return tempTable;
    
  }
  /**
   * Add the pair of key value as a Object[] into the hashTable
   * The hashTable uses chaining to avoid collision.
   * Duplicates will not be added to the hashTable
   * @param key the key of the hashTable
   * @param value correspond to the key
   * @return true if the pairs are added successfully and false otherwise
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    // when key is null
    if(key == null) {
      return false;
    }    
    // store the key value pair into an Object[]
    int index = Math.abs((key.hashCode())) % capacity;
    Object[] pairs = new Object[2];
    pairs[0] = key;
    pairs[1] = value;
    // avoid adding duplicates
    for(int i = 0; i < hashTable[index].size(); i++) {
      Integer temp = Math.abs(hashTable[index].get(i)[0].hashCode());
      if (Integer.valueOf(Math.abs(key.hashCode())).equals(temp)) {
        return false;
      }
    }
    
    hashTable[index].add(pairs);
    size++;
    // check the load factor 
//    double loadfactor = (double)size / capacity;
//    if(loadfactor >= 0.85) {
//      hashTable = resize();
//    }
    return true;
  }
  /**
   * The getter method of the class
   * By parsing in the key, the method returns the corresponding 
   * value if the key exists.
   * @param key the key to search for
   * @return the value of the key if it exists
   * @exception NoSuchElementException will be thrown if the key does not exist
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    int index = Math.abs((key.hashCode())) % capacity;
    for(int i = 0; i < hashTable[index].size(); i++) {
      Integer temp = Math.abs(hashTable[index].get(i)[0].hashCode());
      if(Integer.valueOf(Math.abs(key.hashCode())).equals(temp)){
        return (ValueType) hashTable[index].get(i)[1];
      }
    }
    throw new NoSuchElementException("key Not found");
  }
  
  /**
   * return the number of pairs of key and value in the hashTable array
   */
  @Override
  public int size() {
    return size;
  }
  
  /**
   * Check whether a specific key exists
   * @param key the key to search for
   * @return true if it exists and false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {
    int index = Math.abs((key.hashCode())) % capacity;
    for(int i = 0; i < hashTable[index].size(); i++) {
      Integer temp = Math.abs(hashTable[index].get(i)[0].hashCode());
      if(Integer.valueOf(Math.abs(key.hashCode())).equals(temp)){
        return true;
      }
    }
    return false;
  }
  
  /**
   * Remove the key and value pairs from the LinkedList
   * and return the reference to that value
   * @param key the key to search for
   * @return the reference to the value or null if key does not exist
   */
  @Override
  public ValueType remove(KeyType key) {
    int index = Math.abs((key.hashCode())) % capacity;
    for(int i = 0; i < hashTable[index].size(); i++) {
      Integer temp = hashTable[index].get(i)[0].hashCode();
      if(Integer.valueOf(key.hashCode()).equals(temp)){ 
        size--;
        return (ValueType) hashTable[index].remove(i)[1];
      }
    }
    return null;
  }
  /**
   * clear all the pairs of key and value and set the hashTable to null 
   * and size to 0.
   */
  @Override
  public void clear() {
    hashTable = null;
    size = 0;
    
  }
  
  /**
   * Get the capacity of the array
   * for debugging purposes
   */
  public int getCapacity() {
    return this.capacity;
  }


}
