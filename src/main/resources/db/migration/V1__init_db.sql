create table if not exists customer
(
    id       uuid default gen_random_uuid()
        primary key,
    surname  text not null,
    home     text not null,
    discount numeric(5, 2)
        constraint customer_discount_check
            check ((discount >= 0.00) AND (discount <= 100.00))
);

create index if not exists customer_surname_index on customer (surname);
create index if not exists customer_home_index on customer (home);

create table if not exists shop
(
    id         uuid default gen_random_uuid()
        primary key,
    name       text not null,
    district   text not null,
    commission numeric(5, 2)
        constraint shop_commission_check
            check ((commission >= 0.00) AND (commission <= 100.00))
);

create index if not exists shop_name_index on shop (name);
create index if not exists shop_district_index on shop (district);


create table if not exists book
(
    id       uuid default gen_random_uuid()
        primary key,
    title    text    not null,
    price    numeric not null,
    stock    text    not null,
    quantity bigint
        constraint book_quantity_check
            check (book.quantity >= 0)
);

create index if not exists book_title_index on book (title);
create index if not exists book_stock_index on book (stock);


create table if not exists purchase
(
    id                uuid                                                 default gen_random_uuid()
        primary key,
    number            serial,
    created_timestamp timestamp with time zone                    not null default now(),
    shop_id           uuid references shop (id) on delete cascade not null,
    customer_id       uuid
        references customer (id) on delete cascade                not null,
    book_id           uuid
        references book (id) on delete cascade                    not null,
    quantity          bigint
        constraint purchase_quantity_check
            check (quantity > 0),
    total_price       numeric                                     not null
        constraint purchase_total_price_check
            check (total_price >= 0)
);

create index if not exists purchase_fks_index on purchase (shop_id, customer_id, book_id);