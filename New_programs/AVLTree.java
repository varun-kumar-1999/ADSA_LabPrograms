import java.util.Scanner;

class Node {
    int val, height;
    Node left, right;

    Node(int d) {
        val = d;
        height = 1;
    }
}

class AVLTree {
    Node root;

    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    Node insert(Node root, int val) {
        if (root == null)
            return (new Node(val));

        if (val < root.val)
            root.left = insert(root.left, val);
        else if (val > node.val)
            root.right = insert(root.right, val);
        else
            return root;
        //UPDATE BALANCE
        root.height = 1 + max(height(root.left), height(root.right));

        int balance = getBalance(node);

        if (balance > 1 && val < root.left.val)
            return rightRotate(root);

        if (balance < -1 && val > root.right.val)
            return leftRotate(root);

        if (balance > 1 && val > root.left.val) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && val < root.right.val) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    Node deleteNode(Node root, int val) {
        if (root == null)
            return root;

        if (val < root.val)
            root.left = deleteNode(root.left, val);
        else if (val > root.val)
            root.right = deleteNode(root.right, val);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                temp = (temp == root.left)?root.right:root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } 
            else {
                Node temp = minValueNode(root.right);
                root.val = temp.val;
                root.right = deleteNode(root.right, temp.val);
            }
        }

       //UPDATE BALANCE
        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node search(Node root, int val) {
        if (root == null || root.val == val)
            return root;

        if (root.val < val)
            return search(root.right, val);

        return search(root.left, val);
    }

    int countNodes(Node root) {
        if (root == null)
            return 0;

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAVL Tree Operations:");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Count Nodes");
            System.out.println("5. Display Preorder");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            int val;

            switch (choice) {
                case 1:
                    System.out.println("Enter val to insert:");
                    val = scanner.nextInt();
                    tree.root = tree.insert(tree.root, val);
                    System.out.println(val + " inserted into the AVL tree.");
                    break;
                case 2:
                    System.out.println("Enter val to delete:");
                    val = scanner.nextInt();
                    tree.root = tree.deleteNode(tree.root, val);
                    System.out.println(val + " deleted from the AVL tree.");
                    break;
                case 3:
                    System.out.println("Enter val to search:");
                    val = scanner.nextInt();
                    Node searchResult = tree.search(tree.root, val);
                    if (searchResult != null)
                        System.out.println(val + " found in the AVL tree.");
                    else
                        System.out.println(val + " not found in the AVL tree.");
                    break;
                case 4:
                    int nodeCount = tree.countNodes(tree.root);
                    System.out.println("Number of nodes in the AVL tree: " + nodeCount);
                    break;
                case 5:
                    System.out.println("Preorder traversal of the AVL tree:");
                    tree.preOrder(tree.root);
                    System.out.println();
                    break;
                case 6:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
