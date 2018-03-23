package by.kolbun.andersen.blogic.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "money", nullable = false)
    private BigInteger money;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
//    @OneToMany(targetEntity = Transh.class, cascade = CascadeType.ALL)
    /*@JoinTable(name = "transhes",
            joinColumns = {@JoinColumn(name = "accounts.id")}, inverseJoinColumns = {@JoinColumn(name = "transhes.id")})*/
//    private final Set<Transh> transhes = new HashSet<>();

    public Account() {
    }

    // TODO: 23.03.2018 добавить атрибут blocked и функционал управления

    public Account(BigInteger money, User user) {
        this.money = money;
        this.user = user;
        this.status = AccountStatus.ACTIVE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getMoney() {
        return money;
    }

    public void setMoney(BigInteger money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.update(user);
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

/* public Set<Transh> getTranshes() {
        return transhes;
    }

    public void setTranshes(Set<Transh> newTranshes) {
        transhes.clear();
        transhes.addAll(newTranshes);
    }

    *//**//*
    public void addTransh(Transh t) {
        this.transhes.add(t);
    }*/

    public void switchStatus() {
        if (status == AccountStatus.ACTIVE) status = AccountStatus.BLOCKED;
        else status = AccountStatus.ACTIVE;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "[" + Integer.toHexString(this.hashCode()) + "]" +
                " - id:" + id +
                ", money: " + money +
                ", User: " + user +
                " " + (status == AccountStatus.BLOCKED ? "[blocked]" : "[active]");
    }
}
