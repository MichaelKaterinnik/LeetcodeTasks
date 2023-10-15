package org.example.patterns;

import java.util.HashMap;
import java.util.Map;

/**
 * Adaptation of multiple interfaces
 * Let's imagine a situation where, on the one hand, we have a certain database in which data is stored. The database
 * has a standard set of commands (methods) for providing data; they are described in the IncomeData interface.
 * Examples of data presentation are given in the comments next to each method. On the other hand, there are users
 * who want to receive data from this database, but in some other (specific) format. Requests from users are
 * represented by methods in the Customer and Contact interfaces. There (in the comments next to each method) there
 * are examples of presenting information in the form in which users want to receive it from the database.
 *
 * Your task: write the logic of the adapter class IncomeDataAdapter, which will, based on requests from methods
 * from the Customer and Contact interfaces, contact the database (methods of the IncomeData interface), receive
 * data, process it, change the presentation if necessary, and return it as a result.
 *
 * Initialize countries before running the program. Correspondence between country code and name:
 * UA Ukraine
 * RU Russia
 * CA Canada
 *
 * If necessary, pad the beginning of the phone number (without the country code) with zeros up to 10 digits (see
 * examples in the comments to the corresponding method). Pay attention to the output format of the person's phone
 * number, last name and first name (see examples in the comments to the corresponding method).
 */
public class AdapterExample2 {
    public static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;

        public IncomeDataAdapter(IncomeData incomeData) {
            this.data = incomeData;
        }

        @Override
        public String getCompanyName() {
            return data.getCompany();
        }
        @Override
        public String getCountryName() {
            return countries.get(data.getCountryCode());
        }
        @Override
        public String getName() {
            return String.format("%s, %s", data.getContactLastName(), data.getContactFirstName());
        }
        @Override
        public String getPhoneNumber() {
            String phoneCode = String.valueOf(data.getCountryPhoneCode());
            String phoneNumber = String.valueOf(data.getPhoneNumber());
            String last7Chars = phoneNumber.substring(phoneNumber.length() - 7);
            int opCode = Integer.parseInt(phoneNumber.substring(0, phoneNumber.length() - 7));

            return String.format("+%s(%03d)%s-%s-%s", phoneCode, opCode,
                    last7Chars.substring(0, 3), last7Chars.substring(3, 5), last7Chars.substring(5));
        }
    }


    public interface IncomeData {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        int getCountryPhoneCode();      //For example: 38

        int getPhoneNumber();           //For example1: 501234567, For example2: 71112233
    }

    public interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example1: +38(050)123-45-67, For example2: +38(007)111-22-33
    }
}
