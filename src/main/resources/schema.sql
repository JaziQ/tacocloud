
create table if not exists ingredient (
                                          id varchar(4) not null PRIMARY KEY,
                                          name varchar(25) not null,
                                          type varchar(10) not null
);

-- Таблица Taco
create table if not exists taco (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    name varchar(50) not null,
                                    createdAt timestamp not null
);


create table if not exists taco_ingredients (
                                                taco INT not null,
                                                ingredient varchar(4) not null,
                                                primary key (taco, ingredient)
);


alter table taco_ingredients
    add foreign key (taco) references taco(id),
    add foreign key (ingredient) references ingredient(id);


create table if not exists taco_order (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          name varchar(50) not null,
                                          street varchar(50) not null,
                                          city varchar(50) not null,
                                          state varchar(20) not null,
                                          zip varchar(10) not null,
                                          ccNumber varchar(16) not null,
                                          ccExpiration varchar(5) not null,
                                          ccCVV varchar(3) not null,
                                          placedAt timestamp not null
);


create table if not exists taco_order_tacos (
                                                tacoOrder INT not null,
                                                taco INT not null,
                                                primary key (tacoOrder, taco)
);


alter table taco_order_tacos
    add foreign key (tacoOrder) references taco_order(id),
    add foreign key (taco) references taco(id);