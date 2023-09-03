package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src//test//resources//features",
        glue={"stepDefinition"},
//        dryRun = true, //Cucumber will only check that every Step mentioned in the Feature File has corresponding code written in Step Definition file or not. So in case any of the functions are missed in the Step Definition for any Step in Feature File, it will give us the message.
        monochrome = true,
        tags = "@Smoke", //execute tags with @Smoke
//        tags = "@Smoke and @Regression", //execute tags with @Smoke and @Regression
//        tags = "@Smoke or @Regression", //execute tags with @Smoke or @Regression
//        tags = "not @Fail", //execute tags without @Fail
//        tags = "@Test",
        plugin = { "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/cucumber.json"
        }
)

public class runner {
}
