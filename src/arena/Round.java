package arena;

import java.util.Objects;

/**
 * Đại diện cho một Round trong Tournament.
 */
public class Round {

    /** Tên vòng thi đấu. */
    private final String name;

    /**
     * Tournament chứa Round này.
     * Tham chiếu được khởi tạo một lần trong constructor.
     */
    private final Tournament tournament;

    /**
     * Khởi tạo Round và liên kết với Tournament.
     *
     * @param name tên Round
     * @param tournament Tournament sở hữu Round
     */
    Round(String name, Tournament tournament) {
        this.name = Objects.requireNonNull(name);
        this.tournament = Objects.requireNonNull(tournament);
    }

    /**
     * Lấy tên Round.
     *
     * @return tên Round
     */
    public String getName() {
        return name;
    }

    /**
     * Lấy Tournament chứa Round.
     *
     * @return Tournament tương ứng
     */
    public Tournament getTournament() {
        return tournament;
    }
}
