package task.directory.model;

public class FileModel {

    private String name;
    private String mimetype;
    private long size;
    private String extension;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String toString() {
        return String.format("\nName: %s \nSize: %s \nMime Type: %s \nExtension: %s",
                this.getName(), Long.toString(this.getSize()), this.mimetype, this.getExtension());
    }
}
