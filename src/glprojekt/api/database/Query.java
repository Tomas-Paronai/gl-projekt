package glprojekt.api.database;

/**
 * Created by tomas on 4/13/2016.
 */
public enum Query {
    INSERT_EMPLOYEE("INSERT INTO `employee` (`ID`, `Name`, `Surname`, `Sex`, `Birth_date`, `Start_date`) VALUES (?, ?, ?, ?, ?,?)",QueryType.INSERT),
    INSERT_CONTACT("INSERT INTO `contact` (`Employee_id`, `Phone`, `Email`) VALUES (?,?,?)",QueryType.INSERT),
    SELECT_ALL_EMPLOYEE("SELECT * FROM employee",QueryType.SELECT);

    private final String text;
    private final QueryType type;

    private Query(final String text, final QueryType type) {
        this.text = text;
        this.type = type;
    }

    @Override
    public String toString() {
        return text;
    }

    public QueryType getType() {
        return type;
    }
}
