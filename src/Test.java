import org.library.calculus.Derivative;
import org.library.calculus.Integral;
import org.library.mathUtils.MathUtils;
import org.library.polynomial.Polynomial;

import java.util.Arrays;

/**
 * Created by Lagrange on 4/28/2017.
 */
public class Test {

    public static void main(String[] args){
//        Polynomial p = new Polynomial(3);
//        p.print();
//        Polynomial p2 = Polynomial.randomPolynomial(5,-10,10,true);
//        p2.print();
//        Polynomial p3 = p.add(p2);
//        p3.print();
//        Polynomial p4 = p3.subtract(p2);
//        p4.print();

//        Polynomial p5 = new Polynomial(1);
//        Polynomial p6 = new Polynomial(1);
//
//        p5.print();
//        p6.print();
//        Polynomial p7 = p5.multiply(p6);
//        p7.print();
//        Polynomial p8 = p5.power(2);
//        p8.print();
//        Derivative D = new Derivative(1);
//        Polynomial p9 = D.ndx(p8);
//        p9.print();
//
//        Integral i = new Integral();
//
//        Polynomial p10 = i.indefinite(p5);
//        p10.print();
//
//        System.out.print(i.definite(p5,1,6));

        System.out.print(Arrays.toString(MathUtils.getDigits(124)));


    }
}
