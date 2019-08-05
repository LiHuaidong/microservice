package hdli.tree.RedBlack;

import java.nio.BufferUnderflowException;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:50 2019/6/17
 */
public class RedBlackTree {

    private RedBlackNode header;
    private static RedBlackNode nullNode;

    private RedBlackNode current;
    private RedBlackNode parent;
    private RedBlackNode grand;
    private RedBlackNode great;

    static{
        nullNode = new RedBlackNode(null);
        nullNode.left = nullNode.right = nullNode;
    }

    public RedBlackTree(Comparable negInf) {
        header = new RedBlackNode(negInf);
        header.left = header.right = nullNode;
    }

    public void insert(Comparable item) {
        current = parent = grand = header;
        nullNode.element = item;

        while(current.element.compareTo(item) != 0) {
            great = grand;
            grand = parent;
            parent = current;
            //current = item.compareTo(current.element)
        }
    }

//    private RedBlackNode rotate(AnyType item, RedBlackNode parent) {
//        if(compare(item, parent) < 0) {
//
//        }
//    }

    private final int compare(Comparable item, RedBlackNode t) {
        if(t == header) {
            return 1;
        } else {
            return item.compareTo(t.element);
        }



    }

    public boolean isEmpty() {
        return header.right == nullNode;
    }


    public Comparable findMin() {
        if(isEmpty()) {
            throw new BufferUnderflowException();
        }

        RedBlackNode itr = header.left;
        while(itr.left != nullNode) {
            itr = itr.left;
        }
        return itr.element;
    }

    public Comparable findMax() {
        if(isEmpty()) {
            throw new BufferUnderflowException();
        }

        RedBlackNode itr = header.right;
        while(itr.right != nullNode) {
            itr = itr.right;
        }
        return itr.element;
    }

    public boolean contains(Comparable x) {
        nullNode.element = x;
        current = header.right;
        return false;
    }

    public boolean Find(int target, int [][] array) {
        if(array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int outlength = array.length;
        int innnerLength = array[0].length;
        for(int i = 0; i<array.length; i++) {
            int[] inner = array[0];
            if(target >= inner[0] && target <= inner[innnerLength - 1]) {
                return true;
            }
        }
        return false;
    }

}
