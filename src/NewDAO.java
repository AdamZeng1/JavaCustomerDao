public interface NewDAO {
    // query all information
    public void getList();

    // query part information
    public void getNewsTitle(String title);

    // increase
    public void add(int id, String title, String firstName, String lastName, String gender);

    // delete
    public void delete(int id);

    // update
    public void update(int id, String firstName, String lastName);
}
