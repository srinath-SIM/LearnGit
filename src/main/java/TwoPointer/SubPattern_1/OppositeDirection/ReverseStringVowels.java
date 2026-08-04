package TwoPointer.SubPattern_1.OppositeDirection;

public class ReverseStringVowels {

    public static String reverseVowels(String vowels) {

//        {'h','e','l','l','o'}
        char[] single = vowels.toCharArray();

        int left = 0;
        int right = single.length -1;

        while(left < right)
        {
            if(!isVowels(single[left]))
            {
                left++;
            }
            else if (!isVowels(single[right]))
            {
                right--;
            }
            else
            {
                char temp = single[left];
                single[left] = single[right];
                single[right] = temp;


                left++;
                right--;
            }
        }

        return new String(single);
    }

    public static boolean isVowels(char ch)
    {
        ch = Character.toLowerCase(ch);

        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';

    }

    public static void main(String[] args) {

        String vowels = "hello";
        System.out.println(reverseVowels(vowels));
    }
}