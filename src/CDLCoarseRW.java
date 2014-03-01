/*
 NAME : DEEPAK RAVISHANKAR RAMKUMAR
 EMAIL: dramkuma@buffalo.edu
 */
public class CDLCoarseRW<T> extends CDLList<T>{
	public Element head;
	public Cursor cursor;
	private ReadWriteLock lock=new ReadWriteLock();
	public CDLCoarseRW(T v) 
	{
	    super(v);
		head=new Element();
		cursor=new Cursor();
		head.value=v;
		head.next=head;
		head.prev=head;
		cursor.current=head;
        System.out.println("inside coarseRW");
	}
	public class Element extends CDLList<T>.Element {
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
	public class Cursor extends CDLList<T>.Cursor {
	    public Element current;
		public Cursor()
		{ 
			super();
			current=new Element();
		}	
	   public Element current() {
		  try {
			lock.lockRead();
			  return cursor.current;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		  finally{
				lock.unlockRead();
			}
			
			return null;
		}
	                                                             

		public void previous() {
			 try {
				 lock.lockRead();
			 }catch (InterruptedException e) {
					e.printStackTrace();
				}
			cursor.current=cursor.current.prev;
			lock.unlockRead();
				 }
	                                                      

public void next() {
try {
lock.lockRead();
}catch (InterruptedException e) {
e.printStackTrace();
}
cursor.current=cursor.current.next;
lock.unlockRead();
}
	                                                              
public Writer writer() {
Writer w=new Writer();
w.writercurrent=current;
return w;
		}
	}
public class Writer extends CDLList<T>.Writer {
public Element writercurrent;
public Writer(){
		super();
		writercurrent=new Element();
	}
public boolean insertBefore(T val) {
			boolean flag=false;
			 try {
			lock.lockWrite();	
			Element node2=new Element();
			Element temp=writercurrent.prev;
				node2.value=val;
				node2.next=writercurrent;
				node2.prev=temp;
				temp.next=node2;
				writercurrent.prev=node2;
				flag=true;	
			 } catch (InterruptedException e) {
					e.printStackTrace();
				}
			 finally{
				lock.unlockWrite();	
			 }
			return flag;
		}
public boolean insertAfter(T val) {
			boolean flag=false;
			 try {
		    lock.lockWrite();
		    Element node=new Element();
			Element temp=writercurrent.next;
			node.value=val;
			node.prev=writercurrent;
			node.next=temp;
			writercurrent.next=node;
			temp.prev=node;
			flag=true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
					lock.unlockWrite();
				}
			return flag;		
	    	}
  }
}
