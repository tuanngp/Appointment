-- Seed data for SA_PRODUCT_CATEGORY (Opportunity Categories)
INSERT INTO sa_product_category (category_id, category_code, category_level, category_name, category_short_name,
                                 description, effect_date, expire_date, icon, is_leaf, order_no, parent_id, status)
VALUES (1, 'INSURANCE', 1, 'Bảo hiểm', 'Bảo hiểm', 'Các sản phẩm bảo hiểm', '2024-01-01', null, 'insurance-icon', 'Y',
        1, null, 'A'),
       (2, 'CARD_SERVICES', 1, 'Dịch vụ thẻ', 'Dịch vụ thẻ', 'Các dịch vụ liên quan đến thẻ', '2024-01-01', null,
        'card-icon', 'Y', 2, null, 'A'),
       (3, 'SAVINGS', 1, 'Tiết kiệm', 'Tiết kiệm', 'Các sản phẩm tiết kiệm', '2024-01-01', null, 'savings-icon', 'Y', 3,
        null, 'A'),
       (4, 'LOANS', 1, 'Vay', 'Vay', 'Các sản phẩm cho vay', '2024-01-01', null, 'loan-icon', 'Y', 4, null, 'A');

-- Seed data for SA_PRODUCT
INSERT INTO sa_product (id, category_code, category_id, category_name, created_at, created_by, description,
                        product_code, product_name, product_type, status, updated_at, updated_by)
VALUES (1, 'INSURANCE', 1, 'Bảo hiểm', '2024-01-01 00:00:00', 1, 'Bảo hiểm nhân thọ', 'INS001', 'Bảo hiểm nhân thọ',
        'MAIN', 'A', '2024-01-01 00:00:00', 1),
       (2, 'INSURANCE', 1, 'Bảo hiểm', '2024-01-01 00:00:00', 1, 'Bảo hiểm sức khỏe', 'INS002', 'Bảo hiểm sức khỏe',
        'MAIN', 'A', '2024-01-01 00:00:00', 1),
       (3, 'CARD_SERVICES', 2, 'Dịch vụ thẻ', '2024-01-01 00:00:00', 1, 'Thẻ tín dụng', 'CARD001', 'Thẻ tín dụng',
        'MAIN', 'A', '2024-01-01 00:00:00', 1),
       (4, 'CARD_SERVICES', 2, 'Dịch vụ thẻ', '2024-01-01 00:00:00', 1, 'Thẻ ghi nợ', 'CARD002', 'Thẻ ghi nợ', 'MAIN',
        'A', '2024-01-01 00:00:00', 1),
       (5, 'SAVINGS', 3, 'Tiết kiệm', '2024-01-01 00:00:00', 1, 'Tiết kiệm có kỳ hạn', 'SAV001', 'Tiết kiệm có kỳ hạn',
        'MAIN', 'A', '2024-01-01 00:00:00', 1),
       (6, 'SAVINGS', 3, 'Tiết kiệm', '2024-01-01 00:00:00', 1, 'Tiết kiệm không kỳ hạn', 'SAV002',
        'Tiết kiệm không kỳ hạn', 'MAIN', 'A', '2024-01-01 00:00:00', 1),
       (7, 'LOANS', 4, 'Vay', '2024-01-01 00:00:00', 1, 'Vay mua nhà', 'LOAN001', 'Vay mua nhà', 'MAIN', 'A',
        '2024-01-01 00:00:00', 1),
       (8, 'LOANS', 4, 'Vay', '2024-01-01 00:00:00', 1, 'Vay tiêu dùng', 'LOAN002', 'Vay tiêu dùng', 'MAIN', 'A',
        '2024-01-01 00:00:00', 1);

-- Seed data for SA_STAGES (Deal stages)
INSERT INTO sa_stages (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, description, name,
                       position, probability, status)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Tiếp cận khách hàng', 'Tiếp cận', 1, 10.00,
        'A'),
       (2, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Tạo hồ sơ khách hàng', 'Tạo hồ sơ', 2,
        30.00, 'A'),
       (3, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Chờ phê duyệt', 'Chờ phê duyệt', 3, 60.00,
        'A'),
       (4, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Kết quả', 'Kết quả', 4, 100.00, 'A');

-- Seed data for SA_PIPELINES
INSERT INTO sa_pipelines (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, description, name,
                          position, product_id, status)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Pipeline cho bảo hiểm', 'Bảo hiểm Pipeline',
        1, 1, 'A'),
       (2, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Pipeline cho dịch vụ thẻ', 'Thẻ Pipeline',
        2, 3, 'A'),
       (3, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Pipeline cho tiết kiệm',
        'Tiết kiệm Pipeline', 3, 5, 'A'),
       (4, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Pipeline cho vay', 'Vay Pipeline', 4, 7,
        'A');

