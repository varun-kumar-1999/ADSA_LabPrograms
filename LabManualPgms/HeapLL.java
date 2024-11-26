import java.io.*;
import java.util.*;
class heapsort
{ 
LinkedList<Integer> list1 = new LinkedList<Integer>();
LinkedList<Integer> list2 = new LinkedList<Integer>();
void Read(int n)
{
 //int x;
Scanner in=new Scanner(System.in);
//Random r=new Random();
int [] h=new int[100];
for(int i=1;i<=n;i++)
{
 int e = in.nextInt();
 h[i]=e;//in.nextInt();//(100) + (-25)+1;
list1.addLast(h[i]);
}
//list1.addLast(.nextInt(n));
list1.addFirst(0); //index 0 in list1 is not used 
}
public void heaps(int n)
{ int k,v,j,i,heap;
for(i=n/2;i>=1;i--)
{ k=i;
v=list1.get(k);
heap=0;
while((heap==0) && ((2*k)<=n))
{ j=2*k;
if(j<n) //checking for 2 children
if(list1.get(j) < list1.get(j+1))
j++;
if(v>=list1.get(j))
heap=1;
else
{ list1.set(k,list1.get(j));
k=j;
}
}
list1.set(k,v);
}
}
public void heapify(int n)
{ int t;
if(n==1)
{ Display(n);
list2.addLast(list1.get(1));
list1.removeLast();
}
else
{ heaps(n);//construct the heap using bottom up method.
t=list1.get(1);
list1.set(1,list1.get(n));
list1.set(n,t);
list2.addLast(list1.get(n));
Display(n);
list1.removeLast();
heapify(n-1);//reduce the heap size by 1.
}
}
void Display(int n)
{ int x;
System.out.println("\n--------------\n");
for(int i=1;i<=n;i++)
System.out.print(list1.get(i)+ "\t");
}
void Display1(int n)
{ int x;
System.out.println("\nHeapified List is :");
for(int i=0;i<n;i++)
System.out.println(list2.get(i));
}
}
public class HeapLL
{
public static void main (String[] args)
{ int n;
heapsort h=new heapsort();
Scanner in=new Scanner(System.in);
System.out.println("********** HEAP SORT *********");
System.out.println("enter the size of heap :");
n=in.nextInt(); 
 h.Read(n);
System.out.println("Heaped elements : ");
long time = System.currentTimeMillis();
 h.heapify(n);
long timeNow = System.currentTimeMillis();
 h.Display1(n);
System.out.println("time: " + (timeNow - time)+"ms"); 
}
}
