import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.lang.Math;
public class LZW {
    public static void main(String[] args){
        PrintStream out= null;
        try {
            out = new PrintStream(new FileOutputStream("output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(out);
        String test_str ="";
        try {
            File  input = new File("input.txt");
            Scanner myReader = new Scanner(input);
            test_str = myReader.nextLine();
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Map<Integer,String> map=new HashMap<Integer,String>();
        int cnt=65;
        for (char c='A';c<='Z';c++){
            String x= String.valueOf(c);
            map.put(cnt,x);
            cnt++;
        }
        int hamada=0;int mx=-1;
        String search="";int cntr=128;boolean finish=true;
        for(int i=0;i<test_str.length();i++){
            if(map.containsValue(search+test_str.charAt(i))==true){
                search+=test_str.charAt(i);
            }
            else{
                if(i==test_str.length())finish=false;
                String elwg3="";
                for(Map.Entry m:map.entrySet()){
                    elwg3= (String) m.getValue();
                    if(elwg3.equals(search)){
                        hamada++;
                        System.out.println(m.getKey());
                        int x=(int)m.getKey();
                        mx=Math.max(mx,x);
                        break;
                    }
                }
                search+=test_str.charAt(i);
                map.put(cntr,search);
                search="";cntr++;i--;
            }
        }
        if(finish){
            String elwg3="";
            for(Map.Entry m:map.entrySet()){
                elwg3= (String) m.getValue();
                if(elwg3.equals(search)){
                    hamada++;
                    System.out.println(m.getKey());
                    int x=(int)m.getKey();
                    mx=Math.max(mx,x);
                    break;
                }
            }
        }
        System.out.println("Original Size : "+test_str.length()*8);
        int x=(int)(Math.log(mx)/Math.log(2))+1;
        System.out.println("Compressed Size: "+hamada*x);
        System.out.println(x);

    }
}
