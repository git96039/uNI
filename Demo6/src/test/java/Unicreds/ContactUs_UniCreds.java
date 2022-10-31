package Unicreds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ContactUs_UniCreds {
    public void User() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("C://Users//kalyan//IdeaProjects//Demo6/src//test//UserDetails.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        System.setProperty("webdriver.chrome.driver",properties.getProperty("driver") );
        WebDriver driver = new ChromeDriver();

        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();

       WebElement Icon= driver.findElement(By.xpath("(//span[text()='Show us some love'])[2]"));
        String HomePageActual=Icon.getText();
        Assert.assertEquals(HomePageActual,properties.getProperty("validHomePage"));
        System.out.println("Page workong properly");
        driver.close();
    }
}