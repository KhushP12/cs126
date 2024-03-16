package structures;

import stores.Rating;


public class MyAVLtree<K extends Comparable<K>, V> {
    K key;
    Node root;
    private int size;
    private int total;

    public MyAVLtree(){
        this.root = null;
    }
    
    //=============================================================================================================
    // The class for each node in the AVL tree
    // Each node will store a rating object (which contains the rating and timestamp)
    public class Node {
        K key;
        private Rating value;
        private Node left;
        private Node right;
        private int height;

        public Node(K key, Rating value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }

        public Rating getValue(){
            return value;
        }
    }
    //=============================================================================================================

    // To get the height of a node
    private int height(Node node){
        if (node != null) {
            return node.height; }
        else {
            return -1;
        }
    }

    //To see which height is bigger 
    private int max(int a, int b){
        if (a > b){
            return a; }
        else {
            return b;
        }
    }

    // To update the height of a a node
    private void update(Node node) {
        node.height = max(height(node.left), height(node.right)) + 1;
    }

    private int balanceFactor(Node node){
        return height(node.right) - height(node.left);
    }

    // Does a right rotation on a node
    private Node rightRotateNode(Node node){
        // Stores the currently left child node
        Node leftChild = node.left;
        // Sets the left child node to the right child node of the left child node
        node.left = leftChild.right;
        // Sets the right child node of the left child to the node passed in
        leftChild.right = node;
        update(node);
        update(leftChild);
        return leftChild;
    }

    // Does a left rotation on a node
    private Node leftRotateNode(Node node){
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        update(node);
        update(rightChild);
        return rightChild;
    }

    private Node restructure(Node node){

        // If inserting into the left subtree of a left child 
        if (balanceFactor(node) < -1 && balanceFactor(node.left) <= 0) {
            node = rightRotateNode(node);
        }

        // If inserting into the right substree of a right child 
        if (balanceFactor(node) > 1 && balanceFactor(node.right) >= 0) {
            node = leftRotateNode(node);
        }

        // If inserting into the left subtree of a right child 
        if (balanceFactor(node) < -1 && balanceFactor(node.left) > 0 ){
            node.left = leftRotateNode(node.left);
            node = rightRotateNode(node);
        }

        // If inserting into the right subtree of a left child
        if (balanceFactor(node) > 1 && balanceFactor(node.right) < 0) {
            node.right = rightRotateNode(node.right);
            node = leftRotateNode(node);
        }

        return node;

    }

    // Adds the rating
    public void insert(K key, Rating rating){
        // Adds it to the root if the route doesn't exist
        if (root == null) {
            root = new Node(key, rating); 
            System.out.println("root added:" + key); }
            //return new Node(key, rating); }
        // Finds where it needs to be added
        else {
            addToSubTree(root, key, rating);
        }
        size++;
        
    }
    /**
     * 
     * @param node
     * @param rating
     */
    private Node addToSubTree(Node node, K key, Rating rating){

        if (node == null){
            return new Node(key, rating); }
    
        if (key.compareTo(node.key) < 0){
            node.left = addToSubTree(node.left, key, rating); 
            System.out.println("node.left " + node.left); }
        else if (key.compareTo(node.key) > 0) {
            node.right = addToSubTree(node.right, key, rating);
            System.out.println("node.right "+ node.right);}
        // If they are the same
        else {
            node.value = rating;
            return node;
        }

        //update the height of the node 
        update(node);

        // Restructures the AVL tree
        // return restructure(node);

        // Check balance factor and restructure if needed
        int balance = balanceFactor(node);
        if (balance > 1 && key.compareTo(node.right.key) > 0) {
            // Left rotate if right heavy and inserting to the right of the right child
            return leftRotateNode(node);
        }
        if (balance < -1 && key.compareTo(node.left.key) < 0) {
            // Right rotate if left heavy and inserting to the left of the left child
            return rightRotateNode(node);
        }
        if (balance > 1 && key.compareTo(node.right.key) < 0) {
            // Right-Left case: Right rotate the right child, then left rotate the current node
            node.right = rightRotateNode(node.right);
            return leftRotateNode(node);
        }
        if (balance < -1 && key.compareTo(node.left.key) > 0) {
            // Left-Right case: Left rotate the left child, then right rotate the current node
            node.left = leftRotateNode(node.left);
            return rightRotateNode(node);
        }
    
        return node;
        
    }

    public void remove(K key) {
        //Finds the node to remove 
        if (findNode(root, key) == null) {
            return;}
        else {
            removeFromSubTree(root, key);
        }
        size--;
        
    }

    public Node findNode(Node node, K key){
        if (node == null || node.key.equals(key)) {
            return node; }
        else if (node.key.compareTo(key) > 0){
            return findNode(node.left, key); }
        else {
            return findNode(node.right, key);
        }
    }

    private Node removeFromSubTree(Node node, K key){
        if (node != null) {
            if (key.compareTo(node.key) < 0){
                removeFromSubTree(node.left, key); }
            else if (key.compareTo(node.key) > 0) {
                removeFromSubTree(node.right, key); }
            // this is the node
            else {
                // Checks if its a leaf node or only has one child
                if (node.left == null || node.right == null) {
                    Node temp = null;
                    if (node.left != null) {
                        temp = node.left; }
                    else {
                        temp = node.right; }

                    // Checks if the left node isn't here
                    if (node.left == null){
                        temp = node.right; }
                    else {
                        temp = node.left; }
                    if (temp == null) {
                        temp = node; 
                        node = null;}
                    else {
                        node.value = temp.value;
                        node.key = temp.key; } }
                // Node has two children 
                else {
                    Node next = findMinNode(node.right);
                    node.key = next.key;
                    node.value = next.value;
                    node.right = removeFromSubTree(node.right, next.key);
                    }
                }

            if (node != null ){
                update(node);
                return restructure(node); } }

        return null;
    }

    private Node findMinNode(Node node){
        while (node.left != null){
            node = node.left; }
        return node;
    }

    public float[] ratingsArray() {
        int[] i = {0};
        float[] ratingsArray = new float[size];
        getRatingForEachNode(root, ratingsArray, i);
        return ratingsArray;
        
    }

    private void getRatingForEachNode(Node node, float[] ratingsArray, int[] i){
        if (node != null){
            getRatingForEachNode(node.left, ratingsArray, i);
            float number = node.getValue().getRating();
            ratingsArray[i[0]] = number;
            i[0] = i[0] + 1;
            getRatingForEachNode(node.right, ratingsArray, i);
        }
    }

    // For testing 
    
        
    



    
}