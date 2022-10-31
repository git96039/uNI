package Unicreds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ContactUs_UniCreds_Fields {
    @Test(priority = 2)
    public void User() throws IOException, InterruptedException {

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

        driver.findElement(By.id("fullname")).sendKeys(properties.getProperty("fullName"));
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(properties.getProperty("emailAddress"));
        WebElement dropdown=driver.findElement(By.xpath("//select[@class='p-2 pl-4 pr-4 m-2 mt-2 mb-2 form-control form-control-md']"));
        Select select= new Select(dropdown);
        select.selectByValue("91");
        driver.findElement(By.xpath("//input[@placeholder='Phone']")).sendKeys(properties.getProperty("phone"));
        driver.findElement(By.xpath("//div[@class='form-group']/textarea")).sendKeys(properties.getProperty("message"));
        driver.findElement(By.id("contactButton")).click();
        Thread.sleep(5000);
        WebElement SuccessMessage=driver.findElement(By.xpath("//div[text()='Message sent successfully!']"));
        String Success= SuccessMessage.getText();
        Assert.assertEquals(Success,properties.getProperty("ExpectedSuccess"));

    }
}