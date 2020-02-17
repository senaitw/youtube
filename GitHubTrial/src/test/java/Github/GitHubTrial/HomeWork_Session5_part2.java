package Github.GitHubTrial;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeWork_Session5_part2 {

	WebDriver driver;

	@BeforeMethod

	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://techfios.com/test/billing/?ng=login/");
	}

	@Test

	public void deletingEntryAndValidateDelete() throws InterruptedException {

		By User_Name_Locator = By.id("username");
		By Password_Locator = By.name("password");
		By LogIn_Locator = By.name("login");
		By Bank_Cash_Locator = By.xpath("//span[text()='Bank & Cash']");
		By New_account_Locator = By.xpath("//a[text()='New Account']");
		By Initial_Balance_locator = By.id("balance");
		By Account_Submit_Locator = By.xpath("div[@class='col-md-6']/descendant::button");
		By List_Of_Acc_Locator = By.linkText("List Accounts");

		driver.findElement(User_Name_Locator).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(Password_Locator).sendKeys("abc123");
		driver.findElement(LogIn_Locator).click();
		driver.findElement(Bank_Cash_Locator).click();
		Thread.sleep(4000);
		driver.findElement(New_account_Locator).click();
		Thread.sleep(2000);
		String account_Name = "Name: " + new Random().nextInt(999);
		driver.findElement(By.id("account")).sendKeys(account_Name);
		Thread.sleep(2000);
		String description = "KR new income :" + new Random().nextInt(9999);
		Thread.sleep(2000);
		driver.findElement(By.id("description")).sendKeys(description);
		driver.findElement(Initial_Balance_locator).sendKeys("50000.00");

		driver.findElement(By.xpath("//div[@class='col-md-6']/descendant::button")).click();
		Thread.sleep(2000);
		// using explicit wait for Account Successfully created validation
		waitForElement(driver, 30, By.xpath("//div[@class='alert alert-success fade in']"));
		System.out.println(driver.findElement(By.xpath("//div[@class='alert alert-success fade in']")).getText());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,31000)");

		// using explicit wait Validate new account showed up in the bottom of the table
		waitForElement(driver, 30, By.xpath("//td[text()='" + account_Name + "']/parent::tr"));

		driver.findElement(By.xpath("//td[text()='" + account_Name + "']/parent::tr/descendant::i[2]")).click();

		driver.findElement(By.xpath("//button[text()='OK']")).click();
		Thread.sleep(2000);
		// using explicit wait for Account Successfully Deleted validation
		waitForElement(driver, 30, By.xpath("//div[@class='alert alert-success fade in']"));
		System.out.println(driver.findElement(By.xpath("//div[@class='alert alert-success fade in']")).getText());

	}

	private void waitForElement(WebDriver driver, int timeToWaitInSeconds, By ElementLocator) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSeconds);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ElementLocator));

	}

	@AfterMethod

	public void close() {

		 driver.close();
		 driver.quit();
	}

}
