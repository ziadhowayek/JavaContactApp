
import java.io.*;
import java.util.*;

public class Contact implements Serializable{
    private String name;
    private String last;
    private String ville;
    private ArrayList <String> telephone = new ArrayList();
    private ArrayList<Group> contactGroupes= new ArrayList();        
    Contact(String n, String p, String v, ArrayList<String>number){
    this.name = n;
    this.last = p;
    this.ville= v;
    this.telephone = number;
    }
    public void setName(String n){
    this.name = n;
    }
    public String getName(){
    return this.name;
    }
    public void setLast(String p){
    this.last = p;
    }
    public String getLast(){
    return last;
    }
    public void setVille(String v){
    this.ville = v;
    }
    public String getVille(){
    return this.ville;
    }
    public void addNumber(String number){
    telephone.add(number);
    }
    public void removeNumber(String number){
    telephone.remove(number);
    }
    public ArrayList<String> getNumbers(){
    return this.telephone;
    }


    public ArrayList<Group> getContactGroupes(){
    return this.contactGroupes;
    }
    public void addGroup(Group e){
    this.contactGroupes.add(e);
    }
    public void removeGroup(Group e){
    this.contactGroupes.remove(e);
    }    
   public String toString(){
    return this.name+" "+this.last+" "+this.ville;
   }
   
}
