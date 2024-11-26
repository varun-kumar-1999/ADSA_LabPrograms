//import java.io.*;
import java.util.Scanner;

class Node {
    int val;
    Node next, prev;

    public Node(int element) {
        this.val = element;
        this.next = null;
        this.prev = null;
    }
}

class CircularDoublyLinkedList {
    private Node head;
    private int size = 0;

    public CircularDoublyLinkedList() {
        this.head = null;
    }
    public int getSize(){
        return size;
    }
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            newNode.next = head;
            newNode.prev = head;
        } else {
            newNode.next = head;
            newNode.prev = head.prev;
            head.prev.next = newNode;//last node
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
   public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            newNode.next = head;
            newNode.prev = head;
        } else {
            newNode.next = head;
            newNode.prev = head.prev;
            head.prev.next = newNode;
            head.prev = newNode;
        }
        size++;
    }

    // Insert at a given position
    public void insertAtPosition(int data, int position) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            newNode.next = head;
            newNode.prev = head;
        } else {
            Node temp = head;
            if(position == 1){
                insertAtBeginning(data);
                return;
            }
            for (int i = 2; i < position-1; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            newNode.prev = temp;
            temp.next.prev = newNode;
            temp.next = newNode;
        }
        size++;
    }

    public void deleteAtPos(int pos){
        if(head == null){
            System.out.println("List is Empty\n");
            return;
        }
        if(head.next == head && pos == 1){
            head = null;
            return;
        }
        Node tmp = head;
         // If position is 1, delete at the beginning
        if(pos == 1){
            head.prev.next = head.next;
            head.next.prev = head.prev;
            head = head.next;
        }
        else{
            for(int i = 1;i<pos;i++){
                tmp = tmp.next;
            }
            tmp.prev.next = tmp.next;
            tmp.next.prev = tmp.prev;

            if(tmp == head.prev)//last node delete
                head.prev = tmp.prev;
        }
        size--;
    }

    // Display the linked list
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = head;
        do {
            System.out.print(current.val + " ");
            current = current.next;
        } while (current != head);
        System.out.println();
    }
}

public class circular_dll {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        char ch = ' ';
        do {
            System.out.println("\nCircular Doubly Linked List Operations\n" +
                    "1. Insert at beginning\n" +
                    "2. Insert at end\n" +
                    "3. Insert at position\n" +
                    "4. Delete at position\n" +
                    "5. display\n" +
                    "6. Get size");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter integer element to insert");
                    list.insertAtBeginning(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Enter integer element to insert");
                    list.insertAtEnd(scan.nextInt());
                    break;
                case 3:
                    System.out.println("Enter integer element to insert");
                    int num = scan.nextInt();
                    System.out.println("Enter position");
                    int pos = scan.nextInt();
                    if (pos < 1 || pos > list.getSize())
                        System.out.println("Invalid position\n");
                    else
                        list.insertAtPosition(num, pos);
                    break;
                 case 4:
                    System.out.println("Enter position");
                    int p = scan.nextInt();
                    if (p < 1 || p > list.getSize())
                        System.out.println("Invalid position\n");
                    else
                        list.deleteAtPos(p);
                    break;
                case 5:
                    list.display();
                    break;
                case 6:
                    System.out.println("size is:- " +list.getSize());
                    break;
                default:
                    System.out.println("Wrong choice!!!!\n");
                    break;
            }
                System.out.println("\nDo you want to continue(Type y or n)\n");
                ch=scan.next().charAt(0);
            
        }while(ch == 'Y' || ch == 'y');
        list.display();
        scan.close();
    }
}