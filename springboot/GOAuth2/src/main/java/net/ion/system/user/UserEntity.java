package net.ion.system.user;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="chat", type="users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 610130343373451059L;

	@Id private String id;
	private String password;
	private String fullname;
	private String email;
	private String phone;
	
	private Date createDate=new Date();
	private Date updateDate=new Date();
	
	private RoleEnum role;
	boolean isEnabled;

}
