package catchPokemons.model.dataStruture;

import java.util.Comparator;

public class SimpleList<T> {

	private Nodo<T> head;
	private Comparator<T> comparator;

	public SimpleList() {
		this.head = null;
		this.comparator = null;
	}

	public SimpleList(Comparator<T> comparator) {
		this.head = null;
		this.comparator = comparator;
	}

	public Nodo<T> getHead() {
		return head;
	}

	public void setHead(Nodo<T> head) {
		this.head = head;
	}

	public void add(T info) {
		if (head != null) {
			Nodo<T> aux = head;
			while (aux.next != null) {
				aux = aux.next;
			}
			aux.next = (new Nodo<T>(info));
		} else {
			head = new Nodo<T>(info);
		}
	}

	public void insert(T info) {
		this.head = new Nodo<T>(info, this.head);
	}

	public boolean findInfo(T reference) {
		Nodo<T> aux = head;
		boolean enc = false;
		while (aux != null && enc != true) {
			if (reference == aux.getInfo()) {
				enc = true;
			} else {
				aux = aux.next;
			}
		}
		return enc;
	}

	public boolean findList(SimpleList<T> infos, T attribute) {
		Nodo<T> aux = head;
		boolean enc = false;
		while (aux != null && enc != true) {
			if (infos.getHead().getNext().getInfo().equals(attribute)) {
				enc = true;
			} else {
				aux = aux.next;
			}
		}
		return enc;
	}

	public int getPosicion(T reference) throws Exception {
		if (findInfo(reference)) {
			Nodo<T> aux = head;
			int cont = 0;
			while (reference != aux.getInfo()) {
				cont++;
				aux = aux.next;
			}
			return cont;
		} else {
			throw new Exception("Valor inexistente en la lista.");
		}
	}

	public void removeForPosicion(T info) {
		if (info != null && head != null) {
			if (this.head.getInfo() == info) {
				this.head = this.head.next;
			} else {
				Nodo<T> ant = this.head;
				Nodo<T> aux = this.head.next;
				while (aux != null) {
					if (aux.info == info) {
						ant.next = aux.next;
					}
					ant = aux;
					aux = aux.next;
				}
			}
		}
	}

	public void remove(T info) {
		if (info != null && head != null) {
			if (head.info == info) {
				head = head.next;
			} else {
				Nodo<T> ant = head;
				Nodo<T> aux = head.next;
				while (aux != null) {
					if (aux.info == info) {
						ant.next = aux.next;
					}
					ant = aux;
					aux = aux.next;
				}
			}
		}
	}

	public void remove(SimpleList<T> infos, Comparator<T> comparator) {
		Nodo<T> aux = head;
		Nodo<T> auxP = infos.head;
		while (aux.next != null && auxP.next != null) {
			while (auxP.next != null) {
				auxP = auxP.next;
			}
		}
	}

	public void addSort(T info) {
		if (this.head != null) {
			insertByPosition(findPositionInsertWhithoutRepetitions(info), info);
		} else {
			this.head = new Nodo<>(info);
		}
	}

	public int findPositionInsertWhithoutRepetitions(T info) {
		Nodo<T> aux = head;
		int i = 1;
		while (aux != null) {
			if (comparator.compare(aux.info, info) > 0)
				return i;
			if (comparator.compare(aux.info, info) == 0)
				return 0;
			i++;
			aux = aux.next;
		}
		return i;
	}

	public int makeComparison(T infoOne, T infoTwo) {
		return comparator.compare(infoOne, infoTwo);
	}

	public int findPositionOfLocation(T info) {
		Nodo<T> aux = head;
		int i = 1;
		while (aux != null) {
			if (comparator.compare(aux.info, info) == 0)
				return i;
			i++;
			aux = aux.next;
		}
		return 0;
	}

	public void insertByPosition(int position, T info) {
		if (position > 0) {
			Nodo<T> nodoNew = new Nodo<>(info);
			if (position == 1) {
				nodoNew.next = head;
				head = nodoNew;
			} else {
				Nodo<T> nodoAux = head;
				for (int i = 1; i < (position - 1); i++) {
					nodoAux = nodoAux.next;
				}
				if (nodoAux.next == null) {
					nodoAux.next = new Nodo<T>(info);
				} else {
					nodoAux.next = new Nodo<T>(info, nodoAux.next);
				}
			}
		}
	}

	public void addT(T info) {
		if (comparator != null) {
			addSort(info);
		} else {
			add(info);
		}
	}

	public boolean isEmpty() {
		return this.head == null;
	}

	public T getElementIn(int position) {
		Nodo<T> aux = head;
		for (int j = 0; j < position; j++) {
			if (aux.next != null) {
				aux = aux.next;
			} else {
				return null;
			}
		}
		return aux.info;
	}
}