-- Seed data for SA_PIPELINE_STAGES
INSERT INTO sa_pipeline_stages (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, pipeline_id,
                                position, stage_id, status)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 1, 1, 1, 'A'),
       (2, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 1, 2, 2, 'A'),
       (3, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 1, 3, 3, 'A'),
       (4, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 1, 4, 4, 'A'),
       (5, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 2, 1, 1, 'A'),
       (6, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 2, 2, 2, 'A'),
       (7, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 2, 3, 3, 'A'),
       (8, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 2, 4, 4, 'A'),
       (9, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 3, 1, 1, 'A'),
       (10, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 3, 2, 2, 'A'),
       (11, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 3, 3, 3, 'A'),
       (12, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 3, 4, 4, 'A'),
       (13, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 4, 1, 1, 'A'),
       (14, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 4, 2, 2, 'A'),
       (15, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 4, 3, 3, 'A'),
       (16, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 4, 4, 4, 'A');

-- Seed data for SA_CUSTOMER
INSERT INTO sa_customer (customer_id, address_line, approve_status, assets_ids, auth_stat, avatar,
                         birthday, branch_code, branch_id, business, checker_date, checker_id,
                         checker_type, cif_no, cust_class_id, cust_type, customer_job, customer_no,
                         customer_status, date_of_issue, description, email, email_2, email_3,
                         end_by, end_date, exdate_of_issue, facebook, first_name, full_address,
                         full_name, gender, home_phone, home_town, identify_by, identify_channel,
                         identify_id, identify_type, income, init_date, invite_by, is_lead,
                         is_merged, last_name, latitude, longitude, maker_date, maker_id,
                         maker_type, marital_status, mb_user_id, merge_customer_code,
                         merge_customer_id, merge_customer_name, middle_name, mobile_phone,
                         mobile_phone_2, mobile_phone_3, mod_no, nationality_id, parent_no,
                         partner_code, place_id, place_of_issue, preferred_topic_ids, quit_reason,
                         register_by, root_parent_no, sign1, sign2, staff_id, staff_no, status,
                         trans_status, unique_id, unique_image_back, unique_image_front,
                         unique_value, viber, whataps, zalo)
VALUES
-- Customer 1
(1, '123 Main St', 'A', '1,2', 'Y', 'https://example.com/avatar1.jpg',
 '1990-01-01', 'HCM01', 101, 'IT', '2024-06-01', 'checker001',
 'A', 'CIF123456', 1, 1, 'Engineer', 'CUS123456',
 'A', '2010-01-01', 'Test customer 1', 'test1@example.com', NULL, NULL,
 NULL, NULL, '2009-12-01', 'fb.com/test1', 'An', '123 Main St, HCM',
 'Nguyen Van An', 'M', '02812345678', 'Ha Noi', 'admin', 'web',
 10000000001, 'ID', '20M', '2023-01-01', 'inv001', 'true',
 'false', 'An', 10.7769, 106.7009, '2024-06-01', 'maker001',
 'USER', 'S', 1001, NULL,
 NULL, NULL, 'Van', '0909123456',
 NULL, NULL, 1, 704, NULL,
 'PTN001', 1, 'HCM', '1,2,3', NULL,
 'register001', NULL, NULL, NULL, 2001, 'ST001', 'A',
 1, 9999, 'back1.jpg', 'front1.jpg',
 'UV001', 'viber1', 'whatsapp1', 'zalo1'),

-- Customer 2
(2, '456 Side St', 'P', '3,4', 'N', 'https://example.com/avatar2.jpg',
 '1985-05-15', 'HN02', 102, 'Finance', '2024-05-15', 'checker002',
 'B', 'CIF654321', 2, 2, 'Accountant', 'CUS654321',
 'I', '2012-03-10', 'Test customer 2', 'test2@example.com', 'alt2@example.com', NULL,
 'admin', '2024-06-30', '2011-03-10', 'fb.com/test2', 'Binh', '456 Side St, HN',
 'Tran Thi Binh', 'F', '02823456789', 'Da Nang', 'system', 'mobile',
 10000000002, 'CCCD', '30M', '2024-01-01', 'inv002', 'false',
 'true', 'Binh', 21.0285, 105.8542, '2024-05-15', 'maker002',
 'SYS', 'M', 1002, 'MCC002',
 1001, 'Nguyen Van An', 'Thi', '0912233445',
 NULL, NULL, 2, 840, NULL,
 'PTN002', 2, 'HN', '2,3,4', 'Not interested',
 'register002', NULL, NULL, NULL, 2002, 'ST002', 'I',
 2, 9998, 'back2.jpg', 'front2.jpg',
 'UV002', 'viber2', 'whatsapp2', 'zalo2'),

