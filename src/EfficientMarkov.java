import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	EfficientMarkov(){
		this(3);
	}

	EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}
}	
