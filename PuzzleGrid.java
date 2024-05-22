//krystyn bondad
//uses backtracking to solve kakuro puzzle
import java.util.*;
public class PuzzleGrid{
    private int height, width, numCells;
    private Cell[][] puzzle;
    private HashSet<Coordinate> empty;
    public PuzzleGrid(int h, int w){
        height = h;
        width = w;
        puzzle = new Cell[h][w];
        numCells = h * w;
        empty = new HashSet<Coordinate>();

    }
    
    // find solutions for every single empty cell 
    public Boolean solveProblem(){
        Coordinate[] unfilled = new Coordinate[empty.size()];
        int i = 0;

        if (empty.size() == 0){
            System.out.println("hey. thats ALREADY SOLVED. \n\n" + toString());
            
            System.exit(0);
            return true;
        }

        
        for (Coordinate x: empty){
            unfilled[i] = x;
            i++;
        }

        Coordinate c = unfilled[0];


        for (int j = 1; j < 10; j++){
            puzzle[c.getX()][c.getY()].setVal1(j);//System.out.println("trying " + j + " at " + c.getX() + " " + c.getY()); 



            if (unfilled.length == 1)
            {
                if (testVal(c))
                    return true;
            }

            else if (testVal(c)){
                if (fillCoordinate(unfilled, 1))
                    return true;
            }
            
            puzzle[c.getX()][c.getY()].setVal1(0);//System.out.println("og no work. try new: resetting:" + '\n' + toString());
        
        }

            return false;
    }
            
        

    //try to fill the empty cell with a valid value 
    public Boolean fillCoordinate(Coordinate[] u, int index){
        Coordinate c = u[index];
        for (int i = 1; i < 10; i++){

            puzzle[c.getX()][c.getY()].setVal1(i); //System.out.println("trying " + i + " at " + c.getX() + " " + c.getY()); 
            if (!testVal(c)){
                puzzle[c.getX()][c.getY()].setVal1(0); //System.out.println("next num no work, delete: " + '\n' + toString());
            }

            else if(index == u.length -1)
                return true;

            else if(index + 1 < u.length){
                if (fillCoordinate (u, index+1))
                    return true;
                else
                    puzzle[c.getX()][c.getY()].setVal1(0);    
            }
                
        }
        return false;               
    }


    //test the value to make sure it does not break any other rules in the col/row 
    public Boolean testVal(Coordinate c){
        int row = c.getX();
        int col = c.getY();

        if (checkDuplicates(c)){

            //check the entire row
            for (int y = col - 1; y >= 0; y--){
                if (puzzle[row][y].getType() < -2){
                    Coordinate testSum = new Coordinate(row, y);
                    if (!testSum(puzzle[row][y].getType(), testSum))
                        return false; 
                }  
            }

            //check the entire column
            for (int x = row - 1; x >= 0 ; x -- ){
                if (puzzle[x][col].getType() < -2){
                    Coordinate testSum = new Coordinate(x, col);
                    if (!testSum(puzzle[x][col].getType(), testSum))
                        return false;
                }
            }
        }
        else 
            return false;

        //System.out.println("yay! all sums okay! see? " + '\n' + toString());
        return true;

    }


    public Boolean checkDuplicates(Coordinate c){
        
        int row = c.getX();
        int col = c.getY();
        int i = puzzle[row][col].getVal1();


        //System.out.println("checking duplicates for " + i);

        for (int y = col + 1; y < width; y++){
            if (puzzle[row][y].getType() <= -3) 
                break;
            else if (puzzle[row][y].getType() > -1 && puzzle[row][y].getVal1() == i){ //System.out.println(" uh oh! " + row + " " + y + " is already set to " + i );
                return false;
                }
        }

        for (int y = col +- 1; y >= 0; y --){
            if (puzzle[row][y].getType() <= -3)  
                break;
            else if (puzzle[row][y].getType() > -1 && puzzle[row][y].getVal1() == i){ //System.out.println(" uh oh! " + row + " " + y + " is already set to " + i );
                return false; 
                }
        }

        for (int x = row + 1; x < height; x++){
                if (puzzle[x][col].getType() <= -3)
                    break;
                else if (puzzle[x][col].getType() > -1 && puzzle[x][col].getVal1() == i){ //System.out.println(" uh oh! " + x + " " + col + " is already set to " + i );
                    return false; 
                }
        }

        for (int x = row - 1; x >= 0; x--){
            if (puzzle[x][col].getType() <= -3)
                break;
            else if (puzzle[x][col].getType() > -1 && puzzle[x][col].getVal1() == i){ //System.out.println(" uh oh! " + x + " " + col + " is already set to " + i );
                return false; 
            }
        }
        
        //System.out.println("no duplicate issues");
        return true;
    }

