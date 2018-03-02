package tw2.bot.login;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Login {
	
	
	public Login(WebDriver driver,WebDriverWait wait,String username,String password, String lume) throws InterruptedException{
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


		
		//elem =  driver.findElement(By.cssSelector("div.play-form>button.button-login"));
		elem =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.play-form>button.button-login")));
		
		if(elem.isDisplayed())
		elem.click();
		 Thread.sleep(5000);
		 if(lume.contains("isponibila"))
		 elem =  driver.findElement(By.partialLinkText(username));
		 else {
		// String partialLInk =  username.trim()+" ("+lume.trim();
		// belzecur (Valkenburg)
		List<WebElement> worlds= driver.findElements(By.partialLinkText(lume.trim()));
		if(worlds.size()>0) {
			System.out.println("world Available !");
			
			for(WebElement world:worlds) {
				if(world.getAttribute("innerHTML").contains(username))
					elem = world;
				else if(world.getAttribute("innerHTML").contains(username.toUpperCase()))
					elem = world;
				}
		
		}
		/* System.out.println(partialLInk);
		 boolean v =  driver.findElements(By.partialLinkText(partialLInk)).size()>0;
		 if(v)
		 	elem =  driver.findElement(By.partialLinkText(partialLInk));
		 else {
			 partialLInk =  username.trim().toUpperCase()+" ("+lume.trim();
			 System.out.println(partialLInk);
			 elem =  driver.findElement(By.partialLinkText(partialLInk));
		 }
		 	*/
		 }
		 
		 JavascriptExecutor ex = (JavascriptExecutor)driver;
		 ex.executeScript("arguments[0].click();", elem);
		 
		 
	}
	public static void  waitForLoad(WebDriver driver) {
	    new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
		

}
