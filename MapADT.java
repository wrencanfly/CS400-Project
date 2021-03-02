// --== CS400 File Header Information ==--
// Name: Yingwei Song
// Email: ysong279@wisc.edu
// Team: Red
// Group: AF
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: none

import java.util.NoSuchElementException;

public interface MapADT<KeyType, ValueType> {

    public boolean put(KeyType key, ValueType value);
    public ValueType get(KeyType key) throws NoSuchElementException;
    public int size();
    public boolean containsKey(KeyType key);
    public ValueType remove(KeyType key);
    public void clear();

}
