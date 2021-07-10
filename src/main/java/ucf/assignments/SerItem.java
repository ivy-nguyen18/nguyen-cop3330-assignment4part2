package ucf.assignments;

import java.time.LocalDate;

public class SerItem {

    private final String serDescription;
    private final String serDueDate;
    private final boolean serIsCompleted;

    public SerItem(String serDescription, String serDueDate, boolean serIsCompleted) {
        this.serDescription = serDescription;
        this.serDueDate = serDueDate;
        this.serIsCompleted = serIsCompleted;
    }

    public String getSerDescription() {
        return serDescription;
    }

    public String getSerDueDate() {
        return serDueDate;
    }

    public boolean isSerIsCompleted() {
        return serIsCompleted;
    }

}
