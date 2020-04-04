package ProjectoMongoDB.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class User {
	private int idUser;
	private String nameUser;
	private String passUser;
	private ArrayList<Integer> userDecks;
	private ArrayList<Integer> userColection;
	public User() {
		super();
	}
	
	public User(int idUser, String nameUser, String passUser, ArrayList<Integer> userDecks,
			ArrayList<Integer> userColection) {
		super();
		this.idUser = idUser;
		this.nameUser = nameUser;
		this.passUser = passUser;
		this.userDecks = userDecks;
		this.userColection = userColection;
	}

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getPassUser() {
		return passUser;
	}
	public void setPassUser(String passUser) {
		this.passUser = passUser;
	}

	public ArrayList<Integer> getUserDecks() {
		return userDecks;
	}

	public void setUserDecks(ArrayList<Integer> userDecks) {
		this.userDecks = userDecks;
	}

	public ArrayList<Integer> getUserColection() {
		return userColection;
	}

	public void setUserColection(ArrayList<Integer> userColection) {
		this.userColection = userColection;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", nameUser=" + nameUser + ", passUser=" + passUser + ", userDecks="
				+ userDecks + ", userColection=" + userColection + "]";
	}

	
	
	
}
