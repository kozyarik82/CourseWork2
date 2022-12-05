import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PracticeTicket {
    public static void main(String[] args) {
        String s = "ротор";
        System.out.println(isPalindrome(s));

        HashMap<Integer, String> keyValue = new HashMap<>();
        keyValue.put(1, "one");
        keyValue.put(2, "two");
        keyValue.put(3, "three");
        Iterator iter = keyValue.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry map = (Map.Entry) iter.next();
            System.out.println(map.getKey() +" " + map.getValue());
        }
        for (Map.Entry map1 : keyValue.entrySet()) {
            System.out.println(map1.getKey() + " " + map1.getValue());
        }


    }

    private static boolean isPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return isPalindrome(s.substring(1, s.length() - 1));
        }
        return false;
    }
}
