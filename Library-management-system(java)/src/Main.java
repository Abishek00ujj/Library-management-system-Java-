import java.sql.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws SQLException
    {
        String heartEmoji = "\u2764\uFE0F";
        String url="jdbc:postgresql://localhost:5432/abishek";
        String username="postgres";
        String password="1234";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
        }
        catch (Exception e)
        {
            System.out.println("Error in connection:");
        }
        System.out.println("Library Management System");
        while(true)
        {
            System.out.println("1.Add users");
            System.out.println("2.Show users");
            System.out.println("3.Add books");
            System.out.println("4.Add existing books new Stocks");
            System.out.println("5.Display all books");
            System.out.println("6.Get books(For User)");
            System.out.println("7.Return book");
            System.out.println("8.Exit");
            Scanner sc=new Scanner(System.in);
            int choice=sc.nextInt();
            sc.nextLine();
            if(choice==1)
            {
                System.out.println("Enter your name:");
                String name=sc.nextLine();
                System.out.println("Enter your id:");
                int id=sc.nextInt();
                String sql="insert into stud(id,name) values(?,?)";
                try {
                    assert con != null;
                    PreparedStatement pst=con.prepareStatement(sql);
                    pst.setInt(1,id);
                    pst.setString(2,name);
                    int rowsAffected = pst.executeUpdate();
                    System.out.println("User added successfully! Rows affected: " + rowsAffected);
                    System.out.println("User added Succuessfully!");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(choice==2)
            {
                System.out.println("_______________________________________________Showing Users_______________________________________________________________");
                String sql="Select * from stud";
                assert con != null;
                Statement st= con.createStatement();
                ResultSet rs=st.executeQuery(sql);
                rs.next();
                while(rs.next())
                {
                    System.out.println(rs.getString("id")+" "+rs.getString("name"));
                }
            }
            else if(choice==3)
            {
                System.out.println("_______________________________________________Add books_______________________________________________________________");
                System.out.println("Book Name?:");
                String name=sc.nextLine();
                System.out.println("Author name?:");
                String author=sc.nextLine();
                System.out.println("Number of copies?");
                int copies=sc.nextInt();
                String sql="insert into books(name,author,copies) values(?,?,?)";
                assert con!=null;
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setString(1,name);
                pst.setString(2,author);
                pst.setInt(3,copies);
                int rowsAffected = pst.executeUpdate();
                if(rowsAffected!=0)
                {
                    System.out.println("New Books added succuessfully");
                }
            }
            else if(choice==4)
            {
                System.out.println("New stocks of existing books adding!");
                System.out.println("Enter the book name?");
                String name=sc.nextLine();
                sc.nextLine();
                System.out.println("Enter the number of copies (extended)?");
                int newcopies=sc.nextInt();
                String sql="update books set copies=copies+? where name=?";
                assert con != null;
                try(PreparedStatement pst=con.prepareStatement((sql)))
                {
                    pst.setInt(1,newcopies);
                    pst.setString(2,name);
                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected != 0) {
                        System.out.println("Book stock updated successfully.");
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                //System.out.println("Stocks updated succuessfully");
            }
            else if(choice==5)
            {
                System.out.println("_______________________________________________Showing books_______________________________________________________________");
                String sql="Select * from books";
                assert con != null;
                Statement st= con.createStatement();
                ResultSet rs=st.executeQuery(sql);
                rs.next();
                while(rs.next())
                {
                    System.out.println(rs.getString("name")+":"+rs.getString("author")+":5"+rs.getInt("copies"));
                }
            }
            else
            {
                System.out.println("Invalid choice! do u want to continue? y or n");
                String x=sc.nextLine();
                if(x.charAt(0)=='y')
                {
                    continue;
                }
                else
                {
                    System.out.println("_______________________________________________Made with "+heartEmoji+" by Abishek.S_______________________________________________________________");
                    break;
                }
            }
        }
    }
}