public class Node {

    public int value; //attributes of our nodes links and values
    public  Node left;
    public  Node right;


    public Node(int value){ // constructor for creating node with value assigned to it
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
