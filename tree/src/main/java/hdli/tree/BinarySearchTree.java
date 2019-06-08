package hdli.tree;

import java.util.Comparator;

public class BinarySearchTree<T> {

    private static class BinaryNode<T>{
        T element;
        BinaryNode left;
        BinaryNode right;

        BinaryNode(T element){
            this.element = element;
        }

        BinaryNode(T element, BinaryNode lt, BinaryNode rt) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

    }

    private BinaryNode<T> root;
    private Comparator<? super T> cmp;

    public BinarySearchTree(){
        root = null;
    }

    public BinarySearchTree(Comparator<? super T> cmp){
        this.root = null;
        this.cmp = cmp;
    }

    private int myCompare(T lhs, T rhs) {
        if(this.cmp != null) {

            return cmp.compare(lhs, rhs);
        } else {
            return ((Comparable)lhs).compareTo(rhs);}
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }

        return findMin(t.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }

        return findMax(t.right);
    }

}