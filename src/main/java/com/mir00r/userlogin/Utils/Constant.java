package com.mir00r.userlogin.Utils;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
public class Constant {
    public static final String USERS = "users";
    public static final String ROLES = "roles";
    public static final String MODE = "mode";

    public enum ATTRIBUTE_NAME {
        rule(1, "rule"), auth(2, "auth"), control(3, "control");
        private final long id;
        private final String name;

        ATTRIBUTE_NAME(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public enum ROLE_TYPE {
        admin(1L, "ADMIN"), user(2L, "USER");
        private final long roleId;
        private final String roleName;

        ROLE_TYPE(long roleId, String roleName) {
            this.roleId = roleId;
            this.roleName = roleName;
        }

        public long getRoleId() {
            return roleId;
        }

        public String getRoleName() {
            return roleName;
        }
    }

    public enum ACTION_MODE {
        newMode(1, "MODE_NEW"), allMode(2, "MODE_ALL"),
        updateMode(3, "MODE_UPDATE"), infoMode(4, "MODE_INF"),
        detailsMode(5, "MODE_DETAILS"), passMode(6, "MODE_PASS"),
        editMode(7, "MODE_EDIT"), commentMode(7, "MODE_COMMENT");
        private final long Id;
        private final String Name;

        ACTION_MODE(long id, String name) {
            Id = id;
            Name = name;
        }

        public long getId() {
            return Id;
        }

        public String getName() {
            return Name;
        }
    }

    public enum Privileges {
        ADMINISTRATION("Administration"),
        ACCESS_USER_RESOURCES("Access User Resources");

        private final String roleName;

        Privileges(String roleName) {
            this.roleName = roleName;
        }

        public String getRoleName() {
            return roleName;
        }
    }
}
