package ext.group3.utilities.Utilities_UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DocuportWebTableUtils {

    public static String returnAnyField(WebDriver driver, String emailAddress, String field) throws  InterruptedException{

        WebElement element = null;
        String xpath = "";
        //we are locating an email in the table using xpath
        //the xpath with a table would be with an id number of the column, that way we could locate all email in each row

        switch(field.toLowerCase()){
            case "full name":
                xpath = "//td[2][. = '" + emailAddress + "']/preceding-sibling::td//span[2]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "username":
                xpath = "//td[2][. = '" + emailAddress + "']/following-sibling::td[1]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "invitor":
                xpath = "//td[2][. = '" + emailAddress + "']/following-sibling::td[2]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "phone number":
                xpath = "//td[2][. = '" + emailAddress + "']/following-sibling::td[3]/span";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "role":
                xpath = "//td[2][. = '" + emailAddress + "']/following-sibling::td[4]/span/span";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "advisor":
                xpath = "//td[2][. = '" + emailAddress + "']/following-sibling::td[5]";
                element = driver.findElement(By.xpath(xpath));
                break;
            default: throw new InterruptedException("There is no such field " + field);
        }


        return element.getText().trim();
    }
}
