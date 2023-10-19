/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package editString;

import dal.StoryDAO;
import java.util.ArrayList;
import java.util.List;
import model.Story;

/**
 *
 * @author HP
 */
public class Follow {
     public List<Story> getFollowCookie(String txt) {
        StoryDAO d = new  StoryDAO();
         List<Story>  list  = new ArrayList<>();
         

             
       
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("/");
            
               
                for (String i : s) {
                  
                   
                    
                  String id_raw = i;
                  int id = Integer.parseInt(id_raw);
                  
                  
                    Story sa = d.getStoriesbySID(id);
                    
                    list.add(sa);
                 
                    
                }
            }
        } catch (NumberFormatException e) {
        }
        return list;
    }
     
     public  String removesidFollow (String txt, String idToRemove) {
        String[] elements = txt.split("/");
        ArrayList<String> filteredElements = new ArrayList<>();

        for (String element : elements) {
        

            if (!element.equals(idToRemove)) {
                filteredElements.add(element);
            }
        }

        String result = String.join("/", filteredElements);

        return result;
    }
     public boolean isStoryinCfollow (String txt,String sid){
         String[] s = txt.split("/");
         for (String string : s) {
             if(sid.equals(string)){
                 return true;
             }
         }
         return false;
         }
     public static void main(String[] args) {
         Follow f = new Follow();
      String s = "1/2/3/4/5";
      s=f.removesidFollow(s, "1");
       s=f.removesidFollow(s, "2");
         System.out.println(s);
    }
}
