package fr.lirmm.object;



/**
 *
 * @author Juan Antonio LOSSIO VENTURA
 */
public class PT_LIDF_value {
    private String term;
    private String pos_seq;
    private String pos_seq_alternative;
    private int frequency;
    
    private int nb_doc; // nombre de documents dans lequel il est
    private int num_doc_flag; // flag qui sert pour savoir si le doc a changé à un autre
    private double idf;
    
    private int num_word;
    private int new_frequency; //para disminuir a los términos que estan aninados
    private int sum_new_frequency; //la suma de las frecuencias a los términos que estan anidados
    private int num_other_term_higer;
    
    private int freq_pattern;
    
    private double measure_1; //c-value*p(a)*idf
    private double measure_2; //graph
    private double measure_3; //web
    
    private double importance; // total
    private boolean isTrueTerm;
    private String sourceDictionary;

    public PT_LIDF_value(String term, String pos_seq, String pos_seq_alternative, int frequency, int nb_doc, int num_doc_flag, int num_word, int freq_pattern){
        this.term = term;
        this.pos_seq = pos_seq;
        this.pos_seq_alternative = pos_seq_alternative;
        this.frequency = frequency;
        this.nb_doc = nb_doc;
        this.num_doc_flag = num_doc_flag;
        this.num_word = num_word;
        this.freq_pattern = freq_pattern;
    }
    /**
     * @return the term
     */
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
     * @return the pos_seq
     */
    public String getPos_seq() {
        return pos_seq;
    }

    /**
     * @param pos_seq the pos_seq to set
     */
    public void setPos_seq(String pos_seq) {
        this.pos_seq = pos_seq;
    }

    /**
     * @return the pos_seq_alternative
     */
    public String getPos_seq_alternative() {
        return pos_seq_alternative;
    }

    /**
     * @param pos_seq_alternative the pos_seq_alternative to set
     */
    public void setPos_seq_alternative(String pos_seq_alternative) {
        this.pos_seq_alternative = pos_seq_alternative;
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
     * @return the idf
     */
    public double getIdf() {
        return idf;
    }

    /**
     * @param idf the idf to set
     */
    public void setIdf(double idf) {
        this.idf = idf;
    }

    /**
     * @return the measure_1
     */
    public double getMeasure_1() {
        return measure_1;
    }

    /**
     * @param measure_1 the measure_1 to set
     */
    public void setMeasure_1(double measure_1) {
        this.measure_1 = measure_1;
    }

    /**
     * @return the measure_2
     */
    public double getMeasure_2() {
        return measure_2;
    }

    /**
     * @param measure_2 the measure_2 to set
     */
    public void setMeasure_2(double measure_2) {
        this.measure_2 = measure_2;
    }

    /**
     * @return the measure_3
     */
    public double getMeasure_3() {
        return measure_3;
    }

    /**
     * @param measure_3 the measure_3 to set
     */
    public void setMeasure_3(double measure_3) {
        this.measure_3 = measure_3;
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
    public boolean getIsTrueTerm() {
        return isTrueTerm;
    }

    /**
     * @param isTrueTerm the isTrueTerm to set
     */
    public void setIsTrueTerm(boolean isTrueTerm) {
        this.isTrueTerm = isTrueTerm;
    }

    /**
     * @return the sourceDictionary
     */
    public String getSourceDictionary() {
        return sourceDictionary;
    }

    /**
     * @param sourceDictionary the sourceDictionary to set
     */
    public void setSourceDictionary(String sourceDictionary) {
        this.sourceDictionary = sourceDictionary;
    }

    /**
     * @return the nb_doc
     */
    public int getNb_doc() {
        return nb_doc;
    }

    /**
     * @param nb_doc the nb_doc to set
     */
    public void setNb_doc(int nb_doc) {
        this.nb_doc = nb_doc;
    }

    /**
     * @return the num_doc_flag
     */
    public int getNum_doc_flag() {
        return num_doc_flag;
    }

    /**
     * @param num_doc_flag the num_doc_flag to set
     */
    public void setNum_doc_flag(int num_doc_flag) {
        this.num_doc_flag = num_doc_flag;
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
     * @return the num_word
     */
    public int getNum_word() {
        return num_word;
    }

    /**
     * @param num_word the num_word to set
     */
    public void setNum_word(int num_word) {
        this.num_word = num_word;
    }

    /**
     * @return the freq_pattern
     */
    public int getFreq_pattern() {
        return freq_pattern;
    }

    /**
     * @param freq_pattern the freq_pattern to set
     */
    public void setFreq_pattern(int freq_pattern) {
        this.freq_pattern = freq_pattern;
    }
}

