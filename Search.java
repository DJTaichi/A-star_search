import java.util.Collections;
import java.util.ArrayList;

class Search{

    public static void main(String args[]){

        //openリストとcloseリストを宣言
        ArrayList<NodeState> open = new ArrayList<>();
        ArrayList<NodeState> close = new ArrayList<>();

        Pazzle firstPazzle = new Pazzle();

        //初期配置配置を決める
        ArrayList<Integer> numbers = new ArrayList<>();
        int number;
        for (number=0;number<9;number++){
            numbers.add(number);
        }
        Collections.shuffle(numbers);

        for(number=0;number<9;number++){
            firstPazzle.coordinate.add(numbers.get(number));
        }

        firstPazzle.printPazzle();

        NodeState firstNode = new NodeState(firstPazzle);

        firstNode.cost = firstNode.current.h2nCalc();

        open.add(firstNode);
        
        while(true) {

            //System.out.println("-----------------------------------");

            //オープンリストが空なら終了（一応）
            if (open.size() == 0){  
                return;
            }

            int compare = 100;
            int min = 0;

            //オープンリストからコスト最小を選ぶ
            int i;
            int j;
            for(i=0;i<open.size();i++){
                if(compare > open.get(i).cost){
                    compare = open.get(i).cost;
                    min = i;
                }
            }
            //System.out.println("展開するノード");
            //open.get(min).current.printPazzle();
            //System.out.println("-----------------------------------");
            //System.out.print("現在のopenリストの数");
            //System.out.println(open.size());


            //子ノードの集合を宣言、要素に追加
            ArrayList<NodeState> children = new ArrayList<>();
            for(i=0;i<open.get(min).current.moveTo().length;i++){

                NodeState child = new NodeState();
                child.former = open.get(min);
                child.current = open.get(min).swap(open.get(min).current.moveTo()[i]);
                child.current.gn = child.former.current.gn + 1;
                child.current.hn = child.current.h2nCalc();
                child.cost = child.current.gn + child.current.hn;
                children.add(child);
            }

            //親ノードをcloseリストに追加
            close.add(open.get(min));
            open.remove(min);
            //System.out.print("現在のclosedリストのサイズ: ");
            //System.out.println(close.size());

            //小ノードのパズルをプリント
            //for(i=0;i<children.size();i++){
                //children.get(i).current.printPazzle();
                //System.out.println();

                //h(n)とg(n)をプリント
                //System.out.print("g(n)");
                //System.out.println(children.get(i).current.gn);
                //System.out.print("h(n)");
                //System.out.println(children.get(i).current.hn);
                //System.out.println();
            //}
            
            //終状態が小ノードに含まれていたら終了
            for (i = 0; i < children.size(); i++) {
                if(children.get(i).goalCheck()){
                    System.out.print("最初のh(n):");
                    System.out.println(firstNode.cost);

                    System.out.println("===========================");

                    children.get(i).current.printPazzle();
                    System.out.println();
                    System.out.println("↑");
                    System.out.println();

                    //答えのArrayList作る
                    ArrayList<NodeState> answers = new ArrayList<>();
                    answers.add(children.get(i).former);

                    for(j=0;j<children.get(i).current.gn - 1 ;j++){
                        answers.add(answers.get(j).former);
                    }
                    //答えのプリント
                    for(j=0;j<answers.size();j++){
                        answers.get(j).current.printPazzle();
                        System.out.println();
                        System.out.println("↑");
                        System.out.println();
                    }

                    System.out.println("===========================");
                    return;
                }
            }
            //closeリストに含まれているnを子供ノードの集合から削除する
            for(i=0;i<close.size();i++){
                for(j=0;j<children.size();j++){
                    if(close.get(i).current.coordinate.equals(children.get(j).current.coordinate)){
                        children.remove(j);
                        //System.out.println("closedリストに含まれていたため削除しました。");
                    }
                }
            }

            //openサイズを記憶
            int sizeOpen = open.size();


            //openリストに追加する。但し、コストが低いものがすでに存在していたら何もしない。
            for (i = 0; i < sizeOpen; i++) {
                for (j = 0; j < children.size(); j++) {
                    if (open.get(i).current.coordinate.equals(children.get(j).current.coordinate)) {
                        //コストの低い方を残す
                        if(open.get(i).cost <= children.get(j).cost){
                            //System.out.println("既存のノードのコストが勝ちました");
                            continue;
                        }else{
                            //System.out.println("最新のノードのコストが勝ちました");
                            open.remove(i);
                            //System.out.println("openに追加しました1");
                            open.add(children.get(j));
                        }
                    }
                }
            }

            sizeOpen = open.size();


            //全く新しいノードが見つかった時openに追加
            for(j=0;j<children.size();j++){
                int check = 1;
                for(i=0;i<sizeOpen;i++){
                    if(open.get(i).current.coordinate.equals(children.get(j).current.coordinate)){
                        check *= 0;
                    }
                }
                if(check == 1){
                    //System.out.println("openに追加しました2");
                    open.add(children.get(j));
                }
            }

            //closedリストに含まれるnに対して、今回の方がコストがよければnをclosedリストから削除して、今回のnをopenリストに入れる
            for (i = 0; i < close.size(); i++) {
                for (j = 0; j < children.size(); j++) {
                    if (close.get(i).current.coordinate.equals(children.get(j).current.coordinate)) {
                        if(close.get(i).cost > children.get(j).cost){
                            close.remove(i);
                            //System.out.println("openに追加しました3");
                            open.add(children.get(j));
                        }
                    }
                }
            }
            if(open.size() > 10000) {
                System.out.println("openリストが10000を超えました。");
                return;
            }
        }
    }
}