package com.jkkp.modules.order.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "refund_reply")
public class RefundReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 退款单号
     */
    @Column(name = "refund_id")
    private Integer refundId;

    /**
     * 3个角色:1用户2供货商3管理后台4系统触发
     */
    @Column(name = "reply_role")
    private Byte replyRole;

    /**
     * 回复人名称
     */
    @Column(name = "reply_name")
    private String replyName;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 退款凭证
     */
    private String pid;

    /**
     * 操作类型1同意-1 拒绝
     */
    @Column(name = "operate_status")
    private Byte operateStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取退款单号
     *
     * @return refund_id - 退款单号
     */
    public Integer getRefundId() {
        return refundId;
    }

    /**
     * 设置退款单号
     *
     * @param refundId 退款单号
     */
    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    /**
     * 获取3个角色:1用户2供货商3管理后台4系统触发
     *
     * @return reply_role - 3个角色:1用户2供货商3管理后台4系统触发
     */
    public Byte getReplyRole() {
        return replyRole;
    }

    /**
     * 设置3个角色:1用户2供货商3管理后台4系统触发
     *
     * @param replyRole 3个角色:1用户2供货商3管理后台4系统触发
     */
    public void setReplyRole(Byte replyRole) {
        this.replyRole = replyRole;
    }

    /**
     * 获取回复人名称
     *
     * @return reply_name - 回复人名称
     */
    public String getReplyName() {
        return replyName;
    }

    /**
     * 设置回复人名称
     *
     * @param replyName 回复人名称
     */
    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    /**
     * 获取回复内容
     *
     * @return content - 回复内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置回复内容
     *
     * @param content 回复内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取退款凭证
     *
     * @return pid - 退款凭证
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置退款凭证
     *
     * @param pid 退款凭证
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取操作类型1同意-1 拒绝
     *
     * @return operate_status - 操作类型1同意-1 拒绝
     */
    public Byte getOperateStatus() {
        return operateStatus;
    }

    /**
     * 设置操作类型1同意-1 拒绝
     *
     * @param operateStatus 操作类型1同意-1 拒绝
     */
    public void setOperateStatus(Byte operateStatus) {
        this.operateStatus = operateStatus;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}