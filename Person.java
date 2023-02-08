package conta;

public class Person {

	private String name, adresse;
	private int phoneNr;

	public Person() {

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setPhoneNr(int phoneNr) {
		this.phoneNr = phoneNr;
	}

	public int getPhoneNr() {
		return phoneNr;
	}
}
