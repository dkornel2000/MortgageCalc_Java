import java.text.NumberFormat;
import java.util.Scanner;

public class Logic {
    static final byte MONTHS_IN_YEAR = 12;
    static final byte PERCENTAGE = 100;
    double INTEREST_RATE;
    byte PERIOD_TOTAL;
    int TOTAL_AMOUNT;

    public String Controller() {
        totalAmount();

        interestRate();

        periodTotal();

        checkData();

        String monthlyRate = mortgageMonthlyPayment(MONTHS_IN_YEAR, PERCENTAGE);
        return monthlyRate;
    }
    public void totalAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the total amount of the mortgage:");
        int input = scanner.nextInt();
        this.TOTAL_AMOUNT = input;
    }

    public void interestRate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the annual interest rate:");
        double input = scanner.nextDouble();
        this.INTEREST_RATE = input;
    }

    public void periodTotal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the period of the mortgage (years):");
        byte input = scanner.nextByte();
        this.PERIOD_TOTAL = input;
    }

    public void checkData() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("Would you liked to change any data previously given? Give 'yes' or 'no'.");
        while (!input.equals("no")) {
            input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "yes":
                    System.out.println("What would you liked to change? \n" +
                            "1: Total amount. \n" +
                            "2: Annual interest rate \n" +
                            "3: Period (years)"
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
        System.out.print("Would you like to change anything? Type 'yes' or 'no': ");
    }
    public String mortgageMonthlyPayment(byte MONTHS_IN_YEAR, byte PERCENTAGE){
        short periodMonths = (short) (this.PERIOD_TOTAL * MONTHS_IN_YEAR) ;
        double interestRateMonthly = this.INTEREST_RATE/PERCENTAGE/periodMonths;

        double mortgagePerMonth =
                this.TOTAL_AMOUNT *
                        ((interestRateMonthly*Math.pow((1+interestRateMonthly),periodMonths))
                                / (Math.pow((1+interestRateMonthly),periodMonths)-1));

        String resultMonthly = NumberFormat.getCurrencyInstance().format(mortgagePerMonth);
        return resultMonthly;
    }
}

