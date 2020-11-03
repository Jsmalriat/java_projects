/* Author: Jonah Malriat
 * Date: 09/25/2020
 * Course: CIS 2168
 * Project: Takes a string, removes all spaces and punctuation and trusn to lower case. Then, using
 * Solitare encyrption, preforms a number of steps to generate a key value for each letter of the
 * string. Uses the circular linked list package as a deck to create key values through 5 steps.
 * Once the string is encrypted, it cxan also be decrypted using the original deck in its orignal
 * positions.
 * */

import java.io.CharArrayReader;

import circularlinkedlist.*;

public class SolitaireEncryption {

    public static char encryptChar(char letter, int key) {
        int value = letter - 'a';
        int encryptedValue =  (value + key) % 26;
        char encryptedChar = (char) (encryptedValue + 'a');
        return encryptedChar;
    }

    public static char decryptChar(char letter, int key) {
        int value = letter - 'a';
        int decryptedValue =  (value + (26-key)) % 26;
        char decryptedChar = (char) (decryptedValue + 'a');
        return decryptedChar;
    }

    public static int getKey(CircularLinkedList<Integer> deck){
        step1(deck);
        step2(deck);
        step3(deck);
        step4(deck);
        int key = step5(deck);
        return (key % 26);
    }

    private static void step1(CircularLinkedList<Integer> deck){
        int joker1 = 27;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) == joker1) {
                int before = deck.get(((i + 27) % 28));
                deck.set(((i + 27) % 28), joker1);
                deck.set(i, before);
            }
        }
    }

    private static void step2(CircularLinkedList<Integer> deck){
        int joker2 = 28;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) == joker2) {
                int after1 = deck.get((i + 1) % 28);
                int after2 = deck.get((i + 2) % 28);
                deck.set(((i + 2) % 28), joker2);
                deck.set(((i + 1) % 28), after2);
                deck.set(i, after1);
                i = deck.size();
            }
        }
    }

    private static void step3(CircularLinkedList<Integer> deck){
        CircularLinkedList<Integer> before = new CircularLinkedList<>();
        CircularLinkedList<Integer> middle = new CircularLinkedList<>();
        while ((deck.get(0) != 27) && (deck.get(0) != 28)) {
            before.add(deck.remove(0));
        }
        if (deck.get(0) == 27) {
            do {
                middle.add(deck.remove(0));
            } while (deck.get(0) != 28);
            middle.add(deck.remove(0));
        }
        else if (deck.get(0) == 28) {
            do {
                middle.add(deck.remove(0));
            } while (deck.get(0) != 27);
            middle.add(deck.remove(0));
        }
        while (middle.size() > 0) {
            deck.add(middle.remove(0));
        }
        while (before.size() > 0) {
            deck.add(before.remove(0));
        }

    }

    private static void step4(CircularLinkedList<Integer> deck){
        CircularLinkedList<Integer> firstXCards = new CircularLinkedList<>();
        int lastnum = deck.get(27);
        if (lastnum == 28) {
            lastnum = 27;
        }
        while (lastnum > 0) {
            firstXCards.add(deck.remove(0));
            lastnum--;
        }
        while (deck.size() < 28) {
            deck.add((deck.size() - 1), firstXCards.remove(0));
        }
    }

    private static int step5(CircularLinkedList<Integer> deck){
        return deck.get(0);
    }

    public static char[] encryptReady(String toEncrypt) {
        toEncrypt = toEncrypt.toLowerCase();
        toEncrypt = toEncrypt.replaceAll("[^a-z]","");
        char[] encryptArray = new char[toEncrypt.length()];
        for (int i = 0; i < toEncrypt.length(); i++) { 
            encryptArray[i] = toEncrypt.charAt(i); 
        } 
        return encryptArray;
    }

    public static void main(String[] args) {
        CircularLinkedList<Integer> deck = new CircularLinkedList<>();
        int[] attempt = new int[]{6, 4, 28, 23, 15, 19, 9, 22, 26, 1, 3, 12, 24, 18, 2, 14, 11, 7, 21, 5, 25, 8, 10, 16, 17, 20, 27, 13};
		for(int i = 0; i < attempt.length; i++) {
			deck.add(attempt[i]);
        }
        String toEncrypt = "Good morning";
        char[] encryptReady = encryptReady(toEncrypt);
        System.out.println(encryptReady);

//        char[] encrypted = new char[encryptReady.length];
//        for (int i = 0; i < encryptReady.length; i++) {
//          encrypted[i] = encryptChar(encryptReady[i], getKey(deck));
//        }
//        System.out.println(encrypted);


        char[] encrypted = {'n','i','p','t','q','k','q','w','s','d','h'};
        char[] decrypted = new char[encrypted.length];
        for (int i = 0; i < encrypted.length; i++) {
            decrypted[i] = decryptChar(encrypted[i], getKey(deck));
       }
        System.out.println(decrypted);
    }
}


