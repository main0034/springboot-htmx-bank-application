create table accounts (
    account_id uuid primary key,
    customer_id uuid not null,
    balance_in_cents bigint not null,

    constraint accounts_customers foreign key (customer_id) references customers(customer_id)
);
