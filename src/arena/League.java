package arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Đại diện cho một League.
 * Một League chứa nhiều Tournament.
 */
public class League {

    /** Tên giải đấu lớn. */
    private final String name;

    /**
     * Danh sách Tournament thuộc League.
     * Danh sách được khởi tạo một lần và không thay đổi.
     */
    private final List<Tournament> tournaments;

    /**
     * Khởi tạo League và toàn bộ Tournament trực thuộc.
     *
     * @param name tên League
     * @param specs thông tin các Tournament cần tạo
     */
    public League(String name, List<TournamentSpec> specs) {
        this.name = Objects.requireNonNull(name);

        List<Tournament> created = new ArrayList<>();

        // Khởi tạo các Tournament ngay khi tạo League.
        for (TournamentSpec spec : specs) {
            created.add(new Tournament(
                spec.getName(),
                this,
                spec.getRoundNames()
            ));
        }

        this.tournaments = Collections.unmodifiableList(created);
    }

    /**
     * Lấy tên League.
     *
     * @return tên League
     */
    public String getName() {
        return name;
    }

    /**
     * Lấy danh sách Tournament.
     *
     * @return danh sách Tournament không thể chỉnh sửa
     */
    public List<Tournament> getTournaments() {
        return tournaments;
    }
}
