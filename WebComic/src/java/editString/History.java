/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package editString;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import dal.StoryDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Chapter;
import model.Story;

/**
 *
 * @author HP
 */
public class History {
    public LinkedHashMap<Story,Chapter> getHistory(String txt) {
        StoryDAO d = new  StoryDAO();
         LinkedHashMap<Story,Chapter> linkedHashMap=new LinkedHashMap<>();

             
       
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("/");
                reverseArray(s);
               
                for (String i : s) {
                    String[] n = i.split(":");
                   
                    
                  String id_raw = n[0];
                  int id = Integer.parseInt(id_raw);
                    int chapid = Integer.parseInt(n[1]);
                    Chapter chap = d.getChapbyId(chapid);
                    Story sa = d.getStoriesbySID(id);
                    if(sa!=null){
                          linkedHashMap.put(sa, chap );
                        }
                    
                    
                  
                    
                }
            }
        } catch (NumberFormatException e) {
        }
        return linkedHashMap;
    }
//    public LinkedHashMap<Story,Chapter> getHistory(String txt) {
//    StoryDAO d = new StoryDAO();
//    LinkedHashMap<Story,Chapter> linkedHashMap = new LinkedHashMap<>();
//    
//    List<Story> list = new ArrayList<>();
//    try {
//        if (txt != null && txt.length() != 0) {
//            String[] s = txt.split("/");
//            reverseArray(s);
//            
//            Map<Integer, String> idMap = new LinkedHashMap<>(); // Track id_raw values
//            
//            for (String i : s) {
//                String[] n = i.split(":");
//
//                String id_raw = n[0];
//                int id = Integer.parseInt(id_raw);
//                int chapid = Integer.parseInt(n[1]);
//                
//                idMap.put(id, id_raw); // Store the latest occurrence of id_raw
//                
//                Chapter chap = d.getChapbyId(chapid);
//                Story sa = d.getStories(id, null, null).get(0);
//                list.add(sa);
//                linkedHashMap.put(sa, chap);
//            }
//            
//            // Remove duplicates and keep the latest occurrence of id_raw
//            Set<Integer> processedIds = new HashSet<>();
//            Iterator<Map.Entry<Integer, String>> iterator = idMap.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<Integer, String> entry = iterator.next();
//                int id = entry.getKey();
//                if (processedIds.contains(id)) {
//                    iterator.remove();
//                } else {
//                    processedIds.add(id);
//                }
//            }
//            
//            // Update linkedHashMap with the filtered entries
//            LinkedHashMap<Story, Chapter> filteredMap = new LinkedHashMap<>();
//            for (Map.Entry<Integer, String> entry : idMap.entrySet()) {
//                int id = entry.getKey();
//                String id_raw = entry.getValue();
//                Story sa = d.getStories(id, null, null).get(0);
//                Chapter chap = linkedHashMap.get(sa);
//                filteredMap.put(sa, chap);
//            }
//            linkedHashMap = filteredMap;
//        }
//    } catch (NumberFormatException e) {
//        // Handle NumberFormatException if required
//    }
//    return linkedHashMap;
//}
   
    public static void reverseArray(String[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            // Hoán đổi giá trị của hai phần tử
            String temp = array[start];
            array[start] = array[end];
            array[end] = temp;

            // Di chuyển về phía trung tâm của mảng
            start++;
            end--;
        }
    }
 
   // Xoa id trung lap trong cookie
    public String processString(String input) {
        String[] elements = input.split("/");
        ArrayList<String> uniqueElements = new ArrayList<>();

        HashSet<String> idSet = new HashSet<>();

        for (int i = elements.length - 1; i >= 0; i--) {
            String element = elements[i];
            String id = element.split(":")[0];

            if (!idSet.contains(id)) {
                uniqueElements.add(0, element);
                idSet.add(id);
            }
        }

        String result = String.join("/", uniqueElements);

        return result;
    }
 // xóa 1 phần tử trong cookie theo id
public  String removeElementsWithId(String txt, String idToRemove) {
        String[] elements = txt.split("/");
        ArrayList<String> filteredElements = new ArrayList<>();

        for (String element : elements) {
            String[] parts = element.split(":");
            String id = parts[0];

            if (!id.equals(idToRemove)) {
                filteredElements.add(element);
            }
        }

        String result = String.join("/", filteredElements);

        return result;
    }
    public static void main(String[] args) {
        History h = new History();
        String s = "1:1/2:3/3:3/1:2";
        System.out.println(h.processString(s));
    }
      
  
    
}



   

     
