package hdli.DataStructures;

// Basic node stored in red-black trees
// Note that this class is not accessible outside
// of package DataStructures

class RedBlackNode {

	static final int BLACK = 1;    // Black must be 1
	static final int RED = 0;

	// Constructors
	RedBlackNode(java.lang.Comparable theElement) {
		this(theElement, null, null);
	}

	RedBlackNode(java.lang.Comparable theElement, RedBlackNode lt, RedBlackNode rt) {
		element = theElement;
		left = lt;
		right = rt;
		color = BLACK;
	}

	// Friendly data; accessible by other package routines
	java.lang.Comparable element;    // The data in the node
	RedBlackNode left;       // Left child
	RedBlackNode right;      // Right child
	int color;      // Color
}
