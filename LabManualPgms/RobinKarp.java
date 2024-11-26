import java.io.*;
import java.util.*;
public class RobinKarp
{
String text = null, pattern = null; 
int m,n,p,q;
int flag=0;
public void preprocessing()
{ m= pattern.length();
n= text.length(); 
q=11;
p=Integer.parseInt(pattern)%q ;
}
public void string_match() 
{
System.out.println( "Enter the Numeric String :"); 
Scanner in=new Scanner(System.in);
text=in.nextLine();
System.out.print("Enter the pattern to be searched:\n ");
pattern = in.nextLine();
preprocessing(); 
int i=0,rem;
for(int s=0;s<=n-m;s++)
{ i=0;
rem=Integer.parseInt(text.substring(s,s+m))%q; 
// performs mod operation on the substring of size m 
if (p==rem)
{ while(i<m &&text.charAt(s+i)==pattern.charAt(i)) 
i++;
if (i==m)
{ System.out.print("\n SUCCESS!!! The pattern is found at position " + (s+1));
flag=1;
}
}
}
if(flag==0)
{
System.out.print("FAILURE!!!! \nThe pattern "+pattern+" is not found in the ntext");
}
}
public static void main(String args[]) throws IOException
{ RobinKarp r = new RobinKarp();
    r.string_match();
}
}