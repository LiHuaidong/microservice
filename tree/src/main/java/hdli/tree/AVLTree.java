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
        AVLNode<T> left;
        AVLNode<T> right;
        int height;
    }

    private AVLNode<T> root;
    private Comparator<? super T> cmp;

    private int height(AVLNode<T> t) {
        return t == null ? -1 : t.height;
    }

    private int myCompare(T lhs, T rhs) {
        if (this.cmp != null) {
            return cmp.compare(lhs, rhs);
        } else {
            return ((Comparable) lhs).compareTo(rhs);
        }
    }

    private AVLNode<T> insert(T x, AVLNode<T> t) {
        if (t == null) {
            return new AVLNode<T>(x);
        }

        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) { //左右子树不平衡
                if (myCompare(x, t.left.element) < 0) {
                    rotateWithLeftChild(t);
                } else {
                    doublieRotateWithLeftChild(t);
                }
            }
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (myCompare(x, t.right.element) > 0) {
                    rotateWithRightChild(t);
                } else {
                    doublieRotateWithRightChild(t);
                }
            }
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private AVLNode<T> remove(T x, AVLNode<T> t) {
        return null;
    }

    private AVLNode<T> rotateWithLeftChild(AVLNode<T> oldRoot) {
        AVLNode<T> newRoot = oldRoot.left;
        oldRoot.left = newRoot.right;
        newRoot.right = oldRoot;
        oldRoot.height = Math.max(height(oldRoot.left), height(oldRoot.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }

    private AVLNode<T> rotateWithRightChild(AVLNode<T> oldRoot) {
        AVLNode<T> newRoot = oldRoot.right;
        oldRoot.right = newRoot.left;
        newRoot.left = oldRoot;
        oldRoot.height = Math.max(height(oldRoot.left), height(oldRoot.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }

    private AVLNode<T> doublieRotateWithLeftChild(AVLNode<T> oldRoot) {
        oldRoot.left = rotateWithRightChild(oldRoot.left);
        return rotateWithLeftChild(oldRoot);
    }

    private AVLNode<T> doublieRotateWithRightChild(AVLNode<T> oldRoot) {
        oldRoot.right = rotateWithLeftChild(oldRoot.right);
        return rotateWithRightChild(oldRoot);
    }

}
