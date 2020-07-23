package edu.learning.tyh.pojo;

import java.io.Serializable;

public class TyhUsers implements Serializable {
	
    @Override
	public String toString() {
		return "TyhUsers={userid:" + userid + ", username:" + username + ", userpwd:" + userpwd + ", usertruename:"
				+ usertruename + ", userstate:" + userstate + "}";
	}

	private String userid;

    private String username;

    private String userpwd;

    private String usertruename;

    private Integer userstate;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getUsertruename() {
        return usertruename;
    }

    public void setUsertruename(String usertruename) {
        this.usertruename = usertruename == null ? null : usertruename.trim();
    }

    public Integer getUserstate() {
        return userstate;
    }

    public void setUserstate(Integer userstate) {
        this.userstate = userstate;
    }
}