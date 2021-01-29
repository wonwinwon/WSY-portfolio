package web08;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class book_edit
 */
@WebServlet("/BookEdit")
public class BookEdit extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
        
        String url;
        String userid;
        String passwd;
        
    public BookEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                url = "jdbc:oracle:thin:@localhost:1521:orcl";
                userid="system";
                passwd="human123";
                
                Connection conn=null;
                Statement stmt=null;
                ResultSet rs=null;
                
                PrintWriter out=response.getWriter();
                
                try {
                        String sql="update book set room_id=?, checkin=?, checkout=?,"+
                                " howmany2=?, expense=?, name=?, mobile=? where book_id=?";
                        System.out.println(sql);
                        Class.forName("oracle.jdbc.driver.OracleDriver");
            
            conn = DriverManager.getConnection(url,userid,passwd);
//          stmt=conn.createStatement();
//                        rs=stmt.executeQuery(sql);
                        System.out.println("작동1");
                        PreparedStatement ps = conn.prepareStatement(sql);
                        
                        String roomname1 = request.getParameter("roomname1");
                    System.out.println("roomname1 : "+roomname1);
                    
                    String book_id = request.getParameter("book_id");
            String room_id = request.getParameter("hi_roomId");
            System.out.println("room_id : "+room_id);
            System.out.println("작동2");
            String checkin2 = request.getParameter("checkin2");
            System.out.println("checkin2 : "+checkin2);
            
            String checkout2 = request.getParameter("checkout2");
            System.out.println("checkout2 : "+checkout2);
            
            String roomperson = request.getParameter("roomperson");
            System.out.println("roomperson : "+roomperson);
            
            String mobilenumber = request.getParameter("mobilenumber");
            System.out.println("mobilenumber : "+mobilenumber);
            
            String chairman = request.getParameter("chairman");
            System.out.println("chairman : "+chairman);
            
            String oneday = request.getParameter("oneday");
            System.out.println("oneday : "+oneday);
            
//            String outprint="<table><tr><td class=roomnameTD>"+roomname1+"</td><td>,"+checkin2+" ~ "+
//                    checkout2+", "+roomperson+"</td></tr></table>";
//            System.out.println(outprint);
//            out.println(outprint);
            
            out.println("<table><tr><td class=roomnameTD>"+roomname1+"</td><td> ,"+checkin2+" ~ "+
                    checkout2+", "+roomperson+"</td></tr></table>");
            
            System.out.println("작동3");
            ps.setString(1,room_id);
            System.out.println("작동4");
            ps.setString(2, checkin2);
            ps.setString(3, checkout2);
            ps.setString(4, roomperson);
            System.out.println("작동5");
            ps.setString(5, oneday);
            ps.setString(6, chairman);
            ps.setString(7, mobilenumber);
            ps.setString(8, book_id);
            System.out.println("작동6");
            ps.executeUpdate();
            
                  
                  
                  ps.close();
                  
                        
                }catch(Exception e) {
                        out.println("error");
                }finally {
                        try {
                                conn.close();
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                        
                }
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                doGet(request, response);
        }

}
// 1120 1504