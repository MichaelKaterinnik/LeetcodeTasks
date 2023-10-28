package org.example.IOstreams;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Reading and writing to a file: JavaRush
 * Implement the logic for writing to a file and reading from a file for the JavaRush class.
 * A User object cannot have empty fields. It is convenient to store the date in the file in the long format.
 * The main method is implemented only for you and is not involved in testing.
 */
public class SavingObjectsNoSerialize {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File savedUser = File.createTempFile("saveduser", null);
            OutputStream outputStream = new FileOutputStream(savedUser);
            InputStream inputStream = new FileInputStream(savedUser);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user1 = new User();
            user1.setFirstName("ILYUKA");
            user1.setLastName("MADDYSON");
            user1.setMale(true);
            user1.setCountry(User.Country.RUSSIA);
            Calendar calendar = Calendar.getInstance();
            calendar.set(1988, Calendar.MARCH, 8, 11, 30, 30);
            user1.setBirthDate(calendar.getTime());
            javaRush.users.add(user1);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            StringBuilder sb = new StringBuilder();
            boolean isUsersPresent = users != null;
            sb.append(isUsersPresent).append("\n");
            if (isUsersPresent) {
                for (User user : users) {
                    sb.append(user.getFirstName()).append("\n");
                    sb.append(user.getLastName()).append("\n");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
                    String birthDate = sdf.format(user.getBirthDate());
                    sb.append(birthDate).append("\n");
                    sb.append(user.isMale()).append("\n");
                    sb.append(user.getCountry()).append("\n");
                }
            }

            outputStream.write(sb.toString().getBytes());
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод\
            byte[] b = new byte[inputStream.available()];
            StringBuilder sb = new StringBuilder();
            while (inputStream.available() > 0) sb.append((char)inputStream.read());

            String[] lines = sb.toString().split("\n");
            boolean isUsersPresent = Boolean.parseBoolean(lines[0]);
            if (isUsersPresent) {
                for (int i = 1; i < lines.length; i = i + 5) {
                    User loadedUser = new User();
                    loadedUser.setFirstName(lines[i]);
                    loadedUser.setLastName(lines[i + 1]);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
                    Date loadedDate = sdf.parse(lines[i + 2]);
                    loadedUser.setBirthDate(loadedDate);
                    loadedUser.setMale(Boolean.parseBoolean(lines[i + 3]));
                    loadedUser.setCountry(User.Country.valueOf(lines[i + 4]));
                    users.add(loadedUser);
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }

    public static class User {
        private String firstName;
        private String lastName;
        private Date birthDate;
        private boolean isMale;
        private Country country;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        public boolean isMale() {
            return isMale;
        }

        public void setMale(boolean male) {
            isMale = male;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public static enum Country {
            UKRAINE("Ukraine"),
            RUSSIA("Russia"),
            OTHER("Other");

            private String name;

            private Country(String name) {
                this.name = name;
            }

            public String getDisplayName() {
                return this.name;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (isMale != user.isMale) return false;
            if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
            if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
            if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
            return country == user.country;

        }

        @Override
        public int hashCode() {
            int result = firstName != null ? firstName.hashCode() : 0;
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
            result = 31 * result + (isMale ? 1 : 0);
            result = 31 * result + (country != null ? country.hashCode() : 0);
            return result;
        }
    }
}
