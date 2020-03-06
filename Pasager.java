import java.util.StringTokenizer;

public class Pasager {
	private String categoryID;
	private String name;
	private int age;
	private Ticket ticket;
	private boolean highPriority;
	private boolean specialNeeds;
	private Category categoryReference;

	public Pasager(String categoryID, String name, int age, Ticket ticket, boolean highPriority, boolean specialNeeds, Category category) {
		this.categoryID = categoryID;
		this.name = name;
		this.age = age;
		this.ticket = ticket;
		this.highPriority = highPriority;
		this.specialNeeds = specialNeeds;
		categoryReference = category;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public Category getCategoryReference() {
		return categoryReference;
	}

	public String getName() {
		return name;
	}

	public int calculatePersonalCost() {
		int cost = 0;
		if (age >= 60) cost = 15;
		else if (age >= 5 && age < 10) cost = 5;
		else if (age >= 2 && age < 5) cost = 10;
		else if (age >= 0 && age < 2) cost = 20;

		switch (ticket.getTicketType()) {
			case "Business": cost += 35; break;
			case "Premium": cost += 20; break;
		}

		if (highPriority) cost +=30;
		if (specialNeeds) cost += 100;
		
		return cost;
	}

	@Override
	public String toString() {
		return categoryID + " " + name + " " + age + " " + ticket.getTicketType();
	}
}