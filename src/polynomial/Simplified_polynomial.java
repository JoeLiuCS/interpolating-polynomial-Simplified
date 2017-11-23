package polynomial;

import java.util.List;

public class Simplified_polynomial {
	
	private List<Double> x;
	private List<Double> top_coeff;
	private double[][] coeff;
	private String Simplified="";
	
	public Simplified_polynomial(List<Double> x,List<Double> top_coeff){
		this.x = x;
		this.top_coeff = top_coeff;
		makeSecretCoff();
		MakeResult();
	}
	
	
	
	private void MakeResult(){
		for(int i=0,k=1;i<coeff.length;i++,k++){
			double mult = top_coeff.get(k);
			for(int j=0;j<coeff[i].length;j++){
				coeff[i][j] = rounding_fourDigits(mult*coeff[i][j]);
			}
		}
		double [] temp_result = new double[coeff[0].length];
		for(int i =0;i<coeff[0].length;i++){
			if(i==0){
				temp_result[i] += top_coeff.get(i);
			}
			for(int j=0;j<coeff.length;j++){
				temp_result[i] += coeff[j][i];
			}
		}
		for(int i=0;i<temp_result.length;i++){
			temp_result[i] = rounding_fourDigits(temp_result[i]);
		}
		for(int i=0;i<temp_result.length;i++){
			if(i==0){
				Simplified += String.valueOf(temp_result[i]);
			}
			else if(i==1){
				if(temp_result[i]!=0){
					if(temp_result[i]>0){
						Simplified += "+";
					}
					Simplified += " "+String.valueOf(temp_result[i]) + "X";
				}
			}
			else{
				if(temp_result[i]!=0){
					if(temp_result[i]>0){
						Simplified += " +";
					}
					Simplified += " "+String.valueOf(temp_result[i])+"X^"+i;
				}
			}
		}
	}
	
	
	
	private void makeSecretCoff(){
		int row = x.size()-1;
		int coln = x.size();
		coeff = new double[row][coln+1];

		
		double [][] temp = new double [row][2];
		for(int i=0; i<row; i++) {
		    for(int j=0; j<2; j++) {
		        if(j==0){
		        	temp[i][j] = -1 * x.get(i);
		        }
		        else{
		        	temp[i][j] = 1;
		        }
		    }
		}
		
		for(int i=0;i<row;i++){
			int startCount =0;
			for(int j=0;j<2;j++){
				// first coln
				if(i==0){
					coeff[i][j] = temp[i][j];
				}
				else{
					for(int k=0;k<coln;k++){
						double c1 =coeff[i-1][k];
						double c2 = temp[i][j];
						coeff[i][k+startCount] += c1 *c2  ;
					}
					startCount++;
				}
				
			}
		}
	}
	public String getSimplified(){
		return Simplified;
	}
	
	
	private double rounding_fourDigits(double value){
		return (double)Math.round(value * 10000) / 10000;
	}
	
}
