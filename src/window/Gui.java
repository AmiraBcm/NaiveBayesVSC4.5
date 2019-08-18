package window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFrame;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.UnassignedClassException;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import weka.filters.AllFilter;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;
import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class Gui extends JFrame {


	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	static JRadioButton Pierb;
	static JRadioButton Barrb;
	private JTextField Splitfield;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JRadioButton Splitrb;
	private JRadioButton Crossrb;
	private JTextField Crossfield;
	static JPanel ViewPanel;
	private JMenuItem mntmExit;
	static JMenuItem Save;
	private JMenuItem mntmOpen;
	static JLabel lblrelation;
	static JLabel lblinstances;
	static JLabel lblattribute;
	static JLabel lblsumweight;
	static JScrollPane scrollP_attlist;
	static JScrollPane scrollPselectatt;
	static JLabel lblNname;
	static JLabel lblNmissing;
	static JLabel lblNdistinct;
	static JLabel lblNtype;
	static JLabel lblNunique;
	private JPanel panel_selectatt,panel_1;
	private JPanel panel_Attlist;
	private JPanel panel_Currentrelation;
	private String datapath;
	private JMenuItem view;
	private JMenuItem AddInst;
	private JMenuItem Delete;
	private JMenuItem Rename;
	private JMenu mnEdit;
	private JComboBox<String> comboBox_1;
	static JComboBox<String> comboBox_2,visucombobox;
	static JTable NominalTab,NumericTab,table_nonModify;
	static JTable attrtable;
    static Instances data_arff,data_arff_noModify;
    private Instances data_select_nb=null,data_select_c45=null;
    static DefaultTableModel modelatt;
    static JTextField class_att_txtfield;
    private Edit edit=new Edit();
    static int indiceattrib_selected;
    private JButton btnNewButton;
    private JTextArea textArea;
    private JTabbedPane tabbedPane;
    static JComboBox<String> visu2combobox;
    static JRadioButton BarChartrb;
    static JButton btnUpdate;
    static ChartFrame frame;
    static JButton icon;
    static  int indice;
    private JButton btnNewButton_1;
    private JRadioButton rdbtnNewRadioButton,rdbtnNewRadioButton_1,rdbtnNewRadioButton_2;
    private JLabel lblAxisVariants;
    static JButton Displaybt;
    static boolean perform=true;
    private JPanel choixgraph_panel;
    private JMenu mnHelp;
    private JMenuItem mntmHelp;
    
    private Map<ArrayList<Double>,Integer> selectSaveNB=new LinkedHashMap<ArrayList<Double>,Integer>(),selectSaveC45=new LinkedHashMap<ArrayList<Double>,Integer>();
    private Map<ArrayList<Double>,Instances> selectDataNB=new LinkedHashMap<ArrayList<Double>,Instances>(),selectDataC45=new LinkedHashMap<ArrayList<Double>,Instances>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Displaying the splash screen
		new SplashScreen("/window/vue/ApplicationSplashScreen.gif", 5000);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					  Gui frame = new Gui();
					  
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
	public Gui() {
		
		InitComponent();
		ComponentEvents();
	}

	private void InitComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 472);
		setTitle("ALPA-Academic Licence Project Application");
		setResizable(true);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Gui.class.getResource("/window/vue/OurIcon.png")));
		JMenuBar menuBar = new JMenuBar();
		///////////////////////////////////
		menuBar.setMargin(new Insets(2, 2, 2, 0));
		menuBar.setBorder(new CompoundBorder(new CompoundBorder(new CompoundBorder(), null), null));
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(47, 79, 79));
		menuBar.setBounds(0, 0, 768, 21);
		setJMenuBar(menuBar);
	
		
		JMenu mnFile = new JMenu("File");
		mnFile.setForeground(new Color(255, 255, 255));
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("Open ...");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.VK_ALT)); 
		mnFile.add(mntmOpen);
		
		Save = new JMenuItem("Save ");
		Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.VK_ALT));
		mnFile.add(Save);
		
		mntmExit = new JMenuItem("Exit");
		
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.VK_ALT));
		mnFile.add(mntmExit);
		
		mnEdit = new JMenu("Edit");
		mnEdit.setForeground(new Color(255, 255, 255));
		menuBar.add(mnEdit);
		
		Rename = new JMenuItem("Rename");
		Rename.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.VK_ALT));
		mnEdit.add(Rename);
		
		Delete = new JMenuItem("Delete");
		Delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.VK_ALT));
		mnEdit.add(Delete);
		
		AddInst = new JMenuItem("Add");
		AddInst.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.VK_ALT));
		mnEdit.add(AddInst);
		
		view = new JMenuItem("View");
		view.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.VK_ALT));
		mnEdit.add(view);
		
		mnHelp = new JMenu("Help");
        mnHelp.setForeground(new Color(255, 255, 255));
        menuBar.add(mnHelp);
        
        mntmHelp = new JMenuItem("Help");
        mntmHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.VK_ALT));
        mnHelp.add(mntmHelp);
        
		////////////////
		contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder());/****************/
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
					.addGap(0))
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Preprocess", null, panel, null);
		
		panel_Currentrelation = new JPanel();
		panel_Currentrelation.setBackground(new Color(218, 165, 32));
		panel_Currentrelation.setForeground(Color.WHITE);
		
		JLabel lblCurrentRelation = new JLabel("Current Relation:");
		lblCurrentRelation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblCurrentRelation.setForeground(new Color(255, 255, 255));
		
		JLabel lblRelation = new JLabel("Relation:");
		lblRelation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRelation.setForeground(new Color(255, 255, 255));
		
		lblrelation = new JLabel("None");
		lblrelation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblrelation.setForeground(new Color(255, 255, 255));
		
		JLabel lblInstances = new JLabel("Instances:");
		lblInstances.setForeground(new Color(255, 255, 255));
		lblInstances.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblinstances = new JLabel("None");
		lblinstances.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblinstances.setForeground(new Color(255, 255, 255));
		
		JLabel lblNu = new JLabel("Attributes:");
		lblNu.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNu.setForeground(new Color(255, 255, 255));
		
		lblattribute = new JLabel("None");
		lblattribute.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblattribute.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblattribute.setForeground(new Color(255, 255, 255));
		
		JLabel lblSumOfWeight = new JLabel("Sum of Weight:");
		lblSumOfWeight.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblSumOfWeight.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSumOfWeight.setForeground(new Color(255, 255, 255));
		
		lblsumweight = new JLabel("None");
		lblsumweight.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblsumweight.setForeground(new Color(255, 255, 255));
		lblsumweight.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		panel_Attlist = new JPanel();
		panel_Attlist.setBackground(new Color(47, 79, 79));
		
		panel_selectatt = new JPanel();
		panel_selectatt.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_selectatt.setForeground(new Color(255, 255, 255));
		panel_selectatt.setBackground(new Color(0, 128, 128));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(0, 128, 128));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(47, 79, 79));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_Currentrelation, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
								.addComponent(panel_Attlist, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_12, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
								.addComponent(panel_selectatt, GroupLayout.PREFERRED_SIZE, 402, Short.MAX_VALUE))))
					.addGap(6))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(panel_Currentrelation, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_selectatt, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_Attlist, GroupLayout.PREFERRED_SIZE, 246, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
					.addGap(6))
		);
		
		class_att_txtfield = new JTextField();
		class_att_txtfield.setBackground(SystemColor.control);
		class_att_txtfield.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Class Attribute:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(new Color (218,165,32));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_2)
					.addGap(18)
					.addComponent(class_att_txtfield, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(13)
					.addComponent(lblNewLabel_2))
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(11)
					.addComponent(class_att_txtfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel_3.setLayout(gl_panel_3);
		GroupLayout gl_panel_Currentrelation = new GroupLayout(panel_Currentrelation);
		gl_panel_Currentrelation.setHorizontalGroup(
			gl_panel_Currentrelation.createParallelGroup(Alignment.LEADING)
				.addComponent(lblCurrentRelation, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel_Currentrelation.createSequentialGroup()
					.addGap(47)
					.addComponent(lblRelation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(lblrelation, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(lblNu, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(lblattribute, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addGap(80))
				.addGroup(gl_panel_Currentrelation.createSequentialGroup()
					.addGap(39)
					.addComponent(lblInstances, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(lblinstances, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
					.addGap(165)
					.addComponent(lblSumOfWeight, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(lblsumweight, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
					.addGap(29))
		);
		gl_panel_Currentrelation.setVerticalGroup(
			gl_panel_Currentrelation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Currentrelation.createSequentialGroup()
					.addGap(1)
					.addComponent(lblCurrentRelation, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel_Currentrelation.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRelation, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblrelation, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNu, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblattribute, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_Currentrelation.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInstances, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblinstances, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSumOfWeight, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblsumweight, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
		);
		panel_Currentrelation.setLayout(gl_panel_Currentrelation);
		
		scrollPselectatt = new JScrollPane();
		GroupLayout gl_panel_12 = new GroupLayout(panel_12);
		gl_panel_12.setHorizontalGroup(
			gl_panel_12.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_12.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPselectatt, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_12.setVerticalGroup(
			gl_panel_12.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_12.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPselectatt, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_12.setLayout(gl_panel_12);
		
		JLabel lblNewLabel_3 = new JLabel("Selected Attribute");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_4 = new JLabel("Name:");
		lblNewLabel_4.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblNname = new JLabel("None");
		lblNname.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNname.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNname.setForeground(new Color(255, 255, 255));
		
		JLabel lblDistinct = new JLabel("Distinct:");
		lblDistinct.setAlignmentY(Component.TOP_ALIGNMENT);
		lblDistinct.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDistinct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDistinct.setForeground(new Color(255, 255, 255));
		
		lblNdistinct = new JLabel("None");
		lblNdistinct.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNdistinct.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNdistinct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNdistinct.setForeground(new Color(255, 255, 255));
		
		JLabel lblType = new JLabel("Type:");
		lblType.setAlignmentY(Component.TOP_ALIGNMENT);
		lblType.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblType.setForeground(new Color(255, 255, 255));
		lblType.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblNtype = new JLabel("None");
		lblNtype.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNtype.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNtype.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNtype.setForeground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_5 = new JLabel("Unique:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblNunique = new JLabel("None");
		lblNunique.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNunique.setForeground(new Color(255, 255, 255));
		
		JLabel lblMissing = new JLabel("Missing:");
		lblMissing.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMissing.setForeground(new Color(255, 255, 255));
		
		lblNmissing = new JLabel("None");
		lblNmissing.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNmissing.setForeground(new Color(255, 255, 255));
		
		JLabel vide = new JLabel("");
		GroupLayout gl_panel_selectatt = new GroupLayout(panel_selectatt);
		gl_panel_selectatt.setHorizontalGroup(
			gl_panel_selectatt.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel_selectatt.createSequentialGroup()
					.addGap(57)
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
					.addComponent(lblNname, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
					.addGroup(gl_panel_selectatt.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_selectatt.createSequentialGroup()
							.addGap(53)
							.addComponent(lblNdistinct, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
						.addGroup(gl_panel_selectatt.createSequentialGroup()
							.addComponent(lblDistinct, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
							.addGap(145)))
					.addGap(1))
				.addGroup(gl_panel_selectatt.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_panel_selectatt.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_selectatt.createSequentialGroup()
							.addGap(12)
							.addComponent(lblType, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
						.addComponent(lblMissing, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
					.addGroup(gl_panel_selectatt.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNtype, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addComponent(lblNmissing, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
					.addGroup(gl_panel_selectatt.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_selectatt.createSequentialGroup()
							.addGap(53)
							.addComponent(lblNunique, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
						.addGroup(gl_panel_selectatt.createSequentialGroup()
							.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
							.addGap(145))
						.addComponent(vide, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
					.addGap(1))
		);
		gl_panel_selectatt.setVerticalGroup(
			gl_panel_selectatt.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_selectatt.createSequentialGroup()
					.addGap(1)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel_selectatt.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNname, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNdistinct, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDistinct, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_selectatt.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_selectatt.createSequentialGroup()
							.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMissing, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_selectatt.createSequentialGroup()
							.addComponent(lblNtype, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNmissing, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNunique, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_selectatt.createSequentialGroup()
							.addGap(13)
							.addComponent(vide, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))))
		);
		panel_selectatt.setLayout(gl_panel_selectatt);
		
		JLabel lblAttributesList = new JLabel("Attributes List");
		lblAttributesList.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblAttributesList.setForeground(new Color(255, 255, 255));
		
		scrollP_attlist = new JScrollPane();
		
		GroupLayout gl_panel_Attlist = new GroupLayout(panel_Attlist);
		gl_panel_Attlist.setHorizontalGroup(
			gl_panel_Attlist.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Attlist.createSequentialGroup()
					.addGroup(gl_panel_Attlist.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Attlist.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollP_attlist, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
						.addComponent(lblAttributesList, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_Attlist.setVerticalGroup(
			gl_panel_Attlist.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Attlist.createSequentialGroup()
					.addComponent(lblAttributesList)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollP_attlist, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		panel_Attlist.setLayout(gl_panel_Attlist);
		panel.setLayout(gl_panel);
		
		 panel_1 = new JPanel();
		tabbedPane.addTab("Classify", null, panel_1, null);
		tabbedPane.setEnabledAt(1, false);
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(218, 165, 32));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(218, 165, 32));
		
		btnNewButton = new JButton("Start");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 128));
		
		JScrollPane scrollPane = new JScrollPane();
		
		comboBox_2 = new JComboBox<String>();
		
		JLabel lblClassAttribute = new JLabel("Class Attribute");
		lblClassAttribute.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblClassAttribute.setForeground(new Color(255, 255, 255));
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClassAttribute, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, 0, 231, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGap(4)
					.addComponent(lblClassAttribute)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_8.setLayout(gl_panel_8);
		
		JLabel lblClassifier = new JLabel("Classifier");
		lblClassifier.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblClassifier.setForeground(new Color(255, 255, 255));
		
		 comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Bayes-Naive Bayes", "Trees-C4.5", "Naive Bayes VS C4.5"}));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(lblClassifier, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addGap(168))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(comboBox_1, 0, 231, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(4)
					.addComponent(lblClassifier)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		
	    textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 128, 128));
		
		JLabel lblNewLabel_6 = new JLabel("Attributes Selection");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		
		rdbtnNewRadioButton = new JRadioButton("None");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		rdbtnNewRadioButton.setForeground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setBackground(new Color(0, 128, 128));
		
		rdbtnNewRadioButton_1 = new JRadioButton("Naive Bayes");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		rdbtnNewRadioButton_1.setBackground(new Color(0, 128, 128));
		rdbtnNewRadioButton_1.setForeground(new Color(255, 255, 255));
		
		 rdbtnNewRadioButton_2 = new JRadioButton("Trees-C4.5");
		rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		rdbtnNewRadioButton_2.setForeground(new Color(255, 255, 255));
		rdbtnNewRadioButton_2.setBackground(new Color(0, 128, 128));
		
		btnNewButton_1 = new JButton("Launch");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBackground(new Color(47, 79, 79));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnNewRadioButton_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
							.addComponent(rdbtnNewRadioButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addGroup(Alignment.LEADING, gl_panel_5.createSequentialGroup()
								.addComponent(rdbtnNewRadioButton_2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addGap(2)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(rdbtnNewRadioButton)
					.addComponent(rdbtnNewRadioButton_1)
					.addGap(2)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(1)
							.addComponent(rdbtnNewRadioButton_2))
						.addComponent(btnNewButton_1)))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(47, 79, 79));
		
		JLabel lblNewLabel = new JLabel("Test Options");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		Splitrb = new JRadioButton("Percentage split");
		Splitrb.setSelected(true);
		
		buttonGroup_1.add(Splitrb);
		Splitrb.setBackground(new Color(47, 79, 79));
		Splitrb.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		Splitrb.setForeground(new Color(255, 255, 255));
		
		JLabel lblFolds = new JLabel("Folds");
		lblFolds.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblFolds.setForeground(new Color(255, 255, 255));
		
		Crossfield = new JTextField("10");
		Crossfield.setEnabled(false);
		Crossfield.setColumns(10);
		
		Splitfield = new JTextField("66");
		Splitfield.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		
		Crossrb = new JRadioButton("Cross-validation");
		
		buttonGroup_1.add(Crossrb);
		Crossrb.setBackground(new Color(47, 79, 79));
		Crossrb.setForeground(new Color(255, 255, 255));
		Crossrb.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createSequentialGroup()
							.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
								.addComponent(Splitrb)
								.addComponent(Crossrb))
							.addGap(27)
							.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFolds, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
								.addComponent(Splitfield, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(Crossfield, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGap(4)
					.addComponent(lblNewLabel)
					.addGap(6)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addComponent(Splitrb)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
							.addComponent(Splitfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFolds)
						.addComponent(Crossfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Crossrb))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
						.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(88)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_7, 0, 0, Short.MAX_VALUE))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
					.addGap(11))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		
		tabbedPane.addTab("Visualize", null, panel_2, null);
		tabbedPane.setEnabledAt(2, false);
		choixgraph_panel = new JPanel();
		choixgraph_panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		choixgraph_panel.setBackground(new Color(47, 79, 79));
		choixgraph_panel.setForeground(new Color(255, 255, 255));
		
		Barrb = new JRadioButton("Histogram");
		Barrb.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		buttonGroup.add(Barrb);
		Barrb.setSelected(true);
		
		Barrb.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		Barrb.setForeground(new Color(255, 255, 255));
		Barrb.setBackground(new Color(47, 79, 79));
		
		Pierb = new JRadioButton("Pie Chart");
		Pierb.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonGroup.add(Pierb);
		Pierb.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		Pierb.setForeground(new Color(255, 255, 255));
		Pierb.setBackground(new Color(47, 79, 79));
		
		icon = new JButton("");
		icon.setBorder(new EmptyBorder(0, 0, 0, 0));
		icon.setBackground(new Color(47, 79, 79));
		icon.setIcon(new ImageIcon(Gui.class.getResource("/window/vue/DisplayNewWindow.png")));
		icon.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 139, 139));
		
		visucombobox = new JComboBox<String>();
		
		visu2combobox = new JComboBox<String>();
		
		lblAxisVariants = new JLabel("Axis variants");
		lblAxisVariants.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAxisVariants.setForeground(new Color(255, 255, 255));
		
		ViewPanel = new JPanel();
		ViewPanel.setBackground(Color.GRAY);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(choixgraph_panel, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 578, Short.MAX_VALUE)
						.addComponent(ViewPanel, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
					.addGap(16))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(choixgraph_panel, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ViewPanel, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(47, 79, 79));
		btnUpdate.setEnabled(false);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(7)
					.addComponent(lblAxisVariants, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(visucombobox, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
						.addComponent(visu2combobox, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(3)
					.addComponent(lblAxisVariants)
					.addGap(3)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(visucombobox, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(visu2combobox, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(8)
							.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))))
		);
		panel_4.setLayout(gl_panel_4);
		
		BarChartrb = new JRadioButton("Bar Chart");
		buttonGroup.add(BarChartrb);
		BarChartrb.setBackground(new Color(47, 79, 79));
		BarChartrb.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		BarChartrb.setForeground(Color.WHITE);
		
		Displaybt = new JButton("Display");
		
		Displaybt.setBackground(new Color(0, 128, 128));
		Displaybt.setForeground(Color.WHITE);
		Displaybt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblGraphs = new JLabel("Graphs");
		lblGraphs.setForeground(new Color(255, 255, 255));
		lblGraphs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblGraphs.setBackground(new Color(47, 79, 79));
		GroupLayout gl_choixgraph_panel = new GroupLayout(choixgraph_panel);
		gl_choixgraph_panel.setHorizontalGroup(
			gl_choixgraph_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_choixgraph_panel.createSequentialGroup()
					.addGap(7)
					.addComponent(lblGraphs, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_choixgraph_panel.createSequentialGroup()
					.addGap(48)
					.addComponent(icon, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_choixgraph_panel.createSequentialGroup()
					.addGap(17)
					.addComponent(Barrb, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_choixgraph_panel.createSequentialGroup()
					.addGap(17)
					.addComponent(BarChartrb, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_choixgraph_panel.createSequentialGroup()
					.addGap(17)
					.addComponent(Pierb, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_choixgraph_panel.createSequentialGroup()
					.addGap(31)
					.addComponent(Displaybt, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
		);
		gl_choixgraph_panel.setVerticalGroup(
			gl_choixgraph_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_choixgraph_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblGraphs, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(icon, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(Barrb, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(BarChartrb)
					.addGap(3)
					.addComponent(Pierb, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(59)
					.addComponent(Displaybt, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
		);
		choixgraph_panel.setLayout(gl_choixgraph_panel);
		
		ViewPanel.setLayout(new GridLayout(1, 0, 0, 0));
		panel_2.setLayout(gl_panel_2);
		
		contentPane.setLayout(gl_contentPane);
         Rename.setEnabled(false);
         Delete.setEnabled(false);
         AddInst.setEnabled(false);
         view.setEnabled(false);
         Save.setEnabled(false);
         
         
	}

	private void ComponentEvents() {
		/////////////////////////////Menu Bar///////////////////////////
		//HELP
		Help help=new Help();
		mntmHelp.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		help.setVisible(true);
         	}
         });
		//EXIT
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});
		//OPEN
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)  {
				
				UIManager.put("ScrollPane.background", new Color(47,79,79));
				 UIManager.put("List.background", new Color(47,79,79));
			     UIManager.put("List.foreground", Color.WHITE);
				
				  UIManager.put("ToolBar.background", new Color(218,165,32));
				  UIManager.put("ComboBox.background", new Color(0,128,128));
				  UIManager.put("ComboBox.foreground", Color.WHITE);
				
   
				JComponent.setDefaultLocale(Locale.ENGLISH);
				
				JFileChooser dialogue = new JFileChooser("."){
		            @Override
		            protected javax.swing.JDialog createDialog(java.awt.Component parent) throws java.awt.HeadlessException {
		                javax.swing.JDialog dialog = super.createDialog(parent);

		                dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(AttributeEdit.class.getResource("/window/vue/open-file.png")));

		                return dialog;

		            }};//changer l'icon
				 dialogue.setLocale(Locale.ENGLISH);
				 
				FileFilter filter = new FileNameExtensionFilter("ARFF File (*.arff)","arff"),
				filter2 = new FileNameExtensionFilter("CSV File (*.csv)","csv");
				dialogue.setAcceptAllFileFilterUsed(false);
				dialogue.setFileFilter(filter);
				dialogue.addChoosableFileFilter(filter2);
				
				if (dialogue.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) 
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					datapath=dialogue.getSelectedFile().getAbsolutePath();
					
				    try {
				    	if(dialogue.getSelectedFile().getName().endsWith(".csv"))//la convertion csv to arff
						 {
				    		String datapath2=datapath.replaceAll("csv","arff");
				    	ConvertCSVToArff(new File(datapath), new File(datapath2));
				    	    datapath2=datapath;
				    		}
				    	
						 data_arff = LectureArffFile(datapath).getData();
	                      TableAttributes();
	                      ////INITIALIZE THE OPEN TAB/////////////
	                      data_select_nb=null;data_select_c45=null;
	                      selectDataC45.clear();selectDataNB.clear();selectSaveC45.clear();selectSaveNB.clear();
	                     btnNewButton_1.setText("Launch");
	      				btnNewButton_1.setEnabled(false);
	      				rdbtnNewRadioButton.setSelected(true);
	      				rdbtnNewRadioButton_2.setSelected(false);
	      				rdbtnNewRadioButton_1.setSelected(false);
	      				comboBox_1.setEnabled(true);
	      				comboBox_2.setEnabled(true);
	      				btnNewButton.setEnabled(true);
	      				Crossrb.setSelected(false);Crossfield.setText("10");Crossfield.setEnabled(false);
	      				Splitrb.setSelected(true);Splitfield.setText("66");Splitfield.setEnabled(true);
	      				textArea.setText("");
	                      ///////////////END ////////////
						 edit.fillFrame(data_arff);
						 edit.setVisible(false);///////////////////////
						 icon.setEnabled(false);
		                 ViewPanel.removeAll();
		                 ViewPanel.repaint();
		                 
		                  Rename.setEnabled(true);
		                  Delete.setEnabled(true);
		                  AddInst.setEnabled(true);
		                  view.setEnabled(true);
		                  Save.setEnabled(true);
		                  tabbedPane.setEnabledAt(1, true); 
		                  tabbedPane.setEnabledAt(2, true);
		                  setCursor(Cursor.getDefaultCursor());
				    } catch ( UnassignedClassException  e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				  
	
			}
		});
		/*****************le traitement des sous menus edit********************/
	       //////////////////////////////////////////////////////////////////////////////////
			Rename.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)  {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					edit.setTitle("Edit-Rename");
				    edit.panel.setVisible(false);
					edit.panel_2.setVisible(true);
					edit.panel_1.setVisible(true);
					edit.ButtonDeleteattr.setVisible(false);
					edit.textField.setEditable(true);
					edit.ButtonCancelEdit.setVisible(true);
					edit.setSize(new Dimension(527,230));
					edit.ButtonOKEdit.setVisible(true);
					for(int i=0;i<=edit.BoxEdit.getComponentCount()-1;i++)
					{
						((JTextField)edit.BoxEdit.getComponent(i)).setEditable(true);
						((JTextField)edit.BoxEdit.getComponent(i)).setBackground(UIManager.getColor("Menu.background"));
					}
					edit.setVisible(true);
					setCursor(Cursor.getDefaultCursor());
							}});
	        Delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)  {
	    				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    				edit.setTitle("Edit-Delete");
	    				edit.panel.setVisible(true);
	    				edit.panel_2.setVisible(false);
	    				edit.panel_1.setVisible(true);
	    				edit.ButtonDeleteattr.setVisible(true);
	    				edit.ButtonAddInstance.setVisible(false);
	    				edit.ButtonDeleteInst.setVisible(true);
	    				edit.ButtonCancelEdit.setVisible(true);
	    				edit.ButtonOKEdit.setVisible(true);
	    				edit.setSize(new Dimension(800,423));
	    				for(int i=0;i<=edit.BoxEdit.getComponentCount()-1;i++)
	    				{
	    					((JTextField)edit.BoxEdit.getComponent(i)).setEditable(false);
	    				}
	    				
	    				edit.setVisible(true);
	    				
	    				data_arff_noModify=new Instances(data_arff);
	    				indiceattrib_selected=attrtable.getSelectedRow();
	    			   setCursor(Cursor.getDefaultCursor());
	    						}});
	        
	        AddInst.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)  {
	    				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    				edit.setTitle("Edit-Add");
	    				edit.panel.setVisible(true);
	    				edit.panel_2.setVisible(false);
	    				edit.panel_1.setVisible(false);
	    				edit.ButtonAddInstance.setText("Add Instance");
	    				edit.ButtonAddInstance.setVisible(true);edit.ButtonDeleteInst.setVisible(false);
	    				edit.ButtonCancelEdit.setVisible(true);
	    				edit.ButtonOKEdit.setVisible(false);
	    				edit.setSize(new Dimension(800,423));
	    				//((MyModel)edit.table.getModel()).setEditable(false);
	    				edit.setVisible(true);
	    				data_arff_noModify=new Instances(data_arff);
	    			
	    			   setCursor(Cursor.getDefaultCursor());
	    						}});
	        
	       view.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)  {
	    				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    				edit.setTitle("Edit-View");
	    				edit.panel.setVisible(true);
	    				edit.panel_2.setVisible(true);
	    				edit.panel_1.setVisible(true);
	    				edit.ButtonDeleteattr.setVisible(false);
	    				edit.ButtonAddInstance.setVisible(false);edit.ButtonDeleteInst.setVisible(false);
	    				edit.textField.setEditable(false);
	    				edit.ButtonCancelEdit.setVisible(false);
	    				edit.ButtonOKEdit.setVisible(false);
	    				for(int i=0;i<=edit.BoxEdit.getComponentCount()-1;i++)
	    				{
	    					((JTextField)edit.BoxEdit.getComponent(i)).setEditable(false);
	    				}
	    				edit.setSize(new Dimension(800,423));	    		
	    				edit.setVisible(true);
	    			   setCursor(Cursor.getDefaultCursor());
	    						}});
	       /***********************fin d'edit**************************************/

		
		//SAVE
       Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)  {
				 JComponent.setDefaultLocale(Locale.ENGLISH);//pour la langue
				JFileChooser dialogue = new JFileChooser(datapath) {
		            @Override
		            protected javax.swing.JDialog createDialog(java.awt.Component parent) throws java.awt.HeadlessException {
		                javax.swing.JDialog dialog = super.createDialog(parent);

		                dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(AttributeEdit.class.getResource("/window/vue/save.png")));

		                return dialog;

		            }};//changer l'icon
				
				 dialogue.setLocale(Locale.ENGLISH);
				 
				FileFilter filter = new FileNameExtensionFilter("ARFF File (*.arff)","arff"),
				filter2 = new FileNameExtensionFilter("CSV File (*.csv)","csv");
				dialogue.setAcceptAllFileFilterUsed(false);
				dialogue.setFileFilter(filter);
				dialogue.addChoosableFileFilter(filter2);
				dialogue.setBackground(Color.BLACK);
				 
				if (dialogue.showSaveDialog(null)== JFileChooser.APPROVE_OPTION) 
				{
					String newFilePath=dialogue.getSelectedFile().getAbsolutePath();
					if(dialogue.getFileFilter()==filter)
					{newFilePath=newFilePath+".arff";}
					else
					{newFilePath=newFilePath+".csv";}
					try {
						weka.core.converters.ConverterUtils.DataSink.write(newFilePath, data_arff) ;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"FAIL TO SAVE ALL DATA TO FILE","Error",JOptionPane.ERROR_MESSAGE);	
					}
				}
			}});
       
		////////////////////////////////////////////////////////////////
        
		///////////////////Visualize/////////////////////////////
