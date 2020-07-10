package Model;

public class LabourModel {


    private String info;
    private String link;
    private String name;

    public LabourModel() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LabourModel{" +
                "info='" + info + '\'' +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
