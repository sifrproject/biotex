/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildListToValidate;

import Object.CandidatTerm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author juanlossio
 */
public class BuildFilterManyLists {
	
	/**
     * Funcion principal que se encarga de crear las listas de unigram, bigram, 3-gram, 4+grams y la mezcla de todos
     */
	public static void createList(ArrayList<CandidatTerm> list_candidat_terms_validated, String source_stop_word, String source_OUTPUT, String typeTerms, String language) {
		int length_pattern;
        if(typeTerms.equalsIgnoreCase("multi")){
            length_pattern = 2;
        }else{
            length_pattern = 1;
        }
        
        
        source_stop_word = source_stop_word + File.separator + "Stop-words-" + language + ".txt";
        
        
		load_variables(source_OUTPUT);
        //load_terms_ontologos();
        load_stop_word(source_stop_word);
        //load_stop_word(source_stop_word_en);
        compute_terme_gramme(list_candidat_terms_validated,length_pattern);
        
        if(length_pattern==1){
        	//create1gramme();
        	create_general_gram(source_t1gramme, t1gramme);
        }
        create_general_gram(source_t2gramme, t2gramme);
        create_general_gram(source_t3gramme, t3gramme);
        create_general_gram(source_t4gramme, t4gramme);
        create_general_gram(source_n_gramme, n_gramme);
        
        /*
        create2gramme();
        create3gramme();
        create4gramme();
        */
    }

    /**
     * @param args the command line arguments
     */
    static HashMap<String, Integer> map_stop_word = new HashMap<>();
    
    static ArrayList<String> list_term_application = new ArrayList<>();
    
    static ArrayList<String> t1gramme = new ArrayList<>();
    static ArrayList<String> t2gramme = new ArrayList<>();
    static ArrayList<String> t3gramme = new ArrayList<>();
    static ArrayList<String> t4gramme = new ArrayList<>();
    static ArrayList<String> n_gramme = new ArrayList<>();
    
    static String source_t1gramme = "";
    static String source_t2gramme = "";
    static String source_t3gramme = "";
    static String source_t4gramme = "";
    static String source_n_gramme = "";
    
    static String[] articles_prep_french = {"un","une","des","le","l","la","les","au","du","à","aux","de","en","dans","avec","sur","ou","et","chez","entre","jusque","hors","par","pour","vers"};
    static String[] articles_prep_spanish = {"un","une","des","le","l","la","les","au","du","à","aux","de","en","dans","avec","sur","ou","et","chez","entre","jusque","hors","par","pour","vers"};
    static String[] articles_prep_english = {"un","une","des","le","l","la","les","au","du","à","aux","de","en","dans","avec","sur","ou","et","chez","entre","jusque","hors","par","pour","vers"};
    
    static int nb_terms_application = 20000;
    
    
    private static void load_variables(String source_OUTPUT){
    	map_stop_word = new HashMap<>();
    	list_term_application = new ArrayList<>();
    	
    	t1gramme = new ArrayList<>();
        t2gramme = new ArrayList<>();
        t3gramme = new ArrayList<>();
        t4gramme = new ArrayList<>();
        n_gramme = new ArrayList<>();
        
        source_t1gramme = source_OUTPUT + File.separator + "t1gram.txt";
    	source_t2gramme = source_OUTPUT + File.separator + "t2gram.txt";
        source_t3gramme = source_OUTPUT + File.separator + "t3gram.txt";
        source_t4gramme = source_OUTPUT + File.separator + "t4gram.txt";
        source_n_gramme = source_OUTPUT + File.separator + "ALL_gram.txt";
        
        nb_terms_application = 20000;
    }
    
