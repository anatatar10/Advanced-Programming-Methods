/* Write a Java program that prints the maximum value from all the double numbers given as command-line parameters.*/
public class Ex2 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter at least one argument");
            return;
        }
        Double max = Double.parseDouble(args[0]);
        for(String arg: args)
        {
            if(Integer.parseInt(arg) > max)
            {
                max = Double.parseDouble(arg);
            }
        }
        System.out.print(max);
    }
}
