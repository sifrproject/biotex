package Measure.LIDF_Value;


import CommonResources.MathFonctions;
import CommonResources.Split;
import Object.CandidatTerm;
import Object.PT_LIDF_value;
import Object.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 *
 * @author juanlossio
 */
public class LIDF_value {

    /**
     * @param args the command line arguments
     */
	    
    static ArrayList<Word> list_word;
    static HashMap<String, PT_LIDF_value> map_list_term;
    static List<Map.Entry<String, PT_LIDF_value>> entries;
    static HashMap<String, Integer> map_list_pattern; // 1



    //Datos robados de ECLIPSE
    static String point_tagger;
    
    static ArrayList<CandidatTerm> list_candidat_terms;
    
    static String mots[];
    static int count_doc;   //Para guardar el valor del numero de documentos
    static int min_frequency; //La frecuencia minima con la que se extraeran los términos candidatos
    static int sum_total_frequency; // 
    static int num_patterns; // numero maximo de patrones permitidos
    
    private static void initialize(String lang, HashMap<String,Integer> hm_patrons, int frequency_min_of_terms){
        list_word = new ArrayList<>();
        map_list_term = new HashMap<>();
        list_candidat_terms = new ArrayList<>();
        map_list_pattern = new HashMap<>();
        
        if(lang.equalsIgnoreCase("spanish")){
        	point_tagger = "FS";
        }else{
        	point_tagger = "SENT";
        }
        
        mots = new String [2];
        min_frequency = frequency_min_of_terms;
        count_doc = 1;
        sum_total_frequency = 0;
        num_patterns = 200;
        map_list_pattern = hm_patrons;
        sum_total_frequency = compute_sum_frequency(map_list_pattern);
    }
    
    private static int compute_sum_frequency(HashMap<String,Integer> hm_patrons){
        int sum = 0;
        for (Integer value : hm_patrons.values()) {
           sum += value;
        }
        return sum;
    }
    
    private static void compute_LIDF_value(){
        sortMap_by_Num_Words();
        for(int i=0;i<getEntries().size();i++){
            int [] values = isInOtherString(i);
            int subtraction = getEntries().get(i).getValue().getNew_frequency()-values[0];
            if(subtraction >= 0){
                getEntries().get(i).getValue().setNew_frequency(subtraction);
            }else{
                System.out.println(getEntries().get(i).getKey()+";"+subtraction);
            }
            getEntries().get(i).getValue().setSum_new_frequency(values[0]);
            getEntries().get(i).getValue().setNum_other_term_higer((values[1]));
            LIDF_value(i);
        }
    }
    
    private static void LIDF_value(int i){
        double c_value;
        if(getEntries().get(i).getValue().getNum_other_term_higer()>0){
            if(getEntries().get(i).getValue().getFrequency()-(getEntries().get(i).getValue().getSum_new_frequency()*1.0/getEntries().get(i).getValue().getNum_other_term_higer())<=0.0){
                c_value = MathFonctions.log2(((getEntries().get(i).getValue().getNum_word()+1)))*(getEntries().get(i).getValue().getFrequency()-
                        (getEntries().get(i).getValue().getSum_new_frequency()*1.0/(getEntries().get(i).getValue().getNum_other_term_higer())+0.5));
                
            }else{
                c_value = MathFonctions.log2(((getEntries().get(i).getValue().getNum_word()+1)))*(getEntries().get(i).getValue().getFrequency()-
                        (getEntries().get(i).getValue().getSum_new_frequency()*1.0/getEntries().get(i).getValue().getNum_other_term_higer()));
            }

        }else{
            c_value = MathFonctions.log2(((getEntries().get(i).getValue().getNum_word()+1)))*getEntries().get(i).getValue().getFrequency();
        }
        
        double probability = 1.0*getEntries().get(i).getValue().getFreq_pattern()/sum_total_frequency;
        double idf = Math.log10(1.0*(count_doc-1)/getEntries().get(i).getValue().getNb_doc());
        //double measure_1 = c_value*probability;
        double measure_1 = c_value*probability*idf;
       
        getEntries().get(i).getValue().setIdf(MathFonctions.Round(idf,4));
        getEntries().get(i).getValue().setMeasure_1(MathFonctions.Round(measure_1,4));

    }
    
