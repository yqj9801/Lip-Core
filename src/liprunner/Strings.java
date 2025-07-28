package liprunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Strings {
public static int argindex=0;
public static String removeFirstSpace(String s) {
String result=new String(s);
for(int i=0;i<result.length();i++) {
if(result.charAt(i)<=' ') {
result=result.substring(1);
i-=1;
}else {
break;
}
}
return result;
}
public static String removeLastSpace(String s) {
String result=new String(s);
for(int i=result.length()-1;i>=0;i--) {
if(result.charAt(i)<=' ') {
result=result.substring(0,result.length()-1);
}else {
break;
}
}
return result;
}
public static int countChar(String s,char c) {
int count=0;
for(int i=0;i<s.length();i++) {
if(s.charAt(i)==c) {
count+=1;
}
}
return count;
}
public static int countString(String s,String s2) {
int count=0;
for(int i=s2.length();i<=s.length();i++) {
if(s.substring(i-s2.length(),i).equals(s2)) {
count+=1;
}
}
return count;
}
public static String[] split(String s,String s2) {
String str=new String(s);
String[] result=new String[countString(s,s2)+1];
int index=0;
for(int i=0;i<str.length()-s2.length();i++) {
if(s2.equals(str.substring(i,i+s2.length()))) {
result[index]=str.substring(0,i);
str=str.substring(i+s2.length());
i=0;
index+=1;
}
}
result[index]=str;
return result;
}
public static String[] split(String s,String s2,int count) {
if(count==0) {
return new String[] {s};
}
String str=new String(s);
String[] result=new String[count+1];
int index=0;
for(int i=0;i<str.length()-s2.length();i++) {
if(s2.equals(str.substring(i,i+s2.length()))) {
result[index]=str.substring(0,i);
str=str.substring(i+s2.length());
i=0;
index+=1;
if(index>=count) {
result[index]=str;
return result;
}
}
}
result[index]=str;
return result;
}
public static boolean vaildNameCheck(String s) {
int first=s.toCharArray()[0];
if((first>=65&&first<=90)||(first>=97&&first<=122)||first==95) {
if(s.matches("\\w")) {
return true;
}
}
return false;
}
public static String trim(String str) {
String s=str;
for(int i=0;i<s.length();i++) {
if(s.charAt(i)==' '||s.charAt(i)=='\n'||s.charAt(i)=='\t') {
s=s.substring(0,i)+s.substring(i+1);
i-=1;
}
}
return s;
}
public static boolean isDigit(char c) {
return c=='0'||c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9';
}
private static Object calcOne(String s,Map<String,Object> args) {
if(countString(s,"||")==1) {
return ((Boolean)args.get(split(s,"||")[0]))||((Boolean)args.get(split(s,"||")[1]));
}
if(countString(s,"&&")==1) {
return ((Boolean)args.get(split(s,"&&")[0]))&&((Boolean)args.get(split(s,"&&")[1]));
}
if(countChar(s,'!')==1) {
return !((Boolean)args.get(s.substring(1)));
}
if(countString(s,"==")==1) {
	if((args.get(split(s,"==")[0]) instanceof Float)||(args.get(split(s,"==")[1]) instanceof Float)){
		return ((Float)args.get(split(s,"==")[0]))==((Float)args.get(split(s,"==")[1]));
		}
		if((args.get(split(s,"==")[0]) instanceof Integer)||(args.get(split(s,"==")[1]) instanceof Integer)){
		return ((Integer)args.get(split(s,"==")[0]))==((Integer)args.get(split(s,"==")[1]));
		}
}
if(countString(s,">=")==1) {
if((args.get(split(s,">=")[0]) instanceof Float)||(args.get(split(s,">=")[1]) instanceof Float)){
return ((Float)args.get(split(s,">=")[0]))>=((Float)args.get(split(s,">=")[1]));
}
if((args.get(split(s,">=")[0]) instanceof Integer)||(args.get(split(s,">=")[1]) instanceof Integer)){
return ((Integer)args.get(split(s,">=")[0]))>=((Integer)args.get(split(s,">=")[1]));
}
}
if(countString(s,"<=")==1) {
if((args.get(split(s,"<=")[0]) instanceof Float)||(args.get(split(s,"<=")[1]) instanceof Float)){
return ((Float)args.get(split(s,"<=")[0]))<=((Float)args.get(split(s,"<=")[1]));
}
if((args.get(split(s,"<=")[0]) instanceof Integer)||(args.get(split(s,"<=")[1]) instanceof Integer)){
return ((Integer)args.get(split(s,"<=")[0]))<=((Integer)args.get(split(s,"<=")[1]));
}
}
if(countChar(s,'>')==1&&!split(s,">")[1].startsWith("=")) {
if((args.get(split(s,">")[0]) instanceof Float)||(args.get(split(s,">")[1]) instanceof Float)){
return ((Float)args.get(split(s,">")[0]))>((Float)args.get(split(s,">")[1]));
}
if((args.get(split(s,">")[0]) instanceof Integer)||(args.get(split(s,">")[1]) instanceof Integer)){
return ((Integer)args.get(split(s,">")[0]))>((Integer)args.get(split(s,">")[1]));
}
}
if(countChar(s,'<')==1&&!split(s,"<")[1].startsWith("=")) {
if((args.get(split(s,"<")[0]) instanceof Float)||(args.get(split(s,"<")[1]) instanceof Float)){
return ((Float)args.get(split(s,"<")[0]))<((Float)args.get(split(s,"<")[1]));
}
if((args.get(split(s,"<")[0]) instanceof Integer)||(args.get(split(s,"<")[1]) instanceof Integer)){
return ((Integer)args.get(split(s,"<")[0]))<((Integer)args.get(split(s,"<")[1]));
}
}
if(countChar(s,'+')==1) {
if((args.get(split(s,"+")[0]) instanceof Float)||(args.get(split(s,"+")[1]) instanceof Float)){
return ((Float)args.get(split(s,"+")[0]))+((Float)args.get(split(s,"+")[1]));
}
if((args.get(split(s,"+")[0]) instanceof Integer)&&(args.get(split(s,"+")[1]) instanceof Integer)){
return ((Integer)args.get(split(s,"+")[0]))+((Integer)args.get(split(s,"+")[1]));
}
if((args.get(split(s,"+")[0]) instanceof String)&&(args.get(split(s,"+")[1]) instanceof String)){
return ((String)args.get(split(s,"+")[0]))+((String)args.get(split(s,"+")[1]));
}
}
if(countChar(s,'-')==1) {
if((args.get(split(s,"-")[0]) instanceof Float)||(args.get(split(s,"-")[1]) instanceof Float)){
return ((Float)args.get(split(s,"-")[0]))-((Float)args.get(split(s,"-")[1]));
}
if((args.get(split(s,"-")[0]) instanceof Integer)&&(args.get(split(s,"-")[1]) instanceof Integer)){
return ((Integer)args.get(split(s,"-")[0]))-((Integer)args.get(split(s,"-")[1]));
}
}
if(countChar(s,'*')==1) {
if((args.get(split(s,"*")[0]) instanceof Float)||(args.get(split(s,"*")[1]) instanceof Float)){
return ((Float)args.get(split(s,"*")[0]))*((Float)args.get(split(s,"*")[1]));
}
if((args.get(split(s,"*")[0]) instanceof Integer)&&(args.get(split(s,"*")[1]) instanceof Integer)){
return ((Integer)args.get(split(s,"*")[0]))*((Integer)args.get(split(s,"*")[1]));
}
}
if(countChar(s,'/')==1) {
if((args.get(split(s,"/")[0]) instanceof Float)||(args.get(split(s,"/")[1]) instanceof Float)){
return ((Float)args.get(split(s,"/")[0]))/((Float)args.get(split(s,"/")[1]));
}
if((args.get(split(s,"/")[0]) instanceof Integer)&&(args.get(split(s,"/")[1]) instanceof Integer)){
return ((Integer)args.get(split(s,"/")[0]))/((Integer)args.get(split(s,"/")[1]));
}
}
return null;
}
public static String fixString(String s) {
String str=new String();
for(int i=0;i<s.length();i++) {
if(s.charAt(i)=='\\'&&i<=s.length()-5&&s.charAt(i+1)=='c') {
char c=(char)Integer.parseInt(s.substring(i+2,i+5));
str=str+c;
i+=4;
}else {
str=str+s.charAt(i);
}
}
return str;
}
public static Map<String,Object> prepareCalc(String s,Map<String,Object> args,Operations o){
String str=s+" ";
Map<String,Object> newargs=new HashMap<>();
newargs.putAll(args);
int strindex=-1;
int digindex=-1;
for(int i=0;i<str.length();i++) {
if(isDigit(str.charAt(i))) {
if(digindex==-1)digindex=i;
}else{
if(digindex!=-1) {
float f=Float.parseFloat(str.substring(digindex,i));
if(countChar(str.substring(digindex,i),'.')==0) {
newargs.put(str.substring(digindex,i),(int)f);
}else {
newargs.put(str.substring(digindex,i),f);
}
digindex=-1;
}
}
if(str.charAt(i)=='\"') {
if(strindex==-1) {
strindex=i;
}else {
newargs.put(str.substring(strindex,i+1),fixString(str.substring(strindex+1,i)));
strindex=-1;
}
}
if((str.length()-6)>i&&str.substring(i,i+6).equals("invoke")) {
String invokestr=str.substring(i+6).trim();
int count=1;
List<Object> argslist=new ArrayList<>();
String bs=new String();
for(int i2=1;count!=0;i2++) {
if(invokestr.charAt(i2)=='(') {
count+=1;
}else if(invokestr.charAt(i2)==')') {
count-=1;
}else if(invokestr.charAt(i2)==',') {
argslist.add(calc(bs,prepareCalc(bs,args,o)));
bs=new String();
}else {
bs+=invokestr.charAt(i2);
}
}
argslist.add(calc(bs,prepareCalc(bs,args,o)));
bs=new String();
int count2=0;
boolean count2changed=false;
String invokefullstr=new String();
for(int i2=i;count2!=0||(!count2changed);i2++) {
if(s.charAt(i2)=='(') {
count2+=1;
count2changed=true;
}
if(s.charAt(i2)==')') {
count2-=1;
count2changed=true;
}
invokefullstr+=s.charAt(i2);
}
String methodname=(String)argslist.get(0);
argslist.remove(0);
newargs.put(invokefullstr,o.invoke(methodname,argslist.toArray()));
}
}
newargs.put("true",true);
newargs.put("false",false);
return newargs;
}
public static Object calc(String str,Map<String,Object> args) {
String s=new String(str);
Iterator<String> it=args.keySet().iterator();
int invokeindex=0;
Map<String,Object> tmpargs=new HashMap<>();
tmpargs.putAll(args);
while(it.hasNext()) {
String keystr=it.next();
if(keystr.startsWith("invoke")) {
Object value=args.get(keystr);
tmpargs.remove(keystr);
tmpargs.put("#$"+invokeindex,value);
s=s.replace(keystr,"#$"+invokeindex);
invokeindex+=1;
}
}
args=tmpargs;
if((countChar(s,'(')==0&&countChar(s,')')==0)||(s.startsWith("(")&&s.endsWith(")"))) {
if(s.startsWith("(")&&s.endsWith(")")&&countChar(s,'(')==1&&countChar(s,')')==1) {
s=s.substring(1,s.length()-1);
}
int count=countString(s,"||")+countString(s,"&&")+countChar(s,'!')+countString(s,"==")+countChar(s,'>')+countChar(s,'<')+countChar(s,'+')+countChar(s,'-')+countChar(s,'*')+countChar(s,'/');
if(s.length()>6&&s.substring(1,s.length()-1).startsWith("invoke")) {
return args.get(s.substring(1,s.length()-1));
}
if(count==0) {
return args.get(s);
}else if(count==1) {
return calcOne(s,args);
}else {
if(countChar(s,'(')==0&&countChar(s,')')==0) {
for(int i=0;i<count;i++) {
s="("+s;
}
for(int i=count;i<s.length();i++) {
char c=s.charAt(i);
String cs=s.substring(i-1,i+1);
if(c=='+'||c=='-'||c=='*'||c=='/'||c=='>'||c=='<'||c=='!') {
s=s.substring(0,i)+")"+s.substring(i);
i+=1;
}
if(cs.equals(">=")||cs.equals("<=")||cs.equals("==")||cs.equals("!=")||cs.equals("||")||cs.equals("&&")) {
s=s.substring(0,i-1)+")"+s.substring(i-1);
i+=1;
}
}
}
List<Integer> l=new ArrayList<>();
s="("+s+")";
for(int i=0;i<s.length();i++) {
if(s.charAt(i)=='(') {
l.add(i);
}
if(s.charAt(i)==')') {
Object obj=calc(s.substring(l.get(l.size()-1)+1,i),args);
String news=s.substring(0,Math.max(l.get(l.size()-1),0))+"#$"+argindex+s.substring(i+1);
args.put("#$"+argindex,obj);
i=i-(s.length()-news.length());
s=news;
l.remove(l.size()-1);
argindex+=1;
}
}
return args.get(s);
}
}
return null;
}
}
