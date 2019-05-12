package com.diabin.latte.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user_profile")
public class UserProfile {

    @Id
    private long userId = 0;
    private String userName = null;
    private String phone = null;
    private String avatar = null;
    private String sex = null;
    private String nickName = null;
    @Generated(hash = 2062424379)
    public UserProfile(long userId, String userName, String phone, String avatar,
            String sex, String nickName) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.avatar = avatar;
        this.sex = sex;
        this.nickName = nickName;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


}
