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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class adminShow extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
        @Override
        public void doPost(HttpServletRequest req , HttpServletResponse res)  throws IOException , ServletException
        {
            try
            {
                PrintWriter out = res.getWriter() ;
                        
                 Class.forName("com.mysql.jdbc.Driver") ;
                
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root" , "root") ;
                
                PreparedStatement ps = con.prepareStatement("SELECT * FROM registeration ") ;
                
                 ResultSet rs = ps.executeQuery() ;
                 
                 ResultSetMetaData rsmd=rs.getMetaData();
                 
                 out.print("<style>") ;
                 out.print("body{");
                 out.print("text-align:center ;");
                 out.print("position:fixed ;") ;
                 out.print("left:270px") ;
                 out.print("}");
//                 out.print(".du{") ;
//                 out.print("margin : 50% 50% ;") ;
//                 out.print("}") ;
                 out.print(".tab{") ;
                 out.print("border : 4px solid black ;") ;
                 out.print("border-collapse : collapse ;") ;
                 out.print("padding:17px ;") ;
                 out.print("font-size:19px") ;
                 out.print("text-align : center ;") ;
                 out.print("}") ;
                 out.print("</style>") ;
                 
                 out.println("<h1 class><u>Library Member Details</u></h1>"); 
                 out.print("<table class='du tab'>") ;
                 
                   out.print("<tr>");

                     out.print("<th class='tab'>"+rsmd.getColumnName(2)+"</th>");

                     out.print("<th class='tab'>"+rsmd.getColumnName(3)+"</th>");
                     
                     out.print("<th class='tab'>"+rsmd.getColumnName(4)+"</th>");
                     
                     out.print("<th class='tab'>"+rsmd.getColumnName(5)+"</th>");
                     
                     out.print("</tr>");
                 while(rs.next())
                 {
                    
                     
                     out.print("<tr>");      
                        out.print("<td class='tab'>"+rs.getString(2)+"</td>");

                        out.print("<td class='tab'>"+rs.getString(3)+"</td>");
                        
                        out.print("<td class='tab'>"+rs.getString(4)+"</td>");

                        out.print("<td class='tab'>"+rs.getString(5)+"</td>");                  
                     out.print("</tr>");

                     }

                     out.print("</table>");
                 }
            catch(Exception e)
            {
                e.printStackTrace() ;
            }
            }
            
        
    }



