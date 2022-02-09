import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
public class UpdateGroupe extends JFrame implements ActionListener{
    JPanel newGroupeapp,newGroupeLeft,newGroupeRight,newGroupeCenter,
            newGroupeTop,newGroupeRightHeader,
            newGroupeRightHeader1,newGroupeRightHeader2,
            newGroupeRightFooter,newGroupeRightCenter;
    JLabel groups,gestionDesContact,groupName,groupDescription;
    JTextField typeName,typeDesc;
    contactList contactl;
    String[] tableHeader = {"Contact name","City","Add to group"};
    Object[][] tableContent  ;
    JTable contactTable;
    JScrollPane scroll;
    JButton saveGroup,cancel;
    DefaultTableModel model;
    Group g;
UpdateGroupe(contactList cl,Group group){
    g=group;
    contactl=cl;
    setSize(630,500);
    fillLeftGroupe();
    fillRightGroupe();
    fillCenterGroupe();
    fillTopGroupe();
    newGroupeapp = new JPanel();
    newGroupeapp.setLayout(new BorderLayout());
    newGroupeapp.add(newGroupeLeft,BorderLayout.WEST);
    newGroupeapp.add(newGroupeRight,BorderLayout.EAST);
    newGroupeapp.add(newGroupeTop,BorderLayout.NORTH);
    newGroupeapp.add(newGroupeCenter,BorderLayout.CENTER);    
    add(newGroupeapp);
       setResizable(false);           
    setVisible(true);
    }
    public void fillLeftGroupe(){
    newGroupeLeft = new JPanel();
    newGroupeLeft.setLayout(new BoxLayout(newGroupeLeft,BoxLayout.Y_AXIS));
    groups = new JLabel("Add new Group");
    groups.setForeground(Color.red);
    groups.setFont(new Font("arial",Font.BOLD,18));
    newGroupeLeft.add(Box.createRigidArea(new Dimension(0,20)));    
    newGroupeLeft.add(groups);
    }
    public void fillRightGroupe(){
    newGroupeRight = new JPanel();  
    newGroupeRightHeader = new JPanel();
    newGroupeRightHeader1 = new JPanel();
    newGroupeRightHeader2 = new JPanel();
    newGroupeRightFooter = new JPanel();
    newGroupeRightCenter = new JPanel();
    newGroupeRightCenter.setLayout(new BoxLayout(newGroupeRightCenter,BoxLayout.Y_AXIS));
    saveGroup = new JButton("Save Group");
    cancel = new JButton("Cancel");
    groupName = new JLabel("Group name");
    groupDescription = new JLabel("Description");
    typeName = new JTextField(25);
    typeDesc = new JTextField(26);    
    typeName.setText(g.getNom());
    typeDesc.setText(g.getDescription());
    saveGroup.addActionListener(this);
    cancel.addActionListener(this);    
    model = new DefaultTableModel(fillTableContent(tableContent),tableHeader){
  @Override
    public boolean isCellEditable(int row,int column){
        if(column == 0  || column ==1)
    return false;
        else return true;
    }
  @Override
    public Class getColumnClass(int column) {
    switch (column) {
    case 0: return String.class;
    case 1: return String.class;
    default: return Boolean.class; }
}
};
     
    contactTable = new JTable(model);
    scroll = new JScrollPane(contactTable);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.setPreferredSize(new Dimension(150, 500));    
    newGroupeRight.setLayout(new BorderLayout());
    newGroupeRightHeader.setLayout(new GridLayout(2,1));
    newGroupeRightHeader1.add(groupName);
    newGroupeRightHeader1.add(typeName);
    newGroupeRightHeader2.add(groupDescription);
    newGroupeRightHeader2.add(typeDesc);
    newGroupeRightHeader.add(newGroupeRightHeader1);
    newGroupeRightHeader.add(newGroupeRightHeader2);
    newGroupeRightFooter.add(saveGroup);
    newGroupeRightFooter.add(cancel);  
    newGroupeRightCenter.add(Box.createRigidArea(new Dimension(0,30)));
    newGroupeRightCenter.add(scroll);    
    newGroupeRight.add(newGroupeRightHeader,BorderLayout.NORTH);
    newGroupeRight.add(newGroupeRightCenter,BorderLayout.CENTER);
    newGroupeRight.add(newGroupeRightFooter,BorderLayout.SOUTH);    
    }
    public void fillTopGroupe(){
    newGroupeTop = new JPanel();    
    gestionDesContact = new JLabel("Gestion des contacts");
    gestionDesContact.setForeground(Color.blue);
    gestionDesContact.setFont(new Font("arial",Font.BOLD,18));    
    newGroupeTop.add(gestionDesContact);
    }
    public void fillCenterGroupe(){
    newGroupeCenter = new JPanel();    
    newGroupeCenter.setBackground(Color.cyan);
    }
    public Object[][] fillTableContent(Object[][] content){
    content = new Object[contactList.getContacts().size()][3];
    for(int i=0;i<content.length;i++){
    content[i][0] = contactList.getContacts().get(i).getName()+" "+contactList.getContacts().get(i).getLast();
    content[i][1] = contactList.getContacts().get(i).getVille();
    if(g.getParticipants().contains(contactList.getContacts().get(i))){ content[i][2] = true;}
    else{
    content[i][2] = false;
    }}
    return content;
}    

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action =ae.getActionCommand();
        if(action.equals("Save Group")){
        String groupName = typeName.getText();
        String groupDesc = typeDesc.getText();
        if(groupName.isEmpty()){
        JOptionPane.showMessageDialog(newGroupeapp,"un group doit avoir un nom","",  JOptionPane.ERROR_MESSAGE);                       
        }
        else{
        g.setNom(groupName);
        g.setDescription(groupDesc);
        g.getParticipants().clear();
        for(int i=0;i<contactTable.getRowCount();i++){
        if(contactTable.getValueAt(i, 2).equals(true)){
        contactl.addContactToGroupe(contactList.getContacts().get(i),g);       
        }
        }
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));                      
        }
        }
        if(action.equals("Cancel")){
        int result = JOptionPane.showConfirmDialog(newGroupeapp,"Are you sure you wish to exit this window?",null, JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION) {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));        
        }
        }
    }
    
}
