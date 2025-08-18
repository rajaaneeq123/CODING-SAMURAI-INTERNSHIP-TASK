import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SimpleCalculator{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String operator;
        int num1, num2;
        int result = 0;
        while(true){
            try {
                //first number
                System.out.print("\nEnter the first number: ");
                num1 = Math.round(sc.nextInt());
                sc.nextLine();

                //operator
                System.out.print("Enter the operator(+,-,*,/): ");
                operator = sc.next();
                String[] operators = {"+","-","x","/"};
                if(!Arrays.asList(operators).contains(operator)){
                    System.out.println("Enter a valid input!!!");
                    continue;
                }
                
                //second number
                System.out.print("Enter the second number: ");
                num2 = Math.round(sc.nextInt());
                
                break;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Enter a valid input!!!");
            }
        }

        switch(operator){
            case "+" ->result = add(num1,num2);
            case "-" ->result = subtract(num1,num2);
            case "*" ->result = multiply(num1,num2);
            case "/" ->result = divide(num1,num2);
        }

        System.out.printf("%d %s %d = %d", num1,operator,num2,result);

    }

    public static int add(int a, int b){
        return a+b;
    }
    public static int subtract(int a, int b){
        return a-b;
    }
    public static int multiply(int a, int b){
        return a*b;
    }
    public static int divide(int a, int b){
        return a/b;
    }
}