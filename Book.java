package ish;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title;
	private String author;
	private String genre;
	private Boolean checkedOut;
	private int bID;
	private static final AtomicInteger count = new AtomicInteger(-1); 
	static ArrayList<Book> booklist = new ArrayList<>();
	static File bList = new File("BookList.txt");
	
	
	Book(){
		title = "Blank";
		author = "Blank";
		genre = "Blank";
	}
	
	Book(String title, String author, String genre){
		this.title = title;
		this.author = author;
		this.genre = genre;
		checkedOut = false;
		bID = count.incrementAndGet();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getID() {
		return bID;
	}
	public void setID(int i) {
		bID = i;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public static void createBook(String t, String a, String g) {
		Book book = new Book(t, a, g);
		booklist.add(book);
		System.out.println("Book successfully created!");
		Book.serializeBooks(booklist);
	}
	public static void serializeBooks(ArrayList<Book> list) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bList));
			oos.writeObject(list);
			}
		catch(IOException io) {
			io.printStackTrace();
		}
		
	}
	
	public static void printList() {
		for(Book b: booklist) {
			System.out.println(b.toString());
		}
	}
	
	public boolean equals(Book me) {
        boolean isEqual=false;
        if(me!=null && me instanceof Book) {
            isEqual=(this.title==me.title);
        }
        return isEqual;
    }
	
	public String toString() {
		String Status;
		if(checkedOut) {
			Status = "Yes";
		}
		else{
			Status = "No";
		}
		
		return "Title: " + title +
				"\nAuthor: " + author +
				"\nGenre: " + genre +
				"\nBook ID: " + bID +
				"\nIs the book checked out? " + Status + "\n";				
		
	}
	
	public String genreFormat() {
		String Status;
		if(checkedOut) {
			Status = "\tCurrently Checked out";
		}
		else {
			Status = "\tThis Book is available";
		}
		
		return "Title- " + title + "\tAuthor- " + author + "\tGenre- " + "\tBook ID " + bID + Status;
	}

	public Boolean getCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(Boolean checkedOut) {
		this.checkedOut = checkedOut;
	}
	
	

}
