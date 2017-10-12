public abstract class AbstractPlayer {
    //метка игрока
    String dot;

    //ход игрока
    abstract boolean makeMove(int x, int y);

    //победил или нет
    abstract boolean win();

}

