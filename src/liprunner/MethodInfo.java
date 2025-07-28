package liprunner;

public abstract class MethodInfo {
protected String name;
public MethodInfo(String name) {
this.name=name;
}
public abstract Object invoke(Object[] args);
}
