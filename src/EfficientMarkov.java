import java.util.*;

/**
 * This class is an extension of the BaseMarkov
 * with new constructors that uses HashMaps
 * and Overridden methods setTraining and
 * getFollows to add to a HashMap.
 *
 */

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;

	/**
	 * Constructs the default markov of 3-order.
	 */

	EfficientMarkov(){
		this(3);
		myMap = new HashMap<String,ArrayList<String>>();
	}

	/**
	 * Constructs a Markov of an argument order.
	 * @param order is the new order
	 */

	EfficientMarkov(int order) {
		//in order to construct the self, i must construct the parent.
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}

	/**
	 *The HashMap is constructed and the keys/values
	 * added.
	 * @param text is the training text
	 * @return a HashMap with all the possible Markov keys
	 * in the text and initializes the ArrayLists associated with keys.
	 */

	@Override
	public void setTraining(String text) {
		myMap.clear();
		myText = text;
		String key;
		String nextK;
		//super.setTraining(text);

		for (int i = 0; i < myText.length() + 1 - myOrder; i++){
			//set a key of order myOrder
			key = myText.substring(i, i+myOrder);
			//if key not seen, put in map and initialize ArrayList<String>()
			if(!myMap.containsKey(key)){
				myMap.put(key, new ArrayList<String>());
			}
			//if key is the last word in file, add a pseudo-string to indicate the end.
			if(myText.length() == i+myOrder){
				myMap.get(key).add(PSEUDO_EOS);
				break;
			}
			//if key already seen, move on the the next key as the next myOrder letters.
			else{
				nextK = myText.substring(i+myOrder, i+myOrder+1);
				myMap.get(key).add(nextK);
			}
		}

	}

	/**
	 * Looks up the values of the keys for a particular Markov key.
	 * added.
	 * @param key is a Markov key
	 * @return the values of the key.
	 */

	@Override
	public ArrayList<String> getFollows(String key) {
		if (!myMap.containsKey(key)){
			throw new NoSuchElementException(key+" not in map");
		}
		return myMap.get(key);
	}

}
