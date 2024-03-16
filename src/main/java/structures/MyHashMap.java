package structures;

public class MyHashMap<K,V> {
    // Prime number to reduce collisions
    private static int initial_cap = 13; 
    private static float initial_lf = 0.75f;
    private int capacity;
    private float loadFactor;
    private int size;
    //Makes an array of nodes
    private Node<K, V> [] array; 

    public MyHashMap(){
        this(initial_cap, initial_lf);
    }

    public MyHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        //Makes an array of nodes with the given capacity
        this.array = (Node<K, V> []) new Node[capacity]; 
    }


    /**
     * The class for the nodes.
     * Stores a key, a value and the next node.
     */
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    /**
     * Takes in the key object and returns an integer hash code
     * @param key the key which is the ID
     * @return code which is the integer produced
     */
    protected int hash(K key){
        int code = key.hashCode();
        return Math.abs(code);
    }

    //Can overide the .hashCode() function if it is slow

    /**
     * Adds a key value pair to the hash map.
     * Makes use of chaining when collisons occur
     * @param key the unique identifier being passed in
     * @param value The value being stored with that key
     */
    public void put(K key, V value){
        int hash_code = hash(key);
        int loc = (hash_code % array.length);
        Node<K, V> node = array[loc];

        //If there is a collison
        while (node != null){
            // If the keys are the same, it updates the value
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            // Checks the next node 
            node = node.next;
        }

        // If the node is null, creates a new node
        Node <K, V> nNode = new Node<>(key, value, array[loc]);
        array[loc] = nNode;
        size = size + 1;
        

        //Resizes if the load factor goes above 0.75
        if (((float) size / capacity) > loadFactor){
            resize();
        }


    }

    /**
     * To resize the hashmap if its load factor goes above 0.75
     */
    private void resize(){
        // Doubles capacity
        int newCap = capacity * 2; //+1
        Node<K, V> [] newArray = new Node[newCap];

        for (int i=0; i < capacity; i++) {
            // Gets the node at that index
            Node<K, V> node = array[i];

            // While that node exists and is not null
            while (node != null) {
                // Finds the location that it should be stored at
                int hash_code = hash(node.key);
                int loc = hash_code % newCap;

                // Stores the pointer to the next node
                Node<K, V> nextNodePointer = node.next;
                // Updates the pointer to the next node to its location in the new array
                node.next = newArray[loc];
                // Inserts the node in the bigger array
                newArray[loc] = node;
                // Sets the node to the next node
                node = nextNodePointer;
            }
        }
    capacity = newCap;
    array = newArray;
    }

    /**
     * Checks if the key is already in the hash map. This is then used to check if the item should be added or not.
     * @param key the movie id 
     * @return a boolean value, true if the value is in the hah map and false if it is not
     */
    public boolean hasKey(K key){
        int hash_code = hash(key);
        int loc = (hash_code % array.length);
        Node<K, V> node = array[loc];

        //If there is a collison
        while (node != null){
            // If the keys are the same, it updates the value
            if (node.key.equals(key)) {
                return true;
            }
            // Checks the next node 
            node = node.next;
        }
        return false;
    }

    /**
     * Takes in a key (id) and removes that movie from the hashmap
     * @param key the movie id
     * @return true if the movie is removed and false if the movie is not removed
     */
    public boolean remove(K key) {
        // Finds the node with the given key
        int hash_code = hash(key);
        int loc = (hash_code % array.length);
        Node<K, V> node = array[loc];

        Node<K, V> prevNode = null;

        // If there is a node at this location
        while (node != null){
            // If the keys match
            if (node.key.equals(key) == true) {
                // If the node is at the head of the array, sets the head of the array to point to the next node
                if (prevNode == null){
                    array[loc] = node.next; }
                // If the node is not at the head, sets the previous node to skip the node
                else {
                    prevNode.next = node.next;
                }
                // Decrements size as an node has been removed
                size = size - 1;
                return true;
            }
            // Sets the previous node to the one that was just checked and node to the next one along
            prevNode = node;
            node = node.next;
        }
        return false;
    }

    /**
     * Takes in a key and returns the associated value
     * @param key the unique identifier passed in
     * @return gives back the value if found and null if not
     */
    public V get(K key){
        //Finds a node with the key passed in
        int hash_code = hash(key);
        int loc = (hash_code % array.length);
        Node<K, V> node = array[loc];

        // If the node is not empty, and the keys match it returns the value
        while (node != null){
            if (node.key.equals(key) == true) {
                return node.value;
            }
        }
        //If no node is found, it returns null
        return null;
    }

    /**
     * Makes an array of all of the keys in the hashmap. It works by looping through each node and if its existent, it's key value is stored 
     * @return gives back the array
     */
    public int[] keyArray(){
        //Creates an empty array of the number of elements in the hash map
        int [] arr = new int[size];

        // Loops through each node in the array of nodes and if it is not empty, it's key is added to the array of keys
        // Needs an extra index variable for when it is inside the for loop
        int index = 0;
        for (int i=0; i < array.length ; i++) {
            Node<K, V> node = array[i];

            while (node != null) {
                arr[index] = (int)node.key;
                index = index+1;
                node = node.next;
            }
        }
        return arr;
        
    }

    /**
     * Gives back the size of the hash map
     * @return the size variable which is incremented when items are added and decremented when items are removed
     */
    public int size(){
        return size;
    }
    
}
