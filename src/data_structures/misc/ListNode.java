package data_structures.misc;

public class ListNode {

    private int value;
    ListNode next;

    ListNode(int x) {
        this.value = x;
        this.next = null;
    }

    public ListNode() {

    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast!= null && fast.next != null) {
            slow = slow.next;
            System.out.println(slow.value);
            fast = fast.next.next;
            System.out.println(fast.value);
            System.out.println();

            if(slow==fast)
            {
                System.out.println("Found linked list at "+slow.value);
                return true;
            }

        }
        return false;
    }

    public ListNode initializeAndReturnHead(){
        ListNode n3=new ListNode(3);
        ListNode n7=new ListNode(7);
        ListNode n8=new ListNode(8);
        ListNode n9=new ListNode(9);
        ListNode n5=new ListNode(5);

        n3.next=n5;
        n5.next=n7;
        n7.next=n8;
        n8.next=n9;
        n9.next=n3;
        return n3;

    }

    public static void main(String[] args) {
        ListNode ln=new ListNode();
       ListNode head= ln.initializeAndReturnHead();
        System.out.println(ln.hasCycle(head));
    }
}
