/*Write a Java program that prints the greatest common divisor of all integer numbers given as command-line parameters.*/
public class Ex3 {
    public static int findGCD(int n1, int n2)
    {
        if(n2 == 0)
        {
            return n1;
        }
        return findGCD(n2, n1 % n2);
    }
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter at least 2 arguments");
            return;
        }

        int gcd = Integer.parseInt(args[0]);
        for(String arg: args)
        {
            gcd = findGCD(gcd, Integer.parseInt(arg));
        }
        System.out.println("The gcd between the integer numbers is "+gcd);
    }
}
