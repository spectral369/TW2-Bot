package tw2.bot.Assests;

public class ArmataPrezenta {
	
	//Defensive
	
	public ArmataPrezenta(int lancier, int spadasin, int arcas, int cavalerieGrea, int luptatorCuSecure,
			int cavalerieUsoara, int arcasCalare, int berbec, int catapulta, int berserk, int trabuchet, int nobil,
			int paladin) {
		super();
		Lancier = lancier;
		Spadasin = spadasin;
		Arcas = arcas;
		CavalerieGrea = cavalerieGrea;
		LuptatorCuSecure = luptatorCuSecure;
		CavalerieUsoara = cavalerieUsoara;
		ArcasCalare = arcasCalare;
		Berbec = berbec;
		Catapulta = catapulta;
		Berserk = berserk;
		Trabuchet = trabuchet;
		Nobil = nobil;
		Paladin = paladin;
	}


	protected int Lancier = 0;
	protected int Spadasin = 0;
	protected int Arcas = 0;
	protected int CavalerieGrea = 0;
	
	
	//ofensive
	
	protected int  LuptatorCuSecure = 0;
	protected int CavalerieUsoara = 0;
	protected int ArcasCalare = 0;
	protected int Berbec = 0;
	protected int Catapulta = 0;
	protected int Berserk = 0;
	protected int Trabuchet = 0;
	
	//nobil
	protected int Nobil = 0;
	
	//paldin
	protected int Paladin = 0;
	
	public ArmataPrezenta(){}


	public ArmataPrezenta(ArmataPrezenta armata) {
		// TODO Auto-generated constructor stub
		Lancier = armata.getLancier();
		Spadasin = armata.getSpadasin();
		Arcas = armata.getArcas();
		CavalerieGrea = armata.getCavalerieGrea();
		LuptatorCuSecure =armata.getLuptatorCuSecure();
		CavalerieUsoara = armata.getCavalerieUsoara();
		ArcasCalare = armata.getArcasCalare();
		Berbec = armata.getBerbec();
		Catapulta = armata.getCatapulta();
		Berserk = armata.getBerserk();
		Trabuchet = armata.getTrabuchet();
		Nobil = armata.getNobil();
		Paladin = armata.getPaladin();
	}


	public int getLancier() {
		return Lancier;
	}


	public void setLancier(int lancier) {
		Lancier = lancier;
	}


	public int getSpadasin() {
		return Spadasin;
	}


	public void setSpadasin(int spadasin) {
		Spadasin = spadasin;
	}


	public int getArcas() {
		return Arcas;
	}


	public void setArcas(int arcas) {
		Arcas = arcas;
	}


	public int getCavalerieGrea() {
		return CavalerieGrea;
	}


	public void setCavalerieGrea(int cavalerieGrea) {
		CavalerieGrea = cavalerieGrea;
	}


	public int getLuptatorCuSecure() {
		return LuptatorCuSecure;
	}


	public void setLuptatorCuSecure(int luptatorCuSecure) {
		LuptatorCuSecure = luptatorCuSecure;
	}


	public int getCavalerieUsoara() {
		return CavalerieUsoara;
	}


	public void setCavalerieUsoara(int cavalerieUsoara) {
		CavalerieUsoara = cavalerieUsoara;
	}


	public int getArcasCalare() {
		return ArcasCalare;
	}


	public void setArcasCalare(int arcasCalare) {
		ArcasCalare = arcasCalare;
	}


	public int getBerbec() {
		return Berbec;
	}


	public void setBerbec(int berbec) {
		Berbec = berbec;
	}


	public int getCatapulta() {
		return Catapulta;
	}


	public void setCatapulta(int catapulta) {
		Catapulta = catapulta;
	}


	@Override
	public String toString() {
		return "ArmataPrezenta [Lancier=" + Lancier + ", Spadasin=" + Spadasin + ", Arcas=" + Arcas + ", CavalerieGrea="
				+ CavalerieGrea + ", LuptatorCuSecure=" + LuptatorCuSecure + ", CavalerieUsoara=" + CavalerieUsoara
				+ ", ArcasCalare=" + ArcasCalare + ", Berbec=" + Berbec + ", Catapulta=" + Catapulta + ", Berserk="
				+ Berserk + ", Trabuchet=" + Trabuchet + ", Nobil=" + Nobil + ", Paladin=" + Paladin + "]";
	}


	public int getBerserk() {
		return Berserk;
	}


	public void setBerserk(int berserk) {
		Berserk = berserk;
	}


	public int getTrabuchet() {
		return Trabuchet;
	}


	public void setTrabuchet(int trabuchet) {
		Trabuchet = trabuchet;
	}


	public int getNobil() {
		return Nobil;
	}


	public void setNobil(int nobil) {
		Nobil = nobil;
	}


	public int getPaladin() {
		return Paladin;
	}


	public void setPaladin(int paladin) {
		Paladin = paladin;
	}
	
	public int getByName(String name){
		switch(name){
		case "Lancier":return getLancier();
		 
		case "Spadasin":return getSpadasin();
		 
		case "Arcas" : return getArcas();
		 
		case "CavalerieGrea" : return getCavalerieGrea();
		 
		case "LuptatorCuSecure" : return getLuptatorCuSecure();
		 
		case "CavalerieUsoara" : return getCavalerieUsoara();
		 
		case "ArcasCalare"  : return getArcasCalare();
		 
		case "Berbec": return getBerbec();
		 
		case "Catapulta": return getCatapulta();
		 
		case "Berserk": return getBerserk();
		 
		case "Trabuchet" : return getTrabuchet();
		 
		case "Nobil" : return getNobil();
		 
		case "Paladin": return getPaladin();
		 
		}
		return -1;
	}
	public int setByName(String name,int num){
		switch(name){
		case "Lancier":setLancier(num);
		 break;
		case "Spadasin":setSpadasin(num);
		 break;
		case "Arcas" : setArcas(num);
		 break;
		case "CavalerieGrea" :  setCavalerieGrea(num);
		 break;
		case "LuptatorCuSecure" :setLuptatorCuSecure(num);
		 break;
		case "CavalerieUsoara" : setCavalerieUsoara(num);
		 break;
		case "ArcasCalare"  : setArcasCalare(num);
		 break;
		case "Berbec": setBerbec(num);
		 break;
		case "Catapulta":  setCatapulta(num);
		 break;
		case "Berserk":setBerserk(num);
		 break;
		case "Trabuchet" : setTrabuchet(num);
		 break;
		case "Nobil" : setNobil(num);
		 break;
		case "Paladin": setPaladin(num);
		 break;
		}
		return -1;
	}
	

}
