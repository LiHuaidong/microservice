package hdli.tree.RedBlack;

public class RedBlackNode {

    static final int BLACK = 1;    // Black must be 1
    static final int RED = 0;

    Comparable element;
    RedBlackNode left;
    RedBlackNode right;
    int color;

    RedBlackNode(Comparable element) {
        this.element = element;
    }

    RedBlackNode(Comparable element, RedBlackNode lt, RedBlackNode rt) {
        element = element;
        left = lt;
        right = rt;
        color = BLACK;
    }

}
