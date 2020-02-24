/* David Perry
 * 30010019
 * 17 February 2020
 * A program for a Balanced Binary Search Tree to hold a list of Parts
 */
package portfolioq2;

public class BinarySearchTree<E> {

    // Node class
    class Node {
        // Declaring fields

        private String data;
        private Node left;
        private Node right;

        public Node(String data) {
            this.data = data;
        }
    }

    // Declaring the root node (head of the tree)
    private Node root;

    // Default constructor
    public BinarySearchTree() {
    }

    // Method to add data to the tree, if the tree is empty: data will added to root, else Recursive Insert method called
    public String add(String data) {
        Node newItem = new Node(data);
        if (root == null) {
            root = newItem;
        } else {
            root = recursiveAdd(root, newItem);
        }
        String addedItem = newItem.toString();
        return addedItem;
    }

    // Method to insert data into the tree by comparing if data is greater to the node
    // Is less than, the left if block executes. If greater than, the right if block executes
    // Balance tree method called to keep the tree in balance
    private Node recursiveAdd(Node current, Node value) {
        if (current == null) {
            current = value;
            return current;
        } else if (value.data.compareTo(current.data) < 0) {
            current.left = recursiveAdd(current.left, value);
            current = balanceTree(current);
        } else if (value.data.compareTo(current.data) > 0) {
            current.right = recursiveAdd(current.right, value);
            current = balanceTree(current);
        }
        return current;
    }

    // Method to balance tree, if the tree is weighted to one side by no more than one level, no changes needed
    // Other wise the if blocks will call on Rotate methods to required side of the tree
    private Node balanceTree(Node current) {
        int balance = balanceFactor(current);
        if (balance > 1) {
            if (balanceFactor(current.left) > 0) {
                current = rotateLL(current);
            } else {
                current = rotateLR(current);
            }
        } else if (balance < -1) {
            if (balanceFactor(current.right) > 0) {
                current = rotateRL(current);
            } else {
                current = rotateRR(current);
            }
        }
        return current;
    }

    // Method to delete, void method accepts the data input, calls Delete method
    public void delete(String target) {
        root = delete(root, target);
        System.out.println(target + " was deleted");
    }

    // Method to delete input, target data compared the the element to determine which block to execute
    // The block moves the target and balances the tree while recursively called back upon the Delete method
    // The target will finnaly be returned as null and the tree will remain balanced
    private Node delete(Node current, String target) {
        Node parent;
        if (current == null) {
            return null;
        } else {
            //left subtree
            if (target.compareTo(current.data) < 0) {
                current.left = delete(current.left, target);
                if (balanceFactor(current) == -2) {
                    if (balanceFactor(current.right) <= 0) {
                        current = rotateRR(current);
                    } else {
                        current = rotateRL(current);
                    }
                }
            } //right subtree
            else if (target.compareTo(current.data) > 0) {
                current.right = delete(current.right, target);
                if (balanceFactor(current) == 2) {
                    if (balanceFactor(current.left) >= 0) {
                        current = rotateLL(current);
                    } else {
                        current = rotateLR(current);
                    }
                }
            } //if target is found
            else {
                if (current.right != null) {
                    parent = current.right;
                    while (parent.left != null) {
                        parent = parent.left;
                    }
                    current.data = parent.data;
                    current.right = delete(current.right, parent.data);
                    if (balanceFactor(current) == 2)//rebalancing
                    {
                        if (balanceFactor(current.left) >= 0) {
                            current = rotateLL(current);
                        } else {
                            current = rotateLR(current);
                        }
                    }
                } else {
                    return current.left;
                }
            }
        }

        return current;
    }

    // Calls method to find data, prints messages of the outcome
    public void find(String key) {
        if (find(key, root).data.equals(key)) {
            System.out.println(key + " was found");
        } else {
            System.out.println("Nothing found");
        }
    }

    // Method compares data through the tree nodes moving left or right dpeneding on result
    // The Equals method is used and once found the block will return the data
    private Node find(String target, Node current) {

        if (target.compareTo(current.data) < 0) {
            if (target.equals(current.data)) {
                return current;
            } else {
                return find(target, current.left);
            }
        } else {
            if (target.equals(current.data)) {
                return current;
            } else {
                return find(target, current.right);
            }
        }

    }

    // Find max, calls method to find the max value and prints the outcome
    public String findMax() {
        String max = findMax(root).data;
        if (max != null) {
            System.out.println("Max tree value is: " + max);
        } else {
            System.out.println("Tree is empty");
        }
        return max;

    }

    // Method to find the max value, looks the right of the tree value is determined
    private Node findMax(Node node) {
        Node max;
        if (node != null) {
            if (node.right != null) {
                node = findMax(node.right);
            }
            max = node;
            return max;
        } else {
            return null;
        }
    }

