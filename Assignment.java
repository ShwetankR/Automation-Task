import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.List;

public class Assignment {
    public static void main(String[] args) throws Exception {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SHWETANK\\Desktop\\JAVA\\src\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");

        // Locate and click on the dropdown
        WebElement dropdown = driver.findElement(By.xpath("//summary[normalize-space()='Table Data']"));
        dropdown.click();
        Thread.sleep(1000); // Wait for the dropdown to open

        // Clear the preexisting value in the textarea
        WebElement textarea = driver.findElement(By.xpath("//textarea[@id='jsondata']"));
        textarea.clear();

        // Read JSON data from "data.json" file
        String jsonDataFilePath = "C:\\Users\\SHWETANK\\Desktop\\data.json";
        JSONParser jsonParser = new JSONParser();
        FileReader jsonReader = new FileReader(jsonDataFilePath);
        JSONArray jsonDataArray = (JSONArray) jsonParser.parse(jsonReader);

        // Convert the JSON array to a string
        String jsonString = jsonDataArray.toJSONString();

        // Send JSON data to the textarea
        textarea.sendKeys(jsonString);

        // Click the "Refresh Table" button
        WebElement button = driver.findElement(By.xpath("//button[@id='refreshtable']"));
        button.click();

        //fetching table
        List<WebElement> tabledata = driver.findElements(By.xpath("//table[@id='dynamictable']"));

        // Create a variable to track whether the table data matches the input JSON
        Boolean equal=true; 

        //Asserting the data
        for(int j=1;j<tabledata.size();j++) 
        {
            String value = tabledata.get(j).getText();
            System.out.println(value);
            String arr[]=value.split(" ");
            for(int i=0;i<arr.length;i++)
            {
            Boolean found = jsonString.contains(arr[i]);
            if(found==false)
                {
                    equal=false;
                    break;
                }
            }
        }

        //Final Output
        if(equal)
        System.out.println("Table data matches input JSON data.");
        else
        System.out.println("Table data does not match input JSON data.");

    }
}
