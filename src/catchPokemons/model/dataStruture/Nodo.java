package catchPokemons.model.dataStruture;

public class Nodo<T> {

	protected T info;
	protected Nodo<T> next;

	public Nodo(T info2) {
		this.info = info2;
	}

	public Nodo() {
	}

	public Nodo(T info2, Nodo<T> next) {
		this.info = info2;
		this.next = next;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public Nodo<T> getNext() {
		return next;
	}

	public void setNext(Nodo<T> next) {
		this.next = next;
	}
}