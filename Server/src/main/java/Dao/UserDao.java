package Dao;

//import domain.Book;
import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    //添加

    public static Boolean add(final User user) throws Exception {
        JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
            @Override
            protected Boolean doTransaction(Connection conn) throws Exception {
                PreparedStatement ps = conn
                        .prepareStatement("insert into users(name,password) values(?,?)");
                ps.setString(1, user.username);
                ps.setString(2, user.passwd);
                return ps.execute();
            }
        };
        return t.execute();
    }
    //获取
    public static User get(final String username) throws Exception {
        JDBCTemplate<User> q = new Query<User>() {
            @Override
            protected User doQuery(Connection conn) throws Exception {
                PreparedStatement ps = conn
                        .prepareStatement("select * from users where name=?");
                ps.setString(1, username);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                User user = null;
                if (rs.next()) {
                    user = new User();
                    user.setUsername(rs.getString("name"));
                    user.setPasswd(rs.getString("password"));

                }
                return user;
            }
        };
        System.out.println("q.execute:"+q.execute());
        return q.execute();
    }


    // 修改
    public static Boolean update(final User user, final String old_usr_name)
            throws Exception {
        JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
            @Override
            protected Boolean doTransaction(Connection conn) throws Exception {
                PreparedStatement ps = conn
                        .prepareStatement("update users set usr_name=?,pwd=?,tk=? where usr_name=?");
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPasswd());

                return ps.execute();
            }
        };
        return t.execute();
    }

    // 删除
    public static Boolean delete(final String name) throws Exception {
        JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
            @Override
            protected Boolean doTransaction(Connection conn) throws Exception {
                PreparedStatement ps = conn
                        .prepareStatement("delete from users where name=?");
                ps.setString(1, name);
                return ps.execute();
            }
        };
        return t.execute();
    }
}
