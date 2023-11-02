package com.example.yunnan.entity;

public class NoticeDetailEntry {
    private String id;
    private String sponsor;
    private String title;
    private String content;
    private String time;
    private int watch_num;
    private String target;

    public NoticeDetailEntry(String s, String s1, String s2, String s3, String s4, int i, String s5) {
        this.id = s;
        sponsor = s1;
        title = s2;
        content = s3;
        time = s4;
        watch_num = i;
        target = s5;
    }

    @Override
    public String toString() {
        return "NoticeEntry{" +
                "id='" + id + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", watch_num=" + watch_num +
                ", target='" + target + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getWatch_num() {
        return watch_num;
    }

    public void setWatch_num(int watch_num) {
        this.watch_num = watch_num;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
