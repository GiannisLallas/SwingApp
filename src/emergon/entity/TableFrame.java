package emergon.entity;

import emergon.entity.Activity;
import emergon.ui.ActivityTableModel;
import emergon.ui.Laborer;
import emergon.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class TableFrame extends javax.swing.JFrame {

    private static final String QUERY = "from Activity";
    private static final String QUERY_BASED_ON_ACTIVITY = "from Laborer where laborerid=";

    JFrame f;

    TableFrame() {
        f = new JFrame();
        Container c = f.getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        resultTable = new javax.swing.JTable();
        runQueryAndFillResultTable();
        resultTable.setBounds(20, 40, 200, 300);
        JScrollPane sp = new JScrollPane(resultTable);

        JScrollPane jScrollPane = new JScrollPane(laborerTable);
        laborerTable = new javax.swing.JTable();
        //listener
        ListSelectionModel select = resultTable.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = resultTable.getSelectedRow();
                System.out.println("************ " + row);
                displayResult1abor(row);
                laborerTable.setBounds(30, 40, 200, 300);

            }
        });
        c.add(resultTable.getTableHeader());
        c.add(resultTable);
        c.add(laborerTable.getTableHeader());
        c.add(laborerTable);
        f.pack();
        f.setVisible(true);
    }

    //RUN QUERY METHOD TO FILL TABLE
    private void runQueryAndFillResultTable() {
        executeHQLQuery(QUERY);
    }

    //Transaction 
    private void executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            resultList = q.list();
//            System.out.println("**********" + q.list());
            displayResult(resultList);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    
    
    
    

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TableFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TableFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TableFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TableFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableFrame();

            }
        });
    }
    
      

    //Declare resultTable & laborerTable
    private javax.swing.JTable resultTable;
    private javax.swing.JTable laborerTable;
    private List resultList;

    //displayTable
    private void displayResult1abor(int selectedRow) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();
        List<Activity> activityis = resultList;
        tableHeaders.add("Title");
        tableHeaders.add("ItemCode");
        tableHeaders.add("TotalCost");

        for (Object o : activityis.get(selectedRow).getLaborers()) {
            Laborer laborer = (Laborer) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(laborer.getTitle());
            oneRow.add(laborer.getItemcode());
            oneRow.add(laborer.getTotalcost());
            tableData.add(oneRow);
        }
        laborerTable.setModel(new DefaultTableModel(tableData, tableHeaders));
    }

    private void displayResult(List resList) {
        Vector<String> tableHeaders = new Vector<String>();
        List<Activity> list = resList;
        resultTable.setModel(new ActivityTableModel(list));
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
