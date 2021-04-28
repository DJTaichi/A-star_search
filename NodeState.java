public class NodeState {
    public Pazzle current;
    public NodeState former;
    public int cost;

    //初期値ありのコンストラクタ
    NodeState(Pazzle pazzle){
        current = pazzle;
        current.hn = current.h1nCalc();
    }

    //初期値なしのコンストラクタ
    NodeState(){

    }


    //cの数字と8を入れ替える関数
    public Pazzle swap(int c){

        Pazzle next = new Pazzle();

        int i ;

        for(i=0;i<9;i++){
            next.coordinate.add(current.coordinate.get(i));
        }

        //8の座標を取得
        int locateOf8 = next.coordinate.indexOf(8);
        
        //8の場所にcの値を代入
        next.coordinate.set(locateOf8, next.coordinate.get(c));

        //cの場所に8を代入
        next.coordinate.set(c, 8);

        return next;
    }

    boolean goalCheck(){
        int i;
        for(i=0;i<8;i++){
            if (current.coordinate.get(i) != i){
                return false;
            }
        }
        return true;
    }

}
