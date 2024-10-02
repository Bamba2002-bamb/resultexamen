package ci.example.model;


public class Result {
	int id;
	int matricule;
	boolean statut;
	float moyenne;
	
	public Result(int id, int matricule, int statut, float moyenne) {
		this.id = id;
		this.matricule = matricule;
		this.statut = statut == 1;
		this.moyenne = moyenne;
	}
	
	public int getId() {
		return id;
	}
	
	public int getMat() {
		return matricule;
	}
	
	public boolean getStat() {
		return statut;
	}
	
	public float getMoy() {
		return moyenne;
	}
}
