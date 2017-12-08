/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.object;


import fr.lirmm.commonresources.MathFonctions;

import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author juanlossio
 */
public class List_PT_C_value {
    private HashMap<String, PT_C_value> map_list_term;
    private List<Map.Entry<String, PT_C_value>> entries;
    
    private ArrayList<CandidatTerm> candidadTerms;
    public List_PT_C_value(){
        map_list_term = new HashMap<String, PT_C_value>();
    }
    
    public void Add_Term(String string_term, String language){
        String aux_string_term = "";
        //Replacement of apostrof in english and french
        if(language.equalsIgnoreCase("english")){
            aux_string_term = string_term.replaceAll(" '", "'");
        }else{
            if(language.equalsIgnoreCase("french")){
            	//System.out.println(string_term);
                aux_string_term = string_term.replaceAll("' ", "'");
                //System.out.println(aux_string_term);
            }else{
            	 if(language.equalsIgnoreCase("spanish")){
                     aux_string_term = string_term.trim();
                 }
            }
        }
        
        //compute if the term is in the list or not to add it
        if(getMap_list_term().containsKey(aux_string_term)){
        //System.out.println(aux_string_term);
            int freq_aux = getMap_list_term().get(aux_string_term).getFrequency();
            getMap_list_term().get(aux_string_term).setFrequency(freq_aux+1);
        }else{
            PT_C_value pt = new PT_C_value(aux_string_term,1,length_term(string_term),0,0,0);
            getMap_list_term().put(aux_string_term,pt);
        }
    }
    
    public void AfficherTreeSet()
    {
        System.out.println(" =====================================");
        System.out.println("|        LISTE DE POSSIBLE C-VALUE     |");
        System.out.println(" =====================================");                
        for( Iterator it = getMap_list_term().keySet().iterator(); it.hasNext();) {
            String clave = (String)it.next(); 
            PT_C_value valor = (PT_C_value)getMap_list_term().get(clave); 
            System.out.println(valor);
        }
        System.out.println("======================================");
    }
    
