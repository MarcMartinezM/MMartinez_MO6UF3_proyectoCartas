package ProjectoMongoDB.Controller;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ProjectoMongoDB.Utils.Carta;
import ProjectoMongoDB.Utils.Deck;

public class CargaDatosMongoDB {
	
	protected static ArrayList<Deck> decks = new ArrayList<Deck>();
	protected static ArrayList<Carta> cartas = new ArrayList<Carta>();
	
	private static void CargarDecks(MongoClient mgClient) {
		MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
		MongoCollection<Document> collection;
		collection = db.getCollection("Decks");
		FindIterable<Document> doc = collection.find();
		for (Document document : doc) {
			Carta[] cartasDeck;
			decks.add(new Deck(document.getInteger("deckID"), document.getString("deckName"),document.getInteger("deckValue"),cartasDeck=(Carta[]) document.get("infoCartas")));
		}
	}
	private static void CargarCartas(MongoClient mgClient) {
		MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
		MongoCollection<Document> collection;
		collection = db.getCollection("Cartas");
		FindIterable<Document> doc = collection.find();
		for (Document document : doc) {
			cartas.add(new Carta(document.getInteger("id"),document.getString("type"),document.getString("nombre_Carta"),document.getInteger("coste_invocacion"),document.getInteger("ataque"), document.getInteger("vida"),document.getString("habilidad_especial"),document.getString("faccion")));
		}
		
	}
}
