package liprunner;

import java.util.Map;
public interface Operations {
public Object getArgument(String s);
public Object invoke(String methodname,Object[] args);
public Map<String,Object> getValues();
public void setValues(Map<String,Object> values);
public void putValue(String key,Object value);
}
