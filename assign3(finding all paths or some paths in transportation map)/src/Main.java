import java.io.*;
import java.util.ArrayList;


    public class Main {

    public static void main(String[] args) throws IOException {

        FileWriter fileWriter = new FileWriter(args[2]);
        PrintWriter printWriter = new PrintWriter(fileWriter);


        String fileName = args[0]; // reading pointer
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;

        ArrayList<City> network = new ArrayList<City>();


    //    ArrayList<String> airway = new ArrayList<String>();
   //     ArrayList<String> highway = new ArrayList<String>();
     //   ArrayList<String> railway = new ArrayList<String>();

    //    ArrayList<String> temp = airway;
        int flag = -1;
        int numOfCities = 0;
        int helper = 0;
        int helper2 = 0;
        ArrayList<String> cities = new ArrayList<String>();
        while((line = br.readLine()) != null){
            String[] parts = line.split(" ");

            if(parts[0].equals("Airway")){
                flag = 0;
                continue;
            }
            if(parts[0].equals("Highway")){
                flag = 1;
                continue;
            }
            if(parts[0].equals("Railway")){
                flag = 2;
                continue;
            }
            if(line.isEmpty()){
                continue;
            }

            if(flag == 0){
                City tempCity = new City();
                tempCity.name = parts[0];
                tempCity.airway = parts[1];
                network.add(tempCity);
                cities.add(parts[0]);
                numOfCities++;

            }
            else if(flag == 1){
                network.get(helper).highway = parts[1];
                helper++;
            }
            else if(flag == 2){
                network.get(helper2).railway = parts[1];
                helper2++;
            }


        } // reading part ends

        String fileName1 = args[1]; // reading pointer
        File file1 = new File(fileName1);
        FileReader fr1 = new FileReader(file1);
        BufferedReader br1 = new BufferedReader(fr1);

        String line1;


        while((line1 = br1.readLine()) != null){
        //    System.out.println(line1);
        //    printWriter.println(line1);
            String[] parts1 = line1.split(" ");

            for(int i = 0; i < parts1.length; i++){

                if(i == parts1.length-1){
                   // System.out.print(parts1[i]);
                    printWriter.print(parts1[i]);
                }
                else{
                //    System.out.print(parts1[i] + ", ");
                    printWriter.print(parts1[i] + ", ");
                }

            }
      //      System.out.println();
            printWriter.println();

            if(parts1[0].equals("Q1")){
                // q1 istanbul van 2 h
                String temp = "";
                int from = 0;
                int to = 0 ;
                for(int i=0; i<numOfCities;i++){
                    if(cities.get(i).equals(parts1[1])){
                        from = i;
                    }
                    else if(cities.get(i).equals(parts1[2])){
                        to = i;
                    }
                }
                ArrayList<String> paths = new ArrayList<>();
                allPaths(network, network.get(from),numOfCities,to,temp, cities,paths);

                int numOfRep = Integer.parseInt(parts1[3]);
                String wantedType = parts1[4];


                for(int i = 0; i < paths.size(); i++){
                    int counter = 0;
                    String[] route = paths.get(i).split(", ");
                    for(int j = 0; j < route.length; j++){

                        if(route[j].equals(wantedType)){
                            counter++;
                        }
                    }
                    if(counter >= numOfRep){
                     //   System.out.println(paths.get(i));
                        printWriter.println(paths.get(i));
                    }

                }
                if(paths.size() == 0){
                //    System.out.println("There is no way!");
                    printWriter.println("There is no way!");
                }
            }
            else if(parts1[0].equals("Q2")){
                // q2 antalya van kayseri
                String temp = "";
                int from = 0;
                int to = 0 ;
                for(int i=0; i<numOfCities;i++){
                    if(cities.get(i).equals(parts1[1])){
                        from = i;
                    }
                    else if(cities.get(i).equals(parts1[2])){
                        to = i;
                    }
                }

                ArrayList<String> paths = new ArrayList<>();
                allPaths(network, network.get(from),numOfCities,to,temp, cities,paths);

                String durak = parts1[3];

                for(int i = 0; i < paths.size(); i++){
                    if(paths.get(i).contains(durak)){
                      //  System.out.println(paths.get(i));
                        printWriter.println(paths.get(i));
                    }
                }


                if(paths.size() == 0){
             //       System.out.println("There is no way!");
                    printWriter.println("There is no way!");
                }

            }
            else if(parts1[0].equals("Q3")){
                //q3 izmir van a
                String temp = "";
                int from = 0;
                int to = 0 ;
                for(int i=0; i<numOfCities;i++){
                    if(cities.get(i).equals(parts1[1])){
                        from = i;
                    }
                    else if(cities.get(i).equals(parts1[2])){
                        to = i;
                    }
                }

                ArrayList<String> paths = new ArrayList<>();
                allPaths(network, network.get(from),numOfCities,to,temp, cities,paths);

                String type = parts1[3];

                int pathCount = 0;
                for(int i = 0 ; i < paths.size(); i++){

                    if(type.equals("A")){

                        if(paths.get(i).contains(", H,") || paths.get(i).contains(", R,")){
                   //         System.out.println(paths.get(i));
                            continue;
                        }
                        else{
                            pathCount++;
                      //      System.out.println(paths.get(i));
                            printWriter.println(paths.get(i));
                        }
                    }
                    else if(type.equals("H")){

                        if(paths.get(i).contains(", A,") || paths.get(i).contains(", R,")){
                            //         System.out.println(paths.get(i));
                            continue;
                        }
                        else{
                            pathCount++;
                    //        System.out.println(paths.get(i));
                            printWriter.println(paths.get(i));
                        }
                    }
                    else if(type.equals("R")){

                        if(paths.get(i).contains(", A,") || paths.get(i).contains(", H,")){
                            //         System.out.println(paths.get(i));
                            continue;
                        }
                        else{
                            pathCount++;
                     //       System.out.println(paths.get(i));
                            printWriter.println(paths.get(i));
                        }
                    }
                }

                if(pathCount == 0){
             //       System.out.println("There is no way!");
                    printWriter.println("There is no way!");
                }
            }
            else if(parts1[0].equals("Q4")){
                // q4 antalya van a3 h1 r0
                String temp = "";
                int from = 0;
                int to = 0 ;
                for(int i=0; i<numOfCities;i++){
                    if(cities.get(i).equals(parts1[1])){
                        from = i;
                    }
                    else if(cities.get(i).equals(parts1[2])){
                        to = i;
                    }
                }

                ArrayList<String> paths = new ArrayList<>();
                allPaths(network, network.get(from),numOfCities,to,temp, cities,paths);

                int airNum = 0,railNum = 0,highNum = 0;

                for(int i = 3 ; i < 6 ; i++){
                    if(parts1[i].charAt(0) == 'A'){
                        airNum = Character.getNumericValue(parts1[i].charAt(1));
                    }
                    else if(parts1[i].charAt(0) == 'H'){
                        highNum = Character.getNumericValue(parts1[i].charAt(1));
                    }
                    else if(parts1[i].charAt(0) == 'R'){
                        railNum = Character.getNumericValue(parts1[i].charAt(1));
                    }
                }

          //      airNum = Character.getNumericValue(parts1[3].charAt(1));
            //    highNum = Character.getNumericValue(parts1[4].charAt(1));
              //  railNum = Character.getNumericValue(parts1[5].charAt(1));





                for(int i = 0 ; i < paths.size(); i++){
                    int countA = 0,countH = 0,countR = 0;
                    String[] route = paths.get(i).split(", ");
                    for(int j = 0; j < route.length; j++){
                        if(route[j].equals("A")){
                            countA++;
                        }
                        else if(route[j].equals("H")){
                            countH++;
                        }
                        else if(route[j].equals("R")){
                            countR++;
                        }
                    }

                    if(countA == airNum && countH == highNum && countR == railNum){
                //        System.out.println(paths.get(i));
                        printWriter.println(paths.get(i));

                    }
                }



                if(paths.size() == 0){
           //         System.out.println("There is no way!");
                    printWriter.println("There is no way!");
                }


            }
            else if(parts1[0].equals("PRINTGRAPH")){


                for(int i = 0; i<numOfCities;i++){ // iterate over cities
                //    System.out.print(network.get(i).name + " --> ");
                    printWriter.print(network.get(i).name + " --> ");
                    for(int j = 0; j < numOfCities ; j++){

                        if(network.get(i).airway.charAt(j) == '1' || network.get(i).highway.charAt(j) == '1' || network.get(i).railway.charAt(j) == '1'){
            //                System.out.print(network.get(j).name + " ");
                            printWriter.print(network.get(j).name + " ");
                        }
                    }

           //         System.out.println();
                    printWriter.println();
                }



            }

        }
       /* for(int i = 0; i < numOfCities ; i++){
            System.out.println(network.get(i).airway);
            System.out.println(network.get(i).highway);
            System.out.println(network.get(i).railway);
            System.out.println();
        }*/
    /*   String temp = "";
       int target = 7;
       ArrayList<String> paths = new ArrayList<>();
       allPaths(network, network.get(1),numOfCities,target,temp, cities,paths);*/




        printWriter.close();
    } // main ends
    public static void allPaths(ArrayList<City> network,City city, int numOfCities, int target,String temp,ArrayList<String> cities,ArrayList<String> paths){
        for(int i = 0; i < 1; i++){
            if(city.name.equals(network.get(target).name)){
                paths.add(temp + city.name);
                //System.out.println(temp + city.name);
                break;
            }
            if(city.airway.contains("1")){
                for(int j = 0; j < numOfCities; j++){
                    if(city.airway.charAt(j) == '1'){
                      //  temp += city.name+" A ";
                       // System.out.print(city.name+" A ");
                        allPaths(network, network.get(j), numOfCities, target, temp + city.name + ", A, " , cities, paths);
                    }
                }
            }

          /*  else{
                System.out.println(temp);
                paths.add(temp);
                temp = null;

            }*/
             if(city.highway.contains("1")){
                for(int j = 0; j < numOfCities; j++){
                    if(city.highway.charAt(j) == '1'){
                   //     temp += city.name +" H ";
                     //   System.out.print(city.name+" H ");
                        allPaths(network, network.get(j), numOfCities,target, temp+city.name + ", H, " , cities, paths);
                    }
                }
            }

           /*  else{
                 System.out.println(temp);
                 paths.add(temp);
                 temp = null;

             }*/
             if(city.railway.contains("1")){
                for(int j = 0; j < numOfCities; j++){
                    if(city.railway.charAt(j) == '1'){
                       // temp += city.name +" R ";
                      //  System.out.print(city.name+" R ");
                        allPaths(network, network.get(j), numOfCities,target, temp+city.name + ", R, " , cities, paths);
                    }
                }
            }


           /*  else{
                 System.out.println(temp);
                 paths.add(temp);
                 temp = null;

             }*/

        }

    }

}
