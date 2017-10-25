import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DatabaseCon implements ActionListener
{  Connection con;
   JLabel l1,l2,l3,l4,l5;
   JTextField t1,t2,t3,t4,t5;
   JButton b1,b2,b3,b4,b5,b6;
   JFrame jf;
   int index=1,ch=1;

   DatabaseCon()
   {  
	  jf= new JFrame("Student");
      jf.setSize(300,300);
	  jf.setLocation(100,100);
	  jf.setLocationRelativeTo(null);
	  jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  jf.setLayout(null);
	  l1 =new JLabel("Roll No");
	  l1.setOpaque(true);
	  l1.setBounds(50,20,60,20);
	  t1 =new JTextField();
	  t1.setOpaque(true);
	  t1.setBounds(120,20,120,20);
	  l2 =new JLabel("Name");
	  l2.setOpaque(true);
	  l2.setBounds(50,50,60,20);
	  t2 =new JTextField();
	  t2.setOpaque(true);
	  t2.setBounds(120,50,120,20);
      l3 =new JLabel("Address");
	  l3.setOpaque(true);
	  l3.setBounds(50,80,60,20);
	  t3 =new JTextField();
	  t3.setOpaque(true);
	  t3.setBounds(120,80,120,20);
	  l4 =new JLabel("Course 1");
	  l4.setOpaque(true);
	  l4.setBounds(50,110,60,20);
	  t4 =new JTextField();
	  t4.setOpaque(true);
	  t4.setBounds(120,110,120,20);
      l5 =new JLabel("Course 2");
	  l5.setOpaque(true);
	  l5.setBounds(50,140,60,20);
	  t5 =new JTextField();
	  t5.setOpaque(true);
	  t5.setBounds(120,140,120,20);
	  
	  b1 =new JButton("New");
	  b1.setOpaque(true);
	  b1.setBounds(50,170,65,30);
	  b1.addActionListener(this);
	  b2 =new JButton("Edit");
	  b2.setOpaque(true);
	  b2.setBounds(125,170,65,30);
	  b2.addActionListener(this);
	  b3 =new JButton("First");
	  b3.setOpaque(true);
	  b3.setBounds(200,170,65,30);
	  b3.addActionListener(this);
	  b4 =new JButton("Next");
	  b4.setOpaque(true);
	  b4.setBounds(50,210,65,30);
	  b4.addActionListener(this);
	  b5 =new JButton("Save");
	  b5.setOpaque(true);
	  b5.addActionListener(this);
	  b5.setBounds(125,210,65,30);
	  b6 =new JButton("Exit");
	  b6.setOpaque(true);
	  b6.setBounds(200,210,65,30);
	  b6.addActionListener(this);
	  
	  
	  
      jf.add(l1); 
      jf.add(l2);
      jf.add(l3);
      jf.add(l4);
      jf.add(l5);
	  jf.add(t1); 
      jf.add(t2);
      jf.add(t3);
      jf.add(t4);
      jf.add(t5);
	  jf.add(b1); 
      jf.add(b2);
      jf.add(b3);
      jf.add(b4);
      jf.add(b5);
	  jf.add(b6);
	  
      jf.setVisible(true);
	  
	  try
      {  
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost/student","root","310197");
          
      }
      catch (Exception ex)
      {
          System.out.println(ex);
      }
	  
          
          
   }
   void newEntry(String s1,String s2,String s3,String s4,String s5)   
   {  try
      {  int i=Integer.parseInt(s1);
	     String s= "insert into info values(?,?,?)";
	     PreparedStatement pst=con.prepareStatement(s);
		 pst.setInt(1,i);
		 pst.setString(2,s2);
		 pst.setString(3,s3);
		 pst.executeUpdate();
		 s= "insert into course values(?,?,?)";
	     pst=con.prepareStatement(s);
		 pst.setInt(1,i);
		 pst.setString(2,s4);
		 pst.setString(3,s5);
		 pst.executeUpdate();
		 JOptionPane.showMessageDialog(null, "Values are inserted with roll no:"+s1); 
		 
   
      }
      catch (Exception ex)
      {
          JOptionPane.showMessageDialog(null, ""+ex.getMessage()); 
      }
		
    }
	void editEntry(String s1,String s2,String s3,String s4,String s5)   
    { try
      {  
         int i=Integer.parseInt(s1);
	     String s= "UPDATE info SET  name = ?, address = ? WHERE roll=?"; 
	     PreparedStatement pst=con.prepareStatement(s);
		 pst.setInt(3,i);
		 pst.setString(1,s2);
		 pst.setString(2,s3);
		 pst.executeUpdate();
		 s= "UPDATE course set course1 = ?, course2 = ? WHERE roll=?"; 
	     pst=con.prepareStatement(s);
		 pst.setInt(3,i);
		 pst.setString(1,s4);
		 pst.setString(2,s5);
		 pst.executeUpdate();
		 JOptionPane.showMessageDialog(null, "Values are updated for roll no:"+s5); 
   
      }
      catch (Exception ex)
      {
          JOptionPane.showMessageDialog(null, ""+ex.getMessage());  
	  }
		
    }
	void getEntry(int i)   
    {  try
       {  
          String s="select * from info";
		  PreparedStatement st1=con.prepareStatement(s);
		  ResultSet  rs= st1.executeQuery();
		  rs.absolute(i);
		  t1.setText(""+rs.getInt("roll"));
		  t2.setText(rs.getString("name"));
		  t3.setText(rs.getString("address"));
		  s="select * from course";
		  st1=con.prepareStatement(s);
		  ResultSet  rs1= st1.executeQuery();
		  rs1.absolute(i);
		  t4.setText(rs1.getString("course1"));
		  t5.setText(rs1.getString("course2"));
		  
	      			
       }
       catch (Exception ex)
       {
           JOptionPane.showMessageDialog(null, "Last Entry");
       }
	   
	   index++;
		
    }
   
   public void actionPerformed(ActionEvent  ae)
   {  String s1=t1.getText();
      String s2=t2.getText();
	  String s3=t3.getText();
	  String s4=t4.getText();
	  String s5=t5.getText();
	  
      if(ae.getSource() ==b1)
	  {    
           t1.setText("");
           t2.setText("");
	       t3.setText("");
	       t4.setText("");
	       t5.setText("");
		   t1.setEditable(true);
		   t2.setEditable(true);
		   t3.setEditable(true);
		   t4.setEditable(true);
		   t5.setEditable(true); 
		   ch=0;
           
	  } 
      else if (ae.getSource() == b2)
	  {    
           t2.setEditable(true);
		   t3.setEditable(true);
		   t4.setEditable(true);
		   t5.setEditable(true); 
	  }
	  else if (ae.getSource() == b3)
	  {     
           index=1;
		   getEntry(index);
           t1.setEditable(false);
           t2.setEditable(false);
		   t3.setEditable(false);
		   t4.setEditable(false);
		   t5.setEditable(false);
	  }
	  else if (ae.getSource() == b4)
	  {    
           getEntry(index);
           t1.setEditable(false);
           t2.setEditable(false);
		   t3.setEditable(false);
		   t4.setEditable(false);
		   t5.setEditable(false);  
	  }
	  else if (ae.getSource() == b5)
	  {    
           if(ch==0)
		   {
			 newEntry(s1,s2,s3,s4,s5);
             ch=1;			 
		   } 	   
            
	       else
           editEntry(s1,s2,s3,s4,s5);  
	  }
	  else if (ae.getSource() == b6)
	  {   
           jf.dispose();  
	  }
   }
   
   
   public static void main(String args[])
   {    try
        {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (UnsupportedLookAndFeelException e){} 
	    catch (ClassNotFoundException e){}
		catch (InstantiationException e){}
		catch (IllegalAccessException e){}  
		
        new DatabaseCon();
   }
    
     
	
}