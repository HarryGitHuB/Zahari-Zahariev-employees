package api.factories;

import api.model.Pair;

public final class EmployeeFactory {

    public EmployeeFactory() {
    }

    public static Pair execute(long firstEmployee, long secondEmployee, long overlapDuration) {
        return new Pair(

                firstEmployee,
                secondEmployee,
                overlapDuration);
    }
}
