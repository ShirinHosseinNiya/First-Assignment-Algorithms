public class Exercises {

    /*
        there is an array of positive integers as input of function and another integer for the target value
        all the algorithm should do is to find those two integers in array which their multiplication is the target
        then it should return an array of their indices
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        note: you should return the indices in ascending order and every array's solution is unique
    */
    public int[] productIndices(int[] values, int target) {
        int firstNum, secondNum;
        int[] answer = new int [2];
        for (int i = 0 ; i < values.length - 1 ; i++) {
            firstNum = values[i];
            for (int j = i + 1 ; j < values.length ; j++) {
                secondNum = values[j];
                if (firstNum * secondNum == target) {
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                }
            }
        }
        return answer;
    }

    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        int toRight = cols;
        int toDown = rows - 1;
        int toLeft = cols - 1;
        int toUp = rows - 2;
        int[] answer = new int [rows * cols];
        int r = 0;
        int c = -1;
        for (int k = 0 ; k < rows * cols ;) {
            if (k < rows * cols) {
                for (int i = 0 ; i < toRight ; i++) {
                    c++;
                    answer [k] = values[r][c];
                    k++;
                }
                toRight -= 2;
            }

            if (k < rows * cols) {
                for (int i = 0 ; i < toDown ; i++) {
                    r++;
                    answer [k] = values[r][c];
                    k++;
                }
                toDown -= 2;
            }

            if (k < rows * cols) {
                for (int i = 0 ; i < toLeft ; i++) {
                    c--;
                    answer [k] = values[r][c];
                    k++;
                }
                toLeft -= 2;
            }

            if (k < rows * cols) {
                for (int i = 0 ; i < toUp ; i++) {
                    r--;
                    answer [k] = values[r][c];
                    k++;
                }
                toUp -= 2;
            }
        }

        return answer;
    }

    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array
    */
    public int[][] intPartitions(int n) {
        int count = countPartitions(n, n);
        int[][] result = new int[count][n];
        int[] buffer = new int[n];
        fillPartitions(n, n, buffer, 0, result, new int[]{0});
        return trimArray(result, count, n);
    }

    private int countPartitions(int n, int max) {
        if (n == 0) return 1;
        if (n < 0 || max == 0) return 0;
        return countPartitions(n - max, max) + countPartitions(n, max - 1);
    }

    private void fillPartitions(int n, int max, int[] buffer, int index, int[][] result, int[] count) {
        if (n == 0) {
            System.arraycopy(buffer, 0, result[count[0]], 0, index);
            count[0]++;
            return;
        }
        for (int i = Math.min(n, max); i >= 1; i--) {
            buffer[index] = i;
            fillPartitions(n - i, i, buffer, index + 1, result, count);
        }
    }

    private int[][] trimArray(int[][] result, int count, int n) {
        int[][] finalResult = new int[count][];
        for (int i = 0; i < count; i++) {
            int length = 0;
            while (length < n && result[i][length] != 0) {
                length++;
            }
            finalResult[i] = new int[length];
            System.arraycopy(result[i], 0, finalResult[i], 0, length);
        }
        return finalResult;
    }

    public static void main(String[] args) {
        // you can test your code here
    }
}