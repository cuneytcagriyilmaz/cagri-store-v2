//package com.cagri.ecommerce.cagristore.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "roles", schema = "cagristorev2")
//public class Role implements GrantedAuthority {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "role_id")
//    private Long roleId;
//
//
//    @Column(name = "authority")
//    private String authority;
//
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users = new HashSet<>();
//
//
//}

package com.cagri.ecommerce.cagristore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles", schema = "cagristorev2")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @NotBlank(message = "Role boş olamaz")
    @Size(max = 50, message = "Role en fazla 50 karakter olabilir")
    @Column(name = "authority", nullable = false, unique = true)
    private String authority;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return authority;
    }
}
