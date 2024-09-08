package ext.group3.pages.docuport;

import ext.group3.utilities.Utilities_UI.BrowserUtils;
import ext.group3.utilities.Utilities_UI.DocuportConstants;
import ext.group3.utilities.Utilities_UI.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocuLoginPage {

    private static final Logger LOG = LogManager.getLogger();

    public DocuLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//label[.='Username or email']/following-sibling::input")
    public WebElement usernameField;

    @FindBy(xpath = "//label[.='Password']/following-sibling::input")
    public WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//button[@type='submit']//span")
    public WebElement submitButton;

    public void userLogin(String username, String password) {
        //explicit wait
        BrowserUtils.waitForVisibility(usernameField, DocuportConstants.small);
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();

        try
        {
            if(BrowserUtils.waitForVisibility(submitButton, DocuportConstants.small).isDisplayed())
                submitButton.click();
        }catch(Exception e){
            LOG.error("No submit button displayed");
        }
    }

}
