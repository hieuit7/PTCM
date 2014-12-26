PTCM
====

Passenger Car Terminal Manager Project
Tên đề tài: Quản lý bến xe khách.
====
1. Mô tả hệ thống:
====
- Yêu cầu: 
  Một bến xe khách có các tuyến xe đi các bến xe khách khác. Mỗi chuyến xe có thời gian chạy được phân chia sẵn. Bến xe có nhiều xe, mỗi xe có thông tin về xe như mã số, bảng số, số lượng hành khách, chủ xe, tài xế, tiếp viên, thông tin kỹ thuật của xe. Mỗi chuyến xe có nhiều xe tham gia chạy. Thời gian chạy và xe chạy dựa trên bảng phân công. Bảng phân công bao gồm: Số thứ tự, thời gian xuất bến, số xe, tài xế, tiếp viên,... Trên hành trình, xe có thể dừng lại ở một địa điểm bất kỳ, hoặc cố định cho trước (địa điểm này có thể nằm trong hệ thống hoặc không nằm trong hệ thống). Thời gian đổ có thể tùy vào từng chuyến xe được quy định. Việc vào ra bến xe hoặc dừng đổ (ở các trạm có hoặc không có trong hệ thống) phải được thông báo với hệ thống. Sau khi xe đã vào bến, thời gian lưu xe ở bến sẽ được hệ thống cung cấp. Nếu ở các trạm dừng đổ, thời gian sẽ phụ thuộc vào chuyến xe. Ở trạm cuối, các xe ở lại tùy thuộc vào bảng phân công chuyến xe.
  Trên mỗi chuyến xe, ngoài tài xế, tiếp việc, camera giám sát, mỗi xe còn được trang bị thiết bị liên lạc với hệ thống (3G, Internet,..). Thiết bị dùng để thông báo với hệ thống thời gian dừng đổ, hành trình, tình trạng xe.
  Hệ thống sẽ tiếp nhận các tính hiệu của các xe thông báo về tình trạng, hành trình thời gian dừng đổ, vào trạm. Phân phối và kiểm soát thời gian của các chuyến xe, quản lý thông tin các chuyến xe, phân công các chuyến xe đi các bến khác.
  Hệ thống bao gồm máy chủ lưu trữ thông tin của toàn bộ cacis chuyến xe. Ở mỗi trạm của các chuyến xe (bao gồm cả trạm dừng) cũng có các máy chủ thông báo cho người quản lý ở các trạm dừng biết các thông tin về xe sắp vào trạm. Các máy chủ ở các trạm đổ liên lạc trực tiếp với máy chủ trung tâm hoặc liên lạc với các xe trên tuyến.
  
====
2. Phân tích yêu cầu hệ thống:
====
- Yêu cầu chức năng:
  - Đăng nhập vào hệ thống quản lý.
  - Lưu trữ thông tin các bến xe tập trung trên internet.
  - Lập bảng phân công các chuyến xe.
  - Tiếp nhận các yêu cầu của các chuyến xe.
  - Xử lý thông tin giữa các trạm kết nối với nhau.
  - Phân phối lịch trình của các xe.
  - Xem thông tin của các trạm dừng.
  - ...
- Yêu cầu phi chức năng:
  - Các thông tin giao tiếp cần được bảo mật.
  - Dữ liệu gửi đi phải nhanh chóng và tốn ít dung lượng.
  - Đảm bảo các kết nối giữa các trạm với các xe.
  - ...
====
3. Giải thích thuật ngữ sử dụng:
====
  1. Máy chủ: Là máy trung tâm, xử lý tất cả cá thông tin của hệ thống. Máy chủ sẽ có địa chỉ cụ thể trên internet.
  2. Máy trạm: Là các máy ở các bến xe khách và các bến dừng xe. Các máy trạm sẽ có địa chỉ cụ thể trên internet.
  3. Client: Là các thiết bị kết nối ở trên các phương tiện giao thông. Mỗi client sẽ có một định danh đi kèm với các xem khách.
  4. Cổng dịch vụ máy chủ: Là cổng tiếp nhận các thông tin của máy chủ khi trao đổi với các máy trạm và client.
  5. Gói tin: Là gói dữ liệu của hệ thống liên lạc với nhau. Có hai loại gói tin là gói tin dữ liệu và gói tin điều khiển.
  6. Bộ kết nối: Là bộ phận xử lý kiểm tra tín hiệu kết nối tới trung tâm hay các máy trạm và client.
  7. 
====
4. Hoạt động hệ thống
====
  1. Máy chủ:
    - Lập lịch trình cho từng chuyến xe.
    - Phân phối lịch trình cho các xe.
    - Ra lệnh vào ra trạm cho các xe.
    - Trả lời truy vấn thông tin khi có truy vấn.
  2. Máy trạm:
    - Truy vấn thông tin lịch trình của các xe.
    - Thông báo vào ra trạm.
    - Quản lý hành khách vào ra các trạm.
  3. Client:
    - Thông báo với hệ thống về việc dừng đổ, tình trạng xe và lịch trình.
    - Tiếp nhận thông tin điều khiển.
    - Thông báo số lượng khách trên xe. Khách lên xuống xe.
  4. Mô hình hoạt động:
  
    Data                Server                                                        ________________________
    [--]                |-----|                                                       |__|__|__|__|__|__|__|__|
    [--]<-------------->|-----|---------------|               |-----------------------|___@______________@____|
    [--]                |-----|               |               |
                                              |               |                               Client
                          ( --------^-----^---|-------^-------^-----)
                        (         INTERNET                            )
                          (                                             )
                            (                                         )
                              (                                     )
                                (_________________________________)
                                    |                         |
            |---|                   |                         |                       ________________________
      ____  |---|                   |                         |                       |__|__|__|__|__|__|__|__|
      |__|__|---|___________________|                         |_______________________|___@______________@____|
        |
      =====
        Host                                                                                Client

===
5. Hệ thống liên lạc với nhau:
===
