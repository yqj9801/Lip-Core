package liprunner;
import java.util.HashMap;
import java.util.Map;
public class Collections {
public static class TwoObject<A,B>{
public A a;
public B b;
public TwoObject(A a,B b) {
this.a=a;
this.b=b;
}
}
public static <A,B> HashMap<A,B> fill(HashMap<A,B> map,TwoObject<A,B>...objects) {
map.clear();
for(int i=0;i<objects.length;i++) {
map.put(objects[i].a,objects[i].b);
}
return map;
}
public static <A,B> TwoObject<A,B> aPairOf(A a,B b){
return new TwoObject<A,B>(a,b);
}
public static <K,V> void print(Map<K,V> map,java.io.PrintStream stream) {
for(K x:map.keySet()) {
stream.println(x.toString()+";"+map.get(x).toString());
}
}
public static <V> void print(V[] arr,java.io.PrintStream stream) {
for(V x:arr) {
System.out.println(x);
}
}
}
