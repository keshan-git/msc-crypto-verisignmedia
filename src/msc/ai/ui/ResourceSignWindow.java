package msc.ai.ui;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import msc.ai.core.FileOperations;
import msc.ai.core.SecurityOperations;
import msc.ai.model.MediaPack;
import msc.ai.model.ResourceFile;

/**
 *
 * @author Keshan De Silva
 */
public class ResourceSignWindow extends javax.swing.JFrame
{
    private MediaPack mediaPack;
    private FileListTableModel tableModel;
    
    private JFileChooser fileChooser = new JFileChooser();
    /**
     * Creates new form ResourceSignWindow
     */
    public ResourceSignWindow()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        btnAddresources = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableFileList = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtPackName = new javax.swing.JTextField();
        btnExit = new javax.swing.JButton();
        btnPackage = new javax.swing.JButton();
        buttonSign = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();

        setTitle("VeriSign Media - Signing Resources");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Resources"));

        btnAddresources.setIcon(new javax.swing.ImageIcon(getClass().getResource("/msc/ai/resources/New.png"))); // NOI18N
        btnAddresources.setText("(2) Add Resource(s)");
        btnAddresources.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAddresourcesActionPerformed(evt);
            }
        });

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/msc/ai/resources/New.png"))); // NOI18N
        btnNew.setText("(1) New");
        btnNew.setPreferredSize(new java.awt.Dimension(100, 25));
        btnNew.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnNewActionPerformed(evt);
            }
        });

        tableFileList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {},
                {},
                {},
                {}
            },
            new String []
            {

            }
        ));
        jScrollPane2.setViewportView(tableFileList);

        jLabel1.setText("Media Pack Name : ");

        txtPackName.setText("DefaultMediaPack");
        txtPackName.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddresources, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPackName, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txtPackName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddresources)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, txtPackName});

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/msc/ai/resources/Cancel.jpg"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.setPreferredSize(new java.awt.Dimension(100, 25));
        btnExit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnExitActionPerformed(evt);
            }
        });

        btnPackage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/msc/ai/resources/package.png"))); // NOI18N
        btnPackage.setText("(4) Package");
        btnPackage.setPreferredSize(new java.awt.Dimension(120, 25));
        btnPackage.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnPackageActionPerformed(evt);
            }
        });

        buttonSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/msc/ai/resources/Key.png"))); // NOI18N
        buttonSign.setText("(3) Sign");
        buttonSign.setPreferredSize(new java.awt.Dimension(120, 25));
        buttonSign.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonSignActionPerformed(evt);
            }
        });

        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/msc/ai/resources/Export.jpg"))); // NOI18N
        btnExport.setText("(5) Export");
        btnExport.setPreferredSize(new java.awt.Dimension(120, 25));
        btnExport.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonSign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddresourcesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAddresourcesActionPerformed
    {//GEN-HEADEREND:event_btnAddresourcesActionPerformed
        fileChooser.setMultiSelectionEnabled(true);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
          File[] files = fileChooser.getSelectedFiles();
          for (File file : files)
          {
              ResourceFile resourceFile = new ResourceFile(file);
              mediaPack.getResourceFilesSet().addResourceFile(resourceFile);
              
              tableModel.addResourceFile(resourceFile);
          }
        }
    }//GEN-LAST:event_btnAddresourcesActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnNewActionPerformed
    {//GEN-HEADEREND:event_btnNewActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Creating a new Pack, will erase current worksapce\nDo you want to create new Pack?",
                                            "Create New Pack", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION)
        {
            mediaPack = new MediaPack();
            mediaPack.setMediaPackName(txtPackName.getText().trim());
            
            tableModel = new FileListTableModel();
            tableFileList.setModel(tableModel);
            //clear table
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnPackageActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPackageActionPerformed
    {//GEN-HEADEREND:event_btnPackageActionPerformed
        WaitPanel waitPanel = WaitPanel.getInstance();
        JDialog waitDialog = new JDialog(this);
        waitDialog.add(waitPanel);
        waitDialog.pack();
        waitDialog.setLocationRelativeTo(this);
        waitDialog.setVisible(true);
        
        SwingWorker swingWorker = new SwingWorker()
        {
            @Override
            protected Object doInBackground() throws Exception
            {
                boolean sucess = FileOperations.createPackage(mediaPack);
//                sucess &= SecurityOperations.encrypt(mediaPack.getMediaPackName() + ".media",
//                        mediaPack.getMediaPackName() + ".encmedia", SecurityOperations.getMediaCenterPublicKey());
//                
                waitDialog.setVisible(false);
                if (sucess)
                {
                    JOptionPane.showMessageDialog(null, "New meadia package is created", "Package", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Fail to generate new media package", "Package", JOptionPane.WARNING_MESSAGE);
                }
                return sucess;
            } 
        };
        swingWorker.execute();
    }//GEN-LAST:event_btnPackageActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnExitActionPerformed
    {//GEN-HEADEREND:event_btnExitActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Do you want to exis?",
                                            "Exit", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnExportActionPerformed
    {//GEN-HEADEREND:event_btnExportActionPerformed
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int option = fileChooser.showSaveDialog(this);
        
        if (option == JOptionPane.YES_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            boolean sucess = FileOperations.copyFile(txtPackName.getText(), selectedFile);
            if (sucess)
            {
                JOptionPane.showMessageDialog(this, "Meadia package is export sucessfully", "Export Package", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Fail to export meadia package", "Export Package", JOptionPane.WARNING_MESSAGE);

            }
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void buttonSignActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonSignActionPerformed
    {//GEN-HEADEREND:event_buttonSignActionPerformed
        WaitPanel waitPanel = WaitPanel.getInstance();
        JDialog waitDialog = new JDialog(this);
        waitDialog.add(waitPanel);
        waitDialog.pack();
        waitDialog.setLocationRelativeTo(this);
        waitDialog.setVisible(true);
        
        SwingWorker swingWorker = new SwingWorker()
        {
            @Override
            protected Object doInBackground() throws Exception
            {
                boolean sucess = SecurityOperations.generateSignatures(mediaPack);
                waitDialog.setVisible(false);
                if (sucess)
                {
                    JOptionPane.showMessageDialog(null, "Meadia package sign completed", "Sign", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Fail to sing new media package", "Sign", JOptionPane.WARNING_MESSAGE);
                }
                return sucess;
            } 
        };
        swingWorker.execute();
    }//GEN-LAST:event_buttonSignActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[])
//    {
//        try
//        {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
//        }
//        catch (Exception ex)
//        {
//            Logger.getLogger(ResourceSignWindow.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable()
//        {
//            public void run()
//            {
//                new ResourceSignWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddresources;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPackage;
    private javax.swing.JButton buttonSign;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableFileList;
    private javax.swing.JTextField txtPackName;
    // End of variables declaration//GEN-END:variables
}
