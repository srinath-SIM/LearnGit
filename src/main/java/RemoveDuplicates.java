public class RemoveDuplicates {

    public static int removeDuplicates(int[] nums) {

        if (nums.length == 0)
            return 0;

        int index = 0;

        for (int i = 1; i < nums.length; i++) {

            if (nums[index] != nums[i]) {

                index++;

                nums[index] = nums[i];
            }
        }

        return index + 1;
    }

    public static void main(String[] args) {

        int[] nums = {1,1,2};

        int length = removeDuplicates(nums);

        System.out.println("Length = " + length);

        for (int i = 0; i < length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}