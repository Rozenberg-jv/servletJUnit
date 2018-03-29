package by.kolbun.andersen.blogic.entity;

import by.kolbun.andersen.blogic.entity.exceptions.TranshInvalidValuesException;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Table(name = "transactions")
public class Transh {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    private Account sender;
    @OneToOne
    private Account receiver;
    @Column(name = "amount")
    private BigInteger amount;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private TranshStatus status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    public Transh() {
    }

    public Transh(Account sender, Account receiver, BigInteger amount) throws TranshInvalidValuesException {
        if (amount.compareTo(new BigInteger("0")) < 0)
            throw new TranshInvalidValuesException("Transh amount is negative");
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.date = new Date();
        status = TranshStatus.NONE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public TranshStatus getStatus() {
        return status;
    }

    public void setStatus(TranshStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
