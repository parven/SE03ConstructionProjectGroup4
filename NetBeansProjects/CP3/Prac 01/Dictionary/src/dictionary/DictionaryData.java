/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author lewi0146
 */
public class DictionaryData implements Comparable<DictionaryData> {
    
    private String word;
    private int frequency;

//    public DictionaryData(String word, int frequency) {
//        this.word = word;
//        this.frequency = frequency;
//    }
    
    public void setWord(String word){
        this.word = word;
    }
    
    public void setFrequency(int frequency){
        this.frequency = frequency;
    }
    
    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }
    
        
    @Override
    	public String toString() {
		// for example : "orange: frequency = 518";
            StringBuilder sb = new StringBuilder();
            sb.append(word);
            sb.append(": frequency = ");
            sb.append(frequency);
        
        return sb.toString();
		
	}

    @Override
    public int compareTo(DictionaryData o) {
        return this.frequency - o.frequency;
    }
}
