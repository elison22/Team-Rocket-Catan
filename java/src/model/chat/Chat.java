package model.chat;

import model.Game;

import java.util.List;

/**
 * @author Hayden
 * Chat contains a list of messages that have been sent over the chat interface
 */
public class Chat {

    /**The list of chat messages*/
    private arrayList<Message> chatMessages;

    /**
     * Creates a chat object with the list of messages that have been
     * previously sent in case a player joins the game late.
     * @param chatMessages The list of messages sent over the chat interface
     */
    public Chat(arrayList<Message> chatMessages){
        this.chatMessages = chatMessages;
    }
}
