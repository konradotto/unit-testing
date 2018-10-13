package sem.gu.classes;

import sem.gu.exceptions.IllegalGPAException;

public class GPA {

    private int gpa;
    private static final int MAX_GPA = 10;
    private static final int MIN_GPA = 0;

    public GPA (int gpa) throws IllegalGPAException {
        if (gpa > MAX_GPA) {
            throw new IllegalGPAException(String.format("Illegal GPA: The passed GPA is bigger than the allowed maximum (%d)%n", MAX_GPA));
        } else if (gpa < 0) {
            throw new IllegalGPAException(String.format("Illegal GPA: The passed GPA is smaller than the allowed minimum (%d)%n", MIN_GPA));
        }
        this.gpa = gpa;
    }
}
