/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern;

import CommonResources.Split;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 *
 * @author Juan Antonio LOSSIO VENTURA
 */
public class PatternProb {
    public static HashMap<String, Integer> load_patterns(String language, String typeTerms, String tool_POS, int num_patterns, String currentDir, 
    		int freq, String source_patterns){
        HashMap<String, Integer> map_list_pattern = new HashMap<>();
        
        /////////////////////////////////////////////////////////
        ///////////////////// C A M B I O S /////////////////////
        /////////////////////////////////////////////////////////
        //Local Machine : 
        String nameFile = source_patterns + File.separator + "Patterns_"+language+"_"+ tool_POS +".csv";
        //String nameFile = "/Volumes/MacintoshDocuments/EclipseWorkSpace/JarBioTex/patterns/Patterns_"+language+"_"+ tool_POS +".csv";
        
        //Server Machine :
        //String nameFile = "/home/juan/patterns/Patterns_"+language+"_"+ tool_POS +".csv";
        
        try{
            FileReader fr = new FileReader(nameFile);
            BufferedReader bf = new BufferedReader(fr);
            
            int length_pattern;
            if(typeTerms.equalsIgnoreCase("multi")){
                length_pattern = 2;
            }else{
                length_pattern = 1;
            }
        
            boolean eof = false;
            int count = 0;
            int max = 0;
            while (!eof && count < num_patterns) {
                String sLinea = bf.readLine();
                if (sLinea == null) {
                    eof = true;
                }else{
                    if(!sLinea.contains("'")){
                        String mots[] = Split.splitMots(sLinea,";");
                        if((Split.splitWord(mots[0])).length >= length_pattern) {
                            map_list_pattern.put(mots[0],Integer.parseInt(mots[1]));
                            count++;
                            if(Split.splitMots(mots[0]).length>max){
                                max = Split.splitMots(mots[0]).length;
                            }
                        }
                    }
                }
            }
            bf.close();
            fr.close();
        }catch(Exception ex){
            System.err.println("Error Text: " + ex.toString() + " in Load Patterns: ");
        }
        return map_list_pattern;
    }
}
