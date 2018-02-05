package treesandgraphs;

import javax.swing.tree.TreeNode;

/**
 * Created by anoosheh on 1/15/18.
 */
public class TreeImplementation {

    class Node {
        public String name;
        public Node[] children;
    }

    class Tree {
        public Node root;
    }

    class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public String value;

        // Method to print the node
        public void visit() {
            System.out.println(value);
        }
    }

    void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            node.visit();
            inOrderTraversal(node.right);
        }
    }

    void preOrderTraversal(TreeNode node) {
        if (node != null) {
            node.visit();
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    void postOrderTraversal(TreeNode node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            node.visit();
        }
    }


}