/////////////////////Affichage des graphes//////////////////////////
visucombobox.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
if(perform==true)
{
if(visucombobox.getItemCount()!=0)
{

if(data_arff.attribute(visucombobox.getSelectedIndex()).isNumeric())
{
Barrb.setEnabled(true);
Barrb.setSelected(true);
BarChartrb.setEnabled(false);
Pierb.setEnabled(false);

}
else{
if(data_arff.attribute(visucombobox.getSelectedIndex()).isNominal())
{

if(visu2combobox.getItemCount()!=0)
{


BarChartrb.setEnabled(true);
BarChartrb.setSelected(true);
Barrb.setEnabled(false);

if(visucombobox.getSelectedIndex()==visu2combobox.getSelectedIndex()||data_arff.attribute(visu2combobox.getSelectedIndex()).isNumeric())
 {Pierb.setEnabled(true);}
else{if(data_arff.attribute(visu2combobox.getSelectedIndex()).isNominal())
{Pierb.setEnabled(false);}}


}


}}



}
}
}});
visu2combobox.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
if(perform==true)
{
if(visucombobox.getItemCount()!=0)
{

if(data_arff.attribute(visucombobox.getSelectedIndex()).isNominal())
{

if(visu2combobox.getItemCount()!=0)
{

BarChartrb.setEnabled(true);
BarChartrb.setSelected(true);
Barrb.setEnabled(false);
if(visucombobox.getSelectedIndex()==visu2combobox.getSelectedIndex()||data_arff.attribute(visu2combobox.getSelectedIndex()).isNumeric())
 {Pierb.setEnabled(true);}
else{if(data_arff.attribute(visu2combobox.getSelectedIndex()).isNominal())
{Pierb.setEnabled(false);}}       
}
			}

}
}
}});
Displaybt.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
ViewPanel.removeAll();
if(Barrb.isSelected()){Histogram h=new Histogram();ViewPanel.add(h.chPanel);}
if(BarChartrb.isSelected()){BarChart b=new BarChart();ViewPanel.add(b.chPanel);}
if(Pierb.isSelected()){Pie P=new Pie();
ViewPanel.add(P.chPanel);}
ViewPanel.validate();
icon.setEnabled(true);
setCursor(Cursor.getDefaultCursor());
}
});
btnUpdate.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
graphDifusion();
btnUpdate.setEnabled(false);
Displaybt.setEnabled(true);
icon.setEnabled(true);
setCursor(Cursor.getDefaultCursor());
}
});

