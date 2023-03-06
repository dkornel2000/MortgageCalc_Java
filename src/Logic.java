import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Logic {
    double INTEREST_RATE;
    byte PERIOD_TOTAL;
    int TOTAL_AMOUNT;

    public String Controller() {
        totalAmount();

        interestRate();

        periodTotal();

        checkData();

        return mortgageMonthlyPayment();
    }
    public void totalAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the total amount of the mortgage:");
        int input = 0;

        boolean validInput = false;
        while (!validInput)
            try {
                input = scanner.nextInt();;
                while (!(input >= 1000 && input <=1000000)){
                    System.out.println("The total amount must be between 1000 and 1.000.000");
                    input = scanner.nextInt();
                }
                validInput = true;
            }
            catch (InputMismatchException e){
                System.out.println("The total amount must be between 1000 and 1.000.000");
                scanner.nextLine();
            }
        this.TOTAL_AMOUNT= input;
    }

    public void interestRate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the annual interest rate:");
        double input = 0;

        boolean validInput = false;
        while (!validInput)
            try {
                input = scanner.nextDouble();;
                while (!(input > 1 && input <=50)){
                    System.out.println("Please give a valid interest rate between 1 and 50");
                    input = scanner.nextDouble();
                }
                validInput = true;
            }
            catch (InputMismatchException e){
                System.out.println("Please give a valid interest rate number (can be fraction).");
                scanner.nextLine();
            }
        this.INTEREST_RATE= input;
    }

    public void periodTotal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the period of the mortgage (years):");
        byte input = 0;

        boolean validInput = false;
        while (!validInput)
            try {
                input = scanner.nextByte();
                while (!(input > 0 && input <=100)){
                    System.out.println("Please give a valid year number between 1 - 100");
                    input = scanner.nextByte();
                }
                validInput = true;
            }
            catch (InputMismatchException e){
                System.out.println("Please give a valid year number between 1 - 100");
                scanner.nextLine();
            }
        this.PERIOD_TOTAL = input;
        }

    // Displays all the previously given data and gives an opportunity to the user, to change any data.
    public void checkData() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("\nThese are all the data you have previously given:\n");
        System.out.println( "The total amount: "+this.TOTAL_AMOUNT +
                            "\nLength of the mortgage: "+ this.PERIOD_TOTAL +
                            "\nThe interest rate: "+ this.INTEREST_RATE);
        System.out.println("\nWould you liked to change any data previously given? Type 'yes' or 'no'.");
        while (!input.equals("no")) {
            input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "yes":
                    System.out.println("What would you liked to change? \n" +
                            "1: Total amount. \n" +
                            "2: Annual interest rate. \n" +
                            "3: Period (years)."
                    );
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            totalAmount();
                            changeDataMsg();
                            break;
                        case 2:
                            interestRate();
                            changeDataMsg();
                            break;
                        case 3:
                            periodTotal();
                            changeDataMsg();
                            break;
                        default:
                            break;
                    }
                    break;
                case "no":
                    break;
            }
        }
    }


    public void changeDataMsg () {
        System.out.print("Would you like to change anything else? Type 'yes' or 'no': ");
    }

    // Calculates the amount has to be paid each month.
    public String mortgageMonthlyPayment(){

        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENTAGE = 100;

        short periodMonths = (short) (this.PERIOD_TOTAL * MONTHS_IN_YEAR) ;
        double interestMonthly = this.INTEREST_RATE/PERCENTAGE/periodMonths;

        double mortgagePerMonth =
                this.TOTAL_AMOUNT *
                        ((interestMonthly*Math.pow((1+interestMonthly),periodMonths))
                                / (Math.pow((1+interestMonthly),periodMonths)-1));

        String resultMonthly = NumberFormat.getCurrencyInstance().format(mortgagePerMonth);
        return resultMonthly;
    }
}

