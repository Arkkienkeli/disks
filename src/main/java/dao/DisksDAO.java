package dao;

import entity.Disk;
import entity.TakenItem;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by Arkkienkeli on 17.12.2014.
 */
@Repository
public class DisksDAO implements IDisksDao {
    @Autowired
    SessionFactory sf;

    public List<Disk> getAllDisks() {
        Session s  = sf.getCurrentSession();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        try {
            //return (List<Disk>) s.createSQLQuery("SELECT disk.id, disk.title, disk.user_id FROM DISK inner join TAKENITEM where dISK_ID != disk.ID").list();
            //return (List<Disk>) s.createQuery("from Disk d inner join d.TakenItem t where t.disk_id = d.id").list();
            List<Disk> taken =  (List<Disk>) s.createQuery("select d from TakenItem t inner join t.disk d where t.disk.id = d.id").list();
            List<Disk> all = (List<Disk>) s.createQuery("from Disk where user.username != :name").setString("name", name).list();
            all.removeAll(taken);
            return all;
            //return (List<Disk>) s.createQuery("from Disk where user.username != :name").setString("name", name).list();
        }
        catch (HibernateException ex) {
            return Collections.emptyList();
        }
    }


    public List<Disk> getUserDisks() {
        Session s  = sf.getCurrentSession();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        try {
            return (List<Disk>) s.createQuery("from Disk where user.username = :name").setString("name", name).list();
        }
        catch (HibernateException ex) {
            return Collections.emptyList();
        }
    }


    public TakenItem takeDisk(Disk disk) {
        Session s  = sf.getCurrentSession();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = (User) s.createQuery("from User where username = :name").setString("name", name).uniqueResult();
        TakenItem ti = new TakenItem();
        ti.setDisk(disk);
        ti.setUser(user);
        s.save(ti);
        return ti;
    }

    public List<TakenItem> getTakenFromUserDisks() {
        Session s  = sf.getCurrentSession();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        try {
            return (List<TakenItem>) s.createQuery("from TakenItem where disk.user.username = :name").setString("name", name).list();
        }
        catch (HibernateException ex) {
            return Collections.emptyList();
        }

    }

    public List<TakenItem> getTakenDisksByUser() {
        Session s  = sf.getCurrentSession();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        try {
            return (List<TakenItem>) s.createQuery("from TakenItem where user.username = :name").setString("name", name).list();
        }
        catch (HibernateException ex) {
            return Collections.emptyList();
        }

    }

    public TakenItem returnDisk(Disk disk) {
        Session s  = sf.getCurrentSession();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Integer disk_id = disk.getId();
        TakenItem ti = (TakenItem) s.createQuery("from TakenItem where user.username = :name and disk.id = :disk_id").setString("name", name).setInteger("disk_id", disk_id).uniqueResult();
        s.delete(ti);
        return ti;

    }


}
