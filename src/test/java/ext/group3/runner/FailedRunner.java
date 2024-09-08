package ext.group3.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//this runner is for rerunning the failed scenarios only

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/html-reports/cucumber-report.html",
                "json:target/json-reports/json-report.json"},
        features = "@target/rerun.txt",
        glue = "io/loop/step_definitions",
        monochrome = true,
        publish = false
)

public class FailedRunner {
}

