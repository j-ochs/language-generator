import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Class LanguageGenerator produces pseudo-random, syntactically correct text based on
 * n-gram language modeling of the given corpus text.
 * @author Jacob Ochs
 * @version 042716.1
 */
public class LanguageGenerator {
	protected String[] nextWords;
	protected static String[] wordCorpus;
	protected static Random r = new Random();
	protected static Vector<String> vecNoPunct = new Vector<String>();
	protected static Vector<String> wordVec = new Vector<String>();
	protected Vector<String> nGramVec = new Vector<String>();
	protected static String stringCorpus = "";
	protected String noPunctStringCorpus = "";
	protected static Map<String, Integer> nGramMap = new TreeMap<String, Integer>();
	protected static ArrayList<Integer> oneList = new ArrayList<Integer>();
	protected static ArrayList<Integer> incList = new ArrayList<Integer>();
	protected static int ngLen;


	/**
	 * LanguageGenerator constructor creates an instance of the class and reads in the 
	 * corpus texts given by the corpusPath String argument.
	 * @param corpusPath is a String representing the file path to the corpus text documents.
	 */
	public LanguageGenerator(String corpusPath){
		try{
			File dir = new File(corpusPath);

			for (File file : dir.listFiles()) {
				Scanner s = new Scanner(file);
				while (s.hasNext()){
					
					String sCorpus = s.nextLine();
//					myCorpus = sCorpus.split(" ");
//					System.out.println("sCorpus: " + sCorpus);
//					System.out.println(myCorpus.length);
					stringCorpus += sCorpus + " ";
//					for (int i=0; i<myCorpus.length; i++){
//						wordVec.add(myCorpus[i]);
					}
				}
			}													//below: removing punctuation from the corpus vector
/*
			for (int j=0; j<wordVec.size(); j++){
				if (!Character.isLetter(wordVec.elementAt(j).charAt(wordVec.elementAt(j).length()-1))){
					System.out.println(wordVec.elementAt(j).charAt(wordVec.elementAt(j).length()-1) +" "+ wordVec.elementAt(j));
					System.out.println("substrung: " + wordVec.elementAt(j).substring(0, wordVec.elementAt(j).length()-1));
					vecNoPunct.add(wordVec.elementAt(j).substring(0, wordVec.elementAt(j).length()-1));
				}
				else {vecNoPunct.add(wordVec.elementAt(j));}
				noPunctStringCorpus += vecNoPunct.elementAt(j) + " ";
			}
			System.out.println("hello: " + noPunctStringCorpus);
			

			String[] corpus = new String[wordVec.size()];
			for (int i=0; i<wordVec.size(); i++){
				corpus[i] = wordVec.get(i);
			    System.out.println("here it is: " + corpus[i]);
			}
		}
*/
		catch(IOException e){
			System.out.println("Could not read the given text file: " + e.getMessage());
		}
	}
	
	
	
	/*
	public Vector<String> buildNGram(int n, Vector<String> v){		//n = the number of n-gram words to be generated
		int size = wordVec.size();										//v = the vector of Strings to be processed in n-gram
		String[][] nGramArray = new String[size][size];
//		System.out.println("w/Punct: " + wordVec);
//		System.out.println("noPunct: " + vecNoPunct);
		for (int i=0; i<size; i++){
			for (int r=0; r<size; r++){			//row array loop
				for (int c=0; c<size; c++){		//column array loop
					if (v.elementAt(i) != nGramArray[r][c]){
						nGramArray[i][c] = v.elementAt(i);
					}
				}
			}
			
		}
		return v;
	}
	*/
	
	
	/**
	 * ngrams generates our n-grams word tuples of any length and stores them in a TreeMap
	 * with the number of times the phrase appears in the corpus text.
	 * @param s is the full corpus String.
	 * @param len is an integer representing the number of n-grams tuples to be modeled.
	 * @return the TreeMap of word phrases and the number of times it appears in the corpus.
	 */
	public static Map<String, Integer> ngrams(String s, int len) {
		wordCorpus = s.replaceAll("[^a-zA-Z ]", "").split(" ");
		String[] result = new String[wordCorpus.length - len + 1];
		
		for(int i = 0; i < wordCorpus.length - len + 1; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j<len; j++) {
				if (j>0) sb.append(' ');
				sb.append(wordCorpus[i+j]);
			}
	//		nGramMap.put(sb.toString(), 1);
		//	if (Arrays.asList(result).contains(sb.toString())){
			if (nGramMap.containsKey(sb.toString())){
	//			System.out.println("Theres A Match !!!!");
				nGramMap.put(sb.toString(), nGramMap.get(sb.toString())+1);
		//		System.out.println(nGramMap.entrySet());
			}
			else {
				nGramMap.put(sb.toString(), 1);
			}

			result[i] = sb.toString();
//			System.out.println("SB:  "+ i + sb.toString());
//			System.out.println("result: " + Arrays.toString(result));

		}
		System.out.println("MAP ENTRY: " + nGramMap.get("he was"));
		//System.out.println(nGramMap.entrySet());

