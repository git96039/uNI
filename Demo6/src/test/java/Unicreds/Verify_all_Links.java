package Unicreds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Verify_all_Links {
    public static boolean isUrlValid(String Url) {
        try {
            URL mall = new URL(Url);
            mall.toURI();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    @Test

    public void allLinks() throws IOException, InterruptedException {

        FileInputStream fileInputStream = new FileInputStream("C://Users//kalyan//IdeaProjects//Demo6/src//test//UserDetails.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        System.setProperty("webdriver.chrome.driver", properties.getProperty("driver"));
        WebDriver driver = new ChromeDriver();

        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());

        for (int i = 2; i < links.size(); i++) {
            String find = links.get(i).getAttribute("href");
            if (isUrlValid(find)) {
                URL mall = new URL(find);
                        URLConnection conn =mall.openConnection();
                HttpURLConnection connection = (HttpURLConnection) conn;
                connection.setRequestMethod("GET");
                int resp = connection.getResponseCode();
                System.out.println(resp);
                if (resp == 200) {
                    Scanner scan = new Scanner(conn.getInputStream());
                    StringBuffer buff = new StringBuffer();
                    while (scan.hasNextLine()) {
                        buff.append(scan.nextLine() + "");
                    }
                    scan.close();
                }
                } else {

                    System.out.println("not");
                }
            }
        }
    }




