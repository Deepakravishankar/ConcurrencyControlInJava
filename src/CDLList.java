/*
 NAME : DEEPAK RAVISHANKAR RAMKUMAR
 EMAIL: dramkuma@buffalo.edu
 */
public class CDLList<T>  {
public Element head;
public Cursor cursor;
public CDLList(T v) {
	head=new Element();
	cursor=new Cursor();
	head.value=v;
	head.next=head;
	head.prev=head;
	cursor.current=head;
		}                                                    
public class Element {
	T value;
    Element next;
	Element prev;
public T value() {
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
public class Cursor {                                                      
	public Element current;
	public Cursor()
	{
		current=new Element();
	}
   public Element current() {
		return cursor.current;
	}
	public void previous() {
		cursor.current=cursor.current.prev;
	}
	public void next() {
		cursor.current=cursor.current.next;
		}                                                       
	public Writer writer() {
	    Writer w=new Writer();
		w.writercurrent=current;
		return w;
	}
}
public class Writer {                                                                   
public Element writercurrent;
public Writer(){
	writercurrent=new Element();
}
	public boolean insertBefore(T val) {
		boolean flag=false;
		Element node2=new Element();
		Element temp=writercurrent.prev;
			node2.value=val;
			node2.next=writercurrent;
			node2.prev=temp;
			temp.next=node2;
			writercurrent.prev=node2;
			flag=true;	
		return flag;
	}                                                                   
	public boolean insertAfter(T val) {
		boolean flag=false;
		Element node=new Element();
	    Element temp=writercurrent.next;
		node.value=val;
		node.prev=writercurrent;
		node.next=temp;
		temp.prev=node;
		writercurrent.next=node;
		flag=true;
		return flag;		
    	}
    }
}
 