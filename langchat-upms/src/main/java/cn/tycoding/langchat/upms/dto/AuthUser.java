package cn.tycoding.langchat.upms.dto;

import lombok.Getter;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Getter
public class AuthUser {
    private static final long serialVersionUID = -1462618142392426177L;

    private final Long id;

    private final Long deptId;

//    public AuthUser(Long id, Long deptId, String username, String password, boolean enabled,
//                    boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
//                    Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//        this.id = id;
//        this.deptId = deptId;
//    }


    public AuthUser(Long id, Long deptId) {
        this.id = id;
        this.deptId = deptId;
    }
}
