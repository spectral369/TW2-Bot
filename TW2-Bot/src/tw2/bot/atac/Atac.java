package tw2.bot.atac;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tw2.bot.Assests.ArmataPrezenta;
import tw2.bot.army.Army;
import tw2.bot.army.Greedy;
public class Atac {
Greedy imp = null;
ArmataPrezenta original= null;
Army narmy = null;

public void atacPlayer(WebDriver driver, ArmataPrezenta army,List<String>sat,
		WebDriverWait wait,Robot rob,boolean maxAll,boolean ofiter,boolean medic,ArmataPrezenta arm,DateTime dt) {
	this.original =  new ArmataPrezenta(army);
	List<ArmataPrezenta> prada =  new ArrayList<>();
	if(maxAll) {
		ArmataPrezenta all =  this.original;
		all.setLancier(0);
		all.setArcas(0);
		all.setSpadasin(0);
		all.setTrabuchet(0);
		
	prada.add(all);
	}
	else
		prada.add(arm);
	
	Raid pr =  new Raid(driver, army, sat, wait, 0, 0, rob, prada, 0,ofiter,medic,dt);
	pr.start();
	try {
		pr.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}





	public void atac(WebDriver driver,ArmataPrezenta army, List<String> sate,
			WebDriverWait wait,int cicluri,int cantitatePradaPerAtac,Robot rob) throws InterruptedException{
		this.original =  new ArmataPrezenta(army);
List<Raid> th2 =  new ArrayList<>();
List<ArmataPrezenta> prada= testImpartireArmata(army,cantitatePradaPerAtac);
for(int j = 0;j<cicluri;j++)
		for(int i=0;i<sate.size();i++){
			th2.add(new Raid(driver, original, sate, wait, cicluri, cantitatePradaPerAtac, rob, prada, i,false,false,null));
			
		}



ScheduledThreadPoolExecutor service2 =  new ScheduledThreadPoolExecutor(sate.size());
for(int i =0;i<th2.size();i++) {
	service2.schedule(th2.get(i),i*32, TimeUnit.SECONDS);
	//service2.setKeepAliveTime((th2.get(i).mii*2)+20000, TimeUnit.MILLISECONDS);
	service2.setKeepAliveTime(2000, TimeUnit.MILLISECONDS);
}
Thread.sleep(2000);
service2.allowsCoreThreadTimeOut();

while(service2.getActiveCount()>0) {
	try {
		System.out.println("acctive count: "+service2.getActiveCount());
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
service2.shutdown();
service2.awaitTermination(30, TimeUnit.SECONDS);
service2.shutdownNow();
//service2.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

	}
	private List<ArmataPrezenta> testImpartireArmata(ArmataPrezenta army,int cantitatePrada){
		imp =  new Greedy();
		imp.pregatesteTrupe(army, cantitatePrada);
		List<ArmataPrezenta> current = imp.getArmyConfigArm();
		
		return current;
	
	}
	
	private int[] testCoordonateParsing(List<String> sate,int i){
		String tokens[] =  sate.get(i).split("[|:]");
		int[] r = new int[2];
		r[0] = Integer.parseInt(tokens[0]);
		r[1] = Integer.parseInt(tokens[1]);
		return r;
		
	}
	
	private int[] splitDestinationTime(String element){
		
		String[]tokens = element.split("\\s+|[,:-]");
		
			
		//tokens[0] = tokens[0].substring(0, tokens[0].length());
		int[] tpm = new int[3];
		  tpm[0] =  Integer.parseInt(tokens[0]);
		  tpm[1] =  Integer.parseInt(tokens[1]);
		  tpm[2] = Integer.parseInt(tokens[2]);
		  return tpm;
		  
		
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	class Raid extends Thread 
	{
		WebDriver driver=null;
		ArmataPrezenta army=null;
		List<String> sate=null;
		WebDriverWait wait=null;
		int cicluri;
		int cantitatePradaPerAtac;
		Robot rob;
		 long mii= 0;
		 List<ArmataPrezenta> prada = null;
		 int i;
		 boolean ofiter = false;
		 boolean medic =  false;
		 DateTime dt = null;
	    public Raid(WebDriver driver,ArmataPrezenta army, List<String> sate,WebDriverWait wait,
	    		int cicluri,int cantitatePradaPerAtac,Robot rob,List<ArmataPrezenta> prada,int i,
	    		boolean ofiter,boolean medic,DateTime dt) 
	        {
	            this.driver = driver;
	            this.army=army;
	            this.sate=sate;
	            this.wait = wait;
	            this.cicluri=cicluri;
	            this.cantitatePradaPerAtac=cantitatePradaPerAtac;
	            this.rob=rob;
	            this.prada = prada;
	            this.i = i;
	            this.ofiter=ofiter;
	            this.medic=medic;
	            this.dt=dt;
	          
	        }
	    @Override
	    public void run() 
	        {
	    	 WebElement loc =  wait.until(ExpectedConditions.elementToBeClickable(By.id("world-map")));
	    	 try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}// 5 secunde de asteptare
	    	 loc.click();
	    	 try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	 ////td/input
	    	 loc =  driver.findElement(By.xpath("//input[@type='number']"));
	    	 int[] r = testCoordonateParsing(sate, i);
	    	 loc.clear();
	    	 loc.sendKeys(String.valueOf(r[0]));
	    	 ////td[2]/input
	    	 loc =driver.findElement(By.xpath("(//input[@type='number'])[2]"));
	    	 loc.clear();
	    	 loc.sendKeys(String.valueOf(r[1]));
	    	 loc = driver.findElement(By.xpath("//div[2]/div/div/div/table/tbody/tr/td[3]/div"));
	    	 loc.click();
	    	 try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	 loc =  driver.findElement(By.cssSelector("li.context-menu-item.custom-army > div > div.border.ng-scope"));
	    	 loc.click();
	    	 //test
	    	 try {
				Thread.sleep(6000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	// loc = driver.findElement(By.xpath("//li[2]/div/div[2]/div/div/a[2]/span[2]"));
	    	// loc.click();
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 /*test grija*/
	    	 int h = 1;
	    	 
	    if(army.getLancier()!=0){
	    	if(prada.get(i).getLancier()!=0){
	    		rob.mouseWheel(-40);
	    		 loc =  driver.findElement(By.xpath("//li["+h+"]/div/div[3]/input"));
	    		 loc.sendKeys(String.valueOf(prada.get(i).getLancier()));
	    	 h++;
	    	
	    	 }else
	    	 h++;
	    }
	    	 if(army.getSpadasin()!=0){
	    		 h++;
	    		
	    	 }
	    	 if(army.getLuptatorCuSecure()!=0){
	    	 if(prada.get(i).getLuptatorCuSecure()!=0 ){
	    		 loc =  driver.findElement(By.xpath("//li["+h+"]/div/div[3]/input"));
	    		 loc.sendKeys(String.valueOf(prada.get(i).getLuptatorCuSecure()));
	    		 h++;
	    		
	    		 }else
	    	 h++;
	    	 }
	    	 
	    	 if(army.getArcas()!=0){
	    		 h++;
	    		
	    	 }
	    	 if(army.getCavalerieUsoara()!=0){
	    	 if(prada.get(i).getCavalerieUsoara()!=0  ){
	    		 loc =  driver.findElement(By.xpath("//li["+h+"]/div/div[3]/input"));
	    		 loc.sendKeys(String.valueOf(prada.get(i).getCavalerieUsoara()));
	    		 h++;
	    		
	    		 }else
	    	 h++;
	    	 }
	    	 if(army.getArcasCalare()!=0){
	    	 if(prada.get(i).getArcasCalare()!=0 ){
	    		 loc =  driver.findElement(By.xpath("//li["+h+"]/div/div[3]/input"));
	    		 loc.sendKeys(String.valueOf(prada.get(i).getArcasCalare()));
	    		 h++;
	    		
	    		 }else  h++;
	    	 }
	    	 if(army.getCavalerieGrea()!=0){
	    	 if(prada.get(i).getCavalerieGrea()!=0 ){
	    		 loc =  driver.findElement(By.xpath("//li["+h+"]/div/div[3]/input"));
	    		 loc.sendKeys(String.valueOf(prada.get(i).getCavalerieGrea()));
	    		 h++;
	    		
	    		 }else  h++;
	    	 }
	    	 if(army.getBerbec()!=0){
	    		 h++;
	    		
	    	 }
	    	 if(army.getCatapulta()!=0){
	    		 h++;
	    		
	    	 }
	    	 if(army.getBerserk()!=0){
	    		 h++;
	    		
	    	 }
	    	 if(army.getTrabuchet()!=0){
	    		 h++;
	    		
	    	 }
	    	 if(army.getNobil()!=0){
	    		 h++;
	    		
	    	 }
	    	 if(army.getPaladin()!=0){
	    	 if(prada.get(i).getPaladin()!=0){
	    		 rob.mouseWheel(40);
	    		 loc =  driver.findElement(By.xpath("//li["+h+"]/div/div[3]/input"));
	    		 loc.sendKeys(String.valueOf(prada.get(i).getPaladin()));
	    		 h++;
	    		
	    		 }else  h++;
	    	 }
	    	
	    	 
	    	 List<WebElement> timp = driver.findElements(By.cssSelector(".text-center.new.ng-binding"));
	    	 String time =null;
	    	 for(WebElement s :timp){
	    	
	    	//	 System.out.print("Item: "+s);
	    	 if(s.getAttribute("innerHTML").startsWith("~")||s.getAttribute("innerHTML").contains("~") ||s.getAttribute("innerHTML").matches("~|~")){
	    		 time = new String(s.getAttribute("innerHTML").substring(s.getAttribute("innerHTML").lastIndexOf("~")+1,s.getAttribute("innerHTML").length() ));
	    		 	 
	    	 }
	    	 }
	    	 
	    	 ///add ofiter and medic.
	    	 if(cantitatePradaPerAtac==0 && prada.size()==1) {
	    		 System.out.println("inside player attack");
	    		 
	    		  List<WebElement> reachTime =  driver.findElements(By.cssSelector("div.text-center.new.full-width.ng-binding"));
	    		
	    			 System.out.println(reachTime.get(0).getText());
	    			 int[]  asda= splitDestinationTime(reachTime.get(0).getText());
	    			 System.out.println("Ore: "+asda[0]);
	    			 System.out.println("Minute: "+asda[1]);
	    			 System.out.println("Secunde: "+asda[2]);
	    	
	    			 
	    				DateTime dt1 = DateTime.now();
	    			
	    				DateTime dt2 = dt;
	    				if(asda[0]!=0)
	    				dt2 =  dt2.minusHours(asda[0]);
	    				dt2 =  dt2.minusMinutes(asda[1]);
	    				dt2 =  dt2.minusSeconds(asda[2]);
	    				
	    				Period p = new Period(dt1, dt2);
	    				if(p.getMinutes()<0) {
	    					dt2 = dt2.plusDays(1);
	    					p =  new Period(dt1,dt2);
	    				}
	    			
	    				System.out.println("HOURS: " + p.getHours());
	    				System.out.println("MINUTES: " + p.getMinutes());
	    				System.out.println("SECONDS: " + p.getSeconds());
	    				rob.mouseWheel(200);
	    		 List<WebElement> boots =  driver.findElements(By.cssSelector("div.switch.switch-34x66.switch-vertical"));
	    		 if(ofiter)
	    			 boots.get(0).click();
	    		 if(medic)
	    			 boots.get(2).click();
	    		 
	    	System.out.println("Hours to millis: "+TimeUnit.HOURS.toMillis(p.getHours()));
	    	System.out.println("Minutes to millis: "+TimeUnit.HOURS.toMillis(p.getMinutes()));
	    	System.out.println("Seconds to millis: "+TimeUnit.HOURS.toMillis(p.getSeconds()));
	    	try {
	    		if(p.getHours()!=0)
				Thread.sleep(TimeUnit.HOURS.toMillis(p.getHours()));
				Thread.sleep(TimeUnit.MINUTES.toMillis(p.getMinutes()));
				Thread.sleep(TimeUnit.SECONDS.toMillis(p.getSeconds()));
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    		 
	    	 }
	    	 
	    	
	    	 /*if(loc.isEnabled())
	    		 loc.click();*/
	    	 
	    	 loc =  driver.findElement(By.linkText("Atac"));
	    	 loc.click();
	    	 try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 if(time!=null && cicluri>=1){
	    		 System.out.println(time);
	    		 int[]  tmp= splitDestinationTime(time);
	    		
	    		 DateTime dt = new DateTime();  // current time
	    		 DateTime old = null;
	    		
	    			old =  new DateTime()
	    					    .withHourOfDay(tmp[0])
	    					    .withMinuteOfHour(tmp[1])
	    					    .withSecondOfMinute(tmp[2]);
	    		
	    		 

	    		
	    		 this.mii =  old.getMillis()-dt.getMillis();
	    		 System.out.println("Durata asteptare: "+TimeUnit.MILLISECONDS.toMinutes(this.mii)+" minute");
	    		System.out.println("secunde: " +TimeUnit.MILLISECONDS.toSeconds(this.mii));
	    		
	    		
	    	 }
	    	 waiting();
	    	 }

	        
	    
	    public void waiting(){
	    	   try {
	    		   
	    		  
	    		   long m =  TimeUnit.MILLISECONDS.toSeconds(this.mii);
	    		   System.out.println("Inside wait seconds method: "+m);
	    		   Thread.sleep(TimeUnit.SECONDS.toMillis(m));  //x2 pentru dus si intors( presupunand ca durata de intoarece este egala cu cea de atac
	    		   Thread.sleep(TimeUnit.SECONDS.toMillis(m));;
		    		 Thread.sleep(TimeUnit.MINUTES.toMillis(1));//1 minut pentru ca asa o vrut sfantul trompi
		         this.join();
	   		} catch (InterruptedException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}
	    }
	    
	}




	
}
