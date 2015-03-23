package model.schat;


import java.util.ArrayList;

import serializer.json.JsonMessageList;

/**
 * @author Hayden
 * Chat contains a list of messages that have been sent over the chat interface
 */
public class ServerChat {

    /**The list of chat messages*/
    private ArrayList<ServerMessage> chatMessages;
    
    public ServerChat() {
    	chatMessages = new ArrayList<ServerMessage>();
    }

    /**
     * Creates a chat object with the list of messages that have been
     * previously sent in case a player joins the game late.
     * @param chatMessages The list of messages sent over the chat interface
     */
    public ServerChat(ArrayList<ServerMessage> chatMessages)
    {
        this.chatMessages = chatMessages;
    }

    public ServerChat(JsonMessageList newChat)
    {
        chatMessages = new ArrayList<ServerMessage>();
        for(int i = 0; i < newChat.getLines().length; i++)
        {
            chatMessages.add(new ServerMessage(newChat.getLines()[i].getSource(), newChat.getLines()[i].getMessage()));
        }
    }

    public ArrayList<ServerMessage> getChatMessages()
    {
        return chatMessages;
    }
    
    public void sendChat(String owner, String message)
    {
    	chatMessages.add(new ServerMessage(owner, message));
    }
}
