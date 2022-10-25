
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final String FileCSV = "./src/main/resources/people.csv";

    public static void main(String[] args) throws InvalidLineFormatException, IOException {

        List<Person> persons = new ArrayList<>();
        String line;

        BufferedReader br = new BufferedReader (new FileReader(FileCSV));
        line = br.readLine();


        try {

            while (line != null) {
                String[] people = line.split(":", -3);
                if(people[1] == "") {
                    people[1] = "Unknown";
                }
                if(people[2] == "") {
                    people[2] = "0";
                }
                persons.add(new Person(people[0], people[1], Integer.parseInt(people[2])));

                System.out.println("Name: " + people[0] + ". " + "Town: " + people[1] + ". " + "Age: " + people[2]);
                line = br.readLine();
            }

        } catch (Exception ex) {
            if (!separator(line)) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
                throw new InvalidLineFormatException("Falta el último delimitador de campo (:)");
            }
            System.err.println(ex.getMessage());
        }

        //FILTROS DE STREAM
        System.out.println("-------------------------------------");
        System.out.println("Personas menores de 25 años");
        filterByAge(persons);

        System.out.println("-------------------------------------");
        System.out.println("Personas que No comienzan por la Letra A");
        filterByA(persons);

        System.out.println("-------------------------------------");
        System.out.println("Contiene la ciudad de Madrid");
        filterByMadrid(persons);

        System.out.println("-------------------------------------");
        System.out.println("Contiene la ciudad de Barcelona");
        filterByBarcelona(persons);

    }
    public static boolean separator(String linea1) {
        int count = 0;

        for (int i = 0; i > linea1.length(); i++) {
            if (linea1.charAt(i)  == ':') {
                count++;
            }
        }
        return count == 2;
    }


    //
    static List<Person> filterByAge(List<Person> persons) {
        persons.stream()
                .filter((Person p) -> p.getAge() < 25 && p.getAge() != 0)
                .forEach((Person) -> System.out.println("Name: " + Person.getName() + ". " + "Town: " + Person.getTown() + ". " + "Age: " + Person.getAge()));


        return persons;
    }
    public static List<Person> filterByA(List<Person> persons){
        persons.stream()
                .filter((Person p)-> !p.getName().startsWith("A"))
                .forEach((Person)-> System.out.println("Name: " + Person.getName() + ". " + "Town: " + Person.getTown() + ". " + "Age: " + Person.getAge()));
        return persons;
    }

    public static List<Person> filterByMadrid(List<Person> persons){
        Optional<String> optionalCity = Optional.of("Madrid");

        persons.stream()
                .filter((Person p) -> p.getTown()
                        .contains(optionalCity.get()))
                .forEach((Person)-> System.out.println("Name: " + Person.getName() + ". " + "Town: " + Person.getTown() + ". " + "Age: " + Person.getAge()));
        return persons;
    }


    public static List<Person> filterByBarcelona(List<Person> persons){
        Optional<String> optionalCity2 = Optional.ofNullable("Barcelona");

        persons.stream()
                .filter((Person p) -> p.getTown()
                        .equals(optionalCity2.get()))
                .forEach((Person)-> System.out.println("Name: " + Person.getName() + ". " + "Town: " + Person.getTown() + ". " + "Age: " + Person.getAge()));
        return persons;
        //Este lo imprime de toda la lista -- Tengo que rectificarlo
    }


    private static class InvalidLineFormatException extends Throwable {
        public InvalidLineFormatException(String s) {
        }
    }
}