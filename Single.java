import java.util.Vector;

/*
 * O categorie de pasageri de tip single difera de celalte doua tipuri, in sensul in care o astfel de structura poate
 * contine un singur pasager, astfel ca la apelul de add si remove doar se va seta variabila passenger la o referinta
 * catre un pasager (in cazul add) sau la null (in cazul remove), astfel ca verificarea isEmpty este o verificare a
 * acestei referinte, mai precis daca aceasta este null sau nu.
 *
 * Totodata, apelul functiei getPassengers va intoarce un vector cu un singur element, pentru a pastra generalitatea
 * de care aceste clase beneficiaza in restul codului (fiind usor interschimbabile).
 */
public class Single extends Category {

	private Pasager passenger;

	public Single(String categoryID) {
		super(categoryID);
	}

	@Override
	public void addPassenger(Pasager p) {
		passenger = p;
	}

	@Override
	public void removePassenger(Pasager p) {
		passenger = null;
	}

	@Override
	public Vector<Pasager> getPassengers() {
		Vector<Pasager> new_vec = new Vector<>();
		new_vec.add(passenger);
		return new_vec;
	}

	@Override
	public boolean isEmpty() {
		return passenger == null;
	}

	@Override
	public int getCategoryCost() {
		return 0;
	}

	@Override
	public String toString() {
		return passenger.toString();
	}
}
