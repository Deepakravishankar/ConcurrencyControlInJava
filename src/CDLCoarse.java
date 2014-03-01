/*
 NAME : DEEPAK RAVISHANKAR RAMKUMAR
 EMAIL: dramkuma@buffalo.edu
 */
class CDLCoarse<T> extends CDLList<T>{
public Element head;
public Cursor cursor;
public CDLCoarse(T v) {
	    super(v);
		head=new Element();
		cursor=new Cursor();
		head.value=v;
		head.next=head;
		head.prev=head;
		cursor.current=head;
        System.out.println("inside coarse");
	}
public class Element extends CDLList<T>.Element{
	T value;
    Element next;
	Element prev;	
	public Element()
	{
		super();
	}
public  T value() {
	return this.value;	
}
}
public Element head() {
	return head;
}
public Cursor reader(Element from) {
cursor.current=from;
return cursor;
}
public class Cursor extends CDLList<T>.Cursor {
	public Element current;
	public Cursor(){
		super();
		current=new Element();
		}
public Element current() {
	synchronized(CDLCoarse.this){
	return cursor.current;
	}
}
public void previous() {
	synchronized(CDLCoarse.this){
	cursor.current=cursor.current.prev;
	}
}
public void next() {
synchronized(CDLCoarse.this){
	cursor.current=cursor.current.next;
}
}
public  Writer writer() {
	Writer w=new Writer();
	w.writercurrent=current;
	return w;
	
}
}
public class Writer extends CDLList<T>.Writer{
	public Element writercurrent;
	public Writer()
	{
	super();	
	writercurrent=new Element();
	
	}   
public boolean insertBefore(T val) {
	boolean flag=false;
	synchronized(CDLCoarse.this){
	Element node2=new Element();
	Element temp=writercurrent.prev;
		node2.value=val;
		node2.next=writercurrent;
		node2.prev=temp;
		temp.next=node2;
		writercurrent.prev=node2;
		flag=true;
	}
	return flag;
}                  
public boolean insertAfter(T val) {
	boolean flag=false;
	synchronized(CDLCoarse.this){
    Element node=new Element();
    Element temp=new Element();
    temp=writercurrent.next;
	node.value=val;
	node.prev=writercurrent;
	node.next=temp;
	writercurrent.next=node;
	temp.prev=node;
	flag=true;	
	}
	return flag;
}
}
}

