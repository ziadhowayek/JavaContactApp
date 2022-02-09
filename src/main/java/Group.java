
import java.io.*;
import java.util.ArrayList;

public class Group implements Serializable{
    private String nom;
    private String description;
    private ArrayList <Contact> participants = new ArrayList();
    Group(String n,String d){
        this.nom= n;
        this.description = d;

    }
    public void ajoutParticipant(Contact participant){
    this.participants.add(participant);
    }
    public void suppressionParticipant(Contact participant){
    this.participants.remove(participant);
    }
    public ArrayList<Contact> getParticipants(){
    return this.participants;
    }
    public void setNom(String s){
    this.nom = s;
    }
    public String getNom(){
    return this.nom;
    }
    public void setDescription(String desc){
    this.description = desc;
    }
    public String getDescription(){
    return this.description;
    }


    public String toString(){

    return this.nom;
    }


    
}
