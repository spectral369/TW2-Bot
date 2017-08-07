package tw2.bot.webdr;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebSetup {
	WebDriver driver = null;
	Robot r =  null;
	 WebDriverWait wait = null;
	// String arh = null;
	 public boolean isHeadless =false;
	public WebSetup(boolean b){
		isHeadless=b;
/*String arch = System.getenv("PROCESSOR_ARCHITECTURE");
		String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");

		/*arh = arch.endsWith("64")
		                  || wow64Arch != null && wow64Arch.endsWith("64")
		                      ? "64" : "32";
		System.out.print(arh);*/
		String os = System.getProperty("os.name").toUpperCase();
		if(os.contains("LINUX")){
			if(!isHeadless){
			System.out.println("NOT headless LInux");
				InputStream res = WebSetup.class.getResourceAsStream("/resources/chromedriver");
		
				
				File target = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "chromedriver");
				
				if(!target.exists())
				try {
					Files.copy(res, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
					Set<PosixFilePermission> perms = new HashSet<>();
					perms.add(PosixFilePermission.OWNER_READ);
					perms.add(PosixFilePermission.OWNER_WRITE);
					perms.add(PosixFilePermission.OWNER_EXECUTE);

					Files.setPosixFilePermissions(target.toPath(), perms);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
					
				}
				
				if (!target.canExecute())
					try {
						throw new FileNotFoundException("chrome(linux) copy did not work!");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				try {
					System.setProperty("webdriver.chrome.driver", target.getCanonicalPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}else{
			System.out.println("Headless LInux");
			InputStream res = WebSetup.class.getResourceAsStream("/resources/chromedriver");
			
			
			File target = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "chromedriver");
			
			if(!target.exists())
			try {
				Files.copy(res, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
				Set<PosixFilePermission> perms = new HashSet<>();
				perms.add(PosixFilePermission.OWNER_READ);
				perms.add(PosixFilePermission.OWNER_WRITE);
				perms.add(PosixFilePermission.OWNER_EXECUTE);

				Files.setPosixFilePermissions(target.toPath(), perms);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
				
			}
			
			if (!target.canExecute())
				try {
					throw new FileNotFoundException("chromedriver(headless)(linux) copy did not work!");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			try {
				
				//System.setProperty("phantomjs.binary.path", target.getCanonicalPath());
				System.setProperty("webdriver.chrome.driver", target.getCanonicalPath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		}
		if(os.contains("WIN")){
			if(!isHeadless){
				System.out.println("NOT headless WIndows");
			InputStream res = WebSetup.class.getResourceAsStream("/resources/chromedriver.exe");
		
			File target = new File(System.getProperty("java.io.tmpdir") +"chromedriver.exe");
			
			if(!target.exists())	
			try {
				
			
				
				Files.copy(res, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (!target.canExecute())
				try {
					throw new FileNotFoundException("chrome.exe(win) copy did not work!");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			try {
				System.setProperty("webdriver.chrome.driver", target.getCanonicalPath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else{
			System.out.println("Headless WIndows");
			InputStream res = WebSetup.class.getResourceAsStream("/resources/chromedriver.exe");
		
			File target = new File(System.getProperty("java.io.tmpdir") +"chromedriver.exe");
			
			if(!target.exists())	
			try {
				
			
				
				Files.copy(res, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (!target.canExecute())
				try {
					throw new FileNotFoundException("phantomjs.exe(win) copy did not work!");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			try {
				//System.setProperty("phantomjs.binary.path", target.getCanonicalPath());
				System.setProperty("webdriver.chrome.driver", target.getCanonicalPath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		}
		if(!isHeadless){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("enable-automation");
			options.addArguments("--disable-infobars");
			options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		
		System.out.println("Chrome driver instance");
		}
		else{
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("enable-automation");
			options.addArguments("--disable-infobars");
			options.addArguments("--mute-audio");
			//options.addArguments("window-size=1600x1024");
			options.addArguments("headless");
			/*Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("binary", "/usr/lib/chromium-browser/chromium-browser");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			capabilities.setCapability("headless", true);*/ //testing
			
			driver = new ChromeDriver(options);
			
			
			
			System.out.println("headless(chrome) driver instance");
			
			
		/*	new  DesiredCapabilities();
			DesiredCapabilities dCaps =  DesiredCapabilities.phantomjs();
			dCaps.setJavascriptEnabled(true);
			  dCaps.setCapability("takesScreenshot", false);
			  String[] cli_args = new String[]{"--ignore-ssl-errors=true"};
			  
			  dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cli_args );
			driver  =new PhantomJSDriver(dCaps);
			driver.manage().window().setSize(new Dimension(1920, 1080));
			System.out.println("PhantomJS driver instance");*/
			
		}
		wait = new WebDriverWait(driver, 100);
		try {
			r =  new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public WebDriver getChromeWebDriver(){
		return this.driver;
	}
	
	public Robot getAWTRobot(){
		return this.r;
	}
	
	public WebDriverWait getWait(){
		return this.wait;
	}
	
	public void quitWithDelay() throws InterruptedException{
		if(driver!=null){
			//Thread.sleep(6000);//6 secunde 
			driver.quit();
		}
			
	}


}
