use shop;

create table cart
(
    code       int primary key auto_increment,
    totalPrice int,
    placedDate date not null
);

create table customer
(
    email        varchar(255) primary key,
    password     varchar(255)        not null,
    firstName    varchar(255)        not null,
    lastName     varchar(255)        not null,
    gender       varchar(10)         not null,
    birthdayDate date                not null,
    phoneNumber  varchar(100) unique not null,
    cartCode     int unique,
    foreign key (cartCode) references cart (code) ON DELETE NO ACTION #One to one rel. customer and his cart
);

create table category
(
    code int primary key auto_increment,
    name varchar(100) unique not null
);


create table product
(
    code         int primary key,
    name         varchar(100) not null,
    price        int          not null,
    description  varchar(255) not null,
    categoryCode int          not null,
    foreign key (categoryCode) references category (code) ON DELETE NO ACTION #one to one rel. product and its category
);

create table cart
(
    code       int primary key auto_increment,
    totalPrice int  not null,
    placedDate date not null
);

create table cartEntry
(
    entryNumber int primary key auto_increment,
    quantity    int not null,
    totalPrice  int not null,
    cartCode    int not null,
    foreign key (cartCode) references cart (code) ON DELETE NO ACTION,      #many to one rel. 1 cart and many entries
    productCode int not null,
    foreign key (productCode) references product (code) ON DELETE NO ACTION #one to one rel. one product for each entry
);

create table address
(
    addressEntryId int primary key auto_increment,
    street         varchar(255) unique not null,
    town           varchar(20)         not null,
    region         varchar(20)         not null,
    country        varchar(20)         not null,
    customerId     varchar(255)        not null,
    foreign key (customerId) references customer (email) ON DELETE NO ACTION #one to one rel. customer has one address.
);
