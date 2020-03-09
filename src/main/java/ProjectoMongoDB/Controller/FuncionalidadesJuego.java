package ProjectoMongoDB.Controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ProjectoMongoDB.Utils.Deck;

public class FuncionalidadesJuego {
	
	protected static ArrayList<String> decksUsuario = new ArrayList<String>();
	
	public static void menuJuego(MongoClient mgClient,String nombreUser,int UserID) {
	
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
            System.out.println("=================================");
            System.out.println("|                               |");
            System.out.println("|       League of Runeterra     |");
            System.out.println("|                               |");
            System.out.println("|     1. Creacion de un deck.   |");
            System.out.println("|     2. importar deck.         |");
            System.out.println("|     3. Modificar un deck.     |");
            System.out.println("|     4. Consultar deck.        |");
            System.out.println("|     5. Comprar cartas.        |");
            System.out.println("|     6. Consultar Coleccion.   |"); 
            System.out.println("|     7. Salir.                 |");
            System.out.println("=================================");
            
 
            try {
 
                System.out.println("Escribe una de las opciones");
                opcion = leerI();
 
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion 1");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        break;
                    case 3:
                       
                        break;
                    case 4:
                    	 consultarDeckUsuario(mgClient, nombreUser,UserID);
                        break;
                    case 5:
                        System.out.println("Has seleccionado la opcion 3");
                        break;
                    case 6:
                        System.out.println("Has seleccionado la opcion 3");
                        break;
                    case 7:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 7");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                leerI();
            }
        }
	}
	private static void consultarDeckUsuario(MongoClient mgClient,String nombreUser,int UserID) {
	
		System.out.println("LISTA DE DECKS DEL USUARIO "+nombreUser+ " :");
		 MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
			MongoCollection<Document> collection;
			collection = db.getCollection("Users");
			FindIterable<Document> doc = collection.find();
			int cont=0;
			String[] utilNombreDeck = null;
			String[] utilIDDek = null;
			for (Document document : doc) {
				if(document.getString("nameUser").equalsIgnoreCase(nombreUser) && document.getInteger("idUser")==UserID) {
					decksUsuario.add(document.getString("userDecks"));
					System.out.println(decksUsuario.toString());
					utilNombreDeck[cont]=decksUsuario.toString();
					cont++;
				}
				
			}
			cont=0;
			String var;
			
			for (int i = 0; i < utilNombreDeck.length; i++) {
				var=utilNombreDeck[i];
				String[] parts = var.split(";");
				utilIDDek[i] = parts[0];
				utilNombreDeck[i] = parts[1];
				
			}
			boolean salir= false;
			String nombreDeck= null;
			while(!salir) {
				System.out.println("Introduce nombre del deck que quieres consultar:");
				nombreDeck = leerS();
				for (int i = 0; i < utilNombreDeck.length; i++) {
					if(utilNombreDeck[i].equals(nombreDeck) ){
						i=utilNombreDeck.length;
						salir= true;
					}
				}
			}
			
			for (Deck dk : CargaDatosMongoDB.decks) {
				for (int i = 0; i < utilIDDek.length; i++) {
					if(dk.getDeckName().equalsIgnoreCase(nombreDeck) && dk.getDeckID()==Integer.parseInt(utilIDDek[i])) {
						System.out.println(dk.toString());
					}
				}
				
			}
			
	}

	private static void consultarColectionUsuario(MongoClient mgClient,String nombreUser) {
		System.out.println("LISTA DE COLECION DEL USUARIO "+ nombreUser + " :");
		 MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
			MongoCollection<Document> collection;
			collection = db.getCollection("Users");
			FindIterable<Document> doc = collection.find();
			for (Document document : doc) {
				String deck = document.getString("userColection");
			}
	}
	
	 public static int leerI() {
	 		Scanner sc = new Scanner(System.in);
	 		return sc.nextInt();
	 	}

	 	public static float leerF() {
	 		Scanner sc = new Scanner(System.in);
	 		return sc.nextFloat();
	 	}

	 	public static String leerS() {
	 		Scanner sc = new Scanner(System.in);
	 		return sc.nextLine();
	 	}
}
