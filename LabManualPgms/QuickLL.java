import java.io.*;
import java.util.*;
class Quick
{ LinkedList<Integer> list1 = new LinkedList<Integer>();
Scanner in=new Scanner(System.in);
int i,j;
 public void Read(int n)
 { Random r=new Random();
 int [] h=new int[100];
 for(int i=1;i<=n;i++)
 { h[i]=r.nextInt(100)+(-25) +1;//max 100 and min 25 range i.e -25 to 100
 list1.addLast(h[i]);
 list1.addLast(0);
 } 
} 
public void swap(int m,int n)
{ int t;
t=list1.get(m);
list1.set(m,list1.get(n));
list1.set(n,t);
}
public int partition(int l,int r)
{ int p,i,j;
p=list1.get(l);
i=l+1;
j=r;
 
while(i<=j)
{ while(list1.get(i)<=p & i<=r)
i++;
while(list1.get(j)>p)
j--;
swap(i,j);
}
swap(i,j);
swap(l,j);
return j;
}
public void QSort(int l,int r)
{ int s;
if(l<r)
{ s=partition(l,r);
QSort(l,s-1);
QSort(s+1,r);
}
} 
public void Display(int n)
{ System.out.print("sorted order :");
for(i=0;i<n;i++)
System.out.print(list1.get(i)+" ");
} 
}
public class QuickLL 
{ public static void main(String[] args)
 { int n;
Scanner in=new Scanner(System.in);
Quick q=new Quick();
 System.out.println("********** QUICK SORT *********");
System.out.println("Enter n :");
n=in.nextInt();
q.Read(n);
long time = System.currentTimeMillis();
 q.QSort(0,n-1);
long timeNow = System.currentTimeMillis();
 q.Display(n);
System.out.println("\ntime: " + (timeNow - time)+"ms");
 }
}