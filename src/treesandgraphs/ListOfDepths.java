package treesandgraphs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 4.3: Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth
 * (e.g, if you have a tree with depth D, you'll have D linked list)
 *
 * Created by anoosheh on 1/18/18.
 */
public class ListOfDepths {
    class DepthFirstSearch {
        /*
            we might think at first glance that this problem requires a level-by-level traversal, this isn't actually necessary.
            we can traverse the graph any way that we'd like, provided we know which level we're on as we do so.

            we can implement a simple modification of the pre-order traversal algorithm, where we pass in level + 1 to
            the next recursive call. The code below provides an implementation using depth-first-search.
        */

        void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
            if (root == null) {
                return; // base case
            }
            LinkedList<TreeNode> list = null;
            if (lists.size() == level) {
                // Level not contained in list
                list = new LinkedList<TreeNode>();
             /*
             Levels are always traversed in order. So, if this is the first time we've visited level i,
             we must have seen level 0 through i-1. we can therefore safely add the level at the end.
              */

                lists.add(list);
            } else {
                list = lists.get(level);
            }
            list.add(root);
            createLevelLinkedList(root.left, lists, level + 1);
            createLevelLinkedList(root.right, lists, level + 1);
        }

        ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
            ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
            createLevelLinkedList(root, lists, 0);
            return lists;
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

    class BreathFirstSearch{
        ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
            ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
            // "visit" the root
            LinkedList<TreeNode> current = new LinkedList<TreeNode>();
            if (root != null) {
                current.add(root);
            }

            while (current.size() > 0) {
                result.add(current);  // Add previous level
                LinkedList<TreeNode> parents = current;  // Go to next level
                current = new LinkedList<TreeNode>();
                for (TreeNode parent : parents) {
                    // Visit the children
                    if (parent.left != null) {
                        current.add(parent.left);
                    }
                    if (parent.right != null) {
                        current.add(parent.right);
                    }
                }
            }
            return result;
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


}
