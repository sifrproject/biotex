/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.biotex;

import fr.lirmm.measure.*;
import fr.lirmm.object.CandidatTerm;

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
    	
        ArrayList<CandidatTerm> list_candidat_terms = new ArrayList<>();
        
        int measure1 = 0;
        if(measure.equalsIgnoreCase("CValue"))
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
        if(measure.equalsIgnoreCase("LIDFValue"))
        	measure1 = 14;
        if(measure.equalsIgnoreCase("LValue"))
        	measure1 = 15;
        
        switch(measure1){
            case 1 : 
                list_candidat_terms = CValue.computePossibleTerms(al_POS_Phrases, al_Patterns, language, frequency_min_of_terms);
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
                list_candidat_terms = FOkapi.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"MAX", frequency_min_of_terms);
                break;
            case 9 : 
                list_candidat_terms = FOkapi.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"SUM", frequency_min_of_terms);
                break;
            case 10 : 
                list_candidat_terms = FOkapi.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"AVG", frequency_min_of_terms);
                break;
            case 11 : 
                list_candidat_terms = FTFIDFC.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"MAX", frequency_min_of_terms);
                break;
            case 12 : 
                list_candidat_terms = FTFIDFC.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"SUM", frequency_min_of_terms);
                break;
            case 13 : 
                list_candidat_terms = FTFIDFC.computePossibleTerms(al_POS_Phrases, al_Patterns, language,"AVG", frequency_min_of_terms);
                break;
            case 14 : 
	            list_candidat_terms = LIDFValue.computePossibleTerms(al_POS_Phrases, al_Patterns, language, hm_Patterns, frequency_min_of_terms);
	            break;
            case 15 : 
            	list_candidat_terms = LValue.computePossibleTerms(al_POS_Phrases, al_Patterns, language, hm_Patterns, frequency_min_of_terms);
	            break;
            
        }
        return list_candidat_terms;
    }
}
