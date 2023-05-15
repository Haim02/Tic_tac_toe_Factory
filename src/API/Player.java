package API;

import com.objects.Board;
import com.objects.Mark;

public interface Player {

    void playTurn(Board board, Mark mark);
}
