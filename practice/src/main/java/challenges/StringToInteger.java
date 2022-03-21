package challenges;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class StringToInteger {

    public int myAtoi(String s) {

        CharacterIterator iterator = new StringCharacterIterator(s.trim());

        char[] chars = s.toCharArray();
        boolean isNegative = false;

        List<Integer> integerList = new ArrayList<>();
        Integer integer = 0;

        for (char c : chars) {

            if(c == '-'){

                isNegative = true;
            }
            if(Character.isDigit(c)){
                integerList.add((int) c);

            }

            if(Character.isLetter(c)){
                for(int i = 0; i < integerList.size(); i++){
                    integer = integer + integerList.indexOf(i) * i;

                }

                if(isNegative){
                    integer *= -1;
                }

                return integer;
            }

        }

        return integer;
    }

}
