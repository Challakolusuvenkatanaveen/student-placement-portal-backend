package com.example.demo.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long roleId;

	    private String roleName;
	    
	    
	    
	    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	    private List<User> users ;



		public Long getRoleId() {
			return roleId;
		}



		public void setRoleId(Long roleId) {
			this.roleId = roleId;
		}



		public String getRoleName() {
			return roleName;
		}



		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}



		public List<User> getUsers() {
			return users;
		}



		public void setUsers(List<User> users) {
			this.users = users;
		}
	    
	    
	
	
	
}
