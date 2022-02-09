    import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
public class GroupeFrame extends JFrame implements ActionListener,TableModelListener,observers{
    JPanel groupeApplication,leftGroupe,rightGroupe,topGroupe,centerGroupe,
            rightGroupeHeader,rightGroupeFooter;
    contactList contactl;
    JLabel groups,gestionDesContact,listOfGroups;
    JButton addNewGroup,updateGroup,delete;
    String[] tableHeader = {"Group name", "Nb. of contacts"};
    Object[][] tableContent = new Object[contactList.getGroups().size()][2];
    JTable groupList,groupContacts;
    String[] contactTableHeader = {"Contact Name","Contact City"};
    Object[][] contactTableContent = new Object[7][2];
    JScrollPane tableScroll,scroll;
    GroupeFrame(contactList cl){
    contactl=cl;
    contactl.addObserver(this);
    setSize(630,500);
    fillLeftGroupe();
    fillRightGroupe();
    fillCenterGroupe();
    fillTopGroupe();
    groupeApplication = new JPanel();
    groupeApplication.setLayout(new BorderLayout());
    groupeApplication.add(leftGroupe,BorderLayout.WEST);
    groupeApplication.add(rightGroupe,BorderLayout.EAST);
    groupeApplication.add(topGroupe,BorderLayout.NORTH);
    groupeApplication.add(centerGroupe,BorderLayout.CENTER);    
    add(groupeApplication);
            
    setVisible(true);
    }
    public void fillLeftGroupe(){
    leftGroupe = new JPanel();
    leftGroupe.setLayout(new BoxLayout(leftGroupe,BoxLayout.Y_AXIS));
    groups = new JLabel("Groups");
    groups.setForeground(Color.red);
    groups.setFont(new Font("arial",Font.BOLD,18));
    addNewGroup = new JButton("Add new Group");
    addNewGroup.addActionListener(this);    
    leftGroupe.add(Box.createRigidArea(new Dimension(0,30)));
    leftGroupe.add(groups);
    leftGroupe.add(Box.createRigidArea(new Dimension(0,20)));
    leftGroupe.add(addNewGroup);
    }
    public void fillRightGroupe(){
    rightGroupe = new JPanel();  
    rightGroupeHeader = new JPanel();
    rightGroupeFooter = new JPanel();
    updateGroup = new JButton("Update Group");
    delete = new JButton("Delete");
    updateGroup.addActionListener(this);
    delete.addActionListener(this);
    rightGroupe.setLayout(new BoxLayout(rightGroupe,BoxLayout.Y_AXIS));
    listOfGroups = new JLabel("List of groups");
    groupList= new JTable(fillTableContent(tableContent),tableHeader);
    groupList.getModel().addTableModelListener(this);
    groupContacts = new JTable(contactTableContent,contactTableHeader);
    groupContacts.setEnabled(false);
    scroll = new JScrollPane(groupList);      
    tableScroll = new JScrollPane(groupContacts);  
    tableScroll.setPreferredSize(new Dimension(100, 500));    
    scroll.setPreferredSize(new Dimension(100, 500));                
    rightGroupeHeader.add(listOfGroups);
    rightGroupeFooter.add(updateGroup);
    rightGroupeFooter.add(delete);    
    rightGroupe.add(rightGroupeHeader);
    rightGroupe.add(Box.createRigidArea(new Dimension(0,20)));
    rightGroupe.add(new JScrollPane(groupList));
    rightGroupe.add(Box.createRigidArea(new Dimension(0,10))); 
    rightGroupe.add(tableScroll);    
    rightGroupe.add(rightGroupeFooter);
    }
    public void fillTopGroupe(){
    topGroupe = new JPanel();    
    gestionDesContact = new JLabel("Gestion des contacts");
    gestionDesContact.setForeground(Color.blue);
    gestionDesContact.setFont(new Font("arial",Font.BOLD,18));    
    topGroupe.add(gestionDesContact);
    }
    public void fillCenterGroupe(){
    centerGroupe = new JPanel();    
    centerGroupe.setBackground(Color.cyan);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if(action.equals("Add new Group")){
        NewGroupe newGroupe = new NewGroupe(contactl);
        }
        if(action.equals("Update Group")){
            try{
        UpdateGroupe updateGroup = new UpdateGroupe(contactl,contactList.getGroups().get(groupList.getSelectedRow()));
        }
            catch(NullPointerException e){
                
            }
            catch(IndexOutOfBoundsException e){
                
            }
        }
        if(action.equals("Delete")){
        int result = JOptionPane.showConfirmDialog(groupeApplication,"Vous voulez supprimer ce group?",null, JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION) {
            try{
        contactl.removeGroup(contactList.getGroups().get(groupList.getSelectedRow()));
        }
               catch(IndexOutOfBoundsException e){}
            catch(NullPointerException e){}
        }      
        }
        
        }
                
    

    @Override
    public void tableChanged(TableModelEvent tme) {
        try{
        int selection = groupList.getSelectedRow();
        contactTableContent = new Object[7][2];        
        for(int i=0;i<contactList.getGroups().get(selection).getParticipants().size();i++){
        contactTableContent[i][0] = contactList.getGroups().get(selection).getParticipants().get(i).getName()
        +" "+contactList.getGroups().get(selection).getParticipants().get(i).getLast();
        contactTableContent[i][1] = contactList.getGroups().get(selection).getParticipants().get(i).getVille();
        }
        
        if(contactList.getGroups().get(selection).getParticipants().size()==0){
                  contactTableContent = new Object[5][2];  

        }}
         catch(IndexOutOfBoundsException e){
          contactTableContent = new Object[5][2];  
            }
        groupContacts = new JTable(contactTableContent,contactTableHeader);
        groupList= new JTable(fillTableContent(tableContent),tableHeader);        
        rightGroupe.removeAll();
        rightGroupe.revalidate(); 
        rightGroupe.add(rightGroupeHeader);
        rightGroupe.add(new JScrollPane(groupList));
        groupList.getModel().addTableModelListener(this);
        rightGroupe.add(new JScrollPane(groupContacts));
        rightGroupe.add(rightGroupeFooter);        
        
           

    }

    @Override
    public void update() {
        rightGroupe.removeAll();
        rightGroupe.revalidate(); 
        groupList= new JTable(fillTableContent(tableContent),tableHeader);                        
        rightGroupe.add(rightGroupeHeader);
        rightGroupe.add(new JScrollPane(groupList));
        groupList.getModel().addTableModelListener(this);
        rightGroupe.add(new JScrollPane(groupContacts));
        rightGroupe.add(rightGroupeFooter);          
    }
    public Object[][] fillTableContent(Object[][] o){
        o = new Object[contactList.getGroups().size()+1][2];
     for(int i=0;i<contactList.getGroups().size();i++){
    o[i][0] = contactList.getGroups().get(i).getNom();
    o[i][1] = contactList.getGroups().get(i).getParticipants().size();
    }
     
        return o;
}
}
