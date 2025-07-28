package liprunner;

public class LipScriptException extends Exception{
public LipScriptException(String s) {
new Exception(s);
}
public LipScriptException() {
new Exception("Ooops!A Unknown Exception!");
}
}
