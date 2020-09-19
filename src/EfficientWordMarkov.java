import java.util.*;
/**
 * This class is an extension of the BaseWordMarkov
 * with new constructors that uses HashMaps
 * and two Overridden methods setTraining and
 * getFollows to add to a HashMap.
 *
 */
public class EfficientWordMarkov extends BaseWordMarkov {
    private Map<WordGram, ArrayList<String>> myMap;
    /**
     * Constructs the default Wordgram of 3-order.
     */

    EfficientWordMarkov(){
        this(3);
        myMap = new HashMap<WordGram,ArrayList<String>>();
    }

    /**
     * Constructs a Wordgram of an argument order.
     * @param order is the new order
     */

    EfficientWordMarkov(int order) {
        //in order to construct the self, i must construct the parent.
        super(order);
        myOrder = order;
        myMap = new HashMap<WordGram,ArrayList<String>>();
    }

    /**
     *The HashMap is constructed and the keys/valuesadded.
     * @param text is the training text
     * @return a HashMap with all the possible WordGram keys
     * in the text and ArrayLists of possible WordGrams that follow
     * associated with keys.
     */

    @Override
    public void setTraining(String text) {
        myMap.clear();
        //myWords is a file split on \\s+
        myWords = text.split("\\s+");
        String nextK;
        //super.setTraining(text);

        for (int i = 0; i < myWords.length + 1 - myOrder; i++){
            //set a key (construct new WordGram) of order myOrder
            WordGram key = new WordGram(myWords, i, myOrder);
            //if key not seen, put in map and initialize ArrayList<String>()
            if(!myMap.containsKey(key)){
                myMap.put(key, new ArrayList<String>());
            }
            //if key is the last word in file, add a pseudo-string to indicate the end.
            if(myWords.length == i+myOrder){
                myMap.get(key).add(PSEUDO_EOS);
                break;
            }
            //if key already seen, move on the the next key as the next myOrder letters.
            else{
                nextK = myWords[myOrder + i];
                myMap.get(key).add(nextK);
            }
        }

    }

    /**
     * Looks up the values of the keys for a particular Wordgram key.
     * @param key is a WordGram key
     * @return the values of the key.
     */

    @Override
    public ArrayList<String> getFollows(WordGram key) {
        if (!myMap.containsKey(key)){
            throw new NoSuchElementException(key+" not in map");
        }
        return myMap.get(key);
    }
}
