package data_structures.misc;

public class BSTVerifierProgram {
    public static void main(String[] args) {
        BSTVerifierProgram obj = new BSTVerifierProgram();
        int[] sortedArray = {6, 5, 7, 9, 11, 13, 15};

        // Create a balanced binary tree
        Node root = createBinaryTree(sortedArray, 0, sortedArray.length - 1);
        System.out.println("In-order Traversal of the Binary Tree:");
        inOrderTraversal(root);
        System.out.println(obj.checkBST(root));
    }

    public static void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }
    public static Node createBinaryTree(int[] sortedArray, int start, int end) {
        // Base case: if start index is greater than end, return null
        if (start > end) {
            return null;
        }

        // Find the middle element and make it the root
        int mid = (start + end) / 2;
        Node root = new Node(sortedArray[mid]);

        // Recursively construct the left and right subtrees
        root.left = createBinaryTree(sortedArray, start, mid - 1);
        root.right = createBinaryTree(sortedArray, mid + 1, end);

        return root;
    }

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    boolean checkBST(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        boolean isBST = false;
        if (root.left != null) {
            isBST = root.left.data < root.data;
        }
        if (root.right != null) {
            isBST = isBST && (root.right.data > root.data);
        }
        return isBST && checkBST(root.left) && checkBST(root.right);
    }
}
