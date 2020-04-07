package com.webapp.model;

import javax.persistence.Column;
import javax.persistence.Id;

import com.webapp.enums.Category;

public class Post {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="author_id")
    private int authorId;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="cateogry")
    private int category;
    
    private String postEmote;

    public int    getId()         { return this.id;        }
    public int    getAuthorId()   { return this.authorId;  }
    public String getTitle()      { return this.title;     }
    public String getContent()    { return this.content;   }
    public int getCategory()      { return this.category;  }
    public String getEmote()      { return this.postEmote; }

    public void setId(int id)              { this.id = id;           }
    public void setAuthorId(int aid)       { this.authorId = aid;    }
    public void setTitle(String title)     { this.title = title;     }
    public void setContent(String content) { this.content = content; }
    public void setCategory(int cat)       { this.category = cat;    }
    public void setEmote(String emote)     { this.postEmote = emote; }
}
