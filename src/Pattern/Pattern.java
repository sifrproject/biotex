/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern;

import CommonResources.Split;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author juanlossio
 */
public class Pattern {
    public static ArrayList<String> load_Patterns(String language, String typeTerms, String tool_POS, int num_patterns, String currentDir, 
    		int freq, String source_patterns){
        ArrayList<String> al_Patterns = new ArrayList<String>();
        //String nameFile = currentDir + "/patterns/Patterns_"+language+"_"+ tool_POS +".csv";
         
		/////////////////////////////////////////////////////////
		///////////////////// C A M B I O S /////////////////////
		/////////////////////////////////////////////////////////
        //Local Machine : 
        String nameFile = source_patterns + File.separator + "Patterns_"+language+"_"+ tool_POS +".csv";
        //String nameFile = "/Volumes/MacintoshDocuments/EclipseWorkSpace/JarBioTex/patterns/Patterns_"+language+"_"+ tool_POS +".csv";
        
        //Server Machine :
        //String nameFile = "/home/juan/patterns/Patterns_"+language+"_"+ tool_POS +".csv";
        
        int length_pattern;
        if(typeTerms.equalsIgnoreCase("multi")){
            length_pattern = 2;
        }else{
            length_pattern = 1;
        }
        
        try{
            FileReader fr = new FileReader(nameFile);
            BufferedReader bf = new BufferedReader(fr);
            
            boolean eof = false;
            int count = 0;
            while (!eof && count < num_patterns) {
                String sLinea = bf.readLine();
                if (sLinea == null) {
                    eof = true;
                }else{
                    String mots[] = Split.splitMots(sLinea,";");
                    if((Split.splitWord(mots[0])).length >= length_pattern) {
                        al_Patterns.add(mots[0]);
                        count++;
                    }
                }
            }
            bf.close();
            fr.close();
            //System.out.println("al_Patterns : " + al_Patterns.size());
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in Load Patterns: ");
        }
        return al_Patterns;
    }
}
