package data_structures.recursion;

public class AddTwoNumbersRecursively {
    public static void main(String[] args) {
        int[] l1 = {1, 2, 3}, l2 = {4, 5, 6};
        AddTwoNumbersRecursively obj = new AddTwoNumbersRecursively();
        ListNode ln1 = obj.init(l1);
        ListNode ln2 = obj.init(l2);
        System.out.println("Init complete \n" + ln1.printAll() + "\n" + ln2.printAll());
        ListNode ln = obj.addTwoNumbers(ln1, ln2);
        // numbers.addTwoNumbers(ln1,ln2);
    }

    private ListNode init(int[] array) {
        if (array.length == 0) return null;
        ListNode ln = null;
        for (int i = array.length - 1; i >= 0; i--) {
            ListNode tempNode = new ListNode(array[i], ln);//1,null,         2, 1               ,3, 2
            ln = tempNode;//ln=(1,null),       (2,1)       ,   (3,2)
        }
        return ln;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return addTwoNumbers(l1, l2, 0);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0)
            return null;
        int val1 = (l1 != null) ? l1.val : 0;
        int val2 = (l2 != null) ? l2.val : 0;
        int sum = val1 + val2 + carry;
        int digit = sum % 10;
        int ncarry = sum / 10;
        ListNode ln = new ListNode(digit);
        ln.next = addTwoNumbers((l1 != null) ? l1.next : null, (l2 != null) ? l2.next : null, ncarry);
        return ln;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }

        public String printAll() {
            String all = null;
            ListNode ln = this;
            while (ln != null) {
                all += this.toString();
                ln = ln.next;
            }
            return all;
        }
    }
}