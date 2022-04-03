import java.util.*;

class Logic {
    int[][] blockS = { { 4, 3, 5, 8, 7, 6, 1, 2, 9 },
            { 8, 7, 6, 2, 1, 9, 3, 4, 5 },
            { 2, 1, 9, 4, 3, 5, 7, 8, 6 },
            { 5, 2, 3, 6, 4, 7, 8, 9, 1 },
            { 9, 8, 1, 5, 2, 3, 4, 6, 7 },
            { 6, 4, 7, 9, 8, 1, 2, 5, 3 },
            { 7, 5, 4, 1, 6, 8, 9, 3, 2 },
            { 3, 9, 2, 7, 5, 4, 6, 1, 8 },
            { 1, 6, 8, 3, 9, 2, 5, 7, 4 } };

    Random R_num = new Random();
    Random Grid_R_num = new Random();
    Random R_exnum = new Random();
    Random H_Rnum = new Random();

    int firstrow, secondrow, firstcol, secondcol, firstgrid, secondgrid, gc = 0;
    int carry[] = new int[9];
    int blockh[][] = new int[9][9];
    int blockc[][] = new int[9][9];


    int[][] generate() {
        int x = 10 + R_num.nextInt(10);
        for (int y = 0; y < x; y++) {
            for (int a = 0; a < 3; a++) {

                if (a == 0) {
                    firstrow = R_num.nextInt(3);
                    secondrow = R_num.nextInt(3);
                }

                else if (a == 1) {
                    firstrow = 3 + R_num.nextInt(3);
                    secondrow = 3 + R_num.nextInt(3);
                }

                else if (a == 2) {
                    firstrow = 6 + R_num.nextInt(3);
                    secondrow = 6 + R_num.nextInt(3);
                }

                for (int i = 0; i < 9; i++) {
                    carry[i] = blockS[firstrow][i];
                    blockS[firstrow][i] = blockS[secondrow][i];
                    blockS[secondrow][i] = carry[i];
                }
            }

            for (int a = 0; a < 3; a++) {

                if (a == 0) {
                    firstcol = R_num.nextInt(3);
                    secondcol = R_num.nextInt(3);
                }

                else if (a == 1) {
                    firstcol = 3 + R_num.nextInt(3);
                    secondcol = 3 + R_num.nextInt(3);
                }

                else if (a == 2) {
                    firstcol = 6 + R_num.nextInt(3);
                    secondcol = 6 + R_num.nextInt(3);
                }

                for (int i = 0; i < 9; i++) {
                    carry[i] = blockS[i][firstcol];
                    blockS[i][firstcol] = blockS[i][secondcol];
                    blockS[i][secondcol] = carry[i];
                }
            }
        }

        firstgrid = 1 + Grid_R_num.nextInt(3);
        secondgrid = 1 + Grid_R_num.nextInt(3);

        if ((firstgrid == 1 && secondgrid == 2) || (firstgrid == 2 && secondgrid == 1)) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 9; j++) {
                    carry[j] = blockS[i][j];
                    blockS[i][j] = blockS[i + 3][j];
                    blockS[i + 3][j] = carry[j];
                }
        } else if ((firstgrid == 2 && secondgrid == 3) || (firstgrid == 3 && secondgrid == 2)) {
            for (int i = 3; i < 6; i++)
                for (int j = 0; j < 9; j++) {
                    carry[j] = blockS[i][j];
                    blockS[i][j] = blockS[i + 3][j];
                    blockS[i + 3][j] = carry[j];
                }
        } else if ((firstgrid == 1 && secondgrid == 3) || (firstgrid == 3 && secondgrid == 1)) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 9; j++) {
                    carry[j] = blockS[i][j];
                    blockS[i][j] = blockS[i + 6][j];
                    blockS[i + 6][j] = carry[j];
                }
        }

        int firstnum, secondnum, shuffle;

        shuffle = 3 + R_num.nextInt(6);

        for (int k = 0; k < shuffle; k++) {
            firstnum = 1 + R_exnum.nextInt(9);
            secondnum = 1 + R_exnum.nextInt(9);

            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++) {
                    if (blockS[i][j] == firstnum) {
                        blockS[i][j] = secondnum;
                        continue;
                    }

                    if (blockS[i][j] == secondnum) blockS[i][j] = firstnum;
                }
        }
        return blockS;
    }

    int[][] save() {
        if (gc == 0) blockc = generate();

        gc = 1;

        return blockc;
    }

    int[][] hide() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                blockh[i][j] = blockc[i][j];

        int row, column, hidingnum;

        hidingnum = 50 + R_num.nextInt(10);

        for (int i = 0; i < hidingnum; i++) {
            row = H_Rnum.nextInt(9);
            column = H_Rnum.nextInt(9);
            // System.out.println("\nrow,col = ("+row+","+column+")");
            blockh[row][column] = 0;
        }
        return blockh;
    }

}