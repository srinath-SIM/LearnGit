package TwoPointer.SubPattern_1.OppositeDirection;

public class ReverseString {

    public static void reverse(char[] single) {

        int left = 0;
        int right = single.length - 1;

        while (left < right) {

            char temp = single[left];
            single[left] = single[right];
            single[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {

        char[] single = {'h','e','l','l','o'};

        reverse(single);

        System.out.println(single);
    }
}