/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POS;

import java.io.*; 
import java.util.ArrayList;

/** 
 * POS TreeTagger. 
 * @author  Juan LOSSIO 
 */ 
public class POS_TreeTagger { 
	
	//Local Machine : 
	
    public static ArrayList<String> POS_TreeTagger(String file, String language, String source_tagger) {  
        ArrayList<String> al_POS= new ArrayList<String>();
        try{
			
            //Local Machine : 
        	String cmd = source_tagger + File.separator + "cmd"+ File.separator +"tree-tagger-"+language;
        	//String cmd =  "cmd /c tag-"+language;
       
            
			/////////////////////////////////////////////////////////
			///////////////////// C A M B I O S /////////////////////
			/////////////////////////////////////////////////////////
        	//PARA UNIX
            String par = file;
            String[] cmd_parameters = new String[] {cmd,par};
            Runtime runtime = Runtime.getRuntime();
            final Process process = runtime.exec(cmd_parameters);
        	
        	//PARA WINDOWS
        	/*Runtime runtime = Runtime.getRuntime();
        	final Process process = runtime.exec(cmd + " " + file);
        	*/

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            try{
                while((line = reader.readLine()) != null) {
                	if(line.indexOf("l'")>-1){
                		//System.out.println(line);
                	}
                    al_POS.add(line);
                    //System.out.println(line);
                }
            }finally {
                reader.close();
            }
        }catch (Exception e){
            System.err.println(e.toString());
        }
        return al_POS;
    }
}