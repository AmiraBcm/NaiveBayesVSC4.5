package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class Interval extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable table;
    public DefaultTableModel modelinterval;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interval frame = new Interval();
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
	@SuppressWarnings("serial")
	public Interval() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AttributeEdit.class.getResource("/window/vue/BarChart.png")));
		setTitle("Interval-"+Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).name());
		setBounds(100, 100, 341, 290);
		setLocation(900,200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0,128,128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		 modelinterval =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Number", "Interval","Total"
				}
			) {
	
				boolean[] columnEditables = new boolean[] {
					false, false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table = new JTable(modelinterval);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setForeground(new Color(0, 0, 0));
		//table.setGridColor(new Color(218, 165, 32));
		
		table.setForeground(Color.WHITE);
      	table.setBackground(new Color(0,128,128));
      	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//modelinterval.removeRow(0);
		
       /*  for ( int  i = 0; i <= BoxEdit.getComponentCount()-1; i++) {
        	 modelinterval.addRow(new Object[] {(i+1),((JTextField) (BoxEdit.getComponent(i))).getText()});
         } */
		/*************************************************************/
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		
		scrollPane.setViewportView(table);
	}
}
