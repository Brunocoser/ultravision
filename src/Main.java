import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Load load = new Load();
        load.loadCustomers();
        load.loadTitles();
        load.loadCustomerTitles();
        load.joinTitleRented();
        load.showMenu();
    }
}
