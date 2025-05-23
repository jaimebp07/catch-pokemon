package catchPokemons.model.dataStruture;

public class MyQueue<T> {

	Nodo<T> firts, last;
	int tam;

	public Nodo<T> getFirts() {
		return firts;
	}

	public int getTam() {
		return tam;
	}

	public MyQueue() {
		firts = null;
		tam = 0;
	}

	public boolean isEmpy() {
		return firts == null;
	}

	public void offer(T info) {
		Nodo<T> nodo = new Nodo<>(info);
		if (isEmpy()) {
			firts = nodo;
		} else {
			last.next = nodo;
		}
		last = nodo;
		tam++;
	}

	public T remove() {
		T aux = firts.info;
		firts = firts.next;
		tam--;
		return aux;
	}

	public T peekNext(Byte maxLetters) {
		Nodo<T> aux = firts;
		for (int i = 0; i < maxLetters; i++) {
			aux = aux.next;
		}
		return aux.info;
	}

	public T peek() {
		Nodo<T> aux = firts;
		return aux.info;
	}
}
