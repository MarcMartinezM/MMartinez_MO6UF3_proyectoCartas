package ProjectoMongoDB.Controller;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import ProjectoMongoDB.Utils.Carta;
import ProjectoMongoDB.Utils.Deck;

public class CargaDatosMongoDB {
	
	public static ArrayList<Deck> decks = new ArrayList<Deck>();
	protected static ArrayList<Integer> cartas = new ArrayList<Integer>();
	public static ArrayList<Carta> allCartas = new ArrayList<Carta>();
	static void CargarDecks(MongoClient mgClient) {
		MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
		MongoCollection<Document> collection;
		collection = db.getCollection("Decks");
	
		FindIterable<Document> doc = collection.find();
	for (Document dd : doc) {
		decks.add(new Deck(dd.getInteger("deckID"),dd.getString("deckName"),dd.getInteger("deckValue"),cartas = (ArrayList<Integer>) dd.get("infoCartas")));
	}
		
		
	}
	static void CargarCartas(MongoClient mgClient) {
		MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
		MongoCollection<Document> collection;
		collection = db.getCollection("Cartas");
		FindIterable<Document> doc = collection.find();
		for (Document document : doc) {
			allCartas.add(new Carta(document.getInteger("id"),document.getString("tipo"),document.getString("nombre_carta"),document.getInteger("coste_invocacion"),document.getInteger("ataque"), document.getInteger("vida"),document.getString("habilidad_especial"),document.getString("faccion")));
		}
		
	}
}
