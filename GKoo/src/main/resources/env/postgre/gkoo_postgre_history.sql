-- 10.10.2020

SELECT bs.object_id, bs.userid, bs.orderid, bs.buying_price, bs.order_date, bs.ship_price, bs.box_actual_weight, bs.box_volume_weight, bs.buying_service_state,
		bsp.buying_service_payment_state FROM BUYING_SERVICE bs, BUYING_SERVICE_PAYMENT bsp WHERE bs.object_id = bsp.fk_buying_service and bs.object_id = 23;	

UPDATE buying_service SET ship_price = 13000,
		box_actual_weight = 1, 
		box_volume_weight = 1, 
		buying_service_state = 3 
		WHERE object_id = 23;
UPDATE buying_service_payment SET buying_service_payment_state = 3 WHERE fk_buying_service = 23;

-- 01.10.2020
insert into payment_history_transfer(userid, payment_date, transfer_deposit_payment, buying_payment, orderid, pd_itemname) 
values (
	'ekgml54', 
	'2020-09-23',
	53000,
	53000,
	'20200923141800',
	'Data Squad Manga Comic Nr. 3 2008 Panini Comics inkl. 2 Poster RARITÄT'
)

ALTER TABLE payment_history_transfer
ALTER COLUMN pd_itemname TYPE VARCHAR(100);

-- 27.09.2020
----[CREATE_BUYING_SERVICE]
insert into buying_service(userid, orderid, buying_price, buying_service_state, shop_url, order_date, product_list_total_price) 
values (
	'ekgml54', 
	'20200923141800', 
	53000, 
	1,
	'https://www.ebay.de/itm/Digimon-Data-Squad-Manga-Comic-Nr-3-2008-Panini-Comics-inkl-2-Poster-RARITAT/164246229656?hash=item263dd69298:g:vRUAAOSw8RFeaz0G',
	'2020-09-23',
	32.99
) RETURNING buying_service.object_id; -- object_id 23

----[CREATE_BUYING_SERVICE_RECIPIENT]
insert into buying_service_recipient(name_kor, name_eng, transit_nr, 
									 phonenumber_first, phonenumber_second, zip_code, address, userComment, fk_buying_service) 
values ('김다희', '', 'P170019046005', '010-4614-6963', '', '05396', '서울특별시 강동구 성내로15길 10-6 드림하이츠 105동 402호', '', 23);

----[CREATE_BUYING_SERVICE] 입금확인후
UPDATE buying_service SET buying_service_state = 2 
		WHERE object_id = 23;
		
----[CREATE_BUYING_SERVICE_PAYMENT]
	--구매대행 결제대기    
    --PRODUCT_PAYMENT_READY(1),
    
    --구매대행 결제완료(관리자결제확인함)
    --PRODUCT_PAYMENT_COMPLETION(2)
insert into buying_service_payment(buying_service_payment_state, fk_buying_service,  
								   buying_deposit_ownername, payment_art, buying_deposit_payment, buying_payment_date) 
								   values(2, 23, '김다희', 1, 53000, '2020-09-23')	

----[CREATE_BUYING_PRODUCT]
insert into buying_service_product(pd_categorytitle, pd_itemtitle, pd_brandname, pd_itemname, pd_amount, pd_price, 
								   pd_totalprice, fk_buying_service)
values('[목록]서적/CD', '책/잡지류', 'Digimon', 'Data Squad Manga Comic Nr. 3 2008 Panini Comics inkl. 2 Poster RARITÄT', 1, 29.99,   29.99, 23)


---- 메인페이지 대표이미지 URL 링크걸기
UPDATE buying_service SET main_image_url = 'https://i.ebayimg.com/images/g/vRUAAOSw8RFeaz0G/s-l1600.jpg'
		WHERE object_id = '23';
		
---- 상세페이지 이미지 URL 링크걸기
UPDATE buying_service_product SET pd_image_url = 'https://i.ebayimg.com/images/g/vRUAAOSw8RFeaz0G/s-l1600.jpg'
		WHERE fk_buying_service = '23';
		
-- 26.09.2020
ALTER TABLE buying_service_product
ALTER COLUMN pd_itemname TYPE VARCHAR(100);

	-- for test character with korean
INSERT INTO test_table (itemtitle) VALUES ('일이삼사오육칠팔구십');
INSERT INTO test_table (itemtitle) VALUES ('일이삼사오육칠팔구십일');
create table test_table (
    object_id serial primary key,
    itemtitle VARCHAR(10)
)
	
-- 23.09.2020
ALTER TABLE buying_service
ALTER COLUMN shop_url TYPE text;

-- 30.08.2020
update auction_bid set auction_result = '2' where   object_id = '3'

update auction_bid set deleted = false where object_id = '7'

-- 29.08.2020
ALTER TABLE auction_bid
ADD COLUMN auction_result numeric(2, 0);

create table auction_bid (
    object_id serial primary key,
    userid VARCHAR(50) NOT NULL,
    product_url text,
    auction_bid_date date,
    bid_value numeric,
    auction_message text,
	deleted BOOLEAN)

drop table auction_bid;

ALTER TABLE auction_bid
ADD COLUMN deleted BOOLEAN;

-- 27.08.2020
create table auction_bid (
    object_id serial primary key,
    userid VARCHAR(50) NOT NULL,
    product_url text,
    auction_bid_date date,
    bid_value numeric,
    auction_message text)

ALTER TABLE buying_service
ADD COLUMN main_image_url text;

ALTER TABLE buying_service_product
ADD COLUMN pd_image_url text;