		return nGramMap;
	}
	 
	
	
	
	//CMD INPUT PARAM: l = NUMBER OF WORDS YOU WANT -- i.e. word count limit
	//2ND INPUT PARAM: TREEMAP OF N-GRAMS
	/**
	 * generateWords produces an informed String of words based on n-gram modeling.
	 * @param l is an integer representing the number of words to be generated.
	 * @param tm is a TreeMap<String, Integer> of n-grams.
	 * @return the chosen String of concatenated words generated from our n-grams.
	 */
	public String generateWords(int l, Map<String, Integer> tm){
		//	System.out.println(tm.entrySet());
		int n = 0;
		Vector<String> first = new Vector<String>();
		Vector<String> next = new Vector<String>();
		String result = "";
		for (Map.Entry<String, Integer> e : tm.entrySet()) {
			if (Character.isUpperCase(e.getKey().charAt(0))){
				first.add(e.getKey());
			}
			else {}

		}
		while (l>n){
			String[] fWord = first.get(r.nextInt(first.size())).split(" ", 2);

			//			System.out.println("BEGINNING SENTENCE: " + fWord[0]);

			//			System.out.println(subsequentWords(fWord[0], l, tm));

			for (Map.Entry<String, Integer> e : tm.entrySet()) {
				if (e.getKey().startsWith(fWord[0])){
					//					System.out.println("SUBSEQUENT WORD: " + e.getKey());
					next.add(e.getKey());
				}
				else {}
			}

			String[] nextWord = next.get(r.nextInt(next.size())).split(" ");
			result += fWord[0] + " ";
			//for (int j=0; j<ngLen; j++){
			result += nextWord[1] + " ";
			//}
			n = result.split(" ").length;
			fWord[0] = nextWord[0];
			//	while (l>n){
			//	System.out.println(subsequentWords(fWord[0], l, n, tm));
			//	}
						System.out.println("WORDS: " + n);
						System.out.println("RESULT: " + result);					//uncomment here to see result from ngram
						System.out.println("NEXT: " + Arrays.toString(nextWord));
			//if (n >= l)result;
			//generateWords(l-n, nGramMap);
		}
		System.out.println("RESULT: " + result);
		return result;
	}
	
	
	
	/**
	 * subsequentWords method gives us all the words proceeding the first word in an input string param.
	 * @param s is the String of words from which we want the words following the first.
	 * @param l is the limiting integer
	 * @param n is the number of n-gram integer
	 * @param tm is the TreeMap of n-grams
	 * @return the resulting string of subsequent words
	 */
	public static String subsequentWords(String s, int l, int n, Map<String, Integer> tm){
		//	int n = 0;
		String result = "";
		Vector<String> next = new Vector<String>();

		while (l>n){
			for (Map.Entry<String, Integer> e : tm.entrySet()) {
				if (e.getKey().startsWith(s)){
					System.out.println("SUBSEQUENT WORD: " + e.getKey());
					next.add(e.getKey());
				}
				else {}
			}
			String nextWord = next.get(r.nextInt(next.size()));
			result += next.get(r.nextInt(next.size())) + " ";
			n = result.split(" ").length;
			s = nextWord;

			System.out.println("COUNT: " + n);
		}
		return result;
	}
	
	
	
	
	/**
	 * generateRandom produces an uninformed random String of words of a given param-length n.
	 * @param n is a integer representing the number of words to be generated.
	 * @param corpus is the String Array of corpus words from which we will randomly choose.
	 * @return the resulting randomly-generated String of words.
	 */
	public static String generateRandom(int n, String[] corpus){
		String result = "";				//n = number of words to be generated
		String punct = "";
		for (int i=0; i<n; i++){
			result += corpus[r.nextInt(corpus.length)] + " ";
		}
		result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
		punct += "." + "?" + "!";
		result = result.substring(0, result.length()-1);
		result += punct.charAt(r.nextInt(punct.length()));
		System.out.println(result);
		
		return result;
	}
	
	
	
	
	/**
	 * The main method exercises the functionality of methods in the class.
	 * @param args is a command line String argument.  We will have 3 cmd arguments:
	 * (1) the corpus path, (2) the number of n-grams to be modeled, and (3) the 
	 * number of words to be generated.
	 */
	public static void main(String[] args){						//1st argument is the path to the folder containing
		LanguageGenerator lg = new LanguageGenerator(args[0]);	//the (4) corpus text documents.  2nd argument is the
		System.out.println("CorpusPath: " + args[0]);			//number of n-gram to be modeled.  3rd argument
		lg.ngrams(stringCorpus, Integer.parseInt(args[1]));
	//	System.out.println(Arrays.toString(lg.ngrams(stringCorpus, 3)));
		ngLen = Integer.parseInt(args[1]);
		lg.generateWords(30, nGramMap);							//uncomment here to run word generation using ngrams
		lg.generateRandom(Integer.parseInt(args[2]), wordCorpus);
	}
}
