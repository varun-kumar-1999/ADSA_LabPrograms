import java.util.Scanner;

class AVLNode {
    AVLNode left, right;
    int data;
    int height;

    public AVLNode(int n) {
        left = null;
        right = null;
        data = n;
        height = 0;
    }
}

class AVLTree {
    private AVLNode root;

    public AVLTree() {
        root = null;
    }

    public void insert(int data) {
        root = insert(data, root);
    }

     AVLNode insert(int x, AVLNode t) {
        if (t == null)
            t = new AVLNode(x);
        else if (x < t.data) {
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x < t.left.data)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithLeftChild(t);
            }
        } else if (x > t.data) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x > t.right.data)
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);
            }
        }
        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }

     int height(AVLNode t) {
        return t == null ? -1 : t.height;
    }

     int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

     AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

     AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }

     AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

     AVLNode doubleWithRightChild(AVLNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public void postorder(AVLNode r) {
        if (r != null) {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.data + " ");
        }
    }
}

public class avl {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        AVLTree avlt = new AVLTree();
        char ch;
        do {
            System.out.println("Enter integer element to insert");
            avlt.insert(scan.nextInt());
            System.out.println("Do you want to continue (Type y or n)");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');

        System.out.print("\nPost order: ");
        avlt.postorder();

        scan.close();
    }
}
