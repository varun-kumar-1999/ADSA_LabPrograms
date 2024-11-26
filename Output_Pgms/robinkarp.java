import java.util.*;
import java.io.*;
public class robinkarp{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String text,pattern;
        System.out.println("Enter Input String: ");
        text = s.nextLine();
        System.out.println("Enter pattern: ");
        pattern = s.nextLine();
        int position = text.indexOf(pattern);
        if(position == -1){
            System.out.println("Pattern Not Found");
        }
        else{
            
            System.out.println("SUCCESS!!!  Pattern Found at position:" +(position+1));
        }
    }
}