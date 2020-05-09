import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        load load = new load();

        load.loadCustomers();
        load.loadTitles();
        load.loadCustomerTitles();
        load.joinTitleRented();


        Scanner myScanner = new Scanner(System.in);
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
                    menuEmployee(load.returnListTitles(), load.returnListCustomers());
                    break;
                case "2":
                    menuCustomer(load.returnListTitles(), load.returnListCustomers());
                    break;
                default:
                    System.out.println("It's an invalid option");
                    break;
            }
        } while (true);
    }

    public static void menuEmployee(ArrayList<Title> listTitles, ArrayList<Customer> listCustomers) throws IOException {

        Functionality functionality = new Functionality();

        Scanner myScanner = new Scanner(System.in);
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
                    functionality.SearchTitle(listTitles);
                    break;
                case "2":
                    functionality.AddTitle(listTitles, myScanner);
                    break;
                case "3":
                    functionality.searchCustomers(listCustomers);
                    break;
                case "4":
                    functionality.AddCustomers(listCustomers);
                    break;
                case "5":
                    functionality.UpdateCustomers(listCustomers);
                    break;
                default:
                    System.out.println("It's an invalid option");
                    break;
            }
        } while (!option.equals("0"));
    }

    public static void menuCustomer(ArrayList<Title> listTitles, ArrayList<Customer> listCustomers) {

        Functionality functionality = new Functionality();

        Scanner myScanner = new Scanner(System.in);
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
            option = myScanner.nextLine();
            switch (option) {
                case "0":
                    System.out.println("Return");
                    break;
                case "1":
                    functionality.SearchTitle(listTitles);
                    break;
                case "2":
                    functionality.RegisterRent(listCustomers, listTitles, myScanner);
                    break;
                case "3":
                    functionality.ReturnTitle(listCustomers, listTitles, myScanner);
                    break;
                default:
                    System.out.println("It's an invalid option");
                    break;
            }
        } while (!option.equals("0"));
    }
}
