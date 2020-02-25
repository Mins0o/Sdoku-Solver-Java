
class Sudoku_Puzzle {
	// Puzzle array's first dimension will be for each boxes the actual puzzle has
	// the second dimension is for checking the candidate numbers
	private boolean[][] Puzzle = new boolean[81][9];
	//puzlist stores numbers of each boxes. 0 for undetermined
	private short[] puzlist = new short[81];

	// this function helps calculating which area a box is located in
	// area divides the boxes into 9 areas.
	private int areaNumber(int boxnum_ar) {
		return ((boxnum_ar / 9) / 3) * 3 + (boxnum_ar % 9) / 3;
	}

	// construction for the bool[][] Puzzle : input will be an array of numbers for
	// corresponding indexes. Blanks will be filled with 0s
	public Sudoku_Puzzle() {
		this(new short[81]);
	}

	public Sudoku_Puzzle(short[] puzzle_input) {
		// set all of the number candidates as 'possible'
		for (int i = 0; i < 81; i++) {
			for (short j = 0; j < 9; j++) {
				this.Puzzle[i][j] = true;
			}
		}

		// setting for initial condition
		for (int boxscan = 0; boxscan < 81; boxscan++) {
			// writeNum function writes the designated number on the box with the index
			// while updating the adjacent boxes' number candidate list
			if (puzzle_input[boxscan] != 0) {
				writeNum(boxscan, puzzle_input[boxscan]);
			}

		}

	}

	// while writing down the number in the box, this method also opts-out the
	// possibilities from adjacent blocks according to the rules
	private void writeNum(int box_wr, short value) {
		// if the new value for the box is same as the current one, skip the redundant
		// procedure
		if (this.puzlist[box_wr] == value)
			return;

		// the number is determined in the box
		if (value <= 9 && value >= 1) {
			// reset the value in the box
			//columnEli(box_wr % 9, this.puzlist[box_wr], true);
			//rowEli(box_wr / 9, this.puzlist[box_wr], true);
			//areaEli(areaNumber(box_wr), this.puzlist[box_wr], true);

			this.puzlist[box_wr] = value;
			for (short i = 0; i < 9; i++) {
				this.Puzzle[box_wr][i] = false;
			}
			// the *Eli methods sets the candidate options in adjacent boxes to 'false'
			columnEli(box_wr % 9, value, false);
			rowEli(box_wr / 9, value, false);
			areaEli(areaNumber(box_wr), value, false);
			// of course, don't forget to leave our candidate true
			this.Puzzle[box_wr][value - 1] = true;

		}

		else if (value == 0) {

		} else
			return;

	}

	// the following three methods are for writeNum method. it deletes candidates on
	// adjacent boxes
	private void columnEli(int col, short value, boolean mode) {
		for (int i = col; i < 81; i = i + 9) {
			this.Puzzle[i][value - 1] = false;
		}
	}

	private void rowEli(int row, short value, boolean mode) {
		for (int i = row * 9; i < row * 9 + 9; i++) {
			this.Puzzle[i][value - 1] = false;
		}
	}

	private void areaEli(int area, short value, boolean mode) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.Puzzle[(area / 3) * 27 + (area % 3) * 3 + j + i * 9][value - 1] = false;
			}
		}
	}

	public void solve() {
		this.check_Only();
		for (int i = 0; i < 81; i++) {
			this.check_Def(i);
		}
	}

	// this method seeks for the only capable box for a number among its relatives.
	// but yet have marked as 'determined'
	private void check_Only() {
		short rCount;
		short cCount;
		short aCount;
		int rMemory;
		int cMemory;
		int aMemory;
		// for 9 rows/columns/areas
		for (short rpt = 0; rpt < 9; rpt++) {
			// for each number candidates
			for (short num = 1; num < 10; num++) {
				// we have to count how many boxes has the number as candidate
				rCount = 0;
				cCount = 0;
				aCount = 0;
				rMemory = 0;
				cMemory = 0;
				aMemory = 0;
				for (short i = 0; i < 3; i++) {
					for (short j = 0; j < 3; j++) {
						if (this.Puzzle[rpt * 9 + j + i * 3][num - 1] == true) {
							rCount++;
							rMemory = rpt * 9 + j + i * 3;
						}
						if (this.Puzzle[rpt + j * 9 + i * 27][num - 1] == true) {
							cCount++;
							cMemory = rpt + j * 9 + i * 27;
						}
						if (this.Puzzle[(rpt / 3) * 27 + (rpt % 3) * 3 + i * 9 + j][num - 1] == true) {
							aCount++;
							aMemory = (rpt / 3) * 27 + (rpt % 3) * 3 + i * 9 + j;
						}
					}
				}
				if (rCount == 1) {
					this.writeNum(rMemory, num);
				}
				if (cCount == 1) {
					this.writeNum(cMemory, num);
				}
				if (aCount == 1) {
					this.writeNum(aMemory, num);
				}
			}
		}
	}

	private void check_Def(int boxnum) {
		short count = 0;
		short memory = 0;
		for (short i = 0; i < 9; i++) {
			if (this.Puzzle[boxnum][i] == true) {
				count++;
				if (count == 2) {
					memory = 10;
					break;
				}
				memory = (short) (i + 1);
			}
		}
		if (memory < 10)
			this.writeNum(boxnum, memory);
	}

	// this method returns array of numbers in the puzzle boxes
	public short[] getPuzzle() {
		return this.puzlist;
	}

	public void print() {
		short[] list = this.getPuzzle();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(list[i * 9 + j] + " |");
			}
			System.out.println();
		}

	}

}
