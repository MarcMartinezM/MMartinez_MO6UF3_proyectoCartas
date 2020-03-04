package ProjectoMongoDB.Utils;

import java.util.Arrays;

public class User {
	private int idUser;
	private String nameUser;
	private String passUser;
	private Deck[] userDecks;
	private Carta[] userColection;
	public User() {
		super();
	}
	public User(int idUser, String nameUser, String passUser, Deck[] userDecks, Carta[] userColection) {
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
	public Deck[] getUserDecks() {
		return userDecks;
	}
	public void setUserDecks(Deck[] userDecks) {
		this.userDecks = userDecks;
	}
	public Carta[] getUserColection() {
		return userColection;
	}
	public void setUserColection(Carta[] userColection) {
		this.userColection = userColection;
	}
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", nameUser=" + nameUser + ", passUser=" + passUser + ", userDecks="
				+ Arrays.toString(userDecks) + ", userColection=" + Arrays.toString(userColection) + "]";
	}
	
	
}
