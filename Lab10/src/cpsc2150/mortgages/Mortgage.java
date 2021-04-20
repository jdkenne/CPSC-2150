package cpsc2150.mortgages;

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

    private final double percentDown;
    private final int years;
    private final double rate;
    private final double principal;
    private final double dToI;
    private final double pay;
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

    Mortgage(double h, double dp, int y, ICustomer c){
        percentDown = dp / h;
        years = y;

        double apr = BASERATE;
        dToI = (c.getMonthlyDebtPayments()/ c.getIncome())*years;
        principal = h - dp;
        int numPayments = years * MONTHS;

        if(years < 30){
            apr += GOODRATEADD;
        }else{
            apr += NORMALRATEADD;
        }

        if(percentDown < PREFERRED_PERCENT_DOWN){
            apr += GOODRATEADD;
        }

        if(c.getCreditScore() < BADCREDIT){
            apr += VERYBADRATEADD;
        }else if(c.getCreditScore() >= BADCREDIT && c.getCreditScore() < FAIRCREDIT){
            apr +=BADRATEADD;
        }else if(c.getCreditScore() >= FAIRCREDIT && c.getCreditScore() < GOODCREDIT){
            apr +=NORMALRATEADD;
        }else if(c.getCreditScore() >= GOODCREDIT && c.getCreditScore() < GREATCREDIT){
            apr +=GOODRATEADD;
        }

        rate= apr /MONTHS;
        pay = (rate * principal) / (1-Math.pow(1 + rate, (-((double) numPayments))));

    }


    @Override
    public boolean loanApproved() {
        if((getRate() >= RATETOOHIGH)) return false;
        if(percentDown < MIN_PERCENT_DOWN) return false;
        return !(dToI > DTOITOOHIGH);
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
