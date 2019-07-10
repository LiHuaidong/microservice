    package hdli.DataStructures;

    // Basic node stored in leftist heaps
    // Note that this class is not accessible outside
    // of package DataStructures

    class LeftHeapNode
    {
            // Constructors
        LeftHeapNode( java.lang.Comparable theElement )
        {
            this( theElement, null, null );
        }

        LeftHeapNode(java.lang.Comparable theElement, LeftHeapNode lt, LeftHeapNode rt )
        {
            element = theElement;
            left    = lt;
            right   = rt;
            npl     = 0;
        }

            // Friendly data; accessible by other package routines
        java.lang.Comparable element;      // The data in the node
        LeftHeapNode left;         // Left child
        LeftHeapNode right;        // Right child
        int          npl;          // null path length
    }
