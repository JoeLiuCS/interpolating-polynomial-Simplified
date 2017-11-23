package polynomial;

import java.util.ArrayList;
import java.util.List;

public class Polynomial_Root {
		private double Root;
		private List<Double> child_root;
		
		public Polynomial_Root(double value){
			Root = value;
			child_root = new ArrayList<>();
		}
		
		public void addChild(double child){
			child_root.add(child);
		}
		public List<Double> getChilds(){
			return child_root;
		}
		public double get_Root(){
			return Root;
		}
		public void setNewChilds(List<Double> childs){
			child_root = childs;
		}
		public double getLeftest_Child(){
			return child_root.get(0);
		}
		public double getRighttest_Child(){
			return child_root.get(child_root.size() - 1);
		}
}
