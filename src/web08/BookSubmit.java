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

/**
 * Servlet implementation class BookSubmit
 */
@WebServlet("/BookSubmit")
public class BookSubmit extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
        String url;
        String userid;
        String passwd;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSubmit() {
        super();
        // TODO Auto-generated constructor stub
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                url="jdbc:oracle:thin:@localhost:1521:orcl";
                userid="system";
                passwd="human123";
                Connection conn=null;
                Statement stmt=null;
                Statement stmt2=null;
                ResultSet rs=null;
                ResultSet rs2=null;
//                ResultSet rs1=null;
                
                PrintWriter out=response.getWriter();
                
                try {        
                                String roomname1 = request.getParameter("roomname1");
                                System.out.println("roomname1 : "+roomname1);
                                
                        String room_id = request.getParameter("hi_roomId");
                        System.out.println("room_id : "+room_id);
                        
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
                        
                        String sql="insert into book values(?,?,?,?,?,?,?,?,to_char(sysdate,'yyyymmdd'))";
                        String sql1="select seg_id.nextval book_idR from dual";
                        String sql2="select howmany1,howmuch from room where room_id="+room_id;
//                        String sql2="select room_id from room";
                        System.out.println(22);
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        
                        conn = DriverManager.getConnection(url,userid,passwd);
                        stmt=conn.createStatement();
                        rs=stmt.executeQuery(sql1);
                        stmt2=conn.createStatement();
                        rs2=stmt2.executeQuery(sql2);
                        
                        rs2.next();
                        String howmany1 = rs2.getString("howmany1");
                        String howmuch = rs2.getString("howmuch");
                        
                        
                        rs.next();
                        String book_id=rs.getString("book_idR");
                        
                        PreparedStatement ps=conn.prepareStatement(sql);
                        
                        out.println("<option value="+book_id+"><input type=hidden id=hi_howmany1 value="+howmany1+"><input type=hidden id=hi_howmuch value="+howmuch+"><table><tr><td class=roomnameTD>"+roomname1+"</td><td>,"+checkin2+" ~ "+
                        checkout2+", "+roomperson+"</td></tr></table></option>");
                        
                        ps.setString(1,book_id);
                        
//                        rs1=stmt.executeQuery(sql2);
//                        String room_id = rs.getString("room_id");
                        ps.setString(2,room_id);

                        
                        ps.setString(3, checkin2);
                        ps.setString(4, checkout2);
                        ps.setString(5, roomperson);
                        
                        ps.setString(6, oneday);
                        ps.setString(7, chairman);
                        ps.setString(8, mobilenumber);
                        
                        ps.executeUpdate();
                        
                        
                        
                        ps.close();
                        conn.close();
                        
                }catch(Exception e) {
                        out.println("error");
                        System.out.println(e);
                }finally {
                        try {
                                rs.close();
                        } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                        }
//                        try {
//                                rs1.close();
//                        } catch (SQLException e1) {
//                                // TODO Auto-generated catch block
//                                e1.printStackTrace();
//                        }
                        try {
                                stmt.close();
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
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
// 1120 1503