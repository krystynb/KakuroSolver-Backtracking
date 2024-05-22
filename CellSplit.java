/**
 * Author: Lon Smith, Ph.D.
 * 
 * This program is to show several ways to manipulate string and 
 * test for characters and substrings.
 *
 * Note: StringTokenizer, split, delimenters (RegEx), "On Comma", "On backslash", charAt
 */
import java.util.StringTokenizer;

public class CellSplit
{
    public static void main(String[] args)
    {
        String line = "height = 3, width = 3";
        StringTokenizer myTokenizer = new StringTokenizer(line, ",");
        System.out.println(line);

        String token = myTokenizer.nextToken();
        StringTokenizer tokenSplit = new StringTokenizer(token, " = ");
        String buffer = tokenSplit.nextToken();
        int height = Integer.parseInt(tokenSplit.nextToken());
        
        token = myTokenizer.nextToken();
        tokenSplit = new StringTokenizer(token, " = ");
        buffer = tokenSplit.nextToken();
        int width = Integer.parseInt(tokenSplit.nextToken());




        System.out.println(height + width);






        System.out.println("###");
        String txt1 = "9\\ ";
        String txt2 = "\\17 ";
        String txt3 = "32\\15 ";
        String delimt = "\\\\";

        String[] txt1a = txt1.split(delimt);
        System.out.println(txt1);
        for(int i = 0; i < txt1a.length; i++)
        {
            System.out.println(txt1a[i] + "0");
        }
        System.out.println("###");
        String[] txt2a = txt2.split(delimt);
        System.out.println(txt2);
        for(int i = 0; i < txt2a.length; i++)
        {
            System.out.println(txt2a[i]);
        }
        System.out.println("###");
        String[] txt3a = txt3.split(delimt);
        System.out.println(txt3);
        for(int i = 0; i < txt3a.length; i++)
        {
            System.out.println(txt3a[i] + 0);
        }
        System.out.println("###");
        System.out.println(txt1);
        if(txt1.charAt(0)=='\\')
        {
            System.out.println("Right");
        } else
        {
            System.out.println("Top");
        }
        System.out.println("###");
        System.out.println(txt2);
        if(txt2.charAt(0)=='\\')
        {
            System.out.println("Right");
        } else
        {
            System.out.println("Top");
        }
        System.out.println("###");
   }
}
