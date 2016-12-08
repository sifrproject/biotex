/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BioTex;
import CommonResources.*;
import Object.CandidatTerm;
import POS.*;
import Pattern.*;
import Validation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Juan Antonio LOSSIO-VENTURA
 */
public class Execution {
    
    static ArrayList<String> al_Patterns  = new ArrayList<String>();
    static HashMap<String, Integer> hm_Patterns = new HashMap<>();
    //static HashMap<String, Integer> TermsReference = new HashMap<String, Integer>(); 
    
    static ArrayList<CandidatTerm> list_candidat_terms = new ArrayList<CandidatTerm>();
    static ArrayList<CandidatTerm> list_candidat_terms_validated = new ArrayList<CandidatTerm>();
    static String currentDir;
    static int name_file = (int) (Math.random() * 1000000);
    static String file_to_tag;
    
    public static ArrayList<CandidatTerm> main_execution(
    		String language, // english french or spanish
    		int number_patterns, // number of patterns to load
    		String typeTerms, // all (single+multi word terms) or multi (multi word terms)
    		String measure, // 15 possibles 
    		int typeSource, // 1= single document    or   2=set of documents
    		int frequency_min_of_terms,
    		String source, // Document to analyze
    		String tool_POS, //TreeTagger by default
    		String source_patterns,
    		String source_datasetreference,
    		String source_tagger,
    		String source_OUTPUT
    		){
        
    	al_Patterns  = new ArrayList<String>();
    	hm_Patterns = new HashMap<>();
    	
    	list_candidat_terms = new ArrayList<CandidatTerm>();
    	list_candidat_terms_validated = new ArrayList<CandidatTerm>();
    	
    	ArrayList<String> al_POS_Phrases;
    	name_file = (int) (Math.random() * 1000000);
        
        /////////////////////////////////////////////////////////
        ///////////////////// C A M B I O S /////////////////////
        /////////////////////////////////////////////////////////
        //Local Machine : 
        currentDir = System.getProperty("user.dir");
        //System.out.println("currentDir :   " + currentDir);
        
        //Server Machine :
        //currentDir = "/home/juan/tagged";
    	
        //System.out.println("currentDir : " + currentDir);
        //System.out.println("list_candidat_terms : " + list_candidat_terms.size());
        //System.out.println("list_candidat_terms_validated : " + list_candidat_terms_validated.size());
        
        try{
        /*
         * Language : english, french, spanish
         * number_patrons : number of first pattern to take into account 
         * typeTerms : all (single word + multi words terms), multi (multi words terms) 
         * measure = 15 possible measures 
         * tool_Tagger il est TreeTagger
        */  
            
            //Esta funcion de carga de patrones la podemos lanzar con un HILO
            al_Patterns = Pattern.load_Patterns(language, typeTerms, tool_POS, number_patterns, currentDir, 9, source_patterns);
            hm_Patterns = PatternProb.load_patterns(language, typeTerms, tool_POS, number_patterns, currentDir, 9, source_patterns);
            
            //System.out.println("patterns : " + al_Patterns.size());
            
            //TermsReference = Validation.loadTrueTerms(language);
            
            if(typeSource == 1){//Single file
                if(ParameterVerification.verifySource_is_TextFile(source)==1){//If 1, then is a text file
                    FileReader fr = new FileReader(source);
                    BufferedReader br = new BufferedReader(fr);
                    
                    file_to_tag = currentDir + File.separator + "to_tag_"+name_file+".txt";
                    //System.out.println("file_to_tag :   " + file_to_tag);
                    //cambiar fileOUT para utilizar un random para poner el nombre, es el fichero que esta normalizado, justo para pasar al PoS tagger
                    
                    FileWriter fw = new FileWriter(file_to_tag);
                    BufferedWriter bw = new BufferedWriter(fw);

                    ArrayList<String> al_Word_Phrases_Pre;
                    ArrayList<String> al_Word_Phrases;
                    boolean eof = false;
                    while (!eof) {
                        String sLinea = br.readLine();
                        if (sLinea == null){
                            eof = true;
                        }else{
                            sLinea = Cleaning.cleaningText(sLinea.trim(),language); //Clean the caracters of each ligne
                            //al_Word_Phrases_Pre = new ArrayList<>();
                            al_Word_Phrases_Pre = Split.splitInPhrasesPreTraitement(sLinea.trim());
                            for(int i=0;i<al_Word_Phrases_Pre.size();i++){
                                //al_Word_Phrases = new ArrayList<>();
                                al_Word_Phrases = Split.splitInPhrases(al_Word_Phrases_Pre.get(i).trim());
                                for(int j=0;j<al_Word_Phrases.size();j++){
                                    bw.write(al_Word_Phrases.get(j) + " .");
                                    bw.newLine();
                                    //System.out.println(al_Word_Phrases.get(j));
                                }
                                al_Word_Phrases.clear();
                            }
                            al_Word_Phrases_Pre.clear();
                        }
                    }       
                    br.close();
                    fr.close();
                    bw.close();
                    fw.close();
                    
                    al_POS_Phrases = POS_TreeTagger.POS_TreeTagger(file_to_tag, language, source_tagger);
                    //After the POS to apply the measures
                    //System.out.println("al_POS_Phrases : " + al_POS_Phrases.size());
                    list_candidat_terms = Controller.controller_execution(al_POS_Phrases, al_Patterns, hm_Patterns, language, measure, typeSource, frequency_min_of_terms);
                    
                    
                }
            }else{
                if(typeSource == 2){//a set of files, it means a single document separated with flag
                    //if(ParameterVerification.verifySource_is_Directory(source)==1){//If 1, then is a directory
                    if(ParameterVerification.verifySource_is_TextFile(source)==1){//If 1, then is a text file
                        if(ParameterVerification.verifySource_contains_FlagEndFile(source)==1){
                            FileReader fr = new FileReader(source);
                            BufferedReader br = new BufferedReader(fr);

                            //String fileOUT = currentDir + "/tempFiles/test1.txt";
                            file_to_tag = currentDir + File.separator + "to_tag_"+name_file+".txt";
                            //cambiar fileOUT para utilizar un random para poner el nombre

                            FileWriter fw = new FileWriter(file_to_tag);
                            BufferedWriter bw = new BufferedWriter(fw);

                            ArrayList<String> al_Word_Phrases_Pre;
                            ArrayList<String> al_Word_Phrases;
                            boolean eof = false;
                            while (!eof) {
                                String sLinea = br.readLine();
                                if (sLinea == null){
                                    eof = true;
                                }else{
                                    if(sLinea.trim().equalsIgnoreCase("##########END##########")){
                                        bw.write(sLinea.trim());
                                        bw.newLine();
                                    }else{
                                        sLinea = Cleaning.cleaningText(sLinea.trim(),language); //Clean the caracters of each ligne
                                    //al_Word_Phrases_Pre = new ArrayList<>();
                                        al_Word_Phrases_Pre = Split.splitInPhrasesPreTraitement(sLinea.trim());
                                        for(int i=0;i<al_Word_Phrases_Pre.size();i++){
                                            //al_Word_Phrases = new ArrayList<>();
                                            al_Word_Phrases = Split.splitInPhrases(al_Word_Phrases_Pre.get(i).trim());
                                            for(int j=0;j<al_Word_Phrases.size();j++){
                                                bw.write(al_Word_Phrases.get(j) + " .");
                                                bw.newLine();
                                                //System.out.println(al_Word_Phrases.get(j));
                                            }
                                            al_Word_Phrases.clear();
                                        }
                                        al_Word_Phrases_Pre.clear();
                                    }
                                }
                            }       
                            br.close();
                            fr.close();
                            bw.close();
                            fw.close();

                            al_POS_Phrases = POS_TreeTagger.POS_TreeTagger(file_to_tag, language, source_tagger);
                            //After the POS to apply the measures
                            //System.out.println("al_POS_Phrases : " + al_POS_Phrases.size());
                            //System.out.println("list_candidat_terms : " + list_candidat_terms.size());
                            list_candidat_terms = Controller.controller_execution(al_POS_Phrases, al_Patterns, hm_Patterns, language, measure, typeSource, frequency_min_of_terms);
                        }
                    }
                }
            }
            File file = new File(file_to_tag);
            file.delete();
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
        
        //Aqui realizamos la validation con las bases de datos que disponemos
        //System.out.println(list_candidat_terms.size());
        if(list_candidat_terms.size()>0){
        	list_candidat_terms_validated = Validation.Validate_All_Terms(language, list_candidat_terms, source_datasetreference);
        }
        //list_candidat_terms_validated = list_candidat_terms; 
        
        return list_candidat_terms_validated;
    }
}