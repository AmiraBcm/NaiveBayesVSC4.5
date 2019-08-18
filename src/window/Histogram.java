/*
 * Creating & filling Histogram chart with data 

 * Case of :
 * *numeric**numeric
 * *numeric**nominal
*/
package window;

import java.awt.Color;
import java.awt.Toolkit;
import java.text.DecimalFormat;

import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.ui.TextAnchor;   
   
public class Histogram   
{   
	private  String[] s=null;
	private int borne;
    public ChartPanel chPanel;
    private HistogramDataset dataset = new HistogramDataset();
    private Interval interval;
    public Histogram()
    {  
    	    /****The case of selecting a numeric attribute for both domain and range****/
	    	if(Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).isNumeric())
	    	{   
                     //Filling the data set
	    		     intervalMatrix();
	    		     
		    		 //Filling the histogram with data
		    	       JFreeChart chart = ChartFactory.createHistogram(
		    	        Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).name(),
		    			null,
		    			null,
		    			dataset,
		    			PlotOrientation.VERTICAL,
		    			false,
		    			false,
		    			false
		    			);
		    			XYPlot plot = (XYPlot) chart.getPlot();
		    			XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
		    			renderer.setDrawBarOutline(false);
		    			renderer.setBaseItemLabelsVisible(true);
		    			
		    			/////getting ride of the Y axis' values////////////
		    			ValueAxis range = plot.getRangeAxis();
		    			range.setVisible(false);
		    		
