import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;



public class Exp4 {

    public static void main(String[] args) throws IOException {

        FileWriter fileWriter = new FileWriter(args[2]);
        PrintWriter printWriter = new PrintWriter(fileWriter); // writing pointer


        float[][] cfi = new float[10][70];  // to store all values of counteries
        Countries[] countries = new Countries[70]; // to store countries with objects

        int a;
        for(a = 0; a < 70; a++){
            countries[a] = new Countries();
        }

        Countries[][] sortedCountries = new Countries[10][70];
        for(int b = 0; b < 10; b++){
            for(int c = 0; c < 70; c++){
                sortedCountries[b][c] = new Countries();
            }
        }

        String cifFile = args[0];

        String fileName = cifFile+"/africa"; // sending 1 by 1
        File[] file = new File(fileName).listFiles();
        int indexNumber = 0;
        addToList(file,cfi,indexNumber,countries);
        indexNumber += file.length;

        String fileName2 = cifFile+"/america";
        File[] file2 = new File(fileName2).listFiles();
        addToList(file2,cfi,indexNumber,countries);
        indexNumber += file2.length; // index number updates indexes to storei n array
        String fileName3 = cifFile+"/asia";
        File[] file3 = new File(fileName3).listFiles();
        addToList(file3,cfi,indexNumber,countries);
        indexNumber += file3.length;
        String fileName4 = cifFile+"/australia";
        File[] file4 = new File(fileName4).listFiles();
        addToList(file4,cfi,indexNumber,countries);
        indexNumber += file4.length;
        String fileName5 = cifFile+"/europe";
        File[] file5 = new File(fileName5).listFiles();
        addToList(file5,cfi,indexNumber,countries);
        indexNumber += file5.length;
        String fileName6 = cifFile+"/middle-east";
        File[] file6 = new File(fileName6).listFiles();
        addToList(file6,cfi,indexNumber,countries);
        indexNumber += file6.length;




        ArrayList<Countries> sortedArraylist = new ArrayList<Countries>(70); // tree sort part and a array list to return values of that tree sort
        TreeSort tree = new TreeSort();
        tree.treeins(cfi[0]);
        tree.sortPop(tree.root,countries,sortedArraylist);


        ArrayList<Countries> sortedArraylist1 = new ArrayList<Countries>(70);
        TreeSort tree1 = new TreeSort();
        tree1.treeins(cfi[1]);
        tree1.sortTotalLand(tree1.root,countries,sortedArraylist1);

        ArrayList<Countries> sortedArraylist2 = new ArrayList<Countries>(70);
        TreeSort tree2 = new TreeSort();
        tree2.treeins(cfi[2]);
        tree2.sortAreaLand(tree2.root,countries,sortedArraylist2);

        ArrayList<Countries> sortedArraylist3 = new ArrayList<Countries>(70);
        TreeSort tree3 = new TreeSort();
        tree3.treeins(cfi[3]);
        tree3.sortAreaWater(tree3.root,countries,sortedArraylist3);

        ArrayList<Countries> sortedArraylist4 = new ArrayList<Countries>(70);
        TreeSort tree4 = new TreeSort();
        tree4.treeins(cfi[4]);
        tree4.sortMedAgeM(tree4.root,countries,sortedArraylist4);

        ArrayList<Countries> sortedArraylist5 = new ArrayList<Countries>(70);
        TreeSort tree5 = new TreeSort();
        tree5.treeins(cfi[5]);
        tree5.sortMedAgeF(tree5.root,countries,sortedArraylist5);

        ArrayList<Countries> sortedArraylist6 = new ArrayList<Countries>(70);
        TreeSort tree6 = new TreeSort();
        tree6.treeins(cfi[6]);
        tree6.sortBRate(tree6.root,countries,sortedArraylist6);

        ArrayList<Countries> sortedArraylist7 = new ArrayList<Countries>(70);
        TreeSort tree7 = new TreeSort();
        tree7.treeins(cfi[7]);
        tree7.sortDRate(tree7.root,countries,sortedArraylist7);

        ArrayList<Countries> sortedArraylist8 = new ArrayList<Countries>(70);
        TreeSort tree8 = new TreeSort();
        tree8.treeins(cfi[8]);
        tree8.sortLitF(tree8.root,countries,sortedArraylist8);

        ArrayList<Countries> sortedArraylist9 = new ArrayList<Countries>(70);
        TreeSort tree9 = new TreeSort();
        tree9.treeins(cfi[9]);
        tree9.sortAirport(tree9.root,countries,sortedArraylist9);

        ArrayList<ArrayList<Countries>> allArrayLists = new ArrayList<>(); // one arraylist to save them all
        allArrayLists.add(sortedArraylist);
        allArrayLists.add(sortedArraylist1);
        allArrayLists.add(sortedArraylist2);
        allArrayLists.add(sortedArraylist3);
        allArrayLists.add(sortedArraylist4);
        allArrayLists.add(sortedArraylist5);
        allArrayLists.add(sortedArraylist6);
        allArrayLists.add(sortedArraylist7);
        allArrayLists.add(sortedArraylist8);
        allArrayLists.add(sortedArraylist9);


        String input = args[1]; // reading pointer
        File inputFile = new File(input);
        FileReader fr = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fr);

