create table transactions (
    transaction_id uuid primary key,
    account_id uuid not null,
    type int not null,
    amount bigint not null,
    timestamp timestamp not null,
    description varchar(200),

    constraint transactions_accounts foreign key (account_id) references accounts(account_id)
);

create index idx_account_id ON transactions(account_id);
