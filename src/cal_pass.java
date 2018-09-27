import java.sql.SQLException;
import java.util.Scanner;

public class cal_pass {
    public static void main(String[] args) throws SQLException {
        Scanner sc=new Scanner(System.in);
        String s = sc.next();
        for(int i=0;i<s.length();i++){
            System.out.printf("%c",s.charAt(i)^0x18);
        }
    }
}
