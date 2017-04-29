package org.library.matrix;

import org.library.vector.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Lagrange on 4/27/2017.
 *
 * Column Major 2-Dimensional Matrix
 * i.e. |1 4 7|
 *      |2 5 8|
 *      |3 6 9|
 *
 *  TODO: CREATE 3-DIMENSIONAL MATRIX AND GENERAL N-DIMENSIONAL MATRIX.
 *
 */
public class Matrix {

    private double[] elements; // holds elements of matrix COLUMN MAJOR MATRIX (
    private int rows; // number of rows of the matrix
    private int columns; // number of columns of the matrix

    /*
        forms a non-square matrix with default values 0.
     */
    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        elements = new double[rows * columns];
    }

    /*
        forms a square matrix with of size length * length with default values 1.
     */
    public Matrix(int length){
        this.rows = length;
        this.columns = length;
        elements = new double[length * length];
    }

    /*
        forms an identity matrix i.e. a square matrix with diagonals equal to 1
     */
    public static Matrix identity(int length){
        Matrix result = new Matrix(length);
        for(int k = 0; k < length; k++){
            result.put(k,k,1);
        }
        return result;
    }

    /*
       returns a matrix which is the product of the called matrix with the input matrix
     */
    public Matrix multiply(Matrix matrix){
        if(this.columns != matrix.rows) throw new IllegalArgumentException("org.library.org.library.matrix.Matrix Multiplication Error: Rows must match columns");
        Matrix result = new Matrix(this.rows,matrix.columns);
        for(int y = 0; y < matrix.columns; y++){
            for(int x = 0; x < this.rows; x++){
                double sum = 0;
                for(int e = 0; e < this.columns; e++){
                    sum += this.elements[x + e * this.rows] * matrix.elements[e + y * matrix.rows];
                }
                result.elements[x + y * 4] = sum;
            }
        }
        return result;
    }

    /*
        returns a vector which is the product/transformation of that vector with the called matrix.
     */
    public Vector multiply(Vector v){
        if(this.columns != v.size() ) throw new IllegalArgumentException("org.library.vector.Vector Transformation Error: org.library.org.library.matrix.Matrix columns must equal org.library.polynomial.vector dimensions.");
        Vector result = new Vector(this.rows);

        for(int i = 0; i < this.rows; i++){
            double sum = 0;
            for(int e = 0; e < this.columns; e++){
                sum += this.get(i,e) * v.get(e);
            }
            result.put(i,sum);
        }
        return result;
    }


    /*
        returns a matrix which is the product of the called matrix and  a constant
     */
    public Matrix multiply(double constant){
        Matrix result = new Matrix(this.rows, this.columns);
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                result.put(i,j,constant* this.get(i,j));
            }
        }
        return result;
    }

    /*
        returns the sum of the called matrix with the input matrix matrix of same dimension
     */
    public Matrix add(Matrix matrix){
        if(!this.isEqualDimensions(matrix)) throw new IllegalArgumentException("org.library.org.library.matrix.Matrix Addition Error: Matrices must have equals dimensions");
        Matrix result = new Matrix(this.rows, this.columns);
            for(int i = 0; i < this.rows; i++){
                for(int j = 0; j < this.columns; j++){
                 result.put(i,j,this.get(i,j) + matrix.get(i,j));
                }
            }
        return result;
    }

    /*
       TODO: MAKE MORE THAN 2 DIMENSIONS AVAILABLE FOR ROTATION MATRIX.
     */
    // returns rotation matrix for input angle and dimensions. CURRENTLY ONLY 2 DIMENSIONS SUPPORTED
    public static Matrix rotate(int dimension, double angle){
        if(dimension != 2) throw new IllegalArgumentException("Rotation Matrix Dimension Error: Only 2 dimensions currently supported.");
        Matrix result = new Matrix(dimension);
        double theta = Math.toRadians(angle);
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        result.put(0,0,cos);
        result.put(0,1,sin);
        result.put(1,0,-sin);
        result.put(1,1,cos);
        return result;
    }






    // returns a matrix which is the sum of the called matrix and input vector;
    // sum is given column wise. i.e. vector is summed with each column of matrix.
    public Matrix add(Vector v){
        if(this.rows != v.size()) throw new IllegalArgumentException("org.library.org.library.matrix.Matrix - org.library.vector.Vector Addition Error: Rows must equal org.library.polynomial.vector dimensions.");
        Matrix result = new Matrix(this.rows,this.columns);
        for(int j = 0; j < columns; j++){
            for(int e = 0; e < this.rows; e++){
                result.put(e,j,this.get(e,j) + v.get(e));
            }
        }
        return result;
    }

    // returns a random matrix with values from 0-1;
    public static Matrix random(int rows, int columns){
        Matrix result = new Matrix(rows,columns);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                result.put(i,j,Math.random());
            }
        }
        return result;
    }

    // returns the transpose of the matrix;
    public Matrix transpose(){
        Matrix result = new Matrix(this.columns,this.rows);
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                result.put(j,i,this.get(i,j));
            }
        }
        return result;
    }

    // returns the size of the matrix;
    public int size(){
        return rows * columns;
    }

    // checks for squareness
    public boolean isSquare(){
        return this.rows == this.columns;
    }

    // checks whether the called matrix has the same dimensions as the input matrix.
    public boolean isEqualDimensions(Matrix matrix){
        return this.rows == matrix.rows && this.columns == matrix.columns;
    }

    //checks whether all elements of the called matrix are equal to the corresponding elements of the input matrix.
    public boolean equals(Matrix matrix){
        if(!this.isEqualDimensions(matrix)) return false;
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                if(this.get(i,j) != matrix.get(i,j)) return false;
            }
        }
        return true;
    }

    /*
                getters and setters
     */
    public int getRows(){return this.rows;}
    public int getColumns(){return this.columns;}

    public void put(int row, int column, double value){
        this.elements[row + this.rows* column] = value;
    }

    public double get(int row, int column){
        return elements[row + this.rows * column];
    }

    // prints the matrix in a neat format.
    public void print(){
        System.out.print("\n\n");
        NumberFormat f = new DecimalFormat("#0.00");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                double e = this.get(i,j);
                String s = f.format(e);
                if(e >= 0) System.out.print("  "+ s);
                else System.out.print(" " + s);
            }

            System.out.print("\n");
        }
        System.out.print("\n");

    }





}
