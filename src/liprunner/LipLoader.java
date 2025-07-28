package liprunner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
public class LipLoader {
public static void loadSource(String s) {
String str=s;
List<String> program=Arrays.asList(str.split(";"));
Map<Integer,String> loaded=new HashMap<>();
for(int i=0;i<program.size();i++) {
if(program.get(i).trim().equals("")) {
program.remove(i);
i-=1;
continue;
}
}
List<Integer> whiles=new ArrayList<>();
for(int i=0;i<program.size();i++) {
String line=program.get(i);
line=Strings.removeFirstSpace(line);
line=Strings.removeLastSpace(line);
}
}
}
