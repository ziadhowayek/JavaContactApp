
import java.util.*;
import javax.swing.JFrame;


public class contactList   {
    private static ArrayList<Contact> contacts = new ArrayList();
    private static ArrayList<Group> groupList = new ArrayList(); 
    private static ArrayList<observers> observerslist = new ArrayList();
public void  addNewContact(String n, String l, String v, ArrayList<String>number){
    contacts.add(new Contact(n, l,v,number));
    notifyObservers();
}

public void  removeContact(Contact c){
    for(int i=0;i<c.getContactGroupes().size();i++){
    removeContactToGroup(c,c.getContactGroupes().get(i));
            }
    contacts.remove(c);
    notifyObservers();
    }

public void setContactName(Contact c,String s){
c.setName(s);
notifyObservers();
}
public void setContactLast(Contact c,String s){
c.setLast(s);
notifyObservers();
}
public void setContactCity(Contact c,String s){
c.setVille(s);
notifyObservers();
}
public void addContactNumber(Contact c,String i){
c.addNumber(i);
notifyObservers();
}
public void removeContactNumber(Contact c,String i){
c.removeNumber(i);
notifyObservers();
}
public void addContactToGroupe(Contact c,Group g){
c.addGroup(g);
g.ajoutParticipant(c);
notifyObservers();
}
public void sortByFirstName(){
contacts.sort(new SortByFirstNameComp());
notifyObservers();
}
public void sortByLastName(){
contacts.sort(new SortByLastNameComp());
notifyObservers();
}
public void sortByAddress(){
contacts.sort(new SortByAddrComp());
notifyObservers();
}
public static ArrayList<Contact> getContacts(){
return contacts;
}
public void addGroup(String s,String d){
groupList.add(new Group(s,d));
notifyObservers();
}
public void removeGroup(Group g){
g.getParticipants();
for(int i=0;i<g.getParticipants().size();i++){
removeContactToGroup(g.getParticipants().get(i),g);
}
groupList.remove(g);
notifyObservers();
}

public void removeContactToGroup(Contact c,Group g){
g.suppressionParticipant(c);
c.removeGroup(g);
notifyObservers();
}
public static ArrayList<Group> getGroups(){
return groupList;
}
public void addObserver(observers o){
    observerslist.add(o);
}
public void notifyObservers(){
for(int i=0;i<observerslist.size();i++){
   observerslist.get(i).update();

}
}

}
class SortByFirstNameComp implements Comparator{

    @Override
    public int compare(Object t, Object t1) {
     Contact first = (Contact) t;
     Contact second = (Contact) t1;
     return first.getName().compareToIgnoreCase(second.getName());
    }

}    

class SortByLastNameComp implements Comparator{

    @Override
    public int compare(Object t, Object t1) {
     Contact first = (Contact) t;
     Contact second = (Contact) t1;
     return first.getLast().compareToIgnoreCase(second.getLast());
    }

}    
class SortByAddrComp implements Comparator{

    @Override
    public int compare(Object t, Object t1) {
     Contact first = (Contact) t;
     Contact second = (Contact) t1;
     if(first.getVille().equals("") || second.getVille().equals(""))return -1;
     return first.getVille().compareToIgnoreCase(second.getVille());
    }

}    