    public void delete_by_frequency(int min_frequency){
        try{
            //System.out.println(map_list_term.size());
            for( Iterator it = getMap_list_term().keySet().iterator(); it.hasNext();) {
                if(getMap_list_term().get((String)it.next()).getFrequency() < min_frequency){
                    it.remove();
                }
            }
            //System.out.println(map_list_term.size());
            //System.out.println("fini");
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
    }
    
    public List sortMap_by_Importance(){
        try{
            // Tri de la liste sur la valeur de l'entree
            Collections.sort(getEntries(), new Comparator<Map.Entry<String, PT_C_value>>() {
                @Override
                public int compare(Entry<String, PT_C_value> o1, Entry<String, PT_C_value> o2) {
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
            /*for (final Entry<String, PT_C_value> entry : getEntries()) {
                //System.out.println(entry.getKey() + " " + entry.getValue());
                System.out.println(entry.getValue().getTerm()+";"+entry.getValue().getImportance());
            }*/
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
        return getEntries();
    }
    
    public List sortMap_by_Num_Words(){
        //List<Map.Entry<String, PossibleTerm_C_value_>> entries;
        setEntries(new ArrayList<Entry<String, PT_C_value>>(getMap_list_term().entrySet()));
        try{
            // Tri de la liste sur la valeur de l'entree
            Collections.sort(getEntries(), new Comparator<Map.Entry<String, PT_C_value>>() {
                @Override
                public int compare(Entry<String, PT_C_value> o1, Entry<String, PT_C_value> o2) {
                    if(o2.getValue().getNum_words()>o1.getValue().getNum_words()){
                        return 1;
                    }else{
                        if(o2.getValue().getNum_words()<o1.getValue().getNum_words()){
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
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
        return getEntries();
    }
    
    private int[] isInOtherString(int i){
        int values_computed[] = new int [2];
        int sum = 0;
        int count = 0;
        for(int j=0;j<i;j++){
            if(getEntries().get(i).getValue().getNum_words() < getEntries().get(j).getValue().getNum_words()){
                if((getEntries().get(j).getKey().indexOf(" "+getEntries().get(i).getKey()+" ")>-1) 
                        ||  (getEntries().get(j).getKey().startsWith(getEntries().get(i).getKey()+" "))
                        || (getEntries().get(j).getKey().endsWith(" "+getEntries().get(i).getKey())))
                {  
                    sum += getEntries().get(j).getValue().getNew_frequency();
                    count++;
                }
            }else{
                break;
            }
        }
        values_computed[0] = sum;
        values_computed[1] = count;
        return values_computed;
    }
    
    private void C_value(int i){
        double c_value;
        if(getEntries().get(i).getValue().getNum_other_term_higer()>0){
            if(getEntries().get(i).getValue().getFrequency()-(getEntries().get(i).getValue().getSum_new_frequency()*1.0/getEntries().get(i).getValue().getNum_other_term_higer())<=0.0){
                c_value = MathFonctions.log2(((getEntries().get(i).getValue().getNum_words()+1)))*(getEntries().get(i).getValue().getFrequency()-
                        (getEntries().get(i).getValue().getSum_new_frequency()*1.0/(getEntries().get(i).getValue().getNum_other_term_higer())+0.5));
                
            }else{
                c_value = MathFonctions.log2(((getEntries().get(i).getValue().getNum_words()+1)))*(getEntries().get(i).getValue().getFrequency()-
                        (getEntries().get(i).getValue().getSum_new_frequency()*1.0/getEntries().get(i).getValue().getNum_other_term_higer()));
            }

        }else{
            c_value = MathFonctions.log2(((getEntries().get(i).getValue().getNum_words()+1)))*getEntries().get(i).getValue().getFrequency();
        }
        getEntries().get(i).getValue().setImportance(MathFonctions.Round(c_value,4));
    }
    
    public void compute_C_value(){
        sortMap_by_Num_Words();
        for(int i=0;i<getEntries().size();i++){
            int [] values = isInOtherString(i);
            int subtraction = getEntries().get(i).getValue().getNew_frequency()-values[0];
            if(subtraction >= 0){
                getEntries().get(i).getValue().setNew_frequency(subtraction);
            }else{
                //System.out.println(getEntries().get(i).getKey()+";"+subtraction);
            }
            getEntries().get(i).getValue().setSum_new_frequency(values[0]);
            getEntries().get(i).getValue().setNum_other_term_higer((values[1]));
            C_value(i);
        }
    }
    
    public void show_c_value(){
        try{
            //System.out.println(map_list_term.size());
            for( Iterator it = getMap_list_term().keySet().iterator(); it.hasNext();) {
                String clave = (String)it.next(); 
                PT_C_value valor = (PT_C_value)getMap_list_term().get(clave); 
                //System.out.println(valor.getTerm()+";"+valor.getImportance());
            }
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
    }
    
    //compute how many words are contained in a term
    private int length_term(String string_term){
        String [] sub_term = string_term.split(" ");
        return sub_term.length;
    }

    /**
     * @return the map_list_term
     */
    public HashMap<String, PT_C_value> getMap_list_term() {
        return map_list_term;
    }

    /**
     * @param map_list_term the map_list_term to set
     */
    public void setMap_list_term(HashMap<String, PT_C_value> map_list_term) {
        this.map_list_term = map_list_term;
    }

    /**
     * @return the entries
     */
    public List<Map.Entry<String, PT_C_value>> getEntries() {
        return entries;
    }

    /**
     * @param entries the entries to set
     */
    public void setEntries(List<Map.Entry<String, PT_C_value>> entries) {
        this.entries = entries;
    }
    
    public ArrayList<CandidatTerm> get_CandidatTerms(){
        candidadTerms = new ArrayList<CandidatTerm> ();
        for (final Entry<String, PT_C_value> entry : getEntries()) {
            CandidatTerm ct = new CandidatTerm(entry.getValue().getTerm(), entry.getValue().getImportance());
            candidadTerms.add(ct);
        }
        return candidadTerms;
    }
}
