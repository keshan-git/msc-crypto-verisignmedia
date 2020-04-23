package msc.ai.ui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import msc.ai.model.ResourceFile;
import msc.ai.model.VerificationStatus;

/**
 *
 * @author Keshan De Silva
 */
public class ContentListTableModel extends AbstractTableModel
{
    private ArrayList<ResourceFile> resourceFileList = new ArrayList<>();
    private ArrayList<VerificationStatus> verificationStatusList = new ArrayList<>();
    private final String[] columnNames = new String[]{"Signature", "File Type", "File Name", "Filse Size"};
    private final Class[] columnClasses = new Class[]{VerificationStatus.class, String.class, String.class, String.class};
        
    @Override
    public int getRowCount()
    {
        return resourceFileList.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int column)
    {
        return columnClasses[column];
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        ResourceFile resourceFile = resourceFileList.get(row);
        VerificationStatus verificationStatus = verificationStatusList.get(row);
        switch (column)
        {
            case 0 : return verificationStatus;
            case 1 : return resourceFile.getFileType();
            case 2 : return resourceFile.getFileName();
            case 3 : return resourceFile.getFileSize();
        }
        
        return "-";
    }
    
    public void addResourceFile(ResourceFile file)
    {
        resourceFileList.add(file);
        verificationStatusList.add(VerificationStatus.NOT_VERIFY);
        fireTableDataChanged();
    }
    
    public void addResourceFiles(ArrayList<ResourceFile> fileList)
    {
        for (ResourceFile file : fileList)
        {   
            if (!file.getFileName().endsWith(".signature"))
            {
                resourceFileList.add(file);
                verificationStatusList.add(VerificationStatus.NOT_VERIFY);
            }
        }
    }

    public void updateVerificationList(ArrayList<VerificationStatus> verificationList)
    {
        this.verificationStatusList = verificationList;
        fireTableDataChanged();
    }
}
