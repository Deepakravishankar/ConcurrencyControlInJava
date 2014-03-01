/*
 NAME : DEEPAK RAVISHANKAR RAMKUMAR
 EMAIL: dramkuma@buffalo.edu
 */
public class CDLListFineRW<T> extends CDLList<T> {
	public Element head;
	public Cursor cursor;
	private Element magicelement;
public CDLListFineRW(T v) {
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
        System.out.println("inside FineRW");
}
public class Element extends CDLList<T>.Element{
	T value;
    Element next;
	Element prev;	
	ReadWriteLock lock=new ReadWriteLock();
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
 try{
	 current.lock.lockRead();
	return cursor.current;
}catch(InterruptedException e){
	e.printStackTrace();
}finally{
current.lock.unlockRead();
}
 return null;
	}
public void previous() {
	try{
		current.lock.lockRead();
		if(cursor.current.prev==magicelement){
			cursor.current=magicelement.prev;
		}
		else
		cursor.current=cursor.current.prev;
	}catch(InterruptedException e){
		e.printStackTrace();
	}finally{
	current.next.lock.unlockRead();
	}
}                                                     
public void next() {
	try{
		current.lock.lockRead();
		if(cursor.current.next==magicelement){
			cursor.current=magicelement.next;
		}
		else
		cursor.current=cursor.current.next;	
	}catch(InterruptedException e){
		e.printStackTrace();
	}finally{
	current.prev.lock.unlockRead();
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
	try{
	previous.lock.lockWrite();
	try{
	writercurrent.lock.lockWrite();
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
				}catch(InterruptedException e){
					e.printStackTrace();
											}
			}catch(InterruptedException e){
				e.printStackTrace();
										}
			finally{
				writercurrent.lock.unlockWrite();
				previous.lock.unlockWrite();
				}
		}
	}
 public boolean insertAfter(T val) {
	boolean flag = false;
	try{
	writercurrent.lock.lockWrite();
    try{
    writercurrent.next.lock.lockWrite();
    Element temp=writercurrent.next;
	Element node=new Element();
	node.value=val;
	node.prev=writercurrent;
	node.next=temp;
	temp.prev=node;
	writercurrent.next=node;
	flag=true;	
	node.next.lock.unlockWrite();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}finally{
				 writercurrent.lock.unlockWrite();
			}
	return flag;
 		}
	}
}






