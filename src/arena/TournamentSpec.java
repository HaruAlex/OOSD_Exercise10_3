package arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Lưu thông tin cần thiết để khởi tạo một Tournament
 * cùng danh sách các Round trực thuộc.
 */
public class TournamentSpec {

    /** Tên giải thi đấu. */
    private final String name;

    /** Danh sách tên các vòng thi đấu. */
    private final List<String> roundNames;

    /**
     * Khởi tạo thông tin giải đấu.
     *
     * @param name tên giải thi đấu
     * @param roundNames danh sách tên các vòng
     */
    public TournamentSpec(String name, List<String> roundNames) {
        this.name = Objects.requireNonNull(name);
        this.roundNames = Collections.unmodifiableList(
            new ArrayList<>(roundNames)
        );
    }

    /**
     * Lấy tên giải thi đấu.
     *
     * @return tên giải đấu
     */
    public String getName() {
        return name;
    }

    /**
     * Lấy danh sách tên các vòng.
     *
     * @return danh sách tên vòng thi đấu
     */
    public List<String> getRoundNames() {
        return roundNames;
    }
}
