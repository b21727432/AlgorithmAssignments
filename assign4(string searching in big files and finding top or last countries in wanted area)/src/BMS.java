import java.util.concurrent.atomic.AtomicInteger;

public class BMS {
    public static int NO_OF_CHARS = 256;

    //A utility function to get maximum of two integers
    public static int max (int a, int b) { return (a > b)? a: b; }

    //The preprocessing function for Boyer Moore's
    //bad character heuristic
    public static void badCharHeuristic( char []str, int size,int badchar[])
    {
        int i;

        // Initialize all occurrences as -1
        for (i = 0; i < NO_OF_CHARS; i++)
            badchar[i] = -1;

        // Fill the actual value of last occurrence
        // of a character
        for (i = 0; i < size; i++)
            badchar[(int) str[i]] = i;
    }

    /* A pattern searching function that uses Bad
    Character Heuristic of Boyer Moore Algorithm */
    public static void search( char txt[],  char pat[], AtomicInteger flag)
    {
        int m = pat.length;
        int n = txt.length;

        int badchar[] = new int[NO_OF_CHARS];


        badCharHeuristic(pat, m, badchar);

        int s = 0;  // s is shift of the pattern with
        // respect to text
        while(s <= (n - m))
        {
            int j = m-1;


            while(j >= 0 && pat[j] == txt[s+j])
                j--;


            if (j < 0)
            {
              //  System.out.println("Patterns occur at shift = " + s);
                flag.set(1);


                s += (s+m < n)? m-badchar[txt[s+m]] : 1;

            }

            else



       //         System.out.println(txt[s+j]+ "  " + badchar[txt[s+j]]+"noluyo");
          //      System.out.println(s + "  " + j + "  " + txt[s+j] + " " + Character.getNumericValue(txt[s+j]) );
           //     System.out.println( );
             //   System.out.println(Character.getNumericValue('\'')); -1 veriyor
                if(Character.getNumericValue(txt[s+j]) == -1){
                    s += 1;
                }
                else{
                    s += max(1, j - badchar[txt[s+j]]);
                }

        }


    }
}
/* This code is taken from geeks for geeks for educating purposes.This code is not written by me(Ali Kayadibi)
but i understood what this code does and how it does it. I also made some small changes such as returning a flag,
 that says searching string found in this file*/