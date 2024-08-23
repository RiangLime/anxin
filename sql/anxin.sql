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

-- 分销系统
create table Distribute_Level
(
    level_id    int not null primary key comment '等级 1 2 3 ...',
    level_name  nvarchar(64) default '默认' comment '等级名称',
    rate1       int          default 0 comment '一级佣金比例 百分比',
    rate2       int          default 0 comment '二级佣金比例 百分比',
    rate3       int          default 0 comment '自购佣金比例 百分比',
    gmt_created timestamp    default CURRENT_TIMESTAMP comment '创建时间'
);

create table Distribute_Invite_Relation
(
    id          bigint primary key comment 'id',
    user_id     bigint not null comment '用户ID',
    inviter_id  bigint not null comment '上级ID',
    gmt_created timestamp default CURRENT_TIMESTAMP comment '创建时间'
);

create table Distribute_Application
(
    id           bigint primary key comment '申请ID',
    user_id      bigint         not null comment '用户ID',
    real_name    nvarchar(64)   not null comment '真实姓名',
    phone        nvarchar(64)   not null comment '联系电话',
    region       nvarchar(128)  null comment '所属地区',
    reason       nvarchar(2048) null comment '申请理由',
    state        tinyint   default 0 comment '申请状态 0待同意 1已批准',
    approve_time bigint         null comment '批准时间',
    remark       nvarchar(256)  null comment '管理员备注',
    gmt_created  timestamp default CURRENT_TIMESTAMP comment '创建时间'
);

create table Distribute_User
(
    user_id       bigint primary key comment '用户ID',
    level_id      int not null comment '等级ID',
    assets_get    int not null default 0 comment '已入账佣金',
    assets_remain int not null default 0 comment '待入账佣金',
    gmt_created   timestamp    default CURRENT_TIMESTAMP comment '创建时间',
    gmt_modified  timestamp    default null on update CURRENT_TIMESTAMP comment '修改时间'
);

create table Distribute_Product
(
    id          bigint primary key comment '分销商品ID',
    product_id  bigint not null comment '参与分销商品ID',
    sku_id      bigint not null comment '参与分销商品SkuId',
    gmt_created timestamp default CURRENT_TIMESTAMP comment '创建时间'
);

create table Distribute_Order_Log
(
    id          bigint primary key comment 'ID',
    order_id    bigint              not null comment '订单ID',
    op_type     tinyint             not null comment '1订单完成 2退款',
    user_id     bigint              not null comment '相关的分销商用户ID',
    amount      int       default 0 not null comment '涉及分销金额',
    gmt_created timestamp default CURRENT_TIMESTAMP comment '创建时间'
);