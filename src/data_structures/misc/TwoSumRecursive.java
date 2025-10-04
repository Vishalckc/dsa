package data_structures.misc;

import com.sun.tools.javac.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class TwoSumRecursive {
    public static void main(String[] args) {
        TwoSumRecursive obj = new TwoSumRecursive();
        List<Integer> l1 = List.of(2, 4, 9);
        List<Integer> l2 = List.of(5, 6, 4, 9);
        ListNode ln1 = null, ln2 = null;
        ListNode temp = null;
        ln1 = getListNode(l1, temp, ln1);
        temp = null;
        ln2 = getListNode(l2, temp, ln2);
        ListNode listNode = obj.addTwoNumbers(ln1, ln2);
        printList(listNode);
    }

    public ListNode resList = new ListNode(0);
    public ListNode head = resList;
    public int carry = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val + carry;
        carry = sum / 10;
        resList.next = new ListNode(sum % 10);
        resList = resList.next;

        if (l1.next != null && l2.next != null)
            addTwoNumbers(l1.next, l2.next);
        else if (l1.next != null)
            addTwoNumbers(l1.next, new ListNode(0));
        else if (l2.next != null)
            addTwoNumbers(new ListNode(0), l2.next);
        else if (carry > 0) {
            resList.next = new ListNode(1);
            resList = resList.next;
        }
        return head.next;
    }

    private static ListNode getListNode(List<Integer> l1, ListNode temp, ListNode ln1) {
        for (int i = 0; i < l1.size(); i++) {
            ListNode ln = new ListNode(l1.get(i));
            if (temp != null) {
                temp.next = ln;
                temp = temp.next;
            } else {
                ln1 = temp = ln;
            }
        }
        return ln1;
    }

    static class ListNode {
        int val;
        ListNode next;


        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static void printList(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

}
