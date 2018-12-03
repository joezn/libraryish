package ish;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("serial")
public class UserAcc extends Account implements Serializable {
	
	static ArrayList<UserAcc> ulist = new ArrayList<>();
	static File accFile = new File("AccountList.txt");
	private ArrayList<Integer> checkedOutBooks = new ArrayList<>();
	private int aID;
	private static final AtomicInteger count = new AtomicInteger(-1);
	
	UserAcc() {
		username = null;
		password = null;
		
	}
	
	UserAcc(String username, String password) {
		this.username = username;
		this.password = password;
		aID = count.incrementAndGet();
		
	}
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getId() {
		return aID;
	}
	
	public void addBook(int ID) {
		checkedOutBooks.add(ID);
	}
	public void removeBook(int ID) {
		checkedOutBooks.remove(ID);
	}
	
	public void checkInBook(int ID) { 
		Scanner scan = new Scanner(System.in);
		if(Book.booklist.get(ID).getCheckedOut() == true) {
			System.out.println("Would you like to check in " + Book.booklist.get(ID).getTitle() + "? (yes or no)");
			String yON = scan.nextLine();
			if(yON.equalsIgnoreCase("yes"));{
				checkedOutBooks.remove(ID);
				Book.booklist.get(ID).setCheckedOut(false);
				System.out.println("Book successfully checked in!");
			}
		}
		else {
			System.out.println("This book isn't checked out");
		}
	}
	
	public void printBooks() {
		for(int i = 0; i < checkedOutBooks.size(); i++) {
			int ID = checkedOutBooks.get(i);
			for(int z = 0; z < Book.booklist.size(); z++) {
				if(Book.booklist.get(z).getID() == ID) {
					System.out.println(Book.booklist.get(z).toString());
				}
			}
		}
	}
	
	public static void createAcc(String u, String p) {
		UserAcc ua = new UserAcc(u, p);
		ulist.add(ua);
		System.out.println("User Account successfully created!");
		UserAcc.serializeAcc(ulist);
	}
	
	public static void deleteAcc(String u) {
		for(int i = 0; i < ulist.size(); i++) {
			if(ulist.get(i).getUsername().equals(u) == true){
				ulist.remove(i);
				System.out.println("User Successfully Deleted");
			}
		}
		serializeAcc(ulist);
	}
	
	public static void serializeAcc(ArrayList<UserAcc> list) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(accFile));
			oos.writeObject(list);
			}
		catch(IOException io) {
			io.printStackTrace();
		}
		
	}
	
	public static ArrayList<UserAcc> readAccs(ArrayList<UserAcc> list) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(accFile));
			ulist = (ArrayList<UserAcc>) ois.readObject();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ulist;
	}
	
	public static void printList() {
		for(UserAcc ua: ulist) {
			System.out.println(ua.toString());
		}
	}
	
	public boolean equals(UserAcc me) {
        boolean isEqual=false;
        if(me!=null && me instanceof UserAcc) {
            isEqual=(this.username==me.username);
        }
        return isEqual;
    }
	
	public String toString() {
		return "Username: " + username +
				"\nAccount ID: " + aID;
		
	}

}
