package data_structures.linked_lists;

import data_structures.linked_lists.Node;

public class MyLinkedList {
    Node head;

    // Function to insert a node at the end of the list
    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Function to delete all even nodes
    public void deleteEvenNodes() {
        if (head == null) return;
        Node temp = head;
        while (temp != null) {
            if (temp.data % 2 == 0 && head == temp) {
                head = findNextNonEvenNode(temp);
            } else if (temp.next != null && temp.next.data % 2 == 0) {
                Node n = findNextNonEvenNode(temp.next);
                temp.next = n;
            }
            temp = temp.next;
        }
    }

    private Node findNextNonEvenNode(Node temp) {
        while (temp != null && temp.data % 2 == 0) {
            temp = temp.next;
        }
        return temp;
    }

    // Function to print the linked list
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        int[] numbers = {8, 1, 2, 3, 4, 5, 6, 8};
        //int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
        //int[] numbers = {8, 2, 22, 1, 7, 8};
        //int[] numbers = {2,4,6,8,4,2,8};
        //int[] numbers = {};
        for (int num : numbers) {
            list.insert(num);
        }
        System.out.println("Original List:");
        list.printList();
        list.deleteEvenNodes();

        System.out.println("List after deleting even nodes:");
        list.printList();
    }
}
