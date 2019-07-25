public class BST  {

    public static Node root; // root of our current tree
    public BST(){ // basic constructor for bst
        this.root = null;
    }
    public static void insert(int value){ // inserting values into trees we must check conditions
        Node newNode = new Node(value); // node with no connection
        if(root == null){ // if tree is empty
            root = newNode; // insert the first node as root node
            return;
        }
        Node temp = root;
        Node prev = null;
        while(temp != null){ // we must get the correct position to insert our node into our tree
            if(value > temp.value){ // if it is greater than our value
                prev = temp;
                temp = temp.right;
                if(temp == null){ // means we need to add
                        prev.right = newNode;
                        return;
                }
            }
            else if(value < temp.value){ // we must get to left because it is leseer
                prev = temp;
                temp = temp.left;
                if(temp == null){
                    prev.left = newNode; // update the node
                    return;
                }
            }
            else{
                return;
            }
        }

    } // insert end
    public static int findHeight(Node temp){ // find node with the most height
        if(temp == null){// we reach the null node we will return from here
            return 0;
        }
        else{
            int leftHeight = findHeight(temp.left); // go left until we reach null than we come back as we sum them up
            int rightHeight = findHeight(temp.right);

            // now we have 2 different heights of the tree so we must choose bigger

            if(rightHeight < leftHeight){ // means right is bigger than left
                return (leftHeight+1);
            }
            else{
                return (rightHeight+1); // means left height is bigger than right
            }

        }
    } // find height end
    public static int findMaxWidth(Node temp){ // find the max width of the tree ( shares the same level or height)
        int maxWidth = 0;
        int width;
        int h = findHeight(temp); //  we use height to go through our tree
        int i;

        for(i = 1; i <= h; i++){ // from height 1 to height h we will go through elements
            width = getWidth(temp ,i); // find width of given height
            if(width > maxWidth){
                maxWidth = width; // update if it is bigger than our max width until we find biggest
            }
        }

        return maxWidth;
    }  // get max witdh end

    public static int getWidth(Node temp, int height){ // recursively call this to get nodes that shares the same height
        if(temp == null){ // return from there
            return 0;
        }
        if(height == 1){
            return 1;
        }
        else if(height > 1){ // recursively call and find nodes with same heights
            return getWidth(temp.left, height - 1) + getWidth(temp.right, height - 1);
        }
        return 0;
    } // get width end

    public static void dispPreorder(Node temp){ // recursive pre order method  which prints before the other function calls
        if(temp != null){
            Exp2.printWriter.print(temp.value+" ");
            dispPreorder(temp.left); // print first call later
            dispPreorder(temp.right);
        }
    }

    public static void printLeaves(Node temp){ // if they dont have any child that means it is a leaf node
        if(temp == null){ // if empty
            return;
        }

        if(temp.left == null && temp.right == null){ // this means we ve found the leaf node print the value
            Exp2.printWriter.print(temp.value+" ");
        }



        if(temp.left != null){ // go recursively left to fid values in ascending order
            printLeaves(temp.left);
        }


        if(temp.right != null){ // go recursively right
            printLeaves(temp.right);
        }

    } // print leaves end
    public static Node deleteRoot(Node temp, int value){ // deletion part which takes values because we can delete left,right link of the root if needed
        if(temp == null){ // ending case
            return temp;
        }

        if (value < temp.value) // update to the left link of the root if it is delete left
            temp.left = deleteRoot(temp.left, value);
        else if (value > temp.value) // update to the right link of the root if it is delete right
            temp.right = deleteRoot(temp.right, value);
        else
        {
            // node with only one child or no child
            if (temp.left == null) // if left is null just update the right child
                return temp.right;
            else if (temp.right == null) // if right is null just update the left child
                return temp.left;

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            temp.value = minimumValue(temp.right); // minimum value in right subtree

            //
            temp.right = deleteRoot(temp.right, temp.value);
        }

        return temp;

    }

    public static int minimumValue(Node temp){ // find the minimum value in the right subtree
        int minimumValue = temp.value;
        while (temp.left != null)
        {
            minimumValue = temp.left.value; // set value as mnimum value because it is left subtree
            temp = temp.left;
        }
        return minimumValue; // return value
    }

    public static void dispInOrder(Node temp){ // inorder display , which is sorted in ascending order
        if(temp !=  null){
            dispInOrder(temp.left);
            Exp2.printWriter.print(temp.value+" "); // inorder we go left first than we print than we go left
            dispInOrder(temp.right);
        }
    }


}
