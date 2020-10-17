import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    final static int RED = 0, GREEN = 1, BLUE = 2;
    public static List<String> mixColors(List<List<Integer>> colors, List<List<Integer>> queries)
    {	
        List<String> results = new ArrayList<>();
     
        Map<Integer, Set<List<Integer>>> rMap = new HashMap<>();
        Map<Integer, Set<List<Integer>>> gMap = new HashMap<>();
        Map<Integer, Set<List<Integer>>> bMap = new HashMap<>();
        
        for(int i = 0; i < colors.size(); i++)
        {
            List<Integer> color = colors.get(i);
        
            if(!rMap.containsKey(color.get(RED))) rMap.put(color.get(RED), new HashSet<List<Integer>>());
            if(!gMap.containsKey(color.get(GREEN))) gMap.put(color.get(GREEN), new HashSet<List<Integer>>());
            if(!bMap.containsKey(color.get(BLUE))) bMap.put(color.get(BLUE), new HashSet<List<Integer>>());
        
            rMap.get(color.get(RED)).add(color);
            gMap.get(color.get(GREEN)).add(color);
            bMap.get(color.get(BLUE)).add(color);
        }
        
        for(int i = 0; i <queries.size(); i++)
        {
            List<Integer> query = queries.get(i);
            String result = mixColor(rMap, gMap, bMap, query);
            results.add(result);
        }
        
        return results;
    }
    
    public static String mixColor(
    Map<Integer, Set<List<Integer>>> rMap,
    Map<Integer, Set<List<Integer>>> gMap,
    Map<Integer, Set<List<Integer>>> bMap,
    List<Integer> query
    )
   
    {
        if(!rMap.containsKey(query.get(RED))) return "NO";
        if(!gMap.containsKey(query.get(GREEN))) return "NO";
        if(!bMap.containsKey(query.get(BLUE))) return "NO";
        
        if(!colorFound(rMap, query, RED, GREEN, BLUE)) return "NO";
        if(!colorFound(gMap, query, GREEN, BLUE, RED)) return "NO";
        if(!colorFound(bMap, query, BLUE, RED, GREEN)) return "NO";
        
        return "YES";
    }
    
    public static boolean colorFound(Map<Integer, Set<List<Integer>>> map,
    List<Integer> query,
    int a, int b, int c)
    
    {
        Set<List<Integer>> set = map.get(query.get(a));
        Iterator<List<Integer>> it = set.iterator();
        while(it.hasNext())
        {
            List<Integer> color = it.next();
            if(color.get(b) <= query.get(b) && color.get(c) <= query.get(c))
                return true;
        }
        return false;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> colors = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            String[] colorsRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> colorsRowItems = new ArrayList<>();

            for (int j = 0; j < 3; j++)
            {
                int colorsItem = Integer.parseInt(colorsRowTempItems[j]);
                colorsRowItems.add(colorsItem);
            }

            colors.add(colorsRowItems);
        }

        List<List<Integer>> queries = new ArrayList<>();

        for (int i = 0; i < q; i++)
	{
            String[] queriesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> queriesRowItems = new ArrayList<>();

            for (int j = 0; j < 3; j++)
	    {
                int queriesItem = Integer.parseInt(queriesRowTempItems[j]);
                queriesRowItems.add(queriesItem);
            }

            queries.add(queriesRowItems);
        }

        List<String> result = Result.mixColors(colors, queries);

        for (int i = 0; i < result.size(); i++)
	{
            bufferedWriter.write(result.get(i));

            if (i != result.size() - 1) 
                bufferedWriter.write("\n");
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

