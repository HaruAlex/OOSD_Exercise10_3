package arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Đại diện cho một Tournament.
 * Tournament thuộc một League, chứa nhiều Round
 * và có thể liên kết với nhiều Player.
 */
public class Tournament {

    /** Tên Tournament. */
    private final String name;

    /**
     * League sở hữu Tournament.
     * Tham chiếu được khởi tạo trong constructor và không thay đổi.
     */
    private final League league;

    /**
     * Danh sách Round thuộc Tournament.
     * Được khởi tạo trong constructor và không thay đổi.
     */
    private final List<Round> rounds;

    /** Danh sách Player tham gia Tournament. */
    private final Set<Player> players;

    /**
     * Khởi tạo Tournament và các Round trực thuộc.
     *
     * @param name tên Tournament
     * @param league League sở hữu Tournament
     * @param roundNames danh sách tên các Round
     */
    Tournament(
        String name,
        League league,
        List<String> roundNames
    ) {
        this.name = Objects.requireNonNull(name);
        this.league = Objects.requireNonNull(league);

        List<Round> created = new ArrayList<>();

        // Khởi tạo các Round ngay khi tạo Tournament.
        for (String roundName : roundNames) {
            created.add(new Round(roundName, this));
        }

        this.rounds = Collections.unmodifiableList(created);
        this.players = new HashSet<>();
    }

    /**
     * Lấy tên Tournament.
     *
     * @return tên Tournament
     */
    public String getName() {
        return name;
    }

    /**
     * Lấy League sở hữu Tournament.
     *
     * @return League tương ứng
     */
    public League getLeague() {
        return league;
    }

    /**
     * Lấy danh sách Round.
     *
     * @return danh sách Round không thể chỉnh sửa
     */
    public List<Round> getRounds() {
        return rounds;
    }

    /**
     * Lấy danh sách Player tham gia.
     *
     * @return danh sách Player không thể chỉnh sửa trực tiếp
     */
    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    /**
     * Thêm Player vào Tournament và cập nhật quan hệ
     * tương ứng ở phía Player.
     *
     * @param player Player cần thêm
     */
    public void addPlayer(Player player) {
        Objects.requireNonNull(player);

        if (players.add(player)) {
            player.addTournament(this);
        }
    }

    /**
     * Xóa Player khỏi Tournament và cập nhật quan hệ
     * tương ứng ở phía Player.
     *
     * @param player Player cần xóa
     */
    public void removePlayer(Player player) {
        if (players.remove(player)) {
            player.removeTournament(this);
        }
    }

    /**
     * Phương thức nội bộ để cập nhật quan hệ
     * khi Player thực hiện thêm Tournament.
     *
     * @param player Player cần liên kết
     */
    void addPlayerInternal(Player player) {
        players.add(player);
    }

    /**
     * Phương thức nội bộ để cập nhật quan hệ
     * khi Player thực hiện xóa Tournament.
     *
     * @param player Player cần hủy liên kết
     */
    void removePlayerInternal(Player player) {
        players.remove(player);
    }
}
