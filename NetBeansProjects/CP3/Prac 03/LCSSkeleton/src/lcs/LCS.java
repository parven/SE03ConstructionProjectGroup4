/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lcs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author lewi0146
 */
public class LCS {

    private String x;
    private String y;
    ArrayList<String> list = new ArrayList<>();
    int flag1 = 0;
    int flag2 =0;
    
    private int[][]L ;
    
    public LCS(String x, String y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int[][] calculateMatrix()
    {   L = new int[x.length()+2][y.length()+2];
        char[] p = y.toCharArray();
        int a=0;
        char[] t = x.toCharArray();
        int b=0;
        for(int i=0; i<L.length;i++){
            for(int j=0; j<L[i].length;j++){
                if(i==0 && j==0){
                    L[i][j] = ' ';
                }
                else if(i==0 && j==1){
                    L[i][j]= '-';
                }
                else if(i==0 && j>=2){
                    L[i][j]= p[a];
                    a++;
                }
                else if(i==1 && j==0){
                    L[i][j]= '-';
                }
                else if(i>=2 && j==0){
                    L[i][j]= t[b];
                    b++;
                }
                else if(i==1 && j>=1){
                    L[i][j] = 0;
                } 
                else if(i>=1 && j==1){
                    L[i][j] = 0;
                }
                else
                    break;
                
            }     
        }
        
        int k =0;
        for(int i=2; i<L.length;i++){
            int l = 0;
            for(int j=2; j<L[i].length;j++){
                
                if(x.charAt(k)==y.charAt(l)){
                    L[i][j] = L[i-1][j-1] + 1;
                }
                else{
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                    
                }
                l++;
            }
            k++;
        }
        return L;
    }
    
    public void printMatrix()
    {
        if (L != null)
        {
            for (int i = 0; i < L.length; i++)
            {
                for (int j = 0; j < L[i].length; j++)
                {
                    if(i==0 && j==0){
                        System.out.print(" " + (char)L[i][j]);
                    }
                    else if(i==0 && j==1){
                        System.out.print(" " + (char)L[i][j]);
                    }
                    else if(i==0 && j>=2){
                        System.out.print(" " + (char)L[i][j]);
                    }
                    else if(i==1 && j==0){
                        System.out.print(" " + (char)L[i][j]);
                    }
                    else if(i>=2 && j==0){
                        System.out.print(" " + (char)L[i][j]);
                    }
                    else
                        System.out.print(" " + L[i][j]);
                 }
                System.out.println();
            }
        }
    }
    
    public int getLongestLength()
    {
        int[] l = L[L.length-1];
        return l[l.length-1];
    }
    
    public String getLCS()
    {
        StringBuilder sb = new StringBuilder();
        int k =x.length()-1;
        int l = y.length()-1;
        for(int i=L.length-1; i>=2;i--){
            for(int j=L[i].length-1; j>=2;j--){
                
                if(i<2 || j<2){
                    break;
                }else if(x.charAt(i-2) == y.charAt(j-2)){
                    sb.append(x.charAt(i-2));
                    i--;
                    //k--;
                    //l--;
                    
                }
                else{
                    if(L[i][j-1]>L[i-1][j]){
                        //l--;
                    }
                    else{
                        i--;
                        j++;
                        //k--;
                    }    
                }
            }
        }
        return sb.reverse().toString();
    }
    
    public Collection<String> getAllLCS(int i, int j)
    {
        
                if(i<2 || j<2){
                    Collection<String> set1 = new HashSet();
                    set1.add("");
                    return set1;
                }else if(x.charAt(i-2) == y.charAt(j-2)){
                    Collection<String> set1 ;
                    set1 = getAllLCS(i-1,j-1);
                    Collection<String> set2 = new HashSet();
                    for(String s : set1){
                        s = s+x.charAt(i-2);
                        set2.add(s);
                    }
                    //System.out.println(set2);
                    return set2;
                    
                }
                else{
                        Collection<String> set1 = new HashSet();
                        Collection<String> set2 = new HashSet();
                        if(L[i][j-1]>=L[i-1][j]){
                            set1 = getAllLCS(i,j-1);
                            //l--;
                        }
                        if(L[i-1][j]>=L[i][j-1]){
                            set2 = getAllLCS(i-1,j);
                            
                        }  
                        for(String s : set1){
                            set2.add(s);
                        }
                    //System.out.println(set2);
                    return set2; 
                }
                  
    }
    

   
   
}