-- Customer 3
(3, '789 New Rd', 'A', '5', 'Y', 'https://example.com/avatar3.jpg',
 '2000-12-31', 'DN03', 103, 'Education', NULL, NULL,
 NULL, 'CIF987654', 3, 3, 'Teacher', 'CUS987654',
 'A', '2020-01-01', 'Test customer 3', 'test3@example.com', NULL, 'backup3@example.com',
 NULL, NULL, '2020-01-01', 'fb.com/test3', 'Cuong', '789 New Rd, DN',
 'Le Van Cuong', 'M', '02834567890', 'Hue', 'user', 'app',
 10000000003, 'PASSPORT', '15M', '2020-12-31', 'inv003', 'false',
 'false', 'Cuong', 16.0471, 108.2062, '2020-01-01', 'maker003',
 'CLI', 'S', 1003, NULL,
 NULL, NULL, 'Van', '0933445566',
 NULL, NULL, 3, 458, NULL,
 'PTN003', 3, 'DN', '4,5', NULL,
 'register003', NULL, NULL, NULL, 2003, 'ST003', 'A',
 3, 9997, 'back3.jpg', 'front3.jpg',
 'UV003', 'viber3', 'whatsapp3', 'zalo3');

-- Seed data for SA_ADDRESS_BOOK
INSERT INTO sa_address_book (id, address_line, cust_id, full_address, latitude, longitude, order_no, place_id, status,
                             title)
VALUES (1, '123 Nguyễn Văn Cừ', 1, '123 Nguyễn Văn Cừ, Phường Nguyễn Cư Trinh, Quận 1, TP.HCM', 10776000, 106695000, 1,
        1, 'A', 'Địa chỉ nhà'),
       (2, '456 Lê Lợi', 2, '456 Lê Lợi, Phường 8, Quận 3, TP.HCM', 10776500, 106695500, 1, 2, 'A', 'Địa chỉ nhà'),
       (3, '789 Hai Bà Trưng', 3, '789 Hai Bà Trưng, Phường Tân Định, Quận 1, TP.HCM', 10777000, 106696000, 1, 3, 'A',
        'Địa chỉ nhà');

