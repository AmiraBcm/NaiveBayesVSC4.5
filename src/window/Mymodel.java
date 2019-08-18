package window;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
class MyModel extends DefaultTableModel
{
    private boolean editable;
 
    public MyModel(Object[][] objects, String[] strings) {
    	 super(objects,strings);
	}
	public void setEditable(boolean editable) { this.editable = editable; }
 
    @Override
    public boolean isCellEditable(int row, int col) { 
    	if(Edit.add_inst)
    	{if(row==(this.getRowCount()-1)) return editable;
    	else return false;}
    else return false;
}}