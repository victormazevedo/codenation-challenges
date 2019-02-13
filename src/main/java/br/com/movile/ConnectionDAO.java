package br.com.movile;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectionDAO {

	private static String USER = "italo";
	private static String HOST = "ds133875.mlab.com";
	private static int PORT = 33875;
	private static String PASSWORD = "italo1234";
	private static String DATA_BASE = "heroku_rxsclf8z";
	
	
	public static void main(String[] args) {
		
		 try {  
	            ServerAddress serverAddress = new ServerAddress(HOST,PORT);
	            List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
	            addrs.add(serverAddress);  
	              
	            MongoCredential credential = MongoCredential.createScramSha1Credential(USER, DATA_BASE, PASSWORD.toCharArray());  
	            List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
	            credentials.add(credential);  
	              
	            MongoClient mongoClient = new MongoClient(addrs,credentials);  
	              
	            MongoDatabase mongoDatabase = mongoClient.getDatabase(DATA_BASE); 
	            MongoCollection<Document> collection = mongoDatabase.getCollection("motoboy");
	            Document first = collection.find().first();
	            System.out.println(first.toString());
	            
	            System.out.println("Connect to database successfully");  
	        } catch (Exception e) {  
	            System.err.println( e.getClass().getName() + ": " + e.getMessage() );  
	        }  
		
		
	}

}
