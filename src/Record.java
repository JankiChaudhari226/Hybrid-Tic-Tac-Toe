/**
 * class represents an entry in the dictionary, associating a configuration with its integer score.  
 * @author Janki Chaudhari
 *
 */
public class Record {
	
	private String configration;
	private int score1;
	
	/**
	 * 
	 * @param config Board Configuration 
	 * @param score represents the User Score 
	 */
	public Record(String config, int score) {
		configration = config;
		score1 = score ; 
			
	}
	
	/**
	 * returns the configuration of the board 
	 * @return String configuration 
	 */
	public String getConfig() {
		return configration; 
	}
	
	/**
	 * returns the current score 
	 * @return the integer value for score 
	 */
	 public int getScore() {
		return score1;
	 }
	
	
}
