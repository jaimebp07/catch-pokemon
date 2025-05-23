package catchPokemons.model.dataStruture;
/**
 * 
 * @author Jaime Andres Baron Puin
 * @param <T> tipo de dato almacenado
 */
public class MyCell <T> {
    protected T info;
    protected MyCell <T> nextRigth;
    protected MyCell <T> nextDown;
    
    public MyCell (T info) {
    	this.info = info;
    	this.nextDown = null;
    	this.nextRigth = null;
    }

	public T getInfo() {
		return info;
	}

	public MyCell<T> getNextRigth() {
		return nextRigth;
	}

	public MyCell<T> getNextDown() {
		return nextDown;
	}
}