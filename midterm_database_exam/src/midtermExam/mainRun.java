package midtermExam;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class mainRun {
	static Scanner in = new Scanner(System.in);

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		login();
		
		
		
	}
	public static void login() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBmidtermExam?autoReconnect=true&useSSL=false",	"root", "root");
				
		System.out.println("");
		System.out.println("**LOGIN**");
		System.out.print("Username: ");
		String username = in.next();
		System.out.print("Password: ");
		String password = in.next();
		System.out.println("");
	
		CallableStatement statement3 = conn.prepareCall("call checkUser()");
		ResultSet rs3 = statement3.executeQuery();
		boolean flag = false;

		while (rs3.next()) {
//		System.out.println(rs3.getString(1));
//		System.out.println(rs3.getString(2));
		//System.out.println(rs3.getString(1) + " " + rs3.getString(2));
			if(username.equals(rs3.getString(1)) && password.equals(rs3.getString(2))){
				System.out.println("Successful Login");
				flag = true;
				choices();
			}  
		}
		
		if(!flag) {
			System.out.println("INVALID try again");
			login();
		}
		
	}
	
	
	
	public static void choices() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("--------------");
		System.out.println("*WELCOME*");
		System.out.println("1. Add new user:");
		System.out.println("2. View all user:");
		System.out.println("3. View all order:");
		System.out.println("4. View all inventory:");
		System.out.println("5. Place an order:");
		System.out.println("6. Edit / Update user details");
		System.out.println("7. Activate / or deactivate users");
		System.out.println("8. Add new items");
		System.out.println("9. Add item stock");
		System.out.println("10. Exit program");
		System.out.println("--------------");
		
		int choice = in.nextInt();
		if (choice == 1) {
			adduser();
		} else if (choice == 2) {
			viewAllUser();
		} else if (choice == 3) {
			viewAllOrders();
		} else if (choice == 4) {
			viewAllInv();	
		} else if (choice == 5) {
			placeOrder();
		} else if (choice == 6) {
			UpdateUser();
		} else if (choice == 7) {
			statusChange();
		} else if (choice == 8) {
			addNewItem();
		} else if (choice == 9) {
			addItemStock();
		} else if (choice == 10) {
			exit();
		} else {
			System.out.print("Invalid");
			
		}
		
	}
	public static void adduser() {

		// TODO Auto-generated method stub
		System.out.println("------------");
		System.out.println("INSERT THE FOLLOWING DETAILS!!");
		
		
		System.out.print("Enter username:");
		String username = in.next();
		System.out.print("Enter password:");
		String password= in.next();
		System.out.print("Confirm password: ");
		String recheck = in.next();
		System.out.print("Enter Lastname:");
		String lastname = in.next();
		System.out.print("Enter Firstname:");
		String firstname = in.next();
		System.out.print("Enter Middlename:");
		String middlename = in.next();
		System.out.print("Enter Address:");
		String address = in.next();
		System.out.print("Enter Age:");
		int age = in.nextInt();
		System.out.print("Enter Status (ACTIVE/INACTIVE:)");
		String userstatus = in.next();
		
		while(!password.equals(recheck)) {
			System.out.println("incorrect password, please try again...");
			
			break;
			}
			
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBmidtermExam?autoReconnect=true&useSSL=false","root", "root");
			
			
			CallableStatement statement = conn.prepareCall("{call insertdits(?,?,?,?,?,?,?,?)}");
			
			statement.setString(1,username);
			statement.setString(2,password);
			statement.setString(3,lastname);
			statement.setString(4,firstname);
			statement.setString(5,middlename);
			statement.setString(6,address);
			statement.setInt(7,age);
			statement.setString(8,userstatus);
					
			statement.executeUpdate();
			choices();
		}
		catch(Exception e) {
			//  Block of code to handle errors
		}
	}
	
	
	public static void viewAllUser() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		try {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBmidtermExam?autoReconnect=true&useSSL=false",	"root", "root");
		CallableStatement statement3 = conn.prepareCall("CALL getalluser()");
		ResultSet rs3 = statement3.executeQuery();
		while (rs3.next()) {
		System.out.println(rs3.getString("id") + " "+ rs3.getString("username")+ " " + rs3.getString("Userstatus"));
		
		}
		choices();
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void UpdateUser() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBmidtermExam?autoReconnect=true&useSSL=false",	"root", "root");
			
			System.out.println("Enter new value");
			System.out.println("New last name");
			String lastname = in.next();
			System.out.println("New first name");
			String firstname = in.next();
			System.out.println("New middle name");
			String middlename = in.next();
			System.out.println("New address");
			String address = in.next();
			System.out.println("Enter password");
			String password = in.next();

			CallableStatement statement4 = conn.prepareCall("CALL Updatedits(?,?,?,?,?)");
			statement4.setString(1, lastname);
			statement4.setString(2, firstname);
			statement4.setString(3, middlename);
			statement4.setString(4, address);
			statement4.setString(5, password);
			statement4.executeQuery();
			System.out.println("Please log in again");
			login();
			}catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	
	public static void statusChange() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBmidtermExam?autoReconnect=true&useSSL=false",	"root", "root");
			
			CallableStatement statement4 = conn.prepareCall("call changeStatus(?,?)");
			
			
			System.out.println("Please enter the Item Id");
			int id = in.nextInt();
			in.nextLine();
			
			System.out.println("active or inactive user:");
			String stock =in.next();
			in.nextLine();
			
			statement4.setInt(1, id);
			statement4.setString(2, stock);
		
			statement4.executeQuery();
			
			System.out.println("Successfully change status! ");

			
			choices();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addItemStock() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBmidtermExam?autoReconnect=true&useSSL=false",	"root", "root");
			
			CallableStatement statement4 = conn.prepareCall("call addStock(?,?)");
			
			
			System.out.println("Please enter the Item Id");
			int id = in.nextInt();
			in.nextLine();
			
			System.out.println("Please enter the amount of stock to add");
			int stock =in.nextInt();
			in.nextLine();
			
			statement4.setInt(1, id);
			statement4.setInt(2, stock);
		
			statement4.executeQuery();
			
			System.out.print("Successfully Added New Stock! ");

			
			choices();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
		
	
	public static void addNewItem() {
		// TODO Auto-generated method stub
		System.out.println("------------");
		System.out.println("Add new stock to INVENTORY!!");
		
		
		System.out.print("Enter ITEM NAME: ");
		String ITEMNAME = in.next();
		System.out.print("Enter STOCK QUANTITY: ");
		int stock = in.nextInt();
		System.out.print("PRICE: ");
		int PRICE = in.nextInt();
		
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBmidtermExam?autoReconnect=true&useSSL=false","root", "root");
			
			
			CallableStatement statement = conn.prepareCall("{call insertstock(?,?,?)}");
			
			statement.setString(1,ITEMNAME);
			statement.setInt(2,stock);
			statement.setInt(3,PRICE);
			
					
			statement.executeUpdate();
			choices();
		}
		catch(Exception e) {
			//  Block of code to handle errors
		}
	}
	
	
	
	
	public static void placeOrder() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBmidtermExam?autoReconnect=true&useSSL=false",	"root", "root");
			CallableStatement statement3 = conn.prepareCall("CALL placeOrderz(?,?)");
			
			System.out.println("----------------------------------");
			System.out.println("Enter the item you will order:");
			System.out.println("Item Name: ");
			String item = in.next();
			System.out.println("Amount to Order: ");
			int amount = in.nextInt();
			
			System.out.println("----------------------------------");
			
			statement3.setString(1, item);
			statement3.setInt(2, amount);
			
			statement3.execute();
						
			System.out.println("Order has been successfully placed!");
			choices();		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void viewAllOrders() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBmidtermExam?autoReconnect=true&useSSL=false",	"root", "root");
			CallableStatement statement3 = conn.prepareCall("CALL viewAllOrderz()");
			ResultSet rs3 = statement3.executeQuery();
			while (rs3.next()) {
			System.out.println(rs3.getString(1) + " "+ rs3.getString(2)+ " " + rs3.getString(3));
			
			}
			choices();
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	
	
	public static void viewAllInv() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBmidtermExam?autoReconnect=true&useSSL=false",	"root", "root");
			CallableStatement statement3 = conn.prepareCall("CALL getallinv()");
			ResultSet rs3 = statement3.executeQuery();
			while (rs3.next()) {
			System.out.println(rs3.getString(1) + " "+ rs3.getString(2)+ " " + rs3.getInt(3)+" "+rs3.getInt(4));
			
			}
			choices();
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	
	public static void exit() {
		// TODO Auto-generated method stub
		 System.out.println("Thank you!");
		 System.exit(0);
		
	}
}
		
