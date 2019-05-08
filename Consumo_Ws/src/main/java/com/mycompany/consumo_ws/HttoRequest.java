/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consumo_ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author josemartins
 */
public class HttoRequest {

    boolean uploadFile(String url, File file) throws IOException
{
        HttpPost post = new HttpPost(url);
        post.setHeader("Accept", "multipart/form-data");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        // fileParamName should be replaced with parameter name your REST API expect.
        builder.addPart("rem", new FileBody(file));
        //builder.addPart("optionalParam", new StringBody("true", ContentType.create("text/plain", Consts.ASCII)));
        post.setEntity(builder.build());
        HttpResponse response = (HttpResponse) HttpClients.createDefault().execute(post);
        int httpStatus = response.statusCode();
        // If the returned HTTP response code is not in 200 series then
        // throw the error
        if (httpStatus < 200 || httpStatus > 300) {
            throw new IOException("HTTP " + httpStatus + " - Error during upload of file: ");
        }
        if (httpStatus == 200) {
            JOptionPane.showMessageDialog(null, "Upload feito com sucesso!",
                        "Informação de upload", JOptionPane.INFORMATION_MESSAGE);
        }
    return true;
}
}
