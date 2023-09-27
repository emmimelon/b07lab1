import java.io.File;

public class Driver {
	public static void main(String [] args) throws Exception{
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6, 5};
		int [] d1 = {0, 3};
		Polynomial p1 = new Polynomial(c1, d1);
		double [] c2 = {-2,-9};
		int [] d2 = {1, 4};
		Polynomial p2 = new Polynomial(c2, d2);
		Polynomial s = p1.add(p2);
		int i = 0;
		for (i = 0; i < s.coefficients.length; i++){
			System.out.println("s() coefficient: " + s.coefficients[i] + ", s() exponent: " + s.exponents[i]);
		}
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		File testfile = new File("C:\\Users\\alice\\b07lab1\\lab2testfile.txt");
		Polynomial polyf = new Polynomial(testfile);
		for (i = 0; i < polyf.coefficients.length; i++){
			System.out.println("f() coefficient: " + polyf.coefficients[i] + ", f() exponent: " + polyf.exponents[i]);
		}
		Polynomial g = polyf.multiply(s);
		for (i = 0; i < g.coefficients.length; i++){
			System.out.println("g() coefficient: " + g.coefficients[i] + ", g() exponent: " + g.exponents[i]);
		}
		g.saveToFile("C:\\Users\\alice\\b07lab1\\lab2testresultfile.txt");
	}
}