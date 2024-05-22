import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
public class ReadFile {


/**
 * ttake this class, insid eof the driver run this method and create the puzzle grid. use the puzzle grid created in the driver and then run the methods to solve. yay. 
 * 
**/

    public static void main(String[] args){
        try{
            Scanner scan = new Scanner(new File("kprob.txt"));
            String line = scan.nextLine();
            StringTokenizer myTokenizer = new StringTokenizer(line, " ");
            int height = Integer.parseInt(myTokenizer.nextToken());
            int width = Integer.parseInt(myTokenizer.nextToken()); 
            System.out.println(height + " " + width + " ");

            while (scan.hasNextLine()){
                line = scan.nextLine();
                System.out.println(line);
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()){
                    String token = tokenizer.nextToken();
                    /** if (token.contains("\\"))
                        token = token.replace("\\", "\\\\"); 
                        */
                System.out.println(token);
                }  
            } 
            scan.close(); 
        }
        catch (FileNotFoundException e)
        {
            System.out.println("no file? what is this");
        }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }   


    }
    




