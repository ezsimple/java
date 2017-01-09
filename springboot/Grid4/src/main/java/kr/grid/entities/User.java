package kr.grid.entities;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize
@Document(indexName="user", type="account", shards =1, /* replicas = 0,*/ refreshInterval = "-1")
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4146321671411592113L;

	// @Id private String id;
	@Id private String username;
	private String fullname;
	private String password;
	// private List<Group> groups;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    // @Field(type = FieldType.Auto)
    private Collection<Authority> authorities;
	
}
