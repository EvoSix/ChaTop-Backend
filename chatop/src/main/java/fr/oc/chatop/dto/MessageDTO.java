package fr.oc.chatop.dto;

public class MessageDTO {
    private String messages;
     public MessageDTO(String messages) {
         this.messages = messages;
     }
     public String getMessages() {
         return messages;
     }
     public void setMessages(String messages) {
         this.messages = messages;
     }
}
