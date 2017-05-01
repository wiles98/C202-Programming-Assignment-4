package dictionary;

import java.io.File;
import java.util.Scanner;

/**
 * CSCI C202
 * Programming Assignment 4 - SpellChecker
 * Purpose: Use LinkedLists to build a dictionary and then to spellcheck a
 *          written work titled oliver.txt
 * 
 * @author Thomas Wiles
 * @version 1.0 3/24/17
 */

public class PA4 {
    
    public static void main(String[] args) {
        
        long[] count = new long[1];
        long wordsFound = 0;
        long wordsNotFound = 0;
        long compsForWordsFound = 0;
        long compsForWordsNotFound = 0;
        
        MyLinkedList[] dictionary = new MyLinkedList[26];
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new MyLinkedList<String>();
        } //for
        
        /**
         * fills the dictionary
         */
        try {
            File infiler = new File("dictionary.txt");
            Scanner winput = new Scanner(infiler);
            while (winput.hasNext()) {
                String line = winput.nextLine();
                line = line.toLowerCase();
                dictionary[line.charAt(0)-97].addLast(line);
            } //while
            winput.close();
        } //try
        catch (Exception e) {
        } //catch
        
        /**
         * spellchecks oliver against the dictionary
         */
        try {
            File infiler = new File("oliver.txt");
            Scanner winput = new Scanner(infiler);
            while (winput.hasNext()) {
                String line = winput.nextLine();
                String[] parts = line.split("[\"\t --,.;:*]");
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].length() > 0) {
                        if (parts[i].charAt(0) > 96 && parts[i].charAt(0) < 123) {
                            parts[i] = parts[i].toLowerCase();
                            if (dictionary[parts[i].charAt(0)-97].contains(parts[i],count)) {
                                wordsFound++;
                                compsForWordsFound += count[0];
                            } //if
                            else {
                                wordsNotFound++;
                                compsForWordsNotFound += count[0];
                            } //else
                        } //if
                    } //if
                } //for
            } //while
            winput.close();
            System.out.println("The number of words found is: " + wordsFound);
            System.out.printf("The number of comparisons per word found is: %.2f\n",
                    (double) compsForWordsFound / wordsFound);
            System.out.println("The number of words not found is: " +
                    wordsNotFound);
            System.out.printf("The number of comparisons per word not found " +
                    "is: %.2f\n", (double) compsForWordsNotFound / wordsNotFound);
        } //try
        catch (Exception e) {
        } //catch
        
    } //main
    
} //class
