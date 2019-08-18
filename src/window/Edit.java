package window;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;

@SuppressWarnings("serial")
public class Edit extends JFrame {

	 private JPanel contentPane;
	 public  JButton ButtonOKEdit,ButtonCancelEdit ;
	 public JButton ButtonDeleteattr;
	 public Box BoxEdit;
	 public JScrollPane scrollPaneAttr, scrollPaneInst;
	 public JTextField textField;
	 private boolean  mod_rel=false,mod_att=false,delete_inst=false,delete_attr=false;
	 static boolean add_inst=false;
     private JTable tab;
     public JTable table;
     public JButton ButtonAddInstance, ButtonDeleteInst;
     public JLabel lblNewLabel; 
     private Vector<String> add_instance=new Vector<String>(),del_attr=new Vector<String>();
     private Vector<Integer> delete_instance=new Vector<Integer>();
     public JPanel panel,panel_1,panel_2;
     private Instance inst  ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit frame = new Edit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Edit() {
		setFont(new Font("Trebuchet MS", Font.ITALIC, 12));
		setTitle("Edit");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/*setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);*/
		setBounds(100, 100, 527, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("");
		
		 panel = new JPanel();
		panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel.setBackground(new Color(0, 128, 128));
		
		 panel_1 = new JPanel();
		panel_1.setBackground(new Color(218, 165, 32));
		
		  ButtonOKEdit = new JButton("OK");
		  ButtonOKEdit.setBackground(new Color(105, 105, 105));
		  ButtonOKEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		ButtonOKEdit.setForeground(new Color(255, 255, 255));
		
		 ButtonCancelEdit = new JButton("Cancel");
		 ButtonCancelEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		 ButtonCancelEdit.setBackground(new Color(105, 105, 105));
		ButtonCancelEdit.setForeground(new Color(255, 255, 255));
		
		 panel_2 = new JPanel();
		panel_2.setBackground(new Color(47, 79, 79));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(ButtonOKEdit)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ButtonCancelEdit, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
								.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))))
					.addGap(12))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(111)
							.addComponent(label))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ButtonCancelEdit)
						.addComponent(ButtonOKEdit)))
		);
		
		JLabel lblRelation = new JLabel("Relation:");
		lblRelation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblRelation.setForeground(new Color(255, 255, 255));
		
		textField = new JTextField();
		textField.setBackground(SystemColor.control);
		textField.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRelation, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
					.addGap(14))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRelation)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		panel_2.setLayout(gl_panel_2);
		
		scrollPaneAttr = new JScrollPane();
		scrollPaneAttr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		 lblNewLabel = new JLabel("Attributes:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		ButtonDeleteattr = new JButton("Delete Attribute");
		ButtonDeleteattr.setFont(new Font("Tahoma", Font.BOLD, 12));
		ButtonDeleteattr.setForeground(new Color(255, 255, 255));
		ButtonDeleteattr.setBackground(new Color(47, 79, 79));
		 BoxEdit = Box.createHorizontalBox();
		scrollPaneAttr.setViewportView(BoxEdit);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ButtonDeleteattr, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPaneAttr, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(scrollPaneAttr, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(15)
							.addComponent(ButtonDeleteattr)
							.addGap(20))))
		);
		panel_1.setLayout(gl_panel_1);
		
		 ButtonAddInstance = new JButton("Add Instance");
		ButtonAddInstance.setForeground(new Color(255, 255, 255));
		ButtonAddInstance.setFont(new Font("Tahoma", Font.BOLD, 12));
		ButtonAddInstance.setBackground(new Color(105,105,105));
		ButtonDeleteInst = new JButton("Delete Instance");
		ButtonDeleteInst.setForeground(new Color(255, 255, 255));
		ButtonDeleteInst.setBackground(new Color(105,105,105));
		ButtonDeleteInst.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		 scrollPaneInst = new JScrollPane();
		scrollPaneInst.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneInst.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JLabel lblInstances = new JLabel("Instances:");
		lblInstances.setForeground(new Color(255, 255, 255));
		lblInstances.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		table = new JTable();
		
		scrollPaneInst.setViewportView(table);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblInstances)
						.addComponent(ButtonAddInstance, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
						.addComponent(ButtonDeleteInst, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
					.addGap(12)
					.addComponent(scrollPaneInst, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
					.addGap(6))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblInstances)
					.addGap(36)
					.addComponent(ButtonAddInstance, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(ButtonDeleteInst, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(78, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(scrollPaneInst, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addGap(18))
		);
		
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		 
		Image img=new ImageIcon(this.getClass().getResource("vue/edit.png")).getImage();
		this.setIconImage(img);
		
		/*******************surveiller la relation***************************/
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				mod_rel=true;	
			}
		});
		
		/************ADD_INSTANCES****************/
		
		ButtonAddInstance.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				add_inst=true;
				if(ButtonAddInstance.getText().compareTo("Add Instance")==0)
				{
					add_instance=new Vector<String>();
				for(int k=0;k<=Gui.data_arff.numAttributes()-1;k++)
				{
					if(Gui.data_arff.attribute(k).isNumeric()) {add_instance.add("0");
					
					}
					if(Gui.data_arff.attribute(k).isNominal()) {
				    add_instance.add(Gui.data_arff.attribute(k).value(0));
                      }
					
			    }
				((DefaultTableModel) table.getModel()).addRow(add_instance);
				 ButtonAddInstance.setText("Validate");
				 ((MyModel)table.getModel()).setEditable(true);
				}
				else /******************valider*********************/
				{
					inst = new DenseInstance(Gui.data_arff.numAttributes()) ;
		            inst.setDataset(Gui.data_arff);
		            if (table.isEditing()) table.getCellEditor().stopCellEditing();
		            for(int indice=0;indice<=table.getColumnCount()-1;indice++)
					{
						if(Gui.data_arff.attribute(indice).isNumeric()) 
						{
							try 
							{inst.setValue(indice,Double.parseDouble(table.getValueAt(table.getRowCount()-1,indice).toString()));}
						catch(NumberFormatException e1) {
						
						JOptionPane.showMessageDialog(null,"' "+table.getValueAt(table.getRowCount()-1,indice).toString()+" '  is not a numerical value\n It will be changed by 0","Error",JOptionPane.ERROR_MESSAGE);
						table.setValueAt("0",table.getRowCount()-1,indice);
						inst.setValue(indice,Double.parseDouble(table.getValueAt(table.getRowCount()-1,indice).toString()));
						}}
						else inst.setValue(indice,table.getValueAt(table.getRowCount()-1,indice).toString());
					}
					if(Gui.data_arff.checkInstance(inst))
					{
					Gui.data_arff.add(inst);
					 
		                	 Gui.lblinstances.setText(Gui.data_arff.numInstances()+"");
				 				Gui.lblsumweight.setText(new DecimalFormat("##.##").format(Gui.data_arff.sumOfWeights())+"");//mise à jour
				                  if(Gui.attrtable.getSelectedRow()!=-1)//modifier la table selected attrbts
								{
				 					Gui.CreatSelectedAtt(Gui.attrtable,Gui.data_arff);
		                     	}
				                  Gui.icon.setEnabled(false);
				  				Gui.Displaybt.setEnabled(false);
				  				Gui.btnUpdate.setEnabled(true);
		
					}
					else {JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);}
					ButtonAddInstance.setText("Add Instance");
					 ((MyModel)table.getModel()).setEditable(false);
				}
				
			}});
		
		
		/*******************DELETE_INSTANCES*****************/
		
		ButtonDeleteInst.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			if(table.getSelectedRow()!=-1)
			{
				
				
				delete_instance.addElement((table.getSelectedRow()+delete_instance.size()));
				if (table.isEditing()) table.getCellEditor().stopCellEditing();
				((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
				
				
                delete_inst=true;
           
			}
			}});
		
		/*****************DELETE_ATTR*************************/
		
		ButtonDeleteattr.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AttributeEdit attrframe= new AttributeEdit();
				attrframe.lblNewLabel.setText("Select attribute: ");
				CreatTab();
				attrframe.scrollPane.setViewportView(tab);
				attrframe.setVisible(true);
				attrframe.btnNewButtonOK.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(tab.getSelectedRow()!=-1) {
							
							if(tab.getSelectedRow()!=tab.getRowCount()-1)//pour ne pas supprimer l'attribut classe
							{
								setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
								delete_attr=true;
								del_attr.addElement(tab.getValueAt(tab.getSelectedRow(),1).toString());
								BoxEdit.remove(tab.getSelectedRow());
								scrollPaneAttr.setViewportView(BoxEdit);//modifier le box d'edit
							    
								removeColumn(tab.getSelectedRow(), table);//modifier la table d'edit
								setCursor(Cursor.getDefaultCursor());attrframe.setVisible(false);
							}
						else {
							JOptionPane.showMessageDialog(null,"you can not delete the class attribute","Error",JOptionPane.ERROR_MESSAGE);
							
						}
	                   	
							//Updating visualize comboboxes	
							//Updating visualize comboboxes
							
						}
					}});
			}});
		
		/****************************button OK*************************/
		
		ButtonOKEdit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				
				/************cas renomer la relation*******************/
				  if(mod_rel)
				  {
					  if(!textField.getText().matches("^.*[^a-zA-Z0-9._-À-ÖØ-öø-ÿœŒ].*$") && textField.getText().compareTo("")!=0)
					  {Gui.data_arff.setRelationName(textField.getText());
					    Gui.lblrelation.setText(textField.getText());}
					  else
					  {
						  JOptionPane.showMessageDialog(null," The relation name:  "+textField.getText()+"  can not be done\n the modification will be canceled","Error",JOptionPane.ERROR_MESSAGE); 
						  textField.setText(Gui.data_arff.relationName());
					  }
					    
					    mod_rel=false;
					    
				  }
				 
				/*****************cas renomer l'attribut*************/
				  if(mod_att)
				  {
					  for(int i=0;i<=Gui.attrtable.getRowCount()-1;i++){
							if(!((JTextField)(BoxEdit.getComponent(i))).getText().matches("^.*[^a-zA-Z0-9._-À-ÖØ-öø-ÿœŒ].*$") && ((JTextField)(BoxEdit.getComponent(i))).getText().compareTo("")!=0)
						  
						  //le teste c'est pour ne pas modifier ceux qui ont pas été modifié
							
							{ if(Gui.attrtable.getModel().getValueAt(i,1).equals(((JTextField)(BoxEdit.getComponent(i))).getText())==false)
							{ 
								
								try
								{
								Gui.data_arff.renameAttribute(i,((JTextField)(BoxEdit.getComponent(i))).getText());
								}
								catch(IllegalArgumentException er)
								{
									JOptionPane.showMessageDialog(null,"The name : "+((JTextField)(BoxEdit.getComponent(i))).getText()+" already exists","Error",JOptionPane.ERROR_MESSAGE);	
									((JTextField)(BoxEdit.getComponent(i))).setText(Gui.data_arff.attribute(i).name());
								}
								Gui.attrtable.getModel().setValueAt(((JTextField)(BoxEdit.getComponent(i))).getText(), i, 1);
								
								if(Gui.attrtable.getSelectedRow()==i) {Gui.lblNname.setText(((JTextField)(BoxEdit.getComponent(i))).getText());}//dans le cas ou la ligne est selectionné
								
							}}
							else
							{
								JOptionPane.showMessageDialog(null," The attribute name:  "+((JTextField)(BoxEdit.getComponent(i))).getText()+"  can not be done\n the modification will be canceled","Error",JOptionPane.ERROR_MESSAGE);
								((JTextField)(BoxEdit.getComponent(i))).setText(Gui.data_arff.attribute(i).name());
							}
						
								  Vector <String> vect=new Vector<String>();
						  //modifier le nom des columns de la table instances d'edit
						  for(int j=0;j<=BoxEdit.getComponentCount()-1;j++){
							  vect.addElement(((JTextField)BoxEdit.getComponent(j)).getText());
						  }
						  ((DefaultTableModel)table.getModel()).setColumnIdentifiers(vect);
		                  }
					  for(int attrindice=0;attrindice<=Gui.data_arff.numAttributes()-1;attrindice++)
						{
						if(Gui.data_arff.attribute(attrindice).isNominal())
						{
					   JComboBox <String> valuesnom = new JComboBox<String>();
						for(int i=0;i<=Gui.data_arff.attribute(attrindice).numValues()-1;i++)
							valuesnom.addItem(Gui.data_arff.attribute(attrindice).value(i));
					        table.getColumnModel().getColumn(attrindice).setCellEditor(new DefaultCellEditor(valuesnom));
					    }
						
						
						}
					//Modifying Jcomboboxes
						Gui.comboBox_2.removeAllItems();
						Gui.visucombobox.removeAllItems();
						Gui.visu2combobox.removeAllItems();
			            for (int k = 0; k <= Gui.data_arff.numAttributes() - 1; k++) {
			 
			           //  visucombobox.setModel(new DefaultComboBoxModel(new String[] {data_arff.attribute(i).name()}));
			           Gui.visucombobox.addItem(Gui.data_arff.attribute(k).name());
			           Gui.visu2combobox.addItem(Gui.data_arff.attribute(k).name());
			          if(Gui.data_arff.attribute(k).isNominal()) Gui.comboBox_2.addItem(Gui.data_arff.attribute(k).name());
			           
			           } 
			
					  mod_att=false;
				  }
				  /********************delete attribute*****************************/
				  if(delete_attr)
				  {
					  int save=Gui.attrtable.getSelectedRow();
					  for(String attdelt : del_attr)
						{

						  int indexattr=Gui.data_arff.attribute(attdelt).index();
						  String deletedatt=Gui.data_arff.attribute(indexattr).name();
						  Gui.data_arff.deleteAttributeAt(indexattr);
                            //ceux qui changent
                             Gui.modelatt.removeRow(indexattr);//modifier la table de preprocess
							//modifier le panel selected attribute
							if(save==indexattr)
							{
								Gui.attrtable.setRowSelectionInterval(0,0);
								Gui.CreatSelectedAtt(Gui.attrtable,Gui.data_arff);
								Gui.scrollP_attlist.setViewportView(Gui.attrtable);
							}
							if(Gui.data_arff.attribute(indexattr).isNominal())
								Gui.comboBox_2.removeItem(deletedatt);
								 //Removing the deleted attributs from visualize comboboxes
								 Gui.perform=false;
		                         Gui.visucombobox.removeItemAt(indexattr);
		                         Gui.visu2combobox.removeItemAt(indexattr);
		                         if(Gui.visucombobox.getItemCount()!=0)
		                     	{
		                     		
		                     			if(Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).isNumeric())
		                         {
		                     	  Gui.Barrb.setEnabled(true);
		                       	  Gui.Barrb.setSelected(true);
		                       	  Gui.BarChartrb.setEnabled(false);
		                          Gui.Pierb.setEnabled(false);

		                         }
		                         else{
		                         	if(Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).isNominal())
		                             {

		                              if(Gui.visu2combobox.getItemCount()!=0)
		                     		{
		                               Gui.BarChartrb.setEnabled(true);
		                               Gui.BarChartrb.setSelected(true);
		                               Gui.Barrb.setEnabled(false);
		                       	      if(Gui.visucombobox.getSelectedIndex()==Gui.visu2combobox.getSelectedIndex()||Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).isNumeric())
		                       	        {Gui.Pierb.setEnabled(true);}
		                       	      else{if(Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).isNominal())
		                       	    {Gui.Pierb.setEnabled(false);}}
		                     			}
		                     			}}
		                     		}
		                     
								 Gui.perform=true;
						}
					  Gui.lblattribute.setText(Gui.data_arff.numAttributes()+"");
					 Gui.lblsumweight.setText(new DecimalFormat("##.##").format(Gui.data_arff.sumOfWeights())+"");// modifier les infos de preprocess (current relation)
					//changer les numeros des attributs dans la table attribtable
						for(int k=0;k<=Gui.data_arff.numAttributes()-1;k++)
						{
							Gui.attrtable.getModel().setValueAt((k+1),k,0);
							
						}	
						delete_attr=false;del_attr.clear(); 
						Gui.icon.setEnabled(false);
						Gui.Displaybt.setEnabled(false);
						Gui.btnUpdate.setEnabled(true);}
				
					
                if(delete_inst)
				{
					int j=0;
					for(Integer ist : delete_instance)
					{
						
						 Gui.data_arff.delete(ist-j);
						 j++;
		                
					}
					 Gui.lblinstances.setText(Gui.data_arff.numInstances()+"");
		 				Gui.lblsumweight.setText(new DecimalFormat("##.##").format(Gui.data_arff.sumOfWeights())+"");//mise à jour
		                  if(Gui.attrtable.getSelectedRow()!=-1)//modifier la table selected attrbts
						{
		 					Gui.CreatSelectedAtt(Gui.attrtable,Gui.data_arff);
		 					
						 
						}delete_inst=false;delete_instance.clear();
						Gui.icon.setEnabled(false);
						Gui.Displaybt.setEnabled(false);
						Gui.btnUpdate.setEnabled(true);
				}
               
				
                 setCursor(Cursor.getDefaultCursor());setVisible(false);
				/********************fin button ok*********************/
			}});
	
		/**************bouton cancel*******************/
		ButtonCancelEdit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            /***annuler l'ajout des instances**************/
			if(add_inst)
			{
				Gui.data_arff=new Instances(Gui.data_arff_noModify);
				for(int i=table.getRowCount()-1;i>=Gui.data_arff.numInstances();i--)
			{
				
				((DefaultTableModel)table.getModel()).removeRow(i);
			}
				Gui.lblinstances.setText(Gui.data_arff.numInstances()+"");
 				Gui.lblsumweight.setText(new DecimalFormat("##.##").format(Gui.data_arff.sumOfWeights())+"");//mise à jour
                  if(Gui.attrtable.getSelectedRow()!=-1)//modifier la table selected attrbts
				{
 					Gui.CreatSelectedAtt(Gui.attrtable,Gui.data_arff);
             	}
				add_inst=false;}
	
				/*******************le cas de suppresion *****************/
			if(delete_inst)
			{
				Gui.data_arff=new Instances(Gui.data_arff_noModify);
				fillFrame(Gui.data_arff);
				delete_inst=false;delete_instance.clear();
			}
				if(delete_attr)
				{fillFrame(Gui.data_arff);delete_attr=false;del_attr.clear();}
				/************renomer relation et attributs*************/
				if(mod_rel)
				{textField.setText(Gui.data_arff.relationName());mod_rel=false;}
				if(mod_att)
				{
	               for(int i=0;i<=Gui.data_arff.numAttributes()-1;i++)
					{((JTextField)BoxEdit.getComponent(i)).setText(Gui.data_arff.attribute(i).name());}
	               mod_att=false;}
			setCursor(Cursor.getDefaultCursor());setVisible(false);
	}});
	
	/******
	 * 
	 * 
	 * ********/
		/**************button exit**********************/
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          /***annuler l'ajout des instances**************/
		    	 if(add_inst)
		    	 {
			if( ButtonAddInstance.getText().equals("Validate"))
			{
				Gui.data_arff=new Instances(Gui.data_arff_noModify);
				for(int i=table.getRowCount()-1;i>=Gui.data_arff.numInstances();i--)
			{
				
				((DefaultTableModel)table.getModel()).removeRow(i);
			}}//on a validé ça ne sert a rien.
	        add_inst=false;}
				/*******************le cas de suppresion *****************/
				if(delete_inst)
				{
					Gui.data_arff=new Instances(Gui.data_arff_noModify);
					fillFrame(Gui.data_arff);
					delete_inst=false;delete_instance.clear();
				}
				/*if(delete_attr)
				{fillFrame(Gui.data_arff);delete_attr=false;del_attr.clear();}*///l'attribut reste suprimé
				/************renomer relation et attributs*************/
				if(mod_rel)
				{textField.setText(Gui.data_arff.relationName());mod_rel=false;}
				if(mod_att)
				{
	               for(int i=0;i<=Gui.data_arff.numAttributes()-1;i++)
					{((JTextField)BoxEdit.getComponent(i)).setText(Gui.data_arff.attribute(i).name());}
	               mod_att=false;}
			setCursor(Cursor.getDefaultCursor());		}});
	}
	
	/**
	 * quelques methodes
	 */
	public void CreatTab()
	{
		
        	DefaultTableModel modeltabattredit = new DefaultTableModel(new Object[][] {null},new String[] {"N°", "Attribute",})
                {
	          	    public boolean isCellEditable(int rowIndex, int mColIndex) {
	          	        return false;
	          	    }
	          	};

	   		 tab=new JTable(modeltabattredit);
	   		tab.setForeground(Color.WHITE);
	        tab.setBackground(new Color(49,79,79));
	        tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	              	modeltabattredit.removeRow(0);
                 for ( int  i = 0; i <= BoxEdit.getComponentCount()-1; i++) {
                	 modeltabattredit.addRow(new Object[] {(i+1),((JTextField) (BoxEdit.getComponent(i))).getText()});
                 } 
		
	}
	public  void fillFrame(Instances inst) {
		
		textField.setText(inst.relationName());
		table.setModel(new MyModel(new Object[][] {{null,null},},new String[] {}));
    	table.setBackground(this.getBackground());
    	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	 
    	
    	BoxEdit = Box.createHorizontalBox();
		scrollPaneAttr.setViewportView(BoxEdit);
		for(int attrindice=0;attrindice<=inst.numAttributes()-1;attrindice++)
		{
			JTextField att=new JTextField(inst.attribute(attrindice).name());
			att.setBackground(UIManager.getColor("Menu.background"));
			BoxEdit.add(att);
			((DefaultTableModel) table.getModel()).addColumn(inst.attribute(attrindice).name());
			
			att.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent arg0) {
			    	mod_att=true;
			    	}});
			

		}
		((DefaultTableModel) table.getModel()).removeRow(0);
		String[] s=new String[inst.numAttributes()];
		for(int i=0;i<=inst.numInstances()-1;i++)
		{
		 try 
		 {s=inst.instance(i).toString().split(",");
		 ((DefaultTableModel) table.getModel()).addRow(s);}
		 catch(OutOfMemoryError e){
			 e.toString();//java -Xmx512M -jar myapp.jar
		 }
		
		}
		for(int attrindice=0;attrindice<=inst.numAttributes()-1;attrindice++)
		{
		if(inst.attribute(attrindice).isNominal())
		{
	   JComboBox <String> valuesnom = new JComboBox<String>();
		for(int i=0;i<=inst.attribute(attrindice).numValues()-1;i++)
			valuesnom.addItem(inst.attribute(attrindice).value(i));
	        table.getColumnModel().getColumn(attrindice).setCellEditor(new DefaultCellEditor(valuesnom));
	    }
		
		
		}//l'ajout des jcomboboxs doit se faire apres avoir rempli le tableau
		
	} 
	public void removeColumn(int index, JTable myTable){
	    int nRow= myTable.getRowCount();
	    int nCol= myTable.getColumnCount()-1;
	    Object[][] cells= new Object[nRow][nCol];
	    String[] names= new String[nCol];

	    for(int j=0; j<nCol; j++){
	        if(j<index){
	            names[j]= myTable.getColumnName(j);
	            for(int i=0; i<nRow; i++){
	                cells[i][j]= myTable.getValueAt(i, j);
	            }
	        }else{
	            names[j]= myTable.getColumnName(j+1);
	            for(int i=0; i<nRow; i++){
	                cells[i][j]= myTable.getValueAt(i, j+1);
	            }
	        }
	    }

	    MyModel newModel= new MyModel(cells, names);
	    table.setModel(newModel);
	    for(int attrindice=0;attrindice<=myTable.getColumnCount()-1;attrindice++)
		{
		if(Gui.data_arff.attribute(myTable.getColumnName(attrindice)).isNominal())
		{
	   JComboBox <String> valuesnom = new JComboBox<String>();
		for(int i=0;i<=Gui.data_arff.attribute(myTable.getColumnName(attrindice)).numValues()-1;i++)
			valuesnom.addItem(Gui.data_arff.attribute(myTable.getColumnName(attrindice)).value(i));
	        table.getColumnModel().getColumn(attrindice).setCellEditor(new DefaultCellEditor(valuesnom));
	    }
		}
	          
	}
}

