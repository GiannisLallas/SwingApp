/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emergon.ui;

import emergon.entity.Activity;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public  class ActivityTableModel extends AbstractTableModel {
    
   private final String[] columnNames = new String[] {
            "Title", "ItemCode", "TotalCost" 
    };
   
    private final List<Activity> activityList;

    public ActivityTableModel(List activityList) {
        this.activityList = activityList;
    }
@Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
   
   

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Activity row = activityList.get(rowIndex);
        if (0 == columnIndex) {
            row.setTitle((String) aValue);
            
        } else if (1 == columnIndex) {
            row.setItemcode((String) aValue);
        } 
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(2 == columnIndex){
            return false;
        }else{
            return  true;
        }
    }

    @Override
    public int getRowCount() {
         return activityList.size();
    }

    @Override
    public int getColumnCount() {
         return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
     Activity row = activityList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getTitle();
            
        }
        else if(1 == columnIndex) {
            return row.getItemcode();
        }
        else if(2 == columnIndex) {
            return row.getTotalcost();
        }
       
        return null;
    }

}
