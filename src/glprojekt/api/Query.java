package glprojekt.api;

/**
 * Created by tomas on 4/13/2016.
 */
public enum Query {
    INSERT_EMPLOYEE("INSERT INTO `employee` (`ID`, `Name`, `Surname`, `Sex`, `Birth_date`, `Start_date`) VALUES (?, ?, ?, ?, ?,?)"),
    INSERT_CONTACT("INSERT INTO `contact` (`Employee_id`, `Phone`, `Email`) VALUES (?,?,?)"),
    SELECT_EMPLOYEE("SELECT * FROM employee");

    private final String text;

    private Query(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
