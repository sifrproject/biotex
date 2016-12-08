/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CommonResources;


import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Juan LOSSIO
 */
public class Split {

    private static String clearTexteStrangeCaracteres(String ligneTexte){
        ligneTexte = ligneTexte.replaceAll("«"," ");
        ligneTexte = ligneTexte.replaceAll("»"," ");
        ligneTexte = ligneTexte.replaceAll(" "," ");
        ligneTexte = ligneTexte.replaceAll("- "," ");
        return ligneTexte;
    }
    
    public static String getBetweenParenthesis(String ligneTexte){
        int ind_begin_Parenthesis = ligneTexte.indexOf("(");
        int ind_end_Parenthesis = ligneTexte.indexOf(")");
        if(ind_begin_Parenthesis>-1 && ind_end_Parenthesis>-1){
            if(ind_begin_Parenthesis<ind_end_Parenthesis){
                String aux_before = "", aux_between = "", aux_end = "";
                //System.out.println(ligneTexte);
                
                aux_before = ligneTexte.substring(0, ind_begin_Parenthesis);
                aux_between = ligneTexte.substring(ind_begin_Parenthesis, ind_end_Parenthesis+1);
                aux_end = ligneTexte.substring(ind_end_Parenthesis+1);
                
                if(!aux_end.trim().equalsIgnoreCase("")){
                    ligneTexte = aux_before.trim() + " " + aux_end.trim() + " " + aux_between.trim();
                }else{
                    ligneTexte = aux_before.trim() + " " + aux_between.trim();
                }
                //System.out.println(ligneTexte);
            }
        }
        return ligneTexte;
    }
    /*
    public static String getBetweenParenthesis(String ligneTexte){
        int ind_begin_Parenthesis = ligneTexte.indexOf("(");
        int ind_end_Parenthesis = ligneTexte.indexOf(")");
        while(ind_begin_Parenthesis>-1 && ind_end_Parenthesis>-1){
            if(ind_begin_Parenthesis<ind_end_Parenthesis){
                String aux_before = "", aux_between = "", aux_end = "";
                //System.out.println(ligneTexte);
                
                aux_before = ligneTexte.substring(0, ind_begin_Parenthesis);
                aux_between = ". " + ligneTexte.substring(ind_begin_Parenthesis+1, ind_end_Parenthesis);
                aux_end = ligneTexte.substring(ind_end_Parenthesis+1);
                
                if(!aux_end.trim().equalsIgnoreCase("")){
                    ligneTexte = aux_before.trim() + " " + aux_end.trim() + " " + aux_between.trim();
                }else{
                    ligneTexte = aux_before.trim() + " " + aux_between.trim();
                }
            }
            ind_begin_Parenthesis = ligneTexte.indexOf("(");
            ind_end_Parenthesis = ligneTexte.indexOf(")");
        }
        return ligneTexte;
    }*/
    
    public static String getBetweenCrochet(String ligneTexte){
        int ind_begin_Crochet = ligneTexte.indexOf("[");
        int ind_end_Crochet = ligneTexte.indexOf("]");
        if(ind_begin_Crochet>-1 && ind_end_Crochet>-1){
            if(ind_begin_Crochet<ind_end_Crochet){
                String aux_before = "", aux_between = "", aux_end = "";
                //System.out.println(ligneTexte);
                
                aux_before = ligneTexte.substring(0, ind_begin_Crochet);
                aux_between = ligneTexte.substring(ind_begin_Crochet, ind_end_Crochet+1);
                aux_end = ligneTexte.substring(ind_end_Crochet+1);
                
                if(!aux_end.trim().equalsIgnoreCase("")){
                    ligneTexte = aux_before.trim() + " " + aux_end.trim() + " " + aux_between.trim();
                }else{
                    ligneTexte = aux_before.trim() + " " + aux_between.trim();
                }
                //System.out.println(ligneTexte);
            }
        }
        return ligneTexte;
    }
    
