import java.util.*;
import java.io.FileReader;
import java.io.IOException;

class Main {
  public static ArrayList<String> scrabble(String s) {
    //Set up variables for use outside try catch
    String line = " ";
    ArrayList<String> finalarr = new ArrayList<String>();
    try {
      //Set up variables
      Scanner reader = new Scanner(new FileReader("wordlist.txt"));  
      char[] arr = s.toCharArray();
      char bigtemp = ' ';
      Arrays.sort(arr);
      for (int i = 0;i < arr.length;i++) {
        if (arr[i] == '?' && i + 1 != arr.length) {
          bigtemp = arr[i];
          arr[i] = arr[i+1];
          arr[i+1] = bigtemp;
        }
        arr[i] = Character.toLowerCase(arr[i]);
      }
      int count = 0;
      boolean forbreak = false;
      
      //create while loop which will search through every word in the list
      while(reader.hasNextLine()) {
        //go through every word
        line = reader.nextLine();

        //if word from list has first letter in word given, run loop 
        //compare the word to every letter in word given     
        char[] temp = line.toCharArray();
        Arrays.sort(temp);
         
        for(int j = 0;j < arr.length;j++) {
          for(int k = 0 + count;k < line.length();k++) {
              //if word letters match, increase count. Count should be length of word from list
              if (Character.toLowerCase(temp[k]) == arr[j]) {
                count++;
                //if word from list fits
                if (count == line.length() && count > 1) {
                  finalarr.add(line);
                  forbreak = true;
                  break;
                }
                if (arr[arr.length - 1] == '?' && count + 1 == line.length() && count + 1> 1) {
                  finalarr.add(line);
                  forbreak = true;
                  break;
                }
                break;
              }
             
            }
          //break the loop
          if (forbreak == true) {
            forbreak = false;
            break;
            }
          }       
        count = 0;
        }
    }
      
  
    catch (IOException e){
      e.printStackTrace();
    }
    return finalarr;
  }
  
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Input your letters and the Scrabble finder will give you words which you can make!: ");
    String word = input.next();
    ArrayList<String> arr = scrabble(word);
    input.close();
    System.out.println(arr);
    
  }
}