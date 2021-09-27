package com.example.showpathfiles.entity;

public class MyFile {
    private String filename;
    private int img;
    private String filePath;
    private boolean isDir;

    public MyFile(String filename) {
        this.filename = filename;
    }

    public MyFile(String filename, int img) {
        this.filename = filename;
        this.img = img;
    }

    public MyFile() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }
}
