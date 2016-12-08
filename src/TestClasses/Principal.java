/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClasses;
import BioTex.Execution;
import Object.CandidatTerm;
import BuildListToValidate.BuildFilterManyLists;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;


/**
 *
 * @author juanlossio
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    static ArrayList<CandidatTerm> list_candidat_terms_validated = new ArrayList<CandidatTerm>();
    
    public static void main(String[] args) {        
         
    	/*
    	 * Variables to find: the Pattern List, DataSetReference for Validation, and file where the Tagger Tool is installed
    	 */
    	String source_patterns = "/Volumes/MacintoshDocuments/EclipseWorkSpace/patterns";
    	String source_dataset_reference = "/Volumes/MacintoshDocuments/EclipseWorkSpace/dataSetReference";
    	String source_stop_words = "/Volumes/MacintoshDocuments/EclipseWorkSpace/stopWords";
    	String source_tagger = "/Users/juanlossio/Documents/TreeTagger";
    	
    	
    	
    	/*
    	 * Variable that saves the extracted terms
    	 */
    	String source_OUTPUT = "/Volumes/MacintoshDocuments"; //Mettre le dossier où vous voulez que les fichiers se sauvegardent
    	
    	
    	/*
    	 * File to be analized for the term extraction
    	 */
    	String file_to_be_analyzed = "/Users/juanlossio/Desktop/fff.txt";
    	
    	/*
         * Language : english, french, spanish 
         * number_patrons : number of first pattern to take into account 
         * typeTerms : all (single word + multi words terms), 
         * 			   multi (multi words terms) 
         * measure = 15 possible measures 
         * tool_Tagger: TreeTagger by default
        */ 

    	
    	String type_of_terms = "all"; // all    multi
        String language = "english"; // english french spanish
        int frequency_min_of_terms = 1; // frequency minimal to extract the terms
        
    	list_candidat_terms_validated = Execution.main_execution(
                language, //english french spanish
                200, // nombre de patrons
                type_of_terms, 
                "LIDF_value", // One document     :   L_value C_value 
                		   // Set of documents :   LIDF_value TFIDF_A TFIDF_M TFIDF_S Okapi_A Okapi_M Okapi_S F-OCapi_A F-OCapi_M F-OCapi_S F-TFIDF-C_A F-TFIDF-C_M F-TFIDF-C_S
                2/*single file 1 or a set of files 2*/,
                frequency_min_of_terms,
                file_to_be_analyzed,
                "TreeTagger",
                source_patterns,
                source_dataset_reference,
                source_tagger,
                source_OUTPUT
        );
    	
    	BuildFilterManyLists.createList(list_candidat_terms_validated,source_stop_words,source_OUTPUT,type_of_terms,language);
        System.out.println("Fin de l'exécution");
    }

}
