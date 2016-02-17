package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

        import java.util.ArrayList;
        import java.util.List;

public class UserList {
    private List<User> userList;
    public UserList(){
        userList= new ArrayList<User>();
    }
    public void addUser(User aUser){ userList.add(aUser);}
    public User getUser(int i){
        return userList.get(i);
    }
    public List<User> tos(){
        return this.userList;
    }
    public int getTotalNum(){
        return userList.size();
    }
}

