import java.io.*;

public class Exp2 {
    static FileWriter fileWriter; // global file writer to write into output file
    static PrintWriter printWriter; //  global file writer to write into output
    public static void main(String[] args) throws IOException {
       // BST tree = new BST();

         fileWriter = new FileWriter(args[1]); // writing pointer
         printWriter = new PrintWriter(fileWriter);

        String fileName = args[0]; // reading pointer
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;

        BST tree = new BST(); // first null node with no elements


        while((line = br.readLine()) != null){ // reading input line line by line

         //   System.out.println(line);

            String[] parts = line.split(" "); // first part from the space we check conditions with if else conditions
            if(parts[0].compareTo("CreateBST")==0){ // if our command is create bst we will read indexes

                printWriter.print("BST created with elements:");
                tree.root = null;
                String[] elements = parts[1].split(","); // elements to read
                int i;
                for(i=0;i<elements.length;i++){
                    tree.insert(Integer.parseInt(elements[i]));

                }
                tree.dispInOrder(tree.root);  // writing ordered structure of the tree
                printWriter.print("\n");

            }
            else if(parts[0].compareTo("CreateBSTH")==0){ // if it is bsth we will check h



                int h = Integer.parseInt(parts[1]);
                if(h <= 0){
                    printWriter.print("error");
                }
                else{

                    tree.root = null;
                printWriter.print("A full BST created with elements:");
                int number =(int) Math.pow(2,h+1); // max number of elements

              //  int a = number/2;
                int i;
                int y = number / 2;
                int z = y;

                for(i = 0; i < Math.log(number)/Math.log(2); i++){ // giving elements to get perfect full balanced tree

                    while(true){
                        tree.insert(y);
                        if(y+z >= number){
                            break;
                        }
                        else{
                            y = y + z;
                        }
                    }

                    y = z / 2;
                    z = y;
                }

                tree.dispInOrder(tree.root); // sorted display of tree
                printWriter.print("\n");
                }
            }
            else if(parts[0].compareTo("FindHeight")==0){ // if command is findheight
                int height = tree.findHeight(tree.root) - 1;
                printWriter.println("Height:"+height);
            }
            else if(parts[0].compareTo("FindWidth")==0){ // if command is find width
                int width = tree.findMaxWidth(tree.root);
                printWriter.println("Width:"+width);
            }
            else if(parts[0].compareTo("Preorder")==0){ // if command is preorder
                printWriter.print("Preorder:");
                tree.dispPreorder(tree.root);
                printWriter.print("\n");
            }
            else if(parts[0].compareTo("LeavesAsc")==0){ // all leaves of our tree ( no childs)
               printWriter.print("LeavesAsc:");
                tree.printLeaves(tree.root);
                printWriter.print("\n");
            }
            else if(parts[0].compareTo("DelRoot")==0){ // delete the root , find min of right subtree replace it with the current node, delete that node

                if(tree.root == null){ // if root is null we cant delete
                    printWriter.write("error"+"\n");
                }
                else{
                    printWriter.println("Root Deleted:"+tree.root.value);
                    tree.root = tree.deleteRoot(tree.root,tree.root.value);
                }


            }
            else if(parts[0].compareTo("DelRootLc")==0){ // delete the left child of the root
                if(tree.root.left == null){ // if left is null we cant delete left link
                    printWriter.write("error"+"\n");
                }
                else{
                    printWriter.println("Left Child of Root Deleted:"+tree.root.left.value);
                    tree.root = tree.deleteRoot(tree.root,tree.root.left.value);
                }

            }
            else if(parts[0].compareTo("DelRootRc")==0){
                if(tree.root.right == null){ // if right link is null and we want to delete right node
                    printWriter.write("error"+"\n");
                }
                else{
                    printWriter.println("Right Child of Root Deleted:"+tree.root.right.value);
                    tree.root = tree.deleteRoot(tree.root,tree.root.right.value);
                }

            }




        }
        printWriter.close();
    }


}
