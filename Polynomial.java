import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Polynomial{
	double[] coefficients;
	int [] exponents;

	public Polynomial(){
		coefficients = new double[1];
		coefficients[0] = 0;
		exponents = new int[1];
		exponents[0] = 0;
	}
	public Polynomial(double[] array, int[] array2){
		this.coefficients = array;
		this.exponents = array2;
	}
	public Polynomial(File z) throws Exception{
		BufferedReader b = new BufferedReader(new FileReader(z));
		String line = b.readLine();
		String[] array = line.split("x", 0);
		double[] newcoefficients = new double[array.length];
		int[] newexponents = new int[array.length];
		int checker = 0; 
		String coeff = "";
		String exp = "0";
		int i = 0;
		int j = 0;
		int k = 0;
		for (i = 0; i < array.length; i++) 
		{
			for (j = 0; j < array[i].length(); j++) 
			{
				if (array[i].charAt(j) == '+' || array[i].charAt(j) == '-')
				{
					newcoefficients[k] = Double.parseDouble(coeff);
					newexponents[k] = Integer.parseInt(exp);
					checker = 0;
					k++;
					coeff = "";
					coeff = coeff + array[i].charAt(j);
					exp = "0";
				}
				else
				{
					if (checker == 0)
					{
						coeff = coeff + array[i].charAt(j);
					}
					else if (checker == 1)
					{
						exp = exp + array[i].charAt(j);
					}
				}
			}
			checker = 1;
		}
		newcoefficients[k] = Double.parseDouble(coeff);
		newexponents[k] = Integer.parseInt(exp);
		this.coefficients = newcoefficients;
		this.exponents = newexponents;
	}
	public Polynomial add(Polynomial array){
		int i = 0;
		int largestdegree = 0;
		for (i = 0; (i < exponents.length) || (i < array.exponents.length); i++){
			if ( i < exponents.length){
				if (exponents[i] > largestdegree ){
					largestdegree = exponents[i];
				}
			}
			if (i < array.exponents.length){
				if (array.exponents[i] > largestdegree){
					largestdegree = array.exponents[i];
				}
			}
		}
		double[] temp = new double[largestdegree+1];
		int count = 0;
		for (i = 0; i < coefficients.length; i++)
		{
			temp[exponents[i]] = temp[exponents[i]] + coefficients[i];
		}
		for (i = 0; i < array.coefficients.length; i++)
		{
			temp[array.exponents[i]] = temp[array.exponents[i]] + array.coefficients[i];
		}
		int j = 0;
		for (i = 0; i < (largestdegree+1); i++){
			if (temp[i] != 0)
			{
				count++;
			}
		}
		double[] finalco = new double[count];
		int[] finalex = new int[count];
		for (i = 0; i < (largestdegree+1); i++){
			if (temp[i] != 0)
			{
				finalco[j] = temp[i];
				finalex[j] = i;
				j++;
			}
		}
		Polynomial poly = new Polynomial(finalco, finalex);
		return poly;
	}
	public double evaluate(double x){
		double result = 0;
		for (int i = 0; i < coefficients.length; i++)
		{
			result = result + (coefficients[i]*(Math.pow(x, (exponents[i]))));
		}
		return result;
	}
	public boolean hasRoot(double x){
		return evaluate(x) == 0;
	}
	public Polynomial multiply(Polynomial f){
		int i = 0;
		int j = 0;
		Polynomial newpoly = new Polynomial();
		for (i = 0; i < coefficients.length; i++)
		{
			for (j = 0; j < f.coefficients.length; j++)
			{
				double[] tempco = {(coefficients[i] * f.coefficients[j])};
				int[] tempex = {(exponents[i] + f.exponents[j])};
				Polynomial temp = new Polynomial(tempco, tempex);
				newpoly = newpoly.add(temp);
			}
		}
		return newpoly;
	}
	public void saveToFile(String filename){
		int i = 0;
		String temp = "";
		try {
			FileWriter polytext = new FileWriter(filename);
			for (i = 0; i < coefficients.length; i++){
				if (coefficients[i] < 0){
					temp = coefficients[i] + "x" + exponents[i];
				}
				else if (i != 0){
					temp = "+" + coefficients[i] + "x" + exponents[i];
				}
				else if (i == 0){
					temp = coefficients[i] + "x" + exponents[i];
				}
				polytext.write(temp);
			}
			polytext.close();
		} catch (IOException e){
		}
	}
}