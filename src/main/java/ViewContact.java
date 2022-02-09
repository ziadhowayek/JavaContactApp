import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class ViewContact extends JFrame {
     JPanel newContactPanel,newContactLeft,newContactTop,
            newContactCenter,newContactRight,newContactTopRight,newContactTopRight1
            ,newContactTopRight2,newContactTopRight3,
            newContactCenterRight,newContactBottomRight,newContactBottomBottomRight
            ,newContactBottomBottomRightStraight,testContainer,testContainer1,testContainer2,testContainer3;
    JLabel newContactLabel,gestionDeContact,firstName,lastName,city,phoneNumbers
            ,addToGroup;
    JTextField firstInput,lastInput,cityInput;
    String[] tableHeaders={"Region Code","Phone number"};
    Object[][] tableContent=new Object[5][2];
    JTable contactNumbers;
    contactList contactl;
    JCheckBox group1,group2,group3,group4;
    Contact updated;
    DefaultTableModel model;
    ViewContact(contactList cl,Contact c){
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
    public void fillTopPart(){
       newContactTop = new JPanel();
       gestionDeContact=new JLabel("Gestion des contacts                   ");
       gestionDeContact.setFont(new Font("arial",Font.BOLD,18));
       gestionDeContact.setForeground(Color.BLUE);   
       newContactTop.add(gestionDeContact);       
    }
    public void fillCenterPart(){
        newContactCenter = new JPanel();
        newContactCenter.setBackground(Color.cyan);
  
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
   testContainer3.setLayout(new GridLayout(4,1));
   newContactBottomRight.setLayout(new BoxLayout(newContactBottomRight, BoxLayout.Y_AXIS));
   newContactBottomBottomRight.setLayout(new BoxLayout(newContactBottomBottomRight, BoxLayout.Y_AXIS));
   firstName = new JLabel("First name");
   lastName = new JLabel("Last name");
   city = new JLabel("City");
   phoneNumbers= new JLabel("                   Phone numbers");
   addToGroup=new JLabel(updated.getName()+"'s Groups");
   phoneNumbers.setForeground(Color.BLACK);
   firstInput = new JTextField(30);
   lastInput = new JTextField(30);
   cityInput = new JTextField(34);  
   firstInput.setEditable(false);
   lastInput.setEditable(false);
   cityInput.setEditable(false);
   firstInput.setText(updated.getName());
   lastInput.setText(updated.getLast());
   cityInput.setText(updated.getVille());  
   contactNumbers=new JTable(tableContent,tableHeaders);
   gestionDeContact = new JLabel("Gestion des contacts");  
   for(int i=0;i<updated.getNumbers().size();i++){
   contactNumbers.setValueAt(updated.getNumbers().get(i).toString().substring(0, 2), i, 0);
   contactNumbers.setValueAt(updated.getNumbers().get(i).toString().substring(2), i, 1);   
   }   
   contactNumbers.setEnabled(false);
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
   testContainer3.add(addToGroup);   
   if(updated.getContactGroupes().size()>0){
   group1 = new JCheckBox(updated.getContactGroupes().get(0).toString());
   group1.setSelected(true);
   testContainer3.add(group1);
   newContactBottomRight.add(testContainer3);
   group1.setEnabled(false);
   }
      if(updated.getContactGroupes().size()>1){
   group2 = new JCheckBox(updated.getContactGroupes().get(1).toString());
   testContainer3.add(group2);
   group2.setSelected(true);
   group2.setEnabled(false);   
   }
      if(updated.getContactGroupes().size()>2){
   group3 = new JCheckBox(updated.getContactGroupes().get(2).toString());
   testContainer3.add(group3);
   group3.setSelected(true);   
   group3.setEnabled(false);  
   }
   newContactBottomBottomRight.add(Box.createRigidArea(new Dimension(0,20)));
   newContactRight.add(newContactBottomRight);   
   newContactRight.add(newContactBottomBottomRight);      
    }
}