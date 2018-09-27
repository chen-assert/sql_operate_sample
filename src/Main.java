import java.sql.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://sql.***.***:3306/car?useSSL=false";
        String user = "root";
        String password = "++++++++";
        String password2 = new String();
        for (int i = 0; i < password.length(); i++) {
            password2 += String.format("%c", password.charAt(i) ^ 0x18);
            //using xor to deal password
        }
        Connection conn = DriverManager.getConnection(url, user, password2);
        System.out.println("Connected to MySQL server success!\n");
        int flag = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.printf("Please select the what operate you want to do:\n");
            System.out.printf("1.Add a new member\n");
            System.out.printf("2.Add a new car\n");
            System.out.printf("3.Find a car's information\n");
            System.out.printf("4.show all available cars\n");
            System.out.printf("5.Exit\n");
            //waiting user to input command
            String s=new String();
            while(s.equals("")) {
                s = sc.nextLine();
            }
            int i = (s.charAt(0))-'0';
            switch (i) {
                case 1: {
                    Operate.add_member(conn,sc);
                    break;
                }
                case 2: {
                    Operate.add_car(conn,sc);
                    break;
                }
                case 3:{
                    Operate.find_car(conn,sc);
                    break;
                }
                case 4:{
                    Operate.show_car(conn,sc);
                    break;
                }
                case 5: {
                    System.out.printf("System close!\n");
                    flag = 1;
                    break;
                }
                default:{
                    System.out.println("蛤?");
                }
            }
            if (flag == 1) break;
        }
        conn.close();
    }
}
