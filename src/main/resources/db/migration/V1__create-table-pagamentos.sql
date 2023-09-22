create table pagamentos(

    payment_code integer not null unique,
    document varchar(20) not null,
    payment_method varchar(100) not null,
    card_number varchar(16),
    payment_value numeric(10,2) not null,

    primary key(payment_code)
);