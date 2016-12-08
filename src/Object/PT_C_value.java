/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author juanlossio
 */
public class PT_C_value {
    private String term;
    private int num_words;
    private int frequency;
    
    private int new_frequency; //para disminuir a los términos que estan aninados
    private int sum_new_frequency; //la suma de las frecuencias a los términos que estan anidados
    private int num_other_term_higer;
            
    private double importance;
    private boolean isTrueTerm;
    private int num_docs;

    /**
     * @return the term
     */
    PT_C_value(String term, int frequency, int num_words, int new_frequency, 
            int sum_new_frequency, int num_other_term_higer){
        setTerm(term);
        setFrequency(frequency);
        setNum_words(num_words);
        setNew_frequency(new_frequency);
        setSum_new_frequency(sum_new_frequency);
        setNum_other_term_higer(num_other_term_higer);
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
    
    /*@Override
    public int compareTo(Object o) {
        PossibleTerm a_comparer = (PossibleTerm)o;
        if(num_words > a_comparer.num_words){
            return 1;
        }else{
            if(num_words < a_comparer.num_words){
                return -1;
            }else{
                a_comparer.frequency = this.getFrequency()+1;
                return 0;
                if(term.compareTo(a_comparer.term)<0)
                {
                    return -1;
                }
                else
                {
                    if(term.compareTo(a_comparer.term)>0){
                        return 1;
                    }else{
                        a_comparer.frequency = this.getFrequency()+1;
                        return 0;
                    }
                }
            }
        }
    }*/

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
    
}
