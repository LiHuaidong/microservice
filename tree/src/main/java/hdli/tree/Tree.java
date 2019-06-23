package hdli.tree;

public class Tree {

    private static class TreeNode {
        String element;
        TreeNode firstChild;
        TreeNode nextSibling;
    }

    public void listAll(TreeNode treeNode) {
        System.out.println("element = [" + treeNode.element + "]");
        if (treeNode.firstChild != null) {
            listAll(treeNode.firstChild);
        }

        if (treeNode.nextSibling != null) {
            listAll(treeNode.nextSibling);
        }
    }


}
