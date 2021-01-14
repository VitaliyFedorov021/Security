create table customer
(
    id           int primary key auto_increment,
    email        varchar(255) unique,
    password     varchar(255) not null,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    gender       varchar(10)  not null,
    birthday_date date         not null,
    phone_number  varchar(100) not null
);

create table address
(
    id         int primary key auto_increment,
    street     varchar(255) unique not null,
    postal_code varchar(255)        not null,
    town       varchar(20)         not null,
    region     varchar(20)         not null,
    country    varchar(20)         not null
);

create table category
(
    id   int primary key auto_increment,
    code varchar(255) unique not null,
    name varchar(100) unique not null
);

create table cart
(
    id         int primary key auto_increment,
    code       varchar(255) unique not null,
    total_price double              not null,
    placed_date date,
    customer_id int                 not null,
    foreign key (customer_id) references customer (id) ON DELETE NO ACTION,
    address_id  int                 not null,
    foreign key (address_id) references address (id) ON DELETE CASCADE
);


create table cartEntry
(
    id          int primary key auto_increment,
    entry_number int    not null,
    quantity    int    not null,
    total_price  double not null,
    cart_id      int    not null,
    foreign key (cart_id) references cart (id) ON DELETE NO ACTION,
    product_id   int    not null,
    foreign key (product_id) references product (id) ON DELETE NO ACTION
);

create table product
(
    id          int primary key auto_increment,
    code        varchar(255) unique not null,
    name        varchar(100)        not null,
    price       double              not null,
    description varchar(255)        not null,
    category_id  int                 not null,
    foreign key (category_id) references category (id) ON DELETE NO ACTION
);



