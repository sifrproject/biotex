/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Measure.C_Value;

import CommonResources.*;
import Object.CandidatTerm;
import Object.List_PT_C_value;
import Object.PT_C_value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Antonio Lossio
 */
public class C_value {

    /**
     * @param args the command line arguments
     */
    static List_PT_C_value lpt;
     
    static ArrayList<String> alCol1 = new ArrayList<String>();
    static ArrayList<String> alCol2 = new ArrayList<String>();
    static ArrayList<String> alCol3 = new ArrayList<String>();
    
    static ArrayList<String> alPatterns = new ArrayList<String>();

    static String mots[] = new String[2];
    static String language;
    static int min_frequency;
    
    static String point_tagger;
    
    private static void delete_by_frequency(int min_frequency){
        lpt.delete_by_frequency(min_frequency);
    }
    private static void show(){
        lpt.AfficherTreeSet();
    }
    private static void sort(){
        lpt.sortMap_by_Importance();
    }
    private static void compute_C_value(){
        lpt.compute_C_value();
    }
    
    private static boolean isInTruePatterns(String TAG){
        for(int i=0;i<alPatterns.size();i++){
            if(TAG.equalsIgnoreCase(alPatterns.get(i).toString())){
                return true;
            }
        }
        return false;
    }
    
    private static void computeTermsPhrase(){
        String cadTAG;
        for(int i=0; i<alCol2.size();i++){
            cadTAG = alCol2.get(i);
            for(int j=i;j<alCol2.size();j++){
                if(i!=j){
                    cadTAG = cadTAG + " " + alCol2.get(j);
                }
                if(isInTruePatterns(cadTAG)){
                    String motCompose = "";
                    for(int r=i;r<=j;r++){
                        motCompose = motCompose + " " + alCol1.get(r);
                    }
                    lpt.Add_Term(motCompose.trim().toLowerCase(), language);
                }
            }
        }
    } 
    
    private static void initialize(String lang, ArrayList<String> al_Patterns, int frequency_min_of_terms){
        language = lang;
        min_frequency = frequency_min_of_terms;
        lpt = new List_PT_C_value();
        alPatterns = al_Patterns;
        
        if(lang.equalsIgnoreCase("spanish")){
        	point_tagger = "FS";
        }else{
        	point_tagger = "SENT";
        }
        alCol1 = new ArrayList<String>();
        alCol2 = new ArrayList<String>();
        alCol3 = new ArrayList<String>();
    }
    
    private static void computeTermsWithPatterns(ArrayList<String> al_tagged_doc){
        try{
            for(int i=0;i<al_tagged_doc.size();i++){
                mots = Split.splitMots(al_tagged_doc.get(i));
                if(!mots[1].equalsIgnoreCase(point_tagger)){
                    alCol1.add(mots[0]);
                    alCol2.add(mots[1]);
                    alCol3.add(mots[2]);
                    //System.out.println(mots[1]);
                }else{
                    computeTermsPhrase();
                    alCol1 = new ArrayList<String>();
                    alCol2 = new ArrayList<String>();
                    alCol3 = new ArrayList<String>();
                }
           }
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in computeTermsWithPatterns ");
        }
    }
    
    private static void computeTermsWithPatternsC(ArrayList<String> al_tagged_doc){//Combined
        try{
            for(int i=0;i<al_tagged_doc.size();i++){
                mots = Split.splitMots(al_tagged_doc.get(i));
                if(!mots[0].equalsIgnoreCase("##########END##########")){//####################
                    if(!mots[1].equalsIgnoreCase(point_tagger)){
                        alCol1.add(mots[0]);
                        alCol2.add(mots[1]);
                        alCol3.add(mots[2]);
                        //System.out.println(mots[1]);
                    }else{
                        computeTermsPhrase();
                        alCol1 = new ArrayList<String>();
                        alCol2 = new ArrayList<String>();
                        alCol3 = new ArrayList<String>();
                    }
                }
           }
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in computeTermsWithPatterns ");
        }
    }
    
    public static HashMap<String, PT_C_value> getMap_list_term() {
        return lpt.getMap_list_term();
    }
    
    public static List<Map.Entry<String, PT_C_value>> getEntries() {
        return lpt.getEntries();
    }
    
    public static ArrayList<CandidatTerm> get_CandidatTerms() {
        return lpt.get_CandidatTerms();
    }
    
    public static ArrayList<CandidatTerm> computePossibleTerms(ArrayList<String> al_tagged_doc, ArrayList<String> al_Patterns, String lang, int frequency_min_of_terms){
        try{
            initialize(lang, al_Patterns, frequency_min_of_terms);
            //System.out.println("C_VALUE    :   " + point_tagger);
            computeTermsWithPatterns(al_tagged_doc);
            delete_by_frequency(min_frequency);
            compute_C_value();
            sort();
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in PossibleTerms ");
        }
        return get_CandidatTerms();
    }  
    
    public static HashMap<String, PT_C_value> computePossibleTermsG(ArrayList<String> al_tagged_doc, ArrayList<String> al_Patterns, String lang, int frequency_min_of_terms){
        try{
            initialize(lang, al_Patterns, frequency_min_of_terms);
            computeTermsWithPatternsC(al_tagged_doc);
            delete_by_frequency(min_frequency);
            compute_C_value();
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in PossibleTerms ");
        }
        return getMap_list_term();
    }  
}