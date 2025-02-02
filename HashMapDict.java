import java.math.*;
import java.util.Iterator;

public class HashMapDict<K,V> implements ProjOneDictionary<K,V> {

    // Inner class to represent a node in the hash map
    private static class Node<K,V>{
        K key;
        V value;

        // Constructor for the node
        Node(K key, V value){
            super(); // Calls the Object class constructor
            this.key = key;
            this.value = value;
        }
    }

    // Inner class for iterator implementation
    private class HashIterator implements Iterator<K> {

        int cursor; // Index of the current element in the iteration
        int num_elements; // Number of elements iterated over

        // Constructor for the iterator
        public HashIterator(){
            cursor = 0;
            num_elements = 0;
        }

        // Check if there are more elements to iterate over
        @Override
        public boolean hasNext() {
            return num_elements < size;
        }

        // Returns the next key in the iteration
        @Override
        public K next() {
            if (!hasNext()) return null;

            for (int i = cursor; i < HashArray.length; i++){
                if (HashArray[i] != null){
                    cursor = i + 1;
                    num_elements++;
                    return HashArray[i].key;
                }
            }
            return null;
        }
    }

    // Array of nodes to store key-value pairs
    private Node<K,V>[] HashArray;
    private int size; // Number of key-value pairs in the hash map
    private int capacity; // Current capacity of the hash map

    // Constructor for HashMapDict
    public HashMapDict(){
        HashArray = new Node[10];
        size = 0;
        capacity = 10;
    }

    // Method to expand the capacity of the hash map
    private void Expand_Array(){
        Node<K,V>[] new_array = new Node[capacity * 2];

        for (int i = 0; i < capacity; i++){
            if (HashArray[i] != null){
                new_array[i] = HashArray[i];
            }
        }

        HashArray = new_array;
        capacity = capacity * 2;
    }

    // Insert a key-value pair into the hash map
    @Override
    public boolean insert(K key, V value) throws NullValueException {
        if (value == null) {throw new NullValueException();}

        if (size >= capacity/2 ){
            Expand_Array();
        }

        V my_val = find(key);

        // Update value if key already exists
        if (my_val != null){
            for (int i = 0; i < capacity; i++){
                if (HashArray[i] != null && HashArray[i].key == key){
                    HashArray[i].value = value;
                    break;
                }
            }
            return true;
        }

        // Calculate the index for the new key
        int index = Math.abs(key.hashCode() % HashArray.length);

        // Insert the new key-value pair
        if (HashArray[index] == null) {
            HashArray[index] = new Node(key, value);
        }
        else{
            boolean check_insertion = false;
            for (int i = index + 1; i < HashArray.length; i++){
                if (HashArray[i] == null){
                    HashArray[i] = new Node(key, value);
                    check_insertion = true;
                    break;
                }
            }
            if (!check_insertion){
                for (int i = 0; i < index; i++){
                    if (HashArray[i] == null) {
                        HashArray[i] = new Node(key, value);
                        break;
                    }
                }
            }
        }

        size++;
        return false;
    }

    // Find the value associated with a key
    @Override
    public V find(K key) {
        for (int i = 0; i < HashArray.length; i++){
            if (HashArray[i] != null && HashArray[i].key.equals(key)){
                return HashArray[i].value;
            }
        }
        return null;
    }

    // Delete a key-value pair from the hash map
    @Override
    public boolean delete(K key) {
        boolean element_found = false;

        for (int i = 0; i < HashArray.length; i++){
            if (HashArray[i] != null && HashArray[i].key.equals(key)){
                HashArray[i] = null;
                size--;
                element_found = true;
                break;
            }
        }
        return element_found;
    }

    // Get the number of key-value pairs in the hash map
    @Override
    public int getSize() {
        return size;
    }

    // Get an iterator for the keys in the hash map
    @Override
    public Iterator<K> iterator() {
        return new HashIterator();
    }
}

