import java.util.Comparator;
import java.util.List;

public class CustomComparator implements Comparator<List<String>>{
	@Override
    public int compare(List<String> o1,
        List<String> o2)
    {
        String firstString_o1 = o1.get(2);
        String firstString_o2 = o2.get(2);
        return firstString_o1.compareTo(firstString_o2); 
    }
}
