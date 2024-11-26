import java.io.*;
import java.util.*;
 class set
 {
int n,d,sum,i,flag=0;
int S[]=new int[100];
int x[]=new int[100];
public void Read_Find()
 {
Scanner in=new Scanner(System.in);
System.out.println("********** SUBSET-SUM PROBLEM *********");
System.out.println("Enter the size of set :");
n=in.nextInt();
 System.out.println("Enter set elements in increasing order: ");
for(int i=1;i<=n;i++)
S[i]=in.nextInt();
System.out.println("Enter maximum limit: ");
d=in.nextInt();
System.out.println("The subsets which forms sum "+d+" are:");
for( i=1;i<=n;i++)
sum=sum+S[i];
if(sum>=d)
sumofsub(0,1,sum);
if(flag==0)
System.out.println("{}");
 }
 public void sumofsub(int s,int k,int r)
 {
x[k]=1;
if(s+S[k]==d)
 {
System.out.print("{ ");
for(int i=1;i<=n;i++)
if(x[i]==1)
 {
System.out.print(S[i]+" ");
flag=1;
 }
System.out.println("}");
}
else
 {
if(s+S[k]+S[k+1]<=d)
 {
sumofsub(s+S[k],k+1,r-S[k]);
x[k+1]=0;
 }
if((s+r-S[k]>=d)&&(s+S[k+1]<=d))
 {
x[k]=0;
sumofsub(s,k+1,r-S[k]);
x[k+1]=0;
 }
 }
 }
 }
 class SubSet
 { public static void main (String[] args)
 {
 set s=new set();
s.Read_Find();
 }
 }