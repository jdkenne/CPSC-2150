package cpsc2150.banking;

import cpsc2150.banking.views.*;
import cpsc2150.banking.controllers.*;

public class MortgageApp {
    public static void main(String[] args) {
        IMortgageView view = new MortgageGUIView();
        IMortgageController controller = new MortgageGUIController(view);
        view.setController(controller);
    }
}