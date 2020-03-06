import java.util.StringTokenizer;

/*
 * Clasa folosita la crearea de entitati de tip pasager, bilet si categorie, pe baza stringurilor aferente, citite din
 * fisierul de input, sau pentru crearea de copii ale unei categorii
 */
public class Creator {

	//creeaza o entitate pasager pe baza informatiilor primite din fisierul de input, si a categoriei in care se afla
	public static Pasager createPassenger(String info, Category category) {
		StringTokenizer tokenizer = new StringTokenizer(info);
		if (tokenizer.countTokens() != 6)
			return null;

		String categoryID = tokenizer.nextToken();
		String name = tokenizer.nextToken();
		int age = Integer.parseInt(tokenizer.nextToken());
		Ticket ticket = Creator.createTicket(tokenizer.nextToken());
		boolean highPriority = Creator.createBoolean(tokenizer.nextToken());
		boolean specialNeeds = Creator.createBoolean(tokenizer.nextToken());

		return new Pasager(categoryID, name, age, ticket, highPriority, specialNeeds, category);
	}

	//creeaza o valoare booleana pe baza unui string pentru a fi folosit in createPassenger
	public static boolean createBoolean(String booleanAsAString) {
		if (booleanAsAString.equals("true"))
			return true;
		else
			return false;
	}

	/*
	 * creeaza o entitate bilet, care desi putea fi un simplu string, am ales sa fie facute pe baza unei interfete
	 * pentru a demonstra principiile de polimorfism si abstractizare
	 *
	 * Practic se foloseste un Factory Design Pattern pentru crearea obiectelor
	 */
	public static Ticket createTicket(String ticketType) {
		switch (ticketType) {
			case "e":
				return new Economic();
			case "b":
				return new Business();
			case "p":
				return new Premium();
			default:
				return null;
		}
	}

	/*
	 * Acelasi fel de Factory Desgin Pattern se foloseste si pentru creearea categoriei din care un pasager face parte,
	 * pe baza informatiilor pe care le primim de la input (f - familie, g - grup, s - singur), si adauga pasagerul
	 * respectiv in acea categorie
	 */
	public static Category createCategory(String info) {
		char c = info.charAt(0);
		Category category = null;
		StringTokenizer tokenizer = new StringTokenizer(info);
		String categoryID = tokenizer.nextToken();

		switch (c) {
			case 'f':
				category = new Family(categoryID);
				break;
			case 'g':
				category = new Group(categoryID);
				break;
			case 's':
				category = new Single(categoryID);
				break;
		}

		if (category != null)
			category.addPassenger(Creator.createPassenger(info, category));

		return category;
	}

	//creeaza o copie goala(fara pasageri) a unei instante Category, pe baza id-ului categoriei, cu rol de nod in coada
	public static Category createEmptyCategoryInstance(String categoryID) {
		char c =  categoryID.charAt(0);
		Category category = null;


		switch (c) {
			case 'f':
				category = new Family(categoryID);
				break;
			case 'g':
				category = new Group(categoryID);
				break;
			case 's':
				category = new Single(categoryID);
				break;
		}

		return category;
	}
}
