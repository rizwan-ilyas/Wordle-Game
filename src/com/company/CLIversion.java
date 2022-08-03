package com.company;
import java.util.Scanner;

// class for CLI version of game
public class CLIversion {
    Model model;    // model member of Model class
    char[][] greenChar;
    char[][] goldChar;
    char[][] grayChar;   // char datatypes for their catagories
    // char array for available char in the game
    char[] available = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    String[] word1; // words array for storing the entered words in the game
    static int c = 0; // static int for checking the number of tries by player


    public CLIversion() {
        model = new Model();      // creating/initializing model object of Model class
        word1 = new String[5];
        greenChar = new char[6][5];
        goldChar = new char[6][5];
        grayChar = new char[6][5];
        startGame();        // function call for starting cli version of game
    }

    /*
        function defination of startGame function
        it will start the game
        take inputs from the player
        will check the validity of word entered by player
        calling function for handling coloring catagory
        calling function for printing coloring catagory
        will terminate the game in case of win or loss
     */
    void startGame() {
        Scanner inputWor = new Scanner(System.in);
        do {
            System.out.print("Enter the Word : ");      // ask user to enter the word
            word1[c] = inputWor.next().toUpperCase();     // converting user entered word to upper case
            while (!word1[c].matches("^[a-zA-Z]+$") || word1[c].length() != 5) {    // input validation using regular expression
                System.out.println("Invalid Attempt..Try Again!!!");
                System.out.print("Enter the Word : ");
                word1[c] = inputWor.next().toUpperCase();
            }
            if (changeColor(word1[c])) {      // calling changeColor function and checking its returened value
                c++;        // incrementing c value
                printCatagories();      // calling the print function to pring the catagories
                if(model.isWin(word1[c - 1])){
                    break;
                }
            }
        } while (c < 6);

        // termination conding if player win or loss
        if (model.isWin(word1[c - 1])) {
            System.out.println("Congrats! You Win the Game");
        } else {
            System.out.println("You have lost the Game.");
        }

    }

    // function defination of change color
    boolean changeColor(String str) {
        int[] arr = model.checkWords(str);
        if (model.flage1) {// checking if flage1 is set
            boolean flage = false;
            for (int i = 0; i < 5; i++) {
                if (arr[i] == 0 || arr[i] == 1) {
                    flage = false;
                    break;
                }
                flage = true;
            }
            if (flage) {
                System.out.println("Error! No Match Case...(Try Again)  ");
                return false;
            }
        }

        for (int i = 0; i < 5; i++) {
            int firstNull;  // for checking the first null value in the array
            if (arr[i] == -1) { // updating the catagory
                firstNull = getFirstNull(grayChar[c]);
                grayChar[c][firstNull] = word1[c].charAt(i);  // adding char in graycolor catagory
                available[((int) word1[c].charAt(i)) - 65] = '\0';   // removing char from availble char
            } else if (arr[i] == 0) {
                firstNull = getFirstNull(greenChar[c]);
                greenChar[c][firstNull] = word1[c].charAt(i);     // adding char in green color catagory
                available[((int) word1[c].charAt(i)) - 65] = '\0';   // removing char from availble char
            } else {
                firstNull = getFirstNull(goldChar[c]);
                goldChar[c][firstNull] = word1[c].charAt(i);      // adding char in gold color catagory
                available[((int) word1[c].charAt(i)) - 65] = '\0';   // removing char from availble char
            }
        }
        return true;
    }

    void printCatagories() {     // function for displaying the catagories in CLI
        System.out.println("***Green***\t***Gold***\t***Gray***\tAvailable Words\t");
        for (int i = 0; i <= c; i++) {  // loop for tries of player
            for (int j = 0; j < 5; j++) {   // loop for greenChar
                if (greenChar[i][j] == '\0') {
                    System.out.print("  ");
                } else {
                    System.out.print(greenChar[i][j] + ",");
                }
            }
            System.out.print("\t");
            for (int j = 0; j < 5; j++) {  // loop for goldChar
                if (goldChar[i][j] == '\0') {
                    System.out.print("  ");
                } else {
                    System.out.print(goldChar[i][j] + ",");
                }
            }
            System.out.print("\t");
            for (int j = 0; j < 5; j++) {      // loop for grayChar
                if (grayChar[i][j] == '\0') {
                    System.out.print("  ");
                } else {
                    System.out.print(grayChar[i][j] + ",");
                }
            }
            System.out.print("\t");
            if (i == 0) {
                for (char a : available) {      // loop for available chars
                    if (a != '\0') {
                        System.out.print(a + ",");
                    }
                }
            }
            System.out.println("");
        }

    }

    // function for easyness of logic
    // that return the most first
    // null index of array passed
    int getFirstNull(char []Arr) {
        int i = 0;
        for (char a : Arr) {
            if (a == '\0') {
                return i;
            }
            i++;
        }
        return i;
    }

    /*public static void main(String[] args) {
        CLIversion clIversion=new CLIversion();
    }
     */
}
