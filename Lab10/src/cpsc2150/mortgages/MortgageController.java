//Joshua Kennerly and Xavier James

package cpsc2150.mortgages;

import java.util.Scanner;

public class MortgageController implements IMortgageController{
    IMortgageView v;
    double cost, dp, md, ic;
    int years, cs;
    int MAX_CREDIT_SCORE = 850;
    String name;
    Scanner s = new Scanner(System.in);

public MortgageController(IMortgageView view){
    v = view;
}

@Override
public void submitApplication() {

    while (true) {
        boolean t = false;
        while (!t) {
            name = v.getName();
            if (name != null) {
                t = true;
            }
        }

        t = false;
        while (!t) {
            ic = v.getYearlyIncome();
            if (ic >= 0) {
                t = true;
            }
        }

        t = false;
        while (!t) {
            md = v.getMonthlyDebt();
            if (md >= 0) {
                t = true;
            }
        }

        t = false;
        while (!t) {
            cs = v.getCreditScore();
            if (cs >= 0 && cs <= MAX_CREDIT_SCORE) {
                t = true;
            }
        }

        t = false;
        while (!t) {
            cost = v.getHouseCost();
            if (cost >= 0) {
                t = true;
            }
        }

        t = false;
        while (!t) {
            dp = v.getDownPayment();
            if (dp >= 0) {
                t = true;
            }
        }

        t = false;
        while (!t) {
            years = v.getYears();
            if (years >= 1) {
                t = true;
            }
        }


        Customer u = new Customer(md, ic, cs, name);
        Mortgage mo = new Mortgage(cost, dp, years, u);
        System.out.println(u);
        v.printToUser("Mortgage Info: ");
        System.out.println(mo);

        System.out.println("Would you like to apply for another mortgage? Y/N");
        String input = s.next();
        if (input.equalsIgnoreCase("N")) {
            break;
        }
    }
}
}

