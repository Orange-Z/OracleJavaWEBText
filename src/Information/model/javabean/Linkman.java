package Information.model.javabean;

public class Linkman {
    private int linkmanId;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private String group;
    private long userId;

    public long getLinkmanId() {
        return linkmanId;
    }

    public void setLinkmanId(int linkmanId) {
        this.linkmanId = linkmanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
