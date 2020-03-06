/*
 * Pentru a arata principiul de polimorfism
 */
public interface Ticket {
	public String getTicketType();
}

class Business implements Ticket {
	@Override
	public String getTicketType() {
		return "Business";
	}
}

class Economic implements Ticket {
	@Override
	public String getTicketType() {
		return "Economic";
	}
}

class Premium implements Ticket {
	@Override
	public String getTicketType() {
		return "Premium";
	}
}


