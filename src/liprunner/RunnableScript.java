package liprunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RunnableScript {
private static class EmptyScript extends RunnableScript{
public void run(Operations o){}
}
private static class SingleLineScript extends RunnableScript{
private String script;
protected SingleLineScript(String s) {
this.script=s;
}
public void run(Operations o) {
String str=new String(script);
str=Strings.trim(str);
if(str.startsWith("delete")) {
str=str.substring(6,str.length()-1);
Map<String,Object> args=o.getValues();
if(args.containsKey(str))args.remove(str);
o.setValues(args);
}
if(str.startsWith("invoke")) {
str=str.substring(7,str.length()-2);
List<Object> args=new ArrayList<>();
String methodname=new String();
String[] invokeargs=Strings.split(str,",");
for(int i=0;i<invokeargs.length;i++) {
if(i>0) {
args.add(Strings.calc(invokeargs[i],Strings.prepareCalc(str,o.getValues(),o)));
}else {
methodname=(String)Strings.calc(invokeargs[i],Strings.prepareCalc(str,o.getValues(),o));
}
}
o.invoke(methodname,args.toArray());
}
if(str.startsWith("new")) {
str=str.substring(3,str.length()-1);
if(str.startsWith("int")) {
str=str.substring(3);
if(Strings.vaildNameCheck(Strings.split(str,"=")[0])) {
o.putValue(Strings.split(str,"=")[0],(Integer)Strings.calc("("+Strings.split(str,"=",1)[1]+")",Strings.prepareCalc(Strings.split(str,"=",1)[1],o.getValues(),o)));
}
}
if(str.startsWith("float")) {
str=str.substring(5);
if(Strings.vaildNameCheck(Strings.split(str,"=")[0])) {
o.putValue(Strings.split(str,"=")[0],(Float)Strings.calc("("+Strings.split(str,"=",1)[1]+")",Strings.prepareCalc(Strings.split(str,"=",1)[1],o.getValues(),o)));
}
}
if(str.startsWith("boolean")) {
str=str.substring(7);
if(Strings.vaildNameCheck(Strings.split(str,"=")[0])) {
o.putValue(Strings.split(str,"=")[0],(Boolean)Strings.calc("("+Strings.split(str,"=",1)[1]+")",Strings.prepareCalc(Strings.split(str,"=",1)[1],o.getValues(),o)));
}
}
if(str.startsWith("string")) {
str=str.substring(6);
if(Strings.vaildNameCheck(Strings.split(str,"=")[0])) {
o.putValue(Strings.split(str,"=")[0],(String)Strings.calc("(\""+Strings.split(script+" ","\"",2)[1]+"\")",Strings.prepareCalc(Strings.split(script,"=",1)[1],o.getValues(),o)));
}
}
}
if(str.startsWith("set")) {
Map<String,Object> args=o.getValues();
Object obj=Strings.calc("("+Strings.split(str,"=",1)[1].substring(0,Strings.split(str,"=",1)[1].length()-1)+")",Strings.prepareCalc(Strings.split(str,"=",1)[1].substring(0,Strings.split(str,"=",1)[1].length()-1),o.getValues(),o));
args.remove(Strings.split(str.substring(3),"=")[0]);
args.put(Strings.split(str.substring(3),"=")[0],obj);
o.setValues(args);
}
}
}
private static class ScriptBlock extends RunnableScript{
private static enum ScriptBlockType{
SCRIPT,WHILE,IF
}
private ScriptBlockType script_type;
private String bool;
private Map<String,Object> args=new HashMap<>();
private RunnableScript[] script;
protected ScriptBlock(String s) {
if(s.trim().startsWith("{")) {
script_type=ScriptBlockType.SCRIPT;
}else if(s.trim().startsWith("while")) {
script_type=ScriptBlockType.WHILE;
String tmp=Strings.split(s.trim().substring(5),"{")[0];
bool=tmp;
}else if(s.trim().startsWith("if")) {
script_type=ScriptBlockType.IF;
String tmp=Strings.split(s.trim().substring(2),"{")[0];
bool=tmp;
}
String tmp=Strings.split(s,"{",1)[1];
String[] tmparr=Strings.split(tmp+" ","}");
tmp=new String();
for(int i=0;i<tmparr.length-1;i++) {
tmp+=tmparr[i]+"}";
}
tmp=tmp.substring(0,tmp.length()-1);
String[] scriptString=Strings.split(tmp+" ",";");
List<RunnableScript> scriptlist=new ArrayList<>();
int count1=0;
int count2=0;
String addstr=new String();
for(int i=0;i<scriptString.length;i++) {
count1+=Strings.countChar(scriptString[i],'{');
count2+=Strings.countChar(scriptString[i],'}');
if(count1==count2) {
scriptlist.add(RunnableScript.getScript(addstr+scriptString[i]+";"));
addstr=new String();
}else {
addstr=scriptString[i];
}
}
script=new RunnableScript[scriptlist.size()];
for(int i=0;i<scriptlist.size();i++) {
script[i]=scriptlist.get(i);
}
}
public void run(Operations o) {
args.clear();
args.putAll(o.getValues());
Operations o2=new Operations() {
public Object getArgument(String s) {
return o.getArgument(s);
}
public Object invoke(String methodname, Object[] args) {
return o.invoke(methodname,args);
}
public Map<String, Object> getValues() {
return args;
}
public void setValues(Map<String, Object> values) {
o.setValues(values);
args=values;
}
public void putValue(String key, Object value) {
args.put(key,value);
}
};
if(this.script_type==ScriptBlockType.SCRIPT||(this.script_type==ScriptBlockType.IF&&(Boolean)Strings.calc(bool,Strings.prepareCalc(bool,args,o2))==true)) {
for(int i=0;i<this.script.length;i++) {
this.script[i].run(o2);
}
}else if(this.script_type==ScriptBlockType.WHILE) {
while((Boolean)Strings.calc(bool,Strings.prepareCalc(bool,args,o2))==true){
for(int i=0;i<this.script.length;i++) {
this.script[i].run(o2);
}
}
}
}
}
public abstract void run(Operations o);
public static RunnableScript getScript(String s) {
if(s.trim().equals(";")) {
return empty();
}
if((s.startsWith("{")||s.startsWith("if")||s.startsWith("while"))&&s.endsWith("};")) {
return new ScriptBlock(s);
}
if(Strings.countChar(s,';')==1) {
return new SingleLineScript(s);
}
return empty();
}
public static EmptyScript empty() {
return new EmptyScript();
}
}
