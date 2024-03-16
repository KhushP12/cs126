package structures;

public class BinaryTreeNode<E extends Comparable<E>, V> {

    private E key;
    private V value;
    private BinaryTreeNode<E, V> left;
    private BinaryTreeNode<E, V> right;

    public BinaryTreeNode(E key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public E getKey(){
        return key;
    }

    public V getValue() { 
        return value; 
    }

    public BinaryTreeNode<E, V> getLeft() { 
        return left; 
    }

    public BinaryTreeNode<E, V> getRight() {
        return right; 
    }
    
    public void setKey(E k) {
        key = k;
    }

    public void setValue(V v) { 
        value = v; 
    }

    public void setLeft(BinaryTreeNode<E, V> p) {
        left = p; 
    }

    public void setRight(BinaryTreeNode<E, V> p) {
        right = p; 
    }

}