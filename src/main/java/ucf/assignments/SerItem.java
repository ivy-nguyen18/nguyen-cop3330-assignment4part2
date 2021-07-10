package ucf.assignments;

public class SerItem {

    private final String serDescription;
    private final String serDueDate;
    private final boolean serIsCompleted;

    public SerItem(String serDescription, String serDueDate, boolean serIsCompleted) {
        //instantiate variables
        this.serDescription = serDescription;
        this.serDueDate = serDueDate;
        this.serIsCompleted = serIsCompleted;
    }

    public String getSerDescription() {
        //return serializable description
        return serDescription;
    }

    public String getSerDueDate() {
        //return serializable due date
        return serDueDate;
    }

    public boolean getSerIsCompleted() {
        //return serializable isCompleted checkbox
        return serIsCompleted;
    }

}
