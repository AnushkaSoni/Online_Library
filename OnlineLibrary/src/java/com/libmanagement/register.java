/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class register extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
             String name = request.getParameter("f_name") ;
            String uname = request.getParameter("uname") ;
            String email = request.getParameter("email") ;
            String password = request.getParameter("pass") ;
            
            try{
               
                Class.forName("com.mysql.jdbc.Driver") ;
              
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library" , "root" , "root") ;
              
                String query = "select * from registeration where email=?" ;
               
                PreparedStatement pstmt = con.prepareStatement(query) ; 
                pstmt.setString(1, email);
                 
                ResultSet rs = pstmt.executeQuery() ;
                
                if(rs.next() == false)
                {
                    String query1 = "insert into registeration (name , username , email , password) values(? , ? , ? , ?)" ;
                 
                    PreparedStatement pstmt1 = con.prepareStatement(query1) ; 
                    
                    pstmt1.setString(1 , name) ;
                    pstmt1.setString(2, uname);
                    pstmt1.setString(3 , email) ;
                    pstmt1.setString(4 , password) ;
                        
                    pstmt1.executeUpdate() ;
                    out.println("done") ;
                }
                else{
                    out.println("exist") ;
                }
                
                
                
                con.close() ;
            }
            catch(Exception e)
            {
                out.println(e) ;
                out.println("Here is some problem") ;
            }
           
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
