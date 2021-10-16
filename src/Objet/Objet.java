package Objet;

public class Objet {
	private String nom;
	private double poids;
	private double value;
	private int indexOld;
	private double moyenne;

	public Objet() {
		nom="";
		poids=0;
		value=0;
		setMoyenne(value / poids);
	}

	public Objet(String nom1, double poids1, double value1) {
		nom=nom1;
		poids=poids1;
		value=value1;
		setMoyenne(value / poids);

	}
	
	public double getValue() {
		return value;
	}
	public double getPoids() {
		return poids;
	}
	@Override
	public String toString() {/*return "Objet : " + nom + " poids " + poids + " value " + value + "\n";*/
		return "Objet : " + nom;

	}

	public int getIndexOld() {
		return indexOld;
	}

	public void setIndexOld(int indexOld) {
		this.indexOld = indexOld;
	}

	public double getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(double f) {
		this.moyenne = f;
	}


}
