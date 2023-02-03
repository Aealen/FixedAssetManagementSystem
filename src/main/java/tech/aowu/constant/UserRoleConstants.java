package tech.aowu.constant;

/**
 * @Description: TODO
 * @ClassName: UserRoleConstants
 * @Author: Aealen
 * @Date: 2023/2/3 21:39
 */
public class UserRoleConstants {
    private UserRoleConstants() {
        throw new IllegalStateException("Cannot create instance of static constant class");
    }

    /**
     * Roles
     */
    public static final String ROLE_WORKER="ROLE_WORKER";
    public static final String ROLE_ADMIN="ROLE_ADMIN";
    public static final String ROLE_REPORTER="ROLE_REPORTER";
    public static final String ROLE_CUSTODIAN="ROLE_CUSTODIAN";

    /**
     * 当 Remember 是 false 时，token 有效时间 2 小时
     */
    public static final long EXPIRATION_TIME = 60 * 60 * 2L;

    /**
     * 当 Remember 是 true 时，token 有效时间 7 天
     */
    public static final long EXPIRATION_REMEMBER_TIME = 60 * 60 * 24 * 7L;

}
