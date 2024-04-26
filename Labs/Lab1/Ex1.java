/*Write a Java program that prints the prime numbers among the integers numbers given as command-line parameters.*/

import static java.lang.Math.sqrt;

public class Ex1 {
    static boolean isPrime(int n)
    {
        if(n <= 1)
        {
            return false;
        }
        if(n == 2)
        {
            return true;
        }
        for(int i = 2; i <= sqrt(n); i++)
        {
            if(n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter at least one argument");
            return;
        }
        for (String arg : args) {
            if (isPrime(Integer.parseInt(arg))) {
                System.out.println(Integer.parseInt(arg));
            }
        }
    }
}
