import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class swaglab {

	public WebDriver driver;
	public String Product_Name;

	public void Login() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();//creating webdriver object
		
		this.driver = driver;
		driver.get("https://www.saucedemo.com"); // Accessing the website
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");

		Thread.sleep(2000);
		driver.findElement(By.className("btn_action")).click();
	}

	public void addproduct() throws InterruptedException {

		Product_Name = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
		String Product_Description = driver.findElement(By.className("inventory_item_desc")).getText();
		String Price = driver.findElement(By.className("inventory_item_price")).getText();

		System.out.println("Product Name :" + Product_Name);
		System.out.println("Product Description :" + Product_Description);
		System.out.println("Price :" + Price);
		driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();

		Thread.sleep(2000);
		driver.findElement(By.id("shopping_cart_container")).click();
		String Addedproduct = driver.findElement(By.className("inventory_item_name")).getText();

		Assert.assertEquals(Addedproduct, Product_Name); // verifying the product added to the cart if true execution will continue

	}

	public void userdetails() throws InterruptedException {

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]")).click();
		driver.findElement(By.id("first-name")).sendKeys("Team"); // giving the user details.
		driver.findElement(By.id("last-name")).sendKeys("B");
		driver.findElement(By.id("postal-code")).sendKeys("560037");

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='submit']")).click();

	}

	public void confirmorder() throws InterruptedException {

		String Orderproduct = driver.findElement(By.className("inventory_item_name")).getText();
		Assert.assertEquals(Orderproduct, Product_Name);
		System.out.println();
		System.out.println("Payment Information : " + driver.findElement(By.className("summary_value_label")).getText());
		System.out.println("Total Amount : " + driver.findElement(By.className("summary_total_label")).getText());

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // applying wait
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]")).click();
		System.out.println();
		System.out.println(driver.findElement(By.className("complete-header")).getText()); // Message that order is successfully placed

	}
}
