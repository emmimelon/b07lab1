public class Polynomial{
	double[] coefficients;

	public Polynomial(){
		coefficients = new double[1];
		coefficients[0] = 0;
	}
	public Polynomial(double[] array){
		this.coefficients = array;
	}
	public Polynomial add(Polynomial array){
		for (int i = 0; i < coefficients.length; i++)
		{
			array.coefficients[i] = array.coefficients[i] + coefficients[i];
		}
		return array;
	}
	public double evaluate(double x){
		double result = 0;
		for (int i = 0; i < coefficients.length; i++)
		{
			result = result + (coefficients[i]*(Math.pow(x, i)));
		}
		return result;
	}
	public boolean hasRoot(double x){
		return evaluate(x) == 0;
	}
}