package dao;

import entity.Disk;
import entity.TakenItem;

import java.util.List;

/**
 * Created by Arkkienkeli on 18.12.2014.
 */
public interface IDisksDao {
    public List<Disk> getAllDisks();
    public List<Disk> getUserDisks();
    public TakenItem takeDisk(Disk disk);
    public List<TakenItem> getTakenFromUserDisks();
    public List<TakenItem> getTakenDisksByUser();
    public TakenItem returnDisk(Disk disk);
}
