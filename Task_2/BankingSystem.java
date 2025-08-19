import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystem{
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Integer> account_numbers = new ArrayList<>();
    static ArrayList<String> passwords = new ArrayList<>();
    static ArrayList<Double> balance = new ArrayList<>();

    public static void main(String[] args){

        account_numbers.add(1);
        passwords.add("1");
        balance.add(1000d);

        startMenu();
    }
    public static void startMenu(){
        System.out.println("\n**********WELCOME TO THE BANK ATM**********\n");

        boolean repeat = true;
        while(repeat){
            System.out.print("""
            Which action would you like to perform
                1) Login to existing account
                2) Create a new account
                3) Exit ATM
            choose an option(1-3):   """);
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("\nInvalid choice!!!\n");
                continue;
            }
            System.out.println("");
    
            switch(choice){
                case 1 -> {
                    int index;
                    if((index = login()) >= 0){
                        menu(index);
                    }else{
                        System.out.println("*****Login Failed*****\n");
                    }
                }
                case 2 -> createAccount();
                case 3 -> repeat = false;
            }
        }

    }
    public static int login(){

        int count = 3;
        while(true){
            if(count <= 0) return 0;

            System.out.print("Enter your account number: ");
            int acc_no;
            try {
                acc_no = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("\nInvalid account number!!!\n");
                continue;
            }
            if(!account_numbers.contains(acc_no)){
                System.out.println("\nInvalid Account Number!!!\n");
                count--;
                continue;
            }
    
            System.out.print("Enter your password: ");
            String password = sc.nextLine();
            if(!passwords.get(acc_no-1).equals(password)){
                System.out.println("\nInvalid Password!!!\n");
                count--;
                continue;
            }
            
            System.out.println("\n*****Login Successful*****");
            return acc_no-1;
        }
    }
    public static void createAccount(){

        int account_number = generateAccNo();
        
        System.out.println("*****Creating Account*****\n");

        System.out.print("Choose a password: ");
        String password = sc.nextLine();

        account_numbers.add(account_number);
        passwords.add(password);
        balance.add(0d);

        System.out.println("\nAccount Number: "+account_number);
        System.out.println("Password: "+password);

        System.out.println("\n*****Account Created Successfully*****\n");

    }

    public static int generateAccNo(){
        return account_numbers.get(account_numbers.size()-1)+1;
    }

    public static void menu(int index){
        boolean repeat = true;

        while(repeat){
            System.out.print("""

                1) Check Balance
                2) Deposit Money
                3) Withdraw Money
                4) Exit
            Choose an option (1-5):  """);
            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("\nInvalid Choice!!!\n");
                continue;
            }
            System.out.println("");

            switch(choice){
                case 1 -> checkBalance(index);
                case 2 -> depositMoney(index);
                case 3 -> withdrawMoney(index);
                case 4 -> repeat = false;
            }
        }
    }

    public static void checkBalance(int index){
        System.out.println("\nYour Balance is: " + balance.get(index));
    }

    public static void depositMoney(int index){
        System.out.print("Enter your deposit: ");
        double deposit;
        try {
            deposit = sc.nextDouble();
        } catch (Exception e) {
            sc.nextLine();
            System.out.println("\n*****Invalid Deposit!!!*****");
            return;
        }
        
        balance.set(index, balance.get(index)+deposit);
        
        System.out.println("\n*****Deposit Successful*****");
    }

    public static void withdrawMoney(int index){
        System.out.print("Enter The Amount To Withdraw: ");
        double amount;
        try {
            amount = sc.nextDouble();
        } catch (Exception e) {
            sc.nextLine();
            System.out.println("*****\nInvalid Amount!!!*****");
            return;
        }

        if(amount > balance.get(index)){
            System.out.println("\n*****Low Balance!!!*****");
            return;
        }

        balance.set(index, balance.get(index) - amount);

        System.out.println("\n*****Amount Withdrawn Successfully*****");

    }
}
