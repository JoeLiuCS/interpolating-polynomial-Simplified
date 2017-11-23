package polynomial;

import java.util.ArrayList;
import java.util.List;

public class Interpolating_polynomial {
	private final int startPosition = 0;
	private List<List<Polynomial_Root>> table;
	private String Interpolating="";
	private List<Double> top_coeff;
	
	public Interpolating_polynomial(List<List<Polynomial_Root>> table){
		this.table = table;
		top_coeff = new ArrayList<>();
		generateTable();
	}
	
	private void generateTable(){
		for(int i=0;i<table.size();i++){
			List<Polynomial_Root> getRow = table.get(i);
			double coeff = getRow.get(startPosition).get_Root();
			top_coeff.add(coeff);
			if(coeff>=0&&i!=0){
				Interpolating += "+";
			}
			Interpolating += String.valueOf(coeff);
			List<Double> childs = getRow.get(startPosition).getChilds();
			for(int j=0;j<i;j++){
				String in = String.valueOf(childs.get(j));
				if(childs.get(j)>=0){
					in = "-" + in;
				}
				else{
					in = in.replace("-", "+");
				}
				
				Interpolating +="(X"+in+")";
			}
		}
	}
	public String get_Interpolating_polynomial(){
		return Interpolating;
	}
	public List<Double> get_TopCoeff(){
		return top_coeff;
	}
}
