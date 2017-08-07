package tw2.bot.login;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Login {
	
	
	public Login(WebDriver driver,String username,String password, String lume) throws InterruptedException{
		driver.navigate().to("http://www.tribalwars2.ro");
		driver.manage().window().maximize();
		waitForLoad(driver);
		
		//start login458|477
		WebElement elem =  driver.findElement(By.xpath("//input[@type='text']"));
		
		elem.sendKeys(username);
		elem =  driver.findElement(By.xpath("//input[@type='password']"));
		elem.sendKeys(password);
		elem.submit();
		
		
		Thread.sleep(9000);


		
		elem =  driver.findElement(By.cssSelector("div.play-form>button.button-login"));
		
		
		if(elem.isDisplayed())
		elem.click();
		 Thread.sleep(5000);
		 if(lume.contains("Orava"))
			 elem =  driver.findElement(By.partialLinkText(lume));
			 elem =  driver.findElement(By.partialLinkText(username));
		 
		 
		 JavascriptExecutor ex = (JavascriptExecutor)driver;
		 ex.executeScript("arguments[0].click();", elem);
		 
		 
	}
	public static void  waitForLoad(WebDriver driver) {
	    new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
		

}
