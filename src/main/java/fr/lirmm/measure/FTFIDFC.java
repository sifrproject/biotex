/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.measure;

import fr.lirmm.commonresources.MathFonctions;
import fr.lirmm.object.CandidatTerm;
import fr.lirmm.object.PT_AKE;
import fr.lirmm.object.PT_C_value;

import java.util.*;

/**
 *
 * @author juanlossio
 */
public class FTFIDFC {
    static HashMap<String, PT_C_value> hasM_C;
    static HashMap<String, PT_AKE> hasM_TFIDF;
    
    static HashMap<String, CandidatTerm> F_TFIDF_C = new HashMap<String, CandidatTerm>();
    static List<Map.Entry<String, CandidatTerm>> entries;
    
    private static ArrayList<CandidatTerm> candidadTerms;
    
    public static void sortMap_by_Importance(){
    	entries = new ArrayList<Map.Entry<String, CandidatTerm>>(F_TFIDF_C.entrySet());
        try{
            // Tri de la liste sur la valeur de l'entrée
            Collections.sort(entries, new Comparator<Map.Entry<String, CandidatTerm>>() {
                @Override
                public int compare(Map.Entry<String, CandidatTerm> o1, Map.Entry<String, CandidatTerm> o2) {
                    if(o2.getValue().getImportance()>o1.getValue().getImportance()){
                        return 1;
                    }else{
                        if(o2.getValue().getImportance()<o1.getValue().getImportance()){
                            return -1;
                        }else{
                            if(o2.getValue().getTerm().compareTo(o1.getValue().getTerm())<0){
                                return 1;
                            }else{
                                if(o2.getValue().getTerm().compareTo(o1.getValue().getTerm())>0)
                                {
                                    return -1;
                                }else{
                                    return 0;                                        
                                }
                            }
                        }
                    }
                }
            });      
            /*for (final Map.Entry<String, CandidatTerm> entry : entries) {
                //System.out.println(entry.getKey() + " " + entry.getValue());
                System.out.println(entry.getValue().getTerm()+";"+entry.getValue().getImportance());
            }*/
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
    }
    
    public static ArrayList<CandidatTerm> get_CandidatTerms(){
        candidadTerms = new ArrayList<CandidatTerm> ();
        for (final Map.Entry<String, CandidatTerm> entry : entries) {
            CandidatTerm ct = new CandidatTerm(entry.getValue().getTerm(), entry.getValue().getImportance());
            candidadTerms.add(ct);
        }
        return candidadTerms;
    }
    
    public static ArrayList<CandidatTerm> computePossibleTerms(ArrayList<String> al_tagged_doc, ArrayList<String> al_Patterns, String lang, String option, int frequency_min_of_terms){
        try{
        	hasM_C = new HashMap<String, PT_C_value>();
        	hasM_TFIDF = new HashMap<String, PT_AKE>();
        	F_TFIDF_C = new HashMap<String, CandidatTerm>();
        	entries = new ArrayList<Map.Entry<String, CandidatTerm>>(F_TFIDF_C.entrySet());
        	
            hasM_C = CValue.computePossibleTermsG(al_tagged_doc, al_Patterns, lang,frequency_min_of_terms);
            hasM_TFIDF = TFIDF.computePossibleTermsC(al_tagged_doc, al_Patterns, lang, option);
            

            for( Iterator it = hasM_C.keySet().iterator(); it.hasNext();) {
                String key = (String)it.next();
                if(hasM_TFIDF.containsKey(key)){
                    double f_tfidf_c;
                    f_tfidf_c= 2*(hasM_C.get(key).getImportance()*hasM_TFIDF.get(key).getImportance())/(hasM_C.get(key).getImportance()+hasM_TFIDF.get(key).getImportance());
                    CandidatTerm ct = new CandidatTerm(key, MathFonctions.Round(f_tfidf_c,4));
                    F_TFIDF_C.put(key, ct);
                } 
            }
            sortMap_by_Importance();
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in PossibleTerms ");
        }
        return get_CandidatTerms();
    }  
}
