import java.util.ArrayList;


class Pazzle{

    //座標（swap関数で簡単に入れ替えられる）
    ArrayList<Integer> coordinate = new ArrayList<Integer>();

    //スタートからのコスト
    int gn = 0;

    //ゴールまでの評価
    int hn = 0;

    //hnの計算
    int h1nCalc(){
        int count = 0;
        int i;
        for(i=0;i<8;i++){
            if(coordinate.get(i) != i){
                count++;
            }
        }

        return count;
    }

    int h2nCalc(){
        int distance=0;

        //0の距離
        switch (coordinate.indexOf(0)) {
            case 1,7:
                distance += 1;
                break;
            case 2,6,8:
                distance += 2;
                break;
            case 3,5:
                distance += 3;
                break;
            case 4:
                distance += 4;
                break; 
            default:
                break;
        }

        //1の距離
        switch (coordinate.indexOf(1)) {
            case 0,2,8:
                distance += 1;
                break;
            case 3,5,7:
                distance += 2;
                break;
            case 4,6:
                distance += 3;
                break;   
            default:
                break;
        }

        //2の距離
        switch (coordinate.indexOf(2)) {
            case 1,3:
                distance += 1;
                break;
            case 0,4,8:
                distance += 2;
                break;
            case 5,7:
                distance += 3;
                break;
            case 6:
                distance += 4;
                break;
            default:
                break;
        }

        //3の距離
        switch (coordinate.indexOf(3)) {
        case 2,4,8:
            distance += 1;
            break;
        case 1,5,7:
            distance += 2;
            break;
        case 0,6:
            distance += 3;
            break;
        default:
            break;
        }

        //4の距離
        switch (coordinate.indexOf(4)) {
        case 3,5:
            distance += 1;
            break;
        case 2,6,8:
            distance += 2;
            break;
        case 1, 7:
            distance += 3;
            break;
        case 0:
            distance += 4;
            break;
        default:
            break;
        }

        // 5の距離
        switch (coordinate.indexOf(5)) {
        case 4,6,8:
            distance += 1;
            break;
        case 1,3,7:
            distance += 2;
            break;
        case 0, 2:
            distance += 3;
            break;
        default:
            break;
        }

        // 6の距離
        switch (coordinate.indexOf(6)) {
        case 5,7:
            distance += 1;
            break;
        case 0,4,8:
            distance += 2;
            break;
        case 1, 3:
            distance += 3;
            break;
        case 2:
            distance += 4;
            break;
        default:
            break;
        }

        // 7の距離
        switch (coordinate.indexOf(7)) {
        case 0, 6, 8:
            distance += 1;
            break;
        case 1, 3, 5:
            distance += 2;
            break;
        case 2,4:
            distance += 3;
            break;
        default:
            break;
        }

        return distance;
    }

    void printPazzle(){
        System.out.print(coordinate.get(0) +1);
        System.out.print(coordinate.get(1) +1);
        System.out.println(coordinate.get(2) +1);

        System.out.print(coordinate.get(7) + 1);
        System.out.print(coordinate.get(8) + 1);
        System.out.println(coordinate.get(3) + 1);

        System.out.print(coordinate.get(6) + 1);
        System.out.print(coordinate.get(5) + 1);
        System.out.println(coordinate.get(4) + 1);
    }

    public int[] moveTo(){
        int[] data;

        switch (coordinate.indexOf(8)) {
            case 0:
                data = new int[]{1,7}; 
                break;

            case 1:
                data = new int[]{0,2,8};
                break;

            case 2:
                data = new int[]{1,3};
                break;

            case 3:
                data = new int[]{2,4,8};
                break;

            case 4:
                data = new int[]{3,5};
                break;

            case 5:
                data = new int[]{4,6,8};
                break;

            case 6:
                data = new int[]{5,7};
                break;

            case 7:
                data = new int[]{0,6,8};
                break;

            case 8:
                data = new int[]{1,3,5,7};
                break;
        
            default:
                data = new int[]{-1};
                break;
        }

        return data;

    }

}