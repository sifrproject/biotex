/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.commonresources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author juanlossio
 * This class is for verifying the in parameters 
 */
public class ParameterVerification {
    
    public static int verifySource_is_TextFile(String source){
        try{
            FileReader fr = new FileReader(source);
            fr.close();
            return 1;
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
        return -1;
    }
    
    public static int verifySource_contains_FlagEndFile(String source){
        try{
            FileReader fr = new FileReader(source);
            BufferedReader br = new BufferedReader(fr);
            boolean eof = false;
            int num_doc = 0;
            while (!eof) {
                String sLinea = br.readLine();
                if (sLinea == null){
                    eof = true;
                }else{
                    if(sLinea.equalsIgnoreCase("##########END##########")){
                        num_doc++;
                    }
                    if(num_doc>=2){
                        break;
                    }
                }
            }       
            br.close();
            fr.close();
            if(num_doc <= 1){
            System.out.println("There are not files in this folder or there is only one file");
                return -1;
            }else{
                return 1;
            }
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
        return -1;
    }
    
    public static int verifySource_is_Directory(String source){
        
        File dir = new File(source);
        String[] ficheros = dir.list();
                
        int num_doc = ficheros.length;
        System.out.println(num_doc);
        
        if (ficheros == null) {
            System.out.println("There are not files in this folder");
            return -1;
        }
        else {
            for (int x=0;x<ficheros.length;x++){  
                if(ficheros[x].equalsIgnoreCase(".DS_Store")) {
                    num_doc--;
                }
            }
        }
        
        if(num_doc <= 1){
            System.out.println("There are not files in this folder or there is only one file");
            return -1;
        }else{
            return 1;
        }
    }
}
