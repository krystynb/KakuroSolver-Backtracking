import java.util.*;

public class KakuroSolver {
public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    try{
        System.out.println("Enter the kakuro puzzle text file: ");
        String puzzleFile = scan.nextLine();
        PuzzleGrid kakuroPuzzle = ProblemReader.readProblem(puzzleFile);;
        scan.close();
        kakuroPuzzle.solveProblem();
        System.out.println("\n" + kakuroPuzzle);
    }
    catch (Exception e){
        e.printStackTrace();
        System.out.println("SHAME!!! now everything is BROKEN!!");

    }
}

}