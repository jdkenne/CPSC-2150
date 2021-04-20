package cpsc2150.mortgages;

public abstract class AbsCustomer implements ICustomer {

    //Every customer will have an IMortgage Loan
    //Protected so the child classes can access it
    protected IMortgage loan;

    /**
     *
     * @return string with customer information
     */
    @Override
    public String toString()
    {
        String str = "";
        str += "Name: " + getName() + "\n";
        str += "Income: $" + getIncome() + "\n";
        str += "Credit Score: " + getCreditScore() + "\n";
        str += "Monthly Debt: $" + getMonthlyDebtPayments() + "\n";
        if( appliedForLoan()) {
            str += loan.toString();
        }

        return str;

    }

    @Override
    public boolean applyForLoan(double downPayment, double houseCost, int years) {
        return false;
    }
}
