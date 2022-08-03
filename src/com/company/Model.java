package com.company;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

// Model class for the Game
public class Model {
    // Member variables of model class
    File[] file;    // for files
    String word;    // for word to be choosed
    // flages in order respectively in FR3
    public boolean flage1 = true, flage2 = true, flage3 = true;

    // Constructor of Model class
    public Model() {
        loadFiles();    // function to load files and choose the word
    }

    // function defination of loadFiles function
    public void loadFiles() {
        // checking flags and working accordingly
        if (!flage3) {
            word = "FIXED";
            if (flage2) {
                System.out.println("The Word is : " + word);
            }
        } else {
            file = new File[2];
            file[0] = new File("Word file1.txt");
            file[1] = new File("Word file2.txt");
            Random randFile = new Random();   // random numbers for selecting random files
            Random randWord = new Random();   // random numbers for selecting random words from the files
            int fileNum = randFile.nextInt(2);
            try {
                Scanner myScanner = new Scanner(file[fileNum]); // scanner for reading selected file
                for (int i = 0; i < randWord.nextInt(100); i++) {
                    word = myScanner.nextLine();
                }
                myScanner.close();
                // check for displaying word if flage2 is set
                if (flage2) {
                    System.out.println("The Word is : " + word);
                }
            } catch (Exception e) {   // exception handling
                System.out.println("File Not found " + e);
            }
        }
    }

    /* function for checking words
        and if char exits in word
            at right position it will assign it value 0
            else if position is not right will assign value 1
        if char does not exist will assign value to -1
        against respective int array index
     */
    public int[] checkWords(String wordToCheck) {
        int indx;
        int[] result = new int[5];    // array for returing
        for (int i = 0; i < 5; i++) {
            // checking if char exist and returing repective index in word
            indx = word.indexOf(wordToCheck.charAt(i));
            if (indx != -1) {   // checking if char not exist
                if (i == indx) {    // checking if char at right place
                    result[i] = 0;
                } else {
                    if (wordToCheck.charAt(i) == word.charAt(i)) {  // Again checking if char at right place
                        result[i] = 0;
                    } else {
                        result[i] = 1;
                    }

                }
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    public String getWord() {
        return word;
    }

    // function for checking if player wins the game
    public boolean isWin(String str) {
        return word.equals(str);
    }

}
