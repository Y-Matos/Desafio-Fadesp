create table pagamentos(

    codigo_pagamento integer not null unique,
    documento varchar(20) not null,
    metodo_pagamento varchar(100) not null,
    numero_cartao varchar(16),
    valor_pagamento numeric(10,2) not null,

    primary key(codigo_pagamento)
);