package hdli.tree;

import java.util.Comparator;

public class BinarySearchTree<T> {

    private static class BinaryNode<T>{
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

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

    public void insert(T x) {}

    public BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<T>(x, null, null);
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        }
        else if(compareResult > 0) {
            t.right = insert(x, t.right);
        }
        return t;
    }

    public BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return null;
        }

        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else {
            if (t.left != null && t.right != null) { // 2个孩子的情形
                t.element = findMin(t.right).element;
                t.right = remove(t.element, t.right);
            } else {
                t = t.left != null ? t.left : t.right;
            }
        }

        return t;
    }

    private void printTree(BinaryNode<T> t){
        if(t == null) {
            return;
        }

        System.out.print(t.element);

        printTree(t.left);
        printTree(t.right);
    }

}