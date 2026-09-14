package engin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class ActionsBot {

   // private  Wait<WebDriver> wait;
    private ElementActions elementAction;
    private BrowserActions browserAction;
    private AssertionActions assertionAction;

    public ActionsBot( Wait<WebDriver> wait) {
      //  this.wait = wait;
        this.elementAction = new ElementActions(wait);
        this.browserAction = new BrowserActions(wait);
        this.assertionAction = new AssertionActions(wait);
    }

    public ElementActions getElementAction() {
        return elementAction;
    }
    public BrowserActions getBrowserAction() {
        return browserAction;
    }
    public AssertionActions getAssertionAction() {
        return assertionAction;
    }




}
