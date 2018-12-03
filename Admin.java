package ish;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Admin extends Account implements Serializable {
	static ArrayList<Admin> aList = new ArrayList<>();
	static File accList = new File("AdminAccountList.txt");
	
	public Admin() {
		username = null;
		password = null;
	}
	
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public static void serializeAcc(ArrayList<Admin> list) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(accList));
			oos.writeObject(list);
			oos.close();
			}
		catch(IOException io) {
			io.printStackTrace();
		}
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean equals(Admin me) {
        boolean isEqual=false;
        if(me!=null && me instanceof Admin) {
            isEqual=(this.username==me.username);
        }
        return isEqual;
    }
	
	
	

}
