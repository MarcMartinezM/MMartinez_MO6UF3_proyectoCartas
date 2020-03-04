package ProjectoMongoDB.Controller;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ConnectionMongoDB {
	static MongoClient crearConexion() throws UnknownHostException {
		MongoClientURI mongoURI = new MongoClientURI(
				"mongodb+srv://MarcMartinez:superlocal@cluster0-idx6q.mongodb.net/test?retryWrites=true&w=majority");
		MongoClient conClient = new MongoClient(mongoURI);
		return conClient;
	}
	 static void disconnectMongo(MongoClient mongo) {
		mongo.close();

	}
}

