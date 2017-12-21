package tw2.bot.depozit;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Depozit {
	
	WebDriverWait wait =  null;
	Robot r= null;
	public Depozit(WebDriver driver,WebDriverWait wait,Robot robot) throws InterruptedException{
	WebElement elem  = null;
	this.wait = wait;	
	this.r = robot;
	boolean isBot=false;
		
		Thread.sleep(40000);//60 sec test
		try {
			
			//boolean isPresent = driver.findElements(By.xpath("//div[@id='interface-chat']/div/div[2]/div/div[10]/div[2]/div/div[10]")).size() > 0;
		boolean is = driver.findElements(By.xpath("//div[@id='interface-chat']/div/div[2]/div/div[10]/div[2]/div/div[10]")).get(0).isDisplayed();
			
			if(is) {
		elem= driver.findElement((By.xpath("//div[@id='interface-chat']/div/div[2]/div/div[10]/div[2]/div/div[10]")));
			
			elem.click();
			}
		}finally {
			System.out.println("no chat window acctive");
		}
		 
		boolean is = false;
		System.out.println("before deposit enter");
		try {
			is = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tooltip-map-resource-deposit']/div/div[11]/div/a"))).isDisplayed();
		if(is) {
			elem= driver.findElement(By.xpath("//div[@id='tooltip-map-resource-deposit']/div/div[11]/div/a"));

         
               /* try {
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView(true);", elem);
                } catch (Exception e) {
                		e.printStackTrace();
                }*/
            Thread.sleep(500);			
JavascriptExecutor ex = (JavascriptExecutor)driver;
ex.executeScript("arguments[0].click();", elem);
		}
		}finally {
			if(!is)
		r.keyPress(KeyEvent.VK_D); r.keyRelease(KeyEvent.VK_D);
		}
		
	 System.out.println("after deposit enter");
	 
			 Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			 String dr =  cap.getBrowserName(); 
	 if(dr.toUpperCase().contains("CHRO"))
		Thread.sleep(8000);
	 
		Actions actions = new Actions(driver);
	 WebElement  bottom= null;
	 WebElement start = null;
	 if(dr.toUpperCase().contains("CHRO"))
	 {
		boolean adu = driver.findElements(By.xpath("//a[contains(text(),'Adună')]")).size()>0;
		if(adu) {
			if(driver.findElements(By.xpath("//a[contains(text(),'Adună')]")).get(0).isDisplayed()) {
			System.out.println("Aduna available!");
			  WebElement ee = driver.findElement(By.xpath("//a[contains(text(),'Adună')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()",ee);
		}
			/*if(driver.findElements(By.xpath("//a[contains(text(),'Adună')]")).get(0).isDisplayed()) {
		WebElement e=	driver.findElement(By.xpath("//a[contains(text(),'Adună')]"));
		  actions.moveToElement(e).build().perform();//needs testing
			}*/
		}
		
		isBot =driver.findElements(By.xpath("//a[contains(text(),'Start')]")).size()>0;
		
 
		
		if(isBot) {
			System.out.println("scroll into view ");
			/* ((JavascriptExecutor) driver).executeScript(
	                    "arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[contains(text(),'Start')]")));*/
		bottom =  driver.findElement(By.xpath("//a[contains(text(),'Start')]"));
		actions.moveToElement(bottom).build().perform();
		Thread.sleep(700);
		start  = driver.findElement(By.cssSelector(".reward-corner .ng-binding"));
		}else {
			System.out.println("Nothing to do !");
		}
	 }
	 else{
		 System.out.println("in else last");
		 bottom =  driver.findElement(By.xpath("//a[contains(text(),'Start')]"));
		start  = driver.findElement(By.cssSelector(".reward-corner .ng-binding"));
	 }
		///System.out.println(start.isEnabled()+" start");
	
		
	

		 System.out.println("ready start");
		 
		 while(/*start.isEnabled() &&*/ isBot){
	
			 System.out.println("before 1st click");
		   Thread.sleep(1000);
		   System.out.println("afer scrollto");
		actions.moveToElement(bottom).perform();
			// start.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",bottom);
			 System.out.println("after 1st click");
	
		 Thread.sleep(2300);
	
		    
		// WebElement spans2 = driver.findElement(By.cssSelector("div[class=progress-text] span[class=ng-binding]"));//sterge >
		 WebElement spans2 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class=progress-text] span[class=ng-binding]")));
		// WebElement spans2 = driver.findElement(By.cssSelector(".progress-wrapper span.ng-binding"));
		 String waitString = null;
		 int waitNumber = 0;
		 boolean onlySec = false;
		waitString=spans2.getAttribute("innerHTML");
		 System.out.println("TIME TO COMPLETE: "+spans2.getText().toString()+" "+spans2.getAttribute("innerHTML"));
	
		if(waitString!=null){
			if(onlySec==false){
			  String[]tokens = waitString.split("\\s+|[,:-]");
			  int hours =  Integer.parseInt(tokens[0]);
			  int min =  Integer.parseInt(tokens[1]);
			  int secs = Integer.parseInt(tokens[2]);
			  waitNumber = secs;
			  System.out.println("Ore "+hours+" Minute:"+min+" Secunde:"+secs);
			  if(min>1)
			  Thread.sleep(TimeUnit.MINUTES.toMillis(min));
			  
			  Thread.sleep(TimeUnit.SECONDS.toMillis(secs));
			  if(min< 1 && secs<10)
				  Thread.sleep(5000);//5 secunde delay pnetru evitarea problemelor.
			}
			else{
				System.out.println("only seconds!");
				Thread.sleep(TimeUnit.SECONDS.toMillis(waitNumber));
			}
			
			  WebElement elem3 = driver.findElement(By.xpath("//a[contains(text(),'Adună')]"));
			  actions.moveToElement(elem3).build().perform();
			  Thread.sleep(500);
			  ((JavascriptExecutor) driver).executeScript("arguments[0].click()",elem3);
			/*  try{
				//  elem3.click();
				  actions.click(elem3).click();
			  }finally{
				  ((JavascriptExecutor) driver).executeScript("arguments[0].click()",elem3);
			  }*/
			 	  
					  
		}
		
		//boolean yyu =driver.findElements(By.xpath("//a[contains(text(),'Start')]")).get(0).isEnabled();
		//sau
		boolean yyu =driver.findElements(By.xpath("//a[contains(text(),'Start')]")).size() > 0;
		System.out.println("start found! "+yyu);
		if(yyu) {
		boolean stilIs = driver.findElement(By.xpath("//a[contains(text(),'Start')]")).isEnabled();
		System.out.println("start displayed! "+stilIs);
		if(stilIs) {
		bottom =  driver.findElement(By.xpath("//a[contains(text(),'Start')]"));
		actions.moveToElement(bottom).build().perform();
		Thread.sleep(400);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click()",bottom);
		 Thread.sleep(400);
		start =  driver.findElement(By.cssSelector(".reward-corner .ng-binding"));
		}
		}else {
			System.out.println("end of it!");
			isBot=false;
			//break;//no need
		}
		
	}
		
	}
	
	
}