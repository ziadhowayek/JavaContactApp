import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
public class ContactFrame extends JFrame implements ActionListener,observers{
JPanel contactApplication,topPanel,leftPanel,centerPanel,
        rightPanel,topRightPanel,bottomRightPanel;
JLabel gestionDeContact,contactLabel,search;
JButton sortByFirst,sortByLast,sortByCity,addContact,view,update,delete;
JTextField searchBar;
JList contactsList;
contactList contactl;
    ContactFrame(contactList cl){
        contactl = cl;
        contactl.addObserver(this);
        setSize(630,500);
        contactApplication = new JPanel();
        fillLeftPart();
        fillTopPart();
        fillCenterPart();
        fillRightPart();
        contactApplication.setLayout(new BorderLayout());
        contactApplication.add(leftPanel,BorderLayout.WEST);
        contactApplication.add(topPanel,BorderLayout.NORTH);
        contactApplication.add(centerPanel,BorderLayout.CENTER);
        update();
        contactApplication.add(rightPanel,BorderLayout.EAST);
        add(contactApplication);
        setVisible(true);
    }
    public void fillLeftPart(){
        leftPanel = new JPanel();
        sortByFirst = new JButton("Sort by First name");
        sortByLast = new JButton("Sort by Last name");
        sortByCity = new JButton("Sort by City");
        addContact = new JButton("Add new contact");
        contactLabel = new JLabel("Contacts");
        contactLabel.setForeground(Color.red);
        contactLabel.setFont(new Font("arial",Font.BOLD,18));
        sortByFirst.addActionListener(this);
        sortByLast.addActionListener(this);
        sortByCity.addActionListener(this);
        addContact.addActionListener(this);
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        leftPanel.add(Box.createRigidArea(new Dimension(0,50)));
        leftPanel.add(contactLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,30)));
        leftPanel.add(sortByFirst);
        leftPanel.add(Box.createRigidArea(new Dimension(0,30)));
        leftPanel.add(sortByLast);
        leftPanel.add(Box.createRigidArea(new Dimension(0,30)));
        leftPanel.add(sortByCity);
        leftPanel.add(Box.createRigidArea(new Dimension(0,30)));
        leftPanel.add(addContact);

    }
    public void fillTopPart(){
        topPanel = new JPanel();
        gestionDeContact=new JLabel("Gestion des contacts");
        gestionDeContact.setFont(new Font("arial",Font.BOLD,18));
        gestionDeContact.setForeground(Color.BLUE);
        topPanel.add(gestionDeContact);
    }
    public void fillCenterPart(){
    centerPanel = new JPanel();
    centerPanel.setBackground(Color.cyan);
    }
    public void fillRightPart(){
        rightPanel = new JPanel();
        topRightPanel = new JPanel();
        bottomRightPanel = new JPanel();
        view = new JButton("View");
        update = new JButton("Update");
        delete = new JButton("Delete");
        view.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
        search = new JLabel("Search");
        searchBar = new JTextField(25);
        searchBar.addActionListener(this);
        contactsList = new JList(contactList.getContacts().toArray());
        contactsList.setVisibleRowCount(17);
        contactsList.setFixedCellHeight(20);
        rightPanel.setLayout(new BorderLayout());
        topRightPanel.add(search);
        topRightPanel.add(searchBar);
        bottomRightPanel.add(view);
        bottomRightPanel.add(update);
        bottomRightPanel.add(delete);
        rightPanel.add(topRightPanel,BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(contactsList),BorderLayout.CENTER);
        rightPanel.add(bottomRightPanel,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if(action.equals("Sort by First name")){
        contactl.sortByFirstName();
        }
        if(action.equals("Sort by Last name")){
        contactl.sortByLastName();
        }
        if(action.equals("Sort by City")){
        contactl.sortByAddress();
        }
        if(action.equals("Add new contact")){
        NewContact newContact = new NewContact(contactl);
        }
        if(action.equals("View")){
            try{
        ViewContact viewContact=new ViewContact(contactl,contactList.getContacts().get(contactsList.getSelectedIndex()));
        }
        
        catch(NullPointerException e){
                }
        catch(IndexOutOfBoundsException e){}
        }
        if(action.equals("Update")){
            try{
        UpdateContact updateContact=new UpdateContact(contactl,contactList.getContacts().get(contactsList.getSelectedIndex()));
        }
        
        catch(NullPointerException e){
                }
        catch(IndexOutOfBoundsException e){}    
        
        }
        if(action.equals("Delete")){
            try{
        contactl.removeContact(contactList.getContacts().get(contactsList.getSelectedIndex()));
        }        
        catch(IndexOutOfBoundsException e){}
            catch(NullPointerException e){}
        }
        if(!searchBar.getText().isEmpty()){
        String s = searchBar.getText();
        
        refreshList(displayContacts(s));
         
        }
        if(searchBar.getText().isEmpty()){
        refreshList(contactList.getContacts());        
        }
        
    }


    @Override
    public void update() {
        refreshList(contactList.getContacts());
    }

   
    public void refreshList(ArrayList <Contact>c){
    rightPanel.removeAll();
    rightPanel.revalidate(); 
    contactsList = new JList(c.toArray());    
    rightPanel.add(topRightPanel,BorderLayout.NORTH);
    rightPanel.add(new JScrollPane(contactsList),BorderLayout.CENTER);
    rightPanel.add(bottomRightPanel,BorderLayout.SOUTH);      
    }
    public ArrayList<Contact> displayContacts(String s){
    ArrayList<Contact> display = new ArrayList();
    for(int i=0;i<contactList.getContacts().size();i++){
    if(contactList.getContacts().get(i).toString().startsWith(s)){
    display.add(contactList.getContacts().get(i));
    }
    }
        return display;
    }
}

    


