import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class NewContact extends JFrame implements ActionListener{
    JPanel newContactPanel,newContactLeft,newContactTop,
            newContactCenter,newContactRight,newContactTopRight,newContactTopRight1
            ,newContactTopRight2,newContactTopRight3,
            newContactCenterRight,newContactBottomRight,newContactBottomBottomRight
            ,newContactBottomBottomRightStraight,testContainer,testContainer1,testContainer2,testContainer3;
    JLabel newContactLabel,gestionDeContact,firstName,lastName,city,phoneNumbers
            ,addToGroup;
    JTextField firstInput,lastInput,cityInput;
    JButton save,cancel;
    JCheckBox noGroupe,empty;
    String[] tableHeaders={"Region Code","Phone number"};
    Object[][] tableContent=new Object[5][2];
    JTable contactNumbers;
    contactList contactl;
    JCheckBox group1,group2,group3,group4;
    JScrollPane tableScroll;
    NewContact(contactList cl){
   contactl = cl;
   setSize(630,500);
   newContactPanel = new JPanel();
   add(newContactPanel);   
   fillRightPart();
   fillCenterPart();
   fillLeftPart();
   fillTopPart();
   newContactPanel.setLayout(new BorderLayout());
   newContactPanel.add(newContactLeft,BorderLayout.WEST);
   newContactPanel.add(newContactRight,BorderLayout.EAST);
   newContactPanel.add(newContactTop,BorderLayout.NORTH);
   newContactPanel.add(newContactCenter,BorderLayout.CENTER);   
   setVisible(true);
}
    public void fillLeftPart(){
        newContactLeft = new JPanel();    
        newContactLabel = new JLabel("New Contact");
        newContactLabel.setForeground(Color.red);
        newContactLabel.setFont(new Font("arial",Font.BOLD,18));
        newContactLeft.add(newContactLabel);
    }
    public void fillCenterPart(){
        newContactCenter = new JPanel();
        newContactCenter.setBackground(Color.cyan);
    }
    public void fillTopPart(){
        newContactTop = new JPanel();
        gestionDeContact = new JLabel("Gestion des contacts                  ");
        gestionDeContact.setFont(new Font("arial",Font.BOLD,18));
        gestionDeContact.setForeground(Color.BLUE);    
        newContactTop.add(gestionDeContact);

    }
    public void fillRightPart(){
       newContactRight = new JPanel();
       newContactTopRight = new JPanel();
       newContactTopRight1 = new JPanel();
       newContactTopRight2 = new JPanel();
       newContactTopRight3 = new JPanel();
       newContactCenterRight = new JPanel();
       newContactBottomRight=new JPanel();
       newContactBottomBottomRight=new JPanel();
       newContactBottomBottomRightStraight=new JPanel();   
       testContainer=new JPanel();
       testContainer1= new JPanel();
       testContainer2= new JPanel();
       testContainer3= new JPanel(); 
       newContactBottomRight.setLayout(new BoxLayout(newContactBottomRight, BoxLayout.Y_AXIS));
       newContactBottomBottomRight.setLayout(new BoxLayout(newContactBottomBottomRight, BoxLayout.Y_AXIS));
       firstName = new JLabel("First name");
       lastName = new JLabel("Last name");
       city = new JLabel("City");
       phoneNumbers= new JLabel("                   Phone numbers");
       addToGroup=new JLabel("Add the contact to Groups");
       noGroupe=new JCheckBox("No Groups");
       noGroupe.setSelected(true);
       empty = new JCheckBox(".....");
       phoneNumbers.setForeground(Color.BLACK);
       firstInput = new JTextField(30);
       lastInput = new JTextField(30);
       cityInput = new JTextField(34);   
       contactNumbers=new JTable(tableContent,tableHeaders);
       save = new JButton("Save");
       cancel = new JButton("Cancel");
       cancel.addActionListener(this);
       save.addActionListener(this);   
       newContactTopRight1.add(firstName);
       newContactTopRight1.add(firstInput);
       newContactTopRight2.add(lastName);
       newContactTopRight2.add(lastInput);
       newContactTopRight3.add(city);
       newContactTopRight3.add(cityInput);   
       newContactTopRight.setLayout(new BoxLayout(newContactTopRight, BoxLayout.Y_AXIS));
       newContactTopRight.add(newContactTopRight1);
       newContactTopRight.add(newContactTopRight2);
       newContactTopRight.add(newContactTopRight3);
       newContactCenterRight.setLayout(new BoxLayout(newContactCenterRight, BoxLayout.Y_AXIS));   
       newContactTopRight.add(phoneNumbers);
       newContactRight.add(newContactTopRight);
       newContactRight.add(new JScrollPane(contactNumbers));      
       newContactRight.setLayout(new GridLayout(4, 1));
       testContainer.add(addToGroup);   
       newContactBottomRight.add(testContainer);   
       newContactBottomRight.add(noGroupe);   
        if(!contactList.getGroups().isEmpty()){
       group1 = new JCheckBox(contactList.getGroups().get(0).toString());
       newContactBottomRight.add(group1);
       }
          if(contactList.getGroups().size()>1){
       group2 = new JCheckBox(contactList.getGroups().get(1).toString());
       newContactBottomBottomRight.add(group2);   
       }
          if(contactList.getGroups().size()>2){
       group3 = new JCheckBox(contactList.getGroups().get(2).toString());
       newContactBottomRight.add(group3); 
          }      
       newContactBottomBottomRight.add(empty);
       newContactBottomBottomRight.add(Box.createRigidArea(new Dimension(0,20)));
       newContactBottomBottomRightStraight.add(save);
       newContactBottomBottomRightStraight.add(cancel);
       newContactBottomBottomRight.add(newContactBottomBottomRightStraight);
       newContactRight.add(newContactBottomRight);   
       newContactRight.add(newContactBottomBottomRight); 
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String action  = ae.getActionCommand();
        if(action.equals("Cancel")){
        int result = JOptionPane.showConfirmDialog(newContactPanel,"Vous voulez quitter cette fenetre?",null, JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION) {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));        
        }        
        }
        if(action.equals("Save")){
            
             String first,last,city;
             ArrayList<String> phone = new ArrayList(); 
             first= firstInput.getText();
             last= lastInput.getText();
             city= cityInput.getText();
             for(int i=0;i<contactNumbers.getRowCount();i++){
              
             if(contactNumbers.getValueAt(i,0) != null && contactNumbers.getValueAt(i,1) != null){
             if(!(contactNumbers.getValueAt(i,0).toString().isBlank() && contactNumbers.getValueAt(i,1).toString().isBlank()))
             phone.add((contactNumbers.getValueAt(i,0).toString()+contactNumbers.getValueAt(i,1).toString()));
             }
             }
             if(first.isEmpty()||last.isEmpty()||phone.isEmpty()){
             JOptionPane.showMessageDialog(newContactPanel,"Un contact doit avoir un nom,un prenom et un num\u00E9ro de t\u00E9lephone","",  JOptionPane.ERROR_MESSAGE);
             }
             else{
            contactl.addNewContact(first,last,city,phone);    
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));              
       
            try{
            if(group1.isSelected()){
            contactl.addContactToGroupe(contactList.getContacts().get(contactList.getContacts().size()-1),contactList.getGroups().get(0));
            }
            if(group2.isSelected()){
            contactl.addContactToGroupe(contactList.getContacts().get(contactList.getContacts().size()-1),contactList.getGroups().get(1));
            }
            if(group3.isSelected()){
            contactl.addContactToGroupe(contactList.getContacts().get(contactList.getContacts().size()-1),contactList.getGroups().get(2));
            }  
            }
            catch(NullPointerException e){}
            
             }
    }
}


}