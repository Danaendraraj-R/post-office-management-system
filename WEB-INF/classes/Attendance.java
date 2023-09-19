package org.Exportmodules;
import java.util.*;
import java.io.*;
interface Conversion{

String convert(String str,String col);

}
public class Attendance implements Conversion{
Map<String,String>[] hmap;
public void setdata(List<Map<String, String>> mapList)
{
File file = new File("maps.txt");
try (PrintWriter writer = new PrintWriter(file)) {
    for (Map<String, String> map : mapList) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" ");
            sb.append(entry.getValue());
            sb.append(" ");
        }
        writer.println(sb.toString().trim());
    }
} catch (FileNotFoundException e) {
    System.out.println("Error: " + e.getMessage());
}
}
public List getdata()
{
List<Map<String,String>> mapList = new ArrayList<>();
File file = new File("maps.txt");
try (Scanner scanner = new Scanner(file)) {
    while (scanner.hasNextLine()) {
        Map<String, String> map = new HashMap<>();
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        for (int i = 0; i < parts.length; i += 2) {
            String key = parts[i];
            String value =parts[i + 1];
            map.put(key, value);
        }
        mapList.add(map);
    }
} catch (FileNotFoundException e) {
    System.out.println("Error: " + e.getMessage());
} 
return mapList;
}
@Override 
public String convert(String a,String columnname)
{
List<Map<String,String>> mapList=getdata();
Map<String,String> map;
for(int i=0;i<mapList.size();i++)
{
map=mapList.get(i);
for (Map.Entry<String,String> entry : map.entrySet()) {
      if((entry.getKey().equals("columnname"))&&(entry.getValue().equals(columnname)))
            {
             for (Map.Entry<String,String> entry1 : map.entrySet())
             {
             if(entry1.getKey().equals(a))
               a=entry1.getValue();
             }
            }
    }
   
}   
return a;
}
}
