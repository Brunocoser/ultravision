import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        KioskMenu kioskMenu = new KioskMenu();
        kioskMenu.loadCustomers();
        kioskMenu.loadTitles();
        kioskMenu.loadCustomerTitles();
        kioskMenu.joinTitleRented();
        kioskMenu.showMenu();
    }
}
