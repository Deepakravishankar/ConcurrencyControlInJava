/*
 NAME : DEEPAK RAVISHANKAR RAMKUMAR
 EMAIL: dramkuma@buffalo.edu
 */
public class CDLListFine<T> extends CDLList<T> {
	public Element head;
	public Cursor cursor;
	private Element magicelement;
public CDLListFine(T v) {
	    super(v);
		head=new Element();
		cursor=new Cursor();
		magicelement=new Element();
		head.value=v;
		head.next=magicelement;
		head.prev=magicelement;
		magicelement.next=head;
		magicelement.prev=head;
		cursor.current=head;
        System.out.println("inside Fine");
}
public class Element extends CDLList<T>.Element{
	T value;
    Element next;
	Element prev;		
public Element()
	{
		super();
	}	
public T value() {
	return value;
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
 synchronized(current){
	return cursor.current;
}
	}
public void previous() {
	synchronized(current){
		if(cursor.current.prev==magicelement){
			cursor.current=magicelement.prev;
		}
		else
		cursor.current=cursor.current.prev;
	}
}                                                     
public void next() {
	synchronized(current){
		if(cursor.current.next==magicelement){
			cursor.current=magicelement.next;
		}
		else
		cursor.current=cursor.current.next;	
	}
}
public Writer writer() {
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
	while(true){
	Element previous=writercurrent.prev;
	synchronized(previous){
	synchronized(writercurrent){
	if(previous==writercurrent.prev){
    Element node2=new Element();
	Element temp=writercurrent.prev;
		node2.value=val;
		node2.next=writercurrent;
		node2.prev=temp;
		temp.next=node2;
		writercurrent.prev=node2;
		return true;
					}
				}
			}
		}
	}
 public boolean insertAfter(T val) {
	boolean flag = false;
	synchronized(writercurrent)
	{
    synchronized(writercurrent.next){
	Element node=new Element();
    Element temp=writercurrent.next;
	node.value=val;
	node.prev=writercurrent;
	node.next=temp;
	temp.prev=node;
	writercurrent.next=node;
	flag=true;	
				}
			}
	return flag;
 		}
	}
}






