import java.io.*;
import java.util.*;

//main class
public class Test {

	public static void main(String[] args) throws Exception  {
		int number_of_passengers; //prima linie din fisier care imi spune cati pasageri am
		HashMap<String, Category> passenger_map = new HashMap<>(); //map in care retin toti acesti pasageri
		File inputFile = new File("./queue.in");
		File outputFile = new File("./queue.out");
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		String current_line, current_token;
		StringTokenizer tokenizer;

		PriorityQueueVec priorityQueue = new PriorityQueueVec(); //priority queue, implementat printr-un vector heap

		//toate parsarile sunt puse intr-un try-catch pentru a evita erorile de tipul fisier lipsa/citire neregulametara
		try {
			//aici incepe parsarea pasagerilor
			current_line = br.readLine();
			number_of_passengers = Integer.parseInt(current_line);

			/*
			 * Pentru fiecare pasager, salvez datele in map, in functie de id-ul categoriei acestuia.
			 *
			 * Daca aceasta categorie nu exista inca o creez si o adaug in map, dupa care noile intrari ai categoriei
			 * respective vor fi pusi in aceasta instanta creata.
			 *
			 * Pentru detalii legate de crearea pasagerilor si a categoriilor se vor verifica clasa Creator, respectiv
			 * interfata Category, in care toate functionalitatile sunt pe larg explicate.
			 */
			for (int i = 0; i < number_of_passengers; i++) {
				current_line = br.readLine();
				tokenizer = new StringTokenizer(current_line);
				current_token = tokenizer.nextToken();

				if (passenger_map.containsKey(current_token)) {
					Category currentCategory = passenger_map.get(current_token);
					currentCategory.addPassenger(Creator.createPassenger(current_line, currentCategory));
				} else {
					passenger_map.put(current_token, Creator.createCategory(current_line));
				}
			}

			//aici incepe parsarea instructiunilor
			current_line = br.readLine();
			while (current_line != null) {
				tokenizer = new StringTokenizer(current_line);
				if (tokenizer.hasMoreTokens()) {
					current_token = tokenizer.nextToken();

					switch (current_token) {
						case "insert":
							current_token = tokenizer.nextToken();
							priorityQueue.insertCat(passenger_map.get(current_token));
							break;
						case "embark":
							priorityQueue.embark();
							break;
						case "list":
							bw.write(priorityQueue.list());
							break;
						case "delete":
							//se parseaza categoria
							current_token = tokenizer.nextToken();
							Category category = passenger_map.get(current_token);

							//se parseaza si numele persaonei din categoria respectiva, daca exista
							if (tokenizer.hasMoreTokens()) {
								current_token = tokenizer.nextToken();
								for (Pasager p : category.getPassengers()) {
									if (p.getName().equals(current_token)) {
										priorityQueue.delete(p); //se sterge persoana
										break;
									}
								}
							} else {
								priorityQueue.delete(category); //se sterge categoria
							}
							break;

					}
				}
				
				current_line = br.readLine();
			}

			br.close();
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error parsing file");
		}
	}
}