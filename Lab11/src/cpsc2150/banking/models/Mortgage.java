package cpsc2150.banking.models;
// Xavier James and Josh Kennerly
/**
 * Correspondence:
 *                  Number of Payments = pay
 *                  Principal = principal
 *                  Years = number of years of the loan
 *                  Rate = apr
 *                  Debt to Income Ratio =dToI
 *                  Percent down Payment = percentDown
 *
 */

public class Mortgage extends AbsMortgage implements IMortgage {

    private double percentDown;
    private int years;
    private ICustomer customer;
    private double rate;
    private double principal;
    private double dToI;
    private double apr;
    private int numPayments;
    private double pay;
    public static final int MONTHS = 12;

    /**
     *
     * @param h, the cost of the home for the user as a double
     * @param dp, the down payment on the home of the user as a double.
     * @param y, number of years the customer has on mortgage as a int
     * @param c, The ICustomer object holding the customer information
     *
     *
     * @pre h > 0 and
     * 0 < dp <= h and
     * y > 0
     *
     * @post principal = h * dp and
     * rate = apr/MONTHS and
     * years = y and
     * percentDown = dp / h and
     * pay = years * MONTHS
     *
     */

    public Mortgage(double h, double dp, int y, ICustomer c){
        percentDown = dp / h;
        years = y;
        customer = c;

        apr = BASERATE;
        dToI = (customer.getMonthlyDebtPayments()/customer.getIncome())*years;
        principal = h - dp;
        numPayments = years * MONTHS;

        if(years < 30){
            apr += GOODRATEADD;
        }else{
            apr += NORMALRATEADD;
        }

        if(percentDown < PREFERRED_PERCENT_DOWN){
            apr += GOODRATEADD;
        }

        if(customer.getCreditScore() < BADCREDIT){
            apr += VERYBADRATEADD;
        }else if(customer.getCreditScore() >= BADCREDIT && customer.getCreditScore() < FAIRCREDIT){
            apr+=BADRATEADD;
        }else if(customer.getCreditScore() >= FAIRCREDIT && customer.getCreditScore() < GOODCREDIT){
            apr+=NORMALRATEADD;
        }else if(customer.getCreditScore() >= GOODCREDIT && customer.getCreditScore() < GREATCREDIT){
            apr+=GOODRATEADD;
        }

        rate= apr/MONTHS;
        pay = (rate * principal) / (1-Math.pow(1 + rate, (-((double)numPayments))));

    }


    @Override
    public boolean loanApproved() {
        if((getRate() >= RATETOOHIGH)) return false;
        if(percentDown < MIN_PERCENT_DOWN) return false;
        if(dToI > DTOITOOHIGH) return false;
        return true;
    }

    @Override
    public double getPayment() {
        return pay;
    }

    @Override
    public double getRate() {
        return rate * MONTHS;
    }

    @Override
    public double getPrincipal() {
        //(home - downPay) / customer.getIncome()
        return principal;
    }

    @Override
    public int getYears() {
        return years;
    }
}