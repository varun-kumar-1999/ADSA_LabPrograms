import java.io.*;
//import java.io.InputStreamReader;
//import java.io.IOException;
public class KnuthMorrisPratt
{
private int[] failure;
public KnuthMorrisPratt(String text, String pat)
{
failure=new int[pat.length()];
fail(pat);
int pos=postMatch(text,pat);
if(pos==-1)
System.out.println("\nNo match found");
else
System.out.println("\nMatch found at index "+pos);
}
private void fail(String pat)
{
int n=pat.length();
failure[0]=-1;
for(int j=1;j<n;j++)
{
int i= failure[j-1];
while((pat.charAt(j)!=pat.charAt(i+1))&& i>=0)
i=failure[i];
if(pat.charAt(j)==pat.charAt(i+1))
failure[j]=i+1;
else
failure[j]=-1;
}
}
private int postMatch(String text,String pat)
{
int i=0,j=0;
int lens=text.length();
int lenp=pat.length();
while(i<lens && j<lenp)
{
if(text.charAt(i)==pat.charAt(j))
{
i++;
j++;
}
else if(j==0)
i++;
else
j=failure[j-1]+1;
}
return((j==lenp)?(i-lenp):-1);
}
public static void main(String[] args) throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("KnuthMorrisPratt text");
System.out.println("Enter text\n");
String text=br.readLine();
System.out.println("Enter pattern");
String pattern=br.readLine();
KnuthMorrisPratt kmp=new KnuthMorrisPratt(text,pattern);
}
}