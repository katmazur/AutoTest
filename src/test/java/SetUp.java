import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class SetUp {
    @Parameters("browser")
    @BeforeTest
    public void setUp(String browser) {
        Configuration.timeout = 20000;
        System.setProperty("selenide.browser", browser);
        System.setProperty("selenide.remote", "http://192.168.1.101:4444/wd/hub");
        Configuration.browser = browser;
        Configuration.remote = "http://192.168.1.101:4444/wd/hub";
    }

    @BeforeSuite
    public void beforeSuite() {
        try {
            FileUtils.cleanDirectory(new File(Configuration.reportsFolder));
        } catch (IOException e) {
        }
    }

    @Parameters("browser")
    @AfterTest
    public void quitBrowser(String browser) {
        Selenide.closeWebDriver();
    }
}