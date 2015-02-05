package model.chat;


import java.util.ArrayList;

import serializer.json.JsonMessageList;
import serializer.json.JsonMessageLine;

/**
 * @author Hayden
 * Chat contains a list of messages that have been sent over the chat interface
 */
public class Chat {

    /**The list of chat messages*/
    private ArrayList<Message> chatMessages;

    /**
     * Creates a chat object with the list of messages that have been
     * previously sent in case a player joins the game late.
     * @param chatMessages The list of messages sent over the chat interface
     */
    public Chat(ArrayList<Message> chatMessages)
    {
        this.chatMessages = chatMessages;
    }
    
    public Chat(JsonMessageList newChat)
    {
    	chatMessages = new ArrayList<Message>();
    	for(int i = 0; i < newChat.getLines().length; i++)
    	{
    		chatMessages.add(new Message(newChat.getLines()[i].getSource(), newChat.getLines()[i].getMessage()));
    	}
    }
}
