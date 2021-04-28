//import java.util.Collections;
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
    void printPazzle(){
        System.out.print(coordinate.get(0));
        System.out.print(coordinate.get(1));
        System.out.println(coordinate.get(2));

        System.out.print(coordinate.get(7));
        System.out.print(coordinate.get(8));
        System.out.println(coordinate.get(3));

        System.out.print(coordinate.get(6));
        System.out.print(coordinate.get(5));
        System.out.println(coordinate.get(4));
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