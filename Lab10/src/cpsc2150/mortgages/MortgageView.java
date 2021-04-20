package cpsc2150.mortgages;

import java.util.Scanner;

public class MortgageView implements IMortgageView {

        Scanner s = new Scanner(System.in);
        IMortgageController imc;

    @Override
    public void setController(IMortgageController c) {
        imc = c;
    }

    @Override
    public double getHouseCost() {
        System.out.println("Enter the cost");
        double cost = s.nextDouble();
        return cost;
    }

    @Override
    public double getDownPayment() {
        System.out.println("Enter the down payment");
        double dp = s.nextDouble();
        return dp;
    }

    @Override
    public int getYears() {
        System.out.println("Enter the number years");
        int y = s.nextInt();
        return y;
    }

    @Override
    public double getMonthlyDebt() {
        System.out.println("How much are your monthly debt payments?");
        double md = s.nextDouble();
        return md;
    }

    @Override
    public double getYearlyIncome() {
        System.out.println("How much is your yearly income?");
        double yi = s.nextDouble();
        return yi;
    }

    @Override
    public int getCreditScore() {
        System.out.println("Enter the credit score");
        int cs = s.nextInt();
        return cs;
    }

    @Override
    public String getName() {
        System.out.println("What's your name");
        String name = s.nextLine();
        return name;
    }

    @Override
    public void printToUser(String s) {
        System.out.println(s);
    }

    @Override
    public void displayPayment(double p) {
        System.out.println("Principal Amount: $" + p);
    }

    @Override
    public void displayRate(double r) {
        System.out.println("Interest Rate: " + r + "%");
    }

    @Override
    public void displayApproved(boolean a) {
        System.out.println(a);
    }

    @Override
    public boolean getAnotherMortgage() {
        return false;
    }

    @Override
    public boolean getAnotherCustomer() {
        return false;
    }
}