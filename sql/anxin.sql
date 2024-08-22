create table DetectOrder
(
    id                     bigint        not null primary key comment '二维码ID',
    is_updated             tinyint       not null default 0 comment '是否是升级的检测',
    product_id             bigint        not null comment '关联的商品 ID',
    sku_id                 bigint        not null comment '关联的SKU ID',
    order_id               bigint        null comment '关联的订单ID',
    code                   nvarchar(128) not null comment '数字编码',
    qrcode                 nvarchar(512) not null comment '二维码',
    bind_user_id           bigint        null comment '绑定的用户ID',
    bind_time              timestamp     null comment '用户绑定时间',
    return_deliver_company nvarchar(128) null comment '回寄物流公司',
    return_deliver_id      nvarchar(64)  null comment '回寄物流单号',
    detect_state           int                    default 0 comment '检测状态 0待采样 1采样完毕 2回寄中 3升级产品未付款待检测 4待检测 5检测中 6已出报告',
    report_title           nvarchar(255) null comment '报告标题',
    report_name            nvarchar(255) null comment '报告名称',
    report_is_normal       tinyint       null comment '是否正常',
    report_time            bigint        null comment '报告时间',
    report_url             nvarchar(255)          default '[]' comment '报告URL JSON List',
    contactor_url          nvarchar(255)          default '[]' comment '医师联系方式 JSON List',
    can_report_update      tinyint                default 0 comment '报告是否可以进行升级 0不可 1可以',
    update_product_id      bigint        null comment '升级产品ID',
    update_sku_id          bigint        null comment '升级SKU ID',
    gmt_created            timestamp              default CURRENT_TIMESTAMP comment '创建时间',
    gmt_modified           timestamp              default null on update CURRENT_TIMESTAMP comment '修改时间'
);

create table HomePageStructure
(
    id          int          not null primary key auto_increment comment 'id',
    structure   text         null comment '首页结构',
    version     nvarchar(64) null comment '首页结构版本号',
    gmt_created timestamp default CURRENT_TIMESTAMP comment '创建时间'
);

create table Advertisement
(
    id          int           not null primary key auto_increment comment 'id',
    title       nvarchar(255) null comment '广告标题',
    type        int       default 1 comment '1广告',
    picture     nvarchar(255) null comment '广告图片',
    gmt_created timestamp default CURRENT_TIMESTAMP comment '创建时间'
);
create table AdStructure
(
    id             int            not null primary key auto_increment comment 'id',
    ad_id          int            not null comment '广告ID',
    serial_id      int            not null comment '广告内部单元序列号',
    struct_type    int            not null comment '内容单元类型 1文字 2图片 3视频 4音频 5推广链接',
    struct_content nvarchar(2048) null comment '广告标题',
    gmt_created    timestamp default CURRENT_TIMESTAMP comment '创建时间'
);
create table AdViewLog
(
    id          bigint primary key comment 'id',
    ad_id       int    not null comment '广告ID',
    user_id     bigint null comment '用户ID',
    gmt_created timestamp default CURRENT_TIMESTAMP comment '创建时间'
);