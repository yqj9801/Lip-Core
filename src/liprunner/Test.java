package liprunner;

public class Test {
public static void main(String[] args) {
Lip.run("{new int a=0;\ninvoke(\"system#println\",a);};",new MethodInfo[] {}).printStackTrace();
}
}
