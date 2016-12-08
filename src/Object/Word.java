/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author Juan Antonio LOSSIO VENTURA
 */
public class Word {
    private String pos;
    private String pos_alternative;
    private String word;
    private String lemma;

    public Word(String word, String lemma, String pos, String pos_alternative){
        this.word = word;
        this.lemma = lemma;
        this.pos = pos;
        this.pos_alternative = pos_alternative;
    }
    /**
     * @return the pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * @return the pos_alternative
     */
    public String getPos_alternative() {
        return pos_alternative;
    }

    /**
     * @param pos_alternative the pos_alternative to set
     */
    public void setPos_alternative(String pos_alternative) {
        this.pos_alternative = pos_alternative;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the lemma
     */
    public String getLemma() {
        return lemma;
    }

    /**
     * @param lemma the lemma to set
     */
    public void setLemma(String lemma) {
        this.lemma = lemma;
    }
    
}
