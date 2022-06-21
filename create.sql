
    create table excercises (
       id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table food (
       id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table user (
       email varchar(255) not null,
        firstname varchar(255) not null,
        id integer,
        lastname varchar(255) not null,
        password varchar(255) not null,
        primary key (email)
    ) engine=InnoDB;

    create table user_excercises (
       date date,
        reps integer,
        sets integer,
        user_id varchar(255) not null,
        excercise_id integer not null,
        primary key (user_id, excercise_id)
    ) engine=InnoDB;

    create table user_food (
       date date not null,
        calories integer,
        servings integer,
        food_id integer not null,
        user_id varchar(255) not null,
        primary key (user_id, food_id, date)
    ) engine=InnoDB;

    alter table user_excercises 
       add constraint FKmietq4kwqvpnnya9xpsinhl8j 
       foreign key (user_id) 
       references user (email);

    alter table user_excercises 
       add constraint FK639jechtdrsp01k1no3hx8uri 
       foreign key (excercise_id) 
       references excercises (id);

    alter table user_food 
       add constraint FK1g8eq16xsqum2d2ojkk3hx4x9 
       foreign key (food_id) 
       references food (id);

    alter table user_food 
       add constraint FKcljbolfn2gnq75ujw985r4aa7 
       foreign key (user_id) 
       references user (email);
