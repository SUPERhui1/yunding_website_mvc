package cn.yunding.website.entity;

import java.util.List;

/**
 * @author super hui
 */
public class QueryVo {
    private Work work;
    private List idsList;
    private List<Member> members;

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public List getIdsList() {
        return idsList;
    }

    public void setIdsList(List idsList) {
        this.idsList = idsList;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
