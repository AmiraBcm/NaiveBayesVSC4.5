/*
	 * Creating & filling Pie chart with data 

	 * Case of :
	 * *nominal-numeric 
	 * *nominal-nominal (same nominal attribute)
*/

package window;

import java.awt.Color;

import java.awt.Toolkit;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class Pie extends Thread{
	
	private int l=0;
	public ChartPanel chPanel;
	private DefaultPieDataset datasetP=new DefaultPieDataset();
	
	public Pie(){
	
		//Filling the Pie chart's data
		for(l=0;l<=Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).numValues()-1;l++)	
			datasetP.setValue(
			Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).value(l),
			Gui.data_arff.attributeStats(Gui.visucombobox.getSelectedIndex()).nominalCounts[l]);	
		//Filling the Pie chart with data
			JFreeChart chart = ChartFactory.createPieChart(
					           Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).name(),
					           datasetP,false,true,false); 
			
		    chart.setBackgroundPaint(Color.white);
		    
			PiePlot p=(PiePlot) chart.getPlot();
			p.setBackgroundPaint(Color.WHITE);
			p.setCircular(true);
			p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {2}",new DecimalFormat("#0.00"),new DecimalFormat("#0.00%")));
		//	p.setLabelGenerator(null);
			chPanel = new ChartPanel(chart);
    	    chPanel.setMouseWheelEnabled(true);
    	  
    	    Gui.frame=new ChartFrame("Pie chart",chart);
    	    Gui.frame.setSize(550, 400);
    	    Gui.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Pie.class.getResource("/window/vue/Pie.png")));
    	   
	}
}