        String line;

        while((line = br.readLine()) != null){ // reading query file
            String[] parts = line.split("\\+"); // split from the +'s and check their lenght to find if it is 1 command or more

            if(parts.length == 1){ // if it is only one command
                String[] parts2 = parts[0].split(","); // split from the ,
                int len1 = Integer.parseInt(parts2[2]);
                List<String> list = commandFinder(parts2,allArrayLists); // my command finder algorithm which finds wanted command and returns cities to it
                printWriter.print("[");
                for(int i = 0 ; i < len1; i++){
                    if(i == len1-1){
                        printWriter.print(list.get(i)+"]");
                    }
                    else{
                        printWriter.print(list.get(i)+", ");
                    }

                }
                printWriter.println();
            } // len = 1
            else if(parts.length == 2){ // if there are 2 commands
                String[] parts2 = parts[0].split(",");
                String[] parts3 = parts[1].split(",");

                int len1 = Integer.parseInt(parts2[2]);
                int len2 = Integer.parseInt(parts3[2]);

                List<String> list = commandFinder(parts2,allArrayLists); // return list with countries in them
                List<String> list2 = commandFinder(parts3,allArrayLists);

                int totalLen = len1 + len2 ; // total len to go through in big list

                List<String> finalList = new ArrayList<>();
                for(int i = 0; i < len1; i++){ // add first than we will sort
                    finalList.add(list.get(i));
                 //   System.out.print(list.get(i)+" ");
                }
           //     System.out.println();
                for(int j = 0; j < len2; j++){
                    if(finalList.contains(list2.get(j))){ // if there are already a country with same name
                        totalLen--; // decrease by one
                        continue;
                    }
                    else{
                        finalList.add(list2.get(j));
                    }
                  //  System.out.print(list2.get(j)+" ");
                }
              //  System.out.println();
                sortList(finalList,totalLen); // my sort function which sorts final list
              //  Collections.sort(finalList);
                printWriter.print("[");
                for(int i = 0 ; i < totalLen ;i++){
                    if(i == totalLen-1){
                       printWriter.print(finalList.get(i)+"]");
                    }
                    else{
                        printWriter.print(finalList.get(i)+", ");
                    }
                }
                printWriter.println();
            }
            else if(parts.length == 3){ // if there are 3 commands
                String[] parts2 = parts[0].split(",");
                String[] parts3 = parts[1].split(",");
                String[] parts4 = parts[2].split(",");

                int len1 = Integer.parseInt(parts2[2]);
                int len2 = Integer.parseInt(parts3[2]);
                int len3 = Integer.parseInt(parts4[2]);

                List<String> list = commandFinder(parts2,allArrayLists);
                List<String> list2 = commandFinder(parts3,allArrayLists); // return lists
                List<String> list3 = commandFinder(parts4,allArrayLists);

                int totalLen = len1 + len2 + len3;

                List<String> finalList = new ArrayList<>();
                for(int i = 0; i < len1; i++){

                    finalList.add(list.get(i));
                  //  System.out.print(list.get(i)+" ");
                }
                for(int j = 0; j < len2; j++){
                    if(finalList.contains(list2.get(j))){
                        totalLen--;
                        continue;
                    }
                    else{
                        finalList.add(list2.get(j));
                    }
               //     System.out.print(list2.get(j)+" ");
                }
                for(int k = 0;k < len3;k++){
                    if(finalList.contains(list3.get(k))){
                        totalLen--;
                        continue;
                    }
                    else{
                        finalList.add(list3.get(k));
                    }
                 //   System.out.print(list3.get(k)+" ");
                }
             //   Collections.sort(finalList);
                sortList(finalList,totalLen);
                printWriter.print("[");
                for(int i = 0; i < totalLen ; i++){
                    if(i == totalLen-1){
                        printWriter.print(finalList.get(i)+"]");
                    }
                    else{
                        printWriter.print(finalList.get(i)+", ");
                    }
                }
                printWriter.println();
            }
        }

