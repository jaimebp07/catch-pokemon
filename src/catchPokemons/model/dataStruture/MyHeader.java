package catchPokemons.model.dataStruture;

/**
 * 
 * @author jaime
 *
 * @param <H> tipo de header
 * @param <C> tipo de celda
 */
public class MyHeader <H, C> {
    protected H info;
    protected MyCell <C> firstCell;

    public MyHeader( H info, MyCell<C> firstCell) {
        this.info = info;
        this.firstCell = firstCell;
    }
    
    public MyHeader(H info) {
        this.info = info;
        this.firstCell = null;
    }

    public H getInfo() {
        return info;
    }

    public MyCell<C> getFirstCell() {
        return firstCell;
    }
    
    public boolean firtsIsEmpty() {
    	return firstCell == null;
    }

	public MyCell<C> peekNextDown(int k) {
		MyCell<C> auxCell = firstCell;
		for (int i = 1; i < k; i++) {
			auxCell = auxCell.nextDown;
		}
		return auxCell;
	}
	
	public MyCell<C> peekNextRigth(int k) {
		MyCell<C> auxCell = firstCell;
		for (int i = 1; i < k; i++) {
			auxCell = auxCell.nextRigth;
		}
		return auxCell;
	}
}