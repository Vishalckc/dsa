package data_structures.misc;

import com.sun.tools.javac.util.List;

import java.util.Stack;

/**
 * * Input: l1 = [2,4,3], l2 = [5,6,4]
 * * Output: [7,0,8]
 * * 342 + 465 = 807.
 * A    9,9,9,9,9,9,9
 * B    9,9,9,9
 * R    8,9,9,9,0,0,0,1
 **/
public class TwoSumIterative {
    public static void main(String[] args) {
        TwoSumIterative obj = new TwoSumIterative();
        /* List<Integer> l1 = List.of(2, 4, 3);
        List<Integer> l2 = List.of(5, 6, 4);        */
        List<Integer> l1 = List.of(9, 9, 9, 9, 9, 9, 9);
        List<Integer> l2 = List.of(9, 9, 9, 9);
        ListNode ln1 = null, ln2 = null;
        ListNode temp = null;

        ln1 = getListNode(l1, temp, ln1);
        temp = null;
        ln2 = getListNode(l2, temp, ln2);

        ListNode listNode = obj.addTwoNumbers(ln1, ln2);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack();
        Stack<ListNode> s2 = new Stack();
        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }
        int size = s1.size() > s2.size() ? s2.size() : s1.size();
        ListNode head;
        ListNode temp = null;
        head = temp;
        int carry = 0;
        for (int i = 0; i < size; i++) {
            int sum = s1.pop().val + s2.pop().val + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode n = new ListNode(sum);
            if (head == null) {
                temp = n;
                head = temp;
            } else {
                temp.next = n;
                temp = temp.next;
            }
        }

        while (!s1.isEmpty()) {
            int sum = s1.pop().val + carry;
            carry = sum / 10;
            sum = sum % 10;
            temp.next = new ListNode(sum);
            temp = temp.next;
        }
        while (!s2.isEmpty()) {
            int sum = s2.pop().val + carry;
            carry = sum / 10;
            sum = sum % 10;
            temp.next = new ListNode(sum);
            temp = temp.next;
        }
        if(carry>0) {
            temp.next=new ListNode(carry);
        }
        return head;
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
}