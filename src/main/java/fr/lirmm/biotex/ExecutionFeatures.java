/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.biotex;

import fr.lirmm.commonresources.ParameterVerification;
import fr.lirmm.commonresources.Split;
import fr.lirmm.object.CandidatTerm;

import java.io.*;
import java.util.ArrayList;

import static CommonResources.Cleaning.cleaningText;

/**
 *
 * @author Antonio Lossio
 */
public class ExecutionFeatures {
    
    static ArrayList<String> al_Patterns  = new ArrayList<String>();
    //static HashMap<String, Integer> TermsReference = new HashMap<String, Integer>(); 
    
    static ArrayList<CandidatTerm> list_candidat_terms = new ArrayList<CandidatTerm>();
    static ArrayList<CandidatTerm> list_candidat_terms_validated = new ArrayList<CandidatTerm>();
    static String currentDir;
    static int name_file = (int) (Math.random() * 1000000);
    
    public static ArrayList<CandidatTerm> main_execution(String language, int number_patron, String typeTerms, String measure, int typeSource, String source, String tool_POS){
        
    	al_Patterns  = new ArrayList<String>();
    	list_candidat_terms = new ArrayList<CandidatTerm>();
    	list_candidat_terms_validated = new ArrayList<CandidatTerm>();
    	ArrayList<String> al_POS_Phrases;
    	name_file = (int) (Math.random() * 1000000);
        
        /////////////////////////////////////////////////////////
        ///////////////////// C A M B I O S /////////////////////
        /////////////////////////////////////////////////////////
        //Local Machine : 
        currentDir = System.getProperty("user.dir");
        
        //Server Machine :
        //currentDir = "/home/juan/tagged";
    	
        //System.out.println("currentDir : " + currentDir);
        //System.out.println("list_candidat_terms : " + list_candidat_terms.size());
        //System.out.println("list_candidat_terms_validated : " + list_candidat_terms_validated.size());
        
        try{
        /*
         * Language : english, french 
         * number_patrons : number of first pattern to take into account 
         * typeTerms : all (single word + multi words terms), multi (multi words terms) 
         * measure = 19 possible measures 
         * tool_Tagger ça sera TreeTagger ou Stanford pour l'anglais
        */  
            
            //Esta funcion de carga de patrones la podemos lanzar con un HILO
            //al_Patterns = pattern.load_Patterns(language, typeTerms, tool_POS, number_patron, currentDir, 9);
            //System.out.println("patterns : " + al_Patterns.size());
            
            //TermsReference = validation.loadTrueTerms(language);
            
            if(typeSource == 1){//Single file
                if(ParameterVerification.verifySource_is_TextFile(source)==1){//If 1, then is a text file
                    FileReader fr = new FileReader(source);
                    BufferedReader br = new BufferedReader(fr);
                    
                    String fileOUT = currentDir + File.separator + "tagged"+name_file+".txt";
                    //cambiar fileOUT para utilizar un random para poner el nombre
                    
                    FileWriter fw = new FileWriter(fileOUT);
                    BufferedWriter bw = new BufferedWriter(fw);

                    ArrayList<String> al_Word_Phrases_Pre;
                    ArrayList<String> al_Word_Phrases;
                    boolean eof = false;
                    while (!eof) {
                        String sLinea = br.readLine();
                        if (sLinea == null){
                            eof = true;
                        }else{
                            sLinea = cleaningText(sLinea.trim(),language); //Clean the caracters of each ligne
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
                    
                    //al_POS_Phrases = POSTreeTagger.POSTreeTagger(fileOUT, language);
                    //After the pos to apply the measures
                    //System.out.println(al_POS_Phrases.size());
                    //list_candidat_terms = Controller.controller_execution(al_POS_Phrases, al_Patterns, language, measure, typeSource);
                    
                    //for(int i=0;i<al_POS_Phrases.size();i++){
                        
                    //}
                }
            }else{
                if(typeSource == 2){//multiple files, it means a single document separated with flag
                    //if(ParameterVerification.verifySource_is_Directory(source)==1){//If 1, then is a directory
                    if(ParameterVerification.verifySource_is_TextFile(source)==1){//If 1, then is a text file
                        if(ParameterVerification.verifySource_contains_FlagEndFile(source)==1){
                            FileReader fr = new FileReader(source);
                            BufferedReader br = new BufferedReader(fr);

                            //String fileOUT = currentDir + "/tempFiles/test1.txt";
                            String fileOUT = currentDir + File.separator + "tagged"+name_file+".txt";
                            //cambiar fileOUT para utilizar un random para poner el nombre

                            FileWriter fw = new FileWriter(fileOUT);
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
                                        sLinea = cleaningText(sLinea.trim(),language); //Clean the caracters of each ligne
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

                            //al_POS_Phrases = POSTreeTagger.POSTreeTagger(fileOUT, language);
                            //After the pos to apply the measures
                            //System.out.println("al_POS_Phrases : " + al_POS_Phrases.size());
                            //System.out.println("list_candidat_terms : " + list_candidat_terms.size());
                            //list_candidat_terms = Controller.controller_execution(al_POS_Phrases, al_Patterns, language, measure, typeSource);
                        }
                    }
                }
            }
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
        
        //Aqui realizamos la validation con las bases de datos que disponemos
        //System.out.println(list_candidat_terms.size());
        if(list_candidat_terms.size()>0){
        	//list_candidat_terms_validated = validation.Validate_All_Terms(language, list_candidat_terms);
        }
        return list_candidat_terms;
    }
}