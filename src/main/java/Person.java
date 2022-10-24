

public class Person {
    // Atributos
    String name;
    String town;
    int age;

    // Constructor
    public Person(String name,String town,int age){
        this.name = name;
        this.town = town;
        this.age = age;
    }
    /*
     * Devuelve el nombre del empleado
     * @return nombre del empleado
     */

    public String getName(){
        return name;
    }
    /*
     * Modifica el nombre de un empleado
     * @param nombre
     */

    public void setName (String name){
        this.name = name;
    }

    // Ciudad

    public String getTown() {
        return town;
    }
    public void setTown (String town){
        this.town = town;
    }

    // Edad

    public int getAge() {
        return age;
    }
    public void setTown (int town){
        this.age = age;
    }
}