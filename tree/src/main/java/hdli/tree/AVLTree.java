package hdli.tree;

import java.util.Comparator;

public class AVLTree<T> {

    private static class AVLNode<T> {
        AVLNode(T element) {
            this(element, null, null);
        }

        AVLNode(T element, AVLNode<T> left, AVLNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }

        T element;
        AVLNode left;
        AVLNode right;
        int height;
    }

    private AVLNode<T> root;
    private Comparator<? super T> cmp;

    private int height(AVLNode<T> t) {
        return t == null ? -1 : t.height;
    }

    private int myCompare(T lhs, T rhs) {
        if(this.cmp != null) {

            return cmp.compare(lhs, rhs);
        } else {
            return ((Comparable)lhs).compareTo(rhs);}
    }

    private AVLNode<T> insert(T x, AVLNode<T> t) {
        if(t == null) {
            return new AVLNode<T>(x);
        }

        return null;
    }

}
