import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewDAOImpl extends DBUtil implements NewDAO {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement ps = null;


    @Override
    public void getList() {
        // get the whole information
        String sql = "select CustomerID,title,firstName,lastName,gender from Customer";

        // Initialize Array, avoid null pointer

        rs = this.executeQuery(sql);


        // output the result of rs in the console
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String gender = rs.getString(5);
                System.out.println(id + "\t" + title + "\t" + firstName + "\t" + lastName + "\t" + gender);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.close();
        }
    }

    @Override
    public void getNewsTitle(String title) {
        String sql = "select CustomerID,title,firstName,lastName,gender from Customer where title like ?";

        Object[] params = {title};

        rs = this.executeQuery(sql, params);

        // output the result of rs in the console
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String title1 = rs.getString("title");
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String gender = rs.getString(5);
                System.out.println(id + "\t" + title1 + "\t" + firstName + "\t" + lastName + "\t" + gender);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.close();
        }
    }

    @Override
    public void add(int id, String title, String firstName, String lastName, String gender) {
        String sql = "insert into Customer(CustomerID,title,firstName,lastName,gender) values(?,?,?,?,?)";
        Object[] params = {id, title, firstName, lastName, gender};

        int i = this.executeUpdate(sql, params);
        if (i > 0) {
            System.out.println("insert is successful");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from Customer where CustomerID=?";
        Object[] params = {id};

        int i = this.executeUpdate(sql, params);

        if (i > 0) {
            System.out.println("delete is successful");
        }
    }

    @Override
    public void update(int id, String firstName, String lastName) {
        String sql = "update Customer set firstName = ? where id ?";

        Object[] params = {id, firstName, lastName};

        int i = this.executeUpdate(sql, params);

        if (i > 0) {
            System.out.println("update is successful");
        }
    }

    public static void main(String[] args) {
        NewDAO newDAO = new NewDAOImpl();

        newDAO.getList();
        newDAO.add(1, "Mr.", "Adam", "Zeng", "male");
        newDAO.update(1, "Adam", "Wang");
        newDAO.delete(1);
    }
}
