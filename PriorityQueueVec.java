import java.util.Collections;
import java.util.Vector;

public class PriorityQueueVec {
	private Vector<QueueEntry> queue;
	private int size;

	private class QueueEntry {
		private int priority;
		private Category category;
		private String categoryID;

		public QueueEntry(Category cat, int priority) throws IllegalAccessException, InstantiationException {
			category = Creator.createEmptyCategoryInstance(cat.getCategoryID());
			categoryID = cat.getCategoryID();
			this.priority = priority;
		}

		public void addPassenger(Pasager p, int priority) {
			this.priority += priority;
			category.addPassenger(p);
		}

		@Override
		public String toString() {
			return categoryID;
		}
	}

	public PriorityQueueVec() {
		queue = new Vector<>();
		size = 0;
	}

	public void insertCat(Category cat) throws InstantiationException, IllegalAccessException {
		int priority = cat.getCategoryCost();

		queue.add(new QueueEntry(cat, priority));
		size++;

		for (Pasager p: cat.getPassengers()) {
			insert(p, p.calculatePersonalCost());
		}

		heapify_up(size - 1);
	}

	public void insert(Pasager p, int priority) throws IllegalAccessException, InstantiationException {
		for(int i = size - 1; i >= 0; i--) {
			if(p.getCategoryID().equals(queue.get(i).categoryID)) {
				queue.get(i).addPassenger(p, priority);
				return;
			}
		}

		queue.add(new QueueEntry(p.getCategoryReference(), p.calculatePersonalCost()));
		size++;

		queue.get(size - 1).addPassenger(p, priority);

		heapify_up(size - 1);
	}

	private void heapify_up(int starting_point) {

		int current = starting_point;
		int parent = (current - 1) / 2;

		if (current != 0 && queue.get(current).priority > queue.get(parent).priority) {
			Collections.swap(queue, current, parent);
			heapify_up(parent);
		}
	}

	private void heapify_down(int starting_point) {
		int current = starting_point;
		int left = current * 2 + 1, right = current * 2 + 2;
		int greater = current;

		if (left < size && queue.get(left).priority > queue.get(greater).priority)
			greater = left;
		if (right < size && queue.get(right).priority > queue.get(greater).priority)
			greater = right;
		if (greater != current) {
			Collections.swap(queue, greater, current);
			heapify_down(greater);
		}
	}

	public void delete(Pasager p) {
		for (int i = 0; i < size; i++) {
			if (queue.get(i).categoryID.equals(p.getCategoryID())) {
				for (Pasager pass: queue.get(i).category.getPassengers()) {
					if (pass.equals(p)) {
						queue.get(i).category.removePassenger(pass);
						if (queue.get(i).category.isEmpty())
							delete(queue.get(i).category);
						else {
							queue.get(i).priority -= p.calculatePersonalCost();
							heapify_down(i);
						}
						break;
					}
				}
			}
		}
	}

	public void delete(Category category) {
		for (int i = 0; i < size; i++) {
			if (queue.get(i).categoryID.equals(category.getCategoryID())) {
				size--;
				Collections.swap(queue, i, size);
				queue.remove(size);
				heapify_down(i);
				break;
			}
		}
	}

	public Category embark() {
		Category category = queue.get(0).category;
		size--;
		Collections.swap(queue, 0 , size);
		queue.remove(size);
		heapify_down(0);
		return category;
	}

	private String preorder(int current) {
		int left = current * 2 + 1;
		int right = current * 2 + 2;

		if (current >= size)
			return "";

		return " " + queue.get(current).toString() + preorder(left) + preorder(right);
	}

	public String list() {
		return queue.get(0).toString() + preorder(1) + preorder(2) + '\n';
	}
}
