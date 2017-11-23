package polynomial;

import java.io.FileNotFoundException;

public class Play {
	
	final static String dir = "/Users/shuoqiaoliu/Documents/workspace/differenceTable/src/polynomial/";
	
	public static void main(String[] args) throws FileNotFoundException {
		Difference_table u1 = new Difference_table(dir + "Input1.txt");
		u1.Print_Table();
		
		Interpolating_polynomial I1 = new Interpolating_polynomial(u1.getMyTable());
		System.out.println("\n\nInterpolating = "+I1.get_Interpolating_polynomial());
	
		Simplified_polynomial s1 = new Simplified_polynomial(u1.getX(),I1.get_TopCoeff()); 
		System.out.println("\n\nSimplified = "+s1.getSimplified());
		
		System.out.println("\n\n");
		
		
		
		
		Difference_table u2 = new Difference_table(dir + "Input2.txt");
		u2.Print_Table();
		Interpolating_polynomial I2 = new Interpolating_polynomial(u2.getMyTable());
		System.out.println("\n\nInterpolating = "+I2.get_Interpolating_polynomial());
		
		Simplified_polynomial s2 = new Simplified_polynomial(u2.getX(),I2.get_TopCoeff());
		System.out.println("\n\nSimplified = "+s2.getSimplified());
	}

}
