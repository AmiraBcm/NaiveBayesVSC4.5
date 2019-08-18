package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.Cursor;

public class Help extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help frame = new Help();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Help() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Help.class.getResource("/window/vue/Help.png")));
		setTitle("Help");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setVisible(true);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.add(scrollPane);
		
		JTextArea txtrhelp = new JTextArea();
		txtrhelp.setWrapStyleWord(true);
		txtrhelp.setToolTipText("");
		txtrhelp.setEditable(false);
		txtrhelp.setTabSize(4);
		txtrhelp.setText("Welcome to our graduation application ALPA \u201CAcademic Licence Project Application\u201D!\r\n\r\n With this application you can analyze and modify your data, classify it by using Machine Learning classifiers (Na\u00EFve Bayes and the C4.5) as well as visualizing it in different graphs.\r\n The application is presented as follow:\r\n\t1.\tMenu Bar\r\n\t2.\tPreprocess tab\r\n\t3.\tClassify tab\r\n\t4.\tVisualize tab\r\n The menu bar includes three menus:\r\n\t1.1.\tFile menu:  which contains three items:\r\n\t\t1.1.1.\tOpen (ctrl +O): for opening .arff and .csv  files.\r\n\t\t1.1.2.\tSave (ctrl +S):  for saving files after modifications.\r\n\t\t1.1.3.\tExit (ctrl +E): to leave the application.\r\n\t1.2.\tEdit menu: This menu allows you to modify the information contained in your data file. It contains three items:\r\n\t\t1.2.1.\tRename (ctrl +R): to change the relation name or the attribute names. To confirm click on OK.\r\n\t\t1.2.2.\tDelete (ctrl +D): to delete attributes or instances. Down below are the instructions to follow in order to:\r\n\t\t\ta.\tdelete an attribute :\r\n\t\t\ti.\tClick on the \u201CDelete attribute \u201C button (on the left).\r\n\t\t\tii.\tChoose the attribute you want to remove.\r\n\t\t\tiii.\tClick the \u201COK\u201D button to confirm, or exit the delete frame on the contrast.\r\n\t\t\tThe removal operation can always be canceled by clicking on the \u201CCancel\u201D button of the Edit frame. \r\n\t\t\tb.\tdelete an instance :\r\n\t\t\ti.\tSelect the desired instance from the table of instances of the Edit frame.\r\n\t\t\tii.\tClick on the \u201CDelete instance\u201D button.\r\n\t\t\tiii.\tConfirm by clicking on the \u201COK\u201D button, or the \u201CCancel\u201D button in case you want to cancel the operation.\r\n\r\n\t\t1.2.3.\t Add (ctrl +A): to add any instances you desire. To add an instance follow the coming instructions: \r\n\t\t\ti.\tClick on the \u201CAdd instance\u201D button (on the left).\r\n\t\t\tii.\tModify the values of the new instance (this one will be placed at the end).\r\n\t\t\tPS: You should take in consideration in case of a numeric attribute, entering alphabetic characters isn\u2019t allowed and in that case it will automatically be changed by 0.\r\n\t\t\tiii.\tValidate the created instance by clicking on the \u201CValidate\u201D button (on the left).\r\n\t\t\t\t\t  However you can cancel all the instances added by clicking on the\u201D Cancel\u201D button.\t\r\n\t\t1.2.4.\tView (ctrl +V): this last item visualizes the opened file, by displaying the relation name, the attributes names and the different instances.\r\n    1.3 Help menu: which displays this current text.\r\n\t\r\n After the menu bar comes the three following tabs:\r\n\t1 Preprocess tab:\r\n\tThis tab contains all the statics about the opened file and it is presented in three different panels:\r\n\t\t1.1\tThe \u201CCurrent Relation\u201D panel: shows the relation name, the number of attributes (including the target attribute), the number of instances and its sum of weight.\r\n\t\t1.2\tThe \u201CAttributes list\u201D panel: lists the attributes and indicates the class attribute.\r\n\t\t1.3\tThe \u201CSelected Attribute\u201D panel: is divided into two parts.\r\n\t\tOnce an attribute is selected, the first part shows its name, its type (numeric or nominal), and three statistics: missing, distinct and unique which represent respectively the number of the missing, distinct and unique values of the attribute in the data set.\r\n\t\tThe second part depends on the type of the attribute; it shows the attributes value table in case of a nominal attribute(label, count and Weight) and the statistical information table in case of a numeric attribute(the minimum value, the maximum value, the mean and the standard deviation of the attribute values).\r\n\t\r\n\t2 Classify tab:\r\n\tThis tab allows you to classify the open file\u2019s data; it is divided into two parts:\r\n\tThe first one concerns the classification criteria and the second part, the results obtained from the classification.\r\n\tThe first part of the \u201CClassify\u201D tab contains four panels:\r\n\t\t2.1\t The \u201CTest option\u201D panel: this panel contains two testing methods: the cross validation and the percentage split and two text fields to specify the number of the folds and the percentage for them respectively.\r\n\t\t2.2\t The \u201CAttributes Selection\u201D panel : this panel allows you to select a set of attributes that reaches a higher classification accuracy comparing to the initial attributes set(the complete set) .\r\n\t\t2.3\t The \u201CClassifier\u201D panel:  this panel allows you to choose a classifier among Na\u00EFve Bayes and C4.5, or to compare their performances by choosing (Naive Bayes VS C4.5).\r\n\t\t2.4\t   The \u201CClass Attribute\u201D panel: this panel allows you to specify the target attribute (class label).\r\n\tAnd finally the \u201CStart\u201D button to start the classification.\r\n\t\r\n\tThe results including: the model built, the evaluation measures, the time taken to classify data  and many other details are displayed on the text area.\r\n\tIn order to classify your data, these are the steps to follow:\r\n\t\t(1)\tChoose the testing method (cross validation or percentage split).\r\n\t\t(2)\tIf you want to make a selection on the attributes set, you should choose one    of the two given   classifiers (na\u00EFve bayes or c4.5).\r\n\t\t\tPS: the selection algorithm uses a classifier as well as the information of the test options section, that\u2019s why both classifiers are given for the selection task.\r\n\t\t\tClick on the \u201CLaunch\u201D button to start the process. The text area shows the results obtained (the remaining /removed attributes, the time taken...etc).\r\n\t\t\tOnce the selection is done, the button \u201CSave as file\u201D allows you to save a file preserving the relation name of the opened file, the selected attributes and the instances (keeping only the values of the selected attributes and the target one). \r\n\r\n\t\t\tIf you do not want a selection on the attributes set, choose the \u201CNone\u201D option.\r\n\r\n\t\t(3)\tChoose a classifier from the \u201CClassifier\u201D panel in order to classify your data.\r\n\t\t(4)\tClick on the \u201CStart\u201D button to start the classification.\r\n\t\t\r\n\tThe results will be displayed on the text area.\r\n\t\r\n\t3 Visualize tab:\r\n\tThis last tab displays the open file\u2019s data in graphs for a better analysis. The Visualize tab is organized in three panels:\r\n\t3.1\tThe \u201CAxis variants\u201D panel: it contains two combo boxes to select  x-axis and y-axis values as well as it contains the \u201CUpdate\u201D button.\r\n\t3.2\tThe \u201CGraphics\u201D panel: it contains all the possible graphical representations \r\n\t(Histogram : for a numeric domain; i.e  x-axis, Bar Chart: for a nominal domain, and Pie chart: for the same nominal attribute selected as a domain and a range).\r\n\t3.3\tAnd finally, the last panel that displays the selected graph.\r\n\tTo display a certain graph follow these steps:\r\n\ti.\tChoose from the \u201CAxis variants\u201D panel the domain and range of your graph.\r\n\tii.\tSelect the graphic form from the \u201CGraphics\u201D panel.\r\n\tiii.\tClick on the \u201CDisplay\u201D button and watch your graph being displayed on the main panel.\r\n\tThings to take in consideration:\r\n\t\u2022\tOnce the domain and range are specified, only the possible corresponding graphs are enabled.\r\n\t\u2022\tIn case of a numeric domain and a nominal range, an interval table will be displayed on a frame as an addition to give each number on the graph\u2019s domain its corresponding interval with its value.\r\n\t\u2022\tThe icon button in the \u201CGraphs\u201D panel allows you to display your graph on an independent frame.\r\n\t\u2022\tAfter modifying your data(Edit->{Delete,Add}), you must click on the \u201CUpdate\u201D button in the \u201CAxis variants\u201D panel to update the information represented in your graph.\r\n  \r\n  \r\n                  *****************************End**********************************\r\n");
		scrollPane.setViewportView(txtrhelp);
	}

}
