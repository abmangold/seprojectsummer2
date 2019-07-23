package atm.model;

import java.util.ArrayList;
import java.util.List;

public class AbstractModel implements Model {

	private List<ModelListener> listeners = new ArrayList<ModelListener>();
	
	public void notifyChanged(ModelEvent event) {
		for(ModelListener ml : listeners) {
			ml.modelChanged(event);
		}
	}
	
	public void addModelListener(ModelListener ml) {
		listeners.add(ml);
	}
	
	public void removeModelListener(ModelListener ml) {
		listeners.remove(ml);
	}
}