    private static void compute_terme_gramme(ArrayList<CandidatTerm> list_candidat_terms_validated, int length_pattern){
        try{
        	for(int i=0;i<list_candidat_terms_validated.size();i++){
            	if(list_candidat_terms_validated.get(i).getIsTrueTerm()==0){
            		int nb_mot = number_of_words(list_candidat_terms_validated.get(i).getTerm()).size();
            		if(length_pattern==1){
            			if(nb_mot==1){
                            if(!t1gramme_is_in_stop_words(list_candidat_terms_validated.get(i).getTerm())){
                                t1gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                                		+";"+list_candidat_terms_validated.get(i).getImportance());
                                n_gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                                		+";"+list_candidat_terms_validated.get(i).getImportance());
                            }else{
                                //System.out.println(list_candidat_terms_validated.get(i).getTerm());
                            }
                        }
            		}
            		
                    if(nb_mot==2){
                        if(!t2gramme_is_in_stop_words(list_candidat_terms_validated.get(i).getTerm())){
                            t2gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                            		+";"+list_candidat_terms_validated.get(i).getImportance());
                            n_gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                            		+";"+list_candidat_terms_validated.get(i).getImportance());
                        }else{
                            //System.out.println(list_candidat_terms_validated.get(i).getTerm());
                        }
                    }else{
                        if(nb_mot==3){
                            if(!t3and4gramme_is_in_stop_words(list_candidat_terms_validated.get(i).getTerm())){
                                t3gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                                		+";"+list_candidat_terms_validated.get(i).getImportance());
                                n_gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                                		+";"+list_candidat_terms_validated.get(i).getImportance());
                            }else{
                                //System.out.println(list_candidat_terms_validated.get(i).getTerm());
                            }
                        }else{
                            if(nb_mot>=4){
                                if(!t3and4gramme_is_in_stop_words(list_candidat_terms_validated.get(i).getTerm())){
                                    t4gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                                    		+";"+list_candidat_terms_validated.get(i).getImportance());
                                    n_gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                                    		+";"+list_candidat_terms_validated.get(i).getImportance());
                                }else{
                                    //System.out.println(list_candidat_terms_validated.get(i).getTerm());
                                }
                            }
                        }
                    }
            	}else{
            		int nb_mot = number_of_words(list_candidat_terms_validated.get(i).getTerm()).size();
            		if(length_pattern==1){
            			if(nb_mot==1){
                            t1gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                            		+";"+list_candidat_terms_validated.get(i).getImportance());
                            n_gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                            		+";"+list_candidat_terms_validated.get(i).getImportance());
                        }
            		}
                    if(nb_mot==2){ 
                        t2gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                        		+";"+list_candidat_terms_validated.get(i).getImportance());
                        n_gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                        		+";"+list_candidat_terms_validated.get(i).getImportance());
                    }else{
                        if(nb_mot==3){
                            t3gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                            		+";"+list_candidat_terms_validated.get(i).getImportance());
                            n_gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                            		+";"+list_candidat_terms_validated.get(i).getImportance());
                        }else{
                            if(nb_mot>=4){
                                t4gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                                		+";"+list_candidat_terms_validated.get(i).getImportance());
                                n_gramme.add(list_candidat_terms_validated.get(i).getTerm() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm()
                                		+";"+list_candidat_terms_validated.get(i).getImportance());
                            }
                        }
                    }
            	}
            }
        	
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in compute_terme_gramme: ");
        }
    }
    
    private static void load_stop_word(String source_stop_word){
        try{
            FileReader fr = new FileReader(source_stop_word);
            BufferedReader bf = new BufferedReader(fr);

            boolean eof = false;
            while (!eof) {
                String sLinea = bf.readLine();
                if (sLinea == null){
                    eof = true;
                }else{
                    map_stop_word.put(sLinea.trim().toLowerCase(),1);
                }
            }
            bf.close();
            fr.close();
        }catch(IOException e) {
            System.err.println("Error in stop words: " + e.toString());
        }
        catch(Exception ex) {
            System.err.println("Error in stop words: " + ex);
        }
    }
    
    /*private static void load_terms_ontologos(){
        try{
            FileReader fr = new FileReader(source_ontologos);
            BufferedReader bf = new BufferedReader(fr);

            boolean eof = false;
            while (!eof) {
                String sLinea = bf.readLine();
                if (sLinea == null) {
                    eof = true;
                }else{
                    String mots[] = Split.splitMots(sLinea,";");
                    int is_MeSH = Integer.parseInt(mots[1]);
                    //if(is_MeSH==0){
                        if(list_term_application.size()<nb_terms_application){
                            list_term_application.add(mots[0].trim());
                            //System.out.println("");
                        }else{
                            break;
                        }
                    //}else{
                        //System.out.println(sLinea);
                    //}                    
                }
            }
            bf.close();
            fr.close();
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in load_terms_ontologos: ");
        }
    }*/
    
    public static ArrayList number_of_words(String terme)
    {
        String aux;
        ArrayList<String> allPhrases = new ArrayList<>();
        String [] phrases = terme.split("[\\'\\ ]+");
        for(int i=0;i<phrases.length;i++)
        {
            aux  = phrases[i].trim();
            if(!aux.equalsIgnoreCase("") && aux!= null){
                allPhrases.add(aux);
            }
        }
        return allPhrases;
    }
    
    public static boolean t1gramme_is_in_stop_words(String terme)
    {
        
        if(map_stop_word.containsKey(terme.trim())){
            return true;
        }
        String num = terme.trim();
        try{
            int num_ = Integer.parseInt(num);
            return true;
        }catch(Exception ex){
            
        }
        
        return false;
    }
    
    
    public static boolean t2gramme_is_in_stop_words(String terme)
    {
        String [] phrases = terme.split(" ");
        for(int j=0;j<phrases.length;j++)
        {
            if(map_stop_word.containsKey(phrases[j].trim())){
                return true;
            }
            String num = phrases[j].trim();
            try{
                int num_ = Integer.parseInt(num);
                return true;
            }catch(Exception ex){
                
            }
        }
        return false;
    }
    
    public static boolean t3and4gramme_is_in_stop_words(String terme)
    {
        String [] phrases = terme.split(" ");
        int length = phrases.length;
        for(int ind=0;ind<articles_prep_french.length;ind++){
            if(phrases[length-1].equalsIgnoreCase(articles_prep_french[ind])){
                return true;
            }
        }
        return false;
    }
    
    /*
    public static void create1gramme(){
        try{
                FileWriter fw = new FileWriter(source_t1gramme);
                BufferedWriter bw = new BufferedWriter(fw);

                for(int i=0;i<t1gramme.size();i++){
                    bw.write(t1gramme.get(i));
                    bw.newLine();
                }
                bw.close();
                fw.close();
            }catch(Exception ex1){
                System.err.println(ex1.toString());
            }
    }
    
    public static void create2gramme(){
        try{
                FileWriter fw = new FileWriter(source_t2gramme);
                BufferedWriter bw = new BufferedWriter(fw);

                for(int i=0;i<t2gramme.size();i++){
                    bw.write(t2gramme.get(i));
                    bw.newLine();
                }
                bw.close();
                fw.close();
            }catch(Exception ex1){
                System.err.println(ex1.toString());
            }
    }
    
    public static void create3gramme(){
        try{
                FileWriter fw = new FileWriter(source_t3gramme);
                BufferedWriter bw = new BufferedWriter(fw);

                for(int i=0;i<t3gramme.size();i++){
                    bw.write(t3gramme.get(i));
                    bw.newLine();
                }
                bw.close();
                fw.close();
            }catch(Exception ex1){
                System.err.println(ex1.toString());
            }
    }
    
    public static void create4gramme(){
        try{
                FileWriter fw = new FileWriter(source_t4gramme);
                BufferedWriter bw = new BufferedWriter(fw);

                for(int i=0;i<t4gramme.size();i++){
                    bw.write(t4gramme.get(i));
                    bw.newLine();
                }
                bw.close();
                fw.close();
            }catch(Exception ex1){
                System.err.println(ex1.toString());
            }
    }
    */
    
    public static void create_general_gram(String file_, ArrayList<String> grams_){
        try{
                FileWriter fw = new FileWriter(file_);
                BufferedWriter bw = new BufferedWriter(fw);

                for(int i=0;i<grams_.size();i++){
                    bw.write(grams_.get(i));
                    bw.newLine();
                }
                bw.close();
                fw.close();
            }catch(Exception ex1){
                System.err.println(ex1.toString());
            }
    }
    
}