create table Qrcode(
    id bigint not null primary key comment '二维码ID',
    product_id bigint not null comment '关联的商品 ID',
    sku_id bigint not null comment '关联的SKU ID',
    code nvarchar(128) not null comment '数字编码',
    qrcode nvarchar(512) not null comment '二维码',
    bind_user_id bigint null comment '绑定的用户ID',
    bind_time timestamp null comment '用户绑定时间',
    gmt_created timestamp default CURRENT_TIMESTAMP comment '创建时间',
    gmt_modified timestamp default null on update CURRENT_TIMESTAMP comment '修改时间'
)