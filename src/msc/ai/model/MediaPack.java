package msc.ai.model;

/**
 *
 * @author Keshan De Silva
 */
public class MediaPack
{
    private String mediaPackName;
    private ResourceFilesSet resourceFilesSet;

    public MediaPack()
    {
        resourceFilesSet = new ResourceFilesSet();
    }

    public String getMediaPackName()
    {
        return mediaPackName;
    }

    public void setMediaPackName(String mediaPackName)
    {
        this.mediaPackName = mediaPackName;
    }

    public ResourceFilesSet getResourceFilesSet()
    {
        return resourceFilesSet;
    }

    public void setResourceFilesSet(ResourceFilesSet resourceFilesSet)
    {
        this.resourceFilesSet = resourceFilesSet;
    }
    
    
}
