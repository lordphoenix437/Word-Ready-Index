package com.example.wri.Model;

public class Company_Mail {
    String tiltlespecialize;
    String title;
    String namestudent;
    String content;
    private boolean expanded;

    public Company_Mail() {
    }

    public Company_Mail(String tiltlespecialize, String title, String namestudent, String content) {
        this.tiltlespecialize = tiltlespecialize;
        this.title = title;
        this.namestudent = namestudent;
        this.content = content;
        this.expanded = false;
    }

    public String getTiltlespecialize() {
        return tiltlespecialize;
    }

    public void setTiltlespecialize(String tiltlespecialize) {
        this.tiltlespecialize = tiltlespecialize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNamestudent() {
        return namestudent;
    }

    public void setNamestudent(String namestudent) {
        this.namestudent = namestudent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
    @Override
    public String toString() {
        return "Company_Mail{" +
                "tiltlespecialize='" + tiltlespecialize + '\'' +
                ", title='" + title + '\'' +
                ", namestudent='" + namestudent + '\'' +
                ", content='" + content + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}