    public static String getBetweenBraces(String ligneTexte){
        int ind_begin_Braces = ligneTexte.indexOf("{");
        int ind_end_Braces = ligneTexte.indexOf("}");
        if(ind_begin_Braces>-1 && ind_end_Braces>-1){
            if(ind_begin_Braces<ind_end_Braces){
                String aux_before = "", aux_between = "", aux_end = "";
                //System.out.println(ligneTexte);
                
                aux_before = ligneTexte.substring(0, ind_begin_Braces);
                aux_between = ligneTexte.substring(ind_begin_Braces, ind_end_Braces+1);
                aux_end = ligneTexte.substring(ind_end_Braces+1);
                
                if(!aux_end.trim().equalsIgnoreCase("")){
                    ligneTexte = aux_before.trim() + " " + aux_end.trim() + " " + aux_between.trim();
                }else{
                    ligneTexte = aux_before.trim() + " " + aux_between.trim();
                }
                //System.out.println(ligneTexte);
            }
        }
        return ligneTexte;
    }
    
    public static ArrayList splitInPhrasesPreTraitement(String ligneTexte)
    {
        String aux;
        ArrayList<String> allPhrases = new ArrayList<String>();
        String [] phrases = clearTexteStrangeCaracteres(ligneTexte).split("[\\.\\:\\;\\,\\!\\?\\•]+");
        for(int i=0;i<phrases.length;i++)
        {
            aux  = phrases[i].trim();
            if(!aux.equalsIgnoreCase("") && aux!= null){
                allPhrases.add(aux);
            }
        }
        return allPhrases;
    }
    
    
    public static ArrayList splitInPhrases(String ligneTexte)
    {
        // \\ 
        //System.out.println(ligneTexte);
        //System.out.println("Bien");
        ligneTexte = getBetweenBraces(getBetweenCrochet(getBetweenParenthesis(ligneTexte))); //Solo borrar cuando se trata de los verdaderos términos
        //System.out.println("Acabo");
        String aux;
        ArrayList<String> allPhrases = new ArrayList<String>();
        String [] phrases = ligneTexte.split("[\\.\\:\\;\\,\\!\\?\\•\\(\\)\\[\\]\\=]+"); //() borrar cuando se trata de los verdaderos términos
        for(int i=0;i<phrases.length;i++)
        {
            aux  = phrases[i].trim();
            if(!aux.equalsIgnoreCase("") && aux!= null){
                allPhrases.add(aux);
            }
        }
        return allPhrases;
    }

    public static ArrayList splitInPhrasesTrueTermsExperimentation(String ligneTexte)
    {
        // \\ 
        ligneTexte = getBetweenParenthesis(ligneTexte);
        String aux;
        ArrayList<String> allPhrases = new ArrayList<String>();
        String [] phrases = clearTexteStrangeCaracteres(ligneTexte).split("[\\.\\:\\;\\,\\!\\?\\(\\)\\•\\=]+");
        for(int i=0;i<phrases.length;i++)
        {
            aux  = phrases[i].trim();
            if(!aux.equalsIgnoreCase("") && aux!= null){
                allPhrases.add(aux);
            }
        }
        return allPhrases;
    }
    
    public static String [] splitMots(String ligneTexte)
    {
        StringTokenizer token_space = new StringTokenizer(ligneTexte);
        String [] mots = new String [token_space.countTokens()];

        int ind = 0;
        while(token_space.hasMoreElements())
        {
            mots[ind] = token_space.nextToken();
            ind++;
        }
        return mots;
    }

    public static String [] splitMots(String ligneTexte, String separator)
    {
        StringTokenizer token_space = new StringTokenizer(ligneTexte, separator);
        String [] mots = new String [token_space.countTokens()];

        int ind = 0;
        while(token_space.hasMoreElements())
        {
            mots[ind] = token_space.nextToken();
            ind++;
        }
        return mots;
    }
    
    public static String [] splitWord(String ligneTexte)
    {
        StringTokenizer token_space = new StringTokenizer(ligneTexte);
        String [] mots = new String [token_space.countTokens()];

        //System.out.println("Numero de tokens  : " + token_space.countTokens());
        int ind = 0;
        while(token_space.hasMoreElements())
        {
            mots[ind] = token_space.nextToken();
            //System.out.println(ind + "   " + mots[ind]);
            ind++;
        }
        //System.out.println(ind);
        return mots;
    }
}
