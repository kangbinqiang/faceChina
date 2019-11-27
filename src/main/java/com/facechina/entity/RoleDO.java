package test.domain;

public class RoleDO {
    private Integer id;

    private String roleId;

    private String roleName;

    private String roleDescription;

    private byte[] roleCreatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription == null ? null : roleDescription.trim();
    }

    public byte[] getRoleCreatetime() {
        return roleCreatetime;
    }

    public void setRoleCreatetime(byte[] roleCreatetime) {
        this.roleCreatetime = roleCreatetime;
    }
}