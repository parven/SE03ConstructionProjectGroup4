/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;


import java.io.*;
import java.util.*;

/**
 *
 * @author lewi0146
 */
public class Dictionary {

	private Map<String, DictionaryData> dictionaryMap = null;
                
	public Dictionary() {
		dictionaryMap = instantiateDictionaryMap();
	}

	protected Map<String, DictionaryData> instantiateDictionaryMap() {
                dictionaryMap = new TreeMap<String, DictionaryData>();
		//System.out.println("instantiateDictionaryMap() not implemented yet");
		return dictionaryMap;
	}

	public void insert(String s, DictionaryData data) {
                s = s.toLowerCase();  
                dictionaryMap.put(s, data);
		//System.out.println("insert() not implemented yet");
	}
        public void remove(String s) {
                s = s.toLowerCase();  
                if(contains(s)){
                    dictionaryMap.remove(s);
                }
                //should return "word does not exist, hence cannot be removed", if 'if' condition is 'false'.
		//System.out.println("remove() not implemented yet");		
	}

//	public DictionaryData remove(String s) {
//                s = s.toLowerCase();  
//                if(contains(s)){
//                    
//                }
//		//System.out.println("remove() not implemented yet");
//		return null;
//	}

	public DictionaryData lookup(String s) {
                s = s.toLowerCase();    
                DictionaryData data = dictionaryMap.get(s);
		//System.out.println("lookup() not implemented yet");
		return data;
	}

	public boolean contains(String key) {
                key = key.toLowerCase();  
		return dictionaryMap.containsKey(key);
	}

	public static Dictionary readInDictionary(String fileName) {
		Dictionary d = new Dictionary();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			while (reader.ready()) {
				String nextLine = reader.readLine();
				DictionaryData data = makeDataObjectFromLine(nextLine);
				// TODO: call insert() here to insert the data object into the dictionary! 
                                d.insert(data.getWord(), data);
			}
			if (reader!= null) {
				reader.close();
			}
		}
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return d;
	}

	private static DictionaryData makeDataObjectFromLine(String line) {
                DictionaryData data = new DictionaryData();
                //System.out.println("AND THE LINE IS : " + line);
                String[] terms = line.split(" ");
                data.setWord(terms[1]);
                data.setFrequency(Integer.parseInt(terms[2]));
                //System.out.println(data.getWord() + "AND" + data.getFrequency());
		return data;
	}

	public List<String> spellCheck(String fileName) {    
                ArrayList al = new ArrayList();
                BufferedReader reader = null;
                
                    try {
                            reader = new BufferedReader(new FileReader(new File(fileName)));
                            while (reader.ready()) {
                                    String nextLine = reader.readLine();
                                    String[] terms = nextLine.split("[ \n\t\r,.;:!?(){}]");
                                    
                                    for(int i =0; i<terms.length;i++){
                                        if(!terms[i].isEmpty()){  //if the current string is not empty.
                                            if(!contains(terms[i])){
                                                al.add(terms[i]);
                                            }
                                        }
                                    }
                            }
                            if (reader!= null) {
                                    reader.close();
                            }
                    }
                    catch (FileNotFoundException fnfe) {
                            fnfe.printStackTrace();
                    }
                    catch (IOException ioe) {
                            ioe.printStackTrace();
                    }
		//System.out.println("spellCheck() not implemented yet");
		return al;
	}

	public Collection<DictionaryData> alphabeticalList() {
                ArrayList<DictionaryData> al = new ArrayList<>(dictionaryMap.values());
                
		//System.out.println("alphabeticalList() not implemented yet");
		return al;
	}

	public Collection<DictionaryData> frequencyOrderedList() {
                ArrayList<DictionaryData> al = new ArrayList<>(dictionaryMap.values());
                Collections.sort(al);
		//System.out.println("frequencyOrderedList() not implemented yet");
		return al;
	}

	public static void main(String[] args) {
		String fileName = "word-freq.txt";
		Dictionary d = readInDictionary(fileName);
		//testcp3(d);
		//testcp4(d);
		//testcp5(d);
		//testcp6(d);
		
	}
	
	private static void testcp3(Dictionary d) {
		String[] keys = {"there", "more", "appertain", "orange", "I", "i"};

		DictionaryData data = null;
		// lookup each of the keys and print out what you find
		for (String key: keys) {
			data = d.lookup(key);
			if (data != null) {
				System.out.println(data);
			}
			else {
				System.out.println(key + ": Not found");
			}
		}

		// remove one of them
		d.remove("there");

		// now do it again
		for (String key: keys) {
			data = d.lookup(key);
			if (data != null) {
				System.out.println(data);
			}
			else {
				System.out.println(key + ": Not found");
			}
		}
		
		//TODO: insert the word "aaaarghhh" with frequency 1000000;
                data.setWord("aaaarghhh");
                data.setFrequency(1000000);
                d.insert(data.getWord(), data);
		// now do it again
		for (String key: keys) {
			data = d.lookup(key);
			if (data != null) {
				System.out.println(data);
			}
			else {
				System.out.println(key + ": Not found");
			}
		}
		
	}

	private static void testcp4(Dictionary d) {
		String fileToCheck = "quantum-mechanics.txt";	//TODO: fill this in!
		System.out.println(d.spellCheck(fileToCheck));
	}

	private static void testcp5(Dictionary d) {
		// print out the first 20 words in alphabetical order
		DictionaryData data = null;
		Collection<DictionaryData> lA = d.alphabeticalList(); 
		if (lA != null) {
			Iterator<DictionaryData> itA = lA.iterator();
			for (int i = 0; i < 20; i++) {
				data = itA.next();
				System.out.println(data);
			}
		}
	}

	private static void testcp6(Dictionary d) {
		DictionaryData data = null;
		// print out the first 20 words in frequency order
		Collection<DictionaryData> lF = d.frequencyOrderedList();
		if (lF != null) {
			Iterator<DictionaryData> itF = lF.iterator();
			for (int i = 0; i < 20; i++) {
				data = itF.next();
				System.out.println(data);
			}
		}
	}
}


