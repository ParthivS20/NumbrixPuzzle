/**
 * Numbrix.java  6/11/2011
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 *
 * Solves Numbrix puzzles
 * http://www.parade.com/numbrix
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Represents a Numbrix puzzle.
 */
public class Numbrix
{
	/** The puzzle data */
	private int[][] grid;

	/** Indicates whether numbers are used in the original puzzle
	 *  If the number n is used, then used[n] is true;  Otherwise it's false */
	private boolean[] used;


	/**
	 * Constructs a Numbrix puzzle object.
	 * @param fileName the name of the file containing the puzzle data.
	 * @throws FileNotFoundException when fileName file does not exist.
	 */
	public Numbrix(String fileName) throws FileNotFoundException {
		Scanner file = new Scanner(new File(fileName));

		int rows = file.nextInt();
		int cols = file.nextInt();

		grid = new int[rows][cols];
		used = new boolean[rows * cols + 1];

		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				grid[r][c] = file.nextInt();
				if(grid[r][c] != 0) {
					used[grid[r][c]] = true;
				}
			}
		}
    }

	/**
	 * Finds all solutions to the Numbrix puzzle stored in grid by
	 * calling recursiveSolve for every possible cell in grid that
	 * originally contains a 0 or a 1.  Each of these calls should
	 * attempt to solve the puzzle beginning with the number 1.
	 */
	public void solve() {
		recursiveSolve(0, 0, 1);
	}

	/**
	 * Attempts to solve the Numbrix puzzle by placing n in grid[r][c]
	 * and then makeing recursive calls (up, down, left, and right) to
	 * place n+1 and higher numbers.
	 * @param r the row of the cell in which to place n.
	 * @param c the column of the cell in which to place n.
	 * @param n the number to place in grid[r][c].
	 */
	private void recursiveSolve(int r, int c, int n) {
		boolean zero = grid[r][c] == 0;
		if((c + 1 >= grid[0].length && r + 1 >= grid.length) || (zero && used[n]) || (!zero && n != grid[r][c])) {
			return;
		}

		grid[r][c] = n;

		recursiveSolve(r + 1 >= grid.length ? 0 : r + 1, r + 1 >= grid.length ? c + 1 : c, n);
	}

	/**
	 * Returns a String which represents the puzzle.
	 * @return the puzzle numbers with a tab after each number in a row
	 *         and a new line character after each row.
	 *         '-' characters should replace 0s in the output.
	 */
	public String toString()
	{
		String result = "";

		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				result += (grid[r][c] == 0 ? "-" : grid[r][c]) + (c < grid[r].length - 1 ? "\t" : "\n");
			}
		}

		return result;
	}
}