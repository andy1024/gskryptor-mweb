/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.warheim.gskryptor.mweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andy
 */

@WebServlet("/execute.do")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String src = req.getParameter("source");
        Renderer renderer = new Renderer(Config.width, Config.height);
        String error = null;
        String outputStr = null;
        try {
            List<Command> cmdList = renderer.readCommand(src);
            outputStr = renderer.createImage(cmdList);
        } catch (Exception ex) {
            error = ex.toString();
        }
        if (error != null) {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println(error);
            out.close();            
        } else {
            resp.setContentType("text/html");
//            resp.setContentType("image/svg-xml");
            PrintWriter out = resp.getWriter();
            out.println(outputStr);
            out.close();            
        }
    }
    
    
}
