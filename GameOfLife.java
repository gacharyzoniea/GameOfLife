import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameOfLife {
	
	public static String readFileContents(String Filename) throws FileNotFoundException {
		File f = new File(Filename);
		Scanner sc = new Scanner(f);
		String contents = "";
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			contents = contents + line + "\n";
		}
		return contents;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the starting board file: ");
		String fName = (scan.nextLine());
		System.out.println("Enter the number of generations: ");
		int gen = scan.nextInt();
		System.out.println("Enter the output file: ");
		String test = scan.next();
		PrintWriter pw = new PrintWriter(test); 
		
		//create new board
		Board board = new Board(readFileContents(fName));
		BoardSequence bS = new BoardSequence(board);
		//run "gen" times
		bS.runMoreSteps(gen);
		pw.print(bS.toString());
		if (bS.hasCycle()) {
			pw.print("Cycle detected on generation: " + bS.findCycle());
		}
		else {
			pw.print("No cycles detected");
		}
		scan.close();
		pw.close();
	}
	
}
