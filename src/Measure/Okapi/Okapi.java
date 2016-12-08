/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Measure.Okapi;

import CommonResources.*;
import Object.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Antonio Lossio
 */
public class Okapi {

    /**
     * @param args the command line arguments
     */
    static List_PT_Okapi lpt;
     
    static ArrayList<String> alCol1 = new ArrayList<String>();
    static ArrayList<String> alCol2 = new ArrayList<String>();
    static ArrayList<String> alCol3 = new ArrayList<String>();
    
    static ArrayList<String> alPatterns = new ArrayList<String>();

    static String point_tagger;
    
    static String mots[] = new String[2];
    static String language;
    //static int min_frequency = 2;
    
    static double k1 = 2;
    static double b = 0.75;
    
    private static void show(){
        //lpt.showList();
        //lpt.showFeatures();
        lpt.showFeatures1();
    }
    private static void sort(){
        lpt.sortMap_by_Importance();
    }
    private static void compute_Okapi(String option, double b, double k1){
        lpt.compute_Okapi_BY_Doc(option,b,k1);
    }
    private static void new_document(){
        lpt.add_list_term_to_List();
    }
    
    private static boolean isInTruePatterns(String TAG){
        for(int i=0;i<alPatterns.size();i++){
            if(TAG.equalsIgnoreCase(alPatterns.get(i).toString())){
                return true;
            }
        }
        return false;
    }
    
    private static void computeTermsPhrase(int num_doc){
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
                    lpt.Add_Term(motCompose.trim().toLowerCase(), language, num_doc);
                }
            }
        }
    }
    
    
    private static void initialize(String lang, ArrayList<String> al_Patterns){
        language = lang;
        lpt = new List_PT_Okapi();
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
            int num_doc = 1;
            for(int i=0;i<al_tagged_doc.size();i++){
                mots = Split.splitMots(al_tagged_doc.get(i));
                if(mots[0].equalsIgnoreCase("##########END##########")){
                    num_doc++;
                    new_document();
                }else{
                    if(!mots[1].equalsIgnoreCase(point_tagger)){
                        alCol1.add(mots[0]);
                        alCol2.add(mots[1]);
                        alCol3.add(mots[2]);
                        //System.out.println(mots[1]);
                    }else{
                        computeTermsPhrase(num_doc);
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
 
    
    public static HashMap<String, PT_AKE> getMap_list_term() {
        return lpt.getMap_list_term();
    }
    
    public static List<Map.Entry<String, PT_AKE>> getEntries() {
        return lpt.getEntries();
    }
    
    public static ArrayList<CandidatTerm> get_CandidatTerms() {
        return lpt.get_CandidatTerms();
    }
    private static void delete_by_frequency(int min_frequency){
        lpt.delete_by_frequency(min_frequency);
    }
    public static ArrayList<CandidatTerm> computePossibleTerms(ArrayList<String> al_tagged_doc, ArrayList<String> al_Patterns, String lang, String option){
        try{
            initialize(lang, al_Patterns);
            computeTermsWithPatterns(al_tagged_doc);
            compute_Okapi(option,b,k1);
            //delete_by_frequency(min_frequency);
            //compute_C_value();
            sort();
            //show();
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in PossibleTerms ");
        }
        return get_CandidatTerms();
    }  
    
    public static HashMap<String, PT_AKE> computePossibleTermsC(ArrayList<String> al_tagged_doc, ArrayList<String> al_Patterns, String lang, String option){
        try{
            initialize(lang, al_Patterns);
            computeTermsWithPatterns(al_tagged_doc);
            compute_Okapi(option,b,k1);
            //delete_by_frequency(min_frequency);
            //compute_C_value();
            sort();
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in PossibleTerms ");
        }
        return getMap_list_term();
    }  
    
}