package arena;

import java.util.Arrays;

/**
 * Chương trình minh họa cách sử dụng các lớp.
 */
public class Main {

    /**
     * Điểm bắt đầu chương trình.
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {

        // Khai báo thông tin các Tournament và Round.
        TournamentSpec tournament1 = new TournamentSpec(
            "Tournament A",
            Arrays.asList("Round 1", "Round 2")
        );

        TournamentSpec tournament2 = new TournamentSpec(
            "Tournament B",
            Arrays.asList("Round 1", "Round 2", "Round 3")
        );

        // Tạo League cùng các Tournament và Round.
        League league = new League(
            "League 2026",
            Arrays.asList(tournament1, tournament2)
        );

        // Tạo các Player.
        Player player1 = new Player("Player A");
        Player player2 = new Player("Player B");

        // Thiết lập quan hệ nhiều-nhiều.
        Tournament firstTournament = league.getTournaments().get(0);
        Tournament secondTournament = league.getTournaments().get(1);

        firstTournament.addPlayer(player1);
        firstTournament.addPlayer(player2);
        secondTournament.addPlayer(player1);

        // Kiểm tra quan hệ League - Tournament.
        System.out.println("League: " + league.getName());

        for (Tournament tournament : league.getTournaments()) {
            System.out.println("Tournament: " + tournament.getName());

            // Kiểm tra quan hệ Tournament - Round.
            for (Round round : tournament.getRounds()) {
                System.out.println("  Round: " + round.getName());
            }

            // Kiểm tra danh sách Player.
            for (Player player : tournament.getPlayers()) {
                System.out.println("  Player: " + player.getName());
            }
        }

        // Kiểm tra quan hệ ngược từ Player.
        System.out.println("\nTournament của Player A:");

        for (Tournament tournament : player1.getTournaments()) {
            System.out.println(tournament.getName());
        }

        // Xóa một quan hệ nhiều-nhiều.
        firstTournament.removePlayer(player1);

        System.out.println(
            "\nSố Tournament còn lại của Player A: "
            + player1.getTournaments().size()
        );
    }
}
