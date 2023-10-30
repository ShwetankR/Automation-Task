import java.io.FileReader;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SHWETANK\\Desktop\\JAVA\\src\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
        WebElement dropdown = driver.findElement(By.xpath("//summary[normalize-space()='Table Data']"));
        dropdown.click();
        Thread.sleep(1000);
        WebElement textarea = driver.findElement(By.xpath("//textarea[@id='jsondata']"));
        textarea.clear();
        String str= "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";
        textarea.sendKeys(str);
        WebElement button = driver.findElement(By.xpath("//button[@id='refreshtable']"));
        button.click();
        List<WebElement> tabledata = driver.findElements(By.xpath("//table[@id='dynamictable']"));
        Boolean equal=true;
        for(int j=1;j<tabledata.size();j++) 
        {
            String value = tabledata.get(j).getText();
            System.out.println(value);
            String arr[]=value.split(" ");
            for(int i=0;i<arr.length;i++)
            {
            Boolean found = str.contains(arr[i]);
            if(found==false)
                {
                equal=false;
                break;
                }
            }
        }
        if(equal==true)
        System.out.println("All values are same");
        else
        System.out.println("All values are not same");
    }
}