    // Find min, calls method to find the min value and prints the outcome
    public String findMin() {
        String min = findMin(root).data;
        if (min != null) {
            System.out.println("Min tree value is: " + min);
        } else {
            System.out.println("Tree is empty");
        }
        return min;
    }

    // Method to find the max value, looks the right of the tree value is determined
    private Node findMin(Node node) {
        Node min;
        if (node != null) {
            if (node.left != null) {
                node = findMin(node.left);
            }
            min = node;
            return min;
        } else {
            return null;
        }
    }

    // Method to display tree, calls another method to determine order
    public void displayTree() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        inOrderDisplayTree(root);
        System.out.println("\n");
    }

    // Mehtod to display tree in order, calls left values to the right
    private void inOrderDisplayTree(Node current) {
        if (current != null) {
            inOrderDisplayTree(current.left);
            System.out.print("\n" + current.data);
            inOrderDisplayTree(current.right);

        }

    }

    // Method to return the max of left or right depending on condition, used in Get Height method
    private int max(int left, int right) {
        return left > 0 ? left : right;
    }

    // Method to display the height of the tree, height is determined by how many levels there are, calls get height mehtod
    public int height() {
        int height = getHeight(root);
        if (root == null) {
            System.out.println("Tree is empty");

        }
        System.out.println("There are " + getHeight(root) + " levels on this Binary Tree");
        return height;
    }

    // Method to determine the height of the tree which is used in the balance methods
    // Whichever side of tree is higher will be added by one to deetermine height
    private int getHeight(Node current) {
        int height = 0;
        if (current != null) {
            int left = getHeight(current.left);
            int right = getHeight(current.right);
            int maximum = max(left, right);
            height = maximum + 1;
        }
        return height;
    }

    // Method to return the balance difference value
    private int balanceFactor(Node current) {
        int left = getHeight(current.left);
        int right = getHeight(current.right);
        int balance = left - right;
        return balance;
    }

    // Method called in balancing methods, Rotates data Right Right
    private Node rotateRR(Node parent) {
        Node pivot = parent.right;
        parent.right = pivot.left;
        pivot.left = parent;
        return pivot;
    }

    // Method called in balancing methods, Rotates data Left Left
    private Node rotateLL(Node parent) {
        Node pivot = parent.left;
        parent.left = pivot.right;
        pivot.right = parent;
        return pivot;
    }

    // Method called in balancing methods, Rotates data Left Right
    private Node rotateLR(Node parent) {
        Node pivot = parent.left;
        parent.left = rotateRR(pivot);
        return rotateLL(parent);
    }

    // Method called in balancing methods, Rotates data Right Left
    private Node rotateRL(Node parent) {
        Node pivot = parent.right;
        parent.right = rotateLL(pivot);
        return rotateRR(parent);
    }

    // Method to return length of tree, calls method
    public int length() {
        int length = getLength(root);
        return length;
    }

    // Method to determine length of thre tree, this is the total nodes of data 
    private int getLength(Node current) {
        int length = 0;
        if (current != null) {
            int left = getHeight(current.left);
            int right = getHeight(current.right);
            //int m = max(left, right);
            length = left + right + 1;
        }
        return length;
    }

    // Main method  
    // Binary search tree created, using the Parts class as the data type
    // Various methods called, adding, deleting, searching the tree, displaying results
    public static void main(String[] args) {

        BinarySearchTree<Parts> parts = new BinarySearchTree<>();
        
        Parts p1 = new Parts("Piston");
        Parts p2 = new Parts("Brake Pads");
        Parts p3 = new Parts("Oil Filter");
        Parts p4 = new Parts("Exhaust Pipe");
        Parts p5 = new Parts("Washer");
        Parts p6 = new Parts("Radiator");
        Parts p7 = new Parts("Fan Belt");
        Parts p8 = new Parts("Gasket");
        Parts p9 = new Parts("Air Filter");
        Parts p10 = new Parts("Turbo");
        Parts p11 = new Parts("Wheel Nut");
        Parts p12 = new Parts("Hub Cap");
        parts.add(p1.getName());
        parts.add(p2.getName());
        parts.add(p3.getName());
        parts.add(p4.getName());
        parts.add(p5.getName());
        parts.add(p6.getName());
        parts.add(p7.getName());
        parts.add(p8.getName());
        parts.add(p9.getName());
        parts.add(p10.getName());
        parts.add(p11.getName());
        parts.add(p12.getName());
        System.out.println("");
        System.out.println("-----Parts List-----");
        parts.displayTree();
        parts.find("Turbo");
        parts.delete("Washer");
        parts.delete("Hub Cap");
        System.out.println("");
        System.out.println("-----Updated Parts List-----");
        parts.displayTree();
        parts.findMax();
        parts.findMin();
        parts.height();

    }

}
