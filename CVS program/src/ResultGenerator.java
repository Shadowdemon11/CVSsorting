import java.io.*; 
import java.util.*; 
import com.opencsv.CSVWriter;
public class ResultGenerator {
	public String getListAsCsvString(List<String> list){
        
        StringBuilder sb = new StringBuilder();
        for(String str:list){
            if(sb.length() != 0){
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }
	public void addDataToCSV(File file, List<String[]> csvData) 
    { 
        // first create file object for file placed at location 
        // specified by filepath 
        //File file = new File(output); 
        //Scanner sc = new Scanner(System.in); 
        try { 
            // create FileWriter object with file as parameter 
            FileWriter outputfile = new FileWriter(file); 
  
            // create CSVWriter with ';' as separator 
            CSVWriter writer = new CSVWriter(outputfile);
  
            // create a List which contains Data 
            List<String[]> data = csvData; 
  
            //writer.writeAll(data);
  
            writer.writeAll(data); 
  
            // closing writer connection 
            writer.close(); 
            
        } 
        catch (IOException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        } 
    }
}
