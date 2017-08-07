package tw2.bot.army;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import tw2.bot.Assests.ArmataPrezenta;

public class Greedy {
	String[] list = { "Paladin", "Lancier", "CavalerieUsoara", "ArcasCalare", "LuptatorCuSecure", "CavalerieGrea" };
	ArrayList<ArrayList< Raid>> raids =  null;
	//ArmataPrezenta arm[] = null; 
	List<ArmataPrezenta> arm = null;
	ArmataPrezenta army =null;
	public void pregatesteTrupe(ArmataPrezenta armata, int cantitatePrada) {
		this.army=new ArmataPrezenta(armata);
	//	arm =  new ArmataPrezenta[25]; 
		arm =  new ArrayList<ArmataPrezenta>();
		LinkedHashMap<String, Integer> map = ierarhieUnitatiAtac(army);
		Map<String, Integer> ar = new LinkedHashMap<>();
		
	ArmataPrezenta aa =  new ArmataPrezenta();
		int tot = 0;
		int goalCounter =1;
		for (String s : list) {
			System.out.println(s);
			if (army.getByName(s) != 0) {
				int nr = army.getByName(s);
System.out.println("total arm nr: "+nr);
				for (int t = 1; t <=nr; t++) {

					tot += map.get(s);

					army.setByName(s, army.getByName(s) - 1);
					aa.setByName(s, aa.getByName(s)+1);
					if (tot >= cantitatePrada) {

						String mn = s.concat(String.valueOf(t));
						ar.put(mn, tot);
						//arm[goalCounter-1] = aa;
						//arm.add(aa);
						arm.add(goalCounter-1, aa);
						aa =  new ArmataPrezenta();
						tot = 0;
						goalCounter++;
					//	System.out.println("in part: t:"+t+" nr: "+nr);
						nr = army.getByName(s);
						t=1;//t=0 orginal
					//	System.out.println("in part after reset t:"+t+" nr: "+nr);
						continue;
					}

				}

				ar.put(s, tot);
				//arm[goalCounter-1] = aa;
				//check for last raid not to be empty
				boolean last=false;
				for(String l:list)
					if(aa.getByName(l)!=0)
						last =true;
				if(!last)
				arm.add(goalCounter-1, aa);
			
			}

		}
		int [][] ints = new int[goalCounter][ar.size()];
		int h = 0;
		int q = 0;
		 for (String key: ar.keySet()) {
			 
			 System.out.println("key : " + key);
		    	System.out.println("value : " +ar.get(key));
		    	ints[q][h] =ar.get(key);
		    	h++;
		    	if(ar.get(key)>=cantitatePrada){
		    		q++;
		    		h=0;
		    	} 
		    	
		 }
		 
		/*	for(int i = 0 ;i<goalCounter; i++){///matricea 
				for(int j = 0; j<ar.size(); j++){
					System.out.print(ints[i][j]+" ");
				}
				System.out.println("");
			}*/
		
			raids = new ArrayList<ArrayList< Raid>>();
			 for(int d=0;d<=q;d++){
			    	//System.out.println(d);
			    	
			    	ArrayList< Raid> co = firstFit(ints[d],cantitatePrada) ;
			raids.add(co);
					       /* System.out.println("Container " + (d + 1)
					                + " contains objects with weight " + co.get(0));*/
			    }
			 int fd = 0;
			  for(ArmataPrezenta f : arm){
				   if(f!=null){
					System.out.println("raid "+fd+" -> "+f.toString());
				   System.out.println("--------------------------");
				   fd++;
				   }
				 }
		 
//
	}
	public ArrayList<ArrayList<Raid>> getArmyConfigPrada(){
		return this.raids;
	}
	public List<ArmataPrezenta> getArmyConfigArm(){
		return this.arm;
	}
	public String[] getConfList(){
		return list;
	}
	


	public LinkedHashMap<String, Integer> ierarhieUnitatiAtac(ArmataPrezenta army) {
		LinkedHashMap<String, Integer> linkedList = new LinkedHashMap<String, Integer>();

		for (int i = 0; i < list.length; i++) {

			if (list[i] == "Lancier" && army.getLancier() != 0)
				linkedList.put("Lancier", 25);
			else if (list[i] == "Spadasin" && army.getSpadasin() != 0)
				linkedList.put("Spadasin", 15);
			else if (list[i] == "LuptatorCuSecure" && army.getLuptatorCuSecure() != 0)
				linkedList.put("LuptatorCuSecure", 20);
			else if (list[i] == "Arcas" && army.getArcas() != 0)
				linkedList.put("Arcas", 10);
			else if (list[i] == "CavalerieUsoara" && army.getCavalerieUsoara() != 0)
				linkedList.put("CavalerieUsoara", 50);
			else if (list[i] == "ArcasCalare" && army.getArcasCalare() != 0)
				linkedList.put("ArcasCalare", 50);
			else if (list[i] == "CavalerieGrea" && army.getCavalerieGrea() != 0)
				linkedList.put("CavalerieGrea", 50);
			else if (list[i] == "Berbec" && army.getBerbec() != 0)
				linkedList.put("Berbec", 0);
			else if (list[i] == "Catapulta" && army.getCatapulta() != 0)
				linkedList.put("Catapulta", 0);
			else if (list[i] == "Berserk" && army.getBerserk() != 0)
				linkedList.put("Berserk", 0);
			else if (list[i] == "Trabuchet" && army.getTrabuchet() != 0)
				linkedList.put("Trabuchet", 0);
			else if (list[i] == "Nobil" && army.getNobil() != 0)
				linkedList.put("Nobil", 0);
			else if (list[i] == "Paladin" && army.getPaladin() != 0)
				linkedList.put("Paladin", 100);
		}

		return linkedList;
	}

	public static ArrayList<Raid> firstFit(int[] item,int cantitatePrada) {
		ArrayList< Raid> list = new ArrayList<>();

		for (int i = 0; i < item.length; i++) {
			boolean added = false;
			for ( Raid  Raid : list) {
				if ( Raid.addItem(item[i])) {
					added = true;
					break;
				}
			}
			if (!added) {
				 Raid  Raid = new  Raid(cantitatePrada);
				 Raid.addItem(item[i]);
				list.add(Raid);
			}
		}
		return list;
	}

}

class Raid {
	private ArrayList<Integer> objects = new ArrayList<>();
	private int prada = 8000;
	private int totalPrada = 0;

	public Raid() {
	}

	public Raid(int cantitate) {
		this.prada = cantitate;
	}

	public boolean addItem(int weight) {
		if ((totalPrada + weight) <= prada) {
			objects.add(weight);
			totalPrada += weight;
			return true;
		} else {
			return false;
		}
	}

	public int getNumberOfObjects() {
		return objects.size();
	}

	@Override
	public String toString() {
		return objects.toString();
	}

	public int getCantitatePrada() {
		return prada;
	}

	public void setParada(int cantitate) {
		this.prada = cantitate;
	}

}
