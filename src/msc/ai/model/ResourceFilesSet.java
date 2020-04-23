package msc.ai.model;

import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public class ResourceFilesSet
{
    private ArrayList<ResourceFile> resourceList = new ArrayList<>();

    public ArrayList<ResourceFile> getResourceList()
    {
        return resourceList;
    }

    public void setResourceList(ArrayList<ResourceFile> resourceList)
    {
        this.resourceList = resourceList;
    }
    
    public void addResourceFile(ResourceFile file)
    {
        if (this.resourceList == null)
        {
            resourceList = new ArrayList<>();
        }
        this.resourceList.add(file);
    }
    
    public int getFileCount()
    {
        if (resourceList != null)
        {
            return resourceList.size();
        }
        return 0;
    }
}
