package org.library.polynomial;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * Created by Lagrange on 4/28/2017.
 *
 *
 *   polynomial of degree n (i.e. Pn = an * x^n + an-1 * x^n-1 + ... + a1x + a0)
 *
 *
 */
public class Polynomial{

    private int degree; // degree of the polynomial
    private double[] coefficients; //index maps to degree of term

    //creates polynomial of input degree with coefficients of 1.
    public Polynomial(int degree){
        if(degree < 0) throw new IllegalArgumentException("Polynomial Degree Error: Degree must be positive.");
        this.degree = degree;
        coefficients = new double[degree + 1];
        Arrays.fill(coefficients,1);
    }


    //creates polynomial of input degree with set coefficients.
    public Polynomial(int degree, int value){
        if(degree < 0) throw new IllegalArgumentException("Polynomial Degree Error: Degree must be positive.");
        this.degree = degree;
        coefficients = new double[degree + 1];
        Arrays.fill(coefficients,value);
    }


    //creates polynomial of input degree with array of coefficients.
    public Polynomial(int degree, double[] coefficients){
        if(degree < 0) throw new IllegalArgumentException("Polynomial Degree Error: Degree must be positive.");
        this.degree = degree;
        coefficients = new double[degree + 1];
        this.coefficients = coefficients;
    }

    //creates polynomial of input degree with random coefficients.
    public static Polynomial randomPolynomial(int degree, int min, int max, boolean integers){
        if(degree < 0) throw new IllegalArgumentException("Polynomial Degree Error: Degree must be positive.");
        Polynomial result = new Polynomial(degree,1);
        if(integers) {
            for(int k = 0; k <= degree; k++)
                result.set(k,min + (int) (Math.random() * max));
        }else{
            for(int k = 0; k <= degree; k++)
                result.set(k,min + (Math.random() * max));
        }
        return result;
    }

    //returns polynomial that is the sum of called polynomial and inputted polynomial.
    public Polynomial add(Polynomial p){
        int lowerDegree;
        int higherDegree;
        if(this.degree > p.degree) {
            lowerDegree = p.degree;
            higherDegree = this.degree;
        }
        else {
            lowerDegree = this.degree;
            higherDegree = p.degree;
        }
        Polynomial result = new Polynomial(higherDegree,0);
        for(int k = 0; k <= lowerDegree; k++){
            result.set(k,this.get(k) + p.get(k));
        }
        for(int k = lowerDegree + 1; k <= higherDegree; k++){
            if(this.degree == higherDegree)
                result.set(k,this.get(k));
            else
                result.set(k,p.get(k));
        }
        return result;
    }

    //returns polynomial that is the difference of called polynomial and inputted polynomial.
    public Polynomial subtract(Polynomial p){
        int lowerDegree;
        int higherDegree;
        if(this.degree > p.degree) {
            lowerDegree = p.degree;
            higherDegree = this.degree;
        }
        else {
            lowerDegree = this.degree;
            higherDegree = p.degree;
        }
        Polynomial result = new Polynomial(higherDegree,1);
        for(int k = 0; k <= lowerDegree; k++){
            result.set(k,this.get(k) - p.get(k));
        }
        for(int k = lowerDegree + 1; k <= higherDegree; k++){
            if(this.degree == higherDegree)
                result.set(k,this.get(k));
            else
                result.set(k, -1 * p.get(k));
        }
        return result;
    }

    //returns polynomial that is the product of called polynomial and inputted polynomial.
    public Polynomial multiply(Polynomial p){
        Polynomial result = new Polynomial(p.degree + this.degree,0);
        for(int i = 0; i <= this.degree; i++){
            for(int j = 0; j <= p.degree; j++){
                Polynomial temp = new Polynomial(i + j, 0);
                temp.set(i+j, this.get(i) * p.get(j));
                result = result.add(temp);
            }
        }
        return result;
    }

    //returns polynomial that is the power of called polynomial with a inputted power.
    public Polynomial power(int n){
        Polynomial result = this;
        for(int k = 1; k < n; k++){
            result = result.multiply(this);
        }
        return result;
    }

    //returns value that a polynomial outputs given an input.
    public double value(int x){
        double sum = 0;
        for(int k = 0; k <= this.degree; k++){
            sum += coefficients[k] * Math.pow(x,k);
        }
        return sum;
    }



    /*
            getters and setters
     */
    public int getDegree(){
        return this.degree;
    }

    public void set(int n, double value){
        coefficients[n] = value;
    }

    public double get(int n){
        return coefficients[n];
    }

    public void setCoefficients(double[] coefficients){
        this.coefficients = coefficients;
    }

    public double[] getCoefficients(){
        return coefficients;
    }



    // prints polynomial.
    public void print(){
        System.out.print("\n");
        NumberFormat f = new DecimalFormat("#0.00");
        for(int k = degree; k >= 1; k--){
            System.out.print(f.format(coefficients[k])+ "x^"+k + " + ");
        }
        System.out.print(f.format(coefficients[0]));
        System.out.print("\n");
    }

}
