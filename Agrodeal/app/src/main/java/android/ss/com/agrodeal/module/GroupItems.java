package android.ss.com.agrodeal.module;

import java.io.Serializable;

/**
 * Created by abc on 1/2/18.
 */

public class GroupItems implements Serializable {

    String groupId;
    String groupName;
    String userId;
    String groupDest;
//    ArrayList<MessageItems> arrayList=new ArrayList<>();

//    public GroupItems(String groupId, String groupName, String userId, ArrayList<MessageItems> arrayList) {
//        this.groupId = groupId;
//        this.groupName = groupName;
//        this.userId = userId;
//        this.arrayList = arrayList;
//    }
//
//    public ArrayList<MessageItems> getArrayList() {
//        return arrayList;
//    }

    public String getGroupDest() {
        return groupDest;
    }

    public GroupItems(String groupId, String groupName, String userId, String groupDest) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.userId = userId;

        this.groupDest = groupDest;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getUserId() {
        return userId;
    }
}
