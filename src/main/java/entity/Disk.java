package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Arkkienkeli on 16.12.2014.
 */
@Entity
@Table(name="Disk")
public class Disk implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
