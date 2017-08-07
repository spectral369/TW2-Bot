package tw2.bot.army;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import tw2.bot.Assests.ArmataPrezenta;

public class Army {
	private ArmataPrezenta armata = null;

	public void armyInfo(WebDriver driver, WebDriverWait wait) {
		/// wait few Seconds
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
		
		List<WebElement> army = driver.findElements(By.xpath("//div[@id='unit-bar']/ul/li"));
	
		while(army.get(0).getText().equals("0")) {
		army = driver.findElements(By.xpath("//div[@id='unit-bar']/ul/li"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		int j = 0;
		armata = new ArmataPrezenta();
		for (WebElement g : army) {
			System.out.println("normal: " + g.getText() + " i: " + j);
			switch (j) {
			case 0:
				armata.setLancier(Integer.parseInt(g.getText()));
				break;
			case 1:
				armata.setSpadasin(Integer.parseInt(g.getText()));
				break;
			case 2:
				armata.setLuptatorCuSecure(Integer.parseInt(g.getText()));
				break;
			case 3:
				armata.setArcas(Integer.parseInt(g.getText()));
				break;
			case 4:
				armata.setCavalerieUsoara(Integer.parseInt(g.getText()));
				break;
			case 5:
				armata.setArcasCalare(Integer.parseInt(g.getText()));
				break;
			case 6:
				armata.setCavalerieGrea(Integer.parseInt(g.getText()));
				break;
			case 7:
				armata.setBerbec(Integer.parseInt(g.getText()));
				break;
			case 8:
				armata.setCatapulta(Integer.parseInt(g.getText()));
				break;
			case 9:
				armata.setBerserk(Integer.parseInt(g.getText()));
				break;
			case 10:
				armata.setTrabuchet(Integer.parseInt(g.getText()));
				break;
			case 11:
				armata.setNobil(Integer.parseInt(g.getText()));
				break;
			case 12:
				armata.setPaladin(Integer.parseInt(g.getText()));
				break;
			/// tobe completed !!!
			// default:System.out.println("error"+i);
			}
			j++;
		}
		System.out.println(armata.toString());// prezinta armata in consola.

	}

	public ArmataPrezenta getArmy() {
		return this.armata;
	}

}
