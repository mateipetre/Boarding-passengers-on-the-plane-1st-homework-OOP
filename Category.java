import java.util.Vector;
/*
 * Clasa abstracta Category arata inca odata conceptele de polimorfism si abstractizare, fiind implementarea de baza
 * pentru cele trei tipuri de categorii de oameni: grup, familie sau singur.
 *
 * Acestea au la baza un id de categorie, functia getter pentru acesta, si constructorul de baza al clasei care seteaza
 * acest id la declarare.
 *
 * Functiile abstracte urmeaza a fi implementate in fiecare clasa ce mosteneste clasa Category si au urmatoarele
 * functionalitati:
 *  - addPassenger - adauga pasageri in structura corespunzatoare tipului de categorie
 *  - removePassenger - sterge pasageri din structura respectiva
 *  - isEmpty - ne spune daca grupul este gol
 *  - getPassengers - intoarce un vector cu toti pasagerii din grupul respectiv
 *  - getCategoryCost - intoarce costul categoriei - grup 5, familie 10, singur 0
 */
public abstract class Category {
	private String  categoryID;

	public Category(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public abstract void addPassenger(Pasager p);
	public abstract void removePassenger(Pasager p);
	public abstract Vector<Pasager> getPassengers();
	public abstract boolean isEmpty();
	public abstract int getCategoryCost();
}
