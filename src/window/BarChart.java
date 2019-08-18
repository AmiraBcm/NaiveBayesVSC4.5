/*
 * Creating & filling Bar chart with data 
 * Case of :
 * *nominal-nominal
 * *nominal-numeric 
*/
package window;

import java.awt.Color;
import java.awt.Toolkit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class BarChart {
	private  String[] s=null;
    public ChartPanel chPanel;
    private int l,j,k,i;
    public BarChart(){
    	
    	/*******************Bar Chart's data*******************/
    	 DefaultCategoryDataset defaultcategorydataset=new DefaultCategoryDataset();
    	 
    	 /****The case of selecting a nominal attribute for both domain and range****/
    	 if(Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).isNominal())
    	 {
    		 /////////////////////nominal**nominal///////////////////////
    		 //case 1.1: the selected attributes are different
		     if(Gui.visucombobox.getSelectedIndex()!=Gui.visu2combobox.getSelectedIndex())
			 {int val=Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).numValues()*
			 Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).numValues();
			 
		  	 String[][] att=new String[val][2]; //att contains all the possible combinations of attributes values that are selected
		  	 
		  	    j=0;
				for(k=0;k<=Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).numValues()-1;k++)
			  	{ 
					    for(i=0;i<=Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).numValues()-1;i++){			    	
					       att[j][0]=Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).value(k);
				           att[j][1]=Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).value(i);
				           j++;}
			  	}
				
			   int[] occ=new int[val]; //occ contains the occurrences of each combination of att
			   for(i=0; i<val; i++) {occ[i]=0;}
			   
			   for(i=0;i<Gui.data_arff.numInstances();i++)
				{      s=Gui.data_arff.instance(i).toString().split(",");
				       l=0;
				       while(l<att.length) 
				  	   {if(s[Gui.visucombobox.getSelectedIndex()].compareTo(att[l][0])==0 &&
				  	   s[Gui.visu2combobox.getSelectedIndex()].compareTo(att[l][1])==0)
			           {occ[l]=occ[l]+1;l=att.length;}
				       else l++;
				  	   }    
				}
			  //Filling the dataset 
			  for(l=0; l<att.length; l++)  defaultcategorydataset.addValue(occ[l],att[l][1],att[l][0]);
			 }
    		//case 1.2: the selected attributes are the same
    		 else{
    			 for(l=0; l<Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).numValues(); l++) {
    		     defaultcategorydataset.addValue(Gui.data_arff.attributeStats(Gui.visucombobox.getSelectedIndex()).nominalCounts[l],"",
    		    		                         Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).value(l));
    			 }
    		 }
	
	        //Filling the stacked bar chart 
		    JFreeChart chart = ChartFactory.createStackedBarChart(Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).name(),
		    		                                         "", "",defaultcategorydataset, PlotOrientation.VERTICAL, true, false, false);   
		    chart.setBackgroundPaint(Color.white); 
		    if(Gui.visucombobox.getSelectedIndex()==Gui.visu2combobox.getSelectedIndex())
		    chart.removeLegend();
	        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();   
	        categoryplot.setBackgroundPaint(Color.lightGray);   
	        categoryplot.setRangeGridlinePaint(Color.white);

	        StackedBarRenderer stackedbarrenderer = (StackedBarRenderer)categoryplot.getRenderer();   
	        stackedbarrenderer.setDrawBarOutline(true); 
	        
          /*  stackedbarrenderer.setPositiveItemLabelPosition(new ItemLabelPosition(
	            ItemLabelAnchor.OUTSIDE12,
	            TextAnchor.HALF_ASCENT_CENTER));*/
   	        
	        //Nombre total des chaque colonne 
            int total;
            for(i=0;i<defaultcategorydataset.getColumnCount();i++)
            {   total=0;
            	for(j=0;j<defaultcategorydataset.getRowCount();j++)
            {
            	total=total+defaultcategorydataset.getValue(j, i).intValue();
            }
           
            }
            
			chPanel = new ChartPanel(chart);
			chPanel.setMouseWheelEnabled(true);
			Gui.frame=new ChartFrame("Stacked bar chart",chart);
			
		 }
    	 /****The case of selecting a nominal attribute as a domain and a numeric attribute as a range****/
		 else
		 {
			 if(Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).isNumeric())
        	 {
				/////////////////////nominal**numeric///////////////////////
				//Filling the dataset 
				for(l=0;l<=Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).numValues()-1;l++)	
					defaultcategorydataset.addValue(Gui.data_arff.attributeStats(Gui.visucombobox.getSelectedIndex()).nominalCounts[l],
							                                  "",Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).value(l));	
				
				//Filling the stacked bar chart 
				JFreeChart chart = ChartFactory.createStackedBarChart(
						Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).name(),
						"",
						"Value",
						defaultcategorydataset, 
						PlotOrientation.VERTICAL,
						false,
						false,
						false);   
				
    	    	chart.setBackgroundPaint(Color.white);   
    	        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();   
	    	    categoryplot.setBackgroundPaint(Color.lightGray);   
	    	    categoryplot.setRangeGridlinePaint(Color.white);   
	    	    StackedBarRenderer stackedbarrenderer = (StackedBarRenderer)categoryplot.getRenderer();   
	    	    stackedbarrenderer.setDrawBarOutline(false);     
	    	    stackedbarrenderer.setBaseItemLabelsVisible(true);
	    	    stackedbarrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	   	    	stackedbarrenderer.setBasePositiveItemLabelPosition(
	   	    		new ItemLabelPosition(
		    	     ItemLabelAnchor.OUTSIDE12,
		    	     TextAnchor.HALF_ASCENT_CENTER));
	   	    	
	    	    chPanel = new ChartPanel(chart);
	    	    chPanel.setMouseWheelEnabled(true);
	    	    Gui.frame=new ChartFrame("Bar chart",chart);
	    	    
        	 }
		 }
    	 Gui.frame.setSize(550, 400);
    	 Gui.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(BarChart.class.getResource("/window/vue/BarChart.png")));
    }
}
