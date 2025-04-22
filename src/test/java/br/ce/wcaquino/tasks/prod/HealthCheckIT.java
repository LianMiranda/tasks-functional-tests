package br.ce.wcaquino.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {
    @Test
    public void healthCheck() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("browserName", "firefox");
        options.setCapability("platform", "LINUX");
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.100.50:4444/wd/hub"), options);
        driver.navigate().to("http://192.168.100.50:9999/tasks/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        String version = driver.findElement(By.id("version")).getText();
        System.out.println(version);
        Assert.assertTrue(version.startsWith("build"));
        driver.quit();
    }
}
