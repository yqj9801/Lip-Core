package liprunner;

public class BasicMethods {
public final static MethodInfo[] basicmethods=new MethodInfo[] {
new MethodInfo("math#sqrt") {
public Object invoke(Object[] args) {
return Math.sqrt((double)args[0]);
}
},
new MethodInfo("math#int_abs") {
public Object invoke(Object[] args) {
return Math.abs((int)args[0]);
}
},
new MethodInfo("math#float_abs") {
public Object invoke(Object[] args) {
return Math.abs((float)args[0]);
}
},
new MethodInfo("math#random") {
public Object invoke(Object[] args) {
return Math.random();
}
},
new MethodInfo("math#convert") {
public Object invoke(Object[] args) {
if(args[1].equals("int")) {
if(args[2].equals("float")){
return (float)args[0];
}
if(args[2].equals("string")){
return args[0].toString();
}
}
if(args[1].equals("float")){
if(args[2].equals("int")){
return (int)args[0];
}
if(args[2].equals("string")){
return args[0].toString();
}
}
if(args[1].equals("string")) {
if(args[2].equals("int")){
return Integer.parseInt((String)args[0]);
}
if(args[2].equals("float")){
return Float.parseFloat((String)args[0]);
}
}
return 0;
}
},
new MethodInfo("system#print") {
public Object invoke(Object[] args) {
System.out.print(args[0]);
return 0;
}
},
new MethodInfo("system#println") {
public Object invoke(Object[] args) {
System.out.println(args[0]);
return 0;
}
}
};
}
