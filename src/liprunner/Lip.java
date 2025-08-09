package liprunner;

import java.util.HashMap;
import java.util.Map;

public class Lip{
protected static class LipOperation implements Operations{
private Map<String,MethodInfo> methods=new HashMap<>();
private Map<String,Object> args=new HashMap<>();
public LipOperation(MethodInfo[] methods) {
for(int i=0;i<methods.length;i++) {
this.methods.put(methods[i].name,methods[i]);
}
}
public Object getArgument(String s) {
return null;
}
public Object invoke(String methodname, Object[] args) {
return methods.get(methodname).invoke(args);
}
public Map<String, Object> getValues() {
return args;
}
public void setValues(Map<String, Object> values) {
args.clear();
args.putAll(values);
}
public void putValue(String key, Object value) {
args.put(key,value);
}
}
public static Exception run(String script,MethodInfo[] methods) {
try {
MethodInfo[] tmp=new MethodInfo[methods.length+BasicMethods.basicmethods.length];
System.arraycopy(methods,0,tmp,0,methods.length);
System.arraycopy(BasicMethods.basicmethods,0,tmp,methods.length,BasicMethods.basicmethods.length);
RunnableScript.getScript(script).run(new LipOperation(tmp));
return null;
}catch(Exception e) {
return e;
}
}
}
