/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author juanlossio
 */
public class PT_AKE {
    private String term;
    private int num_words;
    private int frequency;
    
    private int new_frequency; //para disminuir a los términos que estan aninados
    private int sum_new_frequency; //la suma de las frecuencias a los términos que estan anidados
    private int num_other_term_higer;
            
    private double importance;
    private double importance_normalized;
    
    private boolean isTrueTerm;
    private int num_docs;
    private int flag_num_docs;

    /**
     * @return the term
     */
    PT_AKE(String term, int frequency, int num_words, int new_frequency, 
            int sum_new_frequency, int num_other_term_higer, int num_docs, int flag_num_docs){
        setTerm(term);
        setFrequency(frequency);
        setNum_words(num_words);
        setNew_frequency(new_frequency);
        setSum_new_frequency(sum_new_frequency);
        setNum_other_term_higer(num_other_term_higer);
        
        setNum_docs(num_docs);
        setFlag_num_docs(flag_num_docs);
    }
    
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the num_words
     */
    public int getNum_words() {
        return num_words;
    }

    /**
     * @param num_words the num_words to set
     */
    public void setNum_words(int num_words) {
        this.num_words = num_words;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the importance
     */
    public double getImportance() {
        return importance;
    }

    /**
     * @param importance the importance to set
     */
    public void setImportance(double importance) {
        this.importance = importance;
    }

    /**
     * @return the isTrueTerm
     */
    public boolean isIsTrueTerm() {
        return isTrueTerm;
    }

    /**
     * @param isTrueTerm the isTrueTerm to set
     */
    public void setIsTrueTerm(boolean isTrueTerm) {
        this.isTrueTerm = isTrueTerm;
    }

    /**
     * @return the num_docs
     */
    public int getNum_docs() {
        return num_docs;
    }

    /**
     * @param num_docs the num_docs to set
     */
    public void setNum_docs(int num_docs) {
        this.num_docs = num_docs;
    }
    
    @Override
    /*public String toString()
    {
        String cad = getNum_words() + "\n";
        return cad;
    }*/
    public String toString()
    {
        String cad = "\nTerm		:	" + getTerm() + "\n"
                    + "Frequency		:	" + getFrequency() + "\n" 
                    + "Num_words		:	" + getNum_words();
        return cad;
    }
 
    /**
     * @return the new_frequency
     */
    public int getNew_frequency() {
        return new_frequency;
    }

    /**
     * @param new_frequency the new_frequency to set
     */
    public void setNew_frequency(int new_frequency) {
        this.new_frequency = new_frequency;
    }

    /**
     * @return the sum_new_frequency
     */
    public int getSum_new_frequency() {
        return sum_new_frequency;
    }

    /**
     * @param sum_new_frequency the sum_new_frequency to set
     */
    public void setSum_new_frequency(int sum_new_frequency) {
        this.sum_new_frequency = sum_new_frequency;
    }

    /**
     * @return the num_other_term_higer
     */
    public int getNum_other_term_higer() {
        return num_other_term_higer;
    }

    /**
     * @param num_other_term_higer the num_other_term_higer to set
     */
    public void setNum_other_term_higer(int num_other_term_higer) {
        this.num_other_term_higer = num_other_term_higer;
    }

    /**
     * @return the importance_normalized
     */
    public double getImportance_normalized() {
        return importance_normalized;
    }

    /**
     * @param importance_normalized the importance_normalized to set
     */
    public void setImportance_normalized(double importance_normalized) {
        this.importance_normalized = importance_normalized;
    }

    /**
     * @return the flag_num_docs
     */
    public int getFlag_num_docs() {
        return flag_num_docs;
    }

    /**
     * @param flag_num_docs the flag_num_docs to set
     */
    public void setFlag_num_docs(int flag_num_docs) {
        this.flag_num_docs = flag_num_docs;
    }
    
}
