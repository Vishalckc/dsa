package data_structures.trees.binary_search_tree;

import java.util.stream.IntStream;

public class LowestCommonAncestor {
    static Node ancestor = null;

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int arr[] = {2, 1, 3, -1, -1, 4, 5, 6, -1};
        IntStream.range(0, arr.length).forEach(i -> bst.insert(arr[i]));
        int a = 4;
        int b = 6;
        ancestor=bst.root;
        System.out.println(findLCA(bst.root, a, b).value);
    }

    private static Node findLCA(Node root, int v1, int v2) {
        if (root == null) {
            return null;
        }
        if (v1 == root.value) {
            if (v2 > v1) {
                Node n2 = checkNodeExists(root.right, v2);
                if (n2 != null) return root;
            } else {
                Node n2 = checkNodeExists(root.left, v2);
                if (n2 != null) return root;
            }
        } else if (v2 == root.value) {
            if (v2 > v1) {
                Node n1 = checkNodeExists(root.left, v1);
                if (n1 != null) return ancestor;
            } else {
                Node n1 = checkNodeExists(root.right, v2);
                if (n1 != null) return ancestor;
            }
        } else if (v1 < root.value && v2 < root.value) {
            return findLCA(root.left, v1, v2);
        } else if (v1 > root.value && v2 > root.value) {
            return findLCA(root.right, v1, v2);
        }
        return root;
    }

    private static Node checkNodeExists(Node root, int a) {
        if (root == null)
            return null;
        if (a == root.value) {
            return root;
        } else if (a < root.value) {
            return checkNodeExists(root.left, a);
        } else return checkNodeExists(root.right, a);
    }
}
