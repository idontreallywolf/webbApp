package com.webapp.model;

public class MemeForm {
    private String title;
    private String imgData;
    private String imgName;
    private int category;

    public void setTitle(String title)     { this.title = title;       }
    public void setImgData(String imgData) { this.imgData = imgData;   }
    public void setImgName(String name)    { this.imgName = name;      }
    public void setCategory(int category)  { this.category = category; }

    public String getTitle()    { return this.title;    }
    public String getImgData()  { return this.imgData;  }
    public String getImgName()  { return this.imgName;  }
    public int    getCategory() { return this.category; }
}
