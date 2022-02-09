import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
public class UpdateContact extends JFrame implements ActionListener{
     JPanel newContactPanel,newContactLeft,newContactTop,
            newContactCenter,newContactRight,newContactTopRight,newContactTopRight1
            ,newContactTopRight2,newContactTopRight3,
            newContactCenterRight,newContactBottomRight,newContactBottomBottomRight
            ,newContactBottomBottomRightStraight,testContainer,testContainer1,testContainer2,testContainer3;
    JLabel newContactLabel,gestionDeContact,firstName,lastName,city,phoneNumbers
            ,addToGroup;
    JTextField firstInput,lastInput,cityInput;
    JButton save,cancel;
    String[] tableHeaders={"Region Code","Phone number"};
    Object[][] tableContent=new Object[5][2];
    JTable contactNumbers;
    contactList contactl;
    JCheckBox group1,group2,group3,group4;
    Contact updated;
    UpdateContact(contactList cl,Contact c){
        updated = c;
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
        newContactLabel = new JLabel("Update Contact");
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
        gestionDeContact=new JLabel("Gestion des contacts      ");
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
       addToGroup=new JLabel("Change Contact's Groups");
       phoneNumbers.setForeground(Color.BLACK);
       firstInput = new JTextField(30);
       lastInput = new JTextField(30);
       cityInput = new JTextField(34);  
       firstInput.setText(updated.getName());
       lastInput.setText(updated.getLast());
       cityInput.setText(updated.getVille());   
       contactNumbers=new JTable(tableContent,tableHeaders);
       gestionDeContact = new JLabel("Gestion des contacts");
       save = new JButton("Save");
       cancel = new JButton("Cancel");
       cancel.addActionListener(this);
       save.addActionListener(this);   
       for(int i=0;i<updated.getNumbers().size();i++){
       contactNumbers.setValueAt(updated.getNumbers().get(i).toString().substring(0, 2), i, 0);
       contactNumbers.setValueAt(updated.getNumbers().get(i).toString().substring(2), i, 1);   
       }    
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
          if(updated.getContactGroupes().size()>0){
       group1 = new JCheckBox(updated.getContactGroupes().get(0).toString());
       group1.setSelected(true);
       newContactBottomRight.add(group1);
       }
          if(updated.getContactGroupes().size()>1){
       group2 = new JCheckBox(updated.getContactGroupes().get(1).toString());
       newContactBottomRight.add(group2);  
       group2.setSelected(true);   
       }
          if(updated.getContactGroupes().size()>2){
       group3 = new JCheckBox(updated.getContactGroupes().get(2).toString());
       newContactBottomBottomRight.add(group3); 
       group3.setSelected(true);   
       }
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
        int result = JOptionPane.showConfirmDialog(newContactPanel,"Vous voulez quitter cette fenetre",null, JOptionPane.YES_NO_OPTION);
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
            contactl.setContactName(updated, first);
            contactl.setContactLast(updated, last);
            contactl.setContactCity(updated, city);
            updated.getNumbers().clear();
            for(int i=0;i<phone.size();i++){
            contactl.addContactNumber(updated, phone.get(i));
            }
            int count =0;
             if(group1 != null){
            if(!group1.isSelected()){
            contactl.removeContactToGroup(contactList.getContacts().get(contactList.getContacts().size()-1),updated.getContactGroupes().get(0-count));
            count++;
            }
             }
            if(group2 !=null){
            if(!group2.isSelected()){
            contactl.removeContactToGroup(contactList.getContacts().get(contactList.getContacts().size()-1),updated.getContactGroupes().get(1-count));
                        count++;
            }}
            if(group3 != null){
            if(!group3.isSelected()){
            contactl.removeContactToGroup(contactList.getContacts().get(contactList.getContacts().size()-1),updated.getContactGroupes().get(2-count));
                        count++;
            }            
            }
             this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));              

             }
    }
}


}