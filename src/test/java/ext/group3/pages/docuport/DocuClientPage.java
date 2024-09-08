package ext.group3.pages.docuport;

import ext.group3.utilities.Utilities_UI.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocuClientPage extends DocuportBasePage {

    @FindBy(xpath = "//h3[.='Choose account']")
    public WebElement chooseAccountText;

    public DocuClientPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
