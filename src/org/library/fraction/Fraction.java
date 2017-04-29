package org.library.fraction;

/**
 * Created by Lagrange on 4/14/2017.
 */
public final class Fraction{


    private int numerator; //numerator of fraction
    private int denominator; //denominator of fraction

    //creates fraction with a given long numerator and denominator
    public Fraction(long numerator, long denominator){
        this.numerator= (int)numerator;
        this.denominator = (int)denominator;
    }
    //creates fraction with a given long numerator with denominator set to 1.
    public Fraction(long numerator){
        this.numerator = (int)numerator;
        this.denominator = 1;
    }
    //creates fraction with a given int numerator and denominator
    public Fraction(int numerator, int denominator){
        this.numerator= numerator;
        this.denominator = denominator;
    }
    //creates fraction with a given int numerator with denominator set to 1.
    public Fraction(int numerator){
        this.numerator = numerator;
        this.denominator = 1;
    }


    //returns a fraction which is the sum of the called fraction and the input fraction.
    public Fraction add(Fraction f){
        return new Fraction(this.numerator * f.denominator + f.numerator * this.denominator, this.denominator * f.denominator);
    }
    //returns a fraction which is the difference of the called fraction and the input fraction.
    public Fraction subtract(Fraction f){
        return new Fraction(this.numerator * f.denominator - f.numerator * this.denominator, this.denominator * f.denominator);
    }
    //returns a fraction which is the product of the called fraction and the input fraction.
    public Fraction multiply(Fraction f){
        return new Fraction(this.numerator * f.numerator, this.denominator * f.denominator);
    }
    //returns a fraction which is the quotient of the called fraction and the input fraction.
    public Fraction divide(Fraction f){
        return new Fraction(this.numerator * f.denominator, this.denominator * f.numerator);
    }
    //returns a fraction which is the reciprocal of the called fraction.
    public Fraction reciprocal(){
        return new Fraction(this.denominator,this.numerator);
    }

    //returns a fraction which is the simplified called fraction.
    public Fraction simplify(){
        long n = this.numerator;
        long d = this.denominator;
        for(long k = d; k >= 1; k--){
            if(n % k == 0 && d % k == 0) {
                n = n / k;
                d = d / k;
                break;
            }
        }
        return new Fraction(n,d);
    }

    //checks whether two fractions are equal after simplification.
    public boolean equals(Fraction f){
        return f.simplify().getNumerator() == this.simplify().getNumerator() && f.simplify().getDenominator() == this.simplify().getDenominator();
    }
    //checks whether two fractions are equal before simplification.
    public boolean is(Fraction f){
        return f.numerator == this.numerator && f.denominator == this.denominator;
    }

    //converts fraction to a string
    public String toString(){
        return this.numerator + " / " + this.denominator;
    }

    //returns decimal value of fraction
    public double toDouble(){
        return (double)this.numerator /(double)this.denominator;
    }


    /*
            getters and setters
     */

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }












}
