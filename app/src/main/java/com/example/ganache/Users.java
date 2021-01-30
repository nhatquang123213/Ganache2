package com.example.ganache;

public class Users  {
    int CNIC;
    String username;
    String f_name;
    String l_name;
    String email;
    String phone;
    String password;
    String address;
    private static Users users = null;
    private Users() {
    }
    public static Users getInstance() {
        if (users == null)
        {
            users = new Users();
        }
        return users;
    }

    public int getCNIC() {
        return CNIC;
    }

    public void setCNIC(int CNIC) {
        this.CNIC = CNIC;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Users getUsers() {
        return users;
    }

    public static void setUsers(Users users) {
        Users.users = users;
    }
}
