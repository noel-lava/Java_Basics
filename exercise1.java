import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class exercise1 {
  public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int option;  
	  	int x, y, n, m;
	  
		do{
			System.out.print("\nEnter value for N : ");
			n = acceptValidInt(1);

			System.out.print("Enter value for M : ");
			m = acceptValidInt(1);
			
			// Create and populate grid
			String[][] grid = new String[n][m];
			populateGrid(grid);

			System.out.println("\nYour inputs are : N = " + n + "; M = " + m);

			do{
				System.out.println("\nEnter next operation : ");
				System.out.println("1. Print");
				System.out.println("2. Edit");
				System.out.println("3. Search");
				System.out.println("4. Reset");
				System.out.println("5. Exit");

				option = acceptValidInt(1);

				switch(option){
					case 1: // print grid
							printGrid(grid);
							break;
					case 2: // edit grid
							do{
								System.out.print("\nEnter x index : ");
								x = acceptValidInt(0);
								
								if(x >= n || x < 0) System.out.println("Invalid index");
							}while(x >= n || x < 0);
							do{
								System.out.print("\nEnter y index : ");
								y = acceptValidInt(0);
								
								if(y >= m || y < 0) System.out.println("Invalid index");
							}while(y >= m || y < 0);
						
							System.out.println("\n\nEnter new value for index ("+x+","+y+") : ");
							String newVal = scanner.nextLine();
							editGrid(grid, x, y, newVal);
						
							System.out.println("Edit complete... Grid["+x+"]["+y+"] = " + newVal);
							break;
					case 3: // Search
							System.out.println("\n\nEnter value to search : ");
							String searchVal = scanner.nextLine();
						
							searchGrid(grid, searchVal);
							break;
					default: break;
				}

			}while(option < 4 || option > 5);
			
		}while(option != 5);
  	}
	
	private static void printGrid(String[][] grid){
		for(int ctr = 0;ctr < grid.length; ctr++){
			System.out.print("|");
			for(int ctr2 = 0; ctr2 < grid[ctr].length; ctr2++){
				System.out.print(grid[ctr][ctr2] + "|");
			}
			System.out.print("\n");
		}
	}
	
	private static String[][] editGrid(String[][] grid, int x, int y, String val){
		grid[x][y] = val;
		return grid;
	}
	
	private static void searchGrid(String[][] grid, String val){
		//String[] searched = new String[arrLength];
		int match = 0;
		int instance = 0;
		
		if(!val.isEmpty()) {
			for(int ctr = 0;ctr < grid.length; ctr++){
				for(int ctr2 = 0; ctr2 < grid[ctr].length; ctr2++){
					if(grid[ctr][ctr2].contains(val)){
						instance = getValInstances(grid[ctr][ctr2],val);

						System.out.println("("+ctr+","+ctr2+") - " + instance + " instance(s)");
						match++;
					}
				}
			}
		}
		
		if(match==0)System.out.println("Substring not found in grid");
	}
	
	private static String[][] populateGrid(String[][] grid){
		for(int ctr = 0;ctr < grid.length; ctr++){
			for(int ctr2 = 0; ctr2 < grid[ctr].length; ctr2++){
				grid[ctr][ctr2] = getRandomString();
				//grid[ctr][ctr2] = String.valueOf(ctr)+","+String.valueOf(ctr2);
			}
		}
		
		return grid;
	}
	
	private static String getRandomString(){
		Random rand = new Random();
		int random = 0;
		String randString = "";
		for(int ctr = 0; ctr < 5; ctr++){
			random = rand.nextInt(74) + 48;
			randString += (char)random;
		}
		
		return randString;
	}
	
	private static int getValInstances(String base, String sub){
		int index = base.indexOf(sub);
		int count = 0;
		
		while(index != -1){
			index = base.indexOf(sub, index);
			
			if(index != -1){
				count++;
				index++;
			}
		}
		
		return count;
	}
	
	private static int acceptValidInt(int min){
		Scanner scanner = new Scanner(System.in);
		boolean validInput = false;
		int input = 0;
		
		while(!validInput){
			try{
				input = scanner.nextInt();
				
				if(input < min) {
					System.out.println("Invalid input, try again");
					validInput = false;
				} else {
					validInput = true;
				}
			}catch(InputMismatchException ime){
				System.out.println("Invalid input, try again");
				scanner = new Scanner(System.in);
			}
		}
		
		return input;
	}
}
