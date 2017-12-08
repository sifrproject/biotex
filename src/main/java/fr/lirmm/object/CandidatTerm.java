/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.object;

/**
 *
 * @author juanlossio
 */
public class CandidatTerm {
    private String term;
    private double importance;
    private int isTrueTerm;
    private String sourceDictionary;

    public CandidatTerm(String term, double importance) {
        setTerm(term);
        setImportance(importance);
        setSourceDictionary("");
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
     * @param isTrueTerm the isTrueTerm to set
     */
    public void setIsTrueTerm(boolean isTrueTerm) {
        this.setIsTrueTerm(isTrueTerm);
    }

    /**
     * @return the isTrueTerm
     */
    public int getIsTrueTerm() {
        return isTrueTerm;
    }

    /**
     * @param isTrueTerm the isTrueTerm to set
     */
    public void setIsTrueTerm(int isTrueTerm) {
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
    
}
