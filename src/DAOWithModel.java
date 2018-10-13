public class DAOWithModel extends NewDAOImpl {

    public void getNewsTitle(Customer customer) {
        getNewsTitle(customer.getTitle());
    }

    public void add(Customer customer) {
        add(customer.getCustomerID(), customer.getTitle(),
                customer.getFirstName(), customer.getLastName(),
                customer.getGender());
    }

    public void delete(Customer customer) {
        delete(customer.getCustomerID());
    }

    public void update(Customer customer) {
        update(customer.getCustomerID(),
                customer.getFirstName(),
                customer.getLastName());
    }
}
