import java.util.*;
class Node{
    protected int data;
   protected Node prev,next;
    Node(int d){
        data = d;
        prev = null;
        next = null;
    }
}
class list{
    void insertAtStart(int val){
        
    }
}
public class CDLlist{
    public static void main(String[]args){
        Node n = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n.next = n2;
        n2.prev = n;
        n2.next = n3;
        n3.prev = n2;
        Node t = n;
        while(t != null){
            System.out.print(t.data +" <-> ");
            t = t.next;
        }
    }
}
