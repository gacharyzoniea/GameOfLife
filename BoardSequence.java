import java.util.ArrayList;

public class BoardSequence {
	private int currentCount = 0;
	private Board current;
	private ArrayList<Board> sequence = new ArrayList<Board>();
	
	
	BoardSequence(Board startingBoard) {
		sequence.add(startingBoard);
	}
	
	void runMoreSteps(int numSteps) {
		for (int i = 0; i <= numSteps; i++) {
			int loc = sequence.size() - 1;	
				sequence.add(sequence.get(loc).nextBoard());
			}
		}
	/**
	 * 
	 * @param index
	 * @return Returns the Board at the specified index of the sequence.
	 * index should be between 0 and the number of steps run so far, inclusive. Otherwise,
	 * IllegalArgumentException is thrown.
	 */
	Board boardAt(int index) throws IllegalArgumentException {
		if (index < 0 || index >= sequence.size()) {
			throw new IllegalArgumentException("stop dum dum");
		}
		else {
		return sequence.get(index);
		}
	}
	
	public String toString() {
		String print = "";
		int spot = 0; 
//		System.out.println("test: \n ____________________________________" + sequence.toString());
		for (int i = 0; i < sequence.size(); i++) {
			print += "Generation " + spot + ":\n" + sequence.get(i).toString();
			spot++;
		}
	return print;
	}
	
	int findCycle() {
		int totalCycles = -1;
		for (int i = 0; i < sequence.size()-1; i++) {
				for (int j = i +1; j < sequence.size(); j++)
				if (sequence.get(i).isSame(sequence.get(j))) {
					return j;
				}
		}
		return totalCycles;
	}
	/**
	 * 
	 * @return whether or not findCycle() has returned 
	 * that there is a cycle
	 */
	boolean hasCycle() {
		boolean result = false;
		if (this.findCycle() > -1) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
}
