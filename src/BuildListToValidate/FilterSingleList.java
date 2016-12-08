/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildListToValidate;

import CommonResources.Split;
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
public class FilterSingleList {
	
	/**
     * Funcion principal que se encarga de crear las listas de unigram, bigram, 3-gram y 4+grams
     */
	public static void createList(ArrayList<CandidatTerm> list_candidat_terms_validated, String source_stop_word, String source_OUTPUT, String typeTerms) {
		int length_pattern;
        if(typeTerms.equalsIgnoreCase("multi")){
            length_pattern = 2;
        }else{
            length_pattern = 1;
        }
        
		load_variables(source_OUTPUT);
        //load_terms_ontologos();
        load_stop_word(source_stop_word);
        //load_stop_word(source_stop_word_en);
        compute_terme_gramme(list_candidat_terms_validated,length_pattern);
        
        create_n_gramme();
    }

    /**
     * @param args the command line arguments
     */
    static HashMap<String, Integer> map_stop_word = new HashMap<>();
    
    static ArrayList<String> list_term_application = new ArrayList<>();
    
    static ArrayList<String> n_gramme = new ArrayList<>();

    
    static String source_n_gramme = "";

    
    static String[] articles_prep = {"un","une","des","le","l","la","les","au","du","à","aux","de","en","dans","avec","sur","ou","et","chez","entre","jusque","hors","par","pour","vers","�"};
    
    
    private static void load_variables(String source_OUTPUT){
    	map_stop_word = new HashMap<>();
    	list_term_application = new ArrayList<>();
    	
    	n_gramme = new ArrayList<>();
        source_n_gramme = source_OUTPUT;
    }
    
    private static void compute_terme_gramme(ArrayList<CandidatTerm> list_candidat_terms_validated, int length_pattern){
        try{
        	for(int i=0;i<list_candidat_terms_validated.size();i++){
        		if(list_candidat_terms_validated.get(i).getTerm().trim().indexOf("�")==-1){
        			if(list_candidat_terms_validated.get(i).getIsTrueTerm()==0){
	            		int nb_mot = number_of_words(list_candidat_terms_validated.get(i).getTerm().trim()).size();
	            		if(length_pattern==1){
	            			if(nb_mot==1){
	                            if(!t1gramme_is_in_stop_words(list_candidat_terms_validated.get(i).getTerm().trim())){
	                            	n_gramme.add(list_candidat_terms_validated.get(i).getTerm().trim() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm());
	                            }else{
	                                //System.out.println(list_candidat_terms_validated.get(i).getTerm().trim());
	                            }
	                        }
	            		}
	            		
	                    if(nb_mot==2){
	                        if(!t2gramme_is_in_stop_words(list_candidat_terms_validated.get(i).getTerm().trim())){
	                        	n_gramme.add(list_candidat_terms_validated.get(i).getTerm().trim() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm());
	                        }else{
	                            //System.out.println(list_candidat_terms_validated.get(i).getTerm().trim());
	                        }
	                    }else{
	                        if(nb_mot==3){
	                            if(!t3and4gramme_is_in_stop_words(list_candidat_terms_validated.get(i).getTerm().trim())){
	                            	n_gramme.add(list_candidat_terms_validated.get(i).getTerm().trim()+";"+list_candidat_terms_validated.get(i).getIsTrueTerm());
	                            }else{
	                                //System.out.println(list_candidat_terms_validated.get(i).getTerm().trim());
	                            }
	                        }else{
	                            if(nb_mot>=4){
	                                if(!t3and4gramme_is_in_stop_words(list_candidat_terms_validated.get(i).getTerm().trim())){
	                                	n_gramme.add(list_candidat_terms_validated.get(i).getTerm().trim()+";"+list_candidat_terms_validated.get(i).getIsTrueTerm());
	                                }else{
	                                    //System.out.println(list_candidat_terms_validated.get(i).getTerm().trim());
	                                }
	                            }
	                        }
	                    }
	            	}else{
	            		int nb_mot = number_of_words(list_candidat_terms_validated.get(i).getTerm().trim()).size();
	            		if(length_pattern==1){
	            			if(nb_mot==1){
	            				n_gramme.add(list_candidat_terms_validated.get(i).getTerm().trim() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm());
	                        }
	            		}
	                    if(nb_mot==2){ 
	                    	n_gramme.add(list_candidat_terms_validated.get(i).getTerm().trim() +";"+list_candidat_terms_validated.get(i).getIsTrueTerm());
	                    }else{
	                        if(nb_mot==3){
	                        	n_gramme.add(list_candidat_terms_validated.get(i).getTerm().trim()+";"+list_candidat_terms_validated.get(i).getIsTrueTerm());
	                        }else{
	                            if(nb_mot>=4){
	                            	n_gramme.add(list_candidat_terms_validated.get(i).getTerm().trim()+";"+list_candidat_terms_validated.get(i).getIsTrueTerm());
	                            }
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
        for(int ind=0;ind<articles_prep.length;ind++){
            if(phrases[length-1].equalsIgnoreCase(articles_prep[ind])){
                return true;
            }
        }
        return false;
    }
    
    public static void create_n_gramme(){
        try{
                FileWriter fw = new FileWriter(source_n_gramme);
                BufferedWriter bw = new BufferedWriter(fw);

                for(int i=0;i<n_gramme.size();i++){
                    bw.write(n_gramme.get(i));
                    bw.newLine();
                }
                bw.close();
                fw.close();
            }catch(Exception ex1){
                System.err.println(ex1.toString());
            }
    }
    
}