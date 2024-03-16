package structures;

import stores.Rating;

public class BinaryTree<E extends Comparable<E>, V> {
    BinaryTreeNode<E, V> root;
    int size = 0;

    public BinaryTree() {
        root = null;
    }

    //===============================================================================================
    // Adding a new element 
    private void addToSubTree(BinaryTreeNode<E, V> node, E key, V value) {    
        if (node!=null) // sanity check!
        {
            E nodeKeyVal = node.getKey();
            if (key.compareTo(nodeKeyVal) <= 0) {
                System.out.println("Adding "+ key +" to left sub-tree of "+nodeKeyVal);
                if (node.getLeft()==null)
                    node.setLeft(new BinaryTreeNode<>(key, value));
                else
                    addToSubTree(node.getLeft(), key, value);
            }
            else {
                System.out.println("Adding "+key +" to right sub-tree of "+nodeKeyVal);
                if (node.getRight()==null)
                    node.setRight(new BinaryTreeNode<>(key, value));
                else
                    addToSubTree(node.getRight(), key, value);
            }
        }
    }
    

    public void add(E key, V value) {
        if (root==null) {
            System.out.println("Adding "+key+" to root.");
            root = new BinaryTreeNode<>(key, value);
        }
        else {
            addToSubTree(root, key, value);}
        size++;
        
    }
    //===============================================================================================
    // Removing an element from the key

    public void remove(E key){
        root = removeFromSubTree(root, key);
    }

    private BinaryTreeNode<E, V> removeFromSubTree(BinaryTreeNode<E, V> node, E key){
        // If there is no such node
        if (node == null){
            return null;
        }

        // Variable for comparisons
        int c = key.compareTo(node.getKey());
        if (c < 0){
            node.setLeft(removeFromSubTree(node.getLeft(), key)); }
        else if (c > 0){
            node.setRight(removeFromSubTree(node.getRight(), key)); }
        // else they are equal and the node is found
        else {
            // Check that the values match 
            // if (node.getValue().equals(value) == false){
            //     node.setRight(removeFromSubTree(node.getRight(), key, value)); }
             
            if (node.getLeft() == null) {
                return node.getRight(); }
            else if (node.getRight() == null){
                return node.getLeft(); }
            // Then the node has two child nodes
            else {
                BinaryTreeNode<E, V> smallestNode = getMin(node.getRight());
                node.setKey(smallestNode.getKey());
                node.setValue(smallestNode.getValue());
                node.setRight(removeFromSubTree(node.getRight(), smallestNode.getKey()));
            }
            
        }
        size--;
        return node;
    }

    private BinaryTreeNode<E, V> getMin(BinaryTreeNode<E, V> node){
        while (node.getLeft() != null){
            node = node.getLeft(); }
        return node;
    }

    //===============================================================================================

    private void inOrder(BinaryTreeNode<E, V> n) {
        if (n!=null) {
            inOrder(n.getLeft());
            System.out.print("\"" + n.getKey().toString()+"\" ");
            inOrder(n.getRight());
        }
    }

    /**
     * This prints the parent node, the left child node and the right 
     * 
     * @param n this is the current node
     */
    private void preOrder(BinaryTreeNode<E, V> n) {
       if (n != null) {
        System.out.print("\"" + n.getKey().toString()+"\" ");
        preOrder(n.getLeft());
        preOrder(n.getRight());

       }
    }

    /**
     * This prints the left node, right node and parent node 
     * 
     * @param n this is the current node
     */
    private void postOrder(BinaryTreeNode<E, V> n) {
        if (n != null) {
            postOrder(n.getLeft());
            postOrder(n.getRight());
            System.out.print("\"" + n.getKey().toString()+"\" ");
        }
    }

    public void traversalInOrder() {
        System.out.print("Inorder traversal: ");

        inOrder(root);

        System.out.println();
    }

    public void traversalPreOrder() {
        System.out.print("Preorder traversal: ");

        preOrder(root);

        System.out.println();
    }

    public void traversalPostOrder() {
        System.out.print("Postorder traversal: ");

        postOrder(root);

        System.out.println();
    }

    //===============================================================================================
    public void set(E key, V value) {
        if (root==null) {
            System.out.println("Adding "+key+" to root.");
            root = new BinaryTreeNode<>(key, value);
        }
        else {
            addOrSet(root, key, value);}
        size++;
    }

    // Adding a new element 
    private void addOrSet(BinaryTreeNode<E, V> node, E key, V value) {    
        E nodeKeyVal = node.getKey();
        if (key.compareTo(nodeKeyVal) < 0) {
            System.out.println("Adding "+ key +" to left sub-tree of "+nodeKeyVal);
            if (node.getLeft()==null)
                node.setLeft(new BinaryTreeNode<>(key, value));
            else
                addOrSet(node.getLeft(), key, value);
        }
        else if (key.compareTo(nodeKeyVal) > 0){
                System.out.println("Adding "+key +" to right sub-tree of "+nodeKeyVal);
                if (node.getRight()==null)
                    node.setRight(new BinaryTreeNode<>(key, value));
                else
                    addOrSet(node.getRight(), key, value); }
        else {
            node.setValue(value); }
        
    }
    //===============================================================================================
    // To traverse the tree in order
    private int inOrderT(BinaryTreeNode<E, V> node, Object[] arr, int i) {

        if (node!=null) {
            i = inOrderT(node.getLeft(), arr, i);
            arr[i++] = node.getValue(); //Ratings
            //i = i + 1;
            i = inOrderT(node.getRight(), arr, i);
        }
        return i;
    }

    public Rating[] traverse(){
        Rating[] arr = new Rating[size];
        inOrderT(root, arr, 0);
        return arr;
    }

}

