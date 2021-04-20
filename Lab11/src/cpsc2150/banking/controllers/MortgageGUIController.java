//Xavier James and Joshua Kennerly
package cpsc2150.banking.controllers;

import cpsc2150.banking.models.Customer;
import cpsc2150.banking.models.ICustomer;
import cpsc2150.banking.models.Mortgage;
import cpsc2150.banking.views.IMortgageView;

public class MortgageGUIController implements IMortgageController{
    private final IMortgageView view;
    public MortgageGUIController(IMortgageView v){
        view = v;
    }
    public void submitApplication(){
        double houseCost = view.getHouseCost();
        double downPay = view.getDownPayment();
        double monthDebt = view.getMonthlyDebt();
        double yearIn = view.getYearlyIncome();
        int years = view.getYears();
        int credScor = view.getCreditScore();
        String name = view.getName();

        if(yearIn < 0){
            view.printToUser("Income must be greater than 0");
        }else if(monthDebt < 0){
            view.printToUser("Debt must be greater than or equal to 0");
        }else if(credScor < 0 || credScor > ICustomer.MAX_CREDIT_SCORE){
            view.printToUser("Credit Score must be greater than 0 and less than 850");
        }else if(houseCost < 0){
            view.printToUser("House cost must be greater than 0");
        }else if(downPay < 0 || downPay > houseCost){
            view.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
        }else if(years <= 0){
            view.printToUser("Years must be greater than 0.");
        }else{
            Customer user = new Customer(monthDebt,yearIn,credScor,name);
            Mortgage mort = new Mortgage(houseCost, downPay, years, user);

            view.displayApproved(mort.loanApproved());
            view.displayPayment(mort.getPayment());
            view.displayRate(mort.getRate());
        }

    }
}
