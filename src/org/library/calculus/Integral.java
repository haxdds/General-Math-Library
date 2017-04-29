package org.library.calculus;

import org.library.polynomial.Polynomial;

/**
 * Created by Lagrange on 4/28/2017.
 */
public class Integral {

    //Integral is a utility class.
    private Integral(){}

    //returns polynomial which is the indefinite integral of the input integral. ALL UNKNOWN INTEGRATION CONSTANTS ARE SET TO 1.
    public Polynomial indefinite(Polynomial p){
        Polynomial result = new Polynomial(p.getDegree() + 1);
        for (int k = p.getDegree(); k >= 0; k--) {
            result.set(k + 1, p.get(k)/(k+1));
        }
        return result;
    }

    //returns the value of the definite integral of the input polynomial with given limits.
    public double definite(Polynomial p, int lowerLimit, int higherLimit){
        Polynomial i = indefinite(p);
        return i.value(higherLimit) - i.value(lowerLimit);
    }

}
