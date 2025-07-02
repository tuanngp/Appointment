-- Reset sequences for tables with seed data to avoid duplicate key violations
-- This script should be run after inserting seed data

-- Reset sa_deal sequence
SELECT setval('sa_deal_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM sa_deal), false);

-- Reset sa_appointment sequence  
SELECT setval('sa_appointment_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM sa_appointment), false);

-- Reset sa_deal_stages sequence
SELECT setval('sa_deal_stages_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM sa_deal_stages), false);

-- Reset sa_deal_follower sequence
SELECT setval('sa_deal_follower_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM sa_deal_follower), false);

-- Reset sa_customer sequence
SELECT setval('sa_customer_customer_id_seq', (SELECT COALESCE(MAX(customer_id), 0) + 1 FROM sa_customer), false);

-- Reset sa_product sequence
SELECT setval('sa_product_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM sa_product), false);

-- Reset sa_product_category sequence
SELECT setval('sa_product_category_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM sa_product_category), false);

-- Reset sa_stages sequence
SELECT setval('sa_stages_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM sa_stages), false);

-- Reset sa_address sequence
SELECT setval('sa_address_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM sa_address), false);

-- Verify sequences are reset correctly
SELECT 'sa_deal_id_seq' as sequence_name, last_value FROM sa_deal_id_seq
UNION ALL
SELECT 'sa_appointment_id_seq', last_value FROM sa_appointment_id_seq
UNION ALL  
SELECT 'sa_deal_stages_id_seq', last_value FROM sa_deal_stages_id_seq
UNION ALL
SELECT 'sa_deal_follower_id_seq', last_value FROM sa_deal_follower_id_seq
UNION ALL
SELECT 'sa_customer_customer_id_seq', last_value FROM sa_customer_customer_id_seq
UNION ALL
SELECT 'sa_product_id_seq', last_value FROM sa_product_id_seq
UNION ALL
SELECT 'sa_product_category_id_seq', last_value FROM sa_product_category_id_seq
UNION ALL
SELECT 'sa_stages_id_seq', last_value FROM sa_stages_id_seq
UNION ALL
SELECT 'sa_address_id_seq', last_value FROM sa_address_id_seq;
