import java.util.Vector;

/*
 * O categorie de pasageri de tip familie va stoca pasageri intr-un vector pe care il intoarce la apelul getPassengers()
 * si in care se vor adauga pasageri/din care se vor sterge pasageri la apelul functiilor corespunzatoare.
 *
 * Restul functiilor au fost descrise anterior.
 */
public class Family extends Category {

	private Vector<Pasager> passengers;

	public Family(String categoryID) {
		super(categoryID);
		passengers = new Vector<>();
	}
	@Override
	public void addPassenger(Pasager p) {
		passengers.add(p);
	}

	@Override
	public void removePassenger(Pasager p) {
		passengers.remove(p);
	}

	@Override
	public Vector<Pasager> getPassengers() {
		return passengers;
	}

	@Override
	public boolean isEmpty() {
		return passengers.isEmpty();
	}

	@Override
	public int getCategoryCost() {
		return 10;
	}

	@Override
	public String toString() {
		return passengers.toString();
	}
}
