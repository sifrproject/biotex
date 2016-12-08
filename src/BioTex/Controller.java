/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BioTex;

import Measure.C_Value.*;
import Measure.F_OCapi.F_OCapi;
import Measure.F_TFIDF_C.F_TFIDF_C;
import Measure.LIDF_Value.LIDF_value;
import Measure.L_Value.L_value;
import Measure.Okapi.Okapi;
import Measure.TFIDF.TFIDF;
import Object.CandidatTerm;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author Juan Antonio LOSSIO VENTURA
 */
public class Controller {
    
    public static ArrayList<CandidatTerm> controller_execution(
    		ArrayList<String> al_POS_Phrases, 
    		ArrayList<String> al_Patterns,
    		HashMap<String,Integer> hm_Patterns,
            String language, 
            String measure, 
            int typeSource,
            int frequency_min_of_terms
            ){
    	
        ArrayList<CandidatTerm> list_candidat_terms = new ArrayList<CandidatTerm>();
        
        int measure1 = 0;
        if(measure.equalsIgnoreCase("C_value"))
        	measure1 = 1;
        if(measure.equalsIgnoreCase("TFIDF_M"))
        	measure1 = 2;
        if(measure.equalsIgnoreCase("TFIDF_S"))
        	measure1 = 3;
        if(measure.equalsIgnoreCase("TFIDF_A"))
        	measure1 = 4;
        if(measure.equalsIgnoreCase("Okapi_M"))
        	measure1 = 5;
        if(measure.equalsIgnoreCase("Okapi_S"))
        	measure1 = 6;
        if(measure.equalsIgnoreCase("Okapi_A"))
        	measure1 = 7;
        if(measure.equalsIgnoreCase("F-OCapi_M"))
        	measure1 = 8;
        if(measure.equalsIgnoreCase("F-OCapi_S"))
        	measure1 = 9;
        if(measure.equalsIgnoreCase("F-OCapi_A"))
        	measure1 = 10;
        if(measure.equalsIgnoreCase("F-TFIDF-C_M"))
        	measure1 = 11;
        if(measure.equalsIgnoreCase("F-TFIDF-C_S"))
        	measure1 = 12;
        if(measure.equalsIgnoreCase("F-TFIDF-C_A"))
        	measure1 = 13;
        if(measure.equalsIgnoreCase("LIDF_value"))
        	measure1 = 14;
        if(measure.equalsIgnoreCase("L_value"))
        	measure1 = 15;
        
        switch(measure1){
            case 1 : 
                list_candidat_terms = C_value.computePossibleTerms(al_POS_Phrases, al_Patterns, language, frequency_min_of_terms);
                break;
            case 2 : 
                list_candidat_terms = TFIDF.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"MAX");
                break;
            case 3 : 
                list_candidat_terms = TFIDF.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"SUM");
                break;
            case 4 : 
                list_candidat_terms = TFIDF.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"AVG");
                break;
            case 5 : 
                list_candidat_terms = Okapi.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"MAX");
                break;
            case 6 : 
                list_candidat_terms = Okapi.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"SUM");
                break;
            case 7 : 
                list_candidat_terms = Okapi.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"AVG");
                break;
            case 8 : 
                list_candidat_terms = F_OCapi.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"MAX", frequency_min_of_terms);
                break;
            case 9 : 
                list_candidat_terms = F_OCapi.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"SUM", frequency_min_of_terms);
                break;
            case 10 : 
                list_candidat_terms = F_OCapi.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"AVG", frequency_min_of_terms);
                break;
            case 11 : 
                list_candidat_terms = F_TFIDF_C.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"MAX", frequency_min_of_terms);
                break;
            case 12 : 
                list_candidat_terms = F_TFIDF_C.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"SUM", frequency_min_of_terms);
                break;
            case 13 : 
                list_candidat_terms = F_TFIDF_C.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"AVG", frequency_min_of_terms);
                break;
            case 14 : 
	            list_candidat_terms = LIDF_value.computePossibleTerms(al_POS_Phrases, al_Patterns, language, hm_Patterns, frequency_min_of_terms);
	            break;
            case 15 : 
            	list_candidat_terms = L_value.computePossibleTerms(al_POS_Phrases, al_Patterns, language, hm_Patterns, frequency_min_of_terms);
	            break;
            
        }
        return list_candidat_terms;
    }
}
