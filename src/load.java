import Enums.MediaFormats;
import Enums.Plans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class load {

    ArrayList<Customer> M_listCustomers = new ArrayList<Customer>();
    static ArrayList<title> M_listTitles = new ArrayList<title>();
    ArrayList<CustomerTitle> M_listCustomersTitles = new ArrayList<CustomerTitle>();

    public void loadCustomers(){
        ArrayList<Customer> listCustomers = new ArrayList<Customer>();
        try {
            FileInputStream stream = new FileInputStream("Customers.txt");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedR = new BufferedReader(reader);
            String line = bufferedR.readLine();

            while (line != null){

                String [] field = line.split("\\|");
                int id = Integer.parseInt(field[0]);
                String name = field[1];
                String card = field[2];
                String Birthday = field[3];
                String SubPlan = field[4];
                int points = Integer.parseInt(field[5]);

                Customer c = new Customer (id, name, card, Birthday, null, points);

                switch (SubPlan.toUpperCase()){
                    case "ML":
                        c.setSubPlan(Plans.ML);
                        break;
                    case "VL":
                        c.setSubPlan(Plans.VL);
                        break;
                    case "TV":
                        c.setSubPlan(Plans.TV);
                        break;
                    case "PR":
                        c.setSubPlan(Plans.PR);
                        break;
                    case "NONE":
                        c.setSubPlan(Plans.NONE);
                        break;
                    default:
                        c.setSubPlan(Plans.NONE);
                        break;
                }

                line = bufferedR.readLine();
                listCustomers.add(c);
            }

            M_listCustomers = listCustomers;
        }catch (Exception e){
            showError(e, 1);
        }

    }

    public void loadTitles(){

        ArrayList<title> listTitles = new ArrayList<title>();

        try {
            FileInputStream stream = new FileInputStream("Titles.txt");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedR = new BufferedReader(reader);
            String line = bufferedR.readLine();

            while (line != null){

                String [] field = line.split("\\|");
                Integer id = Integer.parseInt(field[0]);
                String title = field[1];
                Integer yearRelease = Integer.parseInt(field[2]);
                String genre = field[3];
                String directorOrBand = field[4];
                String format = field[5];
                String type = field [6];
                String sRented = field[7];

                boolean rented = Boolean.valueOf(sRented);

                title t = new title(id, title, yearRelease, genre, directorOrBand, null, null, rented);

                switch (format.toLowerCase()){
                    case "cd":
                        t.setFormatValue(MediaFormats.CD);
                        break;
                    case "dvd":
                        t.setFormatValue(MediaFormats.DVD);
                        break;
                    case "bluray":
                        t.setFormatValue(MediaFormats.BluRay);
                        break;
                    default:
                        System.out.println("It's an invalid option");
                        break;
                }

                switch (type.toUpperCase()){
                    case "ML":
                        t.setType(Plans.ML);
                        break;
                    case "VL":
                        t.setType(Plans.VL);
                        break;
                    case "TV":
                        t.setType(Plans.TV);
                        break;
                    case "PR":
                        t.setType(Plans.PR);
                        break;
                    case "NONE":
                        t.setType(Plans.NONE);
                        break;
                    default:
                        t.setType(Plans.NONE);
                        break;
            }
            line = bufferedR.readLine();
                listTitles.add(t);
        }
        }catch (Exception e){
            showError(e, 2);
        }

        M_listTitles = listTitles;

        }

        public void loadCustomerTitles(){

        CustomerTitle t;
        ArrayList<CustomerTitle> listCustomersTitles = new ArrayList<CustomerTitle>();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            try{
                FileInputStream stream = new FileInputStream("CustomerTitle.txt");
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader bufferedR = new BufferedReader(reader);
                String line = bufferedR.readLine();

                while (line != null){

                    String[] field = line.split("\\|");

                    int idCustomer = Integer.parseInt(field[0]);
                    int idTitle = Integer.parseInt(field[1]);
                    String sDateRent = field[2];
                    String sDateReturn = field[3];

                    Date DateRent = format.parse(sDateRent);
                    Date DateReturn = format.parse(sDateReturn);

                    t = new CustomerTitle(idTitle, "", 0, "", "", MediaFormats.NONE, Plans.NONE, false);
                    t.setIDCustomer(idCustomer);
                    t.setDateRent(DateRent);
                    t.setDateReturn(DateReturn);

                    line = bufferedR.readLine();

                    listCustomersTitles.add(t);
                }

            }catch (Exception e){
                showError(e, 3);
            }

            M_listCustomersTitles = listCustomersTitles;
        }

        public void joinTitleRented(){
        title title = null;

        try {
            for (Customer c : M_listCustomers){
                for (CustomerTitle ct : M_listCustomersTitles){
                    if(c.getID() == ct.getIDCustomer()){

                        title = SearchTitleById(ct.getCode());

                        ct.setTitle(title.getTitle());
                        ct.setYearRelease(title.getYearRelease());
                        ct.setGenre(title.getGenre());
                        ct.setDirectorOrBand(title.getDirectorOrBand());
                        ct.setFormatValue(title.getFormatValue());
                        ct.setType(title.getType());
                        ct.setRented(title.isRented());

                        c.addArrayTitlesRented(ct);
                    }
                }
            }
        }   catch (Exception e){
                showError(e, 4);
        }
        }

        public static title SearchTitleById(int id) {

            for (int i = 0; i < M_listTitles.size(); i++){

                if (M_listTitles.get(i).getCode() == id){

                    title title = new title(M_listTitles.get(i).getCode(), M_listTitles.get(i).getTitle(),
                            M_listTitles.get(i).getYearRelease(), M_listTitles.get(i).getGenre(), M_listTitles.get(i).getDirectorOrBand(),
                            M_listTitles.get(i).getFormatValue(), M_listTitles.get(i).getType(), M_listTitles.get(i).isRented());
                    return title;
                }
            }
            return null;
        }

        public ArrayList<title> returnListTitles(){
        return M_listTitles;
        }
        public ArrayList<Customer> returnListCustomers(){
        ArrayList<Customer> listCustomers = M_listCustomers;
        return listCustomers;
        }
        public void showError(Exception e, int pointError){

            switch (pointError){
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