icon.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
frame.setVisible(true);
}
});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
///////////////////////////////Classify//////////////////////////////////
		/////////////////////Test options///////////////////////////////
		Crossrb.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				Crossfield.setEnabled(true);
				Splitfield.setEnabled(false);
				////////NB////////
				if(rdbtnNewRadioButton_1.isSelected()) 
				{
					
					ArrayList<Double> list=new ArrayList<Double>();
					try 
					{
					list.add((double) 1);list.add(Double.parseDouble(Crossfield.getText()));
					if(!selectDataNB.containsKey(list))
					{
					btnNewButton_1.setText("Launch");
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
					btnNewButton.setEnabled(false);
					}
					else 
					{  
						if(selectSaveNB.get(list)==0) btnNewButton_1.setText("Save as File");
						   else
						   {    btnNewButton_1.setText("File Saved");
								btnNewButton_1.setEnabled(false);	
							}
						comboBox_1.setEnabled(true);
						comboBox_2.setEnabled(true);
						btnNewButton.setEnabled(true);	
					}
					
					}catch(NumberFormatException e) {
					btnNewButton_1.setText("Launch");
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
					btnNewButton.setEnabled(false);
					}
				}
				///////////C45////////////////////
				if(rdbtnNewRadioButton_2.isSelected()) 
				{
					
					ArrayList<Double> list=new ArrayList<Double>();
					try 
					{
					list.add((double) 1);list.add(Double.parseDouble(Crossfield.getText()));
					if(!selectDataC45.containsKey(list))
					{
					btnNewButton_1.setText("Launch");
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
					btnNewButton.setEnabled(false);
					}
					else 
					{  
						if(selectSaveC45.get(list)==0) btnNewButton_1.setText("Save as File");
						   else
						   {    btnNewButton_1.setText("File Saved");
								btnNewButton_1.setEnabled(false);	
							}
						comboBox_1.setEnabled(true);
						comboBox_2.setEnabled(true);
						btnNewButton.setEnabled(true);	
					}
					
					}catch(NumberFormatException e) {
					btnNewButton_1.setText("Launch");
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
					btnNewButton.setEnabled(false);
					}
				}
				
			}
		});
		Splitrb.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Splitfield.setEnabled(true);
				Crossfield.setEnabled(false);
				//Crossfield.setBackground(new Color(192, 192, 192));
				////////////////NB/////////////
				if(rdbtnNewRadioButton_1.isSelected()) 
				{
					
					ArrayList<Double> list=new ArrayList<Double>();
					try 
					{
					list.add((double) 0);list.add(Double.parseDouble(Splitfield.getText()));
					if(!selectDataNB.containsKey(list))
					{
					btnNewButton_1.setText("Launch");
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
					btnNewButton.setEnabled(false);
					}
					else 
					{  
						if(selectSaveNB.get(list)==0) btnNewButton_1.setText("Save as File");
						   else
						   {    btnNewButton_1.setText("File Saved");
								btnNewButton_1.setEnabled(false);	
							}
						comboBox_1.setEnabled(true);
						comboBox_2.setEnabled(true);
						btnNewButton.setEnabled(true);	
					}
					
					}catch(NumberFormatException e) {
					btnNewButton_1.setText("Launch");
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
					btnNewButton.setEnabled(false);
					}
				}
				///////////C45////////////////////
				if(rdbtnNewRadioButton_2.isSelected()) 
				{
					
					ArrayList<Double> list=new ArrayList<Double>();
					try 
					{
					list.add((double) 0);list.add(Double.parseDouble(Splitfield.getText()));
					if(!selectDataC45.containsKey(list))
					{
					btnNewButton_1.setText("Launch");
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
					btnNewButton.setEnabled(false);
					}
					else 
					{  
						if(selectSaveC45.get(list)==0) btnNewButton_1.setText("Save as File");
						   else
						   {    btnNewButton_1.setText("File Saved");
								btnNewButton_1.setEnabled(false);	
							}
						comboBox_1.setEnabled(true);
						comboBox_2.setEnabled(true);
						btnNewButton.setEnabled(true);	
					}
					
					}catch(NumberFormatException e) {
					btnNewButton_1.setText("Launch");
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
					btnNewButton.setEnabled(false);}
				}
			}
		});
		Crossfield.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				
				Crossfield.setEditable(true);
				btnNewButton_1.setText("Launch");
				btnNewButton_1.setEnabled(false);
				rdbtnNewRadioButton.setSelected(true);
				rdbtnNewRadioButton_2.setSelected(false);
				rdbtnNewRadioButton_1.setSelected(false);
				comboBox_1.setEnabled(true);
				comboBox_2.setEnabled(true);
				btnNewButton.setEnabled(true);
			}});
		Splitfield.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				btnNewButton_1.setText("Launch");
				btnNewButton_1.setEnabled(false);
				rdbtnNewRadioButton.setSelected(true);
				rdbtnNewRadioButton_2.setSelected(false);
				rdbtnNewRadioButton_1.setSelected(false);
				comboBox_1.setEnabled(true);
				comboBox_2.setEnabled(true);
				btnNewButton.setEnabled(true);
			}});
		////////////////END TEST OPTIONS////////////////
              ////////////////NONE////////////////////////
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)  {
				btnNewButton_1.setText("Launch");
				btnNewButton_1.setEnabled(false);
				rdbtnNewRadioButton_2.setSelected(false);
				rdbtnNewRadioButton_1.setSelected(false);
				comboBox_1.setEnabled(true);
				comboBox_2.setEnabled(true);
				btnNewButton.setEnabled(true);
			}});
		////////////////SELECTION WITH NAIVE BAYES//////////////////////
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)  {
				btnNewButton_1.setEnabled(true);/****************/
				rdbtnNewRadioButton_2.setSelected(false);
				rdbtnNewRadioButton.setSelected(false);
				ArrayList<Double> list=new ArrayList<Double>();
				try 
				{
				if(Crossrb.isSelected()) {list.add((double) 1);list.add(Double.parseDouble(Crossfield.getText()));}
				if(Splitrb.isSelected()) {list.add((double) 0);list.add(Double.parseDouble(Splitfield.getText()));}
				if(!selectDataNB.containsKey(list))
				{
				btnNewButton_1.setText("Launch");
				comboBox_1.setEnabled(false);
				comboBox_2.setEnabled(false);
				btnNewButton.setEnabled(false);
				}
				else 
				{  
					if(selectSaveNB.get(list)==0) btnNewButton_1.setText("Save as File");
					   else
					   {    btnNewButton_1.setText("File Saved");
							btnNewButton_1.setEnabled(false);	
						}
					comboBox_1.setEnabled(true);
					comboBox_2.setEnabled(true);
					btnNewButton.setEnabled(true);	
				}}
				catch(NumberFormatException e) {btnNewButton_1.setText("Launch");
					//if(Crossrb.isSelected()) JOptionPane.showMessageDialog(null,"Check the fold","Error",JOptionPane.ERROR_MESSAGE);
					//if(Splitrb.isSelected()) JOptionPane.showMessageDialog(null,"Check the percentage split","Error",JOptionPane.ERROR_MESSAGE);btnNewButton_1.setText("Launch");
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
					btnNewButton.setEnabled(false);
				}
			}});
		
		//////////////////SELECTION WITH C4.5////////////////////
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)  {
				btnNewButton_1.setEnabled(true);
				rdbtnNewRadioButton_1.setSelected(false);
				rdbtnNewRadioButton.setSelected(false);
				ArrayList<Double> list=new ArrayList<Double>();
				try {
				if(Crossrb.isSelected()) {list.add((double) 1);list.add(Double.parseDouble(Crossfield.getText()));}
				if(Splitrb.isSelected()) {list.add((double) 0);list.add(Double.parseDouble(Splitfield.getText()));}
				if(!selectDataC45.containsKey(list))
				{
				btnNewButton_1.setText("Launch");
				comboBox_1.setEnabled(false);
				comboBox_2.setEnabled(false);
				btnNewButton.setEnabled(false);
				}
				else 
				{  if(selectSaveC45.get(list)==0) btnNewButton_1.setText("Save as File");
				   else
				   {    btnNewButton_1.setText("File Saved");
						btnNewButton_1.setEnabled(false);	
					}
					comboBox_1.setEnabled(true);
					comboBox_2.setEnabled(true);
					btnNewButton.setEnabled(true);	
				}}
				catch(NumberFormatException e) {btnNewButton_1.setText("Launch");
					//if(Crossrb.isSelected()) JOptionPane.showMessageDialog(null,"Check the fold","Error",JOptionPane.ERROR_MESSAGE);
					//if(Splitrb.isSelected()) JOptionPane.showMessageDialog(null,"Check the percentage split","Error",JOptionPane.ERROR_MESSAGE);btnNewButton_1.setText("Launch");
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
					btnNewButton.setEnabled(false);
				}
			}});
		////////////LAUNCH///////////////////
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)  {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			
			if(btnNewButton_1.getText().compareTo("Launch")==0)
			{
				textArea.setText("");
				// load the naive bayes selection
			if(rdbtnNewRadioButton_1.isSelected()) 
			 {
				data_select_nb=new Instances(data_arff);
				data_select_nb.setClassIndex(data_arff.classIndex());
				
				
				ArrayList<Double> list=new ArrayList<Double>();
				try {
				if(Crossrb.isSelected()) {list.add((double) 1);list.add(Double.parseDouble(Crossfield.getText()));}
				if(Splitrb.isSelected()) {list.add((double) 0);list.add(Double.parseDouble(Splitfield.getText()));}
				selectDataNB.put(list, data_select_nb);
				selectSaveNB.put(list, 0);
				btnNewButton_1.setText("Save as File");
				comboBox_1.setEnabled(true);
				comboBox_2.setEnabled(true);
				btnNewButton.setEnabled(true);
				if(Crossrb.isSelected()) ApprochCV(new NaiveBayes(),data_select_nb, Crossfield.getText(),data_select_nb.attribute(comboBox_2.getSelectedItem().toString()).index());
				if(Splitrb.isSelected()) ApprochSplit(new NaiveBayes(),data_select_nb, Splitfield.getText(),data_select_nb.attribute(comboBox_2.getSelectedItem().toString()).index());
				
				}
				catch(NumberFormatException  e){
					
					if(Crossrb.isSelected()) JOptionPane.showMessageDialog(null,"Check the fold","Error",JOptionPane.ERROR_MESSAGE);
					if(Splitrb.isSelected()) JOptionPane.showMessageDialog(null,"Check the percentage split","Error",JOptionPane.ERROR_MESSAGE);
					}
			 }
			 
			// load the c4.5 selection
			if(rdbtnNewRadioButton_2.isSelected()) 
			{
				data_select_c45=new Instances(data_arff);
				data_select_c45.setClassIndex(data_arff.classIndex());
				
				
				ArrayList<Double> list=new ArrayList<Double>();
				try {
				if(Crossrb.isSelected()) {list.add((double) 1);list.add(Double.parseDouble(Crossfield.getText()));}
				if(Splitrb.isSelected()) {list.add((double) 0);list.add(Double.parseDouble(Splitfield.getText()));}
				selectDataC45.put(list, data_select_c45);
				selectSaveC45.put(list, 0);
				btnNewButton_1.setText("Save as File");
				comboBox_1.setEnabled(true);
				comboBox_2.setEnabled(true);
				btnNewButton.setEnabled(true);
				if(Crossrb.isSelected()) ApprochCV(new J48(),data_select_c45, Crossfield.getText(),data_select_c45.attribute(comboBox_2.getSelectedItem().toString()).index());
				if(Splitrb.isSelected()) ApprochSplit(new J48(),data_select_c45, Splitfield.getText(),data_select_c45.attribute(comboBox_2.getSelectedItem().toString()).index());
				
				}
				catch(NumberFormatException e)
				{
					if(Crossrb.isSelected()) JOptionPane.showMessageDialog(null,"Check the fold","Error",JOptionPane.ERROR_MESSAGE);
					if(Splitrb.isSelected()) JOptionPane.showMessageDialog(null,"Check the percentage split","Error",JOptionPane.ERROR_MESSAGE);
	
				}
			}
			}
			else/////////////////////SAVE/////////////////////////////
			{
				 JComponent.setDefaultLocale(Locale.ENGLISH);//pour la langue
					JFileChooser dialogue = new JFileChooser(datapath) {
			            @Override
			            protected javax.swing.JDialog createDialog(java.awt.Component parent) throws java.awt.HeadlessException {
			                javax.swing.JDialog dialog = super.createDialog(parent);

			                dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(AttributeEdit.class.getResource("/window/vue/save.png")));

			                return dialog;

			            }};//changer l'icon
					//dialogue.setDefaultLocale(Locale.ENGLISH);
					 dialogue.setLocale(Locale.ENGLISH);
					 
					FileFilter filter = new FileNameExtensionFilter("ARFF File (*.arff)","arff"),
					filter2 = new FileNameExtensionFilter("CSV File (*.csv)","csv");
					dialogue.setAcceptAllFileFilterUsed(false);
					dialogue.setFileFilter(filter);
					dialogue.addChoosableFileFilter(filter2);
					dialogue.setBackground(Color.BLACK);
					 
					if (dialogue.showSaveDialog(null)== JFileChooser.APPROVE_OPTION) 
					{
						String newFilePath=dialogue.getSelectedFile().getAbsolutePath();
						if(dialogue.getFileFilter()==filter)
						{newFilePath=newFilePath+".arff";}
						else
						{newFilePath=newFilePath+".csv";}
						try {
						if(rdbtnNewRadioButton_1.isSelected())
							{weka.core.converters.ConverterUtils.DataSink.write(newFilePath, data_select_nb) ;
							ArrayList<Double> list=new ArrayList<Double>();
							if(Crossrb.isSelected()) {list.add((double) 1);list.add(Double.parseDouble(Crossfield.getText()));}
							if(Splitrb.isSelected()) {list.add((double) 0);list.add(Double.parseDouble(Splitfield.getText()));}
							
							selectSaveNB.put(list, 1);
							}
						if(rdbtnNewRadioButton_2.isSelected())
							{weka.core.converters.ConverterUtils.DataSink.write(newFilePath, data_select_c45);
							ArrayList<Double> list=new ArrayList<Double>();
							if(Crossrb.isSelected()) {list.add((double) 1);list.add(Double.parseDouble(Crossfield.getText()));}
							if(Splitrb.isSelected()) {list.add((double) 0);list.add(Double.parseDouble(Splitfield.getText()));}
							
							selectSaveC45.put(list, 1);
							}
						
						
						btnNewButton_1.setText("File Saved");
						btnNewButton_1.setEnabled(false);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,"FAIL TO SAVE ALL DATA TO FILE","Error",JOptionPane.ERROR_MESSAGE);	
						}
					}

			}
			setCursor(Cursor.getDefaultCursor());
			}});
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0)  {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				//////////////WITHOUT SELECTION///////////////////
				if(rdbtnNewRadioButton.isSelected())
				{
				//testing and training  with naivebayes
				if(comboBox_1.getSelectedIndex()==0 && Splitrb.isSelected()) {TrainingTesting(new NaiveBayes(),data_arff, Splitfield.getText(),data_arff.attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//testing and training with decision tree
				if(comboBox_1.getSelectedIndex()==1 && Splitrb.isSelected()) {TrainingTesting(new J48(),data_arff, Splitfield.getText(),data_arff.attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//cross validation with naivebayes
				if(comboBox_1.getSelectedIndex()==0 && Crossrb.isSelected()) {CrossValidation(new NaiveBayes(),data_arff, Crossfield.getText(),data_arff.attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//cross validation with decision tree
				if(comboBox_1.getSelectedIndex()==1 && Crossrb.isSelected()) {CrossValidation(new J48(),data_arff, Crossfield.getText(),data_arff.attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//compare with cross validation
				if(comboBox_1.getSelectedIndex()==2 && Crossrb.isSelected()) {CompareNaiveBayesC45CrossValid(data_arff, Crossfield.getText(),data_arff.attribute(comboBox_2.getSelectedItem().toString()).index());}
                			
			    //compare with training
				if(comboBox_1.getSelectedIndex()==2 && Splitrb.isSelected()) {CompareNaiveBayesC45Training(data_arff, Splitfield.getText(),data_arff.attribute(comboBox_2.getSelectedItem().toString()).index());}
				}
				/*
				 * 
				 */
				//////////////////SELECTION NAIVZ BAYES//////////////
				if(rdbtnNewRadioButton_1.isSelected())
				{
					
					ArrayList<Double> list=new ArrayList<Double>();
					if(Crossrb.isSelected()) {list.add((double) 1);list.add(Double.parseDouble(Crossfield.getText()));}
					if(Splitrb.isSelected()) {list.add((double) 0);list.add(Double.parseDouble(Splitfield.getText()));}

					if(selectDataNB.containsKey(list))
					{
				//testing and training  with naivebayes
				if(comboBox_1.getSelectedIndex()==0 && Splitrb.isSelected()) {TrainingTesting(new NaiveBayes(),selectDataNB.get(list), Splitfield.getText(),selectDataNB.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//testing and training with decision tree
				if(comboBox_1.getSelectedIndex()==1 && Splitrb.isSelected()) {TrainingTesting(new J48(),selectDataNB.get(list), Splitfield.getText(),selectDataNB.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//cross validation with naivebayes
				if(comboBox_1.getSelectedIndex()==0 && Crossrb.isSelected()) {CrossValidation(new NaiveBayes(),selectDataNB.get(list), Crossfield.getText(),selectDataNB.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//cross validation with decision tree
				if(comboBox_1.getSelectedIndex()==1 && Crossrb.isSelected()) {CrossValidation(new J48(),selectDataNB.get(list), Crossfield.getText(),selectDataNB.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//compare with cross validation
				if(comboBox_1.getSelectedIndex()==2 && Crossrb.isSelected()) {CompareNaiveBayesC45CrossValid(selectDataNB.get(list), Crossfield.getText(),selectDataNB.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
                			
			    //compare with training
				if(comboBox_1.getSelectedIndex()==2 && Splitrb.isSelected()) {CompareNaiveBayesC45Training(selectDataNB.get(list), Splitfield.getText(),selectDataNB.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
					}
					}
				/*
				 * 
				 */
                   //////////////////SELECTION C4.5//////////////
				if(rdbtnNewRadioButton_2.isSelected())
				{
					ArrayList<Double> list=new ArrayList<Double>();
					if(Crossrb.isSelected()) {list.add((double) 1);list.add(Double.parseDouble(Crossfield.getText()));}
					if(Splitrb.isSelected()) {list.add((double) 0);list.add(Double.parseDouble(Splitfield.getText()));}
					
					if(selectDataC45.containsKey(list))
					{
				//testing and training  with naivebayes
				if(comboBox_1.getSelectedIndex()==0 && Splitrb.isSelected()) {TrainingTesting(new NaiveBayes(),selectDataC45.get(list), Splitfield.getText(),selectDataC45.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//testing and training with decision tree
				if(comboBox_1.getSelectedIndex()==1 && Splitrb.isSelected()) {TrainingTesting(new J48(),selectDataC45.get(list), Splitfield.getText(),selectDataC45.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//cross validation with naivebayes
				if(comboBox_1.getSelectedIndex()==0 && Crossrb.isSelected()) {CrossValidation(new NaiveBayes(),selectDataC45.get(list), Crossfield.getText(),selectDataC45.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//cross validation with decision tree
				if(comboBox_1.getSelectedIndex()==1 && Crossrb.isSelected()) {CrossValidation(new J48(),selectDataC45.get(list), Crossfield.getText(),selectDataC45.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
				
				//compare with cross validation
				if(comboBox_1.getSelectedIndex()==2 && Crossrb.isSelected()) {CompareNaiveBayesC45CrossValid(selectDataC45.get(list), Crossfield.getText(),selectDataC45.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
              			
			    //compare with training
				if(comboBox_1.getSelectedIndex()==2 && Splitrb.isSelected()) {CompareNaiveBayesC45Training(selectDataC45.get(list), Splitfield.getText(),selectDataC45.get(list).attribute(comboBox_2.getSelectedItem().toString()).index());}
				}
				}
				setCursor(Cursor.getDefaultCursor());
			}});
		///////////////////////////////////////////////////////////////
		
	}
	////Methodes of Classification///////////
	public void InfoDataset (Instances inst) {
		 textArea.setText("");
		 textArea.append(" Relation:  "+inst.relationName()+"\n");
		 textArea.append(" Attributes:  "+inst.numAttributes()+"\n");
         textArea.append(" Instances:  "+inst.numInstances()+"\n\n");
         
	}
	public void filter(AllFilter ff,Discretize filteras1,Instances inst)throws Exception
	{
        	 ff.setInputFormat(inst);
			inst = weka.filters.Filter.useFilter(inst, ff);
			filteras1.setInputFormat(inst);
	        inst= Filter.useFilter(inst, filteras1);
		}
public void resultEvaluation(Evaluation e) throws Exception {
          /******************le sommaire*********************/
          textArea.append(" Correctly Classified Instances           "+new DecimalFormat("##.##").format(e.correct())+"               "+new DecimalFormat("##.##").format(e.pctCorrect())+"      %\n");
          textArea.append(" Incorrectly Classified Instances         "+new DecimalFormat("##.##").format(e.incorrect())+"               "+new DecimalFormat("##.##").format(e.pctIncorrect())+"      %\n\n");
          /*******************detail accurency*********************/
          textArea.append("=== Detailed Accuracy By Class ===\n\n");
          textArea.append(" Precision	Recall	Class\n");
          for(int i=0;i<data_arff.classAttribute().numValues();i++)
          {
        	  textArea.append(" "+new DecimalFormat("#0.000").format(e.precision(i))+"	"+new DecimalFormat("#0.000").format(e.recall(i))+"	"+data_arff.attribute(data_arff.classIndex()).value(i)+"\n");  
          }
          textArea.append("\n");
          /*****************matrice de confusion*****************/
        textArea.append(e.toMatrixString());
       textArea.append("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n");
           	
	}
	public void TrainingTesting(Classifier cls,Instances inst, String pourcentage,int classattr) {

		/*********info sur le data s***/
            InfoDataset(inst);
          /**
           * *le split**/
          
            textArea.append(" Test mode:    split "+pourcentage+" %  train, remainder test\n" );
            try
            {int trainSize = (int) Math.round(inst.numInstances() * Double.parseDouble(pourcentage)/100);
            if(trainSize < inst.numInstances() && trainSize >=0 )
 		   {  
            textArea.append(" train size:  "+trainSize);
             int testSize = inst.numInstances() - trainSize;
             textArea.append("	test size:  "+testSize+"\n");
           Instances train = new Instances(inst, 0, trainSize);
          train.setClassIndex(classattr);   
          Instances test = new Instances(inst, trainSize, testSize);
             test.setClassIndex(classattr);
             /**
              * implementer le filtre****/
           try {
         	   
               /**** le classificateur*********/
                 long start = System.currentTimeMillis();                   
				 cls.buildClassifier(train);
				 long end = System.currentTimeMillis();
				 textArea.append("\n Classifier model: ***training set***\n");
				 textArea.append(" "+cls.toString());

				if(((end - start) / 1000)>=3600) textArea.append(" Time taken to build model : "+(((end - start) / 1000 )/3600)+" hours  "+
						((((end - start) / 1000) % 3600)/60)+ " minutes  "+new DecimalFormat("#0.000").format((((end - start) / 1000d) % 3600) % 60)+" seconds\n\n");
				else if(((end - start) / 1000)>=60) textArea.append(" Time taken to build model : "+ (((end - start) / 1000)/60)+ " minutes  "+new DecimalFormat("#0.000").format(((end - start) / 1000d) % 60)+" seconds\n\n");       
				else textArea.append(" Time taken to build model : "+new DecimalFormat("#0.000").format((end - start) / 1000d)+" seconds\n\n");
				/**
    				  		 * evaluate classifier model on test split
    				  		
    				  		 		 */
				            
    						
    						start = System.currentTimeMillis();
    						Evaluation evala = new Evaluation(train);    						
    						evala.evaluateModel(cls, test);
    						end = System.currentTimeMillis();
    						
    						textArea.append(" Evaluation:    ***testing set***\n");
    						if(((end - start) / 1000)>=3600) textArea.append(" Time taken to test model : "+(((end - start) / 1000 )/3600)+" hours  "+
    								((((end - start) / 1000) % 3600)/60)+ " minutes  "+new DecimalFormat("#0.000").format((((end - start) / 1000d) % 3600) % 60)+" seconds\n\n");
    						else if(((end - start) / 1000)>=60) textArea.append(" Time taken to test model : "+ (((end - start) / 1000)/60)+ " minutes  "+new DecimalFormat("#0.000").format(((end - start) / 1000d) % 60)+" seconds\n\n");       
    						else textArea.append(" Time taken to test model : "+new DecimalFormat("#0.000").format((end - start) / 1000d)+" seconds\n\n");
    					
    						resultEvaluation(evala);
    						  
                       } catch (Exception e) {
			                     // TODO Auto-generated catch block
               	     			textArea.append(e.toString());e.printStackTrace();
               			
		                                   }
	             }
            
            else JOptionPane.showMessageDialog(null,"Check the percentage split","Error",JOptionPane.ERROR_MESSAGE);
            }catch(NumberFormatException e) {
            	JOptionPane.showMessageDialog(null,"Check the percentage split","Error",JOptionPane.ERROR_MESSAGE);	
            }
  
	}
	public void CrossValidation(Classifier cls,Instances inst, String pourcentage,int classattr) {
		
                   /********info sur le data set**********/
				     InfoDataset(inst);
				 
				  textArea.append(" Test mode:    "+pourcentage+"-fold  cross-validation\n");
				                     
	         /***************implementer le filtre*******/
       	      try {
       	    									
	       /********************** le classificateur*********/
                 long start = System.currentTimeMillis();		                     
			     cls.buildClassifier(inst);
			     long end = System.currentTimeMillis();
			 textArea.append("\n Classifier model:\n");
			textArea.append(" "+cls.toString());
			if(((end - start) / 1000)>=3600) textArea.append(" Time taken to build model : "+(((end - start) / 1000 )/3600)+" hours  "+
					((((end - start) / 1000) % 3600)/60)+ " minutes  "+new DecimalFormat("#0.000").format((((end - start) / 1000d) % 3600) % 60)+" seconds\n\n");
			else if(((end - start) / 1000)>=60) textArea.append(" Time taken to build model : "+ (((end - start) / 1000)/60)+ " minutes  "+new DecimalFormat("#0.000").format(((end - start) / 1000d) % 60)+" seconds\n\n");       
			else textArea.append(" Time taken to build model : "+new DecimalFormat("#0.000").format((end - start) / 1000d)+" seconds\n\n");
					   				
	     /**
	      * evaluate classifier model on test split
	     				  		*/
			                     
	     						Evaluation evala = new Evaluation(inst);
	     	try
	           {start = System.currentTimeMillis();
	     		evala.crossValidateModel(cls,inst,Integer.parseInt(pourcentage), new Random(1));
	              end = System.currentTimeMillis();
	           textArea.append(" Evaluation:\n");
	           if(((end - start) / 1000)>=3600) textArea.append(" Time taken : "+(((end - start) / 1000 )/3600)+" hours  "+
						((((end - start) / 1000) % 3600)/60)+ " minutes  "+new DecimalFormat("#0.000").format((((end - start) / 1000d) % 3600) % 60)+" seconds\n\n");
				else if(((end - start) / 1000)>=60) textArea.append(" Time taken : "+ (((end - start) / 1000)/60)+ " minutes  "+new DecimalFormat("#0.000").format(((end - start) / 1000d) % 60)+" seconds\n\n");       
				else textArea.append(" Time taken : "+new DecimalFormat("#0.000").format((end - start) / 1000d)+" seconds\n\n");
			
	           
	           resultEvaluation(evala);
	     		
	           }
	     		catch(NumberFormatException e) {
		         JOptionPane.showMessageDialog(null,"Check the fold","Error",JOptionPane.ERROR_MESSAGE);}
	     		} catch (Exception e) {
				// TODO Auto-generated catch block
	     			textArea.append(e.toString());e.printStackTrace();
				}    										             
                 
	}
 public void CompareNaiveBayesC45CrossValid(Instances inst, String pourcentage,int classattr) {
	 InfoDataset(inst);
	 textArea.append(" Test mode:    "+pourcentage+"-fold  cross-validation\n\n");
         /***************implementer le filtre*********/
                   try {
					 
         /************ les classificateurs***********/
	                 NaiveBayes   naiveb = new NaiveBayes();J48   c45 = new J48();
	      		  long startbuildnb=System.currentTimeMillis();
	                 naiveb.buildClassifier(inst);
	                 long endbuildnb=System.currentTimeMillis();
	               
	               c45.buildClassifier(inst);
	               long endbuildc45=System.currentTimeMillis();
		   
		  /*******************************************************************************/       
		   /**
	  		 * evaluate classifier model on test split
	  		 		 */
			Evaluation evalnaive = new Evaluation(inst);Evaluation evalc45 = new Evaluation(inst);
			try
			{long starttestnb=System.currentTimeMillis();
			evalnaive.crossValidateModel(naiveb,inst,Integer.parseInt(pourcentage), new Random(1));
			long endtestnb=System.currentTimeMillis();
			evalc45.crossValidateModel(c45,inst,Integer.parseInt(pourcentage), new Random(1));
			long endtestc45=System.currentTimeMillis();
			
			 ComparisonTable(evalnaive, evalc45, startbuildnb, endbuildnb, endbuildc45, starttestnb, endtestnb, endtestc45, inst," Time taken in the evaluation\n");
			}catch(NumberFormatException e) {
            	JOptionPane.showMessageDialog(null,"Check the fold","Error",JOptionPane.ERROR_MESSAGE);}
				}catch (Exception e) {
				// TODO Auto-generated catch block
	     			textArea.append(e.toString());e.printStackTrace();
				}   
	}
 
 public void CompareNaiveBayesC45Training(Instances inst, String pourcentage,int classattr) {
	      InfoDataset(inst);
	     /*****************le split************/
	      textArea.append(" Test mode:    split "+pourcentage+" %  train, remainder test\n" );
          try
          {int trainSize = (int) Math.round(inst.numInstances() * Double.parseDouble(pourcentage)/100);
          if(trainSize < inst.numInstances() && trainSize >=0 )
		   {  
          textArea.append(" train size:  "+trainSize);
           int testSize = inst.numInstances() - trainSize;
           textArea.append("	test size:  "+testSize+"\n");
         Instances train = new Instances(inst, 0, trainSize);
        train.setClassIndex(classattr);   
        Instances test = new Instances(inst, trainSize, testSize);
           test.setClassIndex(classattr);
           /**
            * implementer le filtre****/
         try {
        	 
         /********************** les classificateurs***************************/               
                NaiveBayes   naiveb = new NaiveBayes();
     		    J48   c45 = new J48();
		       
		        long startbuildnb=System.currentTimeMillis();
                naiveb.buildClassifier(train);
                long endbuildnb=System.currentTimeMillis();
              
              c45.buildClassifier(train);
              long endbuildc45=System.currentTimeMillis();
		   
		   /**
	  		 * evaluate classifier model on test split
	  		 		 */
              Evaluation evalnaive = new Evaluation(train);
              Evaluation evalc45 = new Evaluation(train);
              long starttestnb=System.currentTimeMillis();
              evalnaive.evaluateModel(naiveb, test);
			long endtestnb=System.currentTimeMillis();
			evalc45.evaluateModel(c45, test);
			long endtestc45=System.currentTimeMillis();
			
			ComparisonTable(evalnaive, evalc45, startbuildnb, endbuildnb, endbuildc45, starttestnb, endtestnb, endtestc45, inst," Time taken to test model\n");
         
				}catch (Exception e) {
					// TODO Auto-generated catch block
					textArea.append(e.toString());e.printStackTrace();
				}}
         else JOptionPane.showMessageDialog(null,"Check the percentage split","Error",JOptionPane.ERROR_MESSAGE);
         }catch(NumberFormatException e) {
         	JOptionPane.showMessageDialog(null,"Check the percentage split","Error",JOptionPane.ERROR_MESSAGE);	
         }

	}	
 public void ComparisonTable(Evaluation evalnaive, Evaluation evalc45,long startbuildnb,long endbuildnb,long endbuildc45,long  starttestnb,long endtestnb ,long endtestc45 , Instances inst , String s)
 {
	 textArea.append("* * * * * Comparison Table * * * * *\n");
     textArea.append("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
     textArea.append("        Naive bayes	|       C4.5		|\n");
    
     textArea.append("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n");
   
       
      textArea.append(" "+new DecimalFormat("##.##").format(evalnaive.correct())+" Instances  ("+new DecimalFormat("##.##").format(evalnaive.pctCorrect())+" %)	| "+
   		   new DecimalFormat("##.##").format(evalc45.correct())+" Instances  ("+new DecimalFormat("##.##").format(evalc45.pctCorrect())+" %)	| "+"Correctly Classified Instances\n");
      textArea.append("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
      textArea.append(" "+new DecimalFormat("##.##").format(evalnaive.incorrect())+" Instances  ("+new DecimalFormat("##.##").format(evalnaive.pctIncorrect())+" %)	| "+
   		   new DecimalFormat("##.##").format(evalc45.incorrect())+" Instances  ("+new DecimalFormat("##.##").format(evalc45.pctIncorrect())+" %)	| "+"Incorrectly Classified Instances\n");
      textArea.append("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n");
      textArea.append(" Precision	| Recall	| Precision	| Recall	| Class\n");
   
      
      for(int i=0;i<inst.classAttribute().numValues();i++)
     {
   	  textArea.append(" "+new DecimalFormat("#0.000").format(evalnaive.precision(i))+"	| "+new DecimalFormat("#0.000").format(evalnaive.recall(i))+
   		"	| "+new DecimalFormat("#0.000").format(evalc45.precision(i))+"	| "+new DecimalFormat("#0.000").format(evalc45.recall(i))+"	| "+inst.attribute(inst.classIndex()).value(i)+"\n");  
   	  
     }
      textArea.append("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n");
        ////////////////////BUILD MODEL TIME/////////////////////////////
if(((endbuildnb - startbuildnb) / 1000)>=60) {
	  if(((endbuildc45 - endbuildnb) / 1000)>=60) {
		  textArea.append(" "+(((endbuildnb - startbuildnb) / 1000)/60)+" min	"+new DecimalFormat("#0.000").format(((endbuildnb - startbuildnb) / 1000d) % 60)+" sec	| "+
      		  (((endbuildc45 - endbuildnb) / 1000)/60)+" min	"+new DecimalFormat("#0.000").format(((endbuildc45 - endbuildnb) / 1000d) % 60)+" sec	| "+" Time taken to build model\n");
            }
	  else {
		  textArea.append(" "+(((endbuildnb - startbuildnb) / 1000)/60)+" min	"+new DecimalFormat("#0.000").format(((endbuildnb - startbuildnb) / 1000d) % 60)+" sec	| "+
      		  new DecimalFormat("#0.000").format(((endbuildc45 - endbuildnb) / 1000d) % 60)+" sec		| "+" Time taken to build model\n");
        }
}
else {
        if(((endbuildc45 - endbuildnb) / 1000)>=60) {
      	  textArea.append(" "+new DecimalFormat("#0.000").format(((endbuildnb - startbuildnb) / 1000d) % 60)+" sec		| "+
	        		  (((endbuildc45 - endbuildnb) / 1000)/60)+" min	"+new DecimalFormat("#0.000").format(((endbuildc45 - endbuildnb) / 1000d) % 60)+" sec	| "+" Time taken to build model\n");
	               }
	  else {
		  textArea.append(" "+new DecimalFormat("#0.000").format(((endbuildnb - startbuildnb) / 1000d) % 60)+" sec		| "+
      		  new DecimalFormat("#0.000").format(((endbuildc45 - endbuildnb) / 1000d) % 60)+" sec		| "+" Time taken to build model\n");
             
	  } 
}
textArea.append("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n");
//////////////////////TEST TIME///////////////////////
if(((endtestnb - starttestnb) / 1000)>=60) {
	  if(((endtestc45 - endtestnb) / 1000)>=60) {
		  textArea.append(" "+(((endtestnb - starttestnb) / 1000)/60)+" min	"+new DecimalFormat("#0.000").format(((endtestnb - starttestnb) / 1000d) % 60)+" sec	| "+
      		  (((endtestc45 - endtestnb) / 1000)/60)+" min	"+new DecimalFormat("#0.000").format(((endtestc45 - endtestnb) / 1000d) % 60)+" sec	| "+s);
            }
	  else {
		  textArea.append(" "+(((endtestnb - starttestnb) / 1000)/60)+" min	"+new DecimalFormat("#0.000").format(((endtestnb - starttestnb) / 1000d) % 60)+" sec	| "+
      		  new DecimalFormat("#0.000").format(((endtestc45 - endtestnb) / 1000d) % 60)+" sec		| "+s);
        }
}
else {
        if(((endtestc45 - endtestnb) / 1000)>=60) {
      	  textArea.append(" "+new DecimalFormat("#0.000").format(((endtestnb - starttestnb) / 1000d) % 60)+" sec		| "+
	        		  (((endtestc45 - endtestnb) / 1000)/60)+" min	"+new DecimalFormat("#0.000").format(((endtestc45 - endtestnb) / 1000d) % 60)+" sec	| "+s);
	               }
	  else {
		  textArea.append(" "+new DecimalFormat("#0.000").format(((endtestnb - starttestnb) / 1000d) % 60)+" sec		| "+
      		  new DecimalFormat("#0.000").format(((endtestc45 - endtestnb) / 1000d) % 60)+" sec		| "+s);
             
	  } 
}
textArea.append("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n");
////////////////////END TIME///////////////////   
 
 }
    ////////////////////////////////////////////
	/////////////////////////////////////////////
 static void TableAttributes()
	{
		 lblrelation.setText(data_arff.relationName());
         lblinstances.setText(data_arff.numInstances()+"");
         lblattribute.setText(data_arff.numAttributes()+"");
         lblsumweight.setText(new DecimalFormat("##.##").format(data_arff.sumOfWeights())+"");
         
        
      		               
      		modelatt = new DefaultTableModel(new Object[][] {null},new String[] {"N°", "Attribute",})
      		{
      		    public boolean isCellEditable(int rowIndex, int mColIndex) {
      		        return false;
      		    }
      		};//pour ne pas pouvoir selectionner une cellule
      		 
      		attrtable=new JTable(modelatt);
      		attrtable.setForeground(Color.WHITE);
          	attrtable.setBackground(new Color(49,79,79));
          	attrtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      		 modelatt.removeRow(0);//il y'a toujours une ligne en plus au debut
        
      		 comboBox_2.removeAllItems();
      		 data_arff.setClassIndex(data_arff.numAttributes() - 1);// preciser l'att classe (obligatoire)
      		//visu2combobox.addItem("No class");

     		visucombobox.removeAllItems();
			visu2combobox.removeAllItems();
            for ( int  i = 0; i <= data_arff.numAttributes() - 1; i++) {
 
           //  visucombobox.setModel(new DefaultComboBoxModel(new String[] {data_arff.attribute(i).name()}));
           visucombobox.addItem(data_arff.attribute(i).name());
           visu2combobox.addItem(data_arff.attribute(i).name());
          if(data_arff.attribute(i).isNominal()) comboBox_2.addItem(data_arff.attribute(i).name());
           modelatt.addRow(new Object[] {(i+1),data_arff.attribute(i).name()});
           } 
         class_att_txtfield.setText(data_arff.classAttribute().name());class_att_txtfield.setEditable(false); 
         visucombobox.setSelectedIndex(0);
         visu2combobox.setSelectedIndex(data_arff.numAttributes() - 1);
       //  graphDifusion();
         comboBox_2.setSelectedIndex(comboBox_2.getModel().getSize()-1);

        data_arff.deleteWithMissingClass();//pour supprimer les instances ou il manque la valeur de l'attribut classe.
        
       
         
       
        scrollP_attlist.setViewportView(attrtable);
        attrtable.setRowSelectionInterval(0,0);
        CreatSelectedAtt(attrtable,data_arff);
        /************ATTRIBUTE_SELECTED***********/
        
        attrtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			CreatSelectedAtt(attrtable,data_arff);	
			}
		}); 
         /***************************************/

	}
 static void CreatSelectedAtt(JTable tabl,Instances inst)
	{
		if(tabl.getSelectedRow() != -1) { 
		
			lblNname.setText(inst.attribute(tabl.getSelectedRow()).name());
			lblNmissing.setText(inst.attributeStats(tabl.getSelectedRow()).missingCount+"");
			lblNdistinct.setText(inst.attributeStats(tabl.getSelectedRow()).distinctCount+"");
			 if(inst.attribute(tabl.getSelectedRow()).isNominal()) {
				 lblNtype.setText("Nominal");
				    DefaultTableModel model = new DefaultTableModel(new Object[][] {null},new String[] {"N°", "Label","Count","Weight",})
	          	 {
	          	    public boolean isCellEditable(int rowIndex, int mColIndex) {
	          	        return false;
	          	    }
	          	};
	          	NominalTab=new JTable(model);
	          	NominalTab.setForeground(Color.WHITE);
	          	NominalTab.setBackground(new Color(0,128,128));
	          	NominalTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	          	model.removeRow(0);
	       
	          	for(int k=0;k<=inst.attribute(tabl.getSelectedRow()).numValues()-1;k++)
	          	{
	          		model.addRow(new Object[]{(k+1),inst.attribute(tabl.getSelectedRow()).value(k),inst.attributeStats(tabl.getSelectedRow()).nominalCounts[k],new DecimalFormat("##.##").format(inst.attributeStats(tabl.getSelectedRow()).nominalWeights[k])});
	          	}
	          	
	          	scrollPselectatt.setViewportView(NominalTab); 
			 }
			 

				 
             else {
          	   if(inst.attribute(tabl.getSelectedRow()).isNumeric()) {
          		lblNtype.setText("Numeric");
          		
          	 
			DefaultTableModel model = new DefaultTableModel(new Object[][] {null},new String[] {"Statistic", "Value",})
	          	 {
	          	    public boolean isCellEditable(int rowIndex, int mColIndex) {
	          	        return false;
	          	    }
	          	};
	          	NumericTab=new JTable(model);
	          	NumericTab.setForeground(Color.WHITE);
	          	NumericTab.setBackground(new Color(0,128,128));
	          	NumericTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	          	model.removeRow(0);
                model.addRow(new Object[]{"Min", new DecimalFormat("##.##").format(inst.attributeStats(tabl.getSelectedRow()).numericStats.min)});
              model.addRow(new Object[]{"Max", new DecimalFormat("##.##").format(inst.attributeStats(tabl.getSelectedRow()).numericStats.max)});
            model.addRow(new Object[]{"Mean", new DecimalFormat("##.##").format(inst.attributeStats(tabl.getSelectedRow()).numericStats.mean)});
            model.addRow(new Object[]{"StdDev", new DecimalFormat("##.##").format(inst.attributeStats(tabl.getSelectedRow()).numericStats.stdDev)});
         
            scrollPselectatt.setViewportView(NumericTab);
          	   }
          	   else {
          		   if(inst.attribute(tabl.getSelectedRow()).isString()) {
          			 scrollPselectatt.setVisible(false); 
          			lblNtype.setText("String");} 
          		   else {
          			   if(inst.attribute(tabl.getSelectedRow()).isDate())
          			   {
          				lblNtype.setText("Date");
          			    JLabel lblDate=new JLabel();
          			    lblDate.setText("Format:  "+inst.attribute(tabl.getSelectedRow()).getDateFormat());
          			  scrollPselectatt.setViewportView(lblDate);
          			    //display the format date attribut
          			  
          			   }
          			   else {
          				 scrollPselectatt.setVisible(false);
          				lblNtype.setText("Relational");
          			   }
          		   }
          	   }
             }// le type
			 lblNunique.setText(inst.attributeStats(tabl.getSelectedRow()).uniqueCount+"");
			
			
			
	    }

	}

	public void ConvertCSVToArff(File f1,File f2) 
	{
		CSVLoader loader = new CSVLoader();
		try {
			loader.setSource(f1);
			Instances data_csv = loader.getDataSet();
	         ArffSaver saver = new ArffSaver();
			  saver.setInstances(data_csv);
	          saver.setFile(f2);//sauvegarder le fichier converir en cette emplacement
			  saver.writeBatch();
			  datapath=f2.getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void ConvertArffToCSV(File f1,File f2) 
	{
		ArffLoader loader = new ArffLoader();
		try {
			loader.setSource(f1);
			 data_arff = loader.getDataSet();
	         CSVSaver saver = new CSVSaver();
			  saver.setInstances(data_arff);
	          saver.setFile(f2);//sauvegarder le fichier converir en cette emplacement
			  saver.writeBatch();
			  datapath=f2.getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArffReader LectureArffFile(String s) 
	{
		 BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(s));
			ArffReader arff=new ArffReader(reader);
			return arff;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null; 
		
	}
	///////////////////////////Update///////////////////////////////
	static void graphDifusion(){
		 if(data_arff.attribute(visucombobox.getSelectedIndex()).isNumeric())
         {
       	    Histogram h=new Histogram();
       		ViewPanel.removeAll();
       		ViewPanel.add(h.chPanel);
       		ViewPanel.validate();
         }
         else{
             if(data_arff.attribute(visucombobox.getSelectedIndex()).isNominal())
             { BarChart b=new BarChart();
         	  ViewPanel.removeAll();
           	  ViewPanel.add(b.chPanel);
       	      ViewPanel.validate();}
         }
	}
	  ////////////////////////////////////////////
	public void ApprochCV(Classifier cls,Instances inst, String pourcentage,int classattr) {
		   boolean stop=false;
		   double[] j;
		   try {
	       Evaluation eval=null;
	       InfoDataset(inst);
	       textArea.append(" Test mode:    "+pourcentage+"-fold  cross-validation\n\n");
	       textArea.append(" Attributes Selection");
	       if(rdbtnNewRadioButton_1.isSelected()) textArea.append(" by using Naive Bayes classifier . . . \n");
	       if(rdbtnNewRadioButton_2.isSelected()) textArea.append(" by using C.45 classifier . . . \n");
	       try {
	    	   long start = System.currentTimeMillis();
		   while(stop==false)
		   {   			   
   	          cls.buildClassifier(inst);     
			  eval = new Evaluation(inst);
			  eval.crossValidateModel(cls,inst,Integer.parseInt(pourcentage), new Random(1));
	          j=generateSV(cls,inst,indice,pourcentage,classattr);
	          if(j[1]>eval.correct()) 
	          {
	        	  textArea.append(" The attribute :  "+inst.attribute((int)j[0]).name()+"  is removed\n");
	        	  inst.deleteAttributeAt((int)j[0]);
	        
	          classattr--;        
	          }
	          else stop=true;
		   }
		   long end = System.currentTimeMillis();
		   if(((end - start) / 1000)>=3600) textArea.append("\n Time taken to select the attributes : "+(((end - start) / 1000 )/3600)+" hours  "+
					((((end - start) / 1000) % 3600)/60)+ " minutes  "+new DecimalFormat("#0.000").format((((end - start) / 1000d) % 3600) % 60)+" seconds\n\n");
			else if(((end - start) / 1000)>=60) textArea.append("\n Time taken to select the attributes : "+ (((end - start) / 1000)/60)+ " minutes  "+new DecimalFormat("#0.000").format(((end - start) / 1000d) % 60)+" seconds\n\n");       
			else textArea.append("\n Time taken to select the attributes : "+new DecimalFormat("#0.000").format((end - start) / 1000d)+" seconds\n\n");
		
		   textArea.append(" Remaining attributes:\n");
		   for(int a=0;a<inst.numAttributes();a++)
		   {
			   textArea.append(" "+( a+1)+"-"+inst.attribute(a).name()+"\n");  
		   }
		  
	       }catch(NumberFormatException e) {
           	JOptionPane.showMessageDialog(null,"Check the fold","Error",JOptionPane.ERROR_MESSAGE);}
				}catch (Exception e) {
				// TODO Auto-generated catch block
					btnNewButton_1.setText("Launch");textArea.append(e.toString());e.printStackTrace();
				}   
}
	public void ApprochSplit(Classifier cls,Instances inst, String pourcentage,int classattr) {
		   boolean stop=false;
		   double[] j;
		   InfoDataset(inst);
		   textArea.append(" Test mode:    split "+pourcentage+" %  train, remainder test\n" );
              try {
		   int trainSize = (int) (inst.numInstances() * Double.parseDouble(pourcentage)/100);
		   if(trainSize < inst.numInstances() && trainSize >=0 )
		   {
			   textArea.append(" train size:  "+trainSize);
	           int testSize = inst.numInstances() - trainSize;
	           textArea.append("	test size:  "+testSize+"\n\n");
	       Instances train = new Instances(inst, 0, trainSize);
	       train.setClassIndex(classattr);   
	       Instances test = new Instances(inst, trainSize, testSize);
	       test.setClassIndex(classattr);
	       Evaluation eval=null;
	       textArea.append(" Attributes Selection");
	       if(rdbtnNewRadioButton_1.isSelected()) textArea.append(" by using Naive Bayes classifier . . . \n");
	       if(rdbtnNewRadioButton_2.isSelected()) textArea.append(" by using C.45 classifier . . . \n");
	       try {
	    	   long start = System.currentTimeMillis();
	       while(stop==false)
		   { 			   
	          cls.buildClassifier(train);     
			  eval = new Evaluation(train);
			  eval.evaluateModel(cls, test);
	          j=generateSV(cls,inst,indice,pourcentage,classattr);
	          
	          if(j[1]>eval.correct()) 
	          {
	        	  textArea.append(" The attribute :  "+inst.attribute((int)j[0]).name()+"  is removed\n");
	        	  inst.deleteAttributeAt((int)j[0]);
		          classattr--; 
		          train.deleteAttributeAt((int)j[0]); 
		          test.deleteAttributeAt((int)j[0]); 
		          
	          }
	          else stop=true;
	          
		   }
		  		   
		   long end = System.currentTimeMillis();
		   if(((end - start) / 1000)>=3600) textArea.append("\n Time taken to select the attributes : "+(((end - start) / 1000 )/3600)+" hours  "+
					((((end - start) / 1000) % 3600)/60)+ " minutes  "+new DecimalFormat("#0.000").format((((end - start) / 1000d) % 3600) % 60)+" seconds\n\n");
			else if(((end - start) / 1000)>=60) textArea.append("\n Time taken to select the attributes : "+ (((end - start) / 1000)/60)+ " minutes  "+new DecimalFormat("#0.000").format(((end - start) / 1000d) % 60)+" seconds\n\n");       
			else textArea.append("\n Time taken to select the attributes : "+new DecimalFormat("#0.000").format((end - start) / 1000d)+" seconds\n\n");
		
		   textArea.append(" Remaining attributes:\n");
		   for(int a=0;a<inst.numAttributes();a++)
		   {
			   textArea.append(" "+( a+1)+"-"+inst.attribute(a).name()+"\n");  
		   }
	         
 }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}}
		   else JOptionPane.showMessageDialog(null,"Check the percentage split","Error",JOptionPane.ERROR_MESSAGE);
 }catch(NumberFormatException | IllegalFormatException e) {
	JOptionPane.showMessageDialog(null,"Check the percentage split","Error",JOptionPane.ERROR_MESSAGE);} 
}
	
 
	///////////////////////Solutions voisines////////////////////////
    public double [] generateSV(Classifier cls,Instances d,int indice,String pourcentage,int classattr) throws Exception{
		double[] doub= new double[d.numAttributes()-1];
		Instances d1;
		
		for(int i=0;i<d.numAttributes();i++){
			if(i!=classattr){
			d1=new Instances(d);
			d1.setClassIndex(classattr);
			d1.deleteAttributeAt(i);
			if(Splitrb.isSelected())
			{doub[i]=ApprochSplitSV(cls,d1,pourcentage);}
			else{doub[i]=ApprochCV_SV(cls,d1,pourcentage);}
			
			}
		}
		//OptionalDouble max = Arrays.stream(doub).max();
		double max=doub[0];
		indice=0;
		for(int i=1;i<d.numAttributes()-1;i++){
			if(doub[i]>max) {max=doub[i]; indice=i;}
		}
		double [] indmax=new double[2];
		indmax[0]=indice;
		indmax[1]=max;
		
		return indmax;
	}
	//////////////////////////////////////////////////////////////
	public double ApprochSplitSV(Classifier cls,Instances inst, String pourcentage) throws Exception {
	  
		int trainSize = (int) (inst.numInstances() * Integer.parseInt(pourcentage)/100);
        int testSize = inst.numInstances() - trainSize;
      Instances train = new Instances(inst, 0, trainSize);
     train.setClassIndex(inst.classIndex());   
     Instances test = new Instances(inst, trainSize, testSize);
     test.setClassIndex(inst.classIndex());
     
		    cls.buildClassifier(train);     
		    Evaluation evalnaive = new Evaluation(train);
		    
			evalnaive.evaluateModel(cls, test);
           return evalnaive.correct();

	}

	public double ApprochCV_SV(Classifier cls,Instances inst, String pourcentage) throws Exception {	                     
	cls.buildClassifier(inst);			   				
	Evaluation evala = new Evaluation(inst);
    evala.crossValidateModel(cls,inst,Integer.parseInt(pourcentage), new Random(1));
	return evala.correct();  										                   
}
}
