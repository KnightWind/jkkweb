package com.jkkp.modules.index.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/{client-id}")
public class ServerWebSocket  {
	
	 private static final HashMap<String,Session> clients = new HashMap<String,Session>();  
	 
     
     @OnOpen  
     public void onOpen(Session session,@PathParam("client-id") String clientId) {  
    	 session.addMessageHandler(new MessageHandler.Whole<String>() {

			@Override
			public void onMessage(String arg0) {
				// TODO Auto-generated method stub
				
			}
    		 
		}); 
    	 
    	 
    	 clients.put(clientId,session);  
    	 Set<String> ids = clients.keySet();
         try {
        	StringBuilder strs = new StringBuilder("");
        	strs.append("[");
        	for(String id:ids){
        		strs.append("\"");
        		strs.append(id);
        		strs.append("\"");
        		strs.append(",");
        	}
        	if(strs.indexOf(",") > -1){
        		strs.deleteCharAt(strs.lastIndexOf(","));
        	}
        	strs.append("]");
			for(Session s : clients.values()){
				s.getBasicRemote().sendText("init:{clients:"+strs.toString()+"}");
    		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
     }  
     
     @OnMessage  
     public void onMessage(String message,@PathParam("client-id") String clientId) {  
    	 
    	 String sendTo = getSendToClientId(message);
    	 if(sendTo == null || !clients.containsKey(sendTo)){
    		 
	    	 for (Session client : clients.values()) {  
	             try {  
	//                 client.getBasicRemote().sendObject(clientId+": "+message);              
	                 client.getBasicRemote().sendText(clientId+": " + message + "[群发]");
	             } catch (IOException e) {  
	                 e.printStackTrace();  
	//             } catch (EncodeException e) {  
	//                 e.printStackTrace();  
	             }  
	         }  
    	 }else{
    		 
    		 Session session = clients.get(sendTo);
    		 try {
				session.getBasicRemote().sendText(clientId+": " + getMsgBody(message) );
			} catch (IOException e) {
				e.printStackTrace();
			}
    	 }
     }  
     
     @OnClose  
     public void onClose(Session peer) {  
    	 if(clients.containsValue(peer)){
    		 for(Map.Entry<String, Session> entry:clients.entrySet()){
    			 
    			 if(entry.getValue() == peer){
    				 clients.remove(entry.getKey());
    			 }
    		 }
    	 }
     }  
     
     
     
     public String getSendToClientId(String msg){
    	 
    	 String[] strs = msg.split("\\|");
    	 
    	 if(strs.length > 1){
    		 
    		 return strs[1];
    	 }
    	 
    	 return null;
     }
     
     public String getMsgBody(String msg){
    	 
    	 String[] strs = msg.split("\\|");
    	 
    	 if(strs.length > 1){
    		 
    		 return strs[0];
    	 }
    	 
    	 return msg;
     }
	
}
