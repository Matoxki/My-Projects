package IDW;

import java.text.DecimalFormat;

public class Main {
	
	//the static idw function to calculate missing values
    public static double[][] idw(double data[][]) {
    	
    	
    	//create a result array the size of the input array
    	double[][] result=new double[data.length][data[0].length];
        
        for (int y=0;y<data.length;y++) {			//iterate through the input array 
            for (int x=0;x<data[y].length;x++) {
                
                if (Double.isNaN(data[y][x])) {	//check if value is not a number means missing value
                	double numerator = 0.0;
                    double denominator = 0.0;
                	for (int ky=0;ky<data.length;ky++) {	//iterate through the known values 
                        for (int kx=0;kx<data[ky].length;kx++) {
                        	if (!Double.isNaN(data[ky][kx])) {	//if the value is a number, distance and weight are calculated and summed up
                        		double dx = kx - x;
                                double dy = ky - y;
                                double distance = Math.sqrt(dx * dx + dy * dy);
                                double weight = 1.0 / Math.pow(distance, 2);
                                numerator += weight * data[ky][kx];
                                denominator += weight;
                        	}
                        }
                	} 
                	result[y][x] = numerator/denominator; //calculate weighted average and store it in the output array 
                }
                else {
                	result[y][x] = data[y][x];		//if the value is already known, it is stored in the output array
                }
            }
        }      
        return result;
    }

    public static void main(String[] args) {
        
        //define example one input
    	double[][] exampleInput = {
            {1.0, Double.NaN, 3.0},
            {Double.NaN, Double.NaN, 2.0},
            {4.0, 5.0, Double.NaN}
        };
    	
    	 //define example two input
        double[][] exampleInput2 = {
                {10, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,20},
                {Double.NaN, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN},
                {Double.NaN, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN},
                {Double.NaN, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN},
                {Double.NaN, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN},
                {Double.NaN, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN},
                {Double.NaN, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN},
                {Double.NaN, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN},
                {Double.NaN, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN},
                {Double.NaN, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN},
                {30, Double.NaN, Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,Double.NaN,40}
            };
        
        //call idw function with example two array
        double result[][] = idw(exampleInput2);
        
        //output decimals in a readable form
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat df2 = new DecimalFormat("00");
        
        //outputs the results array and the corresponding coordinates
        for (int y=0;y<result.length;y++) {
            for (int x=0;x<result[y].length;x++) {
                System.out.print(df.format(result[y][x]));
                System.out.print("("+df2.format(x)+","+df2.format(y)+") ");
            }
            System.out.println("");
        }
    }
}
