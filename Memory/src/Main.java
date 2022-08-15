import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.util.*;
import java.io.*;
import java.lang.Thread;



class Main {

    public static int difficulty_level() {
        Scanner input = new Scanner(System.in);
        int level = 0;
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("[[ -------  MEMORY GAME   ver. 1.23 ------- ]]");
    /*        System.out.println("----------------------------------------------
            System.out.println("[[ (c) - 2022 A_paracoder ]]"); */
            System.out.print(" - Difficulty level - \n0. Super easy. \n1. Easy\n2. Hard\n3. Impossible\nYour choice: ");
            level = input.nextInt();
            if (level == 0 || level == 1 || level == 2 || level == 3) {
                System.out.print("Good luck. The game is now loading. Please wait");
                for (int i = 0; i < 22; i++) {
                    System.out.print(".");
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            System.out.println("You entered \"" + level + "\" and that is not a valid level.");
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        return level;
    }

    public static String[][] ret_array(int choice) {
        int chosen = choice;
        String[][] myArr_E = {
                {"  ", " 1 ", " 2 ", " 3 ", " 4 "},
                {"A ", " x ", " x ", " x ", " x "},
                {"B ", " x ", " x ", " x ", " x "}
        };
        String[][] myArr_H = {
                {"  ", " 1 ", " 2 ", " 3 ", " 4 "},
                {"A ", " x ", " x ", " x ", " x "},
                {"B ", " x ", " x ", " x ", " x "},
                {"C ", " x ", " x ", " x ", " x "},
                {"D ", " x ", " x ", " x ", " x "}
        };


        switch (chosen) {
            case 0:
                int iq = (int)(Math.random() * 151);
                System.out.println("You just won the game.\nYour estimated intelligence quotient is " + (200+iq) +
                        "\n \nTo keep your brain in excellent condition regularly repeat this game keeping at least quarterly intervals." +
                        "\nC O N G R A T U L A T I O N S !");
                System.exit(0);

            case 1:
                System.out.println();
                return myArr_E;


            case 2:
                System.out.println();
                return myArr_H;

            case 3:
                System.out.println("OK\n");
                //Wait
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("You are in a comfortable tunnel like hall. To the east there is the round green door.\n" +
                        "You see :\n" +
                        "    the wooden chest.\n" +
                        "    Gandalf. Gandalf is carrying a curious map.\n" +
                        "    Thorin.\n" +
                        "Gandalf gives the curious map to you. Thorin waits.\nSuddenly you have died!\nG A M E   O V E R");
                System.exit(0);

        }

    /*    if(chosen==1)
        {
            return myArr_E;
        }
        else
        {
            return myArr_H;
        } */
    return myArr_E;
    }


    public static String[] add_new_word(int n, String[] arr, String x){
       String[] newarr = new String[n + 1];
       for (int i = 0; i < n; i++)
           newarr[i] = arr[i];
       newarr[n] = x;
       return newarr;
   }

    public static String[] read_words_array(){
    String[] listofwords = {};
    try {
        File myObj = new File("Words.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            listofwords = add_new_word(listofwords.length, listofwords, data);
            }
        myReader.close();
        return listofwords;
        }

    catch (FileNotFoundException e) {
        System.out.println("Words file not found.");
         e.printStackTrace();
        }
        return listofwords;
    }

    public static String[] print_words_array(int choice){
    int chosen = choice;
    String[] aword = read_words_array();
    //System.out.println(aword.length);
    String[] drawn = {};
    if(chosen==1){
        for(int i=0; i<4; i++)
        {
            int random = (int)(Math.random() * aword.length);
            String aword_fromlist=aword[random];
            drawn=add_new_word(drawn.length, drawn, aword_fromlist);
            drawn=add_new_word(drawn.length, drawn, aword_fromlist);
        }
    }
    else{
        for(int i=0; i<8; i++)
        {
            int random = (int)(Math.random() * aword.length);
            String aword_fromlist=aword[random];
            drawn=add_new_word(drawn.length, drawn, aword_fromlist);
            drawn=add_new_word(drawn.length, drawn, aword_fromlist);
        }
    }
        return drawn;
    }

    public static String[][] wordset(int choice, String[] Shortlist){
        //shuffling
        List<String> shuffled = Arrays.asList(Shortlist);
        Collections.shuffle(shuffled);
        shuffled.toArray(Shortlist);
        int chosen = choice;

        if(chosen==1){
            String[][] proper_wordlist={
                {Shortlist[0], Shortlist[1], Shortlist[2], Shortlist[3]},
                {Shortlist[4], Shortlist[5], Shortlist[6], Shortlist[7]}
            };
            return proper_wordlist;
        }
        else{
            String[][] proper_wordlist={
                {Shortlist[0], Shortlist[1], Shortlist[2], Shortlist[3]},
                {Shortlist[4], Shortlist[5], Shortlist[6], Shortlist[7]},
                {Shortlist[8], Shortlist[9], Shortlist[10], Shortlist[11]},
                {Shortlist[12], Shortlist[13], Shortlist[14], Shortlist[15]}
            };
            return proper_wordlist;
        }
    }

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int reset=1;
    while(reset!=2)
    {
    int chosen=difficulty_level();
    String[][] x_matrix = ret_array(chosen);
    String[] drawnwords = print_words_array(chosen);
    String[][] shuffled_listofwords = wordset(chosen, drawnwords);
    // X matrix
    int max_points = (chosen==1) ? 4 : 8;
    int guess_chances = (chosen==1) ? 10 : 15;
    int now_points=0;
    String lvl = (chosen==1) ? "Easy" : "Hard";
    while(true)
    {
        System.out.println("---------------------------------------------------------------------\n"+"Level played: "+lvl+"\nGuess chances left: "+guess_chances);
        if(now_points==max_points)
        {
            System.out.println("----------------------------------------------\n" +
                               "--   C O N G R A T U L A T I O N S  ! ! !   --\n" +
                               "----------------------------------------------\n");
            break;
        }
        if(guess_chances==0)
        {
            System.out.println(" _______  _______  __   __  _______    _______  __   __  _______  ______   \n" +
                    "|       ||   _   ||  |_|  ||       |  |       ||  | |  ||       ||    _ |  \n" +
                    "|    ___||  |_|  ||       ||    ___|  |   _   ||  |_|  ||    ___||   | ||  \n" +
                    "|   | __ |       ||       ||   |___   |  | |  ||       ||   |___ |   |_||_ \n" +
                    "|   ||  ||       ||       ||    ___|  |  |_|  ||       ||    ___||    __  |\n" +
                    "|   |_| ||   _   || ||_|| ||   |___   |       | |     | |   |___ |   |  | |\n" +
                    "|_______||__| |__||_|   |_||_______|  |_______|  |___|  |_______||___|  |_|");
            break;
        }

        for (String[] row : x_matrix)
        {
            for (String x : row)
                // format the table
                System.out.printf("%-16s", x);
            System.out.print("\n");
        }
        System.out.println("---------------------------------------------------------------------\n");




    // Input coords
    System.out.print("Enter coordinates to flip\n(a letter followed by a digit - e.g. A1): ");
    String first_choice = input.next().toUpperCase();

    int pL=first_choice.charAt(0)-64;
    int pN=first_choice.charAt(1)-48;

    //Check!
    pL = (pL>4) ? 4 : pL;
    pL = (pL<1) ? 1 : pL;
    pN = (pN>4) ? 4 : pN;
    pN = (pN<1) ? 1 : pN;

    //Show first word
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("---------------------------------------------------------------------\n"+"Level: "+lvl+"\nGuess chances: "+guess_chances);
    x_matrix[pL][pN]=shuffled_listofwords[pL-1][pN-1];
    for (String[] row : x_matrix)
        {
            for (String x : row)
                System.out.printf("%-16s", x);
            System.out.print("\n");
        }
    System.out.println("---------------------------------------------------------------------\n");

    //prevent incorrect entry
    System.out.print("Enter coordinates to flip\n(a letter followed by a digit - e.g. B4): ");
    String second_choice = input.next().toUpperCase();

    int
            dL=second_choice.charAt(0)-64;
    int dN=second_choice.charAt(1)-48;

    dL = (dL>4) ? 4 : dL;
    dL = (dL<1) ? 1 : dL;
    dN = (dN>4) ? 4 : dN;
    dN = (dN<1) ? 1 : dN;

    //Show second word
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("---------------------------------------------------------------------\n"+"Level: "+lvl+"\nGuess chances: "+guess_chances);
    x_matrix[dL][dN]=shuffled_listofwords[dL-1][dN-1];
    for (String[] row : x_matrix)
        {
            for (String x : row)
                System.out.printf("%-16s", x);
            System.out.print("\n");
        }
  //  System.out.println("----------------------------------------------\n");
    // 46 - animated delay after second flip to memorize the revealed word
        if(shuffled_listofwords[pL-1][pN-1]==shuffled_listofwords[dL-1][dN-1] && second_choice.equals(first_choice)==false)
        {
        //    System.out.println("---------------------------------------------------------------------\n");
        } else {
            for (int i = 0; i < 69; i++) {
                System.out.print("-");
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("\n");
        }

    //CLS
    //Thread.sleep(1000);
    //System.out.print("Press any key: ");
    //String wait = input.next();

    System.out.print("\033[H\033[2J");
    System.out.flush();

    //Overwrite if success
    x_matrix[pL][pN]=" x ";
    x_matrix[dL][dN]=" x ";

    if(shuffled_listofwords[pL-1][pN-1]==shuffled_listofwords[dL-1][dN-1] && second_choice.equals(first_choice)==false){
        x_matrix[pL][pN]=shuffled_listofwords[pL-1][pN-1];
        x_matrix[dL][dN]=shuffled_listofwords[dL-1][dN-1];
        now_points++;
        guess_chances--;
    }
    else{
        guess_chances--;
    }

    //CLS
    System.out.print("\033[H\033[2J");
    System.out.flush();

    } //End of while

        System.out.println("Start over?\n1. Yes!\n2. The game is boring.\n");
        System.out.print("Your choice: ");
        reset=input.nextInt();
        //CLS
        System.out.print("\033[H\033[2J");
        System.out.flush();
    } //Restart
        System.out.println ("Boring? Maybe I'm just a simple Java program, but your words can hurt.\n" +
                "Keep that in mind and use them thoughtfully.\n        ┗|*´Д｀|┛");
    }
    //Finish


}
