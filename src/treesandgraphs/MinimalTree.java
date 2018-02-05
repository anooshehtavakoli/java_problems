package treesandgraphs;


/**
 *
 * 4.2: Given a sorted (increasing order) array with unique integer elements, write an algorithm
 * to create a binary search tree with minimal height.
 *
 * Created by anoosheh on 1/16/18.
 */
public class MinimalTree {
    /*
        to create a tree of minimal height, we need to match the number of nodes in the left subtree to
        the number of nodes in the right subtree as much as possible. this means that we want the root to be
        the middle of the array, since this would mean that half the elements would be less than the root and half
        would be greater than it.
        we continue this as the middle of each subsection of the array becomes the root of the node.
    */

    TreeNode createMinimalBST(int array[]) {
        return createMinimalBST(array, 0, array.length - 1);
    }

    TreeNode createMinimalBST(int arr[], int start, int end) {
        if (end <start) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode n = new TreeNode(arr[mid]);
        n.left = createMinimalBST(arr, start, mid -1);
        n.right = createMinimalBST(arr, mid +1, end);
        return n;
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
