import java.util.Iterator;

public class BinarySearchTreeDict<K extends Comparable<K>,V> implements ProjOneDictionary<K,V> {

    private int size; // Keeps track of the number of elements in the tree
    private Node root; // Root node of the binary search tree
    private boolean wasDeleted; // Flag to track if a node was successfully deleted

    // Inner class to represent a node in the binary search tree
    private class Node{
        K key; // The key of the node
        V value; // The value associated with the key
        public Node right; // Reference to the right child
        public Node left; // Reference to the left child

        // Constructor for the node
        Node(K key, V value, Node right, Node left){
            super(); // Calls the Object class constructor
            this.key = key;
            this.value = value;
            this.right = right;
            this.left = left;
        }
    }

    // Inner class for BST iterator
    private class BSTiterator implements Iterator<K>{
        Node cursor = root; // Current node in the iteration
        ListQueue<Node> myQueue; // Queue to manage the iteration order

        // Constructor for the iterator
        private BSTiterator(){
            myQueue = new ListQueue<>();
            if (cursor != null){
                myQueue.enqueue(cursor);
            }
        }

        // Check if there are more elements to iterate over
        @Override
        public boolean hasNext() {
            return myQueue.getSize() != 0;
        }

        // Returns the next key in the iteration
        @Override
        public K next() {
            if (!hasNext()) {
                return null;
            }

            Node current = myQueue.dequeue();
            if (current.left != null) {
                myQueue.enqueue(current.left);
            }
            if (current.right != null) {
                myQueue.enqueue(current.right);
            }

            return current.key;
        }
    }

    // Insert a key-value pair into the tree
    @Override
    public boolean insert(K key, V value) throws NullValueException {
        if(value == null){
            throw new NullValueException(); // Throws an exception if value is null
        }
        Node toAdd = new Node(key, value, null, null); // Create new node
        size++;

        if (root == null){
            root = toAdd; // Set root if tree is empty
            return false;
        }
        else{
            return recursive_insert(root, toAdd); // Recursive insert
        }
    }

    // Helper method for recursive insertion
    public boolean recursive_insert(Node current, Node newNode){
        int compare_result = newNode.key.compareTo(current.key);

        // Inserting in the left subtree
        if (compare_result < 0){
            if (current.left == null){
                current.left = newNode; // Insert node if left child is null
                return false;
            }
            return recursive_insert(current.left, newNode); // Recurse on left child
        }

        // Inserting in the right subtree
        else if (compare_result > 0){
            if (current.right == null){
                current.right = newNode; // Insert node if right child is null
                return false;
            }
            return recursive_insert(current.right, newNode); // Recurse on right child
        }

        // Key already exists, update value
        else{
            current.value = newNode.value;
            size--;
            return true;
        }
    }

    // Find the value associated with a key
    @Override
    public V find(K key) {
        Node current = root;
        while (current != null) {
            int compareResult = key.compareTo(current.key);
            if (compareResult < 0) {
                current = current.left; // Move to left child
            } else if (compareResult > 0) {
                current = current.right; // Move to right child
            } else {
                return current.value; // Key found
            }
        }
        return null; // Key not found
    }

    // Delete a key-value pair from the tree
    @Override
    public boolean delete(K key) {
        if (root == null) {
            return false; // Return false if tree is empty
        }

        wasDeleted = false; // Initialize wasDeleted to false
        root = deleteRecursive(root, key); // Start recursive deletion
        return wasDeleted;
    }

    // Helper method for recursive deletion
    private Node deleteRecursive(Node current, K key) {
        if (current == null) {
            return null;
        }

        int compareResult = key.compareTo(current.key);
        if (compareResult < 0) {
            current.left = deleteRecursive(current.left, key); // Recurse on left child
        } else if (compareResult > 0) {
            current.right = deleteRecursive(current.right, key); // Recurse on right child
        } else {
            // Node to be deleted found
            wasDeleted = true;
            size--;

            // Handling different deletion scenarios
            // Case 1: No child
            if (current.left == null && current.right == null) {
                return null;
            }
            // Case 2: One child
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }
            // Case 3: Two children
            Node smallestNode = findSmallestNode(current.right); // Find the smallest node in the right subtree
            current.key = smallestNode.key;
            current.value = smallestNode.value;
            current.right = deleteRecursive(current.right, smallestNode.key); // Delete the smallest node
        }

        return current;
    }

    // Find the node with the smallest key in the subtree
    private Node findSmallestNode(Node root) {
        Node current = root;
        while (current != null && current.left != null) {
            current = current.left; // Move to the leftmost node
        }
        return current;
    }

    // Get the number of key-value pairs in the tree
    @Override
    public int getSize() {
        return size;
    }

    // Get an iterator for the keys in the tree
    @Override
    public Iterator<K> iterator() {
        return new BSTiterator();
    }
}


