package kr.grid.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonDeserialize
@Document(indexName="user", type="authority", shards =1, /* replicas = 0,*/ refreshInterval = "-1")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 7789806102090637493L;

	// @Id private String id;

	@Id private String authority;
	
	public Authority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

}