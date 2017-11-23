package polynomial;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Difference_table {
	private List<Double> x;
	private List<Double> fx;
	private List<List<Polynomial_Root>> table;
	
	public Difference_table(String file_dir) throws FileNotFoundException{
		File file = new File(file_dir);
		@SuppressWarnings("resource")
		Scanner in = new Scanner(file);
		store_X(in.nextLine());
		store_Fx(in.nextLine());
		setUp_table();
	}
	
	private void setUp_table(){
		table = new ArrayList<>();
		// set fx to table 
		ArrayList<Polynomial_Root> first_row = new ArrayList<>();
		for (int i=0;i<fx.size();i++){
			Polynomial_Root new_root = new Polynomial_Root(fx.get(i));
			new_root.addChild(x.get(i));
			first_row.add(new_root);
		}
		table.add(first_row);
		
		// base on how many x
		for(int i=1;i<x.size();i++){
			List<Polynomial_Root> periousRow =getTable_PeriousRow(i);
			
			ArrayList<Polynomial_Root> new_row = new ArrayList<>();
			for(int j =0;j<x.size()-i;j++){
				Polynomial_Root left = periousRow.get(j);
				Polynomial_Root right = periousRow.get(j+1);
				double new_root_value = getDifference_value(left.getLeftest_Child(),right.getRighttest_Child(),
														left.get_Root(),right.get_Root());
				Polynomial_Root new_root = new Polynomial_Root(rounding_fourDigits(new_root_value));
				new_root.setNewChilds(getNewChilds(left,right));
				new_row.add(new_root);
			}
			table.add(new_row);
		}
	}
	private double rounding_fourDigits(double value){
		return (double)Math.round(value * 10000) / 10000;
	}
	
	private List<Polynomial_Root> getTable_PeriousRow(int currentIndex){
		return table.get(currentIndex-1);
	}
	
	private double getDifference_value(double x,double y,double x_value,double y_value){
		return (y_value - x_value) / (y-x);
	}
	/*
	 * get the node child
	 */
	private List<Double> getNewChilds(Polynomial_Root left,Polynomial_Root right){
		List<Double> result = new ArrayList<>(left.getChilds());
		result.add(right.getRighttest_Child());
		return result;
	}
	
	
	private void store_X(String input){
		x = new ArrayList<>();
		Double [] get_x = Arrays.stream(input.split(" "))
                		 .map(Double::valueOf)
                		 .toArray(Double[]::new);
		x.addAll(Arrays.asList(get_x));
	}
	
	private void store_Fx(String input){
		fx = new ArrayList<>();
		Double [] get_fx = Arrays.stream(input.split(" "))
       		 .map(Double::valueOf)
       		 .toArray(Double[]::new);
		fx.addAll(Arrays.asList(get_fx));
	}
	
	public List<List<Polynomial_Root>> getMyTable(){
		return table;
	}
	
	public List<Double> getX(){
		return x;
	}
	
	//==========================Print======================================
	/*
	 * print x by user input
	 */
	public void Print_X(){
		System.out.println("\nX : "+Arrays.toString(x.toArray()));
		
	}
	/*
	 * print f(x) by user input 
	 */
	public void Print_Fx(){
		System.out.println("\nFx : "+Arrays.toString(fx.toArray()));
		
	}
	/*
	 * Print table
	 */
	public void Print_Table(){
		System.out.println("============================================= Table ==============================================");
		System.out.print("X     : ");
		for(int i=1,j=0;i<x.size()*2;i++){
			if(i%2!=0){
				System.out.print(String_fitTosizeSize(String.valueOf(x.get(j)))+"|");
				j++;
			}
			else{
				System.out.print("       "+"  |");
			}
		}
		int start = 0;
		for(int i=0;i<table.size();i++){
			List<Polynomial_Root> showRow= table.get(i);
			System.out.print("\nF"+ i+"(X)" + " : ");
			for(int l=0;l<start;l++){
				System.out.print("        "+"  ");
			}
			for(int j=1,k=0;j<showRow.size()*2;j++){
				if(j%2!=0){
					String print =  String_fitTosizeSize(String.valueOf(showRow.get(k).get_Root()))+ "|";
					if(k == showRow.size() -1){
						print = print.replace('|', '\u0000');
					}
					System.out.print(print);
					k++;
				}
				else{
					System.out.print("       "+"  |");
				}
				
			}
			start++;
		}
	}
	
	private String String_fitTosizeSize(String s){
		if(s.length() != 8){
			while(s.length() <= 8){
				s = s+" ";
			}
		}
		return s;
	}
	
	
	
}