		    			renderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		    	        renderer.setBaseItemLabelsVisible(true);
		    	    	renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
		    		            ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		    			chPanel = new ChartPanel(chart);
		    			chPanel.setMouseWheelEnabled(true);
		    			Gui.frame=new ChartFrame("Hitogram",chart);
		    			
	    	 }
	    	/****The case of selecting a numeric attribute as a domain and a nominal attribute as a range****/
	    	else
			 {  /////////////////////numeric**nominal///////////////////////
	    		 if(Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).isNominal())
            	 {
	    	   		 
	    			 DefaultCategoryDataset defaultcategorydataset=new DefaultCategoryDataset();
	    		  
	    			   /*********************************************************************************/
	    			   double  intervalle [][] = intervalMatrix(); 
	    			   
	    			   //Filling the interval matrix with the occurrences of each interval
	    			   int val=intervalle.length*Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).numValues();
    				 
	    			   borne=intervalle.length;
    				   double[][] att=new double[val][3];
    				   int j=0;
    				   for(int i=0;i<borne;i++){
    					   for(int k=0;k<Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).numValues();k++){
    						 att[j][0]=intervalle[i][0];
    						 att[j][1]=intervalle[i][1];
    						 att[j][2]=0;
    						 j++;
    					   }
    				   }
    				 
	    				   //Filling attvaleur[] the vector that will contain the nominal attribute values
	    				   int i=0,k;
	    				   String[] attvaleur=new String[val];
	    				   while(i<val){
	    					   k=0;
	    					   while(k<Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).numValues()){
	    						 attvaleur[i]= Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).value(k);
	    						 i++;
	    						 k++;
	    					   }
	    				   }
	    			
	    				   //Calculating the occurrences 
	    				   
	    				   int l=0;
	 	    		       for(i=0;i<Gui.data_arff.numInstances();i++)
	 	    				{      s=Gui.data_arff.instance(i).toString().split(",");
	 	    				       l=0;
	 	    				       while(l<val) 
	 	    				  	   {
	 	    				    	 
	 	    				    	if(Long.valueOf(s[Gui.visucombobox.getSelectedIndex()])>=att[l][0] && Long.valueOf(s[Gui.visucombobox.getSelectedIndex()])<att[l][1] && s[Gui.visu2combobox.getSelectedIndex()].compareTo(attvaleur[l])==0)
	 	    			           {att[l][2]=att[l][2]+1;l=att.length;}
	 	    				       else l++;
	 	    
	 	    				  	   }	 	    				    
	 	    				}
	 	    		    
	 	    		     //Calculating the occurrences in case there are values equal to the last interval sup born 
	 	    		     int index=val-Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).numValues()+1;
	 	    		     for(i=0;i<Gui.data_arff.numInstances();i++)
	 	    				{   
	 	    		    	   s=Gui.data_arff.instance(i).toString().split(",");
	 	    		    	 
	 	    		    	   l=index;
	 	    		    	   while(l<val)
    				    	   if(Long.valueOf(s[Gui.visucombobox.getSelectedIndex()])==att[l][1] && s[Gui.visu2combobox.getSelectedIndex()].compareTo(attvaleur[l])==0)
 	    			           {att[l][2]=att[l][2]+1;l=att.length;}
 	    				       else l++;
 	    				       
 	    
	 	    				} 
	    				  
	 	    		    	//Displaying the Interval frame
	 	    		        
	 	    		        interval.setVisible(true);
                           //Filling the histogram with data
	    				   j=1;
	    				   for(i=0; i<att.length; i++)  
	    					   {defaultcategorydataset.addValue(att[i][2],attvaleur[i],""+j+"");
	    					     if((i+1) % Gui.data_arff.attribute(Gui.visu2combobox.getSelectedIndex()).numValues()==0)
	    					     {j++;}
	    					   }
	                        JFreeChart chart = ChartFactory.createStackedBarChart(
	                        		              Gui.data_arff.attribute(Gui.visucombobox.getSelectedIndex()).name(),"",
	                        		             "Value",defaultcategorydataset, PlotOrientation.VERTICAL, true, false, false);   
	    				    chart.setBackgroundPaint(Color.white); 
	    				    
	    			        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();   
	    			        categoryplot.setBackgroundPaint(Color.lightGray);   
	    			        categoryplot.setRangeGridlinePaint(Color.white);
	    			    	categoryplot.getDomainAxis().setTickLabelsVisible(true);
	    			    	categoryplot.getDomainAxis().setCategoryMargin(0);

	    			   
	    			        StackedBarRenderer stackedbarrenderer = (StackedBarRenderer)categoryplot.getRenderer();   
	    			        stackedbarrenderer.setDrawBarOutline(false); 
	    			        stackedbarrenderer.setShadowVisible(true);
	    			      
	    					chPanel = new ChartPanel(chart);
	    					chPanel.setMouseWheelEnabled(true); 
	    					Gui.frame=new ChartFrame("Stacked histogram",chart);
	    					
            	 }
	    		
			 }
	    	 Gui.frame.setSize(550, 400);
    		 Gui.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Histogram.class.getResource("/window/vue/BarChart.png"))); 
    	
    	 
}   
 //Method that fills the histogram data set, calculates bins number and returns class intervals
    public double [][] intervalMatrix()
    {
		/////////////////////numeric**numeric///////////////////////
	    borne=Gui.data_arff.numInstances();
	    //vector[] contains all the numerical values of the selected attribute, duplications included
	    double []vector=new double[borne]; 
        
	    for(int i=0;i<Gui.data_arff.numInstances();i++)
		{ 
		s=Gui.data_arff.instance(i).toString().split(",");
		if(Gui.data_arff.instance(i)!=null)
		vector[i]=Double.parseDouble(s[Gui.visucombobox.getSelectedIndex()]);
	    }
	    /****Histogram data set****/
    	 
    	 dataset.setType(HistogramType.FREQUENCY);
    	 
    	 /***specification of the number of bins according to the number of values contained in vector[]***/
    	 int bin;
    	 if(Gui.data_arff.numInstances()!=0)
		 {
	    	 if(Gui.data_arff.numInstances()>30)
	    	  bin=(int)(5*Math.log10(Gui.data_arff.numInstances()));
	    	 else
	    	  bin=(int)(Math.sqrt(Gui.data_arff.numInstances()));
    	  
    	  dataset.addSeries("histogram data", vector,bin,
    			 Gui.data_arff.attributeStats(Gui.visucombobox.getSelectedIndex()).numericStats.min,
    			 Gui.data_arff.attributeStats(Gui.visucombobox.getSelectedIndex()).numericStats.max);
    	  
           
    		 }
	 /***Returning intervals specified during the numeric**numeric selection***/
     interval=new Interval();
     
	 double[][] mat=new double[dataset.getItemCount(0)][2];
	 for(int i=0;i<dataset.getItemCount(0);i++){
		mat[i][0]=dataset.getStartXValue(0, i); 
		mat[i][1]=dataset.getEndXValue(0, i);
	
     interval.modelinterval.addRow(new Object[] {(i+1),
      "["+new DecimalFormat("#0.00").format(dataset.getStartXValue(0, i))+"; "
      +new DecimalFormat("#0.00").format(dataset.getEndXValue(0, i))+"]",(int)dataset.getEndYValue(0, i)});
    
		
	 }
	 return mat;
    }
}   