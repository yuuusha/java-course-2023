package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.CallingManager;
import edu.hw2.Task4.ExtraClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    @DisplayName("Один уровень вложенности")
    void oneLevelNesting() {
        CallingInfo res = CallingManager.callingInfo();
        CallingInfo expected = new CallingInfo("edu.hw2.Task4Test", "oneLevelNesting");
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Два уровня вложенности")
    void twoLevelNesting() {
        CallingInfo res = ExtraClass.extraMethod();
        CallingInfo expected = new CallingInfo("edu.hw2.Task4.ExtraClass", "extraMethod");
        assertEquals(expected, res);
    }
}