     /*   List<String> alitest = new ArrayList<>();
        alitest.add("Ali");
        alitest.add("Veli");
        alitest.add("Said");
        alitest.add("Mert");
        alitest.add("Ahmet");
        alitest.add("Can");
        alitest.add("Alican");
        alitest.add("Alik");
        sortList(alitest,8);
        for(int i = 0;i < 8; i++){
            System.out.println(alitest.get(i));
        }*/
        printWriter.close();
    } // main ends

    public static void addToList(File[] file,float[][] cfi,int indexNumber,Countries[] countries) throws IOException {
        int i;
        for(i = 0; i < file.length; i++){ // going through the cities in the file
            FileReader fr = new FileReader(file[i]);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){

                AtomicInteger flag1 = new AtomicInteger(0);
                BMS.search(line.toCharArray(),"\"Area\"".toCharArray(),flag1); // if you find the area value is near
                if(flag1.intValue() == 1){ // if found
                    line = br.readLine();
                    line = br.readLine();
                //    System.out.println(line.charAt(17));
                    int iter1 = 17;
                    String total = "";
                    int temp = 0;
                    int flag = 0;
                    while(line.charAt(17) != '"'){ // read the value
                        if(line.charAt(iter1) != ','){
                            if(line.charAt(iter1) == '.'){
                                total = total + line.charAt(iter1);
                                iter1++;
                                continue;
                            }
                            total = total + line.charAt(iter1); // concav the string
                            iter1++;
                        }
                        else{
                            iter1++;
                        }
                        if(line.charAt(iter1) == ' '){
                            if(line.charAt(iter1+1) == 'm'){
                                flag = 1;
                                break;
                            }
                            break;
                        }
                    }
                    float total1 = Float.parseFloat(total); // update it and load it into the array
                    if(flag == 1){
                        total1 = total1 * 1000000;
                    }
                    cfi[1][i+indexNumber] = total1;
                    countries[i+indexNumber].areatotal = total1;

                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    int iter2 = 17;
                    String total2 = "";
                    int temp2 = 0;
                    int bayrak = 0;
                    while(line.charAt(17) != '"'){
                        if(line.charAt(iter2) != ','){
                            if(line.charAt(iter2) == '.'){
                                total2 = total2 + line.charAt(iter2);

                                iter2++;
                                continue;
                            }
                            total2 = total2 + line.charAt(iter2);
                            iter2++;
                        }
                        else{
                            iter2++;
                        }
                        if(line.charAt(iter2) == ' '){
                            if(line.charAt(iter2+1) == 'm'){
                                bayrak = 1;
                                break;
                            }
                            break;
                        }
                    }

             //       System.out.println(total2);
                    float total3 = Float.parseFloat(total2);
                    if(bayrak == 1){
                        total3 = total3 * 1000000;
                    }
                    cfi[2][i+indexNumber] = total3;
                    countries[i+indexNumber].arealand = total3;



                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    int iter3 = 17;
                    String total4 = "";
                    int temp3 = 0;
                    while(line.charAt(17) != '"'){
                        if(line.charAt(iter3) != ','){
                            if(line.charAt(iter3) == '.'){
                                iter3++;
                                continue;
                            }
                            total4 = total4+ line.charAt(iter3);
                            iter3++;
                        }
                        else{
                            iter3++;
                        }
                        if(line.charAt(iter3) == ' '){
                            if(line.charAt(iter3+1) == 'm'){
                                total4 = total4 + "000";
                                break;
                            }
                            break;
                        }
                    }
            //        System.out.println(total4);

                    float total5 = Float.parseFloat(total4);
                    cfi[3][i+indexNumber] = total5;
                    countries[i+indexNumber].areawater = total5;
                }


                AtomicInteger flag = new AtomicInteger(0);
                BMS.search(line.toCharArray(),"People and Society".toCharArray(),flag);
                if(flag.intValue() == 1){
                    line = br.readLine();
                    line = br.readLine();
          //          System.out.println(line); // char at 15 first letter this is the line with number       "text": "14,546,961",
                    int iter = 15;
                    int bayrak1 = 0;
                    String population = "";
                    while(line.charAt(iter) != '"'){
                        if(line.charAt(iter) != ','){
                            population = population + line.charAt(iter);
                            iter++;
                        }
                        else{
                            iter++;
                        }
                        if(line.charAt(iter) == ' '){
                            if(line.charAt(iter+1) == 'm'){
                                bayrak1 = 1;
                            }
                            break;
                        }

                    }
                    // System.out.println(population); we have found the population as a string
               //     population = population + "f";

                    float pop = Float.parseFloat(population);
                    if(bayrak1 == 1){
                        pop = pop * 1000000;
                    }
                //    System.out.println(pop);
                    cfi[0][i+indexNumber] = pop;
                    countries[i+indexNumber].population = pop;
                } // population part ends
                AtomicInteger flag2 = new AtomicInteger(0);
                BMS.search(line.toCharArray(),"Median age".toCharArray(),flag2);


                if(flag2.intValue() == 1){
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                //    System.out.println(line.charAt(17));
                    int iter4 = 17;
                    String rate = "";
                    while(line.charAt(iter4) != '"'){
                        if(line.charAt(iter4) != ','){
                            rate = rate + line.charAt(iter4);
                            iter4++;
                        }
                        else{
                            iter4++;
                        }
                        if(line.charAt(iter4) == ' '){
                            break;
                        }

                    }
                    //System.out.println(rate);
                    float rateN = Float.parseFloat(rate);

                    cfi[4][i+indexNumber] = rateN;
                    countries[i+indexNumber].agemale = rateN;

                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    //    System.out.println(line.charAt(17));
                    int iter5 = 17;
                    String rate2 = "";
                    while(line.charAt(iter5) != '"'){
                        if(line.charAt(iter5) != ','){
                            rate2 = rate2 + line.charAt(iter5);
                            iter5++;
                        }
                        else{
                            iter5++;
                        }
                        if(line.charAt(iter5) == ' '){
                            break;
                        }

                    }
                    //System.out.println(rate);
                    float rateN2 = Float.parseFloat(rate2);
                    cfi[5][i+indexNumber] = rateN2;
                    countries[i+indexNumber].agefemale = rateN2;





                } // median age
                AtomicInteger prob1 = new AtomicInteger(0);
                BMS.search(line.toCharArray(),"Birth rate".toCharArray(),prob1);


                if(prob1.intValue() == 1){
                    line = br.readLine();
                    int iter6 = 15;
                    String brate = "";
                    while(line.charAt(iter6) != '"'){
                        if(line.charAt(iter6) != ','){
                            brate = brate + line.charAt(iter6);
                            iter6++;
                        }
                        else{
                            iter6++;
                        }
                        if(line.charAt(iter6) == ' '){
                            break;
                        }

                    }
                    float brateN = Float.parseFloat(brate);
                    cfi[6][i+indexNumber] = brateN;
                    countries[i+indexNumber].brate = brateN;
                }

                AtomicInteger prob2 = new AtomicInteger(0);
                BMS.search(line.toCharArray(),"Death rate".toCharArray(),prob2);

                if(prob2.intValue() == 1){
                    line = br.readLine();
                    int iter7 = 15;
                    String drate = "";
                    while(line.charAt(iter7) != '"'){
                        if(line.charAt(iter7) != ','){
                            drate = drate + line.charAt(iter7);
                            iter7++;
                        }
                        else{
                            iter7++;
                        }
                        if(line.charAt(iter7) == ' '){
                            break;
                        }

                    }
                    float drateN = Float.parseFloat(drate);
                    //        System.out.println(drateN);
                    cfi[7][i+indexNumber] = drateN;
                    countries[i+indexNumber].drate = drateN;
                }

                AtomicInteger flag3 = new AtomicInteger(0);
                BMS.search(line.toCharArray(),"Literacy".toCharArray(),flag3);

                if(flag3.intValue() == 1){
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    line = br.readLine();
                    int iter8 = 17;
                    String lit = "";
                    while(line.charAt(iter8) != '"'){
                        if(line.charAt(iter8) != ','){
                            lit = lit + line.charAt(iter8);
                            iter8++;
                        }
                        else{
                            iter8++;
                        }
                        if(line.charAt(iter8) == '%'){
                            break;
                        }

                    }
                  //  System.out.println(i);
                    try {
                        float litN = Float.parseFloat(lit);
                        cfi[8][i+indexNumber] = litN;
                        countries[i+indexNumber].litfemale = litN;
                    }
                    catch (NumberFormatException e){

                    }



                } // literacy ends
                AtomicInteger finalflag = new AtomicInteger(0);
                BMS.search(line.toCharArray(),"conventional short form".toCharArray(),finalflag);
                if(finalflag.intValue() == 1){
                    line = br.readLine();
                    int iter10 = 17;
                    String name = "";
                    while(line.charAt(iter10) != '"'){
                        if(line.charAt(iter10) != ','){
                            name = name + line.charAt(iter10);
                            iter10++;
                        }
                        else{
                            iter10++;
                        }
                        if(line.charAt(iter10) == '"' || line.charAt(iter10) == '('){
                            break;
                        }

                    }
                //    System.out.println(name);
                    countries[i+indexNumber].name = name;
                }

                AtomicInteger flag4 = new AtomicInteger(0);
                BMS.search(line.toCharArray(),"Airports".toCharArray(),flag4);

                if(flag4.intValue() == 1){
                    line = br.readLine();
                    int iter9 = 15;
                    String air = "";
                    while(line.charAt(iter9) != '"'){
                        if(line.charAt(iter9) != ','){
                            air = air + line.charAt(iter9);
                            iter9++;
                        }
                        else{
                            iter9++;
                        }
                        if(line.charAt(iter9) == ' '){
                            break;
                        }

                    }

                    float airN = Float.parseFloat(air);
              //      System.out.println(airN);
                    cfi[9][i+indexNumber] = airN;
                    countries[i+indexNumber].airports = airN;

                    break;
                }


            }


        }
    }
    public static List<String> commandFinder(String[] parts2,ArrayList<ArrayList<Countries>> sortedArraylist2){
        if(parts2[0].equals("area-total")){ // my function to find given command and give a list with wanted countries
            if(parts2[1].equals("top")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(1).get(69-i).name); // get the top n country
                }
                sortList(list,num);
                return list;
            }
            else if(parts2[1].equals("last")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(1).get(i).name); // get the last n country
                }
             //   Collections.sort(list);
                sortList(list,num);
                return list;
            }
        } // area total
        else if(parts2[0].equals("population")){
            if(parts2[1].equals("top")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(0).get(69-i).name);
                }
                //Collections.sort(list);
                sortList(list,num);
                return list;
            }
            else if(parts2[1].equals("last")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(0).get(i).name);
                }
             //   Collections.sort(list);
                sortList(list,num);
                return list;
            }
        }
        else if(parts2[0].equals("area-land")){
            if(parts2[1].equals("top")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(2).get(69-i).name);
                }
              //  Collections.sort(list);
                sortList(list,num);
                return list;
            }
            else if(parts2[1].equals("last")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(2).get(i).name);
                }
            //    Collections.sort(list);
                sortList(list,num);
                return list;
            }
        }
        else if(parts2[0].equals("area-water")){
            if(parts2[1].equals("top")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(3).get(69-i).name);
                }
            //    Collections.sort(list);
                sortList(list,num);
                return list;
            }
            else if(parts2[1].equals("last")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(3).get(i).name);
                }
          //      Collections.sort(list);
                sortList(list,num);
                return list;
            }
        }
        else if(parts2[0].equals("median_age-male")){
            if(parts2[1].equals("top")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(4).get(69-i).name);
                }
                //Collections.sort(list);
                sortList(list,num);
                return list;
            }
            else if(parts2[1].equals("last")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);

                    list.add(sortedArraylist2.get(4).get(i).name);
                }
        //        Collections.sort(list);
                sortList(list,num);
                return list;
            }
        }
        else if(parts2[0].equals("median_age-female")){
            if(parts2[1].equals("top")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(5).get(69-i).name);
                }
      //          Collections.sort(list);
                sortList(list,num);
                return list;
            }
            else if(parts2[1].equals("last")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(5).get(i).name);
                }
              //  Collections.sort(list);
                sortList(list,num);
                return list;
            }
        }
        else if(parts2[0].equals("birth_rate")){
            if(parts2[1].equals("top")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(6).get(69-i).name);
                }
      //          Collections.sort(list);
                sortList(list,num);
                return list;
            }
            else if(parts2[1].equals("last")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(6).get(i).name);
                }
               // Collections.sort(list);
                sortList(list,num);
                return list;
            }
        }
        else if(parts2[0].equals("death_rate")){
            if(parts2[1].equals("top")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(7).get(69-i).name);
                }
          //      Collections.sort(list);
                sortList(list,num);
                return list;
            }
            else if(parts2[1].equals("last")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(7).get(i).name);
                }
               // Collections.sort(list);
                sortList(list,num);
                return list;
            }
        }
        else if(parts2[0].equals("literacy-female")){
            if(parts2[1].equals("top")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(8).get(69-i).name);
                }
                //Collections.sort(list);
                sortList(list,num);
                return list;
            }
            else if(parts2[1].equals("last")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(8).get(i).name);
                }
               // Collections.sort(list);
                sortList(list,num);
                return list;
            }
        }
        else if(parts2[0].equals("airports")){
            if(parts2[1].equals("top")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(9).get(69-i).name);
                }
               // Collections.sort(list);
                sortList(list,num);
                return list;
            }
            else if(parts2[1].equals("last")){
                int num = Integer.parseInt(parts2[2]);
                List<String>  list = new ArrayList<>();
                for(int i = 0; i < num; i++){
                    // System.out.print(sortedArraylist2.get(187-i).name);
                    list.add(sortedArraylist2.get(9).get(i).name);
                }
        //        Collections.sort(list);
                sortList(list,num);
                return list;
            }
        }
        return null;
    }

    public static void sortList(List<String> list, int lenght){ // my sorting algortihm for strings
        List<String> finalList = new ArrayList<>();
        for(int i = 0; i < lenght-1 ; i++){
            String min = list.get(i);
            int index = i;
            for(int j = i + 1; j < lenght; j++){ // find min
                if(list.get(j).compareTo(min) < 0 ){
                    min = list.get(j);
                    index = j;
                }
                else if(list.get(j).compareTo(min) > 0){
                    continue;
                }
            }
            String temp = list.get(i); // exchange min with current and move on
            list.set(i,list.get(index));
            list.set(index,temp);
            finalList.add(min);
        }

    }
}
