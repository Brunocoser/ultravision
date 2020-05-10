import Enums.MediaFormats;
import Enums.Plans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Load {

    ArrayList<Customer> M_listCustomers = new ArrayList<Customer>();
    static ArrayList<Title> M_listTitles = new ArrayList<Title>();
    ArrayList<CustomerTitle> M_listCustomersTitles = new ArrayList<CustomerTitle>();


    public void showMenu() throws IOException{
        Scanner myScanner = new Scanner(System.in);
        myScanner.useDelimiter("\\n");
        String option;

        do {
            System.out.println("-------------------- MENU --------------------\n"
                    + "|                                            |\n"
                    + "|   [1] Employee                             |\n"
                    + "|   [2] Customer                             |\n"
                    + "|   [0] Exit                                 |\n"
                    + "|                                            |\n"
                    + "--------Please, choose a valid option--------");
            option = myScanner.nextLine();

            switch (option) {
                case "0":
                    System.out.println("Exit");
                    System.exit(0);
                    break;
                case "1":
                    menuEmployee(returnListTitles(), returnListCustomers());
                    break;
                case "2":
                    menuCustomer(returnListTitles(), returnListCustomers());
                    break;
                default:
                    System.out.println("It's an invalid option");
                    break;
            }
        } while (true);
    }

    public void menuEmployee(ArrayList<Title> listTitles, ArrayList<Customer> listCustomers) throws IOException {

        Functionality functionality = new Functionality();
        Scanner myScanner = new Scanner(System.in);
        myScanner.useDelimiter("\\n");
        String option = "";

        do {
            System.out.println("---------------------- MENU ----------------------\n"
                    + "|                                                |\n"
                    + "|   [1] Search for Titles                        |\n"
                    + "|   [2] New Title                                |\n"
                    + "|   [3] Search for Customers                     |\n"
                    + "|   [4] New Customer                             |\n"
                    + "|   [5] Update Customers and Subscription Plan   |\n"
                    + "|   [0] Return to main menu                      |\n"
                    + "|                                                |\n"
                    + "----------Please, choose a valid option----------");
            option = myScanner.nextLine();
            switch (option) {
                case "0":
                    System.out.println("Return");
                    break;
                case "1":
                    functionality.searchTitle(listTitles);
                    break;
                case "2":
                    functionality.addTitle(listTitles, myScanner);
                    break;
                case "3":
                    functionality.searchCustomers(listCustomers);
                    break;
                case "4":
                    functionality.addCustomers(listCustomers);
                    break;
                case "5":
                    functionality.updateCustomers(listCustomers);
                    break;
                default:
                    System.out.println("It's an invalid option");
                    break;
            }
        } while (!option.equals("0"));
    }

    public void menuCustomer(ArrayList<Title> listTitles, ArrayList<Customer> listCustomers) {

        Functionality functionality = new Functionality();
        Scanner myScanner = new Scanner(System.in);
        myScanner.useDelimiter("\\n");
        String option = "";

        do {
            System.out.println("---------------------- MENU ----------------------\n"
                    + "|                                                |\n"
                    + "|   [1] Search for Titles                        |\n"
                    + "|   [2] Register a rent                          |\n"
                    + "|   [3] Register a return                        |\n"
                    + "|   [0] Return to main menu                      |\n"
                    + "|                                                |\n"
                    + "----------Please, choose a valid option----------");
            option = myScanner.next();
            switch (option) {
                case "0":
                    System.out.println("Return");
                    break;
                case "1":
                    functionality.searchTitle(listTitles);
                    break;
                case "2":
                    functionality.registerRent(listCustomers, listTitles, myScanner);
                    break;
                case "3":
                    functionality.returnTitle(listCustomers, listTitles, myScanner);
                    break;
                default:
                    System.out.println("It's an invalid option:" + option);
                    break;
            }
        } while (!option.equals("0"));
    }

    public void loadCustomers() {
        ArrayList<Customer> listCustomers = new ArrayList<Customer>();
        try {
            FileInputStream stream = new FileInputStream("Customers.txt");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedR = new BufferedReader(reader);
            String line = bufferedR.readLine();

            while (line != null) {

                String[] field = line.split("\\|");
                int id = Integer.parseInt(field[0]);
                String name = field[1];
                String card = field[2];
                String birthday = field[3];
                String subPlan = field[4];
                int points = Integer.parseInt(field[5]);

                Customer newCustomer = new Customer(id, name, card, birthday, null, points);

                switch (subPlan.toUpperCase()) {
                    case "ML":
                        newCustomer.setSubPlan(Plans.ML);
                        break;
                    case "VL":
                        newCustomer.setSubPlan(Plans.VL);
                        break;
                    case "TV":
                        newCustomer.setSubPlan(Plans.TV);
                        break;
                    case "PR":
                        newCustomer.setSubPlan(Plans.PR);
                        break;
                    case "NONE":
                        newCustomer.setSubPlan(Plans.NONE);
                        break;
                    default:
                        newCustomer.setSubPlan(Plans.NONE);
                        break;
                }

                line = bufferedR.readLine();
                listCustomers.add(newCustomer);
            }

            M_listCustomers = listCustomers;
        } catch (Exception e) {
            showError(e, 1);
        }

    }

    public void loadTitles() {

        ArrayList<Title> listTitles = new ArrayList<Title>();

        try {
            FileInputStream stream = new FileInputStream("Titles.txt");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedR = new BufferedReader(reader);
            String line = bufferedR.readLine();

            while (line != null) {

                String[] field = line.split("\\|");
                int id = Integer.parseInt(field[0]);
                String title = field[1];
                int yearRelease = Integer.parseInt(field[2]);
                String genre = field[3];
                String directorOrBand = field[4];
                String format = field[5];
                String type = field[6];
                String sRented = field[7];

                boolean rented = Boolean.parseBoolean(sRented);

                Title newTitle = new Title(id, title, yearRelease, genre, directorOrBand, null, null, rented);

                switch (format.toLowerCase()) {
                    case "cd":
                        newTitle.setFormatValue(MediaFormats.CD);
                        break;
                    case "dvd":
                        newTitle.setFormatValue(MediaFormats.DVD);
                        break;
                    case "bluray":
                        newTitle.setFormatValue(MediaFormats.BluRay);
                        break;
                    default:
                        System.out.println("It's an invalid option");
                        break;
                }

                switch (type.toUpperCase()) {
                    case "ML":
                        newTitle.setType(Plans.ML);
                        break;
                    case "VL":
                        newTitle.setType(Plans.VL);
                        break;
                    case "TV":
                        newTitle.setType(Plans.TV);
                        break;
                    case "PR":
                        newTitle.setType(Plans.PR);
                        break;
                    case "NONE":
                        newTitle.setType(Plans.NONE);
                        break;
                    default:
                        newTitle.setType(Plans.NONE);
                        break;
                }
                line = bufferedR.readLine();
                listTitles.add(newTitle);
            }
        } catch (Exception e) {
            showError(e, 2);
        }

        M_listTitles = listTitles;

    }

    public void loadCustomerTitles() {

        CustomerTitle customerTitle;
        ArrayList<CustomerTitle> listCustomersTitles = new ArrayList<CustomerTitle>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            FileInputStream stream = new FileInputStream("CustomerTitle.txt");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedR = new BufferedReader(reader);
            String line = bufferedR.readLine();

            while (line != null) {

                String[] field = line.split("\\|");

                int idCustomer = Integer.parseInt(field[0]);
                int idTitle = Integer.parseInt(field[1]);
                String sDateRent = field[2];
                String sDateReturn = field[3];

                Date DateRent = format.parse(sDateRent);
                Date DateReturn = format.parse(sDateReturn);

                customerTitle = new CustomerTitle(idTitle, "", 0, "", "", MediaFormats.NONE, Plans.NONE, false);
                customerTitle.setIntIdCustomer(idCustomer);
                customerTitle.setDateRent(DateRent);
                customerTitle.setDateReturn(DateReturn);

                line = bufferedR.readLine();

                listCustomersTitles.add(customerTitle);
            }

        } catch (Exception e) {
            showError(e, 3);
        }

        M_listCustomersTitles = listCustomersTitles;
    }

    public void joinTitleRented() {
        Title title;

        try {
            for (Customer newCustomer : M_listCustomers) {
                for (CustomerTitle customerTitle : M_listCustomersTitles) {
                    if (newCustomer.getIntId() == customerTitle.getIntIdCustomer()) {

                        title = searchTitleById(customerTitle.getCode());

                        customerTitle.setTitle(title.getTitle());
                        customerTitle.setYearRelease(title.getYearRelease());
                        customerTitle.setGenre(title.getGenre());
                        customerTitle.setDirectorOrBand(title.getDirectorOrBand());
                        customerTitle.setFormatValue(title.getFormatValue());
                        customerTitle.setType(title.getType());
                        customerTitle.setRented(title.isRented());

                        newCustomer.addArrayTitlesRented(customerTitle);
                    }
                }
            }
        } catch (Exception e) {
            showError(e, 4);
        }
    }

    public static Title searchTitleById(int id) {

        for (int i = 0; i < M_listTitles.size(); i++) {

            if (M_listTitles.get(i).getCode() == id) {

                Title title = new Title(M_listTitles.get(i).getCode(), M_listTitles.get(i).getTitle(),
                        M_listTitles.get(i).getYearRelease(), M_listTitles.get(i).getGenre(), M_listTitles.get(i).getDirectorOrBand(),
                        M_listTitles.get(i).getFormatValue(), M_listTitles.get(i).getType(), M_listTitles.get(i).isRented());
                return title;
            }
        }
        return null;
    }

    public ArrayList<Title> returnListTitles() {
        return M_listTitles;
    }

    public ArrayList<Customer> returnListCustomers() {
        ArrayList<Customer> listCustomers = M_listCustomers;
        return listCustomers;
    }

    public void showError(Exception e, int pointError) {

        switch (pointError) {
            case 1:
                System.out.println(e + "\n ERROR LOAD CUSTOMERS");
                break;
            case 2:
                System.out.println(e + "\n ERROR LOAD TITLES");
                break;
            case 3:
                System.out.println(e + "\n ERROR LOAD TITLES RENTED");
                break;
            case 4:
                System.out.println(e + "\n ERROR CUSTOMERS TITLES");
                break;
            default:
                System.out.println(e);
                break;
        }

        System.out.println("CHECK FILES");
        System.out.println("Exit");
        System.exit(0);
    }
}