    private static List sortMap_by_Importance(){
        try{
            // Tri de la liste sur la valeur de l'entrée
            Collections.sort(getEntries(), new Comparator<Map.Entry<String, PT_LIDF_value>>() {
                @Override
                public int compare(Entry<String, PT_LIDF_value> o1, Entry<String, PT_LIDF_value> o2) {
                    if(o2.getValue().getMeasure_1()>o1.getValue().getMeasure_1()){
                        return 1;
                    }else{
                        if(o2.getValue().getMeasure_1()<o1.getValue().getMeasure_1()){
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
        
        for (final Entry<String, PT_LIDF_value> entry : getEntries()) {
            CandidatTerm ct = new CandidatTerm(entry.getValue().getTerm(), entry.getValue().getMeasure_1());
            list_candidat_terms.add(ct);
        }
        
        return list_candidat_terms;

    }
    
   
    private static int[] isInOtherString(int i){
        int values_computed[] = new int [2];
        int sum = 0;
        int count = 0;
        for(int j=0;j<i;j++){
            if(getEntries().get(i).getValue().getNum_word() < getEntries().get(j).getValue().getNum_word()){
                if((getEntries().get(j).getKey().contains(" "+getEntries().get(i).getKey()+" ")) 
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
    
    private static List sortMap_by_Num_Words(){
        setEntries(new ArrayList<>(map_list_term.entrySet()));
        try{
            // Tri de la liste sur la valeur de l'entrée
            Collections.sort(getEntries(), new Comparator<Map.Entry<String, PT_LIDF_value>>() {
                @Override
                public int compare(Map.Entry<String, PT_LIDF_value> o1, Map.Entry<String, PT_LIDF_value> o2) {
                    if(o2.getValue().getNum_word()>o1.getValue().getNum_word()){
                        return 1;
                    }else{
                        if(o2.getValue().getNum_word()<o1.getValue().getNum_word()){
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
    
    private static void delete_by_frequency(){
        try{
            //System.out.println(map_list_term.size());
            for( Iterator it = map_list_term.keySet().iterator(); it.hasNext();) {
                if(map_list_term.get((String)it.next()).getFrequency() < min_frequency){
                    it.remove();
                }
            }
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
    }
    
    private static void computeTermsPhrase(){
        String cadTAG;
        for(int i=0; i<list_word.size();i++){
            cadTAG = list_word.get(i).getPos_alternative();
            for(int j=i;j<list_word.size();j++){
                if(i!=j){
                    cadTAG = cadTAG + " " + list_word.get(j).getPos_alternative();
                }
                if(isInTruePatterns(cadTAG.trim())){
                    String term_candidate = "";
                    for(int r=i;r<=j;r++){
                        //term_candidate = term_candidate + " " + list_word.get(r).getLemma();
                        
                        //Cuando aumentamos esto, tenemos que comentar las lineas de abajo.
                        //Esta linea de abajo obtiene la palabra como tal y no toma en cuenta el lema de las palabras
                        term_candidate = term_candidate + " " + list_word.get(r).getWord();
                        
                        
                        //Las siguientes lineas sirven para extraer el lema de las palabras, excepto
                        //que si es una palabra extraña, pondra como lema "unknown"
                        /*
                        if(list_word.get(r).getLemma().equalsIgnoreCase("<unknown>")){
                            term_candidate = term_candidate + " " + list_word.get(r).getWord();
                        }else{
                            term_candidate = term_candidate + " " + list_word.get(r).getLemma();
                        }*/
                    }
                    Add_Term(term_candidate.trim(), cadTAG.trim(), cadTAG.trim(), 1, (j-i+1), map_list_pattern.get(cadTAG));
                }
            }
        }
    }
    
    private static void Add_Term(String term_candidate, String pos, String pos_alternative, int frequency, int num_word, int freq_pattern){
        term_candidate = term_candidate.toLowerCase();
        String aux_string_term;
        aux_string_term = term_candidate.replaceAll(" 's", "'s");
        aux_string_term = aux_string_term.replaceAll(" '", "'");
        aux_string_term = aux_string_term.replaceAll("' ", "'");
        //compute if the term is in the list or not to add it
        if(map_list_term.containsKey(aux_string_term)){
            int freq_aux = map_list_term.get(aux_string_term).getFrequency();
            map_list_term.get(aux_string_term).setFrequency(freq_aux+1);
            int aux_flag_num_docs = map_list_term.get(aux_string_term).getNum_doc_flag();
            if(aux_flag_num_docs < count_doc){
                int aux_num_docs = map_list_term.get(aux_string_term).getNb_doc();
                map_list_term.get(aux_string_term).setNb_doc(aux_num_docs+1);
                map_list_term.get(aux_string_term).setNum_doc_flag(count_doc);
            }
        }else{
            //System.out.println(term_candidate);
            PT_LIDF_value ct = new PT_LIDF_value(aux_string_term, pos, pos_alternative, 1, 1, count_doc, num_word, freq_pattern);
            map_list_term.put(aux_string_term,ct);
        }
    }
    
    private static boolean isInTruePatterns(String TAG){
        return map_list_pattern.containsKey(TAG);
    }
    
    private static void computeTermsWithPatterns(ArrayList<String> al_tagged_doc){
        for ( int j=0;j<al_tagged_doc.size();j++){
            mots = Split.splitMots(al_tagged_doc.get(j));
            
            String word = mots[0];
            String pos = mots[1];
            String lemma = mots[2];
            
            String pos_alternative = pos;

            if(!word.equalsIgnoreCase("##########END##########")){
                if(!pos.equalsIgnoreCase(point_tagger)){
                    Word word_obj = new Word(word,lemma,pos,pos_alternative);
                    list_word.add(word_obj);
                }else{
                    computeTermsPhrase();
                    list_word = new ArrayList<>();
                }
            }else{
                count_doc++;
            }
        }
    }
    

    /**
     * @param aEntries the entries to set
     */
    private static void setEntries(List<Map.Entry<String, PT_LIDF_value>> aEntries) {
        entries = aEntries;
    }

    /**
     * @return the entries
     */
    private static List<Map.Entry<String, PT_LIDF_value>> getEntries() {
        return entries;
    }
    
    public static ArrayList<CandidatTerm> computePossibleTerms(ArrayList<String> al_tagged_doc, ArrayList<String> al_Patterns, String lang, HashMap<String,Integer> hm_patrons, int frequency_min_of_terms){
        try{
            initialize(lang, hm_patrons, frequency_min_of_terms);
            computeTermsWithPatterns(al_tagged_doc);
            delete_by_frequency();
            compute_LIDF_value();
        
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in PossibleTerms ");
        }
        return (ArrayList<CandidatTerm>) sortMap_by_Importance();
    }  
}
