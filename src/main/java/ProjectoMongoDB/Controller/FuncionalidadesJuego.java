package ProjectoMongoDB.Controller;

import java.util.ArrayList;

import java.util.InputMismatchException;

import java.util.Random;
import java.util.Scanner;



import org.bson.Document;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import ProjectoMongoDB.Utils.Carta;
import ProjectoMongoDB.Utils.Deck;
import ProjectoMongoDB.Utils.User;

public class FuncionalidadesJuego {
	
	public static ArrayList<Integer> decksUsuario = new ArrayList<Integer>();
	public static ArrayList<Integer> colectionUsuario = new ArrayList<Integer>();
	public static MongoCursor<Document> result;
	public static User u = new User();
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
                        crearDeckUsuario(mgClient, UserID);
                        break;
                    case 2:
                        importarDeck(mgClient, UserID);
                        break;
                    case 3:
                       modificarDeck(mgClient,UserID);
                        break;
                    case 4:
                    	 consultarDeckUsuario(nombreUser);
                        break;
                    case 5:
                        comprarCartas(mgClient , UserID);
                        break;
                    case 6:
                        consultarColectionUsuario(nombreUser);
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
	static void modificarDeck(MongoClient mgClient,int userID) {
		System.out.println("ID de los deck que tiene el usuario.");
		System.out.println(decksUsuario.toString());
		System.out.println("introduce el ID del deck que desea modificar:");
		int deckUserEle= leerI();
		for (Integer integer :decksUsuario) {
			if(integer==deckUserEle) {
				boolean salir = false;
		        int opcion; //Guardaremos la opcion del usuario
		 
		        while (!salir) {
		 
		            System.out.println("1.Modificar el nombre.");
		            System.out.println("2.Modificar las cartas.");
		            System.out.println("3.Salir");
		 
		            try {
		 
		                System.out.println("Escribe una de las opciones");
		                opcion = leerI();
		 
		                switch (opcion) {
		                    case 1:
		                        System.out.println("introduce el nuevo nombre para el deck:");
		                        String nuevoNombre= leerS();
		                        for (Deck dd: CargaDatosMongoDB.decks) {
		                        	if(dd.getDeckID()==deckUserEle) {
		                        		dd.setDeckName(nuevoNombre);
		                        	}
								}
		                    	MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
		    					MongoCollection<Document> collection;
		    					collection = db.getCollection("Decks");
		    					Document find = new Document ("deckID",deckUserEle);
		    					Document update = new Document("$set",new Document("deckName",nuevoNombre));
		    					collection.findOneAndUpdate(find, update);
		    					System.out.println("El nombre del deck a sido modificado.");
		                        break;
		                    case 2:
		                    	modificarCartasDeckUser(mgClient,deckUserEle);
		                        break;
		                   
		                    case 3:
		                        salir = true;
		                        break;
		                    default:
		                        System.out.println("Solo números entre 1 y 3");
		                }
		            } catch (InputMismatchException e) {
		                System.out.println("Debes insertar un número");
		                leerI();
		            }
		        }
			}
		}
		
	}
	static void modificarCartasDeckUser(MongoClient mgClient,int deckUserEle) {
		boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
 
            System.out.println("1.insertar carta");
            System.out.println("2. eliminar carta");
            System.out.println("3. Salir");
 
            try {
 
                System.out.println("Escribe una de las opciones");
                opcion = leerI();
 
                switch (opcion) {
                
                    case 1:
                    	int dema=0,nox=0,pz=0,isla=0,jon=0,fre=0;
                    	 for (Deck dd : CargaDatosMongoDB.decks) {
     						if(dd.getDeckID()==deckUserEle) {
     							if(dd.getDeckValue()==60 || dd.getInfoCartas().size()==20) {
     								System.out.println("NO SE PUEDEN AÑADIR MAS CARTAS");
     							}else {
     								for(int inf : dd.getInfoCartas()) {
     									for(Carta cc : CargaDatosMongoDB.allCartas) {
     										if(inf == cc.getId()) {
     											if(cc.getFaccion().equalsIgnoreCase("demacia")) {
     												dema++;
     											}else if(cc.getFaccion().equalsIgnoreCase("noxus")) {
     												nox++;
     											}else if(cc.getFaccion().equalsIgnoreCase("piltover y zaun")) {
     												pz++;
     											}else if(cc.getFaccion().equalsIgnoreCase("islas de las sombras")) {
     												isla++;
     											}else if(cc.getFaccion().equalsIgnoreCase("jonia")) {
     												jon++;
     											}else {
     												fre++;
     											}
     										}
     									}
     								}
     								int total=dema+nox+pz+isla+jon+fre;
     								String faccionDeck = null;
     								if(!(dema==0)) {
     									faccionDeck="demacia";
     								}else if(!(nox==0)) {
     									faccionDeck="noxus";
     								}else if(!(pz==0)) {
     									faccionDeck="piltover y zaun";
     								}else if(!(isla==0)) {
     									faccionDeck="islas de las sombras";
     								}else if(!(jon==0)) {
     									faccionDeck="jonia";
     								}else if(!(fre==0)) {
     									faccionDeck="freljord";
     								}
     								int cont=0;
     								for(int inf : dd.getInfoCartas()) {
     									for (Integer integer : colectionUsuario) {
     										for(Carta cc : CargaDatosMongoDB.allCartas) {
     											if(inf==integer && faccionDeck.equalsIgnoreCase(cc.getFaccion())) {
    												cont++;
    											}
     										}
											
										}
     								}
     								
     								if(cont==total) {
     									System.out.println("estas son las cartas que puedes anadir de la faccion del deck:");
     									int valorDeCartaAnadir=dd.getDeckValue()-60;
     									for(int inf : dd.getInfoCartas()) {
     										for (Integer integer : colectionUsuario) {
     											for(Carta cc : CargaDatosMongoDB.allCartas) {
     												if(inf==integer &&integer==cc.getId()&& faccionDeck.equalsIgnoreCase(cc.getFaccion()) &&cc.getCostCarta()>=valorDeCartaAnadir) {
     													System.out.println(cc.toString());
     												}
     											}
											}
     									}
     									int capNumCarta=0;
     									int coste=0;
     			     					System.out.println("introduce la id de la carta que desea añadir:");
     			     					int idCartaAnadir=leerI();
     			     					boolean laTiene=false;
     			     					for(int inf : dd.getInfoCartas()) {
     										for (Integer integer : colectionUsuario) {
     											for(Carta cc : CargaDatosMongoDB.allCartas) {
     												if(inf==integer&&idCartaAnadir==inf &&integer==cc.getId()&& faccionDeck.equalsIgnoreCase(cc.getFaccion()) &&cc.getCostCarta()>=valorDeCartaAnadir) {
     													capNumCarta++;
     													laTiene=true;
     													coste=cc.getCostCarta();
     												}
     											}
											}
     									}
     	
     			     					if(capNumCarta<=2) {
     			     						System.out.println("no se pueden anadir mas de esas cartas.");
     			     					}else {
     			     						if(laTiene=true) {
     			     							dd.setDeckValue(dd.getDeckValue()+coste);
     			     							
     			     							
     			     							ArrayList<Integer> deckModificado=dd.getInfoCartas();
     			     							deckModificado.add(idCartaAnadir);
     			     							dd.setInfoCartas(deckModificado);
     			     							MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
     			     							MongoCollection<Document> collection;
     			     							collection = db.getCollection("Decks");
     			     							Document find = new Document ("deckID",deckUserEle);
     					    					Document update = new Document("$set",new Document("infoCartas",dd.getInfoCartas()));
     					    					collection.findOneAndUpdate(find, update);
     					    					
     					    					collection = db.getCollection("Decks");
     					    					find = new Document ("deckID",deckUserEle);
     					    					 update = new Document("$set",new Document("deckValue",dd.getDeckValue()));
     					    					collection.findOneAndUpdate(find, update);
     					    					System.out.println("carta anadida dentro del deck.");
     			     						}
     			     					}
     								}else {
     									String segundaFaccionDeck=null;
     									cont=total-cont;
     									if(cont==dema) {
     										segundaFaccionDeck="demacia";
     									}else if(cont==nox) {
     										segundaFaccionDeck="noxus";
     									}else if(cont==pz) {
     										segundaFaccionDeck="piltover y zaun";
     									}else if(cont==isla) {
     										segundaFaccionDeck="islas de las sombras";
     									}else if(cont==jon) {
     										segundaFaccionDeck="jonia";
     									}else if(cont==fre) {
     										segundaFaccionDeck="freljord";
     									}
     									System.out.println("estas son las cartas que puedes anadir de la faccion del deck:");
     									int valorDeCartaAnadir=60-dd.getDeckValue();
     									for(int inf : dd.getInfoCartas()) {
     										for (Integer integer : colectionUsuario) {
     											for(Carta cc : CargaDatosMongoDB.allCartas) {
     												if(inf==integer && integer==cc.getId()&&(faccionDeck.equalsIgnoreCase(cc.getFaccion())|| segundaFaccionDeck.equalsIgnoreCase(cc.getFaccion()))&&cc.getCostCarta()>=valorDeCartaAnadir) {
     													System.out.println(cc.toString());
     												}
     											}
											}
     									}
     									int capNumCarta=0;
     									int coste=0;
     			     					System.out.println("introduce la id de la carta que desea añadir:");
     			     					int idCartaAnadir=leerI();
     			     					boolean laTiene=false;
     			     					for(int inf : dd.getInfoCartas()) {
     										for (Integer integer : colectionUsuario) {
     											for(Carta cc : CargaDatosMongoDB.allCartas) {
     												if(inf==integer&&idCartaAnadir==inf &&  integer==cc.getId()&&(faccionDeck.equalsIgnoreCase(cc.getFaccion())|| segundaFaccionDeck.equalsIgnoreCase(cc.getFaccion())) &&cc.getCostCarta()>=valorDeCartaAnadir) {
     													capNumCarta++;
     													laTiene=true;
     													coste=cc.getCostCarta();
     												}
     											}
											}
     									}
     			     					System.out.println(capNumCarta);
     			     					if(capNumCarta>=2) {
     			     						System.out.println("no se pueden anadir mas de esas cartas.");
     			     					}else {
     			     						if(laTiene=true) {
     			     							dd.setDeckValue(dd.getDeckValue()+coste);
     			     							
     			     							
     			     							ArrayList<Integer> deckModificado=dd.getInfoCartas();
     			     							deckModificado.add(idCartaAnadir);
     			     							dd.setInfoCartas(deckModificado);
     			     							MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
     			     							MongoCollection<Document> collection;
     			     							collection = db.getCollection("Decks");
     			     							Document find = new Document ("deckID",deckUserEle);
     					    					Document update = new Document("$set",new Document("infoCartas",dd.getInfoCartas()));
     					    					collection.findOneAndUpdate(find, update);
     					    					
     					    					collection = db.getCollection("Decks");
     					    					find = new Document ("deckID",deckUserEle);
     					    					 update = new Document("$set",new Document("deckValue",dd.getDeckValue()));
     					    					collection.findOneAndUpdate(find, update);
     					    					System.out.println("carta anadida dentro del deck.");
     			     						}
     			     					}
     									
     									
     								}
     							}
     						}
     						}
           
                        break;
                    case 2:
                    	System.out.println("todas las cartas que tiene el deck:");
                       for (Deck dd : CargaDatosMongoDB.decks) {
						if(dd.getDeckID()==deckUserEle) {
							for(int dc : dd.getInfoCartas()) {
								for(Carta idCarta: CargaDatosMongoDB.allCartas) {
									if(dc==idCarta.getId()) {
										System.out.println(idCarta.toString());
									}
								}
							}
						}
					}
                       int costeCarta=0;
                       System.out.println("introduce la ID de la carta a eliminar:");
                       int idCartaEliminar=leerI();
                       for (Deck dd : CargaDatosMongoDB.decks) {
                    	   if(dd.getDeckID()==deckUserEle) {
                    		   for(int dc : dd.getInfoCartas()) {
                    			   for(Carta idCarta: CargaDatosMongoDB.allCartas) {
   									if(dc==idCarta.getId()) {
   										costeCarta=idCarta.getCostCarta();
   									}
   									}
                    			   if(dc==idCartaEliminar) {
                    				   dd.setDeckValue(dd.getDeckValue()-costeCarta);
                    				   
                    				   ArrayList<Integer> idEliminarArray= new ArrayList<Integer>();
                    				   idEliminarArray= dd.getInfoCartas();
                    				   for (int i = 0; i < idEliminarArray.size(); i++) {
										if(idEliminarArray.get(i)==idCartaEliminar) {
											idEliminarArray.remove(i);
										}
									}
                    				  
                    				   
                    				   dd.setInfoCartas(idEliminarArray);
                    				   MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
       		    					MongoCollection<Document> collection;
       		    					collection = db.getCollection("Decks");
       		    					Document find = new Document ("deckID",deckUserEle);
       		    					Document update = new Document("$set",new Document("deckValue",dd.getDeckValue()));
    		    					collection.findOneAndUpdate(find, update);
    		    					update = new Document("$set",new Document("infoCartas",dd.getInfoCartas()));
    		    					collection.findOneAndUpdate(find, update);
    		    					break;
                    			   }
                    			   
                    		   }
                    	   }
					}
                        break;
                   
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                leerI();
            }
        }
	}
	static void  importarDeck(MongoClient mgClient,int userID) {
		ArrayList<String> codigosAuto= new ArrayList<String>();
		String[] parts;
		String nombreDeckPrede;
		int idDeckDeseado = 0;
		System.out.println("DECKS PREDETERMINADOS");
		for (Deck forDeckPrede : CargaDatosMongoDB.decks) {
			nombreDeckPrede= forDeckPrede.getDeckName();
			 parts= nombreDeckPrede.split("-");
			if(parts[0].equalsIgnoreCase("Predeterminado")) {
				String codigoAutogenerado=givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect();
				System.out.println(nombreDeckPrede+" codigo para importar: "+codigoAutogenerado);
				codigosAuto.add(codigoAutogenerado+"/"+nombreDeckPrede);
			}
		}
		System.out.println("Que deck predeterminado desea:");
		boolean salir=false;
		while(!salir) {
			String deckDeseado= leerS();
			
			for (String string : codigosAuto) {
				parts= string.split("/");
				if(parts[0].equals(deckDeseado)) {
					salir=true;
				}
			}
			if(salir==true) {
				System.out.println("desea ver los datos del deck: introduce SI o NO");
				String verDatos=leerS();
				
				if(verDatos.equalsIgnoreCase("si")) {
					
					for (String string : codigosAuto) {
						parts= string.split("/");
						for (Deck forDeckPrede : CargaDatosMongoDB.decks) {
							if(parts[0].equalsIgnoreCase(deckDeseado)&&parts[1].equalsIgnoreCase(forDeckPrede.getDeckName())) {
								 idDeckDeseado=forDeckPrede.getDeckID();
								System.out.println(forDeckPrede.toString());
							}
						}
					}
					
					boolean salir2=false;
					while(!salir2) {
						for (int tieneElDeck : decksUsuario) {
							if(tieneElDeck==idDeckDeseado) {
								System.out.println("no se puede importar un deck que el usuario ya tiene:");
								salir2=true;
							}
						}
						if(salir2==false) {
							int tieneLasCartas=0;
							
							for (Deck forDeckPrede : CargaDatosMongoDB.decks) {
								if(forDeckPrede.getDeckID()==idDeckDeseado) {
									for(int info : forDeckPrede.getInfoCartas()) {
										for (int coll : colectionUsuario) {
											
											if(coll==info ) {
												tieneLasCartas++;
											}
										}
									}
									
									if(tieneLasCartas==forDeckPrede.getInfoCartas().size()) {
										System.out.println("el usuario tiene las cartas necesarias para importar el mazo.");
										System.out.println("se va a realizar la importacion.");
										decksUsuario.add(idDeckDeseado);
										u.setUserDecks(decksUsuario);
										
										MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
				    					MongoCollection<Document> collection;
				    					collection = db.getCollection("Users");
				    					Document find = new Document ("idUser", userID);
				    					Document update = new Document("$set",new Document("userDecks",u.getUserDecks()));
				    					collection.findOneAndUpdate(find, update);
				    					System.out.println("El deck a sido importado.");
										salir2=true;
									}else {
										System.out.println("el usuario no tiene las cartas necesarias para importar el mazo.");
										salir2=true;
									}
								}
								
							}
							
						}
					}

				}else if(verDatos.equalsIgnoreCase("no")) {
					for (String string : codigosAuto) {
						parts= string.split("/");
						for (Deck forDeckPrede : CargaDatosMongoDB.decks) {
							if(parts[0].equalsIgnoreCase(deckDeseado)&&parts[1].equalsIgnoreCase(forDeckPrede.getDeckName())) {
								 idDeckDeseado=forDeckPrede.getDeckID();
								
							}
						}
					}
					boolean salir2=false;
					while(!salir2) {
						for (int tieneElDeck : decksUsuario) {
							if(tieneElDeck==idDeckDeseado) {
								System.out.println("no se puede importar un deck que el usuario ya tiene:");
								salir2=true;
							}
						}
						if(salir2==false) {
							int tieneLasCartas=0;
							
							for (Deck forDeckPrede : CargaDatosMongoDB.decks) {
								if(forDeckPrede.getDeckID()==idDeckDeseado) {
									for(int info : forDeckPrede.getInfoCartas()) {
										for (int coll : colectionUsuario) {
											
											if(coll==info ) {
												tieneLasCartas++;
											}
										}
									}
									
									if(tieneLasCartas==forDeckPrede.getInfoCartas().size()) {
										System.out.println("el usuario tiene las cartas necesarias para importar el mazo.");
										System.out.println("se va a realizar la importacion.");
										decksUsuario.add(idDeckDeseado);
										u.setUserDecks(decksUsuario);
										
										MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
				    					MongoCollection<Document> collection;
				    					collection = db.getCollection("Users");
				    					Document find = new Document ("idUser", userID);
				    					Document update = new Document("$set",new Document("userDecks",u.getUserDecks()));
				    					collection.findOneAndUpdate(find, update);
				    					System.out.println("El deck a sido importado.");
										salir2=true;
									}else {
										System.out.println("el usuario no tiene las cartas necesarias para importar el mazo.");
										salir2=true;
									}
								}
								
							}
							
						}
					}
					
				}else {
					System.out.println("no has introducido ninguna de las opciones correcta.");
				}
			}else {
				System.out.println("no has introducido un codigo correcto introduce uno correcto:");
			}
		}
		
	}
	public static  String givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 
	    return generatedString;
	}
	static void  crearDeckUsuario(MongoClient mgClient,int userID) {
		System.out.println("introduce nombre del deck que desea crear:");
		String nombreNewDeck = leerS();
		boolean salir = false;
		while(!salir) {
			System.out.println("desea 1 o 2 facciones:");
			int numFaccion= leerI();
			
			
			if(numFaccion == 1) {
				
				System.out.println("que faccion desea:");
				System.out.println("Demacia--Noxus--Piltover y Zaun--Islas de las sombras--Jonia--Freljord");
				String faccionElegida= leerS();
				faccionElegidaDatos(faccionElegida);
				int costeMaximo=0;
				ArrayList<Integer> cantidadCartas= new ArrayList<Integer>();
				while(cantidadCartas.size()<20 && costeMaximo<60) {
					
					System.out.println("Que carta desea añadir(INTRODUCE LA ID,SE REQUIERE MINIMO 1 LEGENDARIA SINO NO SE CREARA EL DECK) --si añades un -1 termina la creacion-- :");
					System.out.println("cantidad de cartas actual: "+cantidadCartas.size());
					System.out.println("coste maximo de cartas actual: "+ costeMaximo);
					int idCartaAnadir=leerI();
					int contadorMaximo=0;
					
					if(idCartaAnadir==-1) {
					break;	
					}else {
						for (Integer ct : u.getUserColection()) {
							
							
							for (Carta ct2 : CargaDatosMongoDB.allCartas) {
								if(ct == idCartaAnadir && ct2.getFaccion().equalsIgnoreCase(faccionElegida) && idCartaAnadir == ct2.getId()) {
								
									
									for(Integer maximoCartas:cantidadCartas) {
										if(idCartaAnadir == maximoCartas) {
											contadorMaximo++;
										}
									}
									System.out.println(contadorMaximo);
									if(contadorMaximo>=2) {
										System.out.println("no se pueden anadir mas cartas con esa id.");
									
									}else {
										contadorMaximo=2;
										cantidadCartas.add(idCartaAnadir);
										costeMaximo = costeMaximo+ct2.getCostCarta();
								
									}
									
								}
							}
							
						}
					}
					
				}

				int esHeroe=0;
				for (Integer ct : cantidadCartas) {
					for (Carta ct2 : CargaDatosMongoDB.allCartas) {
						if(ct == ct2.getId() || ct2.getType().equalsIgnoreCase("Heroe")) {
							esHeroe++;
						}
					}
				}
				if(esHeroe<=1) {
					MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
					MongoCollection<Document> collection;
					collection = db.getCollection("Decks");
					 FindIterable<Document> docs = collection.find();
					 int ultimaID=0;
					 for (Document document : docs) {
						 ultimaID = document.getInteger("deckID");
					}
					Deck newDeck = new Deck(ultimaID+1,nombreNewDeck,costeMaximo,cantidadCartas);
					Document addDeck = new Document("deckID",newDeck.getDeckID()).append("deckName",newDeck.getDeckName()).append("deckValue",newDeck.getDeckValue()).append("infoCartas",newDeck.getInfoCartas());
					collection.insertOne(addDeck);
					System.out.println("deck creado correctamente.");
					
					
					decksUsuario.add(ultimaID+1);
					u.setUserDecks(decksUsuario);
					collection =db.getCollection("Users");
					Document find = new Document ("idUser",userID);
					Document update = new Document("$set",new Document("userDecks",u.getUserDecks()));
					collection.findOneAndUpdate(find, update);
					System.out.println("añadida deck a la coleccion del usuario.");
				}else {
					System.out.println("ERROR: no has introducido ni 1 solo heroe.");
					salir=true;
				}
				
				salir = true;
				
				
				
				
			}else if(numFaccion == 2) {
				
				System.out.println("que faccion desea:");
				System.out.println("Demacia--Noxus--Piltover y Zaun--Islas de las sombras--Jonia--Freljord");
				String faccionElegida= leerS();
				System.out.println("que faccion desea com segunda:");
				System.out.println("Demacia--Noxus--Piltover y Zaun--Islas de las sombras--Jonia--Freljord");
				String faccionElegida2= leerS();
				if((faccionElegida2.equalsIgnoreCase("demacia") || faccionElegida2.equalsIgnoreCase("noxus") || faccionElegida2.equalsIgnoreCase("Piltover y Zaun") || faccionElegida2.equalsIgnoreCase("Islas de las sombras") || faccionElegida2.equalsIgnoreCase("jonia") ||faccionElegida2.equalsIgnoreCase("Freljord"))&&(faccionElegida.equalsIgnoreCase("demacia") || faccionElegida.equalsIgnoreCase("noxus") || faccionElegida.equalsIgnoreCase("Piltover y Zaun") || faccionElegida.equalsIgnoreCase("Islas de las sombras") || faccionElegida.equalsIgnoreCase("jonia") ||faccionElegida.equalsIgnoreCase("Freljord"))) {
					faccionElegidaDosDatos(faccionElegida,faccionElegida2);
				}else {
					System.out.println("no has introducido bien 1 o ninguna facion correcte se introducira por defecto DEMACIA para la primera y NOXUS para la segunda.");
					faccionElegida="demacia";
					faccionElegida2="Noxus";
					faccionElegidaDosDatos(faccionElegida,faccionElegida2);
				}
				
				
				int costeMaximo=0;
				ArrayList<Integer> cantidadCartas= new ArrayList<Integer>();
				while(cantidadCartas.size()<20 && costeMaximo<60) {
					System.out.println("Que carta desea añadir(INTRODUCE LA ID,SE REQUIERE MINIMO 1 LEGENDARIA SINO NO SE CREARA EL DECK) --si añades un -1 termina la creacion-- :");
					System.out.println("cantidad de cartas actual: "+cantidadCartas.size());
					System.out.println("coste maximo de cartas actual: "+ costeMaximo);
					int idCartaAnadir=leerI();
					int contadorMaximo=0;
					
					if(idCartaAnadir==-1) {
					break;	
					}else {
						for (Integer ct : u.getUserColection()) {
							
							
							for (Carta ct2 : CargaDatosMongoDB.allCartas) {
								if(ct == idCartaAnadir && (ct2.getFaccion().equalsIgnoreCase(faccionElegida)||ct2.getFaccion().equalsIgnoreCase(faccionElegida2)) && idCartaAnadir == ct2.getId()) {
									
									
									for(Integer maximoCartas:cantidadCartas) {
										if(idCartaAnadir == maximoCartas) {
											contadorMaximo++;
										}
									}
									System.out.println(contadorMaximo);
									if(contadorMaximo>=2) {
										System.out.println("no se pueden anadir mas cartas con esa id.");
									
									}else {
										contadorMaximo=2;
										cantidadCartas.add(idCartaAnadir);
										costeMaximo = costeMaximo+ct2.getCostCarta();
								
									}
									
								}
							}
							
						}
					}
					
				}

				
				int esHeroe=0;
				for (Integer ct : cantidadCartas) {
					for (Carta ct2 : CargaDatosMongoDB.allCartas) {
						if(ct == ct2.getId() || ct2.getType().equalsIgnoreCase("Heroe")) {
							esHeroe++;
						}
					}
				}
				if(esHeroe<=2) {
					MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
					MongoCollection<Document> collection;
					collection = db.getCollection("Decks");
					 FindIterable<Document> docs = collection.find();
					 int ultimaID=0;
					 for (Document document : docs) {
						 ultimaID = document.getInteger("deckID");
					}
					Deck newDeck = new Deck(ultimaID+1,nombreNewDeck,costeMaximo,cantidadCartas);
					Document addDeck = new Document("deckID",newDeck.getDeckID()).append("deckName",newDeck.getDeckName()).append("deckValue",newDeck.getDeckValue()).append("infoCartas",newDeck.getInfoCartas());
					collection.insertOne(addDeck);
					System.out.println("deck creado correctamente.");
					
					
					decksUsuario.add(ultimaID+1);
					u.setUserDecks(decksUsuario);
					collection =db.getCollection("Users");
					Document find = new Document ("idUser",userID);
					Document update = new Document("$set",new Document("userDecks",u.getUserDecks()));
					collection.findOneAndUpdate(find, update);
					System.out.println("añadida deck a la coleccion del usuario.");
				}else {
					System.out.println("ERROR: no has introducido ni 1 solo heroe.");
					salir=true;
				}
				salir = true;
			}else{
				System.out.println("error debe elegir una opcion correcta.");
			}
		}
		
	}
	
	static String faccionElegidaDatos(String faccionElegida) {
		if(faccionElegida.equalsIgnoreCase("demacia") || faccionElegida.equalsIgnoreCase("noxus") || faccionElegida.equalsIgnoreCase("Piltover y Zaun") || faccionElegida.equalsIgnoreCase("Islas de las sombras") || faccionElegida.equalsIgnoreCase("jonia") ||faccionElegida.equalsIgnoreCase("Freljord")) {
			System.out.println("estas son todas las cartas que posses de faccion:");
			for (Integer ct2 :u.getUserColection()) {
			for (Carta ct : CargaDatosMongoDB.allCartas) {
				
					if(ct.getFaccion().equalsIgnoreCase(faccionElegida) && ct.getId() == ct2) {
						
						System.out.println(ct.toString());
					}
				}
			}
			return faccionElegida;
		}else {
			System.out.println("no has introduce ninguna faccion, por defecto sera DEMACIA.");
			return faccionElegida= "demacia";
		}
	}
	static void faccionElegidaDosDatos(String faccionElegida,String faccionElegida2) {
		if(faccionElegida.equalsIgnoreCase("demacia") || faccionElegida.equalsIgnoreCase("noxus") || faccionElegida.equalsIgnoreCase("Piltover y Zaun") || faccionElegida.equalsIgnoreCase("Islas de las sombras") || faccionElegida.equalsIgnoreCase("jonia") ||faccionElegida.equalsIgnoreCase("Freljord")) {
			if(faccionElegida2.equalsIgnoreCase("demacia") || faccionElegida2.equalsIgnoreCase("noxus") || faccionElegida2.equalsIgnoreCase("Piltover y Zaun") || faccionElegida2.equalsIgnoreCase("Islas de las sombras") || faccionElegida2.equalsIgnoreCase("jonia") ||faccionElegida2.equalsIgnoreCase("Freljord")) {
				System.out.println("estas son todas las cartas que posses de las facciones:");
				for (Integer ct2 :u.getUserColection()) {
				for (Carta ct : CargaDatosMongoDB.allCartas) {
					
						if(ct.getFaccion().equalsIgnoreCase(faccionElegida) && ct.getId() == ct2) {
							
							System.out.println(ct.toString());
						}
					}
				}
				for (Integer ct2 :u.getUserColection()) {
					for (Carta ct : CargaDatosMongoDB.allCartas) {
						
							if(ct.getFaccion().equalsIgnoreCase(faccionElegida2) && ct.getId() == ct2) {
								
								System.out.println(ct.toString());
							}
						}
					}
			}
			
		
		}else {
			System.out.println("no has introduce ninguna faccion");
			
		}
	}
	
	static void comprarCartas(MongoClient mgClient,int userID) {
		boolean salir = false;
		String opcion;
		while(!salir) {
			System.out.println("quieres visualizar todoas las cartas antes?? introduce SI  o NO.");
			opcion = leerS();
			if(opcion.equalsIgnoreCase("SI")) {
				for (Carta ct : CargaDatosMongoDB.allCartas) {
					System.out.println(ct.toString());
				}
				salir = true;
			}else if(opcion.equalsIgnoreCase("NO")) {
				salir = true;
			}else {
				System.out.println("no has introduce lo deseado.");
			}
		}
		System.out.println("introduce id de la carta que deseas comprar:");
		int idCartaAComprar= leerI();
	
		for (Carta ct : CargaDatosMongoDB.allCartas) {
			if(ct.getId() == idCartaAComprar) {
				colectionUsuario.add(idCartaAComprar);
				u.setUserColection(colectionUsuario);
				
			}
		}
		
		 MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
			MongoCollection<Document> collection;
			collection = db.getCollection("Users");
			Document find = new Document ("idUser",userID);
			Document update = new Document("$set",new Document("userColection",u.getUserColection()));
			collection.findOneAndUpdate(find, update);
			System.out.println("añadida carta a la coleccion.");
	}
	static void DatosUsers(MongoClient mgClient,String nombreUser,int UserID) {
		 MongoDatabase db = mgClient.getDatabase("LeagueOfRuneterra");
			MongoCollection<Document> collection;
			collection = db.getCollection("Users");
			
			Document find = new Document("idUser",UserID);
			result = collection.find(find).iterator();
			if(result.hasNext()) {
				Document dd= result.next();
				if(dd.getString("nameUser").equalsIgnoreCase(nombreUser)) {
					u.setIdUser(dd.getInteger("idUser"));
					u.setNameUser(dd.getString("nameUser"));
					u.setPassUser(dd.getString("passUser"));
					decksUsuario = (ArrayList<Integer>) dd.get("userDecks");
					u.setUserDecks(decksUsuario);
					colectionUsuario = (ArrayList<Integer>) dd.get("userColection");
					u.setUserColection(colectionUsuario);
				}
			
			}
			menuJuego(mgClient, nombreUser, UserID);
	}
	private static void consultarDeckUsuario(String nombreUser) {
	
		System.out.println("LISTA DE DECKS DEL USUARIO "+nombreUser+ " :");
		for (Integer id : u.getUserDecks()) {
			for(Deck idDeck : CargaDatosMongoDB.decks) {
				if(id==idDeck.getDeckID()) {
					System.out.println(idDeck.getDeckID()+"-"+idDeck.getDeckName());
				}
			}
		}
			
			boolean salir= false;
			int idDeck= 0;
			while(!salir) {
				System.out.println("Introduce id del deck que quieres consultar:");
				idDeck = leerI();
				for (Integer id : u.getUserDecks()) {
					if(id == idDeck) {
						salir=true;
					}
				}
				if(salir==true) {
					for (Deck dk : CargaDatosMongoDB.decks) {
						
						for (Integer id : u.getUserDecks()) {
							
							if(dk.getDeckID() ==id && id==idDeck) {
								System.out.println(dk.toString());
							}
						}
						
						
					}
				}else {
					System.out.println("el deck no ha sido encontrado.");
				}
				
			}
			
			
			
	}

	private static void consultarColectionUsuario(String nombreUser) {
		System.out.println("LISTA DE COLECION DEL USUARIO "+ nombreUser + " :");
		for (Carta ct : CargaDatosMongoDB.allCartas) {
			for(Integer id : u.getUserColection()) {
				if(ct.getId() == id) {
					System.out.println(ct.toString());
				}
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
}
