package org.library.mathUtils;

import com.sun.deploy.util.ArrayUtil;

import java.util.*;

/**
 * Created by Lagrange on 4/28/2017.
 */
public class MathUtils {


    // Utility Class
    private MathUtils(){};

    //checks whether input is even.
    public static boolean isEven(long n){
        if(n % 2 == 0) return true;
        return false;
    }

    //checks whether input is prime
    public static boolean isPrime(long n){
        if((long)Math.sqrt(n) == Math.sqrt(n)) return false;
        for(int k = 2; k < Math.sqrt(n); k++)
            if(n % k == 0) return false;
        return true;
    }

    //returns list of all primes less than or equal to the input limit using the sieve of Eratosthenes.
    private static List<Integer> getPrimesSieve(int max) {
        boolean[] isComposite = new boolean[max + 1];
        for (int i = 2; i * i <= max; i++) {
            if (!isComposite [i]) {
                for (int j = i; i * j <= max; j++) {
                    isComposite [i*j] = true;
                }
            }
        }
        int numPrimes = 0;
        for (int i = 2; i <= max; i++) {
            if (!isComposite [i]) numPrimes++;
        }
        Integer [] primes = new Integer [numPrimes];
        int index = 0;
        for (int i = 2; i <= max; i++) {
            if (!isComposite [i]) {
                primes [index++] = i;
            }
        }
        List<Integer> list = Arrays.asList(primes);
        return list;
    }

    //returns list of all primes less than or equal to the input limit using brute force.
    public static List<Integer> getPrimes(int limit){
        List<Integer> l = new ArrayList<>();
        for(int k = 2; k <= limit; k++)
            if(isPrime(k)) l.add(k);
        return l;
    }



    //replaces a specified digit of the input number by its index
    public static int replaceDigit(int number, int index, int replacement){
        StringBuilder sb = new StringBuilder(String.valueOf(number));
        sb.setCharAt(index,(char) replacement);
        return Integer.parseInt(sb.toString());
    }

    //returns an array of the digits of the input number
    public static int[] getDigits(int x){
        int[] digits = new int[Integer.toString(x).length()];
        int temp = x;
        int index = 0;
        while(temp != 0){
            int digit = temp % 10;
            digits[index++] = digit;
            temp /= 10;
        }
        reverseArray(digits);
        return digits;
    }

    //reverses the input array completely
    public static void reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    //reverses the input array after an input index.
    public static void reverseArray(int[] arr, int start){
        int mid = (arr.length + start) / 2;
        for(int k = start; k < mid; k++){
            swap(arr,k,arr.length - k + start - 1);
        }
    }


    //returns list of factors of an input
    public static Set<Integer> factor(int n){
        Set<Integer> factors = new HashSet<>();
        for(int k = 2; k < n; k++)
            if(n % k == 0) factors.add(k);

        if(factors.size() == 0) factors.add(n);
        return factors;
    }

    //returns the number of prime factors given the list factors.
    public static int primeFactors(Set<Integer> factors){
        Set<Integer> m = new HashSet<>();
        int size1 = factors.size();
        for(Integer i: factors)
            m.addAll(factor(i));

        factors.clear();
        factors.addAll(m);
        int size2 = factors.size();
        if(size1 != size2) primeFactors(factors);

        return factors.size();
    }

    //returns the number of prime factors of the input number.
    public static int getNumPrimeFactors(int n){
        return primeFactors(factor(n));
    }

    //swaps two elements of an array.
    public static void swap(int[] arr,int m, int n){
        int temp  = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }


    //returns array which is the next permutation of the input array by increasing magnitude.
    public static int[] getNextPerm(int a[]){
        int[] arr = a;
        int maxIndex = 1;
        boolean hasNextPerm = false;
        for(int k = 1; k < arr.length; k++){
            if(arr[k-1] < arr[k]){
                maxIndex = k;
                hasNextPerm = true;
            }
        }

        if(!hasNextPerm){
            System.out.println("Final Permutation.");
            return null;
        }
        int pivot = maxIndex - 1;
        int maxIndex2 = maxIndex;
        for(int k = pivot + 1; k < arr.length; k++){
            if(arr[k] > arr[pivot]){
                maxIndex2 = k;
            }
        }

        swap(arr,pivot,maxIndex2);
        reverseArray(arr,pivot+1);
        return arr;
    }

    //returns the factorial of the input number.
    public static long factorial(int n){
        long product = 1;
        for(long k = n; k > 0; k--){
            product *= k;
        }
        return product;
    }

    //checks whether the input numnber is an abundant number. i.e. the sum of the factors are greater than the number.
    public static boolean isAbundant(int x){
        int sum = 1;
        int squareRoot = (int) Math.sqrt(x);
        for(int k = 2; k <= squareRoot; k++){
            if(x % k == 0) {
                sum += k;
                sum += x / k;
            }
        }
        if(squareRoot * squareRoot == x) sum -= squareRoot;
        return sum > x;
    }

    //returns a number given an array of its digits.
    public static int toInt(int[] digits){
        int i = 0;
        for(int k = 0; k < digits.length; k++){
            i += digits[k];
            i *= 10;
        }
        i /= 10;
        return i;
    }

    //shifts all terms of input array to the right by 1.The last term comes to the front.
    public static int[] rotate(int[] a){
        int[] b = new int[a.length];
        b[0] = a[a.length - 1];
        for(int k = 0; k < a.length - 1; k++){
            b[k+1] = a[k];
        }
        return b;
    }

    //checks whether input prime is a circular prime. i.e. a prime whose digits when rotated remains prime.
    public static boolean isCircularPrime(int prime){
        int[] digits = getDigits(prime);
        int perm = digits.length;
        for(int k = 1; k <= perm; k++){
            digits = rotate(digits);
            int n = toInt(digits);
            //System.out.println(n);
            if(!isPrime(n)) return false;
        }

        return  true;
    }

    //checks whether the input number is a palindrome. i.e. a number who reads same forward and back. 123 and 321.
    public static boolean isPalindrome(int n){
        int b = n;
        int a= 0;
        while(b != 0){
            a += b % 10;
            b /= 10;
            a *= 10;
        }
        a /= 10;
        if(a == n)
            return true;
        return false;
    }









}
