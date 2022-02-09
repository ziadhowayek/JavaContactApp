import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class leProjet extends JFrame implements ActionListener,WindowListener{
JPanel application,leftPart,center,topPart,east;
JButton contact,groupe;
JLabel gestionDeContact;
contactList contactl;
ObjectInputStream objContactIn=null;  
File contactIn=new File("ContactInputOutput.txt"); 
ObjectInputStream objGroupIn=null; 
File groupIn=null;  
leProjet(){
contactl = new contactList(); 
getAllContacts();
getAllGroups();
setSize(600,500);   
setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
setTitle("Projet NFA035");
application= new JPanel();
application.setLayout(new BorderLayout(0,0));
leftPart = new JPanel();
topPart = new JPanel();
center = new JPanel();
east = new JPanel();
center.setBackground(Color.CYAN);
contact = new JButton("Contacts");
groupe = new JButton("Groupe");
contact.addActionListener(this);
groupe.addActionListener(this);
gestionDeContact=new JLabel("Gestion des contacts");
gestionDeContact.setFont(new Font("arial",Font.BOLD,18));
gestionDeContact.setForeground(Color.BLUE);
topPart.add(gestionDeContact);
leftPart.setLayout(new BoxLayout(leftPart, BoxLayout.Y_AXIS));
leftPart.add(Box.createRigidArea(new Dimension(0,80)));
leftPart.add(contact);
leftPart.add(Box.createRigidArea(new Dimension(0,40)));
leftPart.add(groupe);
application.add(topPart, BorderLayout.NORTH);
application.add(leftPart, BorderLayout.WEST);
application.add(center,BorderLayout.CENTER);
application.add(east, BorderLayout.EAST);
add(application);
setVisible(true);
addWindowListener(this);
}
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        leProjet ziad2 = new leProjet();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String s = ae.getActionCommand();
       if(s.equals("Contacts")){
           ContactFrame contactFrame = new ContactFrame(contactl);
       }
       if(s.equals("Groupe")){
       GroupeFrame groupeFrame = new GroupeFrame(contactl);
       }
    }
    public void getAllContacts(){
        try{
           contactIn = new File("ContactInputOutput.txt"); 
           objContactIn = new ObjectInputStream(new FileInputStream(contactIn));
           while(true){
                 contactList.getContacts().add((Contact)objContactIn.readObject());
                }
           }
        catch(IOException e){
        } 
        catch (ClassNotFoundException ex) {
        }
        finally{
            try {
               
                objContactIn.close();
                } 
            catch (IOException ex) {
            }
            }  
    }
    public void getAllGroups(){
        try{
           groupIn = new File("GroupInputOutput.txt"); 
           objGroupIn = new ObjectInputStream(new FileInputStream(groupIn));
           while(true){
                 contactList.getGroups().add((Group)objGroupIn.readObject());
                }
           }
        catch(IOException e){
        } 
        catch (ClassNotFoundException ex) {
        }
        finally{
            try {
                objGroupIn.close();
                } 
            catch (IOException ex) {
            }
            }  
    }    

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent evt) {
        try{
            File contactOut = new File("ContactInputOutput.txt");              
            ObjectOutputStream contactOutStream = new ObjectOutputStream(new FileOutputStream(contactOut));
            for(int i=0;i<contactList.getContacts().size();i++){
            contactOutStream.writeObject(contactList.getContacts().get(i));
            }
            contactOutStream.close();

            }
                 
            catch(IOException e){
            }
        try{
            File groupOut = new File("GroupInputOutput.txt");              
            ObjectOutputStream grouppOutStream = new ObjectOutputStream(new FileOutputStream(groupOut));
            for(int i=0;i<contactList.getGroups().size();i++){
            grouppOutStream.writeObject(contactList.getGroups().get(i));
            }
            grouppOutStream.close();

            }
                 
            catch(IOException e){
            }        
    }
    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
    
    
}
