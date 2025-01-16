package data_structures.trees;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Stream;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(9);
        TreeNode c = new TreeNode(20);
        TreeNode d = new TreeNode(15);
        TreeNode e = new TreeNode(7);
        a.left = b;
        a.right = c;
        c.left = d;
        c.right = e;
        List<List<Integer>> levelOrder = levelOrder(a);
        levelOrder.stream()
                .map(List::toArray)
                .map(Arrays::toString)
                .forEach(System.out::println);

    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> q = new ArrayBlockingQueue<>(2000);
        List<List<Integer>> list = new ArrayList<>();
        q.add(root);
        list.add(Collections.singletonList(root.val));
        while (q.peek() != null) {
            System.out.println(q.peek());
            List<Integer> l = new ArrayList<>();
            if (q.peek().left != null) {
                q.add(q.peek().left);
                l.add(q.peek().left.val);
            }
            if (q.peek().right != null) {
                q.add(q.peek().right);
                l.add(q.peek().right.val);
            }
            if (l.size() > 0) {
                list.add(l);
            }

            q.poll();
        }
        return list;
    }
}