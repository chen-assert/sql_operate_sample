import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public  class Operate {
    public static int add_member(Connection conn, Scanner sc) throws SQLException {
        String name, date, tnumber, addr;
        System.out.printf("Please input the member's information\n");
        System.out.printf("name:");
        name = sc.nextLine();
        System.out.printf("join date:");
        date = sc.nextLine();
        System.out.printf("telephone number:");
        tnumber = sc.nextLine();
        System.out.printf("address:");
        addr = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("insert into member " +
                "(join_date,name,telephone_number,address)values(?,?,?,?)");
        ps.setString(1, date);
        ps.setString(2, name);
        ps.setString(3, tnumber);
        ps.setString(4, addr);
        ps.executeUpdate();
        ps.close();
        System.out.printf("Update success!\n");
        return 1;
    }
    public static int add_car(Connection conn, Scanner sc) throws SQLException {
        String made_year,manufacturer,model;
        int seats,registration_number;
        double price;
        System.out.printf("Please input the car's information\n");
        System.out.printf("made year:");
        made_year = sc.nextLine();
        System.out.printf("manufacturer:");
        manufacturer = sc.nextLine();
        System.out.printf("model:");
        model = sc.nextLine();
        System.out.printf("seats:");
        seats =Integer.parseInt(sc.nextLine());
        System.out.printf("registration number:");
        registration_number=Integer.parseInt(sc.nextLine());
        System.out.printf("price:");
        price=Double.parseDouble(sc.nextLine());
        PreparedStatement ps = conn.prepareStatement("insert into car " +
                "(made_year,manufacturer,model,seats,registration_number,distance,price,available)" +
                "values(?,?,?,?,?,0,?,1)");
        ps.setString(1, made_year);
        ps.setString(2, manufacturer);
        ps.setString(3, model);
        ps.setInt(4, seats);
        ps.setInt(5,registration_number);
        ps.setDouble(6,price);
        ps.executeUpdate();
        ps.close();
        System.out.printf("Update success!\n");
        return 1;
    }
    public static int find_car(Connection conn, Scanner sc) throws SQLException {
        System.out.printf("Please input the car's registration number:");
        int num=Integer.parseInt(sc.nextLine());
        PreparedStatement ps = conn.prepareStatement("select * from car where registration_number=?");
        ps.setInt(1,num);
        ResultSet resultSet = ps.executeQuery();
        int flag=0;
        while (resultSet.next()){
            flag=1;
            int registration_number=resultSet.getInt("registration_number");
            int made_year = resultSet.getInt("made_year");
            String manufacturer = resultSet.getString("manufacturer");
            String model = resultSet.getString("model");
            int seats= resultSet.getInt("seats");
            double distance = resultSet.getDouble("distance");
            double price = resultSet.getDouble("price");
            System.out.printf("%-10d %-10d %-10s %-10s %-5d %-10.2f %-10.2f\n",
                    registration_number,made_year,manufacturer,model,seats,distance,price);
        }
        if(flag==0)System.out.printf("Could not find such a car\n");
        resultSet.close();
        ps.close();
        return 1;
    }
    public static int show_car(Connection conn, Scanner sc) throws SQLException {
        System.out.printf("There are all available cars in our company:\n");
        Statement sta=conn.createStatement();
        ResultSet resultSet = sta.executeQuery("select * from car where available=1");
        List a;

        while (resultSet.next()){
            int registration_number=resultSet.getInt("registration_number");
            int made_year = resultSet.getInt("made_year");
            String manufacturer = resultSet.getString("manufacturer");
            String model = resultSet.getString("model");
            int seats= resultSet.getInt("seats");
            double distance = resultSet.getDouble("distance");
            double price = resultSet.getDouble("price");
            System.out.printf("%-10d %-10d %-10s %-10s %-5d %-10.2f %-10.2f\n",
                    registration_number,made_year,manufacturer,model,seats,distance,price);
        }
        resultSet.close();
        sta.close();
        return 1;
    }
}
//ftp://ftp.chenassert.xyz/file/sql_assignment2.zip