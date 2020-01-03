/**
 * @author Janki Chaudhari
 */
 

//Imports java class needed to use linked lists
import java.util.LinkedList;

/**
 * This class implements a dictionary using a hash table with separate chaining.
 * Implementation class for DictionaryADT

 */
public class Dictionary implements DictionaryADT {
	
	private int size;
	private LinkedList<Record>[] T;	
	private int n = 0;
	
	/** 
	 * Method to compute the has function for the hash table. 
     * @param config represents the key
     * @param a is a prime constant
     * @return hash 
     */
	public int hashFunction(String config, int a) {
		int hash = 0;
		for (int i = 0; i < config.length(); i++){	
			hash = ((hash * a) + config.charAt(i)) % T.length;
			}
		return hash;
	}
	
	/** 
	 * Constructor for Dictionary that takes an integer size of the hash table.
	 * @param size input of Dictionary
     */
	public Dictionary(int size) {
		this.size = size;
		T = (LinkedList<Record>[]) new LinkedList[size];
	}

	/** 
	 * Method to find a key in the hash table.
     * @param config represents the key
     * @return T[pos].get(i).getScore() score stored in the dictionary for a given configuration
     * @return -1 configuration is not in the dictionary 
     */
	public int get(String config) {
		
		int pos = hashFunction(config, 31);
		
		if (T[pos] != null) {
			
			for (int i=0; i < T[pos].size(); i++){
				if (config.compareTo(T[pos].get(i).getConfig()) == 0) {
					return T[pos].get(i).getScore();		
				}
			}		
		}
		return -1; 
	}

	/** 
	 * Method to insert a DictEntry into the hash table.
     * @param pair DictEntry made up of config and score
     * @return 1 insertion of pair produces a collision
     * @return 0 no collision 
     */
	public int insert(Record pair) throws DictionaryException {
		
		int pos = hashFunction(pair.getConfig(), 31);
		
		if (T[pos] != null){	
			if (get(pair.getConfig()) != -1){
				throw new DictionaryException(); 
			}
			
			else {
				T[pos].add(pair);
				n ++;
				return 1;
			}			
		}	
		
		LinkedList<Record> p = new LinkedList<Record>();
		p.add(pair);	
		T[pos] = p;	
		n ++;	
		return 0;		
	}
	
	/** 
	 * Method to remove a DictEntry from the hash table.
     * @param config represents the key
     * return empty
     */
	public void remove(String config) throws DictionaryException {
		
		int pos = hashFunction(config, 31);
		
		if (T[pos]!=null){
			
			if (get(config) != -1) {
				T[pos].remove(config);  // removes the key from the position 
				n--;	
				return;
			}
		}
		
		throw new DictionaryException(); 		
	}
	
	/** 
	 * Method that counts the number of entries in the dictionary.
     * @return n number of DictEntry objects
     */
	public int numElements() {
		return n;
	}
	
}

