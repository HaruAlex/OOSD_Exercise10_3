package arena;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Đại diện cho một Player.
 * Một Player có thể tham gia nhiều Tournament.
 */
public class Player {

    /** Tên người chơi. */
    private final String name;

    /** Danh sách Tournament mà Player tham gia. */
    private final Set<Tournament> tournaments;

    /**
     * Khởi tạo Player.
     *
     * @param name tên người chơi
     */
    public Player(String name) {
        this.name = Objects.requireNonNull(name);
        this.tournaments = new HashSet<>();
    }

    /**
     * Lấy tên người chơi.
     *
     * @return tên Player
     */
    public String getName() {
        return name;
    }

    /**
     * Lấy danh sách Tournament mà Player tham gia.
     *
     * @return danh sách Tournament không thể chỉnh sửa trực tiếp
     */
    public Set<Tournament> getTournaments() {
        return Collections.unmodifiableSet(tournaments);
    }

    /**
     * Thêm Tournament cho Player và cập nhật quan hệ
     * tương ứng ở phía Tournament.
     *
     * @param tournament Tournament cần tham gia
     */
    public void addTournament(Tournament tournament) {
        Objects.requireNonNull(tournament);

        if (tournaments.add(tournament)) {
            tournament.addPlayerInternal(this);
        }
    }

    /**
     * Xóa Tournament khỏi danh sách tham gia
     * và cập nhật quan hệ ở phía Tournament.
     *
     * @param tournament Tournament cần rời khỏi
     */
    public void removeTournament(Tournament tournament) {
        if (tournaments.remove(tournament)) {
            tournament.removePlayerInternal(this);
        }
    }
}
