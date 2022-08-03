import com.company.Model;
import org.junit.Test;

import static org.junit.Assert.*;

//JUnit Test class

public class ModelTest {

    @Test
    public void flage1Test() {
        var model = new Model();
        model.flage3 = false;
        model.loadFiles();
        /*
            if flage1 is set to false
            then the word for selection should be 'FIXED'
         */
        assertEquals("FIXED", model.getWord());
    }

    @Test
    public void isWinTest() {
        var model = new Model();
        /*
            isWin Function should return True
            if the word passed in the peramter
            is equal to the word selected
         */
        assertTrue(model.isWin(model.getWord()));
    }

    @Test
    public void checkWordTest() {
        var model = new Model();
        int[] arr = {0, 0, 0, 0, 0};
        /* as if all the letters exist in the word
         and at right location then checkWord function
         should return array of all 0 numbers
         as i Entered the selected word in the perameter
         of checkWord Function means equal to the word selected
        to check the result
         */
        assertArrayEquals(arr, model.checkWords(model.getWord()));
    }


}