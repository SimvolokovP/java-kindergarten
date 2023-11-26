package Dao;

import models.Child;
import models.Group;

import java.util.List;

public interface Repository {
    Child findChild(int id);
    List<Child> findChildren();
    void saveChild(Child child);
    void updateChild(int id,Child child);
    void removeChild(int id);
    Group findGroup(int id);
    List<Group> findGroups();
    void saveGroup(Group group);
    void updateGroup(int id,Group group);
    void removeGroup(int id);
    void add(int childId, int groupId);
    void removeFromGroup(int childId);
    void show();
    void show(int groupId);
}
