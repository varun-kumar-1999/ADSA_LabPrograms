import java.io.*;
import java.util.*;
class Myqueen
{ int[] x=new int[100];
void display(int n)
{ char[][] chessboard=new char[20][20];
int i,j;
for(i=0;i<n;i++)
for(j=0;j<n;j++)
chessboard[i][j]='x';
for(i=0;i<n;i++)
chessboard[i][x[i]]='Q';
for(i=0;i<n;i++)
{ for(j=0;j<n;j++)
System.out.print(chessboard[i][j]+" ");
System.out.println("\n");
}
System.out.println("\n************\n");
}
int place(int k)
{ for(int i=0;i<k;i++)
if(x[i]==x[k] || (Math.abs(x[i]-x[k]) == Math.abs(i-k)))
return 1;
return 0;
}
public void queen(int n)
{ int k=0,c=0;
x[0]=-1;
while(k>=0)
{ x[k]=x[k]+1;
while(x[k]<n & place(k)==1)
x[k]=x[k]+1;
if(x[k]<n)
{ if(k==n-1)
{ display(n);
c++;
}
else
{ k++;
x[k]=-1;
}
}
else
k--;
}
System.out.println("No. of possibilities for "+n+" queens is :"+c);
if(k<0 && c==0)
System.out.println("\nFailure!!!!!! No solution");
}
}
public class Nqueen
{
 public static void main (String[] args)
 { int n;
Myqueen m=new Myqueen();
 Scanner in=new Scanner(System.in);
System.out.println("Enter no of Queens :");
n=in.nextInt();
m.queen(n);
 
 }
}