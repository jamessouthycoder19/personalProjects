import java.util.Scanner;
public class reverseAString {
    public static void main(String[] args){
        try (Scanner a = new Scanner(System.in)) {
            System.out.println("enter a string to be reversed:");
            String thingy = a.nextLine();
            String reversed = "";
            for(int i = thingy.length();i>0;i--){
                reversed += thingy.substring(i-1,i);
            }
            System.out.println(reversed);
        }
    }
}