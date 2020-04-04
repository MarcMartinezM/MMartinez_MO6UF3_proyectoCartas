package ProjectoMongoDB.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Deck {
		private int deckID;
		private String deckName;
		private int deckValue;
		//private ArrayList<Carta> infoCartas;
		private ArrayList<Integer> infoCartas;
		public Deck() {
			super();
		}
		
		public Deck(int deckID, String deckName, int deckValue, ArrayList<Integer> infoCartas) {
			super();
			this.deckID = deckID;
			this.deckName = deckName;
			this.deckValue = deckValue;
			this.infoCartas = infoCartas;
		}

		public int getDeckID() {
			return deckID;
		}
		public void setDeckID(int deckID) {
			this.deckID = deckID;
		}
		public String getDeckName() {
			return deckName;
		}
		public void setDeckName(String deckName) {
			this.deckName = deckName;
		}
		public int getDeckValue() {
			return deckValue;
		}
		public void setDeckValue(int deckValue) {
			this.deckValue = deckValue;
		}

		public ArrayList<Integer> getInfoCartas() {
			return infoCartas;
		}

		public void setInfoCartas(ArrayList<Integer> infoCartas) {
			this.infoCartas = infoCartas;
		}

		@Override
		public String toString() {
			return "Deck [deckID=" + deckID + ", deckName=" + deckName + ", deckValue=" + deckValue + ", infoCartas="
					+ infoCartas + "]";
		}
		
		
		
}
