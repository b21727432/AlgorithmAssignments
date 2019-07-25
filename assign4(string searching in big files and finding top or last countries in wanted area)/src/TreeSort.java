import java.util.ArrayList;

// Java program to
// implement Tree Sort
class TreeSort
{
    // Class containing left and
    // right child of current
    // node and key value
    class Node
    {
        float key;
        Node left, right;

        public Node(float item)
        {
            key = item;
            left = right = null;
        }
    }

    // Root of BST
    Node root;

    // Constructor
    TreeSort()
    {
        root = null;
    }

    // This method mainly
    // calls insertRec()
    void insert(float key)
    {
        root = insertRec(root, key);
    }

    /* A recursive function to
    insert a new key in BST */
    Node insertRec(Node root, float key)
    {

		/* If the tree is empty,
		return a new node */
        if (root == null)
        {
            root = new Node(key);
            return root;
        }

		/* Otherwise, recur
		down the tree */
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        /* return the root */
        return root;
    }

    // A function to do
    // inorder traversal of BST
    void sortPop(Node root, Countries[] countries, ArrayList<Countries> sortedArraylist)
    {
        if (root != null)
        {
            sortPop(root.left,countries,sortedArraylist);
            for(int i = 0; i < 70; i++){
                if(root.key == countries[i].population){
                    sortedArraylist.add(countries[i]);
                }
            }
            sortPop(root.right,countries,sortedArraylist);
        }
    }
    void sortTotalLand(Node root,Countries[] countries,ArrayList<Countries> sortedArraylist)
    {
        if (root != null)
        {
            sortTotalLand(root.left,countries,sortedArraylist);
            for(int i = 0; i < 70; i++){
                if(root.key == countries[i].areatotal){
                    sortedArraylist.add(countries[i]);
                }
            }
            sortTotalLand(root.right,countries,sortedArraylist);
        }
    }
    void sortAreaLand(Node root,Countries[] countries,ArrayList<Countries> sortedArraylist)
    {
        if (root != null)
        {
            sortAreaLand(root.left,countries,sortedArraylist);
            for(int i = 0; i < 70; i++){
                if(root.key == countries[i].arealand){
                    sortedArraylist.add(countries[i]);
                }
            }
            sortAreaLand(root.right,countries,sortedArraylist);
        }
    }
    void sortAreaWater(Node root,Countries[] countries,ArrayList<Countries> sortedArraylist)
    {
        if (root != null)
        {
            sortAreaWater(root.left,countries,sortedArraylist);
            for(int i = 0; i < 70; i++){
                if(root.key == countries[i].areawater){
                    sortedArraylist.add(countries[i]);
                }
            }
            sortAreaWater(root.right,countries,sortedArraylist);
        }
    }
    void sortMedAgeM(Node root,Countries[] countries,ArrayList<Countries> sortedArraylist)
    {
        if (root != null)
        {
            sortMedAgeM(root.left,countries,sortedArraylist);
            for(int i = 0; i < 70; i++){
                if(root.key == countries[i].agemale){
                    sortedArraylist.add(countries[i]);
                }
            }
            sortMedAgeM(root.right,countries,sortedArraylist);
        }
    }
    void sortMedAgeF(Node root,Countries[] countries,ArrayList<Countries> sortedArraylist)
    {
        if (root != null)
        {
            sortMedAgeF(root.left,countries,sortedArraylist);
            for(int i = 0; i < 70; i++){
                if(root.key == countries[i].agefemale){
                    sortedArraylist.add(countries[i]);
                }
            }
            sortMedAgeF(root.right,countries,sortedArraylist);
        }
    }
    void sortBRate(Node root,Countries[] countries,ArrayList<Countries> sortedArraylist)
    {
        if (root != null)
        {
            sortBRate(root.left,countries,sortedArraylist);
            for(int i = 0; i < 70; i++){
                if(root.key == countries[i].brate){
                    sortedArraylist.add(countries[i]);
                }
            }
            sortBRate(root.right,countries,sortedArraylist);
        }
    }
    void sortDRate(Node root,Countries[] countries,ArrayList<Countries> sortedArraylist)
    {
        if (root != null)
        {
            sortDRate(root.left,countries,sortedArraylist);
            for(int i = 0; i < 70; i++){
                if(root.key == countries[i].drate){
                    sortedArraylist.add(countries[i]);
                }
            }
            sortDRate(root.right,countries,sortedArraylist);
        }
    }
    void sortLitF(Node root,Countries[] countries,ArrayList<Countries> sortedArraylist)
    {
        if (root != null)
        {
            sortLitF(root.left,countries,sortedArraylist);
            for(int i = 0; i < 70; i++){
                if(root.key == countries[i].litfemale){
                    sortedArraylist.add(countries[i]);
                }
            }
            sortLitF(root.right,countries,sortedArraylist);
        }
    }
    void sortAirport(Node root,Countries[] countries,ArrayList<Countries> sortedArraylist)
    {
        if (root != null)
        {
            sortAirport(root.left,countries,sortedArraylist);
            for(int i = 0; i < 70; i++){
                if(root.key == countries[i].airports){
                   // System.out.println(countries[i].name);
                    sortedArraylist.add(countries[i]);
                }
            }
            sortAirport(root.right,countries,sortedArraylist);
        }
    }
    void treeins(float arr[])
    {
        for(int i = 0; i < arr.length; i++)
        {
            insert(arr[i]);
        }

    }


}
/*Some cod fragments of this code is taken from geeks for geeks.Such as creating tree.But other methods such as tree
sorting elements in the tree and taking them into an array is written by me (Ali Kayadibi)*/
// This code is contributed
// by Vibin M
