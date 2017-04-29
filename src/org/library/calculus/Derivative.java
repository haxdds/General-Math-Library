package org.library.calculus;

import org.library.polynomial.Polynomial;

/**
 * Created by Lagrange on 4/28/2017.
 *
 *        UTILITY CLASS
 */
public class Derivative {

    //derivative is a utility class
    private Derivative(){}


    //returns a polynomial which is the first derivative of input polynomial.
    public static Polynomial dx(Polynomial p){
        if(p.getDegree() == 0) return new Polynomial(0,0);
        Polynomial result = new Polynomial(p.getDegree() - 1);
            for (int k = p.getDegree(); k > 0; k--) {
                result.set(k - 1, p.get(k) * k);
             }
        return result;
    }

    //returns a polynomial which is the nth derivative of the input polynomial.
    public static Polynomial ndx(Polynomial p, int n){
        Polynomial result = p;
        for(int k = 0; k < n; k++){
            result = dx(result);
        }
        return result;
    }



}
