import java.util.Random;
import java.util.Scanner;

public class Conway {
    public static int sizeHeight = 0;
    public static int sizeWidth = 0;
    public static Scanner scanner = new Scanner(System.in);
    public static int[][] matrix;

    public static void init(){
        do {
            System.out.println("Enter height, and width:");
            sizeHeight = scanner.nextInt();
            sizeWidth = scanner.nextInt();
        }while (sizeHeight <= 0 || sizeWidth <= 0);

        Random rand = new Random();

        int numberOfSeeds = rand.nextInt(sizeWidth * sizeHeight + 1);

        matrix = new int[sizeHeight][sizeWidth];

        for (int i = 0; i < numberOfSeeds; i++) {
            int seedHeight = rand.nextInt(sizeHeight);
            int seedWidth = rand.nextInt(sizeWidth);
            matrix[seedHeight][seedWidth] = 1;
        }
    }

    public static void initSeed(){
        do {
            System.out.println("Enter height, and width:");
            sizeHeight = scanner.nextInt();
            sizeWidth = scanner.nextInt();
        }while (sizeHeight <= 0 || sizeWidth <= 0);

        int seedHeight;
        int seedWidth;
        int number;
        matrix = new int[sizeHeight][sizeWidth];

        do {
            System.out.println("Enter number of seeds:");
            number = scanner.nextInt();
        }while (number <= 0);

        for (int i = 0; i < number; i++) {
            do {
                System.out.println("Enter seed coordinates:");
                seedHeight = scanner.nextInt();
                seedWidth = scanner.nextInt();
                matrix[seedHeight][seedWidth] = 1;
            }while(seedWidth <= 0 || seedHeight <= 0);
        }
    }

    public static void display()
    {
        for (int i = 0; i < sizeHeight; i++){
            for (int j = 0; j < sizeWidth; j++){
                if (matrix[i][j] == 1)
                    System.out.print("X");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
    }

    public static void getNextGeneration(){
        int[][] newArr = new int[sizeHeight][sizeWidth];
        for (int i = 1; i < sizeHeight - 1; i++){
            for(int j = 1; j < sizeWidth - 1; j++){
                int count = 0;
                for (int l = -1; l <= 1; l++) {
                    for (int m = -1; m <= 1; m++) {
                        count += matrix[i + l][j + m];
                    }
                }

                count -= matrix[i][j];

                if (matrix[i][j] == 1){
                    if (count < 2 || count > 3)
                        newArr[i][j] = 0;
                    else
                        newArr[i][j] = 1;
                }
                else
                if (count == 3)
                    newArr[i][j] = 1;
            }
        }
        matrix = newArr;
    }

    public static void playGame(){
        int option;
        do {
            System.out.println("1: Enter size and generate a seed\n2: Enter size and coordinates of the seed");
            System.out.println("Choose an option");
            option = scanner.nextInt();
        }
        while(option != 1 && option != 2);

        if (option == 1){
            init();
        }
        else
            initSeed();

        while(true){
            scanner.nextLine();
            display();
            getNextGeneration();
        }
    }
}
