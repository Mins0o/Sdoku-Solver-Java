import java.io.IOException;

public class Main {
	public static void main(String[] Args) throws IOException {
		short[] list = {5, 3, 0, 0, 7, 0, 0, 0, 0, 6, 0, 0, 1, 9, 5, 0, 0, 0, 0, 9, 8, 0, 0 ,0 ,0, 6, 0, 8, 0, 0, 0, 6, 0, 0, 0, 3, 4, 0, 0, 8, 0, 3, 0, 0, 1 ,7, 0, 0 ,0 ,2 ,0, 0, 0 ,6, 0, 6 ,0, 0, 0, 0, 2, 8, 0, 0, 0, 0, 4, 1, 9, 0, 0 ,5 ,0 ,0, 0, 0, 8, 0, 0, 7, 9};
		Sudoku_Puzzle first= new Sudoku_Puzzle(list);
		first.solve();first.solve();
		System.out.println("The solution for this  puzzle vvvvvvvvv");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(" ");
				System.out.print(list[i * 9 + j] + " |");
				if(j%3==2) {System.out.print("|");}
			}
			System.out.println();
			if(i%3==2) {
				System.out.println("---------------------------------------");
			}
		}
		System.out.println("\nIs vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n");
		first.print();
		System.in.read();
 	}
	
	public void formatter(short[] list) {
		
	}
}
