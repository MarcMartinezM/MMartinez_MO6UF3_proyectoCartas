package ProjectoMongoDB.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import ProjectoMongoDB.Utils.Carta;
import ProjectoMongoDB.Utils.Deck;

public class CargaDatosMongoDB {
	
	public static ArrayList<Deck> decks = new ArrayList<Deck>();

	public static ArrayList<Carta> allCartas = new ArrayList<Carta>();
	
	static void CargarDecks(MongoClient mgClient) {
		MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
		MongoCollection<Document> collection;
		collection = db.getCollection("Decks");
		
		
		collection.drop();
		JSONParser parser = new JSONParser();
		File f = new File("..\\UF3.projecto\\src\\main\\resources\\Decks.json");
		try {
			FileReader fr = new FileReader(f);
			JSONArray array = (JSONArray) parser.parse(fr);
			Iterator<?> iterator = array.iterator();
			while(iterator.hasNext()) {
				JSONObject obj = (JSONObject) iterator.next();
				Deck deck = new Deck();
				deck.setDeckID(Integer.parseInt(obj.get("deckID").toString()));
				deck.setDeckName((String) obj.get("deckName"));
				deck.setDeckValue(Integer.parseInt(obj.get("deckValue").toString()));
				deck.setInfoCartas((ArrayList<Integer>) obj.get("infoCartas"));
				Document doc = new Document("deckID",deck.getDeckID()).append("deckName", deck.getDeckName()).append("deckValue", deck.getDeckValue()).append("infoCartas", deck.getInfoCartas());
				collection.insertOne(doc);
				decks.add(deck);
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		
//		FindIterable<Document> doc = collection.find();
//	for (Document dd : doc) {
//		decks.add(new Deck(dd.getInteger("deckID"),dd.getString("deckName"),dd.getInteger("deckValue"),cartas = (ArrayList<Integer>) dd.get("infoCartas")));
//	}
//		
		
	}
	static void CargarCartas(MongoClient mgClient) {
		MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
		MongoCollection<Document> collection;
		collection = db.getCollection("Cartas");
		
		collection.drop();
		JSONParser parser = new JSONParser();
		File f = new File("..\\\\UF3.projecto\\\\src\\\\main\\\\resources\\\\Cartas.json");
		try {
			FileReader fr = new FileReader(f);
			JSONArray array = (JSONArray) parser.parse(fr);
			Iterator<?> iterator = array.iterator();
			while(iterator.hasNext()) {
				JSONObject obj = (JSONObject) iterator.next();
				Carta carta = new Carta();
				carta.setId(Integer.parseInt(obj.get("id").toString()));
				carta.setType((String) obj.get("tipo"));
				carta.setNombre_Carta((String) obj.get("nombre_carta"));
				carta.setCostCarta(Integer.parseInt(obj.get("coste_invocacion").toString()));
				carta.setAtkCarta(Integer.parseInt(obj.get("ataque").toString()));
				carta.setHpCarta(Integer.parseInt(obj.get("vida").toString()));
				carta.setSkill((String) obj.get("habilidad_especial"));
				carta.setFaccion((String) obj.get("faccion"));
				Document doc = new Document("id", carta.getId()).append("tipo", carta.getType())
						.append("nombre_carta", carta.getNombre_Carta())
						.append("coste_invocacion", carta.getCostCarta()).append("ataque", carta.getAtkCarta())
						.append("vida", carta.getHpCarta()).append("habilidad_especial", carta.getSkill())
						.append("faccion", carta.getFaccion());
				collection.insertOne(doc);
				allCartas.add(carta);
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
//		FindIterable<Document> doc = collection.find();
//		for (Document document : doc) {
//			allCartas.add(new Carta(document.getInteger("id"),document.getString("tipo"),document.getString("nombre_carta"),document.getInteger("coste_invocacion"),document.getInteger("ataque"), document.getInteger("vida"),document.getString("habilidad_especial"),document.getString("faccion")));
//		}
		
	}
}