-- Seed data for SA_STAGES (Sales Stages)
INSERT INTO sa_stages (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, name, position, probability, description, status)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Tiếp cận', 1, 10.00, 'Giai đoạn tiếp cận khách hàng', 'A'),
       (2, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Tạo hồ sơ', 2, 30.00, 'Giai đoạn tạo hồ sơ khách hàng', 'A'),
       (3, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Chờ phê duyệt', 3, 60.00, 'Giai đoạn chờ phê duyệt', 'A'),
       (4, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Hoàn thành', 4, 100.00, 'Giai đoạn hoàn thành deal', 'A');

-- Seed data for SA_PIPELINES (Sales Pipelines)
INSERT INTO sa_pipelines (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, name, position, description, product_id, status)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Pipeline Bảo hiểm', 1, 'Quy trình bán hàng cho sản phẩm bảo hiểm', 1, 'A'),
       (2, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Pipeline Thẻ tín dụng', 2, 'Quy trình bán hàng cho sản phẩm thẻ tín dụng', 3, 'A'),
       (3, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Pipeline Tiết kiệm', 3, 'Quy trình bán hàng cho sản phẩm tiết kiệm', 5, 'A'),
       (4, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Pipeline Vay', 4, 'Quy trình bán hàng cho sản phẩm vay', 7, 'A');

-- Seed data for SA_PIPELINE_STAGES (Pipeline Stage Mapping)
INSERT INTO sa_pipeline_stages (id, created_at, created_by, updated_at, updated_by, pipeline_id, stage_id, position, status)
VALUES
-- Pipeline Bảo hiểm (ID: 1)
(1, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 1, 1, 1, 'A'),
(2, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 1, 2, 2, 'A'),
(3, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 1, 3, 3, 'A'),
(4, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 1, 4, 4, 'A'),
-- Pipeline Thẻ tín dụng (ID: 2)
(5, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 2, 1, 1, 'A'),
(6, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 2, 2, 2, 'A'),
(7, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 2, 3, 3, 'A'),
(8, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 2, 4, 4, 'A'),
-- Pipeline Tiết kiệm (ID: 3)
(9, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 3, 1, 1, 'A'),
(10, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 3, 2, 2, 'A'),
(11, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 3, 3, 3, 'A'),
(12, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 3, 4, 4, 'A'),
-- Pipeline Vay (ID: 4)
(13, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 4, 1, 1, 'A'),
(14, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 4, 2, 2, 'A'),
(15, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 4, 3, 3, 'A'),
(16, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1, 4, 4, 4, 'A');

-- Seed data for SA_DEAL (Opportunities)
INSERT INTO sa_deal (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, branch_code, branch_id,
                     branch_name, close_date, contact_full_name, contact_id, current_stage_id, cust_code,
                     cust_full_name, cust_id, deal_status, deal_value, description, name, pipeline_id, position,
                     probability, product_id, staff_full_name, staff_id, staff_no, start_date, status, visibility_type)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'HN001', 1, 'Chi nhánh Hà Nội', null,
        'Nguyễn Văn A', 1, 1, 'CUST001', 'Nguyễn Văn A', 1, 'O', 50000000, 'Cơ hội bảo hiểm nhân thọ',
        'Bảo hiểm nhân thọ - Nguyễn Văn A', 1, 1, 10.00, 1, 'Nhân viên A', 1, 'STAFF001', '2024-01-01', 1, 'P'),
       (2, '2024-01-02 00:00:00', 1, null, null, '2024-01-02 00:00:00', 1, 'HN001', 1, 'Chi nhánh Hà Nội', null,
        'Trần Thị B', 2, 2, 'CUST002', 'Trần Thị B', 2, 'O', 30000000, 'Cơ hội thẻ tín dụng',
        'Thẻ tín dụng - Trần Thị B', 2, 2, 30.00, 3, 'Nhân viên A', 1, 'STAFF001', '2024-01-02', 1, 'P'),
       (3, '2024-01-03 00:00:00', 2, null, null, '2024-01-03 00:00:00', 2, 'HN001', 1, 'Chi nhánh Hà Nội', null,
        'Lê Văn C', 3, 3, 'CUST003', 'Lê Văn C', 3, 'O', 100000000, 'Cơ hội vay mua nhà', 'Vay mua nhà - Lê Văn C', 4,
        3, 60.00, 7, 'Nhân viên B', 2, 'STAFF002', '2024-01-03', 1, 'P');

-- Seed data for SA_DEAL_STAGES
INSERT INTO sa_deal_stages (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, begin_date,
                            deal_id, description, ds_status, duration, end_date, pipeline_id, ps_id, stage_id, status)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, '2024-01-01 00:00:00', 1,
        'Bắt đầu tiếp cận khách hàng', 'A', null, null, 1, 1, 1, 1),
       (2, '2024-01-02 00:00:00', 1, null, null, '2024-01-02 00:00:00', 1, '2024-01-02 00:00:00', 2,
        'Đã tạo hồ sơ khách hàng', 'A', 1440, '2024-01-03 00:00:00', 2, 6, 2, 1),
       (3, '2024-01-03 00:00:00', 2, null, null, '2024-01-03 00:00:00', 2, '2024-01-03 00:00:00', 3,
        'Đang chờ phê duyệt', 'A', null, null, 4, 15, 3, 1);

-- Seed data for SA_CALENDAR
INSERT INTO sa_calendar (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, address_note,
                         assigned_staff_id, assigned_staff_no, cust_id, description, event_date, followed_staff_id,
                         followed_staff_no, object_id, priority, prod_note, status, title, type)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, '123 Nguyễn Văn Cừ, Quận 1', 1, 'STAFF001',
        1, 'Cuộc hẹn tư vấn bảo hiểm', '2024-01-05', 1, 'STAFF001', 1, 1, 'Tư vấn sản phẩm bảo hiểm nhân thọ', 'A',
        'Tư vấn bảo hiểm - Nguyễn Văn A', 1),
       (2, '2024-01-02 00:00:00', 1, null, null, '2024-01-02 00:00:00', 1, '456 Lê Lợi, Quận 3', 1, 'STAFF001', 2,
        'Cuộc hẹn tư vấn thẻ tín dụng', '2024-01-06', 1, 'STAFF001', 2, 1, 'Tư vấn sản phẩm thẻ tín dụng', 'A',
        'Tư vấn thẻ - Trần Thị B', 1),
       (3, '2024-01-03 00:00:00', 2, null, null, '2024-01-03 00:00:00', 2, '789 Hai Bà Trưng, Quận 1', 2, 'STAFF002', 3,
        'Cuộc hẹn tư vấn vay mua nhà', '2024-01-07', 2, 'STAFF002', 3, 1, 'Tư vấn sản phẩm vay mua nhà', 'A',
        'Tư vấn vay - Lê Văn C', 1);

-- Seed data for SA_APPOINTMENT
INSERT INTO sa_appointment (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, addr_book_id,
                            contact_full_name, contact_mobile, contact_title, cust_full_name, cust_no, deal_id,
                            deal_name, deal_value, due_date_time, duration_min, note, num_of_attendance, priority,
                            status, title, calendar_id, cust_id)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 1, 'Nguyễn Văn A', '0901234567',
        'Khách hàng', 'Nguyễn Văn A', 'CUST001', 1, 'Bảo hiểm nhân thọ - Nguyễn Văn A', 50000000, '2024-01-05 14:00:00',
        60, 'Tư vấn chi tiết về sản phẩm bảo hiểm', 2, 1, 'A', 'Cuộc hẹn tư vấn bảo hiểm', 1, 1),
       (2, '2024-01-02 00:00:00', 1, null, null, '2024-01-02 00:00:00', 1, 2, 'Trần Thị B', '0901234568', 'Khách hàng',
        'Trần Thị B', 'CUST002', 2, 'Thẻ tín dụng - Trần Thị B', 30000000, '2024-01-06 10:00:00', 45,
        'Tư vấn về các loại thẻ tín dụng', 2, 1, 'A', 'Cuộc hẹn tư vấn thẻ', 2, 2),
       (3, '2024-01-03 00:00:00', 2, null, null, '2024-01-03 00:00:00', 2, 3, 'Lê Văn C', '0901234569', 'Khách hàng',
        'Lê Văn C', 'CUST003', 3, 'Vay mua nhà - Lê Văn C', 100000000, '2024-01-07 09:00:00', 90,
        'Tư vấn về gói vay mua nhà', 2, 1, 'A', 'Cuộc hẹn tư vấn vay', 3, 3);

-- Seed data for SA_DEAL_FOLLOWER
INSERT INTO sa_deal_follower (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, branch_code,
                              branch_id, branch_name, deal_id, description, follower_full_name, follower_staff_id,
                              follower_staff_no, follower_type, object_id, object_type, status)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'HN001', 1, 'Chi nhánh Hà Nội', 1,
        'Nhân viên phụ trách chính', 'Nhân viên A', 1, 'STAFF001', 'O', '1', 'DEAL', 'A'),
       (2, '2024-01-02 00:00:00', 1, null, null, '2024-01-02 00:00:00', 1, 'HN001', 1, 'Chi nhánh Hà Nội', 2,
        'Nhân viên phụ trách chính', 'Nhân viên A', 1, 'STAFF001', 'O', '2', 'DEAL', 'A'),
       (3, '2024-01-03 00:00:00', 2, null, null, '2024-01-03 00:00:00', 2, 'HN001', 1, 'Chi nhánh Hà Nội', 3,
        'Nhân viên phụ trách chính', 'Nhân viên B', 2, 'STAFF002', 'O', '3', 'DEAL', 'A'),
       (4, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'HN001', 1, 'Chi nhánh Hà Nội', 1,
        'Nhân viên hỗ trợ', 'Nhân viên C', 3, 'STAFF003', 'F', '1', 'DEAL', 'A');

-- Seed data for SA_CUST_REMIND
INSERT INTO sa_cust_remind (id, created_at, created_by, deleted_at, deleted_by, updated_at, updated_by, cust_full_name,
                            cust_id, cust_no, due_date, payment_value, product_id, reminder_b4_due, reminder_interval,
                            status, calendar_id)
VALUES (1, '2024-01-01 00:00:00', 1, null, null, '2024-01-01 00:00:00', 1, 'Nguyễn Văn A', 1, 'CUST001', '2024-02-01',
        5000000, 1, 7, 30, 'A', 1),
       (2, '2024-01-02 00:00:00', 1, null, null, '2024-01-02 00:00:00', 1, 'Trần Thị B', 2, 'CUST002', '2024-02-15',
        2000000, 3, 5, 15, 'A', 2),
       (3, '2024-01-03 00:00:00', 2, null, null, '2024-01-03 00:00:00', 2, 'Lê Văn C', 3, 'CUST003', '2024-03-01',
        10000000, 7, 10, 30, 'A', 3);

-- Comments for understanding the data structure
-- Status values: A = Active, I = Inactive, D = Deleted, C = Completed
-- Deal status: O = Open, W = Won, L = Lost, C = Closed
-- Gender: M = Male, F = Female
-- Product types: MAIN = Main product, SUB = Sub product, PNT = Point product
-- Follower types: O = Owner, F = Follower, V = Viewer
-- Calendar types: 0 = Event, 1 = Appointment, 2 = Reminder
