// Eduardo Tafner Obladen
// BCC - Manhã - Turma A

enum PrintType {
    PRE_ORDER_TRANSVERSAL,
    IN_ORDER_TRANSVERSAL,
    POST_ORDER_TRANSVERSAL
}

public class Main {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Criando e inserindo elementos em ordem na arvore binaria... (1)");
        System.out.println();

        BinaryTree tree = new BinaryTree();

        tree.insert(14);
        tree.insert(15);
        tree.insert(4);
        tree.insert(9);
        tree.insert(7);
        tree.insert(18);
        tree.insert(3);
        tree.insert(5);
        tree.insert(16);
        tree.insert(4);
        tree.insert(20);
        tree.insert(17);
        tree.insert(9);
        tree.insert(14);
        tree.insert(5);

        System.out.println("Imprimindo em preordem: (2)");
        tree.print(PrintType.PRE_ORDER_TRANSVERSAL); // 14 4 3 9 7 5 15 18 16 17 20

        System.out.println("Imprimindo em inordem: (2)");
        tree.print(PrintType.IN_ORDER_TRANSVERSAL); // 3 4 5 7 9 14 15 16 17 18 20

        System.out.println("Imprimindo em posordem: (2)");
        tree.print(PrintType.POST_ORDER_TRANSVERSAL); // 3 5 7 9 4 17 16 20 18 15 14

        System.out.println();

        System.out.println("Removendo maior elemento... (3)");
        tree.removeMax();

        System.out.println();

        System.out.println("Imprimindo em in ordem (novamente): ");
        tree.print(PrintType.IN_ORDER_TRANSVERSAL); // 3 4 5 7 9 14 15 16 17 18

        System.out.println();

        System.out.println("Removendo menor elemento... (4)");
        tree.removeMin();

        System.out.println();

        System.out.println("Imprimindo em in ordem (novamente): ");
        tree.print(PrintType.IN_ORDER_TRANSVERSAL); // 4 5 7 9 14 15 16 17 18

        System.out.println();

        System.out.println("Removendo elemento 14... (5)");
        tree.remove(14);

        System.out.println();

        System.out.println("Imprimindo em in ordem (novamente): ");
        tree.print(PrintType.IN_ORDER_TRANSVERSAL); // 4 5 7 9 15 16 17 18 

    }

}

class BinaryTree {

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    // 1. Escreva um algoritmo para inserir um elemento em uma árvore binária de busca.

    public void insert(int value) {
        this.root = insertRecursive(this.root, value);
    }

    private Node insertRecursive(Node current, int value) {

        if (current == null) { return new Node(value); }

        if (value < current.getValue()) {
            current.setLeft(insertRecursive(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(insertRecursive(current.getRight(), value));
        }

        return current;

    }

    // 2. Escreva algoritmos para percorrer uma árvore binária nas formas préordem, inordem e pósordem.

    public void print(PrintType printType) {
        this.print(this.root, printType);
        System.out.println();
    }

    public void print(Node current, PrintType printType) {

        if (current == null) { return; }

        switch (printType) {
            case PRE_ORDER_TRANSVERSAL -> {
                System.out.print(current.getValue() + " ");
                print(current.getLeft(), PrintType.PRE_ORDER_TRANSVERSAL);
                print(current.getRight(), PrintType.PRE_ORDER_TRANSVERSAL);
            }
            case IN_ORDER_TRANSVERSAL -> {
                print(current.getLeft(), PrintType.IN_ORDER_TRANSVERSAL);
                System.out.print(current.getValue() + " ");
                print(current.getRight(), PrintType.IN_ORDER_TRANSVERSAL);
            }
            case POST_ORDER_TRANSVERSAL -> {
                print(current.getLeft(), PrintType.POST_ORDER_TRANSVERSAL);
                print(current.getRight(), PrintType.POST_ORDER_TRANSVERSAL);
                System.out.print(current.getValue() + " ");
            }
        }

    }

    // 3. Escreva um algoritmo para remover o maior elemento de uma árvore binária de busca.

    public void removeMax() {
        this.root = removeMaxNode(this.root);
    }

    private Node removeMaxNode(Node current) {

        if (current == null) { return null; }

        if (current.getRight() == null) {
            return current.getLeft();
        } else {
            current.setRight(removeMaxNode(current.getRight()));
            return current;
        }

    }

    // 4. Escreva um algoritmo para remover o menor elemento de uma árvore binária de busca.

    public void removeMin() {
        this.root = removeMinNode(this.root);
    }

    private Node removeMinNode(Node current) {
        if (current == null) {
            return null;
        } else if (current.getLeft() == null) {
            return current.getRight();
        } else {
            current.setLeft(removeMinNode(current.getLeft()));
            return current;
        }
    }

    // 5. Escreva um algoritmo que remova um determinado elemento com valor N da árvore.

    public void remove(int value) {
        this.root = removeNode(this.root, value);
    }

    private Node removeNode(Node current, int value) {

        if (current == null) { return null; }

        if (value < current.getValue()) {
            current.setLeft(removeNode(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(removeNode(current.getRight(), value));
        } else {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }
            else if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            }
            else {
                Node successor = findMinNode(current.getRight());
                current.setValue(successor.getValue());
                current.setRight(removeNode(current.getRight(), successor.getValue()));
            }
        }

        return current;
    }

    private Node findMinNode(Node current) {
        if (current.getLeft() == null) { return current; }
        return findMinNode(current.getLeft());
    }

}

class Node {

    private int value;

    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;

        left = null;
        right = null;
    }

    public int getValue() {
        return this.value;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}