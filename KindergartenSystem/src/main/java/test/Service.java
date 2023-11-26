package test;

import Dao.DBRepository;
import Dao.Repository;
import models.Child;
import models.Group;

import java.util.List;

public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public List<Child> getAllChildren() {
        return repository.findChildren();
    }

    public List<Group> getAllGroups() {
        return repository.findGroups();
    }
    public Child getChildById(int id) {
        return repository.findChild(id);
    }
    public Group getGroupById(int id) {
        return  repository.findGroup(id);
    }

    public void saveChild(Child child) {
        repository.saveChild(child);
    }

    public void removeChild(int id) {
        repository.removeChild(id);
    }

    public void updateChild(int id, Child child) {
        repository.updateChild(id, child);
    }

    public void updateGroup(int id, Group group) {
        repository.updateGroup(id,group);
    }

    public void saveGroup(Group group) {
        repository.saveGroup(group);
    }

    public void removeGroup(int id) {
        repository.removeGroup(id);
    }

    public void addToGroup(int childId, int groupId) {
        repository.add(childId, groupId);
    }

    public void removeFromGroup(int childId) {
        repository.removeFromGroup(childId);
    }

    public void showAll() {
        repository.show();
    }

    public void showById(int id) {
        repository.show(id);
    }
}
