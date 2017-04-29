package org.library.vector;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Lagrange on 4/27/2017.
 *
 * Column Vector Class
 *
 * TODO: CREATE ROW VECTOR / GENERAL VECTOR.
 *
 */
public class Vector {

    private double[] components; //components of the vector
    private int dimensions; // length or size of vector

    //creates vector of given dimensions with default components 0.
    public Vector(int dimensions){
        this.dimensions = dimensions;
        components = new double[dimensions];
    }

    //returns a vector whose components are the product of the called vector's and input vector's corresponding components.
    public Vector multiply(Vector v){
        if(this.size() != v.size()) throw new IllegalArgumentException("Vector Multiplication Error: Vectors must have same dimensions.");
        Vector result = new Vector(this.size());
        for(int i = 0; i < this.size(); i++){
            result.put(i,this.get(i) * v.get(i));
        }
        return result;
    }

    // returns the dot product of the called vector and the input vector.  u * v
    public double dot(Vector v){
        if(this.size() != v.size()) throw new IllegalArgumentException("Vector Dot Product Error: Vectors must be same dimension.");
        double sum = 0;
        for(int k = 0; k < v.size(); k++){
            sum += this.get(k) * v.get(k);
        }
        return sum;
    }

    //returns the cross/vector product of the called vector and the input vector u x v
    public Vector cross(Vector v){
        if(this.dimensions > 3 || v.dimensions > 3) throw new IllegalArgumentException("Vector Cross Product Error: Dimensions of vectors must be less than 3");
        Vector result = new Vector(3);
        result.put(0,(this.get(1) * v.get(2)) - (this.get(2) * v.get(1)));
        result.put(1,(this.get(2) * v.get(0)) - (this.get(0) * v.get(2)));
        result.put(2,(this.get(0) * v.get(1)) - (this.get(1) * v.get(0)));
        return result;
    }

    // returns a vector which is the sum of the called vector and the input vector.
   public Vector add(Vector v){
        if(this.size() != v.size()) throw new IllegalArgumentException("Vector Addition Error: Vectors must have same dimensions");
        Vector result = new Vector(this.size());
        for(int i = 0; i < this.size(); i++){
            result.put(i,this.get(i) + v.get(i));
        }
        return result;
    }

    // returns a vector which is the sum of the called vector and the input vector.
    public Vector subtract(Vector v){
        if(this.size() != v.size()) throw new IllegalArgumentException("Vector Addition Error: Vectors must have same dimensions");
        Vector result = new Vector(this.size());
        for(int i = 0; i < this.size(); i++){
            result.put(i,this.get(i) - v.get(i));
        }
        return result;
    }

    // returns the magnitude of the called vector. ||v||
    public double magnitude(){
        double squareSum = 0;
        for(int k = 0; k < this.size(); k++){
            squareSum += Math.pow(this.get(k),2);
        }
        return Math.sqrt(squareSum);
    }

    //returns a vector which is the product of the called vector and a constant.
    public Vector multiply(double constant){
        Vector result = new Vector(this.size());
        for(int k = 0; k < this.size(); k++){
            result.put(k,constant * this.get(k));
        }
        return result;
    }

    //TODO: CREATE TRANSPOSE AND ROW VECTORS!
    public Vector transpose(){
        return null;
    }


    //checks whether the called vector's components equal the input vector's corresponding components.
   public boolean equals(Vector v){
        if(this.size() != v.size()) return false;
        for(int k = 0; k < this.size(); k++)
            if(this.get(k) != v.get(k)) return false;
        return true;
   }

   /*
            getters and setters
    */
   public int size(){return dimensions;};

   public void put(int i, double value){
        components[i] = value;
    }

   public double get(int i){
        return components[i];
    }

    // prints the vector in column format
    public void print(){
       System.out.print("\n");
       NumberFormat f = new DecimalFormat("#0.00");
       for(int k = 0; k < this.size(); k++){
           System.out.println(f.format(this.get(k)));
       }
       System.out.print("\n");
    }


}
