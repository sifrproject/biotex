/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonResources;

/**
 *
 * @author juanlossio
 */
public class MathFonctions {
    
    public static double Round(double Rval, int Rpl) {
        double p = (double)Math.pow(10,Rpl);
        Rval = Rval * p;
        double tmp = Math.round(Rval);
        return (double)tmp/p;
    }
    
    public static double log2(double num){
        return (Math.log(num)/Math.log(2));
    }
}
