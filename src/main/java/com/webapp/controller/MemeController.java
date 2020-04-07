package com.webapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.Config;
import com.webapp.dao.PostDao;
import com.webapp.model.MemeForm;

@Controller
public class MemeController extends PageController {

    @Autowired
    PostDao postDao;

    @PostMapping("/newpost")
    @ResponseBody
    public ResponseObject newPost(HttpSession session, @RequestBody MemeForm memeData) {

        // Terminate if there's no active session
        if(session.getAttribute("sessID") == null)
            return new ResponseObject("err#sess");

        try {

            // prepare path & image data
            String UPLOAD_DIR = Config.rootDir + "/src/main/resources/static/memes/";
            String base64Image = memeData.getImgData().split(",")[1];
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
            File file = new File(UPLOAD_DIR +memeData.getImgName() + ".png");

            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                // attemp to create post
                if(postDao.createPost((int)session.getAttribute("sessID"), memeData.getTitle(), memeData.getImgName(), memeData.getCategory())) {
                    outputStream.write(imageBytes);
                } else {
                    return new ResponseObject("err#db");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseObject("invalid file format");
        }
        
        return new ResponseObject("OK");
    }

}