    //test a given sum to make sure it still holds true 
    public Boolean testSum(int t, Coordinate c){
        int maxSum = puzzle[c.getX()][c.getY()].getVal1();

        switch (t){
            case -3:
                return testHSum(c, maxSum);

            case -4:
                return testVSum(c, maxSum);

            case -5:
                return testHVSum(c);
        }
        return false;
    }


    public Boolean testHSum(Coordinate c, int maxSum){

        int row = c.getX();
        int col = c.getY();
        int currentSum = 0;
        int emptyCount = 0;
        int filledEmpty = 0;

        //System.out.println("testing the Hsum at " + row + " " + col + ": " + maxSum + " width: " + width);
        for (int y = col + 1; y < width; y++){ 
            if (puzzle[row][y].getType() > -1){
                if (puzzle[row][y].getType() == 0){
                    emptyCount ++;
                    if (puzzle[row][y].getVal1() > 0){
                        filledEmpty++;
                    }
                }   
                currentSum += puzzle[row][y].getVal1(); //System.out.println("Adding:  " + puzzle[row][y].getVal1() + " | CS: " + currentSum + " | Filled/Empty : " + filledEmpty + '/' + emptyCount);
            }

            if (currentSum > maxSum) { //System.out.println("ERROR current > max");
                return false;
            }

            if (y < width - 2 ){
                if (currentSum == maxSum && puzzle[row][y + 1].getType() == 0){ //System.out.println("max sum reached. next node is empty. not poss");
                    return false;              
                }     
                else if(puzzle[row][y + 1].getType() <= -3) 
                    break;
            }
        }
        if ((filledEmpty == emptyCount && currentSum == maxSum) || (filledEmpty < emptyCount && currentSum < maxSum))
            return true;
        
        //System.out.println("other sum error?");
        return false;
    }


    public Boolean testVSum(Coordinate c, int maxSum){
        int currentSum = 0;
        int row = c.getX();
        int col = c.getY();
        int emptyCount = 0;
        int filledEmpty = 0;

       //System.out.println("testing the Vsum at " + row + " " + col + ": " + maxSum );

        for (int x = row + 1; x < height; x++){ 
            if (puzzle[x][col].getType() > -1){
                if (puzzle[x][col].getType() == 0){
                    emptyCount ++;
                    if (puzzle[x][col].getVal1() > 0){
                        filledEmpty++;
                    }
                }   
                currentSum += puzzle[x][col].getVal1(); //System.out.println("Adding:  " + puzzle[x][col].getVal1() + " | CS: " + currentSum + " | Filled/Empty : " + filledEmpty + '/' + emptyCount);
                if (currentSum > maxSum) { //System.out.println("ERROR: the current sum exceeded the max sum");
                    return false;
                } 
            }

            if (x + 1 < height){
                if ((currentSum == maxSum && puzzle[x+1][col].getType() == 0)){ //System.out.println("max sum reached. next node is empty. not poss");
                    return false;
                }
                    
                else if(puzzle[x+1][col].getType() <= -3) 
                    break;
            }

        }

        if ((filledEmpty == emptyCount && currentSum == maxSum) || (filledEmpty < emptyCount && currentSum < maxSum))
            return true;
        
        //System.out.println("other sum error?");
        return false;
    }

    public Boolean testHVSum(Coordinate c){
        int row = c.getX();
        int col = c.getY();
        int vSum = puzzle[row][col].getVal1();
        int hSum = puzzle[row][col].getVal2();

        return (testHSum(c, hSum) && testVSum(c, vSum));
    }

    public String toString(){
        String string = "";
        for (int x = 0; x < height; x++){
            for (int y = 0; y < width; y++){

                switch(puzzle[x][y].getType()){
                    case 1:
                        string += puzzle[x][y].getVal1(); 
                        break;
                    case 0: 
                        string += puzzle[x][y].getVal1();
                        break;
                    case -2:
                        string += "X";
                        break;
                    case -3:
                        string += "\\" + puzzle[x][y].getVal1();
                        break;
                    case -4:
                        string += puzzle[x][y].getVal1() + "\\";
                        break;
                    case -5:
                        string += puzzle[x][y].getVal1() + "\\" + puzzle[x][y].getVal2();
                        break;
                }
                if (y < width -1)
                        string += ",";
            }
            string += "\n";
        }
        return string;
    }
    public void putEmpty(Coordinate c){
        empty.add(c);
    }
    public void setPuzzleCell(Coordinate c, Cell cell){
        puzzle[c.getX()][c.getY()] = cell;
    }

    public int getHeight() {
        return height;
    }


    public void setHeight(int height) {
        this.height = height;
    }


    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public int getNumCells() {
        return numCells;
    }


    public void setNumCells(int numCells) {
        this.numCells = numCells;
    }

    public Cell[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Cell[][] puzzle) {
        this.puzzle = puzzle;
    }

    public HashSet<Coordinate> getEmpty() {
        return empty;
    }

    public void setEmpty(HashSet<Coordinate> empty) {
        this.empty = empty;
    }




}