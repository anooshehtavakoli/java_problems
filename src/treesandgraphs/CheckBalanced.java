package treesandgraphs;

/**
 * 4.4: Check Balanced: Implement a function to check if a binary tree is balanced. For the ourpose of this question,
 * a balanced tree is defined to be a tree such that the height of the two subtree of any node never differ by more than one.
 *
 * Created by anoosheh on 1/18/18.
 */
public class CheckBalanced {
    /*
    we can simply recurse through the entire tree, and for each node, compute the heights of each subtree.
     */

    int getHeight(TreeNode root) {
        if (root == null) {
            return -1;   // Base case
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;  // Base case
        }
        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1 ) {
            return false;
        } else { // Recure
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }


    class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;

        public TreeNode(int value) {
            this.value = value;
        }
    }

}
