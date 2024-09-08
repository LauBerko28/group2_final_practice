package ext.group3.step_definitions;

import ext.group3.utilities.Utilities_UI.BrowserUtils;
import ext.group3.utilities.Utilities_UI.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private static final Logger LOG  = LogManager.getLogger();

//the point of Hooks class is to getDriver and close Driver
    @Before //this tag says when anything Cucumber related runs, it triggers Hook this will run
    public void setUp(Scenario scenario) {
        Driver.getDriver();
        BrowserUtils.myScenario = scenario;
        LOG.info("...........START AUTOMATION...........LOOP ACADEMY");
    }

    @After
    public void tearDown(Scenario scenario) {
        // only takes a screenshot when scenario is failed
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        LOG.info("...........END AUTOMATION...........LOOP ACADEMY");
        Driver.closeDriver();
    }

    //@AfterStep
    public void screenShot(Scenario scenario){
        final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }
}