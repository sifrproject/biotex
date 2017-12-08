package fr.lirmm.validation;

import fr.lirmm.object.CandidatTerm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Validation {
	public static ArrayList<CandidatTerm> Validate_All_Terms(String language, ArrayList<CandidatTerm> list_candidat_terms, String source_datasetreference){
		HashMap<String, Integer> TermsReference = new HashMap<String, Integer>(); 
		ArrayList<CandidatTerm> list_candidat_terms_validated = new ArrayList<CandidatTerm>();
		        
		/////////////////////////////////////////////////////////
		///////////////////// C H A N G E S /////////////////////
		/////////////////////////////////////////////////////////
        //Local Machine : 
        String nameFile = source_datasetreference + File.separator + "Terms"+language+".txt";
        //String nameFile = "/Volumes/MacintoshDocuments/EclipseWorkSpace/DataSetReference/Terms"+language+".txt";
        
        //Server Machine :
        //String nameFile = "/home/juan/reference/Terms"+language+".txt";
        try
        {
            FileReader fr = new FileReader(nameFile);
            BufferedReader bf = new BufferedReader(fr);
            
            boolean eof = false;
            while (!eof) {
                String sLinea = bf.readLine();
                if (sLinea == null) {
                    eof = true;
                }
                else{
                    //if(Integer.parseInt(campos[1].trim())>=length_true_term) {
                        //System.out.println(campos[0].trim());
                	//String [] colonnes = sLinea.split(";");
                    TermsReference.put(sLinea.toLowerCase().trim(),1);
                    //}
                }
            }
            bf.close();
            fr.close();
        }catch(IOException e) {
            System.err.println("Error: " + e.toString());
        }
        catch(Exception ex) {
            System.err.println("Error: " + ex);
        }
        
        /*for(int i=0;i<list_candidat_terms.size();i++){
            if(TermsReference.containsKey(list_candidat_terms.get(i).getTerm())){
            	list_candidat_terms.get(i).setIsTrueTerm(1);
            }
        }*/
        
        String source = "";
        if(language.equalsIgnoreCase("english")){
        	source = "UMLS";
        }else{
        	if(language.equalsIgnoreCase("french")){
            	source = "MeSH";
            }
        }
        
        int count = 0;
        
		////////////////////////////////////////////////////////////////////////
		///////////////////// Numero de Terminos a validar /////////////////////
		////////////////////////////////////////////////////////////////////////
        /*while(count<50000 && count<list_candidat_terms.size()){
        	if(TermsReference.containsKey(list_candidat_terms.get(count).getTerm())){
            	list_candidat_terms.get(count).setIsTrueTerm(1);
            	list_candidat_terms.get(count).setSourceDictionary(source);
            }
        	list_candidat_terms_validated.add(list_candidat_terms.get(count));
        	count++;
        }*/
        
		////////////////////////////////////////////////////////////////////////
		///////////////////// Todos los términos a validar /////////////////////
		////////////////////////////////////////////////////////////////////////
		while(count<list_candidat_terms.size()){
			if(TermsReference.containsKey(list_candidat_terms.get(count).getTerm().trim())){
				list_candidat_terms.get(count).setIsTrueTerm(1);
				list_candidat_terms.get(count).setSourceDictionary(source);
			}
			list_candidat_terms_validated.add(list_candidat_terms.get(count));
			count++;
		}

        
        return list_candidat_terms_validated;
	}
}
