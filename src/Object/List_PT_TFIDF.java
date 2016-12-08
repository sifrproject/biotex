/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import CommonResources.MathFonctions;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author juanlossio
 */
public class List_PT_TFIDF {
    private HashMap<String, PT_AKE> map_list_term;
    HashMap<String, PT_AKE> list_term;
    ArrayList<HashMap<String, PT_AKE>> list_list_term;
    
    private List<Map.Entry<String, PT_AKE>> entries;
    
    private ArrayList<CandidatTerm> candidadTerms;
    
    public List_PT_TFIDF(){
        list_list_term = new ArrayList<HashMap<String, PT_AKE>>();
        map_list_term = new HashMap<String, PT_AKE>();
        list_term = new HashMap<String, PT_AKE>();
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
    
    public void Add_Term(String string_term, String language, int count_doc){
        String aux_string_term = "";
        //Replacement of apostrof in english and french
        if(language.equalsIgnoreCase("english")){
            aux_string_term = string_term.replaceAll(" '", "'");
        }else{
            if(language.equalsIgnoreCase("french")){
                aux_string_term = string_term.replaceAll("' ", "'");
            }else{
            	if(language.equalsIgnoreCase("spanish")){
                    aux_string_term = string_term.trim();
                }
            }
        }
        
        //compute if the term is in the list or not to add it
        if(getMap_list_term().containsKey(aux_string_term)){
            int freq_aux = getMap_list_term().get(aux_string_term).getFrequency();
            getMap_list_term().get(aux_string_term).setFrequency(freq_aux+1);
            int aux_flag_num_docs = getMap_list_term().get(aux_string_term).getFlag_num_docs();
            if(aux_flag_num_docs < count_doc){
                int aux_num_docs = getMap_list_term().get(aux_string_term).getNum_docs();
                getMap_list_term().get(aux_string_term).setNum_docs(aux_num_docs+1);
                getMap_list_term().get(aux_string_term).setFlag_num_docs(count_doc);
            }
        }else{
            PT_AKE pt = new PT_AKE(aux_string_term,1,length_term(string_term),0,0,0,1,count_doc);
            getMap_list_term().put(aux_string_term,pt);
        }
        
        if(list_term.containsKey(aux_string_term)){
        //System.out.println(aux_string_term);
            int freq_aux = list_term.get(aux_string_term).getFrequency();
            list_term.get(aux_string_term).setFrequency(freq_aux+1);
        }else{
            PT_AKE pt = new PT_AKE(aux_string_term,1,length_term(string_term),0,0,0,0,0);
            list_term.put(aux_string_term,pt);
        }
    }
    
    public void add_list_term_to_List(){
        list_list_term.add(list_term);
        list_term = new HashMap<String, PT_AKE>();
    }
    
    public void showFeatures1()
    {
        System.out.println("map_list_term.size() : " + getMap_list_term().size());
        for( Iterator it = getMap_list_term().keySet().iterator(); it.hasNext();) {
            String clave = (String)it.next(); 
            PT_AKE valor = (PT_AKE)getMap_list_term().get(clave); 
            //System.out.println(valor.getTerm()+";"+valor.getFrequency()+";"+valor.getImportance());
            System.out.println(valor.getTerm()+";"+valor.getImportance());
        }
    }
    
    public void showFeatures()
    {
        System.out.println("list_list_term.size() : "+list_list_term.size());
        for(int i=0;i<list_list_term.size();i++){
            System.out.println("---------------------------------------------NOUVEAU---------------------------------------------");
            HashMap<String,PT_AKE> hmap_aux = list_list_term.get(i);
            for( Iterator it = hmap_aux.keySet().iterator(); it.hasNext();) {
                String clave = (String)it.next(); 
                PT_AKE valor = (PT_AKE)hmap_aux.get(clave); 
                System.out.println(valor.getTerm()+";"+valor.getFrequency()+";"+valor.getNum_docs()+";"+valor.getImportance()+";"+valor.getImportance_normalized());
            }
        }
        System.out.println("======================================");
    }
    
    public void compute_TFIDF_BY_Doc(String option)
    {
        try{
            for(int i=0;i<list_list_term.size();i++){
                int max_freq = computeHighAndSumFreq(list_list_term.get(i));
                double max_tfidf = 0.0;
                double min_tfidf = 0.0;

                for( Iterator it = list_list_term.get(i).keySet().iterator(); it.hasNext();) {
                    String key = (String)it.next();
                    PT_AKE valor = list_list_term.get(i).get(key);

                    double tf = 1.0*valor.getFrequency()/max_freq;
                    double idf = Math.log10(list_list_term.size()*1.0/getMap_list_term().get(key).getNum_docs()+0.1);
                    double tf_idf = MathFonctions.Round(tf*idf,4);
                    
                    valor.setImportance(tf_idf);
                    
                    if(tf_idf>max_tfidf){
                        max_tfidf = tf_idf;
                    }
                    if(tf_idf<min_tfidf){
                        min_tfidf = tf_idf;
                    }
                }
                
                for( Iterator it = list_list_term.get(i).keySet().iterator(); it.hasNext();) {
                    String key = (String)it.next();
                    PT_AKE valor = list_list_term.get(i).get(key);
                    double importance_normalized = MathFonctions.Round((valor.getImportance()-min_tfidf)/(max_tfidf-min_tfidf),4);
                    valor.setImportance_normalized(importance_normalized);
                    int option1 = 0;
                    if(option.equalsIgnoreCase("MAX"))
                    	option1 = 1;
                    if(option.equalsIgnoreCase("SUM"))
                    	option1 = 2;
                    if(option.equalsIgnoreCase("AVG"))
                    	option1 = 3;
                    switch(option1){
                        case 1: 
                            if(map_list_term.get(key).getImportance()<importance_normalized){
                            	map_list_term.get(key).setImportance(importance_normalized);
                            }
                            break;
                        case 2: 
                            double aux_imp = map_list_term.get(key).getImportance();
                            map_list_term.get(key).setImportance(MathFonctions.Round(aux_imp+importance_normalized,4));
                            break;
                        case 3: 
                            double aux_imp_ = map_list_term.get(key).getImportance_normalized();
                            map_list_term.get(key).setImportance_normalized(aux_imp_+importance_normalized);
                            break;
                        default: break;
                    }
                }
            }
            if(option.equalsIgnoreCase("AVG")){
                compute_AVG();
            }
            
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
    }
    
    private void compute_AVG(){
        for( Iterator it = getMap_list_term().keySet().iterator(); it.hasNext();) {
            String key = (String)it.next();
            getMap_list_term().get(key).setImportance(MathFonctions.Round(getMap_list_term().get(key).getImportance_normalized()/getMap_list_term().get(key).getNum_docs(),4));
        }
    }
    
    private int computeHighAndSumFreq(HashMap<String,PT_AKE> hmap_aux){
        int max = 0;
        for( Iterator it = hmap_aux.keySet().iterator(); it.hasNext();) {
            int value = ((PT_AKE)hmap_aux.get((String)it.next())).getFrequency(); 
            if(max<value){
                max = value;
            }
        }
        return max;
    }
    
    public void showList()
    {
        System.out.println(" =====================================");
        System.out.println("|        LISTE DE POSSIBLE TF-IDF     |");
        System.out.println(" =====================================");                
        for( Iterator it = getMap_list_term().keySet().iterator(); it.hasNext();) {
            String clave = (String)it.next(); 
            PT_AKE valor = (PT_AKE)getMap_list_term().get(clave); 
            System.out.println(valor);
        }
        System.out.println("======================================");
    }
    
    public List sortMap_by_Importance(){
        setEntries(new ArrayList<Entry<String, PT_AKE>>(getMap_list_term().entrySet()));
        try{
            // Tri de la liste sur la valeur de l'entrée
            Collections.sort(getEntries(), new Comparator<Map.Entry<String, PT_AKE>>() {
                @Override
                public int compare(Entry<String, PT_AKE> o1, Entry<String, PT_AKE> o2) {
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
            /*for (final Entry<String, PT_AKE> entry : getEntries()) {
                //System.out.println(entry.getKey() + " " + entry.getValue());
                System.out.println(entry.getValue().getTerm()+";"+entry.getValue().getImportance());
            }*/
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
        return getEntries();
    }
    
    //compute how many words are contained in a term
    private int length_term(String string_term){
        String [] sub_term = string_term.split(" ");
        return sub_term.length;
    }

    /**
     * @return the map_list_term
     */
    public HashMap<String, PT_AKE> getMap_list_term() {
        return map_list_term;
    }

    /**
     * @param map_list_term the map_list_term to set
     */
    public void setMap_list_term(HashMap<String, PT_AKE> map_list_term) {
        this.map_list_term = map_list_term;
    }

    /**
     * @return the entries
     */
    public List<Map.Entry<String, PT_AKE>> getEntries() {
        return entries;
    }

    /**
     * @param entries the entries to set
     */
    public void setEntries(List<Map.Entry<String, PT_AKE>> entries) {
        this.entries = entries;
    }
    
    public ArrayList<CandidatTerm> get_CandidatTerms(){
        candidadTerms = new ArrayList<CandidatTerm> ();
        for (final Entry<String, PT_AKE> entry : getEntries()) {
            CandidatTerm ct = new CandidatTerm(entry.getValue().getTerm(), entry.getValue().getImportance());
            candidadTerms.add(ct);
        }
        return candidadTerms;
    }
}
