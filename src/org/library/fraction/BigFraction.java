package org.library.fraction;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Lagrange on 4/14/2017.
 */
public class BigFraction {

    BigInteger numerator; //numerator of BigFraction
    BigInteger denominator; //denominator of BigFraction

    //creates a BigFraction given a BigInteger numerator and denominator.
    public BigFraction(BigInteger num, BigInteger den){
        this.numerator = num;
        this.denominator = den;
    }
    //creates a BigFraction given a BigInteger numerator with denominator set to 1.
    public BigFraction(BigInteger num){
        this.numerator = num;
        this.denominator = BigInteger.ONE;
    }
    //creates a BigFraction given a integer numerator and denominator.
    public BigFraction(int num, int den){
        this.numerator = BigInteger.valueOf(num);
        this.denominator = BigInteger.valueOf(den);
    }
    //creates a BigFraction given a long numerator and denominator.
    public BigFraction(long num, long den){
        this.numerator = BigInteger.valueOf(num);
        this.denominator = BigInteger.valueOf(den);
    }

    //creates a BigFraction given a integer numerator with denominator set to 1.
    public BigFraction(int num){
        this.numerator = BigInteger.valueOf(num);
        this.denominator = BigInteger.ONE;
    }
    //creates a BigFraction given a long numerator with denominator set to 1.
    public BigFraction(long num){
        this.numerator = BigInteger.valueOf(num);
        this.denominator = BigInteger.ONE;
    }


    //returns a BigFraction(Bf) which is the sum of the called Bf and input Bf.
    public BigFraction add(BigFraction bf){
        return new BigFraction(this.numerator.multiply(bf.denominator).add(bf.numerator.multiply(this.denominator)), this.denominator.multiply(bf.denominator));
    }
    //returns a BigFraction(Bf) which is the difference of the called Bf and input Bf.
    public BigFraction subtract(BigFraction bf){
        return new BigFraction(this.numerator.multiply(bf.denominator).subtract(bf.numerator.multiply(this.denominator)), this.denominator.multiply(bf.denominator));
    }
    //returns a BigFraction(Bf) which is the product of the called Bf and input Bf.
    public BigFraction multiply(BigFraction bf){
        return new BigFraction(this.numerator.multiply(bf.numerator),this.denominator.multiply(bf.denominator));
    }
    //returns a BigFraction(Bf) which is the quotient of the called Bf and input Bf.
    public BigFraction divide(BigFraction bf){
        return new BigFraction(this.numerator.multiply(bf.denominator), this.denominator.multiply(bf.numerator));
    }
    //returns the reciprocal of the called fraction.
    public BigFraction reciprocal(){return new BigFraction(this.denominator,this.numerator);}

    //returns a BigFraction(Bf) which is the called Bf simplified.
    public BigFraction simplify(){
        BigInteger n = this.numerator;
        BigInteger d = this.denominator;
        for(BigInteger k = d; k.compareTo(BigInteger.ONE) == 1; k = k.subtract(BigInteger.ONE)){
            if(n.mod(k).compareTo(BigInteger.ZERO) == 0 && d.mod(k).compareTo(BigInteger.ZERO) == 0) {
                n = n.divide(k);
                d = d.divide(k);
                break;
            }
        }
        return new BigFraction(n,d);
    }


    //returns string form of BigFraction.
    public String toString(){
        return this.numerator + " / " + this.denominator;
    }

    //returns BigDecimal which is the decimal expansion of the BigFraction.
    public BigDecimal toBigDecimal(){
        return BigDecimal.valueOf(this.numerator.longValue()).divide(BigDecimal.valueOf(this.denominator.longValue()));
    }



    /*
                getters and setters
     */

    public BigInteger getNumerator() {
        return numerator;
    }

    public void setNumerator(BigInteger numerator) {
        this.numerator = numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public void setDenominator(BigInteger denominator) {
        this.denominator = denominator;
    }





}
