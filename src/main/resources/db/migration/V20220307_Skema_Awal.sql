create table payment_provider(
    id character varying(36) not null,
    code character varying(100) not null,
    name character varying(100) NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) not null,
    updated_at timestamp without time zone,
    updated_by character varying(255),
    logo character varying(255)
);

alter table ONLY payment_provider
    ADD CONSTRAINT payment_provider_pkey PRIMARY KEY (id);

alter table ONLY payment_provider
    ADD CONSTRAINT payment_provider_unique_code UNIQUE (code);

create table invoice_type (
    id character varying(255) not null,
    created_at timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) not null,
    updated_at timestamp without time zone,
    updated_by character varying(255),
    code character varying(100),
    name character varying(100)
);

alter table ONLY invoice_type
    ADD CONSTRAINT invoice_type_pkey PRIMARY KEY (id);

create table invoice_type_provider(
   id_invoice_type  character varying(255) not null,
   id_payment_provider  character varying(36) not null
);

alter table ONLY invoice_type_provider
    ADD CONSTRAINT invoice_table_provider_pkey PRIMARY KEY (id_invoice_type, id_payment_provider);

alter table ONLY invoice_type_provider
    ADD CONSTRAINT fk_invoice_table_provider FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);

alter table ONLY invoice_type_provider
    ADD CONSTRAINT fk_payment_provider FOREIGN KEY (id_payment_provider) REFERENCES payment_provider(id);

create table invoice(
    id character varying(36) not null,
    created_at timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) not null,
    updated_at timestamp without time zone,
    updated_by character varying(255),
    amount numeric(19,2) not null,
    description character varying(255) not null,
    due_date date,
    invoice_number character varying(100) not null,
    paid boolean not null,
    id_invoice_type character varying(255),
    constraint invoice_amount_check check ((amount >= (0)::numeric))
);



alter table ONLY invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (id);

alter table ONLY invoice
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76ffb FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);

create table virtual_account(
    id character varying(36) not null,
    created_at timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) not null,
    updated_at timestamp without time zone,
    updated_by character varying(255),
    account_number character varying(255) not null,
    company_id character varying(255) not null,
    virtual_account_type character varying(255) not null,
    id_invoice character varying(255),
    id_payment_provider character varying(255)
);

alter table ONLY virtual_account
    ADD CONSTRAINT virtual_account_pkey PRIMARY KEY (id);

alter table ONLY virtual_account
    ADD CONSTRAINT fkt3t7f64hvgk4xjblsovqqkpll FOREIGN KEY (id_payment_provider) REFERENCES payment_provider(id);

create table payment(
    id character varying(36) not null,
    created_at timestamp without time zone,
    created_by character varying(255),
    status_record character varying(255) not null,
    updated_at timestamp without time zone,
    updated_by character varying(255),
    amount numeric(19,2) not null,
    provider_reference character varying(255) not null,
    transaction_time timestamp without time zone not null,
    id_virtual_account character varying(255),
    constraint payment_amount_check check ((amount >= (1)::numeric))
);

alter table ONLY payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);

alter table ONLY payment
    ADD CONSTRAINT fkptriq88d7e8io9mhri8p10cq0 FOREIGN KEY (id_virtual_account) REFERENCES virtual_account(id);