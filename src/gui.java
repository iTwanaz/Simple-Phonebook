import javax.swing.*;
import javax.swing.table.*;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
    
public class gui implements ActionListener {    
    private JFrame frame;
    private JPanel pnl1,pnl2, pnl3, pnl4, pnl5;
    private JTextField tf, tf1,tf2;
    private JLabel lbl, lbl1, lbl2;
    private JButton btn, btn1, btn2, btn3;
    private JMenuItem  exit_mi, add_mi,remove_mi, search_mi, clear_mi, about_mi;
    private JMenu File_m, Edit_m, Help_m;
    private JMenuBar mb;
    private JTable tbl;
    private JScrollPane spl;
    private JRadioButton rb, rb1;
    private JCheckBox c;
    private ButtonGroup bg;
    private DefaultTableModel dtm;
    
    private int rowIndex = 0;
    private Boolean alternate = false;
    
    
    public void _init(){
	  /*********************************Creating a new File for records***********************************/
		File file = new File("info.BIN");
    	try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
        	
	  /*********************************User-Interface***********************************/
    	GUI();
    	
	  /*********************************Displaying Data on Start-Up***********************************/
    	display();	
    }
    
    public void GUI(){
       frame = new JFrame("Phonebook");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(new GridLayout(1,2));

       mb = new JMenuBar();    
           File_m = new JMenu("File");
               exit_mi = new JMenuItem("Exit");
               exit_mi.addActionListener(this);
           File_m.add(exit_mi);
                    
           Edit_m = new JMenu("Edit");
               add_mi = new JMenuItem("Add");
               remove_mi = new JMenuItem("Remove");
               search_mi = new JMenuItem("Search");
               clear_mi = new JMenuItem("Clear");
           Edit_m.add(add_mi);
           Edit_m.add(remove_mi);
           Edit_m.add(search_mi);
           Edit_m.add(clear_mi);
                                         
           Help_m = new JMenu("Help");
                about_mi = new JMenuItem("About");
           Help_m.add(about_mi);
       mb.add(File_m);
       mb.add(Edit_m);
       mb.add(Help_m);  
         
       frame.setJMenuBar(mb);
         
       pnl1 = new JPanel();
       pnl1.setBorder(new TitledBorder("Name"));
       pnl1.setLayout(new GridLayout(1,1));
       
       frame.add(pnl1);
          
         
       tbl = new JTable();    
       String[] columnNames = {"First Name", "Last Name","Phone","Private"};  
       dtm = new DefaultTableModel(100,4);
       dtm.setColumnIdentifiers(columnNames);
       tbl.setModel(dtm);
             
       spl= new JScrollPane(tbl);
       pnl1.add(spl);
               
            
       pnl2 = new JPanel();
       pnl2.setLayout(new GridLayout(3,1));
       frame.add(pnl2);
           
       pnl3 = new JPanel();
       pnl3.setBorder(new TitledBorder("Info:"));
       pnl3.setLayout(new GridLayout(4,2));
       pnl2.add(pnl3);
           
       lbl = new JLabel("First Name");
       pnl3.add(lbl);
       tf = new JTextField(25);
       pnl3.add(tf);
            
       lbl1 = new JLabel("Last Name");
       pnl3.add(lbl1);
       tf1 = new JTextField(25);
       pnl3.add(tf1);
           
       lbl2 = new JLabel("Phone");
       pnl3.add(lbl2);
       tf2 = new JTextField(10);
       pnl3.add(tf2);
            
       c = new JCheckBox("Private");
       pnl3.add(c);
            
       pnl4 = new JPanel();
       pnl4.setBorder(new TitledBorder("File as:"));
       pnl4.setLayout(new GridLayout(2,1));
       pnl2.add(pnl4);
           
       rb = new JRadioButton("Forename,Surname");
       pnl4.add(rb);
              
       rb1 = new JRadioButton("Surname,Forename");
       pnl4.add(rb1);
       
       bg = new ButtonGroup();
       bg.add(rb);
       bg.add(rb1);
              
       pnl5 = new JPanel();
       pnl5.setLayout(new GridLayout(2,2));
       pnl2.add(pnl5);
       
       ImageIcon clearIcon = new ImageIcon("images/clear.png");
       ImageIcon newClearIcon = iconResizer(clearIcon);
       btn = new JButton ("Clear", newClearIcon);
       pnl5.add(btn);
          
       ImageIcon searchIcon = new ImageIcon("images/search.png");
       ImageIcon newSearchIcon = iconResizer(searchIcon);
       btn2 = new JButton ("Search", newSearchIcon);
       pnl5.add(btn2);
           
       ImageIcon addIcon = new ImageIcon("images/add.png");
       ImageIcon newAddIcon = iconResizer(addIcon);
       btn1 = new JButton ("Add", newAddIcon);
       pnl5.add(btn1);
           
       ImageIcon removeIcon = new ImageIcon("images/remove.png");
       ImageIcon newRemoveIcon = iconResizer(removeIcon); 
       btn3 = new JButton ("Remove", newRemoveIcon);
       pnl5.add(btn3);
        
       btn.addActionListener(this);
       btn1.addActionListener(this);
       btn2.addActionListener(this);
       btn3.addActionListener(this);
       about_mi.addActionListener(this);
       search_mi.addActionListener(this);
       add_mi.addActionListener(this);
       remove_mi.addActionListener(this);
       clear_mi.addActionListener(this);
       
       rb.addActionListener(this);
       rb1.addActionListener(this);
       
       		rb.setSelected(true);
       		rb.setEnabled(false);
       
       frame.pack();
       frame.setVisible(true);  
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == exit_mi){
        	System.exit(0);
        }else if(e.getSource() == about_mi){
        	JOptionPane.showMessageDialog(null, "Program is still in Trial Version!!");
        }else if(e.getSource() == add_mi){
        	add();
        }else if(e.getSource() == remove_mi){
        	remove();
        }else if(e.getSource() == search_mi){
        	search();
        }else if(e.getSource() == clear_mi){
        	clear();
        }else if(e.getSource() == btn1){
        	add();
        }else if(e.getSource() == btn3){
        	remove();
        }else if(e.getSource() == btn2){
        	search();
        }else if(e.getSource() == btn){
        	clear();
        }else if(e.getSource() == rb){
        	rb.setEnabled(false);
        	rb1.setEnabled(true);
        	
        	if(alternate == true){
        		tbl.moveColumn(1, 0);
        	}
        }else if(e.getSource() == rb1){
        	rb1.setEnabled(false);
        	rb.setEnabled(true);
        	alternate = true;
        	
        	tbl.moveColumn(1, 0);
        }
    }
    
    public void display(){
    	
    	final String path = "info.BIN";
    	String content = null;
    	ArrayList <String> contentArr = new ArrayList <String> ();
    	
    	try{
	    	BufferedReader reader= new BufferedReader(new FileReader(path));
	    	
	    	try {		
				while((content = reader.readLine()) != null){
					contentArr.add(content);	
				}
			}catch(FileNotFoundException ex){
				ex.printStackTrace();
			}catch(IOException ex){
				ex.printStackTrace();
			}finally{
				reader.close();
			}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}

    	for(String data:contentArr){
    		String [] dataRow = data.split("-");
    		dtm.insertRow(rowIndex, dataRow);
//    		System.out.println(Arrays.toString(dataRow));
    		rowIndex++;	
    	} 
    }
    
    public void add(){
    	try{
    		String name = tf.getText();
        	String lastName = tf1.getText();
        	String phone = tf2.getText();
        	Boolean state = c.isSelected();
        	
        	String fileContent;
        	final String path = "info.BIN";
        	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
        	
        	if(name.isEmpty() || lastName.isEmpty() || phone.isEmpty()){
        		JOptionPane.showMessageDialog(null, "Enter all Values!!");
        	}else{ 	
				try{
					if(state == true){
						fileContent = name + "-" + lastName + "-" + phone + "-" + "private";
						
						dtm.insertRow(rowIndex, new Object[]{name, lastName, phone, "private"});
	        			rowIndex++;
					}else{
						fileContent = name + "-" + lastName + "-" + phone + "-" + " ";
						
						dtm.insertRow(rowIndex, new Object[]{name, lastName, phone, ""});	
	        			rowIndex++;
					}
					
					bufferedWriter.write(fileContent + "\r\n");
//					System.out.println("Content Written :" + fileContent);
					System.out.println("Conatct Added.");
					
				}catch(FileNotFoundException e){
					JOptionPane.showMessageDialog(null, "Couldn't locate the the file!");
					e.printStackTrace();
				}catch(IOException ex){
					JOptionPane.showMessageDialog(null, "Couldn't write in the file!");
					ex.printStackTrace();
				}finally{
					bufferedWriter.close();
				}
        	}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}  
    }
    
    public void remove(){
    	int toRemove = tbl.getSelectedRow();
    	String content;
    	
    	final String oldFile = "info.BIN";
    	final String newFile = "new.BIN";
    	
		File newfile = new File(newFile);
		try {
			newfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File oldfile = new File(oldFile);
    	
    	if(toRemove == -1){
    		JOptionPane.showMessageDialog(null, "No Row Selected!!");
    		
    	}else{	
    		try{
    			String check = tbl.getModel().getValueAt(toRemove, 0).toString();
    			BufferedReader reader = new BufferedReader(new FileReader(oldFile));
    			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
    			
    			try{
	    			if(!check.isEmpty()){
	    				while((content = reader.readLine()) != null){
	    					String[] data = content.split("-");
	    					if(!check.equalsIgnoreCase(data[0])){
	    						writer.write(content + "\r\n");
	    					}else{
	    						continue;
	    					}
	    				}			
	    				
	    				dtm.removeRow(toRemove);
	            		dtm.addRow(new String[] {"", "", "", ""});
	            		rowIndex--;
	    			}
    			}finally{
    				
    				reader.close();
    				writer.close();
    				
    				if(oldfile.delete() == true && newfile.renameTo(oldfile) == true){
    					System.out.println("Contact Removed.");
    				}else{
    					System.out.println("Error deleting contact!");
    				}
    			}
    			
    		}catch(NullPointerException ex){
    			System.out.println(ex);
    			JOptionPane.showMessageDialog(null, "The Selected Row is Empty!!");
    		}catch(FileNotFoundException ex){
    			ex.printStackTrace();
    		}catch(IOException ex){
    			ex.printStackTrace();
    		}
    	}
    }
    
    public void clear(){
    	tf.setText("");
    	tf1.setText("");
    	tf2.setText("");
    	c.setSelected(false);
    }
    
    public void search(){
    	JOptionPane.showMessageDialog(null, "Search functionality will be supporrted in professional version!!");
    }
    
    public ImageIcon iconResizer(ImageIcon initialIcon){
		Image initialImg = initialIcon.getImage();
		Image newImg = initialImg.getScaledInstance(32,32,java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);
    	return newIcon;
    }
    
    
    public static void main(String [] args){
        gui main = new gui();
        main._init(); 
    }
}
