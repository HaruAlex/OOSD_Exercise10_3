# Bài tập 10-3 – Mapping Associations to Collections

## 1. Mục tiêu bài tập

Áp dụng các kỹ thuật trong mục 10.4.2 – *Mapping Associations to Collections* của tài liệu OOSD để chuyển các mối quan hệ trong biểu đồ UML thành cấu trúc mã nguồn Java.

Bài tập giả định:
- Tất cả các association đều là quan hệ hai chiều (bidirectional).
- Các quan hệ aggregation không thay đổi sau khi mỗi đối tượng được tạo.
- Quan hệ aggregation phải được khởi tạo trong quá trình tạo đối tượng.

## 2. Phân tích biểu đồ UML

Biểu đồ gồm bốn lớp:

- `League`: Giải đấu.
- `Tournament`: Giải thi đấu.
- `Round`: Vòng thi đấu.
- `Player`: Người chơi.

### 2.1. League – Tournament

- Một `League` có thể chứa nhiều `Tournament`.
- Mỗi `Tournament` thuộc về đúng một `League`.
- Đây là quan hệ aggregation hai chiều.
- Quan hệ được thiết lập khi khởi tạo và không thay đổi sau đó.

### 2.2. Tournament – Round

- Một `Tournament` có nhiều `Round`.
- Mỗi `Round` thuộc về đúng một `Tournament`.
- Đây là quan hệ aggregation hai chiều.
- Quan hệ được thiết lập khi khởi tạo và không thay đổi sau đó.

### 2.3. Tournament – Player

- Một `Tournament` có thể có nhiều `Player`.
- Một `Player` có thể tham gia nhiều `Tournament`.
- Đây là association nhiều-nhiều (many-to-many).
- Quan hệ hai chiều và có thể thay đổi trong quá trình hoạt động.

## 3. Nguyên tắc chuyển UML sang Java

Java không biểu diễn trực tiếp khái niệm Association của UML. Vì vậy, các quan hệ được chuyển thành reference hoặc collection.

| Quan hệ | Cách biểu diễn |
|---|---|
| One-to-many | Một phía sử dụng collection, phía còn lại sử dụng reference |
| Many-to-many | Cả hai phía sử dụng collection |
| Bidirectional | Hai lớp cùng lưu tham chiếu đến đối tượng liên quan |
| Aggregation cố định | Khởi tạo trong constructor, không cung cấp phương thức thay đổi sau đó |

### 3.1. Đối với Aggregation

- `League` tạo các `Tournament`.
- `Tournament` tạo các `Round`.
- Đối tượng con nhận tham chiếu đến đối tượng cha thông qua constructor.
- Các thuộc tính quan hệ được khai báo `private final`.
- Không cung cấp phương thức thêm, xóa hoặc thay đổi quan hệ sau khi khởi tạo.

### 3.2. Đối với Association giữa Tournament và Player

- Hai lớp đều có collection để lưu các đối tượng liên quan.
- Cho phép thêm và xóa mối quan hệ.
- Khi thay đổi ở một phía, phía còn lại phải được cập nhật tương ứng.
- Sử dụng `Set` vì biểu đồ không thể hiện yêu cầu thứ tự và cần tránh liên kết trùng lặp.

**Lưu ý:** Figure 10-11 trong tài liệu sử dụng `List` cho quan hệ Tournament–Player vì ví dụ đó có ký hiệu `{ordered}`. Biểu đồ bài 10-3 không có ký hiệu này, nên lời giải sử dụng `Set`.

## 4. Thiết kế các lớp

| Lớp | Thuộc tính quản lý quan hệ | Vai trò |
|---|---|---|
| `League` | `List<Tournament>` | Quản lý các Tournament |
| `Tournament` | `League`, `List<Round>`, `Set<Player>` | Liên kết League, quản lý Round và Player |
| `Round` | `Tournament` | Tham chiếu Tournament sở hữu |
| `Player` | `Set<Tournament>` | Quản lý các Tournament tham gia |
| `TournamentSpec` | Tên Tournament, danh sách tên Round | Hỗ trợ khởi tạo cấu trúc aggregation |
| `Main` | Không có | Minh họa và kiểm tra hoạt động |

## 5. Tổ chức source code

Mã nguồn Java được tách riêng khỏi tài liệu báo cáo, theo cấu trúc:

```text
src/
└── arena/
    ├── League.java
    ├── Tournament.java
    ├── Round.java
    ├── Player.java
    ├── TournamentSpec.java
    └── Main.java
```

Mỗi lớp có JavaDoc comment mô tả lớp, thuộc tính và phương thức. Mã nguồn nằm trong thư mục `Bai_tap_10-3_Source_Code` và được đóng gói thành file ZIP riêng.

## 6. Giải thích cách hoạt động

### 6.1. Quan hệ League – Tournament

Khi tạo `League`, constructor duyệt danh sách thông tin giải đấu và tạo các `Tournament`. Mỗi `Tournament` nhận tham chiếu đến chính đối tượng `League` đang được tạo.

Kết quả là:
- `League` truy cập được danh sách `Tournament`.
- `Tournament` truy cập được `League` sở hữu nó.
- Quan hệ được thiết lập ngay khi khởi tạo và không có phương thức thay đổi sau đó.

### 6.2. Quan hệ Tournament – Round

Khi tạo `Tournament`, constructor tạo các `Round` trực thuộc. Mỗi `Round` nhận tham chiếu đến `Tournament` tương ứng.

Kết quả là:
- `Tournament` truy cập được các `Round`.
- `Round` truy cập được `Tournament` chứa nó.
- Quan hệ aggregation được cố định sau khi khởi tạo.

### 6.3. Quan hệ Tournament – Player

Hai lớp `Tournament` và `Player` đều lưu collection để quản lý quan hệ.

Khi thêm một `Player` vào `Tournament`, danh sách `Tournament` của `Player` cũng được cập nhật. Khi xóa quan hệ, collection ở cả hai phía đều được cập nhật.

Điều kiện kiểm tra trước khi thêm hoặc xóa giúp tránh liên kết trùng lặp và ngăn hai phương thức gọi qua lại vô hạn.

## 7. Tổng kết

Các nguyên tắc được áp dụng:

1. Sử dụng collection để biểu diễn phía có bội số nhiều.
2. Sử dụng reference để biểu diễn phía có bội số một.
3. Sử dụng `private` để đảm bảo đóng gói.
4. Sử dụng `final` và constructor để cố định các quan hệ aggregation.
5. Sử dụng phương thức thêm, xóa để duy trì tính nhất quán của association hai chiều.
6. Sử dụng `Set` cho quan hệ nhiều-nhiều không yêu cầu thứ tự và không cho phép liên kết trùng lặp.

## 8. Kết luận

Bài tập 10-3 giúp vận dụng kỹ thuật chuyển đổi các mối quan hệ UML thành reference và collection trong Java. Qua đó, có thể quản lý các quan hệ 1-n, n-n và đảm bảo tính nhất quán giữa hai phía của association. Bài tập cũng làm rõ sự khác biệt giữa aggregation cố định sau khi khởi tạo và association có thể thay đổi trong quá trình hoạt động.