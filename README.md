ConcurrencyControlInJava
========================
NAME : DEEPAK RAVISHANKAR RAMKUMAR
EMAIL: dramkuma@buffalo.edu
DESIGN DOCUMENT
FINE GRAIN LOCKING:
Algorithm Used:
 The locking protocol acquires a lock on elements in the order of previous, current and next in order to avoid deadlock.
 Each element in the list has a lock associated with it. Hence a lock on the element protects its value, next and previous fields.
How are Deadlocks avoided?
 The locking protocol stated above makes sure that there is no deadlock when one element performs an insertBefore operation and the following element performs an insertAfter operation concurrently.
 A magic element has been placed before the head element to avoid deadlock when all the elements in the list are performing an insertBefore or an insertAfter operation. The magic element does not have any value stored in it nor does it have any cursors pointing to it. It simply passes control between the elements in the list and is effectively used only to avoid deadlock.
How are race conditions prevented?
 A check on the previous element is made in the insertBefore method once the locks on the previous and current elements have been acquired. This is done to make sure that no other thread has modified the previous element.
 Once we have locked each element with the help of the above mentioned locking protocol there cannot be any data races. I have used synchronized statements to lock my elements and thus avoid data races.
READ/WRITE LOCKS:
 The objects requesting the reader lock is allowed to acquire it when there are no pending writers or write requests. Multiple objects can acquire the same reader lock and read concurrently inside the lockRead() method. If some other object holds a write lock on the same element then current object is made to wait until it is released by that object.
 Once the objects have finished reading they can release the reader lock and notify all the other threads inside the unlockRead() method.
 Only one object can acquire a write lock on a particular element at a time. A write lock can be acquired only when there are no other objects holding a read or a write lock on the same element. Until then it is made to wait for the lock. Once acquired no other object can access that element until it is released.
 The write lock is released by the object holding it once it has performed its write operation on the element. The number of writers count is decremented and all the other threads waiting for that lock are notified.
 In order to maintain fairness no new readers will be allowed to acquire their read locks on an element when a writer has requested for it. The readers will be made to wait whenever the write requests are greater than 0. However a writer has to wait for the current readers to release the lock.