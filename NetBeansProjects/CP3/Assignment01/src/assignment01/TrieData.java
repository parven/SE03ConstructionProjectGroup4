package assignment01;

public class TrieData {

    private String word;
    private int frequency = -1;
    private int rank = -1;

    /**
     * Sets the word in the Data object
     *
     * @param word The word from the dictionary.
     */
    public void setWord(String word){
        this.word = word;
    }
    
    /**
     * Sets the frequency in the Data object
     *
     * @param frequency The frequency of the word from the dictionary.
     */
    public void setFrequency(int frequency){
        this.frequency = frequency;
    }
    
    /**
     * Sets the rank in the Data object
     *
     * @param rank The rank of the word from the dictionary. 
     */
    public void setRank(int rank){
        this.rank = rank;
    }
    
    /**
     * get the word from the Data object
     * 
     * @return word
     */
    public String getWord() {
        return word;
    }

    /**
     * get the frequency from the Data object
     * 
     * @return frequency
     */
    public int getFrequency() {
        return frequency;
    }
    
    /**
     * get the rank from the Data object
     * 
     * @return rank
     */
    public int getRank(){
        return rank;
    }

    public String toString() {
        return "frequency=" + frequency + ",rank=" + rank;
    }
}
