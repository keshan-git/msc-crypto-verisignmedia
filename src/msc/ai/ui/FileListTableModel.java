package msc.ai.ui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import msc.ai.model.ResourceFile;

/**
 *
 * @author Keshan De Silva
 */
public class FileListTableModel extends AbstractTableModel
{
    private ArrayList<ResourceFile> resourceFileList = new ArrayList<>();
    private final String[] columnNames = new String[]{"File Type","File Name","File Size"};
    private final Class[] columnClasses = new Class[]{String.class, String.class, String.class};
        
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
        switch (column)
        {
            case 0 : return resourceFile.getFileType();
            case 1 : return resourceFile.getFileName();
            case 2 : return resourceFile.getFileSize();
        }
        
        return "-";
    }
    
    public void addResourceFile(ResourceFile file)
    {
        resourceFileList.add(file);
        fireTableDataChanged();
    }
}
