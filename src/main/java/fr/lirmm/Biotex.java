 /*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package fr.lirmm;

 import fr.lirmm.biotex.Execution;
 import fr.lirmm.listtovalidate.BuildFilterManyLists;
 import fr.lirmm.object.CandidatTerm;

 import java.io.FileInputStream;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Properties;

 /**
  * @author juanlossio
  */
 public class Biotex {

     /**
      * @param args the command line arguments
      */
     static ArrayList<CandidatTerm> list_candidat_terms_validated = new ArrayList<>();

     public static void main(final String[] args) throws IOException {

         if (args.length < 1) {
             System.err.println("Syntax: Biotex /path/to/biotex.properties");
             System.exit(1);
         }
         final Properties configuration = new Properties();
         configuration.load(new FileInputStream(args[0]));

         /*
          * Variables to find: the pattern List, DataSetReference for validation, and file where the Tagger Tool is installed
          */
         String source_patterns = configuration.getProperty("biotex.source_patterns");
         String source_dataset_reference = configuration.getProperty("biotex.source_dataset_reference");
         String source_stop_words = configuration.getProperty("biotex.source_stop_words");
         String source_tagger = configuration.getProperty("biotex.source_treetagger");



         /*
          * Variable that saves the extracted terms
          */
         String source_OUTPUT = configuration.getProperty("biotex.output"); //Mettre le dossier où vous voulez que les fichiers se sauvegardent


         /*
          * File to be analized for the term extraction
          */
         String file_to_be_analyzed = configuration.getProperty("biotex.input_file");

         /*
          * Language : english, french, spanish
          * number_patrons : number of first pattern to take into account
          * typeTerms : all (single word + multi words terms),
          * 			   multi (multi words terms)
          * measure = 15 possible measures
          * tool_Tagger: TreeTagger by default
          */


         String type_of_terms = configuration.getProperty("biotex.type_of_terms"); // all    multi
         String language = configuration.getProperty("biotex.language"); // english french spanish
         int minTermFrequency = Integer.valueOf(configuration.getProperty("biotex.min_term_frequency")); // frequency minimal to extract the terms

         list_candidat_terms_validated = Execution.main_execution(
                 language, //english french spanish
                 Integer.valueOf(configuration.getProperty("biotex.number_of_patterns")), // nombre de patrons
                 type_of_terms,
                 configuration.getProperty("biotex.measure"), // For one document       :   LValue     CValue
                 // For a set of documents :   LIDFValue  F-OCapi_A   F-OCapi_M   F-OCapi_S   F-TFIDF-C_A     F-TFIDF-C_M     F-TFIDF-C_S
                 //                            TFIDF_A     TFIDF_M     TFIDF_S     Okapi_A     Okapi_M     Okapi_S
                 Integer.valueOf(configuration.getProperty("biotex.source_type")),/* 1 = single file (only for LValue  or CValue)
                     2 = set of files (for LIDF-value or any measure)
                */
                 minTermFrequency,
                 file_to_be_analyzed,
                 "TreeTagger",
                 source_patterns,
                 source_dataset_reference,
                 source_tagger,
                 source_OUTPUT
         );

         BuildFilterManyLists.createList(list_candidat_terms_validated, source_stop_words, source_OUTPUT, type_of_terms, language);
         System.out.println("Fin de l'exécution");
     }

 }
