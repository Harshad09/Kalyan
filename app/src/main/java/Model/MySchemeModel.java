package Model;

import java.util.Map;

public class MySchemeModel {
    private String name;
    private String link;
    private String benefits;
    private String overview;
    private String documentRequired;
    private String department;

    public MySchemeModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDocumentRequired() {
        return documentRequired;
    }

    public void setDocumentRequired(String documentRequired) {
        this.documentRequired = documentRequired;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "MySchemeModel{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", benefits='" + benefits + '\'' +
                ", overview='" + overview + '\'' +
                ", documentRequired='" + documentRequired + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
