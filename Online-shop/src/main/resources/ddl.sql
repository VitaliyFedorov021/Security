

create table customer
(
    id           int primary key auto_increment,
    email        varchar(255) unique,
    password     varchar(255)        not null,
    firstName    varchar(255)        not null,
    lastName     varchar(255)        not null,
    gender       varchar(10)         not null,
    birthdayDate date                not null,
    phoneNumber  varchar(100) unique not null
);

create table address
(
    id         int primary key auto_increment,
    street     varchar(255) unique not null,
    postalCode varchar(255)        not null,
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
    totalPrice double              not null,
    placedDate date                not null,
    customerId int                 not null,
    foreign key (customerId) references customer (id) ON DELETE NO ACTION,
    addressId  int                 not null,
    foreign key (addressId) references address (id) ON DELETE CASCADE
);


create table cartEntry
(
    id          int primary key auto_increment,
    entryNumber int    not null,
    quantity    int    not null,
    totalPrice  double not null,
    cartId      int    not null,
    foreign key (cartId) references cart (id) ON DELETE NO ACTION,
    productId int not null,
    foreign key (productId) references product(id) ON DELETE NO ACTION
);

create table product
(
    id          int primary key auto_increment,
    code        varchar(255) unique not null,
    name        varchar(100)        not null,
    price       double              not null,
    description varchar(255)        not null,
    categoryId  int                 not null,
    foreign key (categoryId) references category (id) ON DELETE NO ACTION
);



