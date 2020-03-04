package ProjectoMongoDB.Utils;

import java.util.Arrays;

public class Deck {
		private int deckID;
		private String deckName;
		private int deckValue;
		private Carta[] infoCartas;
		public Deck() {
			super();
		}
		public Deck(int deckID, String deckName, int deckValue, Carta[] infoCartas) {
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
		public Carta[] getInfoCartas() {
			return infoCartas;
		}
		public void setInfoCartas(Carta[] infoCartas) {
			this.infoCartas = infoCartas;
		}
		@Override
		public String toString() {
			return "Deck [deckID=" + deckID + ", deckName=" + deckName + ", deckValue=" + deckValue + ", infoCartas="
					+ Arrays.toString(infoCartas) + "]";
		}
		
		
}
