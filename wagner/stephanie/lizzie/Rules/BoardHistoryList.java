package wagner.stephanie.lizzie.Rules;

/**
 * Linked list data structure to store board history
 */
public class BoardHistoryList {
    private BoardHistoryNode head;

    /**
     * Initialize a new board history list, whose first node is data
     *
     * @param data the data to be stored for the first entry
     */
    public BoardHistoryList(BoardData data) {
        head = new BoardHistoryNode(data);
    }

    /**
     * Add new data after head. Overwrites any data that may have been stored after head.
     *
     * @param data the data to add
     */
    public void add(BoardData data) {
        head = head.add(new BoardHistoryNode(data));
    }

    /**
     * moves the pointer to the left, returns the data stored there
     *
     * @return data of previous node
     */
    public BoardData previous() {
        if (head.previous() != null)
            head = head.previous();

        return head.getData();
    }

    /**
     * moves the pointer to the right, returns the data stored there
     *
     * @return the data of next node
     */
    public BoardData next() {
        if (head.next() != null)
            head = head.next();

        return head.getData();
    }

    /**
     * @return the data of the current node
     */
    public BoardData getData() {
        return head.getData();
    }

    public Stone[] getStones() {
        return head.getData().stones;
    }

    public int[] getLastMove() {
        return head.getData().lastMove;
    }

    public boolean isBlacksTurn() {
        return head.getData().blackToPlay;
    }

    public Zobrist getZobrist() {
        return head.getData().zobrist.clone();
    }

    /**
     * @param data the board position to check against superko
     * @return whether or not the given position violates the superko rule at the head's state
     */
    public boolean violatesSuperko(BoardData data) {
        BoardHistoryNode head = this.head;

        // check to see if this position has occurred before
        while (head.previous() != null) {
            // if two zobrist hashes are equal, and it's the same player to move, they are the same position
            if (data.zobrist.equals(head.getData().zobrist) && data.blackToPlay == head.getData().blackToPlay)
                return true;

            head = head.previous();
        }

        // no position matched this position, so it's valid
        return false;
    }
}
