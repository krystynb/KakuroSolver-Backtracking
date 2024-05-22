//methods only. reads the file and creates the kakuro puzzle and initializes all cells
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProblemReader {
    public static PuzzleGrid readProblem(String x)
    {
        PuzzleGrid error = new PuzzleGrid(0, 0);
        try{
            Scanner scan = new Scanner(new File(x));

            String line = scan.nextLine();
            StringTokenizer myTokenizer = new StringTokenizer(line, " ");
            int height = Integer.parseInt(myTokenizer.nextToken());
            int width = Integer.parseInt(myTokenizer.nextToken()); 
            PuzzleGrid puzzle = new PuzzleGrid(height, width);

            int i = 0;
            while (scan.hasNextLine()){
                int j = 0;
                line = scan.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, ",");

                while (tokenizer.hasMoreTokens()){
                    String token = tokenizer.nextToken();
                    if (token.contains("\\")) //may not need this?
                        token = token.replace("\\", "\\\\");

                    int cellCheck = checkCell(token);
                    Coordinate cellCoordinate = new Coordinate(i, j);
                    if (cellCheck == 0)
                        puzzle.putEmpty(cellCoordinate);
                    Cell newCell = createCell(cellCheck, token, cellCoordinate); //replace with a line that assigns the proper coordinates to the proper sets.
                    puzzle.setPuzzleCell(cellCoordinate, newCell);
                    j++;
                }
                i++;  
            } 
            scan.close();
            return puzzle;
            
        }
        //catch if the file given does not exist 
        catch (FileNotFoundException e)
        {
            System.out.println("THAT IS NOT A REAL FILE. DONT MESS WITH ME. program TERMINATED. run it again when you're ready to input a REAL file");
            System.exit(0);
        }
        return error;
    }
    
    public static int checkCell(String value){
        if (value.equals("0")) //empty
            return 0;
        else if (value.equals("X")) //no fill
            return -2;
        else if (value.charAt(0)=='\\') //right
            return -3;
        else if(value.charAt(value.length()-1)=='\\') //top
            return -4;
        else if (value.contains("\\")) //left and top
            return -5;
        return 1; //one digit?
    }


    public static Cell createCell(int x, String value, Coordinate c){
        
        Cell cell = new Cell();
        String[] val = value.split("\\\\");
        switch (x){
            case 0:  //empty
                cell = new Cell(0);
                break;
            case 1: //digit
                cell = new Cell(Integer.parseInt(value));
                break;
            case -2: //X
                cell = new Cell(x);
                break;
            case -3: //hSum
                cell = new Cell(x, Integer.parseInt(val[val.length - 1]));
                break;
                
            case -4: //vSum
                cell = new Cell(x, Integer.parseInt(val[0]));
                break;

            case -5: //hvSum
                int val1 = Integer.parseInt(val[0]);
                int val2 = Integer.parseInt(val[val.length - 1]);   
                cell = new Cell(x, val1, val2);
                break;
        }
        return cell;
    }




}
