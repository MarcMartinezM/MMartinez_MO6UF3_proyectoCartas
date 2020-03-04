package ProjectoMongoDB.Controller;

import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ProjectoMongoDB.Utils.User;

public class ControllerMain {
	private static void disableLogs() {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);
		mongoLogger.getLogger("org.mongodb.driver.connection").setLevel(Level.OFF);
		mongoLogger.getLogger("org.mongodb.driver.management").setLevel(Level.OFF);
		mongoLogger.getLogger("org.mongodb.driver.cluster").setLevel(Level.OFF);
		mongoLogger.getLogger("org.mongodb.driver.protocol.insert").setLevel(Level.OFF);
		mongoLogger.getLogger("org.mongodb.driver.protocol.query").setLevel(Level.OFF);
		mongoLogger.getLogger("org.mongodb.driver.protocol.update").setLevel(Level.OFF);
	}
	public static void main(String[] args) throws UnknownHostException {
		MongoClient mgClient = ConnectionMongoDB.crearConexion();
		disableLogs();
		if(mgClient!=null) {
			menuLogin(mgClient);
			
			
		}
	}
	public static void menuLogin(MongoClient mgClient) {
		boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
            System.out.println("======================");
            System.out.println("|                    |");
            System.out.println("| League of Runeterra|");
            System.out.println("| 1. Login           |");
            System.out.println("| 2. Salir           |");
            System.out.println("======================");

            try {
 
                System.out.println("Escribe una de las opciones");
                opcion = leerI();
 
                switch (opcion) {
                    case 1:
                    	if(ConfirmLogin(mgClient)!= null) {
                    	//  MenuJuego(nombreUser);
                    	}else {
                    		System.out.println("vuelve ha introducir datos:");
                    	}
                      
                        break;
                    case 2:
                    	System.out.println("adios :))))");
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                leerI();
            }
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
    private static String ConfirmLogin(MongoClient mgClient) {
    	System.out.println("introduce nombre usuario:");
    	String nombreUser = leerS();
    	
    	if(isNotNull(nombreUser)==true) {
    		System.out.println("introduce contraseña:");
    		String passUser = leerS();
    			if(isNotNull(passUser)==true) {
    				
    				if(consultaUser(mgClient, nombreUser, passUser)==true) {
    					System.out.println("usuario correcto.");
    					return nombreUser;
    				}else {
    					System.out.println("no se ha encontrado el usuario.");
    					return null;
    				}
    			}else {
    				System.out.println("no has introducido ninguna contraseña.");
    			}
    		
    	}else {
    	System.out.println("no has introduce ninguna nombre de usuario.");
    	}
    	return null;
    }
 private static Boolean isNotNull(String Compro) {
	 if(Compro == null || Compro.equals("")) {
		 return false;
	 }else {
		 return true; 
	 }
	
 }
 private static Boolean consultaUser(MongoClient mgClient,String user,String pass) {
	 MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
		MongoCollection<Document> collection;
		collection = db.getCollection("Users");
		FindIterable<Document> doc = collection.find();
		for (Document document : doc) {
		if(user.equalsIgnoreCase(document.getString("nameUser")) && pass.equalsIgnoreCase(document.getString("passUser")) ) {
			
			return true;
		}
		}
	 return false;
	}
}
	

