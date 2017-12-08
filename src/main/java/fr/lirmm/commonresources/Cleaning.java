/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonResources;

/**
 *
 * @author juanlossio
 */
public class Cleaning {
    public static String cleaningText(String ligne, String language){
        //ligne = ligne.replaceAll("\\{","(");
        //ligne = ligne.replaceAll("\\}",")");
        //ligne = ligne.replaceAll("\\[","(");
        //ligne = ligne.replaceAll("\\]",")");
        
        ligne = ligne.replaceAll("«"," ");
        ligne = ligne.replaceAll("»"," ");
        ligne = ligne.replaceAll("“"," ");
        ligne = ligne.replaceAll(" "," ");
        ligne = ligne.replaceAll("- "," ");
        ligne = ligne.replaceAll("^","");
        
        
        ligne = ligne.replaceAll("‘"," ");
        ligne = ligne.replaceAll("’"," ");
        ligne = ligne.replaceAll("&lsquo;","'");
        
        ligne = ligne.replaceAll("&rsquo;","'");
        ligne = ligne.replaceAll("&sbquo;","'");
        ligne = ligne.replaceAll("&ldquo;","\"");
        ligne = ligne.replaceAll("&rdquo;","\"");
        ligne = ligne.replaceAll("&bdquo;","\"");
        
        ligne = ligne.replaceAll("&quot;", "\"");
        ligne = ligne.replaceAll("&#39;", "'");
        
        ligne = ligne.replaceAll("&nbsp;", " ");
        ligne = ligne.replaceAll("&lt;", "<");
        ligne = ligne.replaceAll("&gt;", ">");
        ligne = ligne.replaceAll("\"", "");
        
        ligne = ligne.replaceAll("&amp;", "&");
        ligne = ligne.replaceAll("&lt;", "<");
        ligne = ligne.replaceAll("&gt;", ">");
        ligne = ligne.replaceAll("&euro;", "€");
        
        /*
        ligne = ligne.replaceAll("&dagger;", "†");
        ligne = ligne.replaceAll("&Dagger;", "‡");
        ligne = ligne.replaceAll("&lt;", "‹");
        ligne = ligne.replaceAll("&gt;", "›");
        ligne = ligne.replaceAll("&oelig;", "œ");
        ligne = ligne.replaceAll("&Yuml;", "Ÿ");
        ligne = ligne.replaceAll("&nbsp;", " ");
        ligne = ligne.replaceAll("&iexcl;", "¡");
        ligne = ligne.replaceAll("&cent;", "¢");
        ligne = ligne.replaceAll("&pound;", "£");
        ligne = ligne.replaceAll("&curren;", "¤");
        ligne = ligne.replaceAll("&yen;", "¥");
        ligne = ligne.replaceAll("&sect;", "§");
        ligne = ligne.replaceAll("&uml;", "¨");
        ligne = ligne.replaceAll("&die;", "¨");
        ligne = ligne.replaceAll("&copy;", "©");
        ligne = ligne.replaceAll("&ordf;", "ª");
        ligne = ligne.replaceAll("&laquo;", "«");
        ligne = ligne.replaceAll("&not;", "¬");
        ligne = ligne.replaceAll("&reg;", "®");
        ligne = ligne.replaceAll("&macr;", "¯");
        ligne = ligne.replaceAll("&hibar;", "¯");
        ligne = ligne.replaceAll("&deg;", "°");
        ligne = ligne.replaceAll("&plusmn;", "±");
        ligne = ligne.replaceAll("&sup2;", "²");
        ligne = ligne.replaceAll("&sup3;", "³");
        ligne = ligne.replaceAll("&acute;", "´");
        ligne = ligne.replaceAll("&micro;", "µ");
        ligne = ligne.replaceAll("&para;", "¶");
        ligne = ligne.replaceAll("&middot;", "·");
        ligne = ligne.replaceAll("&cedil;", "¸");
        ligne = ligne.replaceAll("&sup1;", "¹");
        ligne = ligne.replaceAll("&ordm;", "º");
        ligne = ligne.replaceAll("&raquo;", "»");
        ligne = ligne.replaceAll("&frac14;", "¼");
        ligne = ligne.replaceAll("&frac12;", "½");
        ligne = ligne.replaceAll("&frac34;", "¾");
        * */
        ligne = ligne.replaceAll("&iquest;", "¿");
        ligne = ligne.replaceAll("&Agrave;", "À");
        ligne = ligne.replaceAll("&Aacute;", "Á");
        ligne = ligne.replaceAll("&Acirc;", "Â");
        ligne = ligne.replaceAll("&Atilde;", "Ã");
        ligne = ligne.replaceAll("&Auml;", "Ä");
        ligne = ligne.replaceAll("&Aring;", "Å");
        ligne = ligne.replaceAll("&AElig;", "Æ");
        ligne = ligne.replaceAll("&Ccedil;", "Ç");
        ligne = ligne.replaceAll("&Egrave;", "È");
        ligne = ligne.replaceAll("&Eacute;", "É");
        ligne = ligne.replaceAll("&Ecirc;", "Ê");
        ligne = ligne.replaceAll("&Euml;", "Ë");
        ligne = ligne.replaceAll("&Igrave;", "Ì");
        ligne = ligne.replaceAll("&Iacute;", "Í");
        ligne = ligne.replaceAll("&Icirc;", "Î");
        ligne = ligne.replaceAll("&Iuml;", "Ï");
        ligne = ligne.replaceAll("&ETH;", "Ð");
        ligne = ligne.replaceAll("&Dstrok;", "Ð");
        ligne = ligne.replaceAll("&Ntilde;", "Ñ");
        ligne = ligne.replaceAll("&Ograve;", "Ò");
        ligne = ligne.replaceAll("&Oacute;", "Ó");
        ligne = ligne.replaceAll("&Ocirc;", "Ô");
        ligne = ligne.replaceAll("&Otilde;", "Õ");
        ligne = ligne.replaceAll("&Ouml;", "Ö");
        ligne = ligne.replaceAll("&times;", "×");
        ligne = ligne.replaceAll("&Oslash;", "Ø");
        ligne = ligne.replaceAll("&Ugrave;", "Ù");
        ligne = ligne.replaceAll("&Uacute;", "Ú");
        ligne = ligne.replaceAll("&Ucirc;", "Û");
        ligne = ligne.replaceAll("&Uuml;", "Ü");
        ligne = ligne.replaceAll("&Yacute;", "Ý");
        ligne = ligne.replaceAll("&THORN;", "Þ");
        ligne = ligne.replaceAll("&szlig;", "ß");
        ligne = ligne.replaceAll("&agrave;", "à");
        ligne = ligne.replaceAll("&aacute;", "á");
        ligne = ligne.replaceAll("&acirc;", "â");
        ligne = ligne.replaceAll("&atilde;", "ã");
        ligne = ligne.replaceAll("&auml;", "ä");
        ligne = ligne.replaceAll("&aring;", "å");
        ligne = ligne.replaceAll("&aelig;", "æ");
        ligne = ligne.replaceAll("&ccedil;", "ç");
        ligne = ligne.replaceAll("&egrave;", "è");
        ligne = ligne.replaceAll("&eacute;", "é");
        ligne = ligne.replaceAll("&ecirc;", "ê");
        ligne = ligne.replaceAll("&euml;", "ë");
        ligne = ligne.replaceAll("&igrave;", "ì");
        ligne = ligne.replaceAll("&iacute;", "í");
        ligne = ligne.replaceAll("&icirc;", "î");
        ligne = ligne.replaceAll("&iuml;", "ï");
        ligne = ligne.replaceAll("&eth;", "ð");
        ligne = ligne.replaceAll("&ntilde;", "ñ");
        ligne = ligne.replaceAll("&ograve;", "ò");
        ligne = ligne.replaceAll("&oacute;", "ó");
        ligne = ligne.replaceAll("&ocirc;", "ô");
        ligne = ligne.replaceAll("&otilde;", "õ");
        ligne = ligne.replaceAll("&ouml;", "ö");
        ligne = ligne.replaceAll("&divide;", "÷");
        ligne = ligne.replaceAll("&oslash;", "ø");
        ligne = ligne.replaceAll("&ugrave;", "ù");
        ligne = ligne.replaceAll("&uacute;", "ú");
        ligne = ligne.replaceAll("&ucirc;", "û");
        ligne = ligne.replaceAll("&uuml;", "ü");
        ligne = ligne.replaceAll("&yacute;", "ý");
        ligne = ligne.replaceAll("&thorn;", "þ");
        ligne = ligne.replaceAll("&yuml;", "ÿ");
        
        
        
        
        ligne = ligne.replaceAll("&dagger;", " ");
        ligne = ligne.replaceAll("&Dagger;", " ");
        ligne = ligne.replaceAll("&lt;", " ");
        ligne = ligne.replaceAll("&gt;", " ");
        ligne = ligne.replaceAll("&oelig;", " ");
        ligne = ligne.replaceAll("&Yuml;", " ");
        ligne = ligne.replaceAll("&nbsp;", " ");
        ligne = ligne.replaceAll("&iexcl;", " ");
        ligne = ligne.replaceAll("&cent;", " ");
        ligne = ligne.replaceAll("&pound;", " ");
        ligne = ligne.replaceAll("&curren;", " ");
        ligne = ligne.replaceAll("&yen;", " ");
        ligne = ligne.replaceAll("&sect;", " ");
        ligne = ligne.replaceAll("&uml;", " ");
        ligne = ligne.replaceAll("&die;", " ");
        ligne = ligne.replaceAll("&copy;", " ");
        ligne = ligne.replaceAll("&ordf;", " ");
        ligne = ligne.replaceAll("&laquo;", " ");
        ligne = ligne.replaceAll("&not;", " ");
        ligne = ligne.replaceAll("&reg;", " ");
        ligne = ligne.replaceAll("&macr;", " ");
        ligne = ligne.replaceAll("&hibar;", " ");
        ligne = ligne.replaceAll("&deg;", " ");
        ligne = ligne.replaceAll("&plusmn;", " ");
        ligne = ligne.replaceAll("&sup2;", " ");
        ligne = ligne.replaceAll("&sup3;", " ");
        ligne = ligne.replaceAll("&acute;", " ");
        ligne = ligne.replaceAll("&micro;", " ");
        ligne = ligne.replaceAll("&para;", " ");
        ligne = ligne.replaceAll("&middot;", " ");
        ligne = ligne.replaceAll("&cedil;", " ");
        ligne = ligne.replaceAll("&sup1;", " ");
        ligne = ligne.replaceAll("&ordm;", " ");
        ligne = ligne.replaceAll("&raquo;", " ");
        ligne = ligne.replaceAll("&frac14;", " ");
        ligne = ligne.replaceAll("&frac12;", " ");
        ligne = ligne.replaceAll("&frac34;", " ");
        
        
        
        
        ligne = ligne.replaceAll("†"," ");
        ligne = ligne.replaceAll("‡"," ");
        ligne = ligne.replaceAll("‹"," ");
        ligne = ligne.replaceAll("›"," ");
        ligne = ligne.replaceAll("œ"," ");
        ligne = ligne.replaceAll("Ÿ"," ");
        ligne = ligne.replaceAll(" "," ");
        ligne = ligne.replaceAll("¡"," ");
        ligne = ligne.replaceAll("¢"," ");
        ligne = ligne.replaceAll("£"," ");
        ligne = ligne.replaceAll("¤"," ");
        ligne = ligne.replaceAll("¥"," ");
        ligne = ligne.replaceAll("§"," ");
        ligne = ligne.replaceAll("¨"," ");
        ligne = ligne.replaceAll("¨"," ");
        ligne = ligne.replaceAll("©"," ");
        ligne = ligne.replaceAll("ª"," ");
        ligne = ligne.replaceAll("«"," ");
        ligne = ligne.replaceAll("¬"," ");
        ligne = ligne.replaceAll("®"," ");
        ligne = ligne.replaceAll("¯"," ");
        ligne = ligne.replaceAll("¯"," ");
        ligne = ligne.replaceAll("°"," ");
        ligne = ligne.replaceAll("±"," ");
        ligne = ligne.replaceAll("²"," ");
        ligne = ligne.replaceAll("³"," ");
        ligne = ligne.replaceAll("´"," ");
        ligne = ligne.replaceAll("µ"," ");
        ligne = ligne.replaceAll("¶"," ");
        ligne = ligne.replaceAll("·"," ");
        ligne = ligne.replaceAll("¸"," ");
        ligne = ligne.replaceAll("¹"," ");
        ligne = ligne.replaceAll("º"," ");
        ligne = ligne.replaceAll("¼"," ");
        ligne = ligne.replaceAll("½"," ");
        ligne = ligne.replaceAll("¾"," ");
        
        ligne = ligne.replaceAll("€“", "");
        ligne = ligne.replaceAll("€”", "");
        ligne = ligne.replaceAll("€™", "");
        ligne = ligne.replaceAll("€œ", "");
        ligne = ligne.replaceAll("€¨", "");
        ligne = ligne.replaceAll("€�", "");
        ligne = ligne.replaceAll("�", " ");
        ligne = ligne.replaceAll("�", " ");
        ligne = ligne.replaceAll("≥", ""); 
        ligne = ligne.replaceAll("≤", "");
        
        ligne = ligne.replaceAll("€", "");
        
        ligne = ligne.replaceAll("\\*", " ");
        ligne = ligne.replaceAll("/", " ");
        
        ligne = ligne.replaceAll("☼", " ");
        ligne = ligne.replaceAll("و", " ");
        ligne = ligne.replaceAll("\\|", " ");
        ligne = ligne.replaceAll("@", " ");
        ligne = ligne.replaceAll("#", " ");
        ligne = ligne.replaceAll(">", " ");
        ligne = ligne.replaceAll("<", " ");
        ligne = ligne.replaceAll(" + ", " ");
        
        if(language.equalsIgnoreCase("french")){
            ligne = ligne.replaceAll("%", "pour cent");
        }else{
            if(language.equalsIgnoreCase("english")){
                ligne = ligne.replaceAll("%", "percent");
            }
        }
        
        return ligne.trim();

    